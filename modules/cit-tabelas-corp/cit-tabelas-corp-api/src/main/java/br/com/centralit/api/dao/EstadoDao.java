package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Estado;
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
 * @since 09/12/2014 - 14:27:18
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface EstadoDao extends CitGenericDAO {

	/**
	 * Método responsável por listar a Endiade<code>Estado</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @return Collection<Cidade>
	 */
	Collection<Estado> listarEstado(Regiao regiao, String nome);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a regiao a ser excluida é utilizada por estado
	 *
	 * @author iago.almeida
	 *
	 * @param idRegiao
	 * @return boolean
	 */
	boolean existeEstadoVinculadoARegiao(Long idRegiao);

	/**
	 * Método responsável por verificar se existe um estado já cadastrado com o mesmo nome.
	 * 
	 * @author luis.camargo
	 *  
	 * @param estado
	 * @return boolean
	 */
	boolean existeEstadoMesmoNome(Estado estado);

}
