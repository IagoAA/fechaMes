package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PessoaFisicaDao;
import br.com.centralit.api.model.PessoaFisica;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

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
 * <b>Title: </b>PessoaFisicaDaoHibernate
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 02/12/2014 - 17:26:27
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("pessoaFisicaDao")
public class PessoaFisicaDaoHibernate extends CitGenericDAOImpl implements PessoaFisicaDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classs
	 */
	public PessoaFisicaDaoHibernate() {

		super(PessoaFisica.class);
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
	 * Método responsável por listar pessoa fisica por cpf e organizacao
	 * 
	 * @author rogerio.costa
	 * 
	 * @param cpf
	 * 
	 * @param idOrganizacao
	 * 
	 * @return
	 */
	public PessoaFisica findPorCPFAndOrganizacao(String cpf, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();

		search.addFilterIn("cpf", cpf);

		search.addFilterEqual("pessoa.organizacao.id", idOrganizacao);

		search.setMaxResults(1);

		return searchUnique(search, PessoaFisica.class);
	}

}
