package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Bairro;
import br.com.centralit.api.model.Cidade;
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
 * <b>Title: </b>BairroDao
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
 * @author rogerio.cassimiro
 * 
 */
public interface BairroDao extends CitGenericDAO {

	/**
	 * Método responsável por listar a entidade <code>Bairro</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @param estado
	 * 
	 * @return Collection<Bairro>
	 */
	Collection<Bairro> listarBairro(EntidadeNomeBuscaVH<Cidade> cidade);

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
	 * Método responsável por verificar se existe um bairro já cadastrado com o mesmo nome para uma cidade.
	 * 
	 * @author luis.camargo
	 * 
	 * @param bairro
	 * @return boolean
	 */
	boolean existeBairroMesmoNomePorCidade(Bairro bairro);

}
