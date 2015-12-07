package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: Cadastro de Seguradora para o projeto contratos, não há nenhum atributo adicional para Seguradora, então no momento teremos so a opção de marcar uma pessoa como seguradora</b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 13/04/2015 - 16:52:31
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *
 */
@Entity
@Table(name = "parceiro_seguradora")
public class Seguradora extends Parceiro {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -2401992494994994607L;


}
