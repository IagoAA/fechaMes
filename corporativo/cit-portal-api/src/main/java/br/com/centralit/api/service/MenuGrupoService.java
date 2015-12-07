package br.com.centralit.api.service;

import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuGrupo;
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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 * 
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 * 
 * @since 29/08/2015 - 18:32:16
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
public interface MenuGrupoService extends GenericService<MenuGrupo, Long> {

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
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	void verificarRemocao(Menu entity);

}
