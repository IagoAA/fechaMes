package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Pais;
import br.com.centralit.api.model.Regiao;
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
 * <b>Title: </b>RegiaoDao
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 09/12/2014 - 14:46:30
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface RegiaoDao extends CitGenericDAO {

	/**
	 * Método responsável por listar a Endiade<code>Regiao</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @return Collection<Cidade>
	 */
	Collection<Regiao> listarRegiao(Pais pais, String nome);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se o pais a ser excluido é utilizado por regiao
	 *
	 * @author iago.almeida
	 *
	 * @return boolean
	 */
	boolean existeRegiaoVinculadoAoPais(Long idPais);

}
