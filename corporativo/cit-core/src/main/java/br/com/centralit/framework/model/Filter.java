package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;
import br.com.centralit.framework.util.ConstantsQuery;

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
 * <b>Title: </b> Classe Filter
 * </p>
 * 
 * <p>
 * <b>Description: </b> Classe de dominio Filter
 * </p>
 * 
 * @since 19/11/2014 - 09:17:49
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@Entity
@JsonIgnoreProperties({ "listaDominio" })
public class Filter extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -2159335566529914301L;

	/** Atributo fieldDataInativo. */
	public static final String FIELD_DATA_INATIVO = "dataInativo";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	protected Long id;

	/** Atributo type. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String type;

	/** Atributo field. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String field;

	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String valueMin;

	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String valueMax;

	/** Atributo value. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String value;

	/** Atributo dominio. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private Boolean dominio;

	/** Atributo comparison. */
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String comparison = ConstantsQuery.COMPARE_EQUALS;

	/** Atributo searchParams. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private SearchParams searchParams;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Filter() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param field
	 * @param type
	 * @param value
	 * @param comparison
	 */
	public Filter( final String field, final String type, final String value, final String comparison ) {

		super();
		this.field = field;
		this.type = type;
		this.value = value;
		this.comparison = comparison;
	}

	/**
	 * Retorna o valor do atributo <code>type</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getType() {

		return type;
	}

	/**
	 * Define o valor do atributo <code>type</code>.
	 * 
	 * @param type
	 */
	public void setType(String type) {

		this.type = type;
	}

	/**
	 * Retorna o valor do atributo <code>field</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getField() {

		return field;
	}

	/**
	 * Define o valor do atributo <code>field</code>.
	 * 
	 * @param field
	 */
	public void setField(String field) {

		this.field = field;
	}

	/**
	 * Retorna o valor do atributo <code>value</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getValue() {

		return value;
	}

	/**
	 * Define o valor do atributo <code>value</code>.
	 * 
	 * @param value
	 */
	public void setValue(String value) {

		this.value = value;
	}

	/**
	 * Retorna o valor do atributo <code>comparison</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getComparison() {

		return comparison;
	}

	/**
	 * Define o valor do atributo <code>comparison</code>.
	 * 
	 * @param comparison
	 */
	public void setComparison(String comparison) {

		this.comparison = comparison;
	}

	/**
	 * Retorna o valor do atributo <code>searchParams</code>
	 * 
	 * @return <code>SearchParams</code>
	 */
	public SearchParams getSearchParams() {

		return searchParams;
	}

	/**
	 * Define o valor do atributo <code>searchParams</code>.
	 * 
	 * @param searchParams
	 */
	public void setSearchParams(SearchParams searchParams) {

		this.searchParams = searchParams;
	}

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
	 * Retorna o valor do atributo <code>valueMin</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getValueMin() {

		return valueMin;
	}

	/**
	 * Define o valor do atributo <code>valueMin</code>.
	 * 
	 * @param valueMin
	 */
	public void setValueMin(String valueMin) {

		this.valueMin = valueMin;
	}

	/**
	 * Retorna o valor do atributo <code>valueMax</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getValueMax() {

		return valueMax;
	}

	/**
	 * Define o valor do atributo <code>valueMax</code>.
	 * 
	 * @param valueMax
	 */
	public void setValueMax(String valueMax) {

		this.valueMax = valueMax;
	}

	/**
	 * Retorna o valor do atributo <code>dominio</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getDominio() {

		return dominio;
	}

	/**
	 * Define o valor do atributo <code>dominio</code>.
	 * 
	 * @param dominio
	 */
	public void setDominio(Boolean dominio) {

		this.dominio = dominio;
	}

}
