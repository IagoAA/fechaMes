package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PortadorDao;
import br.com.centralit.api.model.Portador;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;

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
 * @since 05/01/2015 - 15:56:35
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Repository("portadorDao")
public class PortadorDaoHibernate extends CitGenericDAOImpl implements PortadorDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public PortadorDaoHibernate() {

		super(Portador.class);
	}

}
