package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.api.model.EstruturaOrganizacional;
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
 * <b>Title: EstruturaOrganizacionalService</b>
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
 * @since 10/12/2014 - 15:44:29
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
public interface EstruturaOrganizacionalService extends GenericService<EstruturaOrganizacional, Long> {

	/**
	 * Método responsável por
	 *
	 * @author thiago.borges
	 * @param descricao
	 *
	 * @return
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionais(String nome);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por buscar todas as estruturas organizacionais nivel 0 (zero)
	 *
	 * @author renato.jesus
	 *
	 * @param idOrganizacao
	 * @return
	 */
	List<EstruturaOrganizacional> findParents(Long idOrganizacao);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar <code>EstruturaOrganizacional</code> de uma <code>Organizacao</code> a partir do nome da <code>EstruturaOrganizacional</code>.
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 *            Nome da EstruturaOrganizacional a se procurar.
	 * @param idOrganizacao
	 *            ID da <code>Organizacao</code> a se procurar a <code>EstruturaOrganizacional</code>.
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorOrganizacao(String nome, Long idOrganizacao);

	/**
	 * Método responsável por listar estruturas organizacionais filhas por organizacao
	 *
	 * @author wilker.machado
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return List<EstruturaOrganizacional>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisFilhasPorOrganizacao(String nome, long idOrganizacao);

	/**
	 *
	 * Método responsável por listar todas as <code>EstruturaOrganizacional</code> de uma <code>Organizacao</code>
	 *
	 * @author geovane.filho
	 *
	 * @param idOrganizacao
	 *            ID da organização a se obter as Estruturas Organizacionais.
	 *
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorOrganizacao(Long idOrganizacao);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por
	 *
	 * @author iago.almeida
	 *
	 * @param localizacaoId
	 * @param usuarioLogado
	 * @return
	 */
	boolean existeEstruturaVinculadaALocacalizacao(Long localizacaoId, Usuario usuarioLogado);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar estruturas organizacionais na tree por nome
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @return List<EstruturaOrganizacional>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorNomeTree(String nome);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar estruturas organizacionais da tree pelo id da órgão do usuário logado e nome e filtro datafim
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idOrganizacao
	 * @param nome
	 * @param exibirEstruturasAtivas
	 * @return List<EstruturaOrganizacional>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisDaTree(Long idOrganizacao, String nome, Boolean exibirEstruturasAtivas);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por buscar todas as estruturas organizacionais filhas com filtro data fim ou não
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idOrganizacao
	 * @param exibirEstruturasAtivas
	 * @return List<EstruturaOrganizacional>
	 */
	List<EstruturaOrganizacional> findChildrens(Long idOrganizacao, Boolean exibirEstruturasAtivas);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por verificar a regra de remoção da estrutura
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 */
	public boolean contemEntidadeVinculada(Long id);


	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar <code>EstruturaOrganizacional</code> que são loja de uma <code>Organizacao</code> a partir do nome da <code>EstruturaOrganizacional</code>.
	 *
	 * @author iago
	 *
	 * @param nome
	 *            Nome da EstruturaOrganizacional a se procurar.
	 * @param idOrganizacao
	 *            ID da <code>Organizacao</code> a se procurar a <code>EstruturaOrganizacional</code>.
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao(String nome, Long idOrganizacao);

}