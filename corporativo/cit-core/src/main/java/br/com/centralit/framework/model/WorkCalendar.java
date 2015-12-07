package br.com.centralit.framework.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;
import br.com.centralit.framework.util.UtilDate;

import com.fasterxml.jackson.annotation.JsonView;

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
public class WorkCalendar extends PersistentObjectAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2838317906078212464L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class })
	protected Long id;
	
	@Column(nullable = false, length=100)
	@JsonView({ Views.GenericView.class})
	private String description;
	
	@Column(nullable = false)
	@JsonView({ Views.GenericView.class})
	private Boolean considerHoliday;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.CalendarEditView.class})
	private WorkDay workDaySun;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.CalendarEditView.class})
	private WorkDay workDayMon;

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.CalendarEditView.class})
	private WorkDay workDayTue;

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.CalendarEditView.class})
	private WorkDay workDayWed;

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.CalendarEditView.class})
	private WorkDay workDayThu;

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.CalendarEditView.class})
	private WorkDay workDayFri;

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@JsonView({ Views.CalendarEditView.class})
	private WorkDay workDaySat;
	
	@Override
	public Long getId() {
		return id;
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
	 * Retorna o valor do atributo <code>considerHoliday</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getConsiderHoliday() {
	
		return considerHoliday;
	}

	
	/**
	 * Define o valor do atributo <code>considerHoliday</code>.
	 *
	 * @param considerHoliday 
	 */
	public void setConsiderHoliday(Boolean considerHoliday) {
	
		this.considerHoliday = considerHoliday;
	}

	
	/**
	 * Retorna o valor do atributo <code>workDaySun</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDaySun() {
	
		return workDaySun;
	}

	
	/**
	 * Define o valor do atributo <code>workDaySun</code>.
	 *
	 * @param workDaySun 
	 */
	public void setWorkDaySun(WorkDay workDaySun) {
	
		this.workDaySun = workDaySun;
	}

	
	/**
	 * Retorna o valor do atributo <code>workDayMon</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDayMon() {
	
		return workDayMon;
	}

	
	/**
	 * Define o valor do atributo <code>workDayMon</code>.
	 *
	 * @param workDayMon 
	 */
	public void setWorkDayMon(WorkDay workDayMon) {
	
		this.workDayMon = workDayMon;
	}

	
	/**
	 * Retorna o valor do atributo <code>workDayTue</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDayTue() {
	
		return workDayTue;
	}

	
	/**
	 * Define o valor do atributo <code>workDayTue</code>.
	 *
	 * @param workDayTue 
	 */
	public void setWorkDayTue(WorkDay workDayTue) {
	
		this.workDayTue = workDayTue;
	}

	
	/**
	 * Retorna o valor do atributo <code>workDayWed</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDayWed() {
	
		return workDayWed;
	}

	
	/**
	 * Define o valor do atributo <code>workDayWed</code>.
	 *
	 * @param workDayWed 
	 */
	public void setWorkDayWed(WorkDay workDayWed) {
	
		this.workDayWed = workDayWed;
	}

	
	/**
	 * Retorna o valor do atributo <code>workDayThu</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDayThu() {
	
		return workDayThu;
	}

	
	/**
	 * Define o valor do atributo <code>workDayThu</code>.
	 *
	 * @param workDayThu 
	 */
	public void setWorkDayThu(WorkDay workDayThu) {
	
		this.workDayThu = workDayThu;
	}

	
	/**
	 * Retorna o valor do atributo <code>workDayFri</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDayFri() {
	
		return workDayFri;
	}

	
	/**
	 * Define o valor do atributo <code>workDayFri</code>.
	 *
	 * @param workDayFri 
	 */
	public void setWorkDayFri(WorkDay workDayFri) {
	
		this.workDayFri = workDayFri;
	}

	
	/**
	 * Retorna o valor do atributo <code>workDaySat</code>
	 *
	 * @return <code>WorkDay</code>
	 */
	public WorkDay getWorkDaySat() {
	
		return workDaySat;
	}

	
	/**
	 * Define o valor do atributo <code>workDaySat</code>.
	 *
	 * @param workDaySat 
	 */
	public void setWorkDaySat(WorkDay workDaySat) {
	
		this.workDaySat = workDaySat;
	}

	
	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param id 
	 */
	public void setId(Long id) {
	
		this.id = id;
	}
	
    public WorkDay retrieveWorkDay(Date date) {
    	WorkDay workDay = null;
		int dayOfWeek = UtilDate.getDiaDaSemanaDoCalendar(date);
		switch (dayOfWeek) {
			case 1:
				workDay = this.getWorkDaySun();
				break;
			case 2:
				workDay = this.getWorkDayMon();
				break;
			case 3:
				workDay = this.getWorkDayTue();
				break;
			case 4:
				workDay = this.getWorkDayWed();
				break;
			case 5:
				workDay = this.getWorkDayThu();
				break;
			case 6:
				workDay = this.getWorkDayFri();
				break;
			case 7:
				workDay = this.getWorkDaySat();
				break;
			default:
				break;
		}
		return workDay;
    }

}