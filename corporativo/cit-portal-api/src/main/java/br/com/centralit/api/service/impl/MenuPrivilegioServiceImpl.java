package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.MenuPrivilegioDao;
import br.com.centralit.api.service.MenuPrivilegioService;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuPrivilegio;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;

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
 * @since 29/08/2015 - 18:34:59
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
@Service("menuPrivilegioService")
public class MenuPrivilegioServiceImpl extends GenericServiceImpl<MenuPrivilegio, Long> implements MenuPrivilegioService {

	/** Atributo dao. */
	private MenuPrivilegioDao menuPriviegioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param menuPriviegioDao
	 */

	@Autowired
	public MenuPrivilegioServiceImpl( MenuPrivilegioDao menuPriviegioDao ) {

		this.dao = menuPriviegioDao;

		this.menuPriviegioDao = menuPriviegioDao;
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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	public void verificarRemocao(Menu entity) {

		Collection<MenuPrivilegio> listaMenuPrivilegioVinculados = this.menuPriviegioDao.findPorIdMenu(entity.getId());

		if (!UtilColecao.isVazio(listaMenuPrivilegioVinculados)) {

			for (MenuPrivilegio menuPrivilegio : listaMenuPrivilegioVinculados) {

				if (!entity.getMenuPrivilegios().contains(menuPrivilegio)) {

					menuPrivilegio.setMenuRemocao(entity);

					menuPrivilegio.setMenu(null);

					this.remove(menuPrivilegio);

				}
			}

		}
	}

	/**
	 * Retorna o valor do atributo <code>menuPriviegioDao</code>
	 * 
	 * @return <code>MenuPriviegioDao</code>
	 */
	public MenuPrivilegioDao getMenuPriviegioDao() {

		return menuPriviegioDao;
	}

	/**
	 * Define o valor do atributo <code>menuPriviegioDao</code>.
	 * 
	 * @param menuPriviegioDao
	 */
	public void setMenuPriviegioDao(MenuPrivilegioDao menuPriviegioDao) {

		this.menuPriviegioDao = menuPriviegioDao;
	}

}
