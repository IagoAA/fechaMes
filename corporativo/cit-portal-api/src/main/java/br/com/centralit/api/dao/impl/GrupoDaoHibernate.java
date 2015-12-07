package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.GrupoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Grupo;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

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
 * @since 04/03/2015 - 15:27:19
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("grupoDao")
public class GrupoDaoHibernate extends CitGenericDAOImpl implements GrupoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public GrupoDaoHibernate() {

		super(Grupo.class);
	}
	
	@Override
	public List<PersistentObject> findAll() {

		SearchSeven searchSeven = new SearchSeven();

		return this.search(searchSeven, this.persistentClass);
	}

}
