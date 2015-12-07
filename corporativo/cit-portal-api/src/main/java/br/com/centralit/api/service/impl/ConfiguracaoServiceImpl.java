package br.com.centralit.api.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.api.dao.ConfiguracaoDao;
import br.com.centralit.api.service.AnexoImagemService;
import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.api.service.ConfiguracaoService;
import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.framework.model.AnexoImagem;
import br.com.centralit.framework.model.Configuracao;
import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

@Service("configuracaoService")
public class ConfiguracaoServiceImpl extends GenericServiceImpl<Configuracao, Long> implements ConfiguracaoService {

	/** Atributo configuracaoDao. */
	private ConfiguracaoDao configuracaoDao;

	/** Atributo anexoImagemService. */
	@Autowired
	private AnexoImagemService anexoImagemService;

	@Autowired
	private ConfiguracaoParametroSistemaService configuracaoParametroSistemaService;

	@Autowired
	private OrganizacaoService organizacaoService;

	@Autowired
	public ConfiguracaoServiceImpl( ConfiguracaoDao configuracaoDao ) {

		this.dao = configuracaoDao;

		this.configuracaoDao = configuracaoDao;
	}

	@Override
	public Configuracao uploadLogoPaginaLoginForResource(MultipartFile file, String filePath) {

		try {
			file.transferTo(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Configuracao();
	}

	@Override
	public Configuracao save(Configuracao configuracao) {

		this.setConfiguracaoInParametros(configuracao);

		this.configuracaoParametroSistemaService.validarParametroSistema(configuracao.getParametros());

		return super.save(configuracao);
	}

	@Override
	public Configuracao merge(Configuracao configuracao) {

		this.setConfiguracaoInParametros(configuracao);

		this.configuracaoParametroSistemaService.validarParametroSistema(configuracao.getParametros());

		return super.merge(configuracao);
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
	 * Método responsável por colocar a referencia da configuracao nos parametros
	 * 
	 * @author renato.jesus
	 * 
	 * @param configuracao
	 */
	private void setConfiguracaoInParametros(Configuracao configuracao) {

		if (UtilObjeto.isReferencia(configuracao.getParametros()) && !configuracao.getParametros().isEmpty()) {
			for (ConfiguracaoParametroSistema parametro : configuracao.getParametros()) {
				parametro.setConfiguracao(configuracao);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Configuracao getConfiguracao(Long idOrganizacao) {

		return this.configuracaoDao.getConfiguracao(idOrganizacao);
	}

	@Override
	public String getParametro(String chave) {

		return configuracaoDao.getParametro(chave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getParametroByOrganizacao(String chave, Long idOrganizacao) {

		ConfiguracaoParametroSistema configuracaoParametroSistema = this.configuracaoParametroSistemaService.getParametro(chave, this.organizacaoService.find(idOrganizacao));

		return UtilObjeto.isReferencia(configuracaoParametroSistema) ? configuracaoParametroSistema.getValor() : null;
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
	 * Método responsável por fazer upload da imagem de órgão
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param file
	 * @param idOrganizacao
	 * @param idConfiguracao
	 */
	@Override
	public void uploadAnexoImagemOrganizacao(MultipartFile file, Long idOrganizacao, Long idConfiguracao) {

		AnexoImagem anexoImagem = null;

		try {

			Configuracao configuracao = (Configuracao) this.configuracaoDao.getReference(idConfiguracao);

			anexoImagem = UtilObjeto.isReferencia(configuracao.getAnexoImagem()) ? configuracao.getAnexoImagem() : new AnexoImagem();

			anexoImagem.setAnexo(file.getBytes());

			anexoImagem.setDescricao(file.getOriginalFilename());

			anexoImagem.setConfiguracao(configuracao);

			anexoImagem.setIdOrganizacao(idOrganizacao);

			AnexoImagem anexoImagemSave = this.anexoImagemService.save(anexoImagem);

			configuracao.setAnexoImagem(anexoImagemSave);

			this.merge(configuracao);

		} catch (IOException e) {

			System.out.println("NAO FOI POSSIVEL ANEXAR O ARQUIVO");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Configuracao atualizarOrganizacaoConfiguracao(Long idOrganizacaoAnterior, Organizacao organizacaoAtual) {

		Configuracao configuracao = this.getConfiguracao(idOrganizacaoAnterior);

		configuracao.setOrganizacao(organizacaoAtual);

		return this.merge(configuracao);
	}

}
