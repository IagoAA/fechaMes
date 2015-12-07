package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.FavoritoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.model.Favorito;

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
 * <b>Title: </b> FavoritoDaoHibernate
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 19/11/2014 - 15:31:43
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Repository("favoritoDao")
public class FavoritoDaoHibernate extends CitGenericDAOImpl implements FavoritoDao {

	public FavoritoDaoHibernate() {

		super(Favorito.class);
	}
}
