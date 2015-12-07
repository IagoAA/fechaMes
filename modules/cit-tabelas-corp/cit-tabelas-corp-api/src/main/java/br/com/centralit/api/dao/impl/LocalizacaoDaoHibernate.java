package br.com.centralit.api.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Sort;

import br.com.centralit.api.dao.LocalizacaoDao;
import br.com.centralit.api.model.Localizacao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

@Repository("localizacaoDao")
public class LocalizacaoDaoHibernate extends CitGenericDAOImpl implements LocalizacaoDao {

	public LocalizacaoDaoHibernate() {

		super(Localizacao.class);

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
	 * Método responsável por buscar a localizacao pelo nome
	 * 
	 * @author renato.jesus
	 * 
	 * @param nome
	 * @return
	 */
	@Override
	public Collection<Localizacao> findLocalizacaoPorNome(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Localizacao.class);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar Localizacao por nome e id da organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	@Override
	public List<Localizacao> listarLocalizacaoPorOrganizacao(String nome, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEmpty("dataInativo");
		
		search.addFilterEqual("organizacao.id", idOrganizacao);

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Localizacao.class);
	}
}
