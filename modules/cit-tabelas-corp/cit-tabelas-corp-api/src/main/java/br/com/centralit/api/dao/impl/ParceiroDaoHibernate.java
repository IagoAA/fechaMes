package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ParceiroDao;
import br.com.centralit.api.model.Parceiro;
import br.com.centralit.api.viewHelper.DominioParceirosNomeBuscaVH;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

import com.googlecode.genericdao.search.Filter;
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
 * @since 09/12/2014 - 08:33:12
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Repository("parceiroDao")
public class ParceiroDaoHibernate extends CitGenericDAOImpl implements ParceiroDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public ParceiroDaoHibernate() {

		super(Parceiro.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Parceiro> findParceiros(DominioParceirosNomeBuscaVH objeto, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();

		search.addField("id");

		search.addField("pessoa");

		search.addField("pessoa.nome");
		
		search.addField("classeParceiro.dominioTipoParceiro.codigo");
		
		search.addField("classeParceiro.dominioTipoParceiro.descricao");

		search.addFilterILike("pessoa.nome", "%" + objeto.getValue() + "%");

		search.addFilterEqual("classeParceiro.dominioTipoParceiro.chave", objeto.getTipoDominio());

		// criar array de Filter para criar uma lista de condições de OR
		Filter [] filters = new Filter [objeto.getParceiros().size()];

		// percorre a lista de parceiros para criar o Filter com a string do tipo de parceiro
		for(int i = 0; i < objeto.getParceiros().size(); i++){

			filters[i] = Filter.equal("classeParceiro.dominioTipoParceiro.nome", objeto.getParceiros().get(i));

		}

		search.addFilterOr(filters);

		search.addFilterEqual("pessoa.organizacao.id", idOrganizacao);
		
		search.addSort(Sort.asc("pessoa.nome"));

		search.setMaxResults(10);

		search.setResultMode(SearchSeven.RESULT_MAP);

		return this.search(search, this.persistentClass);

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar os parceiros através do id da pessoa
	 *
	 * @author rogerio.costa
	 *
	 * @param idPessoa
	 *
	 * @return Collection<Parceiro>
	 */
	public Collection<Parceiro> findPorIdPessoa(Long idPessoa){

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("pessoa.id", idPessoa);

		return this.search(search, Parceiro.class);

	}

}
