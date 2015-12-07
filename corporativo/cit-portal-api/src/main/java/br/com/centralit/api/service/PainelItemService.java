package br.com.centralit.api.service;

import java.util.Collection;

import br.com.centralit.framework.model.Painel;
import br.com.centralit.framework.model.PainelItem;
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
 * @since 23/03/2015 - 10:38:19
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface PainelItemService extends GenericService<PainelItem, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar o painelItem através do id do painel
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * 
	 * @return
	 */
	Collection<PainelItem> findPorIdPainel(Long idPainel);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por resolver as pripriedades transiente
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	void resolverPropriedadesTransiante(Painel entity);
	

	Collection<PainelItem> findPorPainelEPermissaoUsuario(Long id);

}
