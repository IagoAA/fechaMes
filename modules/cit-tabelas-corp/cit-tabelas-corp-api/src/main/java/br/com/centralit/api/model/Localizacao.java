package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

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
 * @since 04/12/2014 - 16:00:55
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
public class Localizacao extends PersistentObjectAuditOrganizacao {

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.LocalizacaoListView.class })
	private String codigo;

	/** Atributo nome. */
	@Column(length = 100)
	@JsonView({ Views.LocalizacaoListView.class, Views.LocalizacaoAutoCompleteSimplesView.class, Views.EstruturaOrganizacionalEditView.class })
	private String nome;

	/** Atributo endereco. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.LocalizacaoListView.class })
	public Endereco endereco;

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -5041306097447426859L;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Localizacao() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param nome
	 * @param endereco
	 * @param organizacao
	 */
	public Localizacao( String nome, Endereco endereco, Organizacao organizacao ) {

		this();
		this.nome = nome;
		this.endereco = endereco;
		this.organizacao = organizacao;
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
	 * Retorna o valor do atributo <code>endereco</code>
	 *
	 * @return <code>Endereco</code>
	 */
	public Endereco getEndereco() {

		return endereco;
	}

	/**
	 * Define o valor do atributo <code>endereco</code>.
	 *
	 * @param endereco
	 */
	public void setEndereco(Endereco endereco) {

		this.endereco = endereco;
	}

}
