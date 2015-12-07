package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.api.model.Fornecedor;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;

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
 * <b>Title: FornecedorDao</b>
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 26/11/2014 - 15:14:49
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface FornecedorDao extends CitGenericDAO {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author thiago.borges
	 * 
	 * @param nome
	 * @param idOrganizacao 
	 * @return
	 */
	List<Fornecedor> listarFornecedores(String nome, Long idOrganizacao);

}
