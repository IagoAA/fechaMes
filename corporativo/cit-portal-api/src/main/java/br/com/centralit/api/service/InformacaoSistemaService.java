package br.com.centralit.api.service;

import br.com.centralit.framework.model.InformacaoSistema;
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
 * <b>Title: InformacaoSistemaService</b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 06/08/2015 - 15:00:29
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
public interface InformacaoSistemaService extends GenericService<InformacaoSistema, Long> {

	/**
	 *  Método responsável por obter informacoes do sistema do sistema
	 *
	 * @author iago.almeida
	 *
	 * @return responseBody
	 */
	public InformacaoSistema getInformacaoSistema();

}
