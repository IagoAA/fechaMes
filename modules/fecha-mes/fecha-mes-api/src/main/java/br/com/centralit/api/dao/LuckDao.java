package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Luck;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.exception.BusinessException;

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
 * @since 26/11/2015 - 10:14:57
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
public interface LuckDao extends CitGenericDAO {

	/**
	 * Método responsável por listar a Endiade<code>Luck</code>
	 *
	 * @author iago
	 *
	 * @return Collection<Cidade>
	 */
	Collection<Luck> listarLuck(String nome);

	/**
	 * Método responsável por verificar se existe um luck já cadastrado com o mesmo nome.
	 *
	 * @author iago
	 *
	 * @param luck
	 * @throws BusinessException
	 */
	boolean existeLuckMesmoNome(Luck luck);
}
