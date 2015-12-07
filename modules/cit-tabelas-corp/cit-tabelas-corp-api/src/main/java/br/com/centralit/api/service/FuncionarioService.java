package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Funcionario;
import br.com.centralit.framework.service.arquitetura.GenericService;

/**
 * @since 10/11/2015 - 16:04:28
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
public interface FuncionarioService extends GenericService<Funcionario, Long> {

	/**
	 *
	 * Método responsável por listar a entidade<code>Funcionario</code> através do nome
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
	 * Método responsável por por listar a entidade <code>Funcionario</code> através do nome e organizacao
	 *
	 * @author iago
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return Collection<Funcionario>
	 */
	Collection<Funcionario> findPorNomeAndOrganizacao(String nome, Long idOrganizacao);

}
