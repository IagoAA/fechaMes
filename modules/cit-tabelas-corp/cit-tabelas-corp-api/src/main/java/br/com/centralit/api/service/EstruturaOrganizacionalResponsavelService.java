package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
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
 * @since 06/01/2015 - 15:08:35
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
public interface EstruturaOrganizacionalResponsavelService extends GenericService<EstruturaOrganizacionalResponsavel, Long> {

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
}
