package br.com.centralit.api.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Usuario;

/**
 * User Data Access Object (GenericDao) interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UsuarioDao extends CitGenericDAO {

	/**
	 * Gets users information based on login name.
	 * 
	 * @param username
	 *            the user's username
	 * @return userDetails populated userDetails object
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *             thrown when user not found in database
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 * Gets a list of users ordered by the uppercase version of their username.
	 * 
	 * @return List populated list of users
	 */
	List<Usuario> getUsers();

	/**
	 * Saves a user's information.
	 * 
	 * @param user
	 *            the object to be saved
	 * @return the persisted User object
	 */
	Usuario saveUser(Usuario user);

	/**
	 * Retrieves the password in DB for a user
	 * 
	 * @param userId
	 *            the user's id
	 * @return the password in DB, if the user is already persisted
	 */
	String getUserPassword(Long userId);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por buscar usuário por nome
	 * 
	 * @author iago.almeida
	 * 
	 * @param username
	 * @return
	 */
	Usuario buscaUsuarioByUsername(String username);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por buscar usuário por email
	 * 
	 * @author iago.almeida
	 * 
	 * @param email
	 * @return
	 */
	Usuario buscaUsuarioByEmail(String email);

	public Usuario loadUserByUsernamePassword(String username, String password);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 * @param token
	 * @return
	 */
	public boolean verifyIfTokenRecoveryPasswordExists(String token);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 * @param token
	 * @return
	 */
	public Usuario getUserByToken(String token);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 * @param idUsuario
	 * @param password
	 */
	public void updateUserPassword(Long idUsuario, String password);

	// TODO REMOVER ESSE METODO APOS CORRIGIR USUARIO
	public void updateTokenUsuario(Long idUsuario, String token);

	List<Usuario> findByUsername(String username);

}
