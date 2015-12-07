package br.com.centralit.api.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PessoaDao;
import br.com.centralit.api.model.Pessoa;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;

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
 * <b>Title: </b>PessoaDaoHibernate
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 28/11/2014 - 11:41:49
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("pessoaDao")
public class PessoaDaoHibernate extends CitGenericDAOImpl implements PessoaDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classs
	 */
	public PessoaDaoHibernate() {

		super(Pessoa.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> findByUsuario(Pessoa pessoa) {
		String queryString = "from Pessoa pessoa where usuario.id = :idUsuario ";
		if (!pessoa.isNew()) {
			queryString += "and pessoa.id <> :idPessoa ";
		}
		Query query = em().createQuery(queryString);
		query.setParameter("idUsuario", pessoa.getUsuario().getId());
		if (!pessoa.isNew()) {
			query.setParameter("idPessoa", pessoa.getId());
		}

		return query.getResultList();
	}

}
