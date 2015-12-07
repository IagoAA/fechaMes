package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * @since 09/06/2015 - 14:56:29
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
public interface ConfiguracaoParametroSistemaService extends GenericService<ConfiguracaoParametroSistema, Long> {

	/**
	 * Método responsável por
	 * 
	 * @author wilker.machado
	 * 
	 * @param chave
	 * @param organizacao
	 * @return
	 */
	ConfiguracaoParametroSistema getParametro(String chave, Organizacao organizacao);

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
	void validarParametroSistema(Collection<ConfiguracaoParametroSistema> parametros);
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
	 * @param organizacao
	 * @return
	 */
	public String gerarNumeroIdentificacao(Long sequencial, Organizacao organizacao);

}
