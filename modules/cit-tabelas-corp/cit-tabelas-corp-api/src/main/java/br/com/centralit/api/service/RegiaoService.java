package br.com.centralit.api.service;


import java.util.Collection;

import br.com.centralit.api.model.Pais;
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
 * @since 03/01/2015 - 15:36:50
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
public interface RegiaoService extends GenericService<Regiao, Long> {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se o pais a ser excluido é utilizado por regiao
	 *
	 * @author iago.almeida
	 *
	 * @param idPais
	 * @return
	 */
	boolean existeRegiaoVinculadoAoPais(Long idPais);
	
	/**
	 * Método responsável por listar a entidade<code>Regiao</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @param pais
	 * 
	 * @param nome
	 * 
	 * @return Collection<Regiao>
	 */
	Collection<Regiao> listarRegiao(Pais pais, String nome);
	
}
