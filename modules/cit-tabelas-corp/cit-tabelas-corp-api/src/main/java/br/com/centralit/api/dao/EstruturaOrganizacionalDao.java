package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Usuario;

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
 * <b>Title: EstruturaOrganizacionalDao</b>
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
 * @since 10/12/2014 - 15:44:11
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
public interface EstruturaOrganizacionalDao extends CitGenericDAO {

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
	public List<EstruturaOrganizacional> findParents(Long idOrganizacao);

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
	 *            ID do <code>Organizacao</code> a se procurar a <code>EstruturaOrganizacional</code>.
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisPorOrganizacao(String nome, Long idOrganizacao);

	/**
	 * Método responsável por listar estruturas organizacionais filhas por orgão
	 *
	 * @author wilker.machado
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return List<EstruturaOrganizacional>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisFilhasPorOrganizacao(String nome, long idOrganizacao);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar todas as <code>EstruturaOrganizacional</code> de uma <code>Organizacao</code>
	 *
	 * @author geovane.filho
	 *
	 * @param idOrganizacao
	 *            ID da Organização a se obter as Estruturas Organizacionais.
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
	 * Método responsável por listar estruturas organizacionais da tree pelo id da órgão do usuário logado e nome
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idEstrutura
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
	 * Método responsável por buscar todas as estruturas organizacionais filhas com filtro datafim ou não
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
	 * Método responsável por validar se uma estrutura possui filhos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idOrganizacao
	 * @return boolean
	 */
	Boolean possuiFilhos(Long idOrganizacao);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por listar <code>EstruturaOrganizacional</code>que são loja de uma <code>Organizacao</code> a partir do nome da <code>EstruturaOrganizacional</code>.
	 *
	 * @author iago
	 *
	 * @param nome
	 *            Nome da EstruturaOrganizacional a se procurar.
	 * @param idOrganizacao
	 *            ID do <code>Organizacao</code> a se procurar a <code>EstruturaOrganizacional</code>.
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	List<EstruturaOrganizacional> listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao(String nome, Long idOrganizacao, Long idDominioUnidadeColeta);

}