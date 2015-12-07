package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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
 * @since 05/12/2014 - 13:54:41
 *
 * @version 1.0.0
 *
 * @author thiago.borges
 *
 */
@Entity
public class MapaOrganizacionalObservacao extends Observacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -9001397800188703532L;

	/** Atributo mapaOrganizacional. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private MapaOrganizacional mapaOrganizacional;
	
	/** Atributo mapaOrganizacionalInativo. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private MapaOrganizacional mapaOrganizacionalInativo;


	/**
	 * Retorna o valor do atributo <code>mapaOrganizacional</code>
	 *
	 * @return <code>MapaOrganizacional</code>
	 */
	public MapaOrganizacional getMapaOrganizacional() {

		return mapaOrganizacional;
	}


	/**
	 * Define o valor do atributo <code>mapaOrganizacional</code>.
	 *
	 * @param mapaOrganizacional
	 */
	public void setMapaOrganizacional(MapaOrganizacional mapaOrganizacional) {

		this.mapaOrganizacional = mapaOrganizacional;
	}
	
	/**
	 * Retorna o valor do atributo <code>mapaOrganizacionalInativo</code>
	 *
	 * @return <code>MapaOrganizacional</code>
	 */
	public MapaOrganizacional getMapaOrganizacionalInativo() {

		return mapaOrganizacionalInativo;
	}


	/**
	 * Define o valor do atributo <code>mapaOrganizacionalInativo</code>.
	 *
	 * @param mapaOrganizacionalInativo
	 */
	public void setMapaOrganizacionalInativo(MapaOrganizacional mapaOrganizacionalInativo) {

		this.mapaOrganizacionalInativo = mapaOrganizacionalInativo;
	}
}
