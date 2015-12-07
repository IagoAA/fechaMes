package br.com.centralit.api.service;

import br.com.centralit.api.model.Pessoa;
import br.com.centralit.api.viewHelper.PessoaVH;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 *
 * <p>
 * <b>Company: </b> Central IT - Governança Corporativa -
 * </p>
 *
 * <p>
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 02/01/2015 - 13:59:04
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
public interface ImportacaoDadosService {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por importar colaborador a partir de um sistema externo
	 *
	 * @author Carlos
	 *
	 * @param pessoa
	 * @return
	 */
	public Pessoa importaColaborador(PessoaVH pessoaVH);

}
