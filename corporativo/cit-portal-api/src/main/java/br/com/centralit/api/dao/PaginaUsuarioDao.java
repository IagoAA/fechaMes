package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.PaginaUsuario;
import br.com.centralit.framework.model.Usuario;

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
 * <b>Title: PaginaUsuarioDao</b> Interface PaginaUsuarioDao
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 19/11/2014 - 10:04:10
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
public interface PaginaUsuarioDao extends CitGenericDAO {

	/**
	 * 
	 * Método responsável por obter <code>PaginaUsuario</code> por <code>Pagina</code>
	 * 
	 * @author wilker.machado
	 * 
	 * @param pageWorkspace
	 *            <code>Pagina</code>
	 * @param usuario
	 *            <code>Usuario</code>
	 * @return <code>PaginaUsuario</code>
	 */
	public PaginaUsuario getPaginaUsuario(Pagina pageWorkspace, Usuario usuario);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por trazer paginas de usuário ativas
	 *
	 * @author iago.almeida
	 *
	 * @param idUsuario
	 * @return
	 */
	public List<PaginaUsuario> findPorIdUsuario(Long idUsuario);

}
