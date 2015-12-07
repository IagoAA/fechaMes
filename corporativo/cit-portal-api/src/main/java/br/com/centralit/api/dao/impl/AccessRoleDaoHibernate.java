package br.com.centralit.api.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.AccessRoleDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
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
 * @since 20/01/2015 - 11:33:21
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Repository("accessRoleDao")
public class AccessRoleDaoHibernate extends CitGenericDAOImpl implements AccessRoleDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public AccessRoleDaoHibernate() {
		super(AccessRole.class);
	}

	/**
	 *
	 */
	@Override
	public AccessRole getAccessRoleByUrl(String url) {
		Query query = em().createQuery("from AccessRole accessRole where upper(accessRole.url) = upper(:url)");
		query.setParameter("url", url);

		@SuppressWarnings("unchecked")
		List<AccessRole> accessRoles = query.getResultList();

		return (accessRoles != null && accessRoles.size() > 0 ? accessRoles.get(0) : null);
	}
}
