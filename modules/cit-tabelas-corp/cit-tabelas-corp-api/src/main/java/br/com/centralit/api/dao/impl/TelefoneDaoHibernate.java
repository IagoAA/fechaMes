package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.TelefoneDao;
import br.com.centralit.api.model.Telefone;
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
 * <b>Title: </b>TelefoneDaoHibernate
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 05/12/2014 - 11:03:59
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("telefoneDao")
public class TelefoneDaoHibernate extends CitGenericDAOImpl implements TelefoneDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public TelefoneDaoHibernate() {

		super(Telefone.class);
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
	 * Método responsável por listar os telefones através do id da pessoa
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPessoa
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<Telefone> findPorIdPessoa(Long idPessoa) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("pessoa.id", idPessoa);

		return this.search(search, Telefone.class);

	}

}
