package br.com.centralit.api.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Sort;

import br.com.centralit.api.dao.ComissaoDao;
import br.com.centralit.api.model.Comissao;
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
 * <b>Title: DAO Hibernate de InventarioComissao </b>
 * </p>
 *
 * <p>
 * <b>Description: Implementação dos serviços de dados de InventarioComissao </b>
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
 * @since 29/12/2014 - 10:46:34
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
@Repository("inventarioComissaoDao")
public class ComissaoDaoHibernate extends CitGenericDAOImpl implements ComissaoDao {

	/** Atributo MAX_RESULT_AUTOCOMPLETE. */
	private static final int MAX_RESULT_AUTOCOMPLETE = 10;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 */
	public ComissaoDaoHibernate() {

		super(Comissao.class);
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
	 * Método responsável por
	 *
	 * @author rogerio.costa
	 *
	 * @param idColaborador
	 * @return
	 */
	public boolean exiteInventarioComissaoVinculadoAoColaborador(Long idColaborador) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("presidente.id", idColaborador);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}


	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar comissões por nome e órgão, com dataExtinção vazia ou maior que a referência vigente
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param referenciaVigente
	 * @return List<InventarioComissao>
	 */
	@Override
	public List<Comissao> listarComissaoPorNomeEOrganizacao(String nome, Long idOrganizacao, Calendar referenciaVigente) {

		SearchSeven searchSeven = new SearchSeven();

		searchSeven.addFilterOr(Filter.ilike("nome", "%"+nome+"%"), Filter.ilike("codigo", "%"+nome+"%"));

		searchSeven.addSort(Sort.asc("nome"));

		searchSeven.addFilterEqual("organizacao.id", idOrganizacao);

		searchSeven.setMaxResults(MAX_RESULT_AUTOCOMPLETE);

		searchSeven.addFilterOr(Filter.isNull("dataExtincao"), Filter.greaterOrEqual("dataExtincao", referenciaVigente));

		return this.search(searchSeven, this.persistentClass);

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar comissões por nome, órgão e domínio
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param idDominio
	 * @param referenciaVigente
	 * @return List<Comissao>
	 */
	@Override
	public List<Comissao> listarComissaoPorNomeEOrganizacaoEDominio(String nome, Long idOrganizacao, Long idDominio, Calendar referenciaVigente){

		SearchSeven searchSeven = new SearchSeven();

		searchSeven.addFilterOr(Filter.ilike("nome", "%"+nome+"%"), Filter.ilike("codigo", "%"+nome+"%"));

		searchSeven.addSort(Sort.asc("nome"));

		searchSeven.addFilterEqual("organizacao.id", idOrganizacao);

		searchSeven.addFilterEqual("dominioTipoComissao.id", idDominio);

		searchSeven.setMaxResults(MAX_RESULT_AUTOCOMPLETE);

		searchSeven.addFilterOr(Filter.isNull("dataExtincao"), Filter.greaterOrEqual("dataExtincao", referenciaVigente));

		return this.search(searchSeven, this.persistentClass);

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se contem Inventario vinculado a estrutura
	 *
	 * @author rogerio.costa
	 *
	 * @param idEstrutura
	 *
	 * @return boolean
	 */
	public boolean existeInventarioVinculadaAEstrutura(Long idEstrutura) {

		SearchSeven search = new SearchSeven();

		search.addFilterIn("estruturaOrganizacional.id", idEstrutura);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
}
