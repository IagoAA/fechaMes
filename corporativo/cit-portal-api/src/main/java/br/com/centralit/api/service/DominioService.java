package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.framework.model.Dominio;
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
 * @since 05/12/2014 - 10:40:49
 *
 * @version 1.0.0
 *
 * @author thiago.borges
 *
 */
public interface DominioService extends GenericService<Dominio, Long> {

	/**
	 * Método responsável por listar a entidade <code>Dominio</code> através da chave
	 *
	 * @author rogerio.costa
	 *
	 * @param chave
	 *
	 * @return Collection<Dominio>
	 */
	Collection<Dominio> listarPorChave(String chave);

	/**
	 * Método responsável por listar a entidade <code>Dominio</code> através da chave e o valor (Autocomplete)
	 *
	 * @author wilker.machado
	 *
	 * @param chave
	 * @param valor
	 * @return <code>Collection<Dominio></code>
	 */
	Collection<Dominio> listarPorChaveValor(String chave, String valor);

	/**
	 * Método responsável por obter o dominio através da chave e codigo
	 *
	 * @author rogerio.costa
	 *
	 * @param chave
	 * @param codigo
	 *
	 * @return Dominio
	 */
	Dominio findByChaveAndCodigo(String chave, Long codigo);

	/**
	 *
	 * Método responsável por listar as chaves de dominios existentes
	 *
	 * @author iago.almeida
	 *
	 * @return
	 */
	Collection<String> listarChavesExistentes();

	/**
	 *
	 * Método responsável por buscar dominio pela chave e o nome
	 *
	 * @author wilker.machado
	 *
	 * @param chave
	 * @param nome
	 * @return <code>Dominio</code>
	 */
	Dominio findByChaveAndNome(String chave, String nome);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar um dominio pelo codigo passado
	 *
	 * @author thiago.borges
	 *
	 * @param codigo
	 * @return
	 */
	Dominio buscaDominioByCodigo(Long codigo);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se para a chave passada ja existe o codigo passado
	 *
	 * @author thiago.borges
	 *
	 * @param chave
	 * @param codigo
	 * @return
	 */
	boolean validaCodigoDominioPorChave(String chave, Long codigo);

}
