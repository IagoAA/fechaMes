package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.PainelItem;
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
 * @since 23/03/2015 - 10:34:05
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface PainelItemDao extends CitGenericDAO {

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
	 * Método responsável por obter painelItem através do id do painel e regras de permissão do usuario logado
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * @param usuario
	 * @return Collection<PainelItem>
	 */
	Collection<PainelItem> findPorPainelEPermissaoUsuario(Long idPainel, Usuario usuario);

}
