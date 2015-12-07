package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.SearchParamsDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.SearchParams;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b> SearchParamsDao</p>
 *
 * <p><b>Description: </b> Classe de Implementação Dao de <code>SearchParamsDao</code></p>
 *
 * @since 19/11/2014 - 09:09:50
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Repository("searchParamsDao")
public class SearchParamsDaoHibernate extends CitGenericDAOImpl implements SearchParamsDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public SearchParamsDaoHibernate() {

		super(SearchParams.class);
	}

	@Override
	public List<SearchParams> findPorIdPaginaUsuario(Long idPaginaUsuario) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("paginaUsuario.id", idPaginaUsuario);

		search.addFilterEmpty("dataInativo");

		return this.search(search, SearchParams.class);
	}

}
