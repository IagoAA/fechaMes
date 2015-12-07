package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.GrupoUsuarioDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.GrupoUsuario;

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
 * @since 04/03/2015 - 15:30:35
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("grupoUsuarioDao")
public class GrupoUsuarioDaoHibernate extends CitGenericDAOImpl implements GrupoUsuarioDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public GrupoUsuarioDaoHibernate() {

		super(GrupoUsuario.class);
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
	 * Método responsável por listar os GrupoUsuario através do id do grupo
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idGrupo
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<GrupoUsuario> findPorIdGrupo(Long idGrupo) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("grupo.id", idGrupo);

		return this.search(search, GrupoUsuario.class);

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
	public Collection<Long> findIdsGrupoUsuarioPorIdUsuario(Long idUsuario) {

		SearchSeven search = new SearchSeven();

		search.addField("grupo.id").addFilterEqual("usuario.id", idUsuario);

		return this.search(search, GrupoUsuario.class);

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
	 * Método responsável por listar os GrupoUsuario através do id do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idUsuario
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<GrupoUsuario> findPorIdUsuario(Long idUsuario) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("usuario.id", idUsuario);

		return this.search(search, GrupoUsuario.class);

	}

}
