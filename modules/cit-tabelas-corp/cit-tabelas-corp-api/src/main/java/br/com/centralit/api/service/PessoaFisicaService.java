package br.com.centralit.api.service;

import br.com.centralit.api.model.PessoaFisica;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * <b>Title: </b>PessoaFisicaService
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
 * @since 02/01/2015 - 09:10:00
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface PessoaFisicaService extends GenericService<PessoaFisica, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por validar os dados da pessoa Fisica
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	void validarDados(PessoaFisica entity);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por localizar uma pessoa pelo CPF e órgão
	 *
	 * @author Carlos
	 *
	 * @param cpf
	 * @param id
	 * @return
	 */
	PessoaFisica findPorCPFAndOrganizacao(String cpf, long idOrganizacao);

}
