package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.service.arquitetura.GenericService;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 13/02/2015 - 10:18:33
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
public interface SearchParamsService extends GenericService<SearchParams, Long>{

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
