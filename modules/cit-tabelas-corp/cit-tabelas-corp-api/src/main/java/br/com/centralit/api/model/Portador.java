package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

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
 * @since 04/12/2014 - 16:39:37
 * 
 * @version 1.0.0
 * 
 * @author ally.barra
 * 
 */
@Entity
@Table(name = "parceiro_portador")
public class Portador extends Parceiro {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 5444364769310678027L;

}
