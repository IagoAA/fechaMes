package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.LuckDao;
import br.com.centralit.api.model.Luck;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

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
 * @since 26/11/2015 - 10:16:02
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
@Repository("luckDao")
public class LuckDaoHibernate extends CitGenericDAOImpl implements LuckDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public LuckDaoHibernate() {

		super(Luck.class);
	}

	/**
	 * Método responsável por listar a Endiade<code>Luck</code>
	 *
	 * @author iago.almeida
	 *
	 * @return Collection<Cidade>
	 */
	public Collection<Luck> listarLuck(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Luck.class);

	}

	/**
	 * Método responsável por verifica se existe um luck cadastrado com o mesmo nome.
	 *
	 * @author luis.camargo
	 *
	 *  @param luck
	 *  @return boolean
	 */
	@Override
	public boolean existeLuckMesmoNome(Luck luck) {
		SearchSeven search = new SearchSeven();
		if(luck.getId() != null) {
			search.addFilterNotEqual("id", luck.getId());
		}
		search.addFilterILike("nome", luck.getNome());
		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
