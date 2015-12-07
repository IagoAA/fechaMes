package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Servico;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.exception.BusinessException;

public interface ServicoDao extends CitGenericDAO {

	/**
	 * Método responsável por verificar se existe um servico já cadastrado com o mesmo nome.
	 *
	 * @author iago.almeida
	 *
	 * @param servico
	 * @throws BusinessException
	 */
	boolean existeServicoMesmoNome(Servico servico);


	/**
	 * Método responsável por listar a Entidade<code>Servico</code>
	 *
	 * @author iago.almeida
	 *
	 * @return Collection<Cidade>
	 */
	Collection<Servico> listarServico(String nome);

	/**
	 * @author iago
	 *
	 * @param nome
	 * @param organizacaoId
	 * @return
	 */
	Collection<Servico> findPorNomeAndOrganizacao(String nome, Long organizacaoId);

}
