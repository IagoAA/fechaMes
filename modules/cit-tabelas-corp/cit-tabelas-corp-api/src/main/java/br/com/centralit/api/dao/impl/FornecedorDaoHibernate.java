package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.FornecedorDao;
import br.com.centralit.api.model.Fornecedor;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

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
 * <b>Title: FornecedorDaoImp</b>
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
 * @since 26/11/2014 - 15:15:46
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Repository("fornecedorDao")
public class FornecedorDaoHibernate extends CitGenericDAOImpl implements FornecedorDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public FornecedorDaoHibernate() {

		super(Fornecedor.class);
	}

	@Override
	public List<Fornecedor> listarFornecedores(String nome, Long idOrganizacao) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addField("id");
		
		search.addField("pessoa.nome");
		
		search.addFilterEqual("pessoa.organizacao.id", idOrganizacao);
		
		search.addFilterILike("pessoa.nome", "%" + nome + "%");

		search.addSort(Sort.asc("pessoa.nome"));

		search.setMaxResults(10);

		search.setResultMode(SearchSeven.RESULT_MAP);

		return this.search(search, this.persistentClass);
	}

}
