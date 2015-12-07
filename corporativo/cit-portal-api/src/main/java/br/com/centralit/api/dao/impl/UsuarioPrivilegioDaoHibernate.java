package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.UsuarioPrivilegioDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.UsuarioPrivilegio;

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
 * @since 31/08/2015 - 00:53:03
 *
 * @version 1.0.0
 *
 * @author Rogério Gomes
 *
 */
@Repository("usuarioPrivilegioDao")
public class UsuarioPrivilegioDaoHibernate extends CitGenericDAOImpl implements UsuarioPrivilegioDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public UsuarioPrivilegioDaoHibernate() {

		super(UsuarioPrivilegio.class);
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
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param idUsuario
	 * @return
	 */
	public Collection<UsuarioPrivilegio> findPorIdUsuario(Long idUsuario) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("usuario.id", idUsuario);

		return this.search(search, UsuarioPrivilegio.class);

	}

}