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
 * @since 09/03/2015 - 17:32:49
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
@JsonIgnoreProperties({ "$index"})
public class PainelItemParametro extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class})
	private String nome;

	/** Atributo atributoFiltro. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class})
	private String atributoFiltro;

	/** Atributo tipoWidgetParametro. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class})
	@ManyToOne(fetch = FetchType.LAZY)
	private Dominio tipoWidgetParametroDominio;

	/** Atributo chaveDominioDefault guarda o valor do atributo da tabela de dominio. */
	/** Atributo chaveDominioDefault. */
	@Column(length = 50)
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class})
	private String chaveDominioDefault;

	/** Atributo dominioDefault. */
	@ManyToOne
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	private Dominio dominioDefault;

	/** Atributo numeroDefault. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	private Long numeroDefault;

	/** Atributo textoDefault. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class})
	private String textoDefault;

	/** Atributo dataDefault. */
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDefault;
	
	@JsonView({ Views.WidgetEditView.class, Views.LookupView.class, Views.PainelEditView.class })
	private Boolean booleanDefault;

	/** Atributo painelItem. */
	@ManyToOne(fetch = FetchType.LAZY)
	private PainelItem painelItem;

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
	 * Retorna o valor do atributo <code>painelItem</code>
	 * 
	 * @return <code>PainelItem</code>
	 */
	public PainelItem getPainelItem() {

		return painelItem;
	}

	/**
	 * Define o valor do atributo <code>painelItem</code>.
	 * 
	 * @param painelItem
	 */
	public void setPainelItem(PainelItem painelItem) {

		this.painelItem = painelItem;
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
	 * Define o valor do atributo <code>id</code>.
	 * 
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
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

	
	/**
	 * Retorna o valor do atributo <code>booleanDefault</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getBooleanDefault() {
	
		return booleanDefault;
	}

	
	/**
	 * Define o valor do atributo <code>booleanDefault</code>.
	 *
	 * @param booleanDefault 
	 */
	public void setBooleanDefault(Boolean booleanDefault) {
	
		this.booleanDefault = booleanDefault;
	}

	

}
