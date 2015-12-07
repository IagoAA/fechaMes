package br.com.centralit.framework.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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
public class WorkDay extends PersistentObjectAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = -143649132615131632L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class })
	protected Long id;
	
	@Column(nullable = false, length=100)
	@JsonView({ Views.GenericView.class})
	private String description;
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "workDay", fetch = FetchType.LAZY)
	@JsonView({Views.CalendarEditView.class, Views.CalendarListView.class})
	@OrderBy("startTime ASC")
	private List<WorkTime> workTimes;

	public WorkDay(String description) {
		this.description = description;
	}
	
	public WorkDay() {
		
	}
	
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
	 * Retorna o valor do atributo <code>workTimes</code>
	 *
	 * @return <code>List<WorkTime></code>
	 */
	public List<WorkTime> getWorkTimes() {
	
		return workTimes;
	}

	
	/**
	 * Define o valor do atributo <code>workTimes</code>.
	 *
	 * @param workTimes 
	 */
	public void setWorkTimes(List<WorkTime> workTimes) {
	
		this.workTimes = workTimes;
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
	 * Retorna array com a hora de início de cada período de tempo
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */
	public long[] startTimeToArray() {
		if (this.getWorkTimes() == null)
			return new long[0];
		
        long[] array = new long[this.getWorkTimes().size()];
        
        for (int i = 0; i < this.getWorkTimes().size(); i++) {
        	WorkTime workTime = this.getWorkTimes().get(i);
        	array[i] = UtilDate.convertTimeToMillis(workTime.getStartTime());
        }
        Arrays.sort(array);

        return array;
    }

	/**
	 * Retorna array com a hora de término de cada período de tempo
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */
	public long[] endTimeToArray() {
		if (this.getWorkTimes() == null)
			return new long[0];
		
        long[] array = new long[this.getWorkTimes().size()];
        
        for (int i = 0; i < this.getWorkTimes().size(); i++) {
        	WorkTime workTime = this.getWorkTimes().get(i);
        	array[i] = UtilDate.convertTimeToMillis(workTime.getEndTime());
        }
        Arrays.sort(array);

        return array;
    }

	/**
	 * Retorna a hora útil inicial de uma data
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */
    public long getStartWorkTime(final long timeRef) {
        long result = timeRef;

        if (this.getWorkTimes() != null) {
	        final long[] startArray = this.startTimeToArray();
	        final long[] endArray = this.endTimeToArray();
	        for (int i = 0; i < startArray.length; i++) {
	        	long startTime = startArray[i];
	        	if (timeRef < startTime) {
		        	if (startTime <= timeRef && endArray[i] >= timeRef) {
		        		result = startArray[i];
		        		break;
		        	}
	        	}else if (timeRef <= endArray[i]) {
	        		break;
	        	}
			}
        }
        
        return result;  
    }
    
	/**
	 * Retorna a hora útil final de uma data
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */
    public long getEndWorkTime(final long timeRef) {
        long result = timeRef;

        if (this.getWorkTimes() != null) {
	        final long[] endArray = this.endTimeToArray();
	        for (int i = 0; i < endArray.length; i++) {
	        	if (endArray[i] > result) {
	        		result = endArray[i];
	        	}
			}
        }
        
        return result;  
    }

	/**
	 * Retorna a última hora da jornada de trabalho
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */
    public long getEndWorkTime() {
    	if (this.getWorkTimes() == null)
    		return 0;
    	
    	final long[] endArray = this.endTimeToArray();
    	return endArray[endArray.length-1];
    }

	/**
	 * Retorna a primeira hora da jornada de trabalho
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */
    public long getStartWorkTime() {    	
	    if (this.getWorkTimes() == null)
			return 0;
		
        final long[] startArray = this.startTimeToArray();
		return startArray[0];
    }

	/**
	 * Retorna a soma dos intervalos da jornada de trabalho
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */    
    public long calculateIntervals(final long startTime, final long endTime) {
    	if (this.getWorkTimes() == null)
    		return 0;
    	
        long interval = 0;
        final long[] startArray = this.startTimeToArray();
        final long[] endArray = this.endTimeToArray();
        for (int i = 0; i < startArray.length-1; i++) {
        	final long thisTime = endArray[i];
        	final long nextTime = startArray[i+1];
        	if (thisTime >= startTime && nextTime <= endTime) {
        		interval += nextTime - thisTime;
        	}
        }
        
        return interval;
    }	
    
	/**
	 * Retorna a soma de horários dos períodos de trabalho até uma data
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */    
    public long calculateTotalWorkTime(final long startTime, final long endTime) {
    	final long interval = this.calculateIntervals(startTime, endTime);
    	
    	final long result = endTime - startTime - interval;
        if (result > 0) {
            return result;
        }else{
        	return 0;
        }
    }
    
	/**
	 * Retorna a soma total de horários dos períodos de trabalho
	 *
	 * @author Carlos.santos
	 *
	 * @return
	 */    
    public long calculateTotalWorkTime() {
        final long startTime = this.getStartWorkTime();
        final long endTime = this.getEndWorkTime();
        
        return this.calculateTotalWorkTime(startTime, endTime);
    }    
    
    /**
     * retorna o fim de um jornada proxima a hora passada
     *
     * @author Carlos
     *
     * @param timeRef
     * @return
     */
    public long getNextEndWorkTime(final long timeRef) {
    	if (this.getWorkTimes() == null)
    		return 0;

        final long[] endArray = this.endTimeToArray();
        for (int i = 0; i < endArray.length; i++) {
            if (endArray[i] >= timeRef) {
                return endArray[i];
            }        	
        }
        return 0;
    }    
}