package br.com.centralit.api.service;

import br.com.centralit.api.model.MapaOrganizacional;
import br.com.centralit.framework.service.arquitetura.GenericService;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 10/12/2014 - 15:51:24
 *
 * @version 1.0.0
 *
 * @author thiago.borges
 *
 */
public interface MapaOrganizacionalService extends GenericService<MapaOrganizacional, Long> {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar o ultimo mapa fechado
	 *
	 * @author thiago.borges
	 *
	 * @return
	 */
	MapaOrganizacional findUltimoMapaFechado();

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar o mapa ativo
	 *
	 * @author thiago.borges
	 *
	 * @return
	 */
	MapaOrganizacional findMapaAtivo();
}

