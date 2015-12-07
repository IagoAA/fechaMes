package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.MenuPrivilegioDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.MenuPrivilegio;

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
 * @since 29/08/2015 - 18:30:44
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
@Repository("menuPrivilegioDao")
public class MenuPrivilegioDaoHibernate extends CitGenericDAOImpl implements MenuPrivilegioDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public MenuPrivilegioDaoHibernate() {

		super(MenuPrivilegio.class);
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
	public Collection<MenuPrivilegio> findPorIdMenu(Long idMenu) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("menu.id", idMenu);

		return this.search(search, MenuPrivilegio.class);

	}

}
