package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
 * @since 27/11/2014 - 14:53:47
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class Cidade extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 5045154881530152556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo codigo. */
	@JsonView({ Views.CidadeListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	@Column(length = 30)
	private String codigo;

	/** Atributo codigoIBGE. */
	@Column(length = 30)
	@JsonView({ Views.CidadeListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class })
	private String codigoIBGE;

	/** Atributo nome. */
	@Column(length = 255)
	@JsonView({ Views.CidadeListView.class, Views.EnderecoListView.class, Views.BairroListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	private String nome;

	/** Atributo estado. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonView({ Views.CidadeListView.class, Views.EnderecoListView.class, Views.BairroListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	private Estado estado;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Cidade() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param codigo
	 * @param codigoIBGE
	 * @param nome
	 * @param estado
	 */
	public Cidade( String codigo, String codigoIBGE, String nome, Estado estado ) {

		this();
		this.codigo = codigo;
		this.codigoIBGE = codigoIBGE;
		this.nome = nome;
		this.estado = estado;
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
	 * Retorna o valor do atributo <code>estado</code>
	 * 
	 * @return <code>Estado</code>
	 */
	public Estado getEstado() {

		return estado;
	}

	/**
	 * Define o valor do atributo <code>estado</code>.
	 * 
	 * @param estado
	 */
	public void setEstado(Estado estado) {

		this.estado = estado;
	}

	/**
	 * Retorna o valor do atributo <code>codigoIBGE</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCodigoIBGE() {

		return codigoIBGE;
	}

	/**
	 * Define o valor do atributo <code>codigoIBGE</code>.
	 * 
	 * @param codigoIBGE
	 */
	public void setCodigoIBGE(String codigoIBGE) {

		this.codigoIBGE = codigoIBGE;
	}

}
