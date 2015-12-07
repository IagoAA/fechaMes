package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Pais;
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
 * @since 26/11/2014 - 10:14:57
 *
 * @version 1.0.0
 *
 * @author david.silva
 *
 */
public interface PaisDao extends CitGenericDAO {

	/**
	 * Método responsável por listar a Endiade<code>Pais</code>
	 *
	 * @author rogerio.costa
	 *
	 * @return Collection<Cidade>
	 */
	Collection<Pais> listarPais(String nome);

	/**
	 * Método responsável por verificar se existe um país já cadastrado com o mesmo nome.
	 *
	 * @author luis.camargo
	 *
	 * @param pais
	 * @throws BusinessException
	 */
	boolean existePaisMesmoNome(Pais pais);
}
