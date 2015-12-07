package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * @since 04/12/2014 - 17:55:25
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
@JsonIgnoreProperties({ "$edit", "$checked" })
public class FornecedorRamoAtividade extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 6469339894192989872L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo fornecedor. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Fornecedor fornecedor;

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
	 * Retorna o valor do atributo <code>fornecedor</code>
	 *
	 * @return <code>Fornecedor</code>
	 */
	public Fornecedor getFornecedor() {

		return fornecedor;
	}

	/**
	 * Define o valor do atributo <code>fornecedor</code>.
	 *
	 * @param fornecedor
	 */
	public void setFornecedor(Fornecedor fornecedor) {

		this.fornecedor = fornecedor;
	}

}
