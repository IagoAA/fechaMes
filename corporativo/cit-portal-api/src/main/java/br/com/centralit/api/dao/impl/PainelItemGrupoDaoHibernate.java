package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PainelItemGrupoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.PainelItemGrupo;

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
 * @since 29/04/2015 - 15:07:31
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("painelItemGrupoDao")
public class PainelItemGrupoDaoHibernate extends CitGenericDAOImpl implements PainelItemGrupoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public PainelItemGrupoDaoHibernate() {

		super(PainelItemGrupo.class);
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
	 * Método responsável por listar os PainelItemGrupo através do id do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainelItem
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<PainelItemGrupo> findPorIdPainelItem(Long idPainelItem) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("painelItem.id", idPainelItem);

		return this.search(search, PainelItemGrupo.class);

	}

}
