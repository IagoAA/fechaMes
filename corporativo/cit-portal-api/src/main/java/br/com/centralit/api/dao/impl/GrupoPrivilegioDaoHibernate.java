package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.GrupoPrivilegioDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.GrupoPrivilegio;

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
 * @since 09/03/2015 - 08:34:02
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("grupoPrivilegioDao")
public class GrupoPrivilegioDaoHibernate extends CitGenericDAOImpl implements GrupoPrivilegioDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classs
	 */
	public GrupoPrivilegioDaoHibernate() {

		super(GrupoPrivilegio.class);
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
	 * Método responsável por listar os GrupoPrivilegio através do id do grupo
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idGrupo
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<GrupoPrivilegio> findPorIdGrupo(Long idGrupo) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("grupo.id", idGrupo);

		return this.search(search, GrupoPrivilegio.class);

	}

}
