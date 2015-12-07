package br.com.centralit.api.dao;

import java.util.Calendar;
import java.util.List;

import br.com.centralit.api.model.Comissao;
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
 * <b>Title: DAO de InventarioComissao </b>
 * </p>
 * 
 * <p>
 * <b>Description: DAO contendo os serviços de dados especificos de InventarioComissao </b>
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
 * @since 29/12/2014 - 10:46:01
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
public interface ComissaoDao extends CitGenericDAO {

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
	 * @author rogerio.costa
	 * 
	 * @param idColaborador
	 * @return
	 */
	boolean exiteInventarioComissaoVinculadoAoColaborador(Long idColaborador);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar comissões por nome e órgão, com dataExtinção vazia ou maior que a referência vigente
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param referenciaVigente
	 * @return List<Comissao>
	 */
	List<Comissao> listarComissaoPorNomeEOrganizacao(String nome, Long idOrganizacao, Calendar referenciaVigente);
	
	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por verificar se contem Inventario vinculado a estrutura
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idEstrutura
	 * 
	 * @return boolean
	 */
	boolean existeInventarioVinculadaAEstrutura(Long idEstrutura);
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar comissões por nome, organização e domínio
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param idDominio
	 * @param referenciaVigente
	 * @return List<Comissao>
	 */
	List<Comissao> listarComissaoPorNomeEOrganizacaoEDominio(String nome, Long idOrganizacao, Long idDominio, Calendar referenciaVigente);
}
