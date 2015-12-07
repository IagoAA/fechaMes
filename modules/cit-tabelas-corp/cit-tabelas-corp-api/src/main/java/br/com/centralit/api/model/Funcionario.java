package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.centralit.framework.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

/**
 *
 * @since 04/12/2014 - 16:39:37
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */

@Entity
@Table(name="parceiro_funcionario")
public class Funcionario extends Parceiro{

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 5444364769310678027L;

	/** Atributo terceirizado. */
	@JsonView({ Views.PessoaEditView.class })
	private Boolean terceirizado;


	/**
	 * Retorna o valor do atributo <code>terceirizado</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getTerceirizado() {

		return terceirizado;
	}


	/**
	 * Define o valor do atributo <code>terceirizado</code>.
	 *
	 * @param terceirizado
	 */
	public void setTerceirizado(Boolean terceirizado) {

		this.terceirizado = terceirizado;
	}

}
