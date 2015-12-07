package br.com.centralit.framework.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
 * @since 12/03/2015 - 10:57:10
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
@JsonIgnoreProperties({ "$index" })
public class WidgetParametro extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class })
	private String nome;

	/** Atributo atributoFiltro. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class})
	private String atributoFiltro;
	
	/** Atributo tipoWidgetParametro. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	@ManyToOne(fetch = FetchType.LAZY)
	private Dominio tipoWidgetParametroDominio;

	/** Atributo chaveDominioDefault guarda o valor do atributo da tabela de dominio. */
	@Column(length = 50)
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class })
	private String chaveDominioDefault;

	@ManyToOne
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class })
	private Dominio dominioDefault;

	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class })
	private Long numeroDefault;

	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	private String textoDefault;

	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class })
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDefault;

	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	private Boolean booleanDefault;

	/** Atributo widget. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Widget widget;

	/** Atributo widget. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Widget widgetInativo;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param nome
	 * @param tipoWidgetParametroDominio
	 * @param chaveDominioDefault
	 * @param dominioDefault
	 * @param numeroDefault
	 * @param textoDefault
	 * @param dataDefault
	 * @param booleanDefault
	 * @param widget
	 */
	public WidgetParametro( String nome, Dominio tipoWidgetParametroDominio, String chaveDominioDefault, Dominio dominioDefault, Long numeroDefault, String textoDefault, Calendar dataDefault, Boolean booleanDefault, Widget widget ) {

		this.nome = nome;
		this.tipoWidgetParametroDominio = tipoWidgetParametroDominio;
		this.chaveDominioDefault = chaveDominioDefault;
		this.dominioDefault = dominioDefault;
		this.numeroDefault = numeroDefault;
		this.textoDefault = textoDefault;
		this.dataDefault = dataDefault;
		this.booleanDefault = booleanDefault;
		this.widget = widget;

	}
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param nome
	 * @param atributoFiltro
	 * @param tipoWidgetParametroDominio
	 * @param chaveDominioDefault
	 * @param dominioDefault
	 * @param numeroDefault
	 * @param textoDefault
	 * @param dataDefault
	 * @param booleanDefault
	 * @param widget
	 */
	public WidgetParametro( String nome, String atributoFiltro, Dominio tipoWidgetParametroDominio, String chaveDominioDefault, Dominio dominioDefault, Long numeroDefault, String textoDefault, Calendar dataDefault, Boolean booleanDefault, Widget widget ) {

		this.nome = nome;
		this.tipoWidgetParametroDominio = tipoWidgetParametroDominio;
		this.chaveDominioDefault = chaveDominioDefault;
		this.dominioDefault = dominioDefault;
		this.numeroDefault = numeroDefault;
		this.textoDefault = textoDefault;
		this.dataDefault = dataDefault;
		this.booleanDefault = booleanDefault;
		this.widget = widget;
		this.atributoFiltro = atributoFiltro;
		
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public WidgetParametro() {

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
	 * Retorna o valor do atributo <code>tipoWidgetParametroDominio</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getTipoWidgetParametroDominio() {

		return tipoWidgetParametroDominio;
	}

	/**
	 * Define o valor do atributo <code>tipoWidgetParametroDominio</code>.
	 * 
	 * @param tipoWidgetParametroDominio
	 */
	public void setTipoWidgetParametroDominio(Dominio tipoWidgetParametroDominio) {

		this.tipoWidgetParametroDominio = tipoWidgetParametroDominio;
	}

	/**
	 * Retorna o valor do atributo <code>widget</code>
	 * 
	 * @return <code>Widget</code>
	 */
	public Widget getWidget() {

		return widget;
	}

	/**
	 * Define o valor do atributo <code>widget</code>.
	 * 
	 * @param widget
	 */
	public void setWidget(Widget widget) {

		this.widget = widget;
	}

	/**
	 * Retorna o valor do atributo <code>widgetInativo</code>
	 * 
	 * @return <code>Widget</code>
	 */
	public Widget getWidgetInativo() {

		return widgetInativo;
	}

	/**
	 * Define o valor do atributo <code>widgetInativo</code>.
	 * 
	 * @param widgetInativo
	 */
	public void setWidgetInativo(Widget widgetInativo) {

		this.widgetInativo = widgetInativo;
	}

	/**
	 * Retorna o valor do atributo <code>chaveDominioDefault</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getChaveDominioDefault() {

		return chaveDominioDefault;
	}

	/**
	 * Define o valor do atributo <code>chaveDominioDefault</code>.
	 * 
	 * @param chaveDominioDefault
	 */
	public void setChaveDominioDefault(String chaveDominioDefault) {

		this.chaveDominioDefault = chaveDominioDefault;
	}

	/**
	 * Retorna o valor do atributo <code>dominioDefault</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioDefault() {

		return dominioDefault;
	}

	/**
	 * Define o valor do atributo <code>dominioDefault</code>.
	 * 
	 * @param dominioDefault
	 */
	public void setDominioDefault(Dominio dominioDefault) {

		this.dominioDefault = dominioDefault;
	}

	/**
	 * Retorna o valor do atributo <code>numeroDefault</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getNumeroDefault() {

		return numeroDefault;
	}

	/**
	 * Define o valor do atributo <code>numeroDefault</code>.
	 * 
	 * @param numeroDefault
	 */
	public void setNumeroDefault(Long numeroDefault) {

		this.numeroDefault = numeroDefault;
	}

	/**
	 * Retorna o valor do atributo <code>textoDefault</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getTextoDefault() {

		return textoDefault;
	}

	/**
	 * Define o valor do atributo <code>textoDefault</code>.
	 * 
	 * @param textoDefault
	 */
	public void setTextoDefault(String textoDefault) {

		this.textoDefault = textoDefault;
	}

	/**
	 * Retorna o valor do atributo <code>dataDefault</code>
	 * 
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataDefault() {

		return dataDefault;
	}

	/**
	 * Define o valor do atributo <code>dataDefault</code>.
	 * 
	 * @param dataDefault
	 */
	public void setDataDefault(Calendar dataDefault) {

		this.dataDefault = dataDefault;
	}

	public Boolean getBooleanDefault() {

		return booleanDefault;
	}

	public void setBooleanDefault(Boolean booleanDefault) {

		this.booleanDefault = booleanDefault;
	}

	public static long getSerialversionuid() {

		return serialVersionUID;
	}

	public void setId(Long id) {

		this.id = id;
	}

	
	/**
	 * Retorna o valor do atributo <code>atributoFiltro</code>
	 *
	 * @return <code>String</code>
	 */
	public String getAtributoFiltro() {
	
		return atributoFiltro;
	}

	
	/**
	 * Define o valor do atributo <code>atributoFiltro</code>.
	 *
	 * @param atributoFiltro 
	 */
	public void setAtributoFiltro(String atributoFiltro) {
	
		this.atributoFiltro = atributoFiltro;
	}
}
