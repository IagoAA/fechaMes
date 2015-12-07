package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.api.model.Pessoa;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b>PessoaDao</p>
 *
 * <p><b>Description: </b></p>
 * 
 * @since 28/11/2014 - 11:38:10
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *	
 */
public interface PessoaDao extends CitGenericDAO{

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por retornar as pessoas vinculadas a um usuário
	 *
	 * @author Carlos
	 *
	 * @param pessoa
	 * @return
	 */
	List<Pessoa> findByUsuario(Pessoa pessoa);

}
