package br.com.centralit.framework.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;
import br.com.centralit.framework.util.UtilObjeto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

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
 * <b>Title: </b> Classe SearchParams
 * </p>
 *
 * <p>
 * <b>Description: </b> Classe de dominio SearchParams (Salvar filtros de página referente ao usuario logado) do Usuario
 * </p>
 *
 * @since 19/11/2014 - 09:13:08
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Entity
@JsonIgnoreProperties({ "$checked" })
public class SearchParams extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 317657072058908515L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoView.class })
	protected Long id;

	/** Atributo sort. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String sort = "id";

	/** Atributo dir. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String dir = "desc";

	/** Atributo start. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private Integer start;

	/** Atributo limit. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	@Column(name = "coluna_limit")
	private Integer limit;

	/** Atributo whereCustomize. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String whereCustomize = "";

	/** Atributo join. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	@Column(name = "coluna_join")
	private String join = "";

	/** Atributo conditionWhere. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String conditionWhere;

	/** Atributo keywordValue. Para busca genérica em todos os filters */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String keywordValue;

	/** Atributo fields. */
	@ElementCollection
	@CollectionTable(name = "searhParams_fields", joinColumns = @JoinColumn(name = "searchParams_id"))
	@Column(name = "field")
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private List<String> fields;

	/** Atributo blnInnerJoin. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private Boolean blnInnerJoin = false;

	/** Atributo nome. */
	@JsonView({ Views.UsuarioEditView.class, Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String nome;

	/** Atributo paginaUsuario. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private PaginaUsuario paginaUsuario;

	/** Atributo filters. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "searchParams", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private List<Filter> filters;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param nome
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>sort</code>
	 *
	 * @return <code>String</code>
	 */
	public String getSort() {

		return sort;
	}

	/**
	 * Define o valor do atributo <code>sort</code>.
	 *
	 * @param sort
	 */
	public void setSort(String sort) {

		this.sort = sort;
	}

	/**
	 * Retorna o valor do atributo <code>dir</code>
	 *
	 * @return <code>String</code>
	 */
	public String getDir() {

		return dir;
	}

	/**
	 * Define o valor do atributo <code>dir</code>.
	 *
	 * @param dir
	 */
	public void setDir(String dir) {

		this.dir = dir;
	}

	/**
	 * Retorna o valor do atributo <code>start</code>
	 *
	 * @return <code>Integer</code>
	 */
	public Integer getStart() {

		return (start * limit) - limit;
	}

	/**
	 * Define o valor do atributo <code>start</code>.
	 *
	 * @param start
	 */
	public void setStart(Integer start) {

		this.start = start;
	}

	/**
	 * Retorna o valor do atributo <code>limit</code>
	 *
	 * @return <code>Integer</code>
	 */
	public Integer getLimit() {

		return limit;
	}

	/**
	 * Define o valor do atributo <code>limit</code>.
	 *
	 * @param limit
	 */
	public void setLimit(Integer limit) {

		this.limit = limit;
	}

	/**
	 * Retorna o valor do atributo <code>whereCustomize</code>
	 *
	 * @return <code>String</code>
	 */
	public String getWhereCustomize() {

		return whereCustomize;
	}

	/**
	 * Define o valor do atributo <code>whereCustomize</code>.
	 *
	 * @param whereCustomize
	 */
	public void setWhereCustomize(String whereCustomize) {

		this.whereCustomize = whereCustomize;
	}

	/**
	 * Retorna o valor do atributo <code>join</code>
	 *
	 * @return <code>String</code>
	 */
	public String getJoin() {

		return join;
	}

	/**
	 * Define o valor do atributo <code>join</code>.
	 *
	 * @param join
	 */
	public void setJoin(String join) {

		this.join = join;
	}

	/**
	 * Retorna o valor do atributo <code>conditionWhere</code>
	 *
	 * @return <code>String</code>
	 */
	public String getConditionWhere() {

		return conditionWhere;
	}

	/**
	 * Define o valor do atributo <code>conditionWhere</code>.
	 *
	 * @param conditionWhere
	 */
	public void setConditionWhere(String conditionWhere) {

		this.conditionWhere = conditionWhere;
	}

	/**
	 * Retorna o valor do atributo <code>keywordValue</code>
	 *
	 * @return <code>String</code>
	 */
	public String getKeywordValue() {
		return keywordValue;
	}

	/**
	 * Define o valor do atributo <code>keywordValue</code>.
	 *
	 * @param keywordValue
	 */
	public void setKeywordValue(String keywordValue) {
		this.keywordValue = keywordValue;
	}

	/**
	 * Retorna o valor do atributo <code>fields</code>
	 *
	 * @return <code>List<String></code>
	 */
	public List<String> getFields() {

		return fields;
	}

	/**
	 * Define o valor do atributo <code>fields</code>.
	 *
	 * @param fields
	 */
	public void setFields(List<String> fields) {

		this.fields = fields;
	}

	/**
	 * Retorna o valor do atributo <code>blnInnerJoin</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getBlnInnerJoin() {

		return blnInnerJoin;
	}

	/**
	 * Define o valor do atributo <code>blnInnerJoin</code>.
	 *
	 * @param blnInnerJoin
	 */
	public void setBlnInnerJoin(Boolean blnInnerJoin) {

		this.blnInnerJoin = blnInnerJoin;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 *
	 * @param nome
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * Retorna o valor do atributo <code>paginaUsuario</code>
	 *
	 * @return <code>PaginaUsuario</code>
	 */
	public PaginaUsuario getPaginaUsuario() {

		return paginaUsuario;
	}

	/**
	 * Define o valor do atributo <code>paginaUsuario</code>.
	 *
	 * @param paginaUsuario
	 */
	public void setPaginaUsuario(PaginaUsuario paginaUsuario) {

		this.paginaUsuario = paginaUsuario;
	}

	/**
	 * Retorna o valor do atributo <code>filters</code>
	 *
	 * @return <code>List<Filter></code>
	 */
	public List<Filter> getFilters() {

		if(!UtilObjeto.isReferencia(this.filters)){

			this.filters = new ArrayList<Filter>();

		}
		return filters;
	}

	/**
	 * Define o valor do atributo <code>filters</code>.
	 *
	 * @param filters
	 */
	public void setFilters(List<Filter> filters) {

		this.filters = filters;
	}

	/**
	 * Método responsável por converter a string(array) em um List<String> adicionando a lista de fields do objeto(this)
	 *
	 * @author wilker.machado
	 *
	 * @param <code>String</code> array
	 */
	public void addFields(String array) {

		this.fields = new ArrayList<String>();

		// converter a string ex('[teste, teste]') em lista
		List<String> lista = Arrays.asList(array.split("\\s*,\\s*"));

		// repetição para percorrer a lista e adicionar os valores de cada iteração
		for (Iterator<String> iterator = lista.iterator(); iterator.hasNext();) {

			this.fields.add((String) iterator.next());
		}
	}

}
