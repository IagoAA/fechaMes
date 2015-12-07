package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Endereco;
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
 * @author rogerio.costa
 * 
 */
public interface EnderecoDao extends CitGenericDAO {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar enderecos
	 * 
	 * @author iago.almeida
	 * 
	 * @param logradouro
	 * @param complemento
	 * @return
	 */
	Collection<Endereco> listarEndereco(String nome);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar os enderecos através do id da pessoa
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPessoa
	 * 
	 * @return Collection<Parceiro>
	 */
	Collection<Endereco> findPorIdPessoa(Long idPessoa);

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @param bairroId
	 * @return
	 */
	boolean existeBairroVinculadoAEndereco(Long bairroId);

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @param enderecoId
	 * @return
	 */
	boolean existeEnderecoVinculadoALocalizacao(Long enderecoId);

}
