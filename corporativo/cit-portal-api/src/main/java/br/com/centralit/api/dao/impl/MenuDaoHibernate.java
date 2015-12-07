package br.com.centralit.api.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.MenuDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Sort;

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
 * <b>Title: MenuDaoHibernate</b>
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
 * @since 09/12/2014 - 17:41:09
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Repository("menuDao")
public class MenuDaoHibernate extends CitGenericDAOImpl implements MenuDao {

	public MenuDaoHibernate() {

		super(Menu.class);
	}

	public List<Menu> findParent() {

		SearchSeven search = new SearchSeven(Menu.class);

		search.addFilterEqual("ativo", true);
		search.addFilterEmpty("parent");
		search.addFilterEmpty("dataInativo");
		search.addSort(Sort.asc("ordem"));
		search.addSort(Sort.asc("coluna"));

		return this.search(search);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getAllParent() {

		SearchSeven search = new SearchSeven(Menu.class);

		search.addFilterEmpty("parent");
		search.addFilterEmpty("dataInativo");
		search.addSort(Sort.asc("ordem"));
		search.addSort(Sort.asc("coluna"));

		return this.search(search);
	}

	@Override
	public List<Menu> findMenuByIdParent(Long idParent) {

		SearchSeven search = new SearchSeven(Menu.class);

		search.addFilterEqual("parent.id", idParent);
		search.addFilterEmpty("dataInativo");
		search.addSort(Sort.asc("ordem"));
		search.addSort(Sort.asc("coluna"));

		return this.search(search);
	}

	@Override
	// TODO pesquisar uma solução para que seja encontrado os registros cadastrados com caracteres especiais quando pesquisa sem o caractere especial
	public List<Menu> findByName(String nome, Collection<Long> idsGrupo, Collection<Long> idsPrivilegioUsuario) {

		SearchSeven search = new SearchSeven(Menu.class);

		search.addFilterOr(Filter.in("menuGrupos.grupo.id", idsGrupo), Filter.in("menuPrivilegios.privilegio.id", idsPrivilegioUsuario), Filter.equal("permissaoDeAcessoDefinida", false), Filter.isEmpty("permissaoDeAcessoDefinida"));

		search.addFilterEqual("ativo", true);
		search.addFilterNotEmpty("pagina");
		search.addFilterILike("nome", "%" + nome + "%");
		search.addFilterOr(Filter.equal("modulo.habilitado", Boolean.TRUE), Filter.isEmpty("modulo"));

		return this.search(search);
	}

	/**
	 * Método responsável por carregar menus da tela com suas estruturas por módulos ativos
	 * 
	 * @author iago.almeida
	 * 
	 * @param usuario
	 * @param idsGrupo
	 * @param idsPrivilegioUsuario
	 * 
	 * @return List<Menu>
	 */
	@Override
	public List<Menu> getAllMenusAtivos(Usuario usuario, Collection<Long> idsGrupo, Collection<Long> idsPrivilegioUsuario) {

		SearchSeven searchSeven = new SearchSeven(Menu.class);

		searchSeven.addFilterOr(Filter.in("menuGrupos.grupo.id", idsGrupo), Filter.in("menuPrivilegios.privilegio.id", idsPrivilegioUsuario), Filter.equal("permissaoDeAcessoDefinida", false), Filter.isEmpty("permissaoDeAcessoDefinida"));

		searchSeven.addFilterEqual("ativo", true);
		searchSeven.addFilterEmpty("parent");
		searchSeven.addSort(Sort.asc("ordem"));
		searchSeven.addSort(Sort.asc("coluna"));
		searchSeven.addFilterOr(Filter.equal("modulo.habilitado", Boolean.TRUE), Filter.isEmpty("modulo"));

		return this.search(searchSeven);
	}

	public List<Modulo> findModulosAtivo() {

		SearchSeven search = new SearchSeven(Modulo.class);

		search.addFilterEqual("habilitado", true);

		return this.search(search, Modulo.class);
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
	 * @param nome
	 * @return
	 */
	public Menu findPorNome(String nome) {

		SearchSeven search = new SearchSeven(Menu.class);

		search.addFilterEqual("nome", nome);

		return this.searchUnique(search);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Menu buscarMenusPorPagina(Pagina pagina) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("pagina.pagina", pagina.getPagina()).removeField(pagina.getAjuda());

		search.setMaxResults(1);

		return this.searchUnique(search);
	}

	@Override
	public PersistentObject mergeIfNotExist(PersistentObject entity) {

		Menu menu = (Menu) entity;

		PersistentObject obj = this.findPorNome(menu.getNome());

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.merge(entity);

		}
	}

	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		Menu menu = (Menu) entity;

		PersistentObject obj = this.findPorNome(menu.getNome());

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
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
	 * Método responsável por obter todos os Menu que não possuem parent e que são de módulos ativos
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @return List<Menu>
	 */
	@Override
	public List<Menu> findMenuPorModulosAtivos() {

		SearchSeven search = new SearchSeven(Menu.class);

		search.addFilterEqual("ativo", true);
		search.addFilterEmpty("parent");
		search.addFilterEmpty("dataInativo");
		search.addSort(Sort.asc("ordem"));
		search.addSort(Sort.asc("coluna"));
		search.addFilterOr(Filter.equal("modulo.habilitado", Boolean.TRUE), Filter.isEmpty("modulo"));

		return this.search(search);
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
	 * Método responsável por obter Menu de módulos inativos
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param baseUrlModulosInativos
	 * @return List<Menu>
	 */
	@Override
	public List<Menu> findMenuModulosInativos(List<String> baseUrlModulosInativos) {

		SearchSeven searchSevenModulosInativos = new SearchSeven(Menu.class);

		Filter[] listFilter = new Filter[baseUrlModulosInativos.size()];

		for (int i = 0; i < baseUrlModulosInativos.size(); i++) {

			listFilter[i] = Filter.ilike("pagina.pagina", baseUrlModulosInativos.get(i) + "%");
		}

		if (baseUrlModulosInativos.size() > 0) {

			searchSevenModulosInativos.addFilterOr(listFilter);
			return this.search(searchSevenModulosInativos);
		}

		return new ArrayList<Menu>();
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
	 * Método responsável por obter Menu por chave
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param chave
	 * @return Menu
	 */
	@Override
	public Menu obterMenuPorChave(String chave) {
		
		SearchSeven searchSeven = new SearchSeven(this.persistentClass);
		searchSeven.addFilterEqual("chave", chave);
		searchSeven.setMaxResults(1);
		
		return this.searchUnique(searchSeven);
	}

}
