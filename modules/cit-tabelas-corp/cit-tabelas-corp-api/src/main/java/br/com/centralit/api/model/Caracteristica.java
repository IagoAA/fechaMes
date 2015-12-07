package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @since 05/12/2014 - 10:00:15
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
public class Caracteristica extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 4709277140243055947L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({Views.GenericView.class, Views.CaracteristicaListView.class})
	private String codigo;

	/** Atributo descricao. */
	@Column(length = 80)
	@JsonView({Views.GenericView.class, Views.CaracteristicaListView.class })
	private String descricao;

	/**
	 * Nenhum: Sem restrições. Por Material: A restrição é por material. Ex: Não pode se repetir a informação no mesmo material. Geral: A restrição é geral pro sistema. Ex: Uma placa não pode ter nenhuma igual no sistema
	 */
	/** Atributo dominioTipoRestricao. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonView({Views.GenericView.class, Views.CaracteristicaListView.class })
	private Dominio dominioTipoRestricao;

	/**
	 * Texto Numero Data TextArea TabelaDominio AutoComplete(Objetos de Neg�cio) Arquivo
	 */
	/** Atributo dominioTipoDado. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({Views.GenericView.class, Views.CaracteristicaListView.class })
	private Dominio dominioTipoDado;

	/** Atributo expressaoRegular. */
	@Column(length = 50)
	@JsonView({Views.GenericView.class, Views.CaracteristicaListView.class })
	private String expressaoRegular;

	/** Atributo tamanho. */
	@JsonView({Views.GenericView.class, Views.CaracteristicaListView.class })
	@Column(length = 5)
	private Long tamanho;

	/** Atributo chaveTipoDominio guarda o valor do atributo da tabela de dominio. */
	@Column(length = 50)
	@JsonView({Views.GenericView.class, Views.CaracteristicaListView.class })
	private String chaveDominio;

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
	 * Retorna o valor do atributo <code>descricao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Define o valor do atributo <code>descricao</code>.
	 *
	 * @param descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Retorna o valor do atributo <code>dominioTipoRestricao</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoRestricao() {

		return dominioTipoRestricao;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoRestricao</code>.
	 *
	 * @param dominioTipoRestricao
	 */
	public void setDominioTipoRestricao(Dominio dominioTipoRestricao) {

		this.dominioTipoRestricao = dominioTipoRestricao;
	}

	/**
	 * Retorna o valor do atributo <code>dominioTipoDado</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoDado() {

		return dominioTipoDado;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoDado</code>.
	 *
	 * @param dominioTipoDado
	 */
	public void setDominioTipoDado(Dominio dominioTipoDado) {

		this.dominioTipoDado = dominioTipoDado;
	}

	/**
	 * Retorna o valor do atributo <code>expressaoRegular</code>
	 *
	 * @return <code>String</code>
	 */
	public String getExpressaoRegular() {

		return expressaoRegular;
	}

	/**
	 * Define o valor do atributo <code>expressaoRegular</code>.
	 *
	 * @param expressaoRegular
	 */
	public void setExpressaoRegular(String expressaoRegular) {

		this.expressaoRegular = expressaoRegular;
	}

	/**
	 * Retorna o valor do atributo <code>tamanho</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getTamanho() {

		return tamanho;
	}

	/**
	 * Define o valor do atributo <code>tamanho</code>.
	 *
	 * @param tamanho
	 */
	public void setTamanho(Long tamanho) {

		this.tamanho = tamanho;
	}

	/**
	 * Retorna o valor do atributo <code>chaveDominio</code>
	 *
	 * @return <code>String</code>
	 */
	public String getChaveDominio() {

		return chaveDominio;
	}

	/**
	 * Define o valor do atributo <code>chaveDominio</code>.
	 *
	 * @param chaveDominio
	 */
	public void setChaveDominio(String chaveDominio) {

		this.chaveDominio = chaveDominio;
	}

}
