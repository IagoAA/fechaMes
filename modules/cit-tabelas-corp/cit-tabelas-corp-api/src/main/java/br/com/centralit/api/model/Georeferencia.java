package br.com.centralit.api.model;

import javax.persistence.Embeddable;

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
 * <b>Title: </b> Georeferencia
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
 * @since 12/03/2015 - 15:11:52
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Embeddable
public class Georeferencia {

	/** Atributo latitude. */
	private Long latitude;

	/** Atributo longitude. */
	private Long longitude;

	/**
	 * Retorna o valor do atributo <code>latitude</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getLatitude() {

		return latitude;
	}

	/**
	 * Define o valor do atributo <code>latitude</code>.
	 * 
	 * @param latitude
	 */
	public void setLatitude(Long latitude) {

		this.latitude = latitude;
	}

	/**
	 * Retorna o valor do atributo <code>longitude</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getLongitude() {

		return longitude;
	}

	/**
	 * Define o valor do atributo <code>longitude</code>.
	 * 
	 * @param longitude
	 */
	public void setLongitude(Long longitude) {

		this.longitude = longitude;
	}

}
