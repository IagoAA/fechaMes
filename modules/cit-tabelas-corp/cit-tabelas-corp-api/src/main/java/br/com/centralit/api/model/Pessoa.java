package br.com.centralit.api.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.centralit.api.framework.json.ViewsTabelasCorp;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

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
 * <b>Title: </b>Pessoa
 * </p>
 *
 * <p>
 * <b>Description: </b>Entidade <code>Pessoa</code>
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
 * @since 27/11/2014 - 10:32:19
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Entity
@JsonIgnoreProperties({ "$checked"})
public class Pessoa extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 4078894032949600689L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.FornecedorFindView.class, Views.ContratoListView.class, Views.SeguradoraAutoCompleteView.class })
	protected Long id;

	/** Atributo nome. */
	// TODO acrescentar view do estrutura organizacional, substituir Views.LookupView.class pelo do estrutura organizacional
	@JsonView({	Views.FornecedorFindView.class,
				Views.LookupView.class,
				Views.PessoaListViewListView.class,
				Views.ColaboradorAutoCompleteView.class,
				Views.ComissaoListView.class,
				Views.EstruturaOrganizacionalEditView.class,
				Views.ParceiroAutoCompleteView.class,
				Views.ContratoListView.class,
				Views.ColaboradorContratoAutoCompleteView.class,
				Views.SeguradoraAutoCompleteView.class,
				Views.ContratoEditView.class,
				Views.GenericView.class,
				Views.FuncionarioAutoCompleteView.class,
				ViewsTabelasCorp.CentroResultadoResponsavelTodosResponsaveis.class,
				ViewsTabelasCorp.CentroResultadoDelegacaoTodasDelegacoes.class,
				ViewsTabelasCorp.CentroResultadoSubstituicao.class })
	private String nome;

	/** Atributo email. */
	@JsonView({ Views.PessoaListViewListView.class })
	private String email;

	/** Atributo dominioPessoa. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dominio_pessoa")
	@JsonView({ Views.PessoaListViewListView.class })
	private Dominio dominioPessoa;

	/** Atributo pessoaFisica. */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonView({ Views.PessoaEditView.class, Views.ContratoEditView.class })
	@PrimaryKeyJoinColumn
	private PessoaFisica pessoaFisica;

	/** Atributo pessoaJuridica. */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonView({ Views.PessoaEditView.class,  Views.ContratoEditView.class })
	@PrimaryKeyJoinColumn
	private PessoaJuridica pessoaJuridica;

	/** Atributo listaTelefone. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
	@JsonView({ Views.PessoaEditView.class })
	private Collection<Telefone> telefones;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
	@JsonView({ Views.PessoaEditView.class })
	private Collection<Parceiro> parceiros;

	/** Atributo listaEndereco. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
	@JsonView({ Views.PessoaEditView.class })
	private Collection<Endereco> enderecos;

	/** Atributo listaContato. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
	@JsonView({ Views.PessoaEditView.class })
	private Collection<Contato> contatos;

	/** Atributo usuario. */
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.PessoaEditView.class})
	private Usuario usuario;

	/** Atributo codigo. */
	@Column(length = 30)
	private String codigo;

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
	 * Retorna o valor do atributo <code>email</code>
	 *
	 * @return <code>String</code>
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * Define o valor do atributo <code>email</code>.
	 *
	 * @param email
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	/**
	 * Define o valor do atributo <code>tipoPessoa</code>.
	 *
	 * @param tipoPessoa
	 */
	public void setTipoPessoa(Dominio tipoPessoa) {

	}

	/**
	 * Retorna o valor do atributo <code>pessoaFisica</code>
	 *
	 * @return <code>PessoaFisica</code>
	 */
	public PessoaFisica getPessoaFisica() {

		return pessoaFisica;
	}

	/**
	 * Define o valor do atributo <code>pessoaFisica</code>.
	 *
	 * @param pessoaFisica
	 */
	public void setPessoaFisica(PessoaFisica pessoaFisica) {

		this.pessoaFisica = pessoaFisica;
	}

	/**
	 * Retorna o valor do atributo <code>pessoaJuridica</code>
	 *
	 * @return <code>PessoaJuridica</code>
	 */
	public PessoaJuridica getPessoaJuridica() {

		return pessoaJuridica;
	}

	/**
	 * Define o valor do atributo <code>pessoaJuridica</code>.
	 *
	 * @param pessoaJuridica
	 */
	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {

		this.pessoaJuridica = pessoaJuridica;
	}

	/**
	 * Retorna o valor do atributo <code>listaParceiro</code>
	 *
	 * @return <code>Collection<Parceiro></code>
	 */
	public Collection<Parceiro> getParceiros() {

		return this.parceiros;
	}

	/**
	 * Define o valor do atributo <code>listaParceiro</code>.
	 *
	 * @param listaParceiro
	 */
	public void setParceiros(final Collection<Parceiro> listaParceiro) {

		this.parceiros = listaParceiro;
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
	 * Retorna o valor do atributo <code>listaTelefone</code>
	 *
	 * @return <code>Collection<Telefone></code>
	 */
	public Collection<Telefone> getTelefones() {

		return telefones;
	}

	/**
	 * Define o valor do atributo <code>listaTelefone</code>.
	 *
	 * @param listaTelefone
	 */
	public void setTelefones(Collection<Telefone> listaTelefone) {

		this.telefones = listaTelefone;
	}

	/**
	 * Retorna o valor do atributo <code>contatos</code>
	 *
	 * @return <code>Collection<Contato></code>
	 */
	public Collection<Contato> getContatos() {

		return contatos;
	}

	/**
	 * Define o valor do atributo <code>contatos</code>.
	 *
	 * @param contatos
	 */
	public void setContatos(Collection<Contato> contatos) {

		this.contatos = contatos;
	}

	/**
	 * Retorna o valor do atributo <code>dominioPessoa</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioPessoa() {

		return dominioPessoa;
	}

	/**
	 * Define o valor do atributo <code>dominioPessoa</code>.
	 *
	 * @param dominioPessoa
	 */
	public void setDominioPessoa(Dominio dominioPessoa) {

		this.dominioPessoa = dominioPessoa;
	}

	/**
	 * Retorna o valor do atributo <code>enderecos</code>
	 *
	 * @return <code>Collection<Endereco></code>
	 */
	public Collection<Endereco> getEnderecos() {

		return enderecos;
	}

	/**
	 * Define o valor do atributo <code>enderecos</code>.
	 *
	 * @param enderecos
	 */
	public void setEnderecos(Collection<Endereco> enderecos) {

		this.enderecos = enderecos;
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
	 * Retorna o valor do atributo <code>usuario</code>
	 *
	 * @return <code>Usuario</code>
	 */
	public Usuario getUsuario() {

		return usuario;
	}


	/**
	 * Define o valor do atributo <code>usuario</code>.
	 *
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}



}
