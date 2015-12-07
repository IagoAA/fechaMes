package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
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
 * @since 27/11/2015 - 14:49:33
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
@Entity
public class Caixa extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -1325563861787101421L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.CaixaListView.class})
	private String codigo;

	/** Atributo nome. */
	@Column(length = 255)
	@JsonView({ Views.CaixaListView.class })
	private String nome;

	/** Atributo sigla. */
	@Column(length = 10)
	@JsonView({ Views.CaixaListView.class })
	private String sigla;

	/** Atributo luck. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonView({ Views.CaixaListView.class })
	private Luck luck;

	/** Atributo dominioTipoDocumentoCaixa. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.CaixaListView.class })
	private Dominio dominioTipoDocumentoCaixa;

	/** Atributo dominioTipoRecolhimentoCaixa. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.CaixaListView.class })
	private Dominio dominioTipoRecolhimentoCaixa;

	/** Atributo estruturaOrganizacionalDestino. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.CaixaListView.class })
	@JoinColumn(name="loja_id")
	private EstruturaOrganizacional loja;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Caixa() {

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
	public Caixa( String codigo, String nome, String sigla, Regiao regiao ) {

		this();
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
		this.setLuck(luck);
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
	 * @return the luck
	 */
	public Luck getLuck() {
		return luck;
	}

	/**
	 * @param luck the luck to set
	 */
	public void setLuck(Luck luck) {
		this.luck = luck;
	}

	public Dominio getDominioTipoDocumentoCaixa() {
		return dominioTipoDocumentoCaixa;
	}

	public void setDominioTipoDocumentoCaixa(Dominio dominioTipoDocumentoCaixa) {
		this.dominioTipoDocumentoCaixa = dominioTipoDocumentoCaixa;
	}

	public Dominio getDominioTipoRecolhimentoCaixa() {
		return dominioTipoRecolhimentoCaixa;
	}

	public void setDominioTipoRecolhimentoCaixa(Dominio dominioTipoRecolhimentoCaixa) {
		this.dominioTipoRecolhimentoCaixa = dominioTipoRecolhimentoCaixa;
	}

	public EstruturaOrganizacional getLoja() {
		return loja;
	}

	public void setLoja(EstruturaOrganizacional loja) {
		this.loja = loja;
	}

}
