package br.com.centralit.api.dao;

import br.com.centralit.api.model.PessoaFisica;
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
 * <b>Title: </b>PessoaFisicaDao
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 02/12/2014 - 17:21:56
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface PessoaFisicaDao extends CitGenericDAO {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar pessoa fisica por cpf e organizacao
	 * 
	 * @author rogerio.costa
	 * 
	 * @param cpf
	 * 
	 * @param idOrganizacao
	 * 
	 * @return
	 */
	PessoaFisica findPorCPFAndOrganizacao(String cpf, Long idOrganizacao);

}
