package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.FilterDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.model.Filter;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b> FilterDaoHibernate</p>
 *
 * <p><b>Description: </b> Classe de Implementação Dao de <code>FilterDao</code></p>
 * 
 * @since 19/11/2014 - 09:09:50
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *	
 */
@Repository("filterDao")
public class FilterDaoHibernate extends CitGenericDAOImpl implements FilterDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public FilterDaoHibernate() {

		super(Filter.class);
	}

}
