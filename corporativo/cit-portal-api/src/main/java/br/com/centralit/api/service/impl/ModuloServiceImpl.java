package br.com.centralit.api.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.ModuloDao;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

@Service("moduloService")
public class ModuloServiceImpl extends GenericServiceImpl<Modulo, Long> implements ModuloService {

	/** Atributo moduloDao. */
	private ModuloDao moduloDao;

	/** Atributo listaWAR. */
	private String listaWAR;

	/** Atributo servletContext. */
	@Autowired
	private ServletContext servletContext;

	/** Atributo WAR. */
	private static final String WAR = ".war.";

	/** Atributo isDiretorioStandalone. */
	private Boolean isDiretorioStandalone = true;

	/** Atributo LOG. */
	static final Logger LOG = Logger.getLogger(ModuloServiceImpl.class);

	@Autowired
	public ModuloServiceImpl( ModuloDao moduloDao, @Qualifier("moduloValidator") Validator validator ) {

		this.dao = moduloDao;

		this.moduloDao = moduloDao;

		this.validator = validator;

	}

	@Override
	public Modulo save(Modulo entity) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(entity, this.validator);
		}

		if (this.moduloDao.existeNomeModulo(entity)) {

			throw new BusinessException("VALIDACAO.NOME_EXISTENTE", CodigoErro.REGRA_NEGOCIO.getValue());
		}

		return super.save(entity);
	}

	@Override
	public Modulo merge(Modulo entity) {

		// VALIDA CAMPO OBRIGATÓRIO DA ENTIDADE
		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(entity, this.validator);
		}

		Modulo moduloPorNome = (Modulo) this.moduloDao.getModuloPorNome(entity.getNome());

		if (this.moduloDao.existeNomeModulo(entity) && ( UtilObjeto.isReferencia(moduloPorNome) && !moduloPorNome.getId().equals(entity.getId()) )) {

			throw new BusinessException("VALIDACAO.NOME_EXISTENTE", CodigoErro.REGRA_NEGOCIO.getValue());
		}

		this.obterWARJBoss();

		if (entity != null && entity.getHabilitado() && isDiretorioStandalone) {

			this.validarModuloServidor(entity);

		} else if(entity != null && entity.getHabilitado() && !isDiretorioStandalone){

			throw new BusinessException("VALIDACAO.JBOSS_ERRO_LEITURA_WAR", CodigoErro.REGRA_NEGOCIO.getValue());
		}

		return super.merge(entity);

	}

	@Override
	public List<Modulo> getModulosAtivos() {

		return this.moduloDao.getModulosAtivos();
	}

	@Override
	public Modulo moduloEstaAtivo(String baseUrl) {

		return this.moduloDao.moduloEstaAtivo(baseUrl);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de neg�cio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * M�todo respons�vel por obter m�dulo por baseUrl
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param baseUrl
	 * @return Modulo
	 */
	@Override
	public Modulo getModuloPorBaseUrl(String baseUrl) {

		return this.moduloDao.getModuloPorBaseUrl(baseUrl);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter a baseUrl dos módulos inativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<String>
	 */
	@Override
	public List<String> getBaseUrlModulosInativos() {

		List<String> baseUrlModulosInativos = new ArrayList<String>();

		List<Modulo> modulos = this.moduloDao.getModulosInativos();

		if (!UtilColecao.isVazio(modulos)) {

			for (Modulo modulo : modulos) {

				baseUrlModulosInativos.add(modulo.getBaseUrl());

			}
		}

		return baseUrlModulosInativos;
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter os id dos módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<Long>
	 */
	@Override
	public List<Long> getIdModulosAtivos() {

		return this.moduloDao.getIdModulosAtivos();
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter a lista de *.war do diretório do JBoss
	 *
	 * @author rogerio.cassimiro
	 *
	 */
	private void obterWARJBoss() {

		listaWAR = new String();

		try {

			String pathJBoss = servletContext.getRealPath("");

			pathJBoss = pathJBoss.substring(0, pathJBoss.indexOf("standalone")) + "standalone" + System.getProperty("file.separator") + "deployments";

			File diretorioJBoss = new File(pathJBoss);

			String[] arquivos = diretorioJBoss.list();

			for (int i = 0; i < arquivos.length; i++) {
				if (!arquivos[i].contains(WAR)) {
					listaWAR = listaWAR.concat(arquivos[i]).concat(";");
				}
			}

		} catch (Exception e) {
			isDiretorioStandalone = Boolean.FALSE;
			e.printStackTrace();
			LOG.info("Erro ao obter os arquivos war do servidor! Estrutura de diretórios do JBoss diferente de standalone");
		}

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por valiidar se o Modulo está contido nos módulos do servidor.
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param entity
	 */
	private void validarModuloServidor(Modulo entity) {

		// Localizou war no diretório standalone
		if(isDiretorioStandalone) {

			String baseUrl = entity.getBaseUrl().replace("/", "");

			if (!listaWAR.contains(baseUrl)) {

				throw new BusinessException("VALIDACAO.MODULO_NAO_ENCONTRADO", CodigoErro.REGRA_NEGOCIO.getValue());
			}

		}

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter a baseUrl dos módulos ativos
	 *
	 * @author iago.almeida
	 *
	 * @return List<String>
	 */
	@Override
	public List<String> getBaseUrlModulosAtivos(){

		List<String> baseUrlModulosAtivos = new ArrayList<String>();

		List<Modulo> modulos = this.moduloDao.getModulosAtivos();

		if (!UtilColecao.isVazio(modulos)) {

			for (Modulo modulo : modulos) {

				baseUrlModulosAtivos.add(modulo.getBaseUrl());

			}
		}

		return baseUrlModulosAtivos;

	}
}
