package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.OrganizacaoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;

@Repository("organizacaoDao")
public class OrganizacaoDaoHibernate extends CitGenericDAOImpl implements OrganizacaoDao {

	private static final int MAX_RESULT_AUTOCOMPLETE = 10;

	public OrganizacaoDaoHibernate() {

		super(Organizacao.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Organizacao> listarOrganizacaoPorNome(String nome) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterILike("nome", "%" + nome + "%");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(MAX_RESULT_AUTOCOMPLETE);

		return this.search(search, this.persistentClass);
	}

	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		Organizacao organizacao = (Organizacao) entity;

		Search search = new Search();

		search.addFilterEqual("nome", organizacao.getNome());

		search.addFilterEqual("sigla", organizacao.getSigla());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}
	
	@Override
	public boolean existeCodigoIgual(String codigo) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("codigo", codigo);

		return this.count(search) > 0;
	}

}
