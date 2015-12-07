package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import br.com.centralit.api.framework.json.ViewsTabelasCorp;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @since 04/12/2014 - 16:36:41
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({ "pessoa.nome", "nome", "classeParceiro.dominioTipoParceiro.codigo", "classeParceiro.dominioTipoParceiro.descricao" })
public class Parceiro extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 7845220686556345972L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.FornecedorFindView.class,
			Views.ColaboradorContratoAutoCompleteView.class, Views.SeguradoraAutoCompleteView.class  })
	private Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.FornecedorFindView.class })
	private String codigo;

	/** Atributo classeParceiro. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonView({ Views.PessoaEditView.class, Views.SeguradoraAutoCompleteView.class })
	private ClasseParceiro classeParceiro;

	/** Atributo pessoa. */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonView({	Views.FornecedorFindView.class,
				Views.ColaboradorAutoCompleteView.class,
				Views.ComissaoListView.class,
				Views.EstruturaOrganizacionalEditView.class,
				Views.ParceiroAutoCompleteView.class,
				Views.ContratoListView.class,
				Views.ColaboradorContratoAutoCompleteView.class,
				Views.SeguradoraAutoCompleteView.class,
				Views.ContratoEditView.class,
				Views.FuncionarioAutoCompleteView.class,
				ViewsTabelasCorp.CentroResultadoResponsavelTodosResponsaveis.class,
				ViewsTabelasCorp.CentroResultadoDelegacaoTodasDelegacoes.class,
				ViewsTabelasCorp.CentroResultadoSubstituicao.class,
				ViewsTabelasCorp.SolicitacaoAlcadaView.class})
	private Pessoa pessoa;

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
	 * Retorna o valor do atributo <code>classeParceiro</code>
	 *
	 * @return <code>ClasseParceiro</code>
	 */
	public ClasseParceiro getClasseParceiro() {

		return classeParceiro;
	}

	/**
	 * Define o valor do atributo <code>classeParceiro</code>.
	 *
	 * @param classeParceiro
	 */
	public void setClasseParceiro(ClasseParceiro classeParceiro) {

		this.classeParceiro = classeParceiro;
	}

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

}
