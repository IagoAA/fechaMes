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
import javax.persistence.Transient;

import br.com.centralit.framework.json.JsonCalendarSerializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class WorkTime extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8773917286707051413L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class })
	protected Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JsonIgnore
	private WorkDay workDay;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.CalendarEditView.class, Views.CalendarListView.class })
	private Calendar startTime;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.CalendarEditView.class, Views.CalendarListView.class })
	private Calendar endTime;
	
	@Transient
	@JsonView({ Views.CalendarEditView.class, Views.CalendarListView.class })
	private String startTimeStr;

	@Transient
	@JsonView({ Views.CalendarEditView.class, Views.CalendarListView.class })
	private String endTimeStr;
	
	public WorkTime(WorkDay workDay, Calendar startTime, Calendar endTime) {
		this.workDay = workDay;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public WorkTime() {
		
	}
	
	@Override
	public Long getId() {
		return id;
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
	 * Retorna o valor do atributo <code>endTime</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getEndTime() {
	
		return endTime;
	}



	
	/**
	 * Define o valor do atributo <code>endTime</code>.
	 *
	 * @param endTime 
	 */
	public void setEndTime(Calendar endTime) {
	
		this.endTime = endTime;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * Retorna o valor do atributo <code>startTimeStr</code>
	 *
	 * @return <code>String</code>
	 */
	public String getStartTimeStr() {
	
		return startTimeStr;
	}

	
	/**
	 * Define o valor do atributo <code>startTimeStr</code>.
	 *
	 * @param startTimeStr 
	 */
	public void setStartTimeStr(String startTimeStr) {
	
		this.startTimeStr = startTimeStr;
	}

	
	/**
	 * Retorna o valor do atributo <code>endTimeStr</code>
	 *
	 * @return <code>String</code>
	 */
	public String getEndTimeStr() {
	
		return endTimeStr;
	}

	
	/**
	 * Define o valor do atributo <code>endTimeStr</code>.
	 *
	 * @param endTimeStr 
	 */
	public void setEndTimeStr(String endTimeStr) {
	
		this.endTimeStr = endTimeStr;
	}

}