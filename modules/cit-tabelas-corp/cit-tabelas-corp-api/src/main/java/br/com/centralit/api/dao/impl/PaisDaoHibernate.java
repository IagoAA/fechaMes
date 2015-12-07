package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Sort;

import br.com.centralit.api.dao.PaisDao;
import br.com.centralit.api.model.Pais;
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
 * @since 26/11/2014 - 10:16:02
 *
 * @version 1.0.0
 *
 * @author david.silva
 *
 */
@Repository("paisDao")
public class PaisDaoHibernate extends CitGenericDAOImpl implements PaisDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public PaisDaoHibernate() {

		super(Pais.class);
	}

	/**
	 * Método responsável por listar a Endiade<code>Pais</code>
	 *
	 * @author rogerio.costa
	 *
	 * @return Collection<Cidade>
	 */
	public Collection<Pais> listarPais(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Pais.class);

	}

	/**
	 * Método responsável por verifica se existe um país cadastrado com o mesmo nome.
	 * 
	 * @author luis.camargo
	 * 
	 *  @param pais
	 *  @return boolean
	 */
	@Override
	public boolean existePaisMesmoNome(Pais pais) {
		SearchSeven search = new SearchSeven();
		if(pais.getId() != null) {
			search.addFilterNotEqual("id", pais.getId());
		}
		search.addFilterILike("nome", pais.getNome());
		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
