package br.com.centralit.api.model;

import javax.persistence.Entity;

import br.com.centralit.framework.json.Views;

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
 * @since 28/11/2014 - 10:32:47
 * 
 * @version 1.0.0
 * 
 * @author thiago.borges
 * 
 */
@Entity
public class OrgaoExterno extends Parceiro {

	private static final long serialVersionUID = -5503087876248538699L;

	/** Atributo sigla. */
	@JsonView({ Views.PessoaEditView.class })
	private String sigla;

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

}
