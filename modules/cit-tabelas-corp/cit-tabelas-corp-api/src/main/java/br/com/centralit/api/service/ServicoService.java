package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Servico;
import br.com.centralit.framework.service.arquitetura.GenericService;

public interface ServicoService extends GenericService<Servico, Long> {

	/**
	 * Método responsável por listar a entidade<code>Servico</code>
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Servico>
	 */
	Collection<Servico> listarServico(String nome);

	/**
	 *
	 * Método responsável por por listar a entidade <code>Servico</code> através do nome e organizacao
	 *
	 * @author iago
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	Collection<Servico> findPorNomeAndOrganizacao(String nome, Long idOrganizacao);

}
