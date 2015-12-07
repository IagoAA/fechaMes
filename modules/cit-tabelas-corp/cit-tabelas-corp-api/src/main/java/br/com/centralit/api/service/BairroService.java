package br.com.centralit.api.service;


import java.util.Collection;

import br.com.centralit.api.model.Bairro;
import br.com.centralit.api.model.Cidade;
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
 * @since 03/01/2015 - 14:32:22
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
public interface BairroService extends GenericService<Bairro, Long> {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se a cidade a ser excluida é utilizada por bairro
	 *
	 * @author iago.almeida
	 *
	 * @param idCidade
	 * @return boolean
	 */
	boolean existeBairroVinculadoACidade(Long idCidade);
	
	/**
	 * Método responsável por listar a Entidade<code>Bairro</code>
	 * 
	 * @author iago.almeida
	 * 
	 * @return Collection<Bairro>
	 */
	Collection<Bairro> listarBairro(EntidadeNomeBuscaVH<Cidade> cidade);
}
