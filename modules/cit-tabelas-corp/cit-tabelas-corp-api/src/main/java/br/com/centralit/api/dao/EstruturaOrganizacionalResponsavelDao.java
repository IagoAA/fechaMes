package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
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
 * @since 06/01/2015 - 15:13:54
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
public interface EstruturaOrganizacionalResponsavelDao extends CitGenericDAO {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por buscar o responsavel pelo id da estrutura organizacional
	 *
	 * @author renato.jesus
	 *
	 * @param estruturaOrganizacional
	 * @return
	 */
	public Collection<EstruturaOrganizacionalResponsavel> findByIdEstruturaOrganizacional(EstruturaOrganizacional estruturaOrganizacional);

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
	 * @param idDetentor
	 * @return
	 */
	boolean exiteEstruturaOrganizacionalResponsavelVinculadoAoColaborador(Long idColaborador);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por buscar os responsáveis pelo id da estrutura organizacional em ordem ascendente do atributo ordem
	 *
	 * @author geovane.filho
	 *
	 * @param estruturaOrganizacionalId id da estrutura
	 * @return
	 */
	public Collection<EstruturaOrganizacionalResponsavel> findByIdEstruturaOrganizacionalOrdemAsc(Long estruturaOrganizacionalId);
}
