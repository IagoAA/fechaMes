package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Estado;
import br.com.centralit.api.model.Regiao;
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
 * @since 03/01/2015 - 15:36:14
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
public interface EstadoService extends GenericService<Estado, Long> {
	
	/**
	 * Método responsável por listar a Endiade<code>Estado</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @param regiao
	 * 
	 * @param nome
	 * 
	 * @return Collection<Estado>
	 */
	Collection<Estado> listarEstado(Regiao regiao, String nome);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a regiao a ser excluida é utilizada por estado
	 *
	 * @author iago.almeida
	 *
	 * @param idRegiao
	 * @return boolean
	 */
	boolean existeEstadoVinculadoARegiao(Long idRegiao);
	
}
