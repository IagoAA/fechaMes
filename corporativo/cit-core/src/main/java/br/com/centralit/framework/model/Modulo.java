package br.com.centralit.framework.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;
import br.com.centralit.framework.util.UtilObjeto;

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
 * @since 24/02/2015 - 14:29:21
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
public class Modulo extends PersistentObjectAudit {

	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	@Column(nullable = false, length = 100, unique = true)
	@JsonView({ Views.GenericView.class, Views.InternacionalizacaoListView.class, Views.TelaJsonView.class, Views.DashBoardListView.class })
	private String nome;

	@Column(nullable = false, length = 100, unique = true)
	@JsonView({ Views.GenericView.class, Views.TelaJsonView.class })
	private String baseUrl;

	@Column(nullable = false, length = 100, unique = true)
	@JsonView({ Views.GenericView.class, Views.TelaJsonView.class })
	private String restAngular;

	@Column(nullable = true, length = 100, unique = true)
	@JsonView({ Views.GenericView.class, Views.TelaJsonView.class })
	private String caminho;

	@Column(nullable = false)
	@JsonView({ Views.GenericView.class })
	private Boolean habilitado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo", cascade = CascadeType.ALL)
	private List<Internacionalizacao> internacionalizacoes;

	public Modulo() {

		super();
	}

	public Modulo( String nome, String baseUrl, String restAngular, String caminho, Boolean habilitado) {

		super();
		this.nome = nome;
		this.baseUrl = baseUrl;
		this.restAngular = restAngular;
		this.caminho = caminho;
		this.habilitado = habilitado;
	}
	
	@PrePersist
	public void prePersist() {

		if (getHabilitado() == null) {
			setHabilitado(false);
		}
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
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
	 * Retorna o valor do atributo <code>baseUrl</code>
	 *
	 * @return <code>String</code>
	 */
	public String getBaseUrl() {

		if(UtilObjeto.isReferencia(baseUrl)){
			
			return baseUrl;
			
		}else{
			
			return "";
		}
	}

	/**
	 * Define o valor do atributo <code>baseUrl</code>.
	 *
	 * @param baseUrl
	 */
	public void setBaseUrl(String baseUrl) {

		this.baseUrl = baseUrl;
	}

	/**
	 * Retorna o valor do atributo <code>restAngular</code>
	 *
	 * @return <code>String</code>
	 */
	public String getRestAngular() {

		return restAngular;
	}

	/**
	 * Define o valor do atributo <code>restAngular</code>.
	 *
	 * @param restAngular
	 */
	public void setRestAngular(String restAngular) {

		this.restAngular = restAngular;
	}

	/**
	 * Retorna o valor do atributo <code>caminho</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCaminho() {

		return caminho;
	}

	/**
	 * Define o valor do atributo <code>caminho</code>.
	 *
	 * @param caminho
	 */
	public void setCaminho(String caminho) {

		this.caminho = caminho;
	}

	/**
	 * Retorna o valor do atributo <code>habilitado</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getHabilitado() {

		return habilitado;
	}

	/**
	 * Define o valor do atributo <code>habilitado</code>.
	 *
	 * @param habilitado
	 */
	public void setHabilitado(Boolean habilitado) {

		this.habilitado = habilitado;
	}

	/**
	 * Retorna o valor do atributo <code>internacionalizacoes</code>
	 *
	 * @return <code>List<Internacionalizacao></code>
	 */
	public List<Internacionalizacao> getInternacionalizacoes() {

		return internacionalizacoes;
	}

	/**
	 * Define o valor do atributo <code>internacionalizacoes</code>.
	 *
	 * @param internacionalizacoes
	 */
	public void setInternacionalizacoes(List<Internacionalizacao> internacionalizacoes) {

		this.internacionalizacoes = internacionalizacoes;
	}
}
