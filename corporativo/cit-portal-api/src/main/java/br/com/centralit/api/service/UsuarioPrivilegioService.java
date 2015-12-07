package br.com.centralit.api.service;

import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioPrivilegio;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * @since 31/08/2015 - 00:56:14
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
public interface UsuarioPrivilegioService extends GenericService<UsuarioPrivilegio, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por remover o grupoUsuario através do id do Usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	void verificarRemocaoAtravesDoUsuario(Usuario entity);

}
