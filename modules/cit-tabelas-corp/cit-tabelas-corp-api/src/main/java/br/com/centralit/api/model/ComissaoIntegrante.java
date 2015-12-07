package br.com.centralit.api.model;

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
 * <b>Title: </b> ComissaoIntegrante
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
 * @since 05/12/2014 - 17:09:16
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
public class ComissaoIntegrante extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 6435920185006473040L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo sequencia. */
	private Long sequencia;

	/** Atributo bemPatrimonial. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.ComissaoEditView.class })
	public Colaborador integrante;

	/** Atributo comissao. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Comissao comissao;

	/** Atributo comissaoInativo. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Comissao comissaoInativo;

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
	 * Retorna o valor do atributo <code>sequencia</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getSequencia() {

		return sequencia;
	}

	/**
	 * Define o valor do atributo <code>sequencia</code>.
	 *
	 * @param sequencia
	 */
	public void setSequencia(Long sequencia) {

		this.sequencia = sequencia;
	}

	/**
	 * Retorna o valor do atributo <code>integrante</code>
	 *
	 * @return <code>Colaborador</code>
	 */
	public Colaborador getIntegrante() {

		return integrante;
	}

	/**
	 * Define o valor do atributo <code>integrante</code>.
	 *
	 * @param integrante
	 */
	public void setIntegrante(Colaborador integrante) {

		this.integrante = integrante;
	}

	/**
	 * Retorna o valor do atributo <code>inventarioComissao</code>
	 *
	 * @return <code>InventarioComissao</code>
	 */
	public Comissao getInventarioComissao() {

		return comissao;
	}

	/**
	 * Define o valor do atributo <code>inventarioComissao</code>.
	 *
	 * @param inventarioComissao
	 */
	public void setInventarioComissao(Comissao inventarioComissao) {

		this.comissao = inventarioComissao;
	}

	/**
	 * Retorna o valor do atributo <code>comissao</code>
	 *
	 * @return <code>Comissao</code>
	 */
	public Comissao getComissao() {

		return comissao;
	}

	/**
	 * Define o valor do atributo <code>comissao</code>.
	 *
	 * @param comissao
	 */
	public void setComissao(Comissao comissao) {

		this.comissao = comissao;
	}

	/**
	 * Retorna o valor do atributo <code>comissaoInativo</code>
	 *
	 * @return <code>Comissao</code>
	 */
	public Comissao getComissaoInativo() {

		return comissaoInativo;
	}

	/**
	 * Define o valor do atributo <code>comissaoInativo</code>.
	 *
	 * @param comissaoInativo
	 */
	public void setComissaoInativo(Comissao comissaoInativo) {

		this.comissaoInativo = comissaoInativo;
	}

}
