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

import br.com.centralit.framework.json.JsonCalendarSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class WorkDayException extends PersistentObjectAudit{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8426582498621369649L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class })
	protected Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JsonIgnore
	private WorkCalendar workCalendar;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonIgnore
	private WorkDay workDay;
	
	@Column(nullable = false, length=100)
	@JsonView({ Views.GenericView.class})
	private String description;
	
	@JsonView({ Views.GenericView.class })
	private Boolean hasWork;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView({ Views.GenericView.class})
	@JsonSerialize(using = JsonCalendarSerializer.class)
	private Calendar startTime;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView({ Views.GenericView.class})
	@JsonSerialize(using = JsonCalendarSerializer.class)
	private Calendar startEnd;

	@Override
	public Long getId() {
		return id;
	}

	
	/**
	 * Retorna o valor do atributo <code>workCalendar</code>
	 *
	 * @return <code>WorkCalendar</code>
	 */
	public WorkCalendar getWorkCalendar() {
	
		return workCalendar;
	}

	
	/**
	 * Define o valor do atributo <code>workCalendar</code>.
	 *
	 * @param workCalendar 
	 */
	public void setWorkCalendar(WorkCalendar workCalendar) {
	
		this.workCalendar = workCalendar;
	}
	
	/**
	 * Retorna o valor do atributo <code>workDay</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDay() {
	
		return workDay;
	}


	
	/**
	 * Define o valor do atributo <code>workDay</code>.
	 *
	 * @param workDay 
	 */
	public void setWorkDay(WorkDay workDay) {
	
		this.workDay = workDay;
	}


	/**
	 * Retorna o valor do atributo <code>description</code>
	 *
	 * @return <code>String</code>
	 */
	public String getDescription() {
	
		return description;
	}

	
	/**
	 * Define o valor do atributo <code>description</code>.
	 *
	 * @param description 
	 */
	public void setDescription(String description) {
	
		this.description = description;
	}

	
	/**
	 * Retorna o valor do atributo <code>startTime</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getStartTime() {
	
		return startTime;
	}

	
	/**
	 * Define o valor do atributo <code>startTime</code>.
	 *
	 * @param startTime 
	 */
	public void setStartTime(Calendar startTime) {
	
		this.startTime = startTime;
	}

	
	/**
	 * Retorna o valor do atributo <code>startEnd</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getStartEnd() {
	
		return startEnd;
	}

	
	/**
	 * Define o valor do atributo <code>startEnd</code>.
	 *
	 * @param startEnd 
	 */
	public void setStartEnd(Calendar startEnd) {
	
		this.startEnd = startEnd;
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
	 * Retorna o valor do atributo <code>hasWork</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getHasWork() {
	
		return hasWork;
	}


	
	/**
	 * Define o valor do atributo <code>hasWork</code>.
	 *
	 * @param hasWork 
	 */
	public void setHasWork(Boolean hasWork) {
	
		this.hasWork = hasWork;
	}

}