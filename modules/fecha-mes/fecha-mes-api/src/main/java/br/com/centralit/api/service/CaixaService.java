package br.com.centralit.api.service;


import java.util.Collection;

import br.com.centralit.api.model.Caixa;
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
 * @since 26/11/2015 - 15:36:50
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
public interface CaixaService extends GenericService<Caixa, Long> {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se o luck a ser excluido é utilizado por caixa
	 *
	 * @author iago.almeida
	 *
	 * @param idLuck
	 * @return boolean
	 */
	boolean existeCaixaVinculadoAoLuck(Long idLuck);

	/**
	 * Método responsável por listar a entidade<code>Caixa</code>
	 *
	 * @author iago
	 *
	 * @param pais
	 * @param nome
	 * @return Collection<Caixa>
	 */
	Collection<Caixa> listarCaixa(Luck luck, String nome);

}
