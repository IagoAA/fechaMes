package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Luck;
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
 * @since 26/11/2015 - 10:09:20
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */
public interface LuckService extends GenericService<Luck, Long>{

	/**
	 * Método responsável por listar a entidade<code>Luck</code>
	 *
	 * @author iago
	 *
	 * @param nome
	 *
	 * @return Collection<Luck>
	 */
	Collection<Luck> listarLuck(String nome);

}
