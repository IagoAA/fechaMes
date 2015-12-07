package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.AccessRoleDao;
import br.com.centralit.api.service.AccessRoleService;
import br.com.centralit.framework.model.AccessRole;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

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
 * @since 20/01/2015 - 11:32:53
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Service("accessRoleService")
public class AccessRoleServiceImpl extends GenericServiceImpl<AccessRole, Long> implements AccessRoleService {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param accessRoleDao
	 */
	@Autowired
	public AccessRoleServiceImpl(AccessRoleDao accessRoleDao) {
    	this.dao = accessRoleDao;
	}

	/**
	 *
	 */
	@Override
	public AccessRole getAccessRoleByUrl(String url) {

		return ((AccessRoleDao)this.dao).getAccessRoleByUrl(url);
	}
}
