package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
 * @since 04/12/2014 - 17:46:14
 * 
 * @version 1.0.0
 * 
 * @author ally.barra
 * 
 */
@Entity
@Table(name = "fornecedor_observacao")
@JsonIgnoreProperties({ "$edit", "$checked" })
public class FornecedorObservacao extends Observacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 7086299803927179258L;

	/** Atributo fornecedor. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Fornecedor fornecedor;

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
