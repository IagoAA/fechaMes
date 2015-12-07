package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.MapaOrganizacionalDao;
import br.com.centralit.api.model.MapaOrganizacional;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Usuario;

import com.googlecode.genericdao.search.Sort;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 10/12/2014 - 15:50:28
 *
 * @version 1.0.0
 *
 * @author david.silva
 *
 */
@Repository("mapaOrganizacionalDao")
public class MapaOrganizacionalDaoHibernate extends CitGenericDAOImpl implements MapaOrganizacionalDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public MapaOrganizacionalDaoHibernate() {
		super(MapaOrganizacional.class);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public MapaOrganizacional findMapaAtivo(Usuario usuarioLogado) {

		SearchSeven search = new SearchSeven(this.persistentClass);
		
		search.addFilterEqual("organizacao.id", usuarioLogado.getOrganizacao().getId());

		search.addFilterEmpty("dataFim");

		return this.searchUnique(search);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar o ultimo mapa organizacional fechado
	 *
	 * @author thiago.borges
	 *
	 * @return
	 */
	@Override
	public MapaOrganizacional findUltimoMapaFechado() {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterNotEmpty("dataFim");

		search.addSort(Sort.desc("dataFim"));

		search.addSort(Sort.desc("id"));

		search.setMaxResults(1);

		return this.searchUnique(search, this.persistentClass);
	}


}
