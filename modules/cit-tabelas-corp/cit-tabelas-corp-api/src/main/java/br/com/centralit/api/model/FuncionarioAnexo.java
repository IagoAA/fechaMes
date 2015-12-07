package br.com.centralit.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonView;

/**
*
* @since 17/11/2015 - 09:55:57
*
* @version 1.0.0
*
* @author iago
*
*/
@Entity
public class FuncionarioAnexo extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -3972425393785600397L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo descricao. */
	@Column(length = 200)
	@JsonView({ Views.PessoaEditView.class })
	private String descricao;

	/** Atributo anexo. */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] anexo;

	/** Atributo tamanho. */
	@JsonView({ Views.PessoaEditView.class })
	private Long tamanho;

	/** Atributo contrato. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Funcionario funcionario;

	/** Atributo funcionarioInativo tem a função de guardar o id do contrato para um anexo que está inativo, pois limparemos
	 * o atributo funcionario para que o carregamento automatico dos anexos so traga os anexos que não estão inativos */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Funcionario funcionarioInativo;

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
	 * Retorna o valor do atributo <code>anexo</code>
	 *
	 * @return <code>byte[]</code>
	 */
	public byte[] getAnexo() {

		return anexo;
	}


	/**
	 * Define o valor do atributo <code>anexo</code>.
	 *
	 * @param anexo
	 */
	public void setAnexo(byte[] anexo) {

		this.anexo = anexo;
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
	 * Retorna o valor do atributo <code>funcionario</code>
	 *
	 * @return <code>Funcionario</code>
	 */
	public Funcionario getFuncionario() {

		return funcionario;
	}


	/**
	 * Define o valor do atributo <code>funcionario</code>.
	 *
	 * @param funcionario
	 */
	public void setFuncionario(Funcionario funcionario) {

		this.funcionario = funcionario;
	}


	/**
	 * Retorna o valor do atributo <code>funcionarioInativo</code>
	 *
	 * @return <code>Funcionario</code>
	 */
	public Funcionario getFuncionarioInativo() {

		return funcionarioInativo;
	}


	/**
	 * Define o valor do atributo <code>funcionarioInativo</code>.
	 *
	 * @param funcionarioInativo
	 */
	public void setFuncionarioInativo(Funcionario funcionarioInativo) {

		this.funcionarioInativo = funcionarioInativo;
	}

}
