package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 04/12/2014 - 16:39:37
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
@Table(name = "parceiro_colaborador")
public class Colaborador extends Parceiro {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -2401992494994994607L;

	/** Atributo estruturaOrganizacional. */
	@JsonView({ Views.ColaboradorAutoCompleteView.class, Views.PessoaEditView.class})
	@ManyToOne(fetch = FetchType.LAZY)
	private EstruturaOrganizacional estruturaOrganizacional;

	/** Atributo matricula. */
	@JsonView({ Views.PessoaEditView.class })
	private String matricula;

	/** Atributo cargo. */
	@JsonView({ Views.PessoaEditView.class })
	private String cargo;

	/** Atributo funcao. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.PessoaEditView.class, Views.ColaboradorAutoCompleteView.class, Views.EstruturaOrganizacionalEditView.class })
	private Funcao funcao;

	/**
	 * Retorna o valor do atributo <code>estruturaOrganizacional</code>
	 *
	 * @return <code>EstruturaOrganizacional</code>
	 */
	public EstruturaOrganizacional getEstruturaOrganizacional() {

		return estruturaOrganizacional;
	}

	/**
	 * Define o valor do atributo <code>estruturaOrganizacional</code>.
	 *
	 * @param estruturaOrganizacional
	 */
	public void setEstruturaOrganizacional(EstruturaOrganizacional estruturaOrganizacional) {

		this.estruturaOrganizacional = estruturaOrganizacional;
	}

	/**
	 * Retorna o valor do atributo <code>matricula</code>
	 *
	 * @return <code>String</code>
	 */
	public String getMatricula() {

		return matricula;
	}

	/**
	 * Define o valor do atributo <code>matricula</code>.
	 *
	 * @param matricula
	 */
	public void setMatricula(String matricula) {

		this.matricula = matricula;
	}

	/**
	 * Retorna o valor do atributo <code>cargo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCargo() {

		return cargo;
	}

	/**
	 * Define o valor do atributo <code>cargo</code>.
	 *
	 * @param cargo
	 */
	public void setCargo(String cargo) {

		this.cargo = cargo;
	}

	/**
	 * Retorna o valor do atributo <code>funcao</code>
	 *
	 * @return <code>Funcao</code>
	 */
	public Funcao getFuncao() {

		return funcao;
	}

	/**
	 * Define o valor do atributo <code>funcao</code>.
	 *
	 * @param funcao
	 */
	public void setFuncao(Funcao funcao) {

		this.funcao = funcao;
	}

}
