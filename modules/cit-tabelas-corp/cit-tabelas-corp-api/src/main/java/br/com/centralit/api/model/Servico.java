package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @since 10/11/2015 - 09:51:15
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */

@Entity
public class Servico extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -8798203126933021163L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.ServicoListView.class, Views.ServicoAutoCompleteView.class, Views.PessoaEditView.class })
	private String codigo;

	/** Atributo nome. */
	@Column(length = 250)
	@JsonView({ Views.ServicoListView.class, Views.ServicoAutoCompleteView.class, Views.PessoaEditView.class })
	private String nome;

	/** Atributo sigla. */
	@Column(length = 5)
	@JsonView({ Views.ServicoListView.class, Views.ServicoAutoCompleteView.class, Views.PessoaEditView.class })
	private String sigla;

	/*@ManyToOne(fetch = FetchType.EAGER)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	private Dominio dominioTipoServico;*/

	public Servico() {
		super();
	}

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
	 * @param nome
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>codigo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigo() {

		return codigo;
	}

	/**
	 * Define o valor do atributo <code>codigo</code>.
	 *
	 * @param codigo
	 */
	public void setCodigo(String codigo) {

		this.codigo = codigo;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 *
	 * @param nome
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

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
