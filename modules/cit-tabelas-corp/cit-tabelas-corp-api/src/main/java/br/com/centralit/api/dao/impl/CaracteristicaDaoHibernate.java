package br.com.centralit.api.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Sort;

import br.com.centralit.api.dao.CaracteristicaDao;
import br.com.centralit.api.model.Caracteristica;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

@Repository("caracteristicaDao")
public class CaracteristicaDaoHibernate extends CitGenericDAOImpl implements CaracteristicaDao {

	public CaracteristicaDaoHibernate() {

		super(Caracteristica.class);
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
	 * Método responsável por listar as entidades <code>Caracteristica</code> a partir de um filtro
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param descricao
	 */
	@Override
	public Collection<Caracteristica> listarCaracteristicas(String descricao) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterILike("descricao", "%" + descricao + "%");

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("descricao"));

		search.setMaxResults(10);

		return this.search(search, this.persistentClass);
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar Caracteristicas por nome e id da organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param descricao
	 * @param idOrganizacao
	 * @return
	 */
	@Override
	public List<Caracteristica> listarCaracteristicasPorOrganizacao(String descricao, Long idOrganizacao) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterILike("descricao", "%" + descricao + "%");

		search.addFilterEmpty("dataInativo");
		
		search.addFilterEqual("organizacao.id", idOrganizacao);

		search.addSort(Sort.asc("descricao"));

		search.setMaxResults(10);

		return this.search(search, Caracteristica.class);
	}

}
