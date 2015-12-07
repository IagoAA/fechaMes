package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Caixa;
import br.com.centralit.api.model.Luck;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;

/**
 * <b>Title: </b>LuckDao
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 09/12/2015 - 14:46:30
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
public interface CaixaDao extends CitGenericDAO {

	/**
	 * Método responsável por listar a Entidade<code>Caixa</code>
	 *
	 * @author iago
	 *
	 * @return Collection<Caixa>
	 */
	Collection<Caixa> listarCaixa(Luck luck, String nome);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se o luck a ser excluido é utilizado por caixa
	 *
	 * @author iago.almeida
	 *
	 * @return boolean
	 */
	boolean existeCaixaVinculadoAoLuck(Long idLuck);

}
