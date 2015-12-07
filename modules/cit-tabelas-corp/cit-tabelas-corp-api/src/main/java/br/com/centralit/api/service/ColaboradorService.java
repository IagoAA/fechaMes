package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.Colaborador;
import br.com.centralit.framework.model.Usuario;
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
 * @since 02/01/2015 - 13:59:04
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
public interface ColaboradorService extends GenericService<Colaborador, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar a entidade <code>Colaborador</code> através do nome
	 *
	 * @author rogerio.costa
	 *
	 * @param nome
	 *
	 * @return Collection<Colaborador>
	 */
	Collection<Colaborador> findPorNome(String nome);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por por listar a entidade <code>Colaborador</code> através do nome e organizacao
	 * 
	 * @author thiago.borges
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	Collection<Colaborador> findPorNomeAndOrganizacao(String nome, Long idOrganizacao);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por verificar se contem colaborador vinculado a estruturaOrganizacional
	 *
	 * @author rogerio.costa
	 *
	 * @param idEstrutura
	 *
	 * @return boolean
	 */
	boolean exiteColaboradorVinculadoAEstrutura(Long idEstrutura);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por retornar o colaborador vinculado ao usuário
	 *
	 * @author carlos.santos
	 *
	 * @param usuario
	 *
	 * @return Colaborador
	 */
	Colaborador findPorUsuario(Usuario usuario);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se a funcao esta vinculada a algum colaborador
	 *
	 * @author juliana.barbosa
	 *
	 * @param idFuncao
	 * @return
	 */
	boolean existeFuncaoVinculadaAColaborador(Long idFuncao);

	/**
	 *
	 * Método responsável por buscar uma pessoa que seja um usuário no sistema
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return Collection<Colaborador>
	 */
	Collection<Colaborador> findPessoaColaboradorUsuarioPorNome(String nome);


}
