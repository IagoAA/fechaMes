package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PessoaJuridicaDao;
import br.com.centralit.api.model.PessoaJuridica;
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
 * <b>Title: </b>PessoaJuridicaDaoHibernate
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 03/12/2014 - 09:26:10
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("pessoaJuridicaDao")
public class PessoaJuridicaDaoHibernate extends CitGenericDAOImpl implements PessoaJuridicaDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classs
	 */
	public PessoaJuridicaDaoHibernate() {

		super(PessoaJuridica.class);
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
	 * Método responsável por obter pessoaJuridica através do cnpj e id do orgão
	 * 
	 * @author rogerio.costa
	 * 
	 * @param cpf
	 * 
	 * @return PessoaJuridica
	 */
	public PessoaJuridica findPorCNPJ(String cnpj, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("cnpj", cnpj);

		search.addFilterEqual("pessoa.organizacao.id", idOrganizacao);

		search.setMaxResults(1);

		return searchUnique(search, PessoaJuridica.class);
	}

}
