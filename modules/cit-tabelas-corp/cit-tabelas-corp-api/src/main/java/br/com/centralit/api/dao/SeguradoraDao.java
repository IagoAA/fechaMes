package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.api.model.Seguradora;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 14/04/2015 - 10:00:09
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *	
 */
public interface SeguradoraDao extends CitGenericDAO {


	List<Seguradora> listarSeguradorasPorNomeOrganizacao(String nome, Long idOrganizacao);

}
