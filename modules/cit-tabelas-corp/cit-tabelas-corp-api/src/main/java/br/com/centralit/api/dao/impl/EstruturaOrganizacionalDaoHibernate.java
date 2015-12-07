package br.com.centralit.api.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.EstruturaOrganizacionalDao;
import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilDataBase;
import br.com.centralit.framework.util.UtilDate;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
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
 * <b>Title: EstruturaOrganizacionalDaoHibernate</b>
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
 * @since 10/12/2014 - 15:44:22
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Repository("estruturaOrganizacionalDao")
public class EstruturaOrganizacionalDaoHibernate extends CitGenericDAOImpl implements EstruturaOrganizacionalDao {

	public EstruturaOrganizacionalDaoHibernate() {

		super(EstruturaOrganizacional.class);
	}

	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionais(String nome) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterOr(Filter.ilike("nome", "%" + nome + "%"), Filter.ilike("classificacao", "%" + nome + "%"));

		search.addSort(Sort.asc("nome"));

		search.addFilterOr(Filter.greaterOrEqual("dataFim", UtilDate.getCalendarDaData(UtilDate.getDataAnterior(new Date()))), Filter.isEmpty("dataFim"));

		search.setMaxResults(10);

		return this.search(search, this.persistentClass);

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
	 * Método responsável por buscar todas as estruturas organizacionais nivel 0 (zero)
	 *
	 * @author renato.jesus
	 *
	 * @param idOrganizacao
	 * @return
	 */
	@Override
	public List<EstruturaOrganizacional> findParents(Long idOrganizacao) {

		// Não foi utilizado SearchSeven por necessitar listar estruturas com dataBloqueio
		Search search = new Search(this.persistentClass);

		search.addFilterEqual("organizacao.id", idOrganizacao);

		search.addFilterEmpty("estruturaOrganizacionalParent.id");

		search.addFilterEmpty("dataInativo");

		search.addFilterOr(Filter.greaterOrEqual("dataFim", UtilDate.getCalendarDaData(UtilDate.getDataAnterior(new Date()))), Filter.isEmpty("dataFim"));

		return this.search(search, this.persistentClass);
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
	 * Método responsável por buscar todas as estruturas organizacionais filhas com filtro datafim ou não
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idOrganizacao
	 * @param exibirEstruturasAtivas
	 * @return List<EstruturaOrganizacional>
	 */
	@Override
	public List<EstruturaOrganizacional> findChildrens(Long idOrganizacao, Boolean exibirEstruturasAtivas) {

		// Não foi utilizado SearchSeven por necessitar listar estruturas com dataBloqueio
		Search search = new Search(this.persistentClass);

		if (exibirEstruturasAtivas) {

			search.addFilterOr(Filter.greaterOrEqual("dataFim", UtilDate.getCalendarDaData(UtilDate.getDataAnterior(new Date()))), Filter.isEmpty("dataFim"));
		}

		search.addFilterEqual("estruturaOrganizacionalParent.id", idOrganizacao);

		search.addSortAsc("nome");

		return this.search(search, this.persistentClass);
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
	 * Método responsável por validar se uma estrutura possui filhos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idOrganizacao
	 * @return boolean
	 */
	@Override
	public Boolean possuiFilhos(Long idOrganizacao) {

		// Não foi utilizado SearchSeven por necessitar listar estruturas com dataBloqueio
		Search search = new Search(this.persistentClass);

		search.addFilterEqual("estruturaOrganizacionalParent.id", idOrganizacao);

		return this.count(search) > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorOrganizacao(String nome, Long idOrganizacao) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterOr(Filter.ilike("nome", "%" + nome + "%"), Filter.ilike("classificacao", "%" + nome + "%"));

		search.addFilterEqual("organizacao.id", idOrganizacao);

		search.addSort(Sort.asc("nome"));

		search.addFilterOr(Filter.greaterOrEqual("dataFim", UtilDate.getCalendarDaData(UtilDate.getDataAnterior(new Date()))), Filter.isEmpty("dataFim"));

		search.setMaxResults(10);

		return this.search(search, this.persistentClass);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisFilhasPorOrganizacao(String nome, long idOrganizacao) {
		StringBuilder queryPostgres = new StringBuilder();

		queryPostgres.append("WITH RECURSIVE rel_tree AS ");
		queryPostgres.append("(SELECT estruturaorganizacional.id, organizacao.nome, organizacao.isOrgao, codigo, classificacao, dataReferenciaVigente, configuracao_id, dataBloqueio, dataFim, dataInicio, possuiBemPatrimonial, sigla, estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id,estruturaOrganizacionalResponsavel_id, organizacao_id, diasRequisicao, unidadeConsumidoraRequisitante, almoxarifado, datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id, 1 AS level, array[estruturaorganizacional.id] AS path_info ");
		queryPostgres.append("FROM estruturaorganizacional ");
		queryPostgres.append("INNER Join  organizacao on organizacao.id = estruturaorganizacional.id ");
		queryPostgres.append("WHERE organizacao_id = " + idOrganizacao + " AND (dataFim is null OR dataFim >= TO_DATE('"+ UtilDate.getDataAtualSemHoras() +"', 'yyyy-MM-dd') ) AND dataBloqueio IS NULL AND (lower(nome) LIKE lower('%" + nome + "%') OR lower(sigla) LIKE lower('%" + nome + "%') OR lower(classificacao) LIKE lower('%" + nome + "%') ) ");
		queryPostgres.append("UNION ALL ");
		queryPostgres.append("SELECT e.id, organizacao.nome, organizacao.isOrgao, e.codigo, e.classificacao, organizacao.dataReferenciaVigente, e.configuracao_id, organizacao.dataBloqueio, e.dataFim, e.dataInicio, e.possuiBemPatrimonial, organizacao.sigla, e.estruturaorganizacionalparent_id, e.localizacao_id, e.dominioTipoEstruturaOrganizacional_id, e.estruturaOrganizacionalResponsavel_id, e.organizacao_id, e.diasRequisicao, e.unidadeConsumidoraRequisitante, e.almoxarifado, organizacao.datacriacao, organizacao.dataedicao, organizacao.datainativo, organizacao.autor_id, organizacao.editor_id, organizacao.version, organizacao.inativador_id, p.level + 1, p.path_info||e.id ");
		queryPostgres.append("FROM estruturaorganizacional e ");
		queryPostgres.append("INNER JOIN  organizacao  on organizacao.id = e.id ");
		queryPostgres.append("JOIN rel_tree p ON p.id = e.organizacao_id ) ");
		queryPostgres.append("SELECT DISTINCT ON (id) id, nome, isOrgao, codigo, classificacao, dataReferenciaVigente, configuracao_id, dataBloqueio, dataFim, dataInicio, possuiBemPatrimonial, sigla, estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id, estruturaOrganizacionalResponsavel_id, organizacao_id, diasRequisicao, unidadeConsumidoraRequisitante, almoxarifado, datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id, level ");
		queryPostgres.append("FROM rel_tree WHERE dataBloqueio IS NULL AND dataInativo IS NULL ORDER BY id, level DESC");

		StringBuilder querySqlServer = new StringBuilder();

		querySqlServer.append("WITH estruturaorganizacional_CTE AS ")
					.append("SELECT estruturaorganizacional.id, organizacao.nome, organizacao.isOrgao, codigo, classificacao, organizacao.dataReferenciaVigente, ")
					.append("configuracao_id, organizacao.dataBloqueio, dataFim, dataInicio, possuiBemPatrimonial, sigla, estruturaorganizacionalparent_id, localizacao_id, ")
					.append("dominioTipoEstruturaOrganizacional_id, estruturaOrganizacionalResponsavel_id, organizacao_id, diasRequisicao, ")
					.append("unidadeConsumidoraRequisitante, almoxarifado, organizacao.datacriacao, organizacao.dataedicao, organizacao.datainativo, organizacao.autor_id, "
							+ "organizacao.editor_id, ")
					.append("version, organizacao.inativador_id, 1 AS level")
					.append("FROM estruturaOrganizacional INNER Join organizacao ON organizacao.id = estruturaorganizacional.id ")
					.append("WHERE organizacao_id =" + idOrganizacao + "AND (dataFim is null OR dataFim >= CAST('"+ UtilDate.getDataAtualSemHoras() +"' AS date) AND dataBloqueio IS NULL AND ")
					.append("(lower(nome) LIKE lower('%" + nome + "%') OR lower(sigla) LIKE lower('%" + nome + "%') OR lower(classificacao) LIKE lower('%" + nome + "%'))) ")
					.append("UNION ALL ")
					.append("SELECT e.id, organizacao.nome, organizacao.isOrgao, e.codigo, e.classificacao, organizacao.dataReferenciaVigente, e.configuracao_id, ")
					.append("organizacao.dataBloqueio, e.dataFim, e.dataInicio, e.possuiBemPatrimonial, organizacao.sigla, e.estruturaorganizacionalparent_id, ")
					.append("e.localizacao_id, e.dominioTipoEstruturaOrganizacional_id, e.estruturaOrganizacionalResponsavel_id, e.organizacao_id, e.diasRequisicao, ")
					.append("e.unidadeConsumidoraRequisitante, e.almoxarifado, organizacao.datacriacao, organizacao.dataedicao, organizacao.datainativo, ")
					.append("organizacao.autor_id, organizacao.editor_id, organizacao.version, organizacao.inativador_id, p.level + 1 ")
					.append("FROM estruturaorganizacional e INNER JOIN organizacao ON organizacao.id = e.id ")
					.append("JOIN estruturaorganizacional_CTE p ON p.id = e.organizacao_id ")
					.append(")")
					.append("SELECT DISTINCT id, nome, isOrgao, codigo, classificacao, dataReferenciaVigente, configuracao_id, dataBloqueio, dataFim, dataInicio, ")
					.append("possuiBemPatrimonial, sigla, estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id, ")
					.append("estruturaOrganizacionalResponsavel_id, organizacao_id, diasRequisicao, unidadeConsumidoraRequisitante, almoxarifado, ")
					.append("datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id, level ")
					.append("FROM estruturaorganizacional_CTE WHERE dataBloqueio IS NULL AND dataInativo IS NULL ORDER BY id, level DESC ");

		Query query = null;
		switch (UtilDataBase.getDataBaseName(em())){
			case POSTGRESQL :
				query = em().createNativeQuery(queryPostgres.toString(), EstruturaOrganizacional.class);
		return query.getResultList();
			case SQL_SERVER :
				query = em().createNativeQuery(querySqlServer.toString(), EstruturaOrganizacional.class);
				return query.getResultList();
			case ORACLE :
			default:
				throw new IllegalArgumentException("N&atilde;o implementado para o banco de dados da conex&atilde;o atual. Verifique a "
							+ "conex&atilde;o e tente novamente.");
	}
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
	 * Método responsável por listar todas as <code>EstruturaOrganizacional</code> de uma <code>Organizacao</code>
	 *
	 * @author geovane.filho
	 *
	 * @param idOrganizacao
	 *            ID da Organizacao a se obter as Estruturas Organizacionais.
	 *
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorOrganizacao(Long idOrganizacao) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterEqual("organizacao.id", idOrganizacao);

		search.addSort(Sort.asc("nome"));

		search.addFilterOr(Filter.greaterOrEqual("dataFim", UtilDate.getCalendarDaData(UtilDate.getDataAnterior(new Date()))), Filter.isEmpty("dataFim"));

		return this.search(search, this.persistentClass);

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
	 * Método responsável por verificar se a localização possui uma estrutura vinculada
	 *
	 * @author iago.almeida
	 *
	 * @param localizacaoId
	 * @return
	 */
	@Override
	public boolean existeEstruturaVinculadaALocacalizacao(Long localizacaoId, Usuario usuarioLogado) {

		SearchSeven search = new SearchSeven();

		search.addFilterOr(Filter.equal("organizacao.id", usuarioLogado.getOrganizacao().getId()), Filter.equal("id", usuarioLogado.getOrganizacao().getId()));

		search.addFilterIn("localizacao.id", localizacaoId);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
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
	 * Método responsável por listar estruturas organizacionais na tree por nome
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @return List<EstruturaOrganizacional>
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorNomeTree(String nome) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterILike("nome", "%" + nome + "%");

		search.addSort(Sort.asc("nome"));

		return this.search(search, this.persistentClass);
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
	 * Método responsável por listar estruturas organizacionais da tree pelo id da órgão do usuário logado e nome
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idEstrutura
	 * @param nome
	 * @param exibirEstruturasAtivas
	 * @return List<EstruturaOrganizacional>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisDaTree(Long idOrganizacao, String nome, Boolean exibirEstruturasAtivas) {

		StringBuilder strQuery = new StringBuilder();
		Query query;

		switch (UtilDataBase.getDataBaseName(em())){

			//////////////////////////////////////// POSTGRESQL //////////////////////////////////////////
			case POSTGRESQL :

		strQuery.append("WITH RECURSIVE rel_tree AS ");
		strQuery.append("(SELECT id, nome, codigo, classificacao, "
				+ "dataBloqueio, dataFim, dataInicio, sigla,"
				+ " estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id, organizacao_id,"
				+ " datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id, 1 AS level, array[id] AS path_info ");
		strQuery.append("FROM estruturaorganizacional ");

		strQuery.append("WHERE organizacao_id = " + idOrganizacao);

		if(exibirEstruturasAtivas) {
			strQuery.append(" AND ((dataFim is null OR dataFim >= TO_DATE('"+ UtilDate.getDataAtualSemHoras() +"', 'yyyy-MM-dd')) AND (dataBloqueio IS NULL OR dataBloqueio >= TO_DATE('"+ UtilDate.getDataAtualSemHoras() +"', 'yyyy-MM-dd')))");
		}

		strQuery.append(" AND (lower(nome) LIKE lower('%" + nome + "%') OR lower(sigla) LIKE lower('%" + nome + "%')) ");
		strQuery.append("AND dataInativo IS NULL ");

		strQuery.append("UNION ALL ");
		strQuery.append("SELECT e.id, e.nome, e.codigo, e.classificacao, "
				+ "e.dataBloqueio, e.dataFim, e.dataInicio, e.sigla, "
				+ "e.estruturaorganizacionalparent_id, e.localizacao_id, e.dominioTipoEstruturaOrganizacional_id, e.organizacao_id, "
				+ "e.datacriacao, e.dataedicao, e.datainativo, e.autor_id, e.editor_id, e.version,"
				+ "e.inativador_id, p.level + 1, p.path_info||e.id ");
		strQuery.append("FROM estruturaorganizacional e ");
		strQuery.append("JOIN rel_tree p ON e.id = p.estruturaorganizacionalparent_id ) ");
		strQuery.append("SELECT DISTINCT ON (id) id, nome, codigo, classificacao, "
				+ "dataBloqueio, dataFim, dataInicio, sigla, estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id, "
				+ "organizacao_id, datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id, level ");
		strQuery.append("FROM rel_tree WHERE (dataBloqueio IS NULL OR dataBloqueio >= TO_DATE('"+ UtilDate.getDataAtualSemHoras() +"', 'yyyy-MM-dd')) AND dataInativo IS NULL ORDER BY id, level DESC");

				query = em().createNativeQuery(strQuery.toString(), EstruturaOrganizacional.class);

		return query.getResultList();

			//////////////////////////////////////SQL SERVER //////////////////////////////////////////
			case SQL_SERVER :

				strQuery.append("WITH rel_tree (id, nome, codigo, classificacao, dataBloqueio, ")
				.append("dataFim, dataInicio, sigla, estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id, ")
				.append("organizacao_id, datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id, level )")
				.append("as (SELECT id, nome, codigo, classificacao, ")
				.append("dataBloqueio, dataFim, dataInicio, sigla, ")
				.append("estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id, organizacao_id, ")
				.append("datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id , 1 AS level FROM estruturaorganizacional estruturaorganizacional ");

				strQuery.append("WHERE organizacao_id = " + idOrganizacao);
				if(exibirEstruturasAtivas) {
					strQuery.append(" AND ((dataFim is null OR dataFim >= CAST('"+ UtilDate.getDataAtualSemHoras() +"' AS date)) AND (dataBloqueio IS NULL OR dataBloqueio >= TO_DATE('"+ UtilDate.getDataAtualSemHoras() +"', 'yyyy-MM-dd')))");
				}
				strQuery.append(" AND (lower(nome) LIKE lower('%" + nome + "%') OR lower(sigla) LIKE lower('%" + nome + "%')) ");
				strQuery.append(" AND dataInativo IS NULL ");

				strQuery.append("UNION ALL ");
				strQuery.append("SELECT e.id, e.nome, e.codigo, e.classificacao, ")
				.append("e.dataBloqueio, e.dataFim, e.dataInicio, e.sigla, ")
				.append("e.estruturaorganizacionalparent_id, e.localizacao_id, e.dominioTipoEstruturaOrganizacional_id, e.organizacao_id, ")
				.append("e.datacriacao, e.dataedicao, e.datainativo, e.autor_id, e.editor_id, e.version, ")
				.append("e.inativador_id , level + 1 FROM estruturaorganizacional e ")
				.append("JOIN rel_tree p ON e.id = p.estruturaorganizacionalparent_id ) ");
				strQuery.append("SELECT DISTINCT (id) id, nome, codigo, classificacao, ")
				.append("dataBloqueio, dataFim, dataInicio, sigla, estruturaorganizacionalparent_id, localizacao_id, dominioTipoEstruturaOrganizacional_id, ")
				.append("organizacao_id, datacriacao, dataedicao, datainativo, autor_id, editor_id, version, inativador_id ")
				.append("FROM rel_tree WHERE (dataBloqueio IS NULL OR dataBloqueio >= TO_DATE('"+ UtilDate.getDataAtualSemHoras() +"', 'yyyy-MM-dd')) AND dataInativo IS NULL ORDER BY id DESC ");

				query = em().createNativeQuery(strQuery.toString(), EstruturaOrganizacional.class);

				return query.getResultList();


			//TODO
	/**
				 * Implementar sql nativa para os outros bancos
				 */
			//////////////////////////////////////ORACLE//////////////////////////////////////////
			case ORACLE :
					throw new IllegalArgumentException("SQL inexistente para o SGBD Oracle");


			//////////////////////////////////////MySQL//////////////////////////////////////////
			case MYSQL :
				throw new IllegalArgumentException("SQL inexistente para o SGBD MySQL");


			//////////////////////////////////////DB2//////////////////////////////////////////
			case DB2 :
				throw new IllegalArgumentException("SQL inexistente para o SGBD MySQL");


			default:
				throw new IllegalArgumentException("SQL inexistente para o SGBD");

			}

	}

	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		Organizacao organizacao = (Organizacao) entity;

		Search search = new Search();

		search.addFilterEqual("nome", organizacao.getNome());

		search.addFilterEqual("sigla", organizacao.getSigla());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EstruturaOrganizacional> listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao(String nome, Long idOrganizacao, Long idDominioUnidadeColeta) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterOr(Filter.ilike("nome", "%" + nome + "%"), Filter.ilike("classificacao", "%" + nome + "%"));

		search.addFilterEqual("organizacao.id", idOrganizacao);

		search.addFilterEqual("dominioTipoEstruturaOrganizacional.id", idDominioUnidadeColeta);

		search.addSort(Sort.asc("nome"));

		search.addFilterOr(Filter.greaterOrEqual("dataFim", UtilDate.getCalendarDaData(UtilDate.getDataAnterior(new Date()))), Filter.isEmpty("dataFim"));

		search.setMaxResults(10);

		return this.search(search, this.persistentClass);
	}

}