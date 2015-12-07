package br.com.centralit.api.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * @since 27/11/2014 - 14:49:33
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class Estado extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -1325563861787101421L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.EstadoListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class })
	private String codigo;

	/** Atributo nome. */
	@Column(length = 255)
	@JsonView({ Views.CidadeListView.class, Views.EnderecoListView.class, Views.EstadoListView.class, Views.BairroListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	private String nome;

	/** Atributo sigla. */
	@Column(length = 10)
	@JsonView({ Views.EstadoListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class })
	private String sigla;

	/** Atributo regiaoPais. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonView({ Views.CidadeListView.class, Views.EnderecoListView.class, Views.BairroListView.class, Views.EstadoListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	private Regiao regiao;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.hibernate.type.StringClobType")
	@JsonView({ Views.GenericView.class })
	private String description;

	/** Atributo listaCidade. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Cidade> listaCidade;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Estado() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param codigo
	 * @param nome
	 * @param sigla
	 * @param regiao
	 */
	public Estado( String codigo, String nome, String sigla, Regiao regiao ) {

		this();
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
		this.regiao = regiao;
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
	 * Retorna o valor do atributo <code>codigo</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCodigo() {

		return codigo;
	}

	/**
	 * Define o valor do atributo <code>codigo</code>.
	 * 
	 * @param codigo
	 */
	public void setCodigo(String codigo) {

		this.codigo = codigo;
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
	 * Retorna o valor do atributo <code>sigla</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getSigla() {

		return sigla;
	}

	/**
	 * Define o valor do atributo <code>sigla</code>.
	 * 
	 * @param sigla
	 */
	public void setSigla(String sigla) {

		this.sigla = sigla;
	}

	/**
	 * Retorna o valor do atributo <code>regiao</code>
	 * 
	 * @return <code>Regiao</code>
	 */
	public Regiao getRegiao() {

		return regiao;
	}

	/**
	 * Define o valor do atributo <code>regiao</code>.
	 * 
	 * @param regiao
	 */
	public void setRegiao(Regiao regiao) {

		this.regiao = regiao;
	}

	/**
	 * Retorna o valor do atributo <code>listaCidade</code>
	 * 
	 * @return <code>Collection<Cidade></code>
	 */
	public Collection<Cidade> getListaCidade() {

		return listaCidade;
	}

	/**
	 * Define o valor do atributo <code>listaCidade</code>.
	 * 
	 * @param listaCidade
	 */
	public void setListaCidade(Collection<Cidade> listaCidade) {

		this.listaCidade = listaCidade;
	}

	@Type(type = "text")
	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

}
