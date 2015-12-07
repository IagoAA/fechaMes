package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Parceiro;
import br.com.centralit.api.viewHelper.DominioParceirosNomeBuscaVH;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;

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
 * <b>Title: </b>EnderecoDao
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * <p>
 * 
 * @since 05/12/2014 - 11:02:57
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
public interface ParceiroDao extends CitGenericDAO {

	/**
	 * Método responsável por buscar parceiros de forma generica passando os tipos de parceiros
	 * 
	 * @author wilker.machado
	 * 
	 * @param objeto
	 * @param idOrganizacao 
	 * @return Collection<Parceiro>
	 */
	Collection<Parceiro> findParceiros(DominioParceirosNomeBuscaVH objeto, Long idOrganizacao);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar os parceiros através do id da pessoa
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPessoa
	 * 
	 * @return Collection<Parceiro>
	 */
	Collection<Parceiro> findPorIdPessoa(Long idPessoa);

}
