package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.EstruturaOrganizacionalResponsavelDao;
import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

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
 * @since 06/01/2015 - 15:13:27
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Repository("estruturaOrganizacionalResponsavelDao")
public class EstruturaOrganizacionalResponsavelDaoHibernate extends CitGenericDAOImpl implements EstruturaOrganizacionalResponsavelDao {

	public EstruturaOrganizacionalResponsavelDaoHibernate() {

		super(EstruturaOrganizacionalResponsavel.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<EstruturaOrganizacionalResponsavel> findByIdEstruturaOrganizacional(EstruturaOrganizacional estruturaOrganizacional) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("estruturaOrganizacional", estruturaOrganizacional);

		return this.search(search, EstruturaOrganizacionalResponsavel.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<EstruturaOrganizacionalResponsavel> findByIdEstruturaOrganizacionalOrdemAsc(Long estruturaOrganizacionalId) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("estruturaOrganizacional.id", estruturaOrganizacionalId);
		search.addSortAsc("ordem");

		return this.search(search, EstruturaOrganizacionalResponsavel.class);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param idDetentor
	 * @return
	 */
	public boolean exiteEstruturaOrganizacionalResponsavelVinculadoAoColaborador(Long idColaborador) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("responsavel.id", idColaborador);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
}
