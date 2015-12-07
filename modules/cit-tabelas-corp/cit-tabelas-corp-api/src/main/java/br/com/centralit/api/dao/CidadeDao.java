package br.com.centralit.api.dao;

import java.util.Collection;


import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.model.Estado;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
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
public interface CidadeDao extends CitGenericDAO {

	/**
	 * Método responsável por listar as cidades de um estado
	 *
	 * @author wilker.machado
	 *
	 * @param id
	 * @param nome 
	 * @return
	 */
	Collection<Cidade> listarCidades(EntidadeNomeBuscaVH<Estado> estado);

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
	 * Método responsável por verificar se existe uma cidade já cadastrada com o mesmo nome para um estado.
	 * 
	 * @author luis.camargo
	 * 
	 * @param cidade
	 * @return boolean
	 */
	boolean existeCidadeMesmoNomePorEstado(Cidade cidade);

}
