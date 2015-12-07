package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.FuncionarioAnexoDao;
import br.com.centralit.api.model.FuncionarioAnexo;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

import com.googlecode.genericdao.search.Sort;

@Repository("funcionarioAnexoDao")
public class FuncionarioAnexoDaoHibernate extends CitGenericDAOImpl implements FuncionarioAnexoDao {

	public FuncionarioAnexoDaoHibernate() {

		super(FuncionarioAnexo.class);
	}

	@Override
	public Collection<FuncionarioAnexo> listarAnexos(Long idFuncionario) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterEqual("funcionario.id", idFuncionario);

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("id"));

		search.setMaxResults(10);

		return this.search(search, this.persistentClass);
	}
}
