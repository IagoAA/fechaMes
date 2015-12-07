package br.com.centralit.api.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
 * <b>Title: </b>RegiaoPais
 * </p>
 * 
 * <p>
 * <b>Description: Entidade<code>RegiaoPais</code></b>
 * </p>
 * 
 * @since 27/11/2014 - 14:51:28
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class Regiao extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -8908630515872093092L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.RegiaoListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class })
	private String codigo;

	/** Atributo nome. */
	@Column(length = 100)
	@JsonView({ Views.CidadeListView.class, Views.EnderecoListView.class, Views.RegiaoListView.class, Views.BairroListView.class, Views.EstadoListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	private String nome;

	/** Atributo listaEstado. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "regiao", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Estado> listaEstado;

	/** Atributo pais. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.CidadeListView.class, Views.EnderecoListView.class, Views.BairroListView.class, Views.EstadoListView.class, Views.RegiaoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class })
	private Pais pais;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Regiao() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param codigo
	 * @param nome
	 * @param pais
	 */
	public Regiao( String codigo, String nome, Pais pais ) {

		this();

		this.codigo = codigo;
		this.nome = nome;
		this.pais = pais;
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
	 * Retorna o valor do atributo <code>pais</code>
	 * 
	 * @return <code>Pais</code>
	 */
	public Pais getPais() {

		return pais;
	}

	/**
	 * Define o valor do atributo <code>pais</code>.
	 * 
	 * @param pais
	 */
	public void setPais(Pais pais) {

		this.pais = pais;
	}

	/**
	 * Retorna o valor do atributo <code>listaEstado</code>
	 * 
	 * @return <code>Collection<Estado></code>
	 */
	public Collection<Estado> getListaEstado() {

		return listaEstado;
	}

	/**
	 * Define o valor do atributo <code>listaEstado</code>.
	 * 
	 * @param listaEstado
	 */
	public void setListaEstado(Collection<Estado> listaEstado) {

		this.listaEstado = listaEstado;
	}

}
