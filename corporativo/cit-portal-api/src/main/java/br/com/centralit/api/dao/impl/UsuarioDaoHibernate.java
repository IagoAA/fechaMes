package br.com.centralit.api.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.UsuarioDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Search;

/**
 * This class interacts with Hibernate session to save/delete and retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a> Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with the
 *         new BaseDaoHibernate implementation that uses generics. Modified by jgarcia (updated to hibernate 4)
 */
@Repository("usuarioDao")
public class UsuarioDaoHibernate extends CitGenericDAOImpl implements UsuarioDao {

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public UsuarioDaoHibernate() {

		super(Usuario.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsers() {

		Query qry = em().createQuery("from Usuario u order by upper(u.username)");
		return qry.getResultList();
	}

	public String getUserPassword(Long id) {

		Query query = em().createQuery("select u.password from Usuario u where u.id = :id");
		query.setParameter("id", id);

		return (String) query.getSingleResult();
	}

	/**
	 * {@inheritDoc}
	 */
	public Usuario saveUser(Usuario user) {

		return (Usuario) save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null)
			return null;

		@SuppressWarnings("rawtypes")
		List users = search(new SearchSeven().addFilterEqual("username", username), Usuario.class);

		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("user '" + username + "' not found...");
		} else {
			Usuario user = (Usuario) users.get(0);

			// user.setUsuarioPrivilegios(new LinkedHashSet());
			//
			// user.getUsuarioPrivilegios().add(new UsuarioPrivilegio(user, (Privilegio) this.getReference(1L, Privilegio.class)));
			// user.getUsuarioPrivilegios().add(new UsuarioPrivilegio(user, (Privilegio) this.getReference(2L, Privilegio.class)));

			for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
				System.out.println("Lazy: " + grantedAuthority.getAuthority());
			}

			return (UserDetails) users.get(0);
		}
	}

	public Usuario loadUserByUsernamePassword(String username, String password) {

		Query query = em().createQuery("from Usuario usuario where upper(usuario.username) = upper(:username) and upper(usuario.password) = upper(:password)");
		query.setParameter("username", username);
		query.setParameter("password", password);

		@SuppressWarnings("rawtypes")
		List usuarios = query.getResultList();

		// VERIFICA SE RETORNOU APENAS UM USUARIO
		if (usuarios != null && !usuarios.isEmpty() && usuarios.size() == 1) {
			return (Usuario) usuarios.get(0);
		} else {
			throw new BadCredentialsException("problema login");
		}
	}

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
	@Override
	public Usuario buscaUsuarioByUsername(String username) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("username", username);

		search.addFilterEmpty("dataInativo");

		search.setMaxResults(1);

		return this.searchUnique(search, Usuario.class);
	}

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
	@Override
	public Usuario buscaUsuarioByEmail(String email) {

		Query query = em().createQuery("select usuario from Usuario usuario where upper(usuario.email) = upper(:email)");
		query.setParameter("email", email);

		@SuppressWarnings("rawtypes")
		List usuarios = query.getResultList();

		// VERIFICA SE RETORNOU APENAS UM USUARIO
		if (usuarios != null && !usuarios.isEmpty() && usuarios.size() == 1) {
			return (Usuario) usuarios.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean verifyIfTokenRecoveryPasswordExists(String token) {

		Query query = em().createQuery("from Usuario usuario where usuario.tokenPasswordRecovery = :token");
		query.setParameter("token", token);

		return query.getResultList().size() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public Usuario getUserByToken(String token) {

		Query query = em().createQuery("from Usuario usuario where usuario.tokenPasswordRecovery = :token");
		query.setParameter("token", token);

		@SuppressWarnings("rawtypes")
		List usuarios = query.getResultList();

		if (usuarios != null && !usuarios.isEmpty() && usuarios.size() == 1) {
			return (Usuario) usuarios.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateUserPassword(Long idUsuario, String password) {

		Query query = em().createQuery("update Usuario set password = :password where id = :idUsuario");
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("password", password);

		query.executeUpdate();
	}

	// TODO REMOVER ESSE METODO APOS CORRIGIR USUARIO
	public void updateTokenUsuario(Long idUsuario, String token) {

		Query query = em().createQuery("update Usuario set tokenPasswordRecovery = :token where id = :idUsuario");
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("token", token);

		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findByUsername(String username) {
		Query query = em().createQuery("from Usuario usuario where upper(usuario.username) like :username order by username");
		query.setParameter("username", "%"+username.toUpperCase()+"%");

		return query.getResultList();
	}

	/**
	 * <p>
	 * <b>Regra(s) de negócio:</b> Consulta entidade de acordo com os parametros(username),
	 * caso ela não exista, salva o registro
	 * </p>
	 *
	 * @author gilberto.nery
	 * @date 09/09/2015
	 *
	 * @return PersistentObject - Entidade que foi salva ou entidade que estava cadastrada
	 */
	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		Usuario usuario = (Usuario) entity;

		Search search = new Search();

		search.addFilterEqual("username", usuario.getUsername());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}

}