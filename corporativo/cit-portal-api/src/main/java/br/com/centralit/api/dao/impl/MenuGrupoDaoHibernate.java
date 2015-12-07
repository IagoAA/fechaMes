package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.MenuGrupoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.MenuGrupo;

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
 * @since 29/08/2015 - 18:29:30
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
@Repository("menuGrupoDao")
public class MenuGrupoDaoHibernate extends CitGenericDAOImpl implements MenuGrupoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public MenuGrupoDaoHibernate() {

		super(MenuGrupo.class);
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
	 * Método responsável por listar Entidade através do id do Menu
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idMenu
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<MenuGrupo> findPorIdMenu(Long idMenu) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("menu.id", idMenu);

		return this.search(search, MenuGrupo.class);

	}

}
