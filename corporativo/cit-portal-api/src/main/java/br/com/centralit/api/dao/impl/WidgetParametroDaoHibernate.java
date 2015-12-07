package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.WidgetParametroDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.model.WidgetParametro;

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
 * @since 20/03/2015 - 11:51:39
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
@Repository("widgetParametroDao")
public class WidgetParametroDaoHibernate extends CitGenericDAOImpl implements WidgetParametroDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public WidgetParametroDaoHibernate() {

		super(WidgetParametro.class);
	}

}
