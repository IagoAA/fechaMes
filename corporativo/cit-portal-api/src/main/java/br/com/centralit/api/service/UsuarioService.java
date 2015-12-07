package br.com.centralit.api.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.centralit.api.dao.UsuarioDao;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.PaginaUsuario;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.service.arquitetura.GenericService;

/**
 * Business Service Interface to handle communication between web and persistence layer.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface UsuarioService extends GenericService<Usuario, Long> {

	/**
	 * Convenience method for testing - allows you to mock the DAO and set it on an interface.
	 * 
	 * @param userDao
	 *            the UserDao implementation to use
	 */
	void setUserDao(UsuarioDao userDao);

	/**
	 * Retrieves a user by userId. An exception is thrown if user not found
	 * 
	 * @param userId
	 *            the identifier for the user
	 * @return User
	 */
	Usuario getUser(String userId);

	/**
	 * Finds a user by their username.
	 * 
	 * @param username
	 *            the user's username used to login
	 * @return User a populated user object
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *             exception thrown when user not found
	 */
	Usuario getUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 * Retrieves a list of all users.
	 * 
	 * @return List
	 */
	List<PersistentObject> getUsers();

	/**
	 * Removes a user from the database
	 * 
	 * @param user
	 *            the user to remove
	 */
	void removeUser(Usuario user);

	/**
	 * Removes a user from the database by their userId
	 * 
	 * @param userId
	 *            the user's id
	 */
	void removeUser(String userId);

	public void registerUser(Usuario user);

	public boolean removeFavorito(PaginaUsuario paginaUsuario);

	public boolean removeFiltro(SearchParams searchParams);

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

	/**
	 * Método responsável por atualizar a organizacao selecionada do usuarioLogado
	 * 
	 * @author wilker.machado
	 * 
	 * @param organizacao
	 * @return <code>Usuario</code>
	 */
	Usuario atualizarOrganizacaoUsuario(Organizacao organizacao);

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
	 * @param usuario
	 * @return
	 */
	public String setUserTokenPasswordRecovery(Usuario usuario);

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
	 * @param email
	 * @param url
	 * @return
	 */
	public String requestRecoveryPasswordEmail(String email, String url);

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
	public void updateUserPassword(Usuario user, String password);

	// TODO REMOVER ESSE METODO APOS CORRIGIR USUARIO
	public void updateTokenUsuario(Long idUsuario, String token);

	/**
	 * Método responsável por
	 * 
	 * @author wilker
	 * 
	 * @param organizacao
	 * @param admin
	 */
	void atualizarOrganizacaoUsuario(Organizacao organizacao, Usuario admin);

	List<Usuario> findByUsername(String username);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por validar se um usuário mobile é valido
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param usuario
	 * @return Boolean
	 */
	Boolean usuarioMobileValido(Usuario usuario);

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
	 * @author rogerio.costa
	 * 
	 * @param idGrupo
	 * @return
	 */
	Long quantidadeUsarioPorGrupo(Long idGrupo);

}
