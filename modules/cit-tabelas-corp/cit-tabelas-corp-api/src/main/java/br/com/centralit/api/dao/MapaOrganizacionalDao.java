package br.com.centralit.api.dao;

import br.com.centralit.api.model.MapaOrganizacional;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Usuario;


public interface MapaOrganizacionalDao extends CitGenericDAO {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar o mapa organizacional ativo
	 *
	 * @author thiago.borges
	 * 
	 * @param usuarioLogado 
	 *
	 * @return
	 */
	MapaOrganizacional findMapaAtivo(Usuario usuarioLogado);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar o ultimo mapa organizacional fechado
	 *
	 * @author thiago.borges
	 *
	 * @return
	 */
	MapaOrganizacional findUltimoMapaFechado();
}