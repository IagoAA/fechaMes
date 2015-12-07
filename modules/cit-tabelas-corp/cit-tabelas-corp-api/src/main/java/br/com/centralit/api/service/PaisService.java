package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Pais;
import br.com.centralit.framework.service.arquitetura.GenericService;


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
 * @since 26/11/2014 - 10:09:20
 *
 * @version 1.0.0
 *
 * @author david.silva
 *	
 */
public interface PaisService extends GenericService<Pais, Long>{
	
	/**
	 * Método responsável por listar a entidade<code>Pais</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @param nome
	 * 
	 * @return Collection<Pais>
	 */
	Collection<Pais> listarPais(String nome);
	
}
