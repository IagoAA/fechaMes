package br.com.centralit.api.viewHelper;

import java.io.Serializable;
import java.util.List;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 	
 * @since 15/12/2014 - 19:43:40
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *	
 */
public class DominioParceirosNomeBuscaVH implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2342505270077662292L;

	/** Atributo value. */
	private String value;

	/** Atributo parceiros. */
	private List<String> parceiros;

	/** Atributo tipoDominio. */
	private String tipoDominio;

	/**
	 * Retorna o valor do atributo <code>value</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getValue() {

		return value;
	}

	/**
	 * Define o valor do atributo <code>value</code>.
	 * 
	 * @param value
	 */
	public void setValue(String value) {

		this.value = value;
	}

	/**
	 * Retorna o valor do atributo <code>parceiros</code>
	 * 
	 * @return <code>List<String></code>
	 */
	public List<String> getParceiros() {

		return parceiros;
	}

	/**
	 * Define o valor do atributo <code>parceiros</code>.
	 * 
	 * @param parceiros
	 */
	public void setParceiros(List<String> parceiros) {

		this.parceiros = parceiros;
	}

	/**
	 * Retorna o valor do atributo <code>tipoDominio</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getTipoDominio() {

		return tipoDominio;
	}

	/**
	 * Define o valor do atributo <code>tipoDominio</code>.
	 * 
	 * @param tipoDominio
	 */
	public void setTipoDominio(String tipoDominio) {

		this.tipoDominio = tipoDominio;
	}

}
