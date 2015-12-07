package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

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
 * <b>Title: </b>PessoaJuridica
 * </p>
 * 
 * <p>
 * <b>Description: </b>Entidade<code>PessoaJuridica</code>
 * </p>
 * 
 * @since 27/11/2014 - 15:37:48
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class PessoaJuridica extends PersistentObject {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -1794494382346213239L;

	@Id
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo cnpj. */
	@JsonView({ Views.PessoaEditView.class,  Views.ContratoEditView.class, })
	private String cnpj;

	/** Atributo nomeFantasia. */
	@JsonView({ Views.PessoaEditView.class })
	private String nomeFantasia;

	/** Atributo razaoSocial. */
	@JsonView({ Views.PessoaEditView.class })
	private String razaoSocial;

	/** Atributo inscricaoEstadual. */
	@JsonView({ Views.PessoaEditView.class })
	private String inscricaoEstadual;

	/** Atributo inscricaoMunicipal. */
	@JsonView({ Views.PessoaEditView.class })
	private String inscricaoMunicipal;

	/** Atributo site. */
	@JsonView({ Views.PessoaEditView.class })
	private String site;

	/** Atributo porte. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dominio_porte")
	@JsonView({ Views.PessoaEditView.class })
	private Dominio dominioPorte;

	/** Atributo abrangencia. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dominio_abrangencia")
	@JsonView({ Views.PessoaEditView.class })
	private Dominio dominioAbrangencia;

	/** Atributo filial. */
	@JsonView({ Views.PessoaEditView.class })
	private Boolean filial;

	/** Atributo filantropico. */
	@JsonView({ Views.PessoaEditView.class })
	private Boolean filantropico;

	/** Atributo impostoSimples. */
	@JsonView({ Views.PessoaEditView.class })
	private Boolean impostoSimples;

	@MapsId
	@OneToOne(mappedBy = "pessoaJuridica")
	private Pessoa pessoa;

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
	 * Retorna o valor do atributo <code>nomeFantasia</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNomeFantasia() {

		return nomeFantasia;
	}

	/**
	 * Define o valor do atributo <code>nomeFantasia</code>.
	 * 
	 * @param nomeFantasia
	 */
	public void setNomeFantasia(String nomeFantasia) {

		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * Retorna o valor do atributo <code>razaoSocial</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getRazaoSocial() {

		return razaoSocial;
	}

	/**
	 * Define o valor do atributo <code>razaoSocial</code>.
	 * 
	 * @param razaoSocial
	 */
	public void setRazaoSocial(String razaoSocial) {

		this.razaoSocial = razaoSocial;
	}

	/**
	 * Retorna o valor do atributo <code>inscricaoEstadual</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getInscricaoEstadual() {

		return inscricaoEstadual;
	}

	/**
	 * Define o valor do atributo <code>inscricaoEstadual</code>.
	 * 
	 * @param inscricaoEstadual
	 */
	public void setInscricaoEstadual(String inscricaoEstadual) {

		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * Retorna o valor do atributo <code>inscricaoMunicipal</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getInscricaoMunicipal() {

		return inscricaoMunicipal;
	}

	/**
	 * Define o valor do atributo <code>inscricaoMunicipal</code>.
	 * 
	 * @param inscricaoMunicipal
	 */
	public void setInscricaoMunicipal(String inscricaoMunicipal) {

		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	/**
	 * Retorna o valor do atributo <code>site</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getSite() {

		return site;
	}

	/**
	 * Define o valor do atributo <code>site</code>.
	 * 
	 * @param site
	 */
	public void setSite(String site) {

		this.site = site;
	}

	/**
	 * Retorna o valor do atributo <code>dominioPorte</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioPorte() {

		return dominioPorte;
	}

	/**
	 * Define o valor do atributo <code>dominioPorte</code>.
	 * 
	 * @param dominioPorte
	 */
	public void setDominioPorte(Dominio dominioPorte) {

		this.dominioPorte = dominioPorte;
	}

	/**
	 * Retorna o valor do atributo <code>dominioAbrangencia</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioAbrangencia() {

		return dominioAbrangencia;
	}

	/**
	 * Define o valor do atributo <code>dominioAbrangencia</code>.
	 * 
	 * @param dominioAbrangencia
	 */
	public void setDominioAbrangencia(Dominio dominioAbrangencia) {

		this.dominioAbrangencia = dominioAbrangencia;
	}

	/**
	 * Retorna o valor do atributo <code>filial</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getFilial() {

		return filial;
	}

	/**
	 * Define o valor do atributo <code>filial</code>.
	 * 
	 * @param filial
	 */
	public void setFilial(Boolean filial) {

		this.filial = filial;
	}

	/**
	 * Retorna o valor do atributo <code>filantropico</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getFilantropico() {

		return filantropico;
	}

	/**
	 * Define o valor do atributo <code>filantropico</code>.
	 * 
	 * @param filantropico
	 */
	public void setFilantropico(Boolean filantropico) {

		this.filantropico = filantropico;
	}

	/**
	 * Retorna o valor do atributo <code>impostoSimples</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getImpostoSimples() {

		return impostoSimples;
	}

	/**
	 * Define o valor do atributo <code>impostoSimples</code>.
	 * 
	 * @param impostoSimples
	 */
	public void setImpostoSimples(Boolean impostoSimples) {

		this.impostoSimples = impostoSimples;
	}

	/**
	 * Retorna o valor do atributo <code>cnpj</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCnpj() {

		return cnpj;
	}

	/**
	 * Define o valor do atributo <code>cnpj</code>.
	 * 
	 * @param cnpj
	 */
	public void setCnpj(String cnpj) {

		this.cnpj = cnpj;
	}

	/**
	 * Retorna o valor do atributo <code>pessoa</code>
	 * 
	 * @return <code>Pessoa</code>
	 */
	public Pessoa getPessoa() {

		return pessoa;
	}

	/**
	 * Define o valor do atributo <code>pessoa</code>.
	 * 
	 * @param pessoa
	 */
	public void setPessoa(Pessoa pessoa) {

		this.pessoa = pessoa;
	}

}
