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
 * @since 04/12/2014 - 15:56:16
 * 
 * @version 1.0.0
 * 
 * @author ally.barra
 * 
 */
@Entity
public class Bairro extends PersistentObjectAudit {

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.BairroListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class })
	private String codigo;

	/** Atributo nome. */
	@Column(length = 255)
	@JsonView({ Views.EnderecoListView.class, Views.BairroListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	private String nome;

	/** Atributo cidade. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonView({ Views.EnderecoListView.class, Views.BairroListView.class, Views.PaisListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class, Views.PessoaEditView.class })
	private Cidade cidade;

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 3294208352082046937L;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Bairro() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param codigo
	 * @param nome
	 * @param cidade
	 */
	public Bairro( String codigo, String nome, Cidade cidade ) {

		this();
		this.codigo = codigo;
		this.nome = nome;
		this.cidade = cidade;
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
	 * Retorna o valor do atributo <code>cidade</code>
	 * 
	 * @return <code>Cidade</code>
	 */
	public Cidade getCidade() {

		return cidade;
	}

	/**
	 * Define o valor do atributo <code>cidade</code>.
	 * 
	 * @param cidade
	 */
	public void setCidade(Cidade cidade) {

		this.cidade = cidade;
	}

}
