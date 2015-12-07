package br.com.centralit.framework.model.arquitetura;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@MappedSuperclass
public abstract class PersistentObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inativador_id")
	protected Usuario inativador;

	@Column(name = "dataInativo")
	@Temporal(TemporalType.DATE)
	@JsonView({ Views.GenericView.class })
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	protected Calendar dataInativo;
	
	@Column(name = "dataBloqueio")
	@Temporal(TemporalType.DATE)
	@JsonView({ Views.GenericView.class })
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	protected Calendar dataBloqueio;

	@JsonIgnore
	public boolean isNew() {

		return this.getId() == null;
	}

	@JsonIgnore
	public boolean isActive() {

		return this.getDataInativo() == null;
	}

	public Usuario getInativador() {
		return inativador;
	}

	public void setInativador(Usuario inativador) {
		this.inativador = inativador;
	}

	public Calendar getDataInativo() {
		return dataInativo;
	}

	public void setDataInativo(Calendar dataInativo) {
		this.dataInativo = dataInativo;
	}
	
	/**
	 * Retorna o valor do atributo <code>dataBloqueio</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataBloqueio() {
		return dataBloqueio;
	}

	/**
	 * Define o valor do atributo <code>dataBloqueio</code>.
	 *
	 * @param Calendar
	 */
	public void setDataBloqueio(Calendar dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.getId() == null ) ? 0 : this.getId().hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersistentObject other = (PersistentObject) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

}
