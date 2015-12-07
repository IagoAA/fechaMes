package br.com.centralit.api.service;

import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.service.arquitetura.GenericService;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: PaginaService</b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 18/12/2014 - 14:28:02
 *
 * @version 1.0.0
 *
 * @author rogerio.cassimiro
 *	
 */
public interface PaginaService extends GenericService<Pagina, Long>{

	/**
	 *
	 * Método responsável por buscar página existente
	 *
	 * @author wilker.machado
	 *
	 * @param pagina
	 * 
	 * @return <code>Pagina</code>
	 */
	public Pagina getPagina(Pagina pagina);
	
}
