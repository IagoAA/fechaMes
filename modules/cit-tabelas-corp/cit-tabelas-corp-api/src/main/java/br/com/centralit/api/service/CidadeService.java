package br.com.centralit.api.service;


import java.util.Collection;

import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.model.Estado;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
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
 * @since 03/01/2015 - 15:35:38
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
public interface CidadeService extends GenericService<Cidade, Long> {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se o estado a ser excluido é utilizado por cidade
	 *
	 * @author iago.almeida
	 *
	 * @param idEstado
	 * @return boolean
	 */
	boolean existeCidadeVinculadoAoEstado(Long idEstado);

	/**
	 * Método responsável por listar a Endiade<code>Cidade</code>
	 * 
	 * @author iago.almeida
	 * 
	 * @return Collection<Cidade>
	 */
	Collection<Cidade> listarCidades(EntidadeNomeBuscaVH<Estado> estado);
}
