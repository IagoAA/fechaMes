package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PainelModuloDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.PainelModulo;

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
 * <b>Title: </b>PainelModuloDaoHibernate
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
@Repository("painelModuloDao")
public class PainelModuloDaoHibernate extends CitGenericDAOImpl implements PainelModuloDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public PainelModuloDaoHibernate() {

		super(PainelModulo.class);
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
	 * Método responsável por listar os ModuloUsuario através do id do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idUsuario
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<PainelModulo> findPorIdPainel(Long idPainel) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("painel.id", idPainel);

		return this.search(search, PainelModulo.class);

	}

}
