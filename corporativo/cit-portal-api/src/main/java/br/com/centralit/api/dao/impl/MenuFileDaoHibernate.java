package br.com.centralit.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.MenuFileDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.MenuFile;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Filter;

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
 * @since 08/07/2015 - 16:51:26
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Repository("menuFileDao")
public class MenuFileDaoHibernate extends CitGenericDAOImpl implements MenuFileDao {

	/** Atributo TIPO_ARQUIVO_JS. */
	private static final String TIPO_ARQUIVO_JS = "js";

	/** Atributo TIPO_ARQUIVO_CSS. */
	private static final String TIPO_ARQUIVO_CSS = "css";

	public MenuFileDaoHibernate() {

		super(MenuFile.class);
	}

	@Override
	public List<MenuFile> findByIdMenu(Long idMenu) {

		SearchSeven search = new SearchSeven(MenuFile.class);

		search.addFilterEqual("menu.id", idMenu);
		search.addFilterEmpty("dataInativo");
		search.addSort("id", false);

		return this.search(search);
	}

	@Override
	public List<MenuFile> findAllJSAtivos() {

		SearchSeven search = new SearchSeven(MenuFile.class);

		search.addFilterEqual("ativo", true);
		search.addFilterEqual("menu.ativo", true);
		search.addFilterEmpty("menu.dataInativo");
		search.addFilterILike("dominioMenuFile.nome", TIPO_ARQUIVO_JS);
		search.addSort("id", false);

		return this.search(search);
	}

	@Override
	public List<MenuFile> findAllCSSAtivos() {

		SearchSeven search = new SearchSeven(MenuFile.class);

		search.addFilterEqual("ativo", true);
		search.addFilterEqual("menu.ativo", true);
		search.addFilterEmpty("menu.dataInativo");
		search.addFilterILike("dominioMenuFile.nome", TIPO_ARQUIVO_CSS);
		search.addSort("id", false);

		return this.search(search);
	}

	@Override
	public List<MenuFile> findAllCSSAtivosPorModulosAtivos() {

		SearchSeven search = new SearchSeven(MenuFile.class);

		search.addFilterEqual("ativo", true);
		search.addFilterEqual("menu.ativo", true);
		search.addFilterEmpty("menu.dataInativo");
		search.addFilterILike("dominioMenuFile.nome", TIPO_ARQUIVO_CSS);
		search.addFilterOr(Filter.equal("menu.modulo.habilitado", Boolean.TRUE), Filter.isEmpty("menu.modulo"));
		search.addSort("id", false);

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
	 * Método responsável por buscar todos js ativos de módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 *@param listBaseUrlInativas
	 * @return List<MenuFile>
	 */
	@Override
	public List<MenuFile> findAllJSAtivosPorModulosAtivos(List<String> listBaseUrlInativas) {

		SearchSeven search = new SearchSeven(MenuFile.class);

		search.addFilterEqual("ativo", true);
		search.addFilterEqual("menu.ativo", true);
		search.addFilterEmpty("menu.dataInativo");
		search.addFilterILike("dominioMenuFile.nome", TIPO_ARQUIVO_JS);

		if(!UtilColecao.isVazio(listBaseUrlInativas)){

			Filter [] filters = new Filter[listBaseUrlInativas.size()];

			for(int i = 0; i < listBaseUrlInativas.size(); i++){

				filters[i] = Filter.not(Filter.ilike("caminho", listBaseUrlInativas.get(i) + "%"));

			}
			search.addFilterAnd(filters);
		}

		search.addFilterOr(Filter.equal("menu.modulo.habilitado", Boolean.TRUE), Filter.isEmpty("menu.modulo"));

		search.addSort("id", false);

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
	 * Método responsável por obter ids dos MenuFile de módulos inativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param baseUrlModulosInativos
	 * @return List<Long>
	 */
	@Override
	public List<Long> findIdsMenuFileModulosInativos(List<String> baseUrlModulosInativos) {

		List<Long> idsBareUrlInativos = new ArrayList<Long>();

		SearchSeven searchSevenModulosInativos = new SearchSeven(MenuFile.class);

		Filter[] listFilter = new Filter[baseUrlModulosInativos.size()];

		for (int i = 0; i < baseUrlModulosInativos.size(); i++) {

			listFilter[i] = Filter.ilike("caminho", baseUrlModulosInativos.get(i) + "%");
		}

		if (baseUrlModulosInativos.size() > 0) {

			searchSevenModulosInativos.addFilterOr(listFilter);
			searchSevenModulosInativos.addField("id");

			idsBareUrlInativos = this.search(searchSevenModulosInativos);
		}

		return idsBareUrlInativos;
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
	 * Método responsável por encontrar um objeto MenuFile pelo caminho passado como parametro
	 *
	 * @author gilberto.nery
	 *
	 * @param nome
	 * @return
	 */
	public MenuFile findPorCaminho(String caminho) {

		SearchSeven search = new SearchSeven(MenuFile.class);

		search.addFilterEqual("caminho", caminho);
		search.setMaxResults(1);

		return this.searchUnique(search);
	}



	/**
	 * Merge se não existir (Rever)
	 */
	@Override
	public PersistentObject mergeIfNotExist(PersistentObject entity) {

		MenuFile menuFile = (MenuFile) entity;

		PersistentObject obj = this.findPorCaminho(menuFile.getCaminho());

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.merge(entity);

		}
	}


	/**
	 * Salva se não existir
	 */
	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		MenuFile menuFile = (MenuFile) entity;

		PersistentObject obj = this.findPorCaminho(menuFile.getCaminho());

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}
}