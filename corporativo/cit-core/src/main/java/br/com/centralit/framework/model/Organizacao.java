package br.com.centralit.framework.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * <b>Description: Cadastro de organização para sistemas, uma organização pode ser um Órgão para entidades públicas ou Empresa para entidades privadas que venham a utilizar o sistema </b>
 * </p>
 *
 * @since 26/08/2015 - 11:18:34
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
@Entity
public class Organizacao extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2875274966399686132L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoView.class})
	protected Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.OrganizacaoEditView.class })
	private String codigo;

	/** Atributo nome. */
	@JsonView({Views.GenericView.class})
	private String nome;

	/** Atributo sigla. */
	@Column(length = 30)
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoListView.class, Views.UsuarioEditView.class, Views.UsuarioLogadoView.class})
	private String sigla;

	/** Atributo dataReferenciaVigente. */
	@Column(name = "dataReferenciaVigente", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoListView.class, Views.UsuarioLogadoView.class})
	private Calendar dataReferenciaVigente;

	/** Atributo dataInicio. */
	@Column(name = "dataInicio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.OrganizacaoEditView.class })
	private Calendar dataInicio;

	/** Atributo dataFim. */
	@Column(name = "dataFim", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.OrganizacaoEditView.class })
	private Calendar dataFim;

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

	/**
	 * Retorna o valor do atributo <code>dataReferenciaVigente</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataReferenciaVigente() {

		return dataReferenciaVigente;
	}

	/**
	 * Define o valor do atributo <code>dataReferenciaVigente</code>.
	 *
	 * @param dataReferenciaVigente
	 */
	public void setDataReferenciaVigente(Calendar dataReferenciaVigente) {

		this.dataReferenciaVigente = dataReferenciaVigente;
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
	 * Retorna o valor do atributo <code>dataInicio</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataInicio() {

		return dataInicio;
	}


	/**
	 * Define o valor do atributo <code>dataInicio</code>.
	 *
	 * @param dataInicio
	 */
	public void setDataInicio(Calendar dataInicio) {

		this.dataInicio = dataInicio;
	}


	/**
	 * Retorna o valor do atributo <code>dataFim</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataFim() {

		return dataFim;
	}


	/**
	 * Define o valor do atributo <code>dataFim</code>.
	 *
	 * @param dataFim
	 */
	public void setDataFim(Calendar dataFim) {

		this.dataFim = dataFim;
	}


}
