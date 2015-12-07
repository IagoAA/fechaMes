package br.com.centralit.api.dao;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.AccessRole;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 20/01/2015 - 11:33:11
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
public interface AccessRoleDao extends CitGenericDAO {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por
	 *
	 * @author ally.barra
	 *
	 * @param url
	 * @return
	 */
	public AccessRole getAccessRoleByUrl(String url);
}
