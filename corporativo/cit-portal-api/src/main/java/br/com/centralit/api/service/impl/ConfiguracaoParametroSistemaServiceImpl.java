package br.com.centralit.api.service.impl;

import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.ConfiguracaoParametroSistemaDao;
import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;
import br.com.centralit.framework.util.UtilString;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 *
 * <p>
 * <b>Company: </b> Central IT - Governança Corporativa -
 * </p>
 *
 * <p>
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 09/06/2015 - 14:57:30
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Service("configuracaoParametroSistemaService")
public class ConfiguracaoParametroSistemaServiceImpl extends GenericServiceImpl<ConfiguracaoParametroSistema, Long> implements ConfiguracaoParametroSistemaService {

	private ConfiguracaoParametroSistemaDao configuracaoParametroSistemaDao;
	/** Atributo PREFIXO_ANO_MASCARA. */
	private static final String PREFIXO_ANO_MASCARA = "AAAA";

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param configuracaoParametroSistemaDao
	 */
	@Autowired
	public ConfiguracaoParametroSistemaServiceImpl( ConfiguracaoParametroSistemaDao configuracaoParametroSistemaDao, @Qualifier("parametroSistemaValidator") Validator validator ) {

		this.dao = configuracaoParametroSistemaDao;

		this.configuracaoParametroSistemaDao = configuracaoParametroSistemaDao;

		this.validator = validator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConfiguracaoParametroSistema getParametro(String chave, Organizacao organizacao) {

		ConfiguracaoParametroSistema parametroSistema = this.configuracaoParametroSistemaDao.getParametro(chave, organizacao);

		if (UtilObjeto.isReferencia(parametroSistema)){
			return parametroSistema;
		} else {
			log.info("N\u00e3o consegui encontrar o par\u00e2metro: " + chave + " na base de dados. Verifique a tabela de configuracaoparametrosistema."
					+ "Estou retornando o objeto vazio ...");
			return new ConfiguracaoParametroSistema();
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
	 * Método responsável por validar a lista de parametro sistema
	 *
	 * @author rogerio.costa
	 *
	 * @param parametros
	 */
	public void validarParametroSistema(Collection<ConfiguracaoParametroSistema> parametros) {

		if (!UtilColecao.isVazio(parametros)) {

			for (ConfiguracaoParametroSistema parametroSistema : parametros) {

				this.validarEntidade(parametroSistema, this.validator);
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
	 * Método responsável por gerar o número de identificação para a entrada.
	 *
	 * @author luis.camargo
	 *
	 * @param sequencial
	 * @organizacao
	 * @return
	 */
	// TODO Melhor obtenção da máscara.
	public String gerarNumeroIdentificacao(Long sequencial, Organizacao organizacao) {

		String numeroIdentificacao = null;
		ConfiguracaoParametroSistema parametro = getParametro("MASCARA_NUMERO_IDENTIFICACAO", organizacao);

		if(parametro == null || parametro.getValor() == null) {
			throw new BusinessException("MSG.PARAMETRO_GERACAO_NUMERO_NAO_CADASTRADO", CodigoErro.INFRAESTRUTURA.getValue());
		}

		if (parametro != null && UtilString.isNumber(parametro.getValor())) { // Apenas sequencial
			numeroIdentificacao = gerarIdentificacaoApenasSequencial(sequencial, parametro);
		} else if (parametro.getValor() != null && parametro.getValor().contains(PREFIXO_ANO_MASCARA)) { // Ano atual e sequencial
			numeroIdentificacao = gerarIdentificacaoAnoESequencial(sequencial, parametro);
		}

		return numeroIdentificacao;
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
	 * Método responsável por
	 *
	 * @author luis.camargo
	 *
	 * @param sequencial
	 * @param parametro
	 * @return
	 */
	private String gerarIdentificacaoAnoESequencial(Long sequencial, ConfiguracaoParametroSistema parametro) {

		String anoAtual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

		String numeroIdentificacao;
		if (sequencial == null || !sequencial.toString().startsWith(anoAtual)) {
			int tamanho = parametro.getValor().split(PREFIXO_ANO_MASCARA)[1].length();
			numeroIdentificacao = anoAtual + StringUtils.leftPad("1", tamanho, "0");
		} else {
			numeroIdentificacao = String.valueOf(sequencial + 1);
		}

		return numeroIdentificacao;
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
	 * Método responsável por
	 *
	 * @author luis.camargo
	 *
	 * @param sequencial
	 * @param parametro
	 * @return
	 */
	private String gerarIdentificacaoApenasSequencial(Long sequencial, ConfiguracaoParametroSistema parametro) {

		String numeroIdentificacao;
		Long numero = sequencial == null ? 1L : sequencial + 1;
		numeroIdentificacao = StringUtils.leftPad(numero.toString(), parametro.getValor().length(), "0");
		return numeroIdentificacao;
	}

}
