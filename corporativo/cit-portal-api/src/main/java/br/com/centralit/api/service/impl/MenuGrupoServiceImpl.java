package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.MenuGrupoDao;
import br.com.centralit.api.service.MenuGrupoService;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuGrupo;
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
 * @since 29/08/2015 - 18:33:22
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
@Service("menuGrupoervice")
public class MenuGrupoServiceImpl extends GenericServiceImpl<MenuGrupo, Long> implements MenuGrupoService {

	/** Atributo dao. */
	private MenuGrupoDao menuGrupoDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param menuGrupoDao
	 */
	@Autowired
	public MenuGrupoServiceImpl( MenuGrupoDao menuGrupoDao ) {

		this.dao = menuGrupoDao;
		
		this.menuGrupoDao = menuGrupoDao;

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

		Collection<MenuGrupo> listaMenuGrupoVinculados = this.menuGrupoDao.findPorIdMenu(entity.getId());

		if (!UtilColecao.isVazio(listaMenuGrupoVinculados)) {

			for (MenuGrupo menuGrupo : listaMenuGrupoVinculados) {

				if (!entity.getMenuGrupos().contains(menuGrupo)) {

					menuGrupo.setMenuRemocao(entity);

					menuGrupo.setMenu(null);

					this.remove(menuGrupo);

				}
			}

		}
	}

	/**
	 * Retorna o valor do atributo <code>menuGrupoDao</code>
	 * 
	 * @return <code>MenuGrupoDao</code>
	 */
	public MenuGrupoDao getMenuGrupoDao() {

		return menuGrupoDao;
	}

}
