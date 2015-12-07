package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.api.model.ComissaoIntegrante;
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
 * <b>Title: DAO de InventarioComissaoIntegrante </b>
 * </p>
 * 
 * <p>
 * <b>Description: DAO contendo os serviços de dados especificos de InventarioComissaoIntegrante </b>
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
public interface ComissaoIntegranteDao extends CitGenericDAO {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por as lista de InventarioComissaoIntegrante por nome do colaborador e idComissao
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idComissao
	 * @return List<InventarioComissaoIntegrante>
	 */
	List<ComissaoIntegrante> findComissaoIntegrantePorColaborador(String nome, Long idComissao);
}
