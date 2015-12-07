package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.SearchParams;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b> Interface SearchParamsDao</p>
 *
 * <p><b>Description: </b> Interface utilizada para persistencia dos filtros de cada página referente ao usuario logado</p>
 * 
 * @since 19/11/2014 - 09:09:50
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *	
 */
public interface SearchParamsDao extends CitGenericDAO {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por buscar lista de filtros por pagina de usuario
	 *
	 * @author iago.almeida
	 *
	 * @param idPaginaUsuario
	 * @return
	 */
	List<SearchParams> findPorIdPaginaUsuario(Long idPaginaUsuario);

}
