package br.com.centralit.api.dao;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Pagina;

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
 * <b>Title: </b> Interface PaginaDao
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 19/11/2014 - 10:06:44
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
public interface PaginaDao extends CitGenericDAO {

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
