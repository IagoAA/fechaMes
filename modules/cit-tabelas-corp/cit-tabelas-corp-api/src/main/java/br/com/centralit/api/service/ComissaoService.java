package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.api.model.Comissao;
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
 * <b>Title: Serviços de InventarioComissao </b>
 * </p>
 * 
 * <p>
 * <b>Description: Serviços especificos de InventarioComissao </b>
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
 * @since 29/12/2014 - 10:49:08
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
public interface ComissaoService extends GenericService<Comissao, Long> {

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
	 * Método responsável por listar comissões por nome e órgão
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return List<InventarioComissao>
	 */
	List<Comissao> listarComissaoPorNomeEOrganizacao(String nome, Long idOrganizacao);
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
	 * Método responsável por listar comissões por nome, órgão e domínio
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param idDominio
	 * @return List<Comissao>
	 */
	List<Comissao> listarComissaoPorNomeEOrganizacaoEDominio(String nome, Long idOrganizacao, Long idDominio);
}
