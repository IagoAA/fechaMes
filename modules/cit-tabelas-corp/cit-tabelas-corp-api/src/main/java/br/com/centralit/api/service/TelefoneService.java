package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Telefone;
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
 * <b>Title: </b>TelefoneService
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 05/12/2014 - 11:06:53
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface TelefoneService extends GenericService<Telefone, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar os telefones através do id da pessoa
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPessoa
	 * 
	 * @return Collection<Parceiro>
	 */
	Collection<Telefone> findPorIdPessoa(Long idPessoa);

}
