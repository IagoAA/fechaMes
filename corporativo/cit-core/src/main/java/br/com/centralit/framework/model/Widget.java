package br.com.centralit.framework.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * @since 12/03/2015 - 10:57:01
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
public class Widget extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({ Views.WidgetListView.class, Views.WidgetEditView.class, Views.LookupView.class })
	private String nome;

	/** Atributo descricao. */
	@JsonView({ Views.WidgetListView.class, Views.WidgetEditView.class, Views.LookupView.class })
	private String descricao;

	/** Atributo tipoComponente. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.WidgetListView.class, Views.WidgetEditView.class, Views.LookupView.class })
	private Dominio tipoComponente;

	@JsonView({ Views.WidgetEditView.class, Views.PainelEditView.class, Views.LookupView.class })
	private String urlServico;

	/** Atributo apresentarUrlServico. */
	@JsonView({ Views.WidgetEditView.class, Views.PainelEditView.class })
	private Boolean apresentarUrlServico;

	/** Atributo parametros. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "widget", cascade = CascadeType.ALL)
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	private List<WidgetParametro> parametros;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Widget( String nome, String desricao, Dominio tipoComponente, Boolean apresentarUrlServico ) {

		this.nome = nome;
		this.descricao = desricao;
		this.tipoComponente = tipoComponente;
		this.apresentarUrlServico = apresentarUrlServico;

	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Widget() {

		super();
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	@Override
	public Long getId() {

		return id;
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
	 * Retorna o valor do atributo <code>tipoComponente</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getTipoComponente() {

		return tipoComponente;
	}

	/**
	 * Define o valor do atributo <code>tipoComponente</code>.
	 * 
	 * @param tipoComponente
	 */
	public void setTipoComponente(Dominio tipoComponente) {

		this.tipoComponente = tipoComponente;
	}

	/**
	 * Retorna o valor do atributo <code>parametros</code>
	 * 
	 * @return <code>List<WidgetParametro></code>
	 */
	public List<WidgetParametro> getParametros() {

		return parametros;
	}

	/**
	 * Define o valor do atributo <code>parametros</code>.
	 * 
	 * @param parametros
	 */
	public void setParametros(List<WidgetParametro> parametros) {

		this.parametros = parametros;
	}

	/**
	 * Retorna o valor do atributo <code>apresentarUrlServico</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getApresentarUrlServico() {

		return apresentarUrlServico;
	}

	/**
	 * Define o valor do atributo <code>apresentarUrlServico</code>.
	 * 
	 * @param apresentarUrlServico
	 */
	public void setApresentarUrlServico(Boolean apresentarUrlServico) {

		this.apresentarUrlServico = apresentarUrlServico;
	}

	/**
	 * Retorna o valor do atributo <code>urlServico</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getUrlServico() {

		if (this.urlServico == null && this.parametros != null) {
			for (WidgetParametro parametro : this.parametros) {
				if (parametro.getTipoWidgetParametroDominio().getCodigo() == Dominio.TIPO_DADO_URL_SERVICO_CODIGO) {
					this.urlServico = parametro.getTextoDefault();
				}
			}
		}
		return urlServico;
	}

	/**
	 * Define o valor do atributo <code>urlServico</code>.
	 * 
	 * @param urlServico
	 */
	public void setUrlServico(String urlServico) {

		this.urlServico = urlServico;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 * 
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
	}

}
