package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Sort;

import br.com.centralit.api.dao.RegiaoDao;
import br.com.centralit.api.model.Pais;
import br.com.centralit.api.model.Regiao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.util.UtilObjeto;

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
 * @since 09/12/2014 - 14:47:19
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Repository("regiaoDao")
public class RegiaoDaoHibernate extends CitGenericDAOImpl implements RegiaoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public RegiaoDaoHibernate() {

		super(Regiao.class);
	}

	/**
	 * Método responsável por listar a Endiade<code>Regiao</code>
	 *
	 * @author rogerio.costa
	 *
	 * @return Collection<Cidade>
	 */
	public Collection<Regiao> listarRegiao(Pais pais, String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		if (UtilObjeto.isReferencia(pais)) {

			search.addFilterEqual("pais.id", pais.getId());

		}

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Regiao.class);

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se o pais a ser excluido é utilizado por regiao
	 *
	 * @author iago.almeida
	 *
	 * @return boolean
	 */
	@Override
	public boolean existeRegiaoVinculadoAoPais(Long idPais) {
		SearchSeven search = new SearchSeven();

		search.addFilterIn("pais.id", idPais);

		search.addFilterEmpty("dataInativo");

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;

	}

}
