package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.CaixaDao;
import br.com.centralit.api.model.Caixa;
import br.com.centralit.api.model.Luck;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Sort;

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
 * @since 09/12/2015 - 14:47:19
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
@Repository("caixaDao")
public class CaixaDaoHibernate extends CitGenericDAOImpl implements CaixaDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public CaixaDaoHibernate() {

		super(Caixa.class);
	}

	/**
	 * Método responsável por listar a Endiade<code>Caixa</code>
	 *
	 * @author iago
	 *
	 * @return Collection<Cidade>
	 */
	public Collection<Caixa> listarCaixa(Luck luck, String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		if (UtilObjeto.isReferencia(luck)) {

			search.addFilterEqual("luck.id", luck.getId());

		}

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Caixa.class);

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se o luck a ser excluido é utilizado por caixa
	 *
	 * @author iago.almeida
	 *
	 * @return boolean
	 */
	@Override
	public boolean existeCaixaVinculadoAoLuck(Long idLuck) {
		SearchSeven search = new SearchSeven();

		search.addFilterIn("luck.id", idLuck);

		search.addFilterEmpty("dataInativo");

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;

	}

}
