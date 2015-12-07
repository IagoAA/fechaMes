package br.com.centralit.api.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.centralit.framework.json.JsonCalendarSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 04/02/2015 - 10:56:16
 *
 * @version 1.0.0
 *
 * @author carlos.alberto
 *	
 */
@Entity
public class Feriado extends PersistentObjectAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 789126953015608573L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class })
	protected Long id;
	
	/** Atributo dominioTipoFeriado. */
	@JsonView({ Views.GenericView.class})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Dominio dominioTipoFeriado;
	
	/** Atributo dominioAbrangenciaFeriado. */
	@JsonView({ Views.GenericView.class})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Dominio dominioAbrangenciaFeriado;
	
	@Column(nullable = false, length=100)
	@JsonView({ Views.GenericView.class})
	private String descricao;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView({ Views.GenericView.class})
	@JsonSerialize(using = JsonCalendarSerializer.class)
	private Calendar dataInicial;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView({ Views.GenericView.class})
	@JsonSerialize(using = JsonCalendarSerializer.class)
	private Calendar dataFinal;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.GenericView.class})
	private Pais pais;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.GenericView.class})
	private Estado estado;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.GenericView.class})
	private Cidade cidade;

	@Column(nullable = false)
	@JsonView({ Views.GenericView.class})
	private Boolean recorrente;
	
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the dataInicial
	 */
	public Calendar getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial the dataInicial to set
	 */
	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return the dataFinal
	 */
	public Calendar getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return the pais
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the recorrente
	 */
	public Boolean getRecorrente() {
		return recorrente;
	}

	/**
	 * @param recorrente the recorrente to set
	 */
	public void setRecorrente(Boolean recorrente) {
		this.recorrente = recorrente;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Dominio getDominioTipoFeriado() {
		return dominioTipoFeriado;
	}

	public void setDominioTipoFeriado(Dominio dominioTipoFeriado) {
		this.dominioTipoFeriado = dominioTipoFeriado;
	}

	public Dominio getDominioAbrangenciaFeriado() {
		return dominioAbrangenciaFeriado;
	}

	public void setDominioAbrangenciaFeriado(Dominio dominioAbrangenciaFeriado) {
		this.dominioAbrangenciaFeriado = dominioAbrangenciaFeriado;
	}


}