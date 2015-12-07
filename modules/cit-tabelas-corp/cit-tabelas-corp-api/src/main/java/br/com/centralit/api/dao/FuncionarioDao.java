package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Funcionario;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;

/**
 * @since 10/11/2015 - 16:05:16
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
public interface FuncionarioDao extends CitGenericDAO {

	/**
	 * Método responsável por listar a entidade <code>Funcionario</code> através do nome
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Funcionario>
	 */
	Collection<Funcionario> findPorNome(String nome);

	/**
	 *
	 * Método responsável por listar a entidade <code>Funcionario</code> através do nome e organizacao
	 *
	 * @author iago
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	Collection<Funcionario> findPorNomeAndOrganizacao(String nome, Long idOrganizacao);

}
