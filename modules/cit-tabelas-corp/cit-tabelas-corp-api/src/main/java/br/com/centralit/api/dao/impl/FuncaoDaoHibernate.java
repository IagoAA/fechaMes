package br.com.centralit.api.dao.impl;

import java.util.Collection;

import br.com.centralit.api.model.Funcao;
import br.com.centralit.api.dao.FuncaoDao;

import org.springframework.stereotype.Repository;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

@Repository("funcaoDao")
public class FuncaoDaoHibernate extends CitGenericDAOImpl implements FuncaoDao {
	public FuncaoDaoHibernate() {
		super(Funcao.class);
	}
	
	public Collection<Funcao> findPorNomeEOrganizacao(String nome, Long organizacaoId) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");
		search.addFilterEqual("organizacao.id", organizacaoId);

		return this.search(search, Funcao.class);

	}	
	
	public boolean verificaSeNomeEUnico(String nome, Long organizacaoId) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", nome);
		search.addFilterEqual("organizacao.id", organizacaoId);

		return this.count(search) > 0;
	}	
	
	public boolean verificaSeCodigoEUnico(String codigo, Long organizacaoId) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("codigo", codigo);
		search.addFilterEqual("organizacao.id", organizacaoId);

		return this.count(search) > 0;
	}		
}
