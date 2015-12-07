package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PainelGrupoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.PainelGrupo;

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
 * <b>Title: </b>PainelGrupoDaoHibernate
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
 * @since 28/04/2015 - 16:44:45
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("painelGrupoDao")
public class PainelGrupoDaoHibernate extends CitGenericDAOImpl implements PainelGrupoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public PainelGrupoDaoHibernate() {

		super(PainelGrupo.class);
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
	public Collection<PainelGrupo> findPorIdPainel(Long idPainel) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("painel.id", idPainel);

		return this.search(search, PainelGrupo.class);

	}

}
