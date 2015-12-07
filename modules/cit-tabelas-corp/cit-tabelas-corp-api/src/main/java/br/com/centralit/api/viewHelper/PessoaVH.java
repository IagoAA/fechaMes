package br.com.centralit.api.viewHelper;

import java.io.Serializable;
import java.util.Collection;

import br.com.centralit.api.model.Cliente;
import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.model.Fornecedor;
import br.com.centralit.api.model.Funcionario;
import br.com.centralit.api.model.OrgaoExterno;
import br.com.centralit.api.model.Pessoa;
import br.com.centralit.api.model.Portador;
import br.com.centralit.api.model.Seguradora;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;

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
 * PessoaVH
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
 * @since 05/01/2015 - 13:50:03
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
public class PessoaVH implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 3317566795980257738L;

	/** Atributo pessoa. */
	@JsonView({ Views.PessoaEditView.class })
	private Pessoa pessoa;

	/** Atributo colaborador. */
	@JsonView({ Views.PessoaEditView.class })
	private Colaborador colaborador;

	/** Atributo orgaoExterno. */
	@JsonView({ Views.PessoaEditView.class })
	private OrgaoExterno orgaoExterno;

	/** Atributo portador. */
	@JsonView({ Views.PessoaEditView.class })
	private Portador portador;

	/** Atributo fornecedor. */
	@JsonView({ Views.PessoaEditView.class })
	private Fornecedor fornecedor;

	/** Atributo seguradora. */
	@JsonView({ Views.PessoaEditView.class })
	private Seguradora seguradora;

	/** Atributo cliente. */
	@JsonView({ Views.PessoaEditView.class })
	private Cliente cliente;

	/** Atributo seguradora. */
	@JsonView({ Views.PessoaEditView.class })
	private Funcionario funcionario;

	/** Atributo dominiosTipoParceiroSelecionados. */
	@JsonView({ Views.PessoaEditView.class })
	private Collection<Dominio> dominiosTipoParceiroSelecionados;

	/**
	 * Retorna o valor do atributo <code>pessoa</code>
	 *
	 * @return <code>Pessoa</code>
	 */
	public Pessoa getPessoa() {

		return pessoa;
	}

	/**
	 * Define o valor do atributo <code>pessoa</code>.
	 *
	 * @param pessoa
	 */
	public void setPessoa(Pessoa pessoa) {

		this.pessoa = pessoa;
	}

	/**
	 * Retorna o valor do atributo <code>colaborador</code>
	 *
	 * @return <code>Colaborador</code>
	 */
	public Colaborador getColaborador() {

		return colaborador;
	}

	/**
	 * Define o valor do atributo <code>colaborador</code>.
	 *
	 * @param colaborador
	 */
	public void setColaborador(Colaborador colaborador) {

		this.colaborador = colaborador;
	}

	/**
	 * Retorna o valor do atributo <code>orgaoExterno</code>
	 *
	 * @return <code>OrgaoExterno</code>
	 */
	public OrgaoExterno getOrgaoExterno() {

		return orgaoExterno;
	}

	/**
	 * Define o valor do atributo <code>orgaoExterno</code>.
	 *
	 * @param orgaoExterno
	 */
	public void setOrgaoExterno(OrgaoExterno orgaoExterno) {

		this.orgaoExterno = orgaoExterno;
	}

	/**
	 * Retorna o valor do atributo <code>portador</code>
	 *
	 * @return <code>Portador</code>
	 */
	public Portador getPortador() {

		return portador;
	}

	/**
	 * Define o valor do atributo <code>portador</code>.
	 *
	 * @param portador
	 */
	public void setPortador(Portador portador) {

		this.portador = portador;
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

	/**
	 * Retorna o valor do atributo <code>dominiosTipoParceiroSelecionados</code>
	 *
	 * @return <code>Collection<Dominio></code>
	 */
	public Collection<Dominio> getDominiosTipoParceiroSelecionados() {

		return dominiosTipoParceiroSelecionados;
	}

	/**
	 * Define o valor do atributo <code>dominiosTipoParceiroSelecionados</code>.
	 *
	 * @param dominiosTipoParceiroSelecionados
	 */
	public void setDominiosTipoParceiroSelecionados(Collection<Dominio> dominiosTipoParceiroSelecionados) {

		this.dominiosTipoParceiroSelecionados = dominiosTipoParceiroSelecionados;
	}


	public Seguradora getSeguradora() {

		return seguradora;
	}


	public void setSeguradora(Seguradora seguradora) {

		this.seguradora = seguradora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


}
