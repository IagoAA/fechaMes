package br.com.centralit.api.dao;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Organizacao;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 	
 * @since 09/06/2015 - 14:58:28
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *	
 */
public interface ConfiguracaoParametroSistemaDao extends CitGenericDAO{

	/**
	 * Método responsável por buscar o valor de um determinado parametro da configuracao de uma organizacao
	 *
	 * @author wilker.machado
	 *
	 * @param chave
	 * @param organizacao
	 * @return
	 */
	ConfiguracaoParametroSistema getParametro(String chave, Organizacao organizacao);

}
