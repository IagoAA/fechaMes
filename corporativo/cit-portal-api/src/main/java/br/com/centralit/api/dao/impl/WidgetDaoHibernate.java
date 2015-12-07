package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.WidgetDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Widget;

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
 * @since 10/03/2015 - 11:51:39
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Repository("widgetDao")
public class WidgetDaoHibernate extends CitGenericDAOImpl implements WidgetDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public WidgetDaoHibernate() {

		super(Widget.class);
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
	 * Método responsável por obter o widget através do id do tipoComponente
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idTipoComponente
	 * @return Widget
	 */
	public Widget findPorTipo(Long idTipoComponente) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("tipoComponente.id", idTipoComponente);

		search.setMaxResults(1);

		return searchUnique(search, Widget.class);
	}

}
