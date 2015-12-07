package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.FuncionarioDao;
import br.com.centralit.api.model.Funcionario;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Dominio;

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
 * @since 05/01/2015 - 15:56:35
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Repository("funcionarioDao")
public class FuncionarioDaoHibernate extends CitGenericDAOImpl implements FuncionarioDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public FuncionarioDaoHibernate() {

		super(Funcionario.class);
	}

	/**
	 * Método responsável por listar a entidade <code>Funcionario</code> através do nome
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	public Collection<Funcionario> findPorNome(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("pessoa.nome", "%" + nome + "%");

		search.addFilterEqual("classeParceiro.dominioTipoParceiro.codigo", Dominio.TIPO_PARCEIRO_FUNCIONARIO);

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("pessoa.nome"));

		search.setMaxResults(10);

		return this.search(search, Funcionario.class);

	}

	/**
	 * Método responsável por listar a entidade <code>Funcionario</code> através do nome e organizacao
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	@Override
	public Collection<Funcionario> findPorNomeAndOrganizacao(String nome, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("pessoa.nome", "%" + nome + "%");

		search.addFilterEqual("pessoa.organizacao.id", idOrganizacao);

		search.addFilterEqual("classeParceiro.dominioTipoParceiro.codigo", Dominio.TIPO_PARCEIRO_FUNCIONARIO);

		search.addSort(Sort.asc("pessoa.nome"));

		search.setMaxResults(10);

		return this.search(search, Funcionario.class);
	}


}
