package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * @since 07/01/2015 - 16:09:00
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
@JsonIgnoreProperties({ "$checked" , "$edit"})
public class Contato extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 8655945103983802213L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({ Views.PessoaEditView.class })
	private String nome;

	/** Atributo cargo. */
	@JsonView({ Views.PessoaEditView.class })
	private String cargo;

	/** Atributo email. */
	@JsonView({ Views.PessoaEditView.class })
	private String email;	

	/** Atributo cpf. */
	@JsonView({ Views.PessoaEditView.class })
	private String cpf;	
	
	/** Atributo ordem. */
	@JsonView({ Views.PessoaEditView.class })
	private Long ordem;

	/** Atributo representanteLegal. */
	@JsonView({ Views.PessoaEditView.class })
	private Boolean representanteLegal;

	/** Atributo telefone. */
	@JsonView({ Views.PessoaEditView.class })
	private String telefone;

	/** Atributo pessoa. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Pessoa pessoa;

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
	 * Retorna o valor do atributo <code>cargo</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCargo() {

		return cargo;
	}

	/**
	 * Define o valor do atributo <code>cargo</code>.
	 * 
	 * @param cargo
	 */
	public void setCargo(String cargo) {

		this.cargo = cargo;
	}

	/**
	 * Retorna o valor do atributo <code>ordem</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getOrdem() {

		return ordem;
	}

	/**
	 * Define o valor do atributo <code>ordem</code>.
	 * 
	 * @param ordem
	 */
	public void setOrdem(Long ordem) {

		this.ordem = ordem;
	}

	/**
	 * Retorna o valor do atributo <code>representanteLegal</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getRepresentanteLegal() {

		return representanteLegal;
	}

	/**
	 * Define o valor do atributo <code>representanteLegal</code>.
	 * 
	 * @param representanteLegal
	 */
	public void setRepresentanteLegal(Boolean representanteLegal) {

		this.representanteLegal = representanteLegal;
	}

	/**
	 * Retorna o valor do atributo <code>telefone</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getTelefone() {

		return telefone;
	}

	/**
	 * Define o valor do atributo <code>telefone</code>.
	 * 
	 * @param telefone
	 */
	public void setTelefone(String telefone) {

		this.telefone = telefone;
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

	
	public String getEmail() {
	
		return email;
	}

	
	public void setEmail(String email) {
	
		this.email = email;
	}

	
	public String getCpf() {
	
		return cpf;
	}

	
	public void setCpf(String cpf) {
	
		this.cpf = cpf;
	}

	
	
}
