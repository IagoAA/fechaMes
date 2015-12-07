package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.api.model.Seguradora;
import br.com.centralit.framework.service.arquitetura.GenericService;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: CAdastro do parceiro Seguradora</b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 14/04/2015 - 09:52:26
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *	
 */
public interface SeguradoraService extends GenericService<Seguradora, Long> {


	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por buscar as pessoas que são seguradoras
	 *
	 * @author juliana.barbosa
	 *
	 * @param nome
	 * @return
	 */
	List<Seguradora> listarSeguradorasPorNomeOrganizacao(String nome, Long idOrganizacao);

}
