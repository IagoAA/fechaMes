package br.com.centralit.framework.exception;

import java.io.Serializable;

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
 * @since 27/11/2014 - 09:02:19
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
public class Erro implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2807230680451854394L;

	/** Atributo atributo. */
	private String atributo;

	/** Atributo label. */
	private String label;

	/** Atributo idCampoTela. */
	private String idCampoTela;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Erro() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param label
	 */
	public Erro( String label ) {

		super();
		this.label = label;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param atributo
	 * @param label
	 */
	public Erro( String atributo, String label ) {

		super();
		this.atributo = atributo;
		this.label = label;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param atributo
	 * @param label
	 * @param idCampoTela
	 */
	public Erro( String atributo, String label, String idCampoTela ) {

		super();
		this.atributo = atributo;
		this.label = label;
		this.idCampoTela = idCampoTela;
	}

	/**
	 * Retorna o valor do atributo <code>atributo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getAtributo() {

		return atributo;
	}

	/**
	 * Define o valor do atributo <code>atributo</code>.
	 *
	 * @param atributo
	 */
	public void setAtributo(String atributo) {

		this.atributo = atributo;
	}

	/**
	 * Retorna o valor do atributo <code>label</code>
	 *
	 * @return <code>String</code>
	 */
	public String getLabel() {

		return label;
	}

	/**
	 * Define o valor do atributo <code>label</code>.
	 *
	 * @param label
	 */
	public void setLabel(String label) {

		this.label = label;
	}

	/**
	 * Retorna o valor do atributo <code>idCampoTela</code>
	 *
	 * @return <code>String</code>
	 */
	public String getIdCampoTela() {

		return idCampoTela;
	}

	/**
	 * Define o valor do atributo <code>idCampoTela</code>.
	 *
	 * @param idCampoTela
	 */
	public void setIdCampoTela(String idCampoTela) {

		this.idCampoTela = idCampoTela;
	}

}
