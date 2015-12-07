package br.com.centralit.api.service;

import java.util.Collection;
import java.util.List;

import br.com.centralit.api.model.Caracteristica;
import br.com.centralit.framework.service.arquitetura.GenericService;

public interface CaracteristicaService extends GenericService<Caracteristica, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar as entidades <code>Caracteristica</code> a partir de um filtro
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param descricao
	 * @return Collection<Caracteristica>
	 */
	Collection<Caracteristica> listarCaracteristicas(String descricao);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar Caracteristicas por nome e id da organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param descricao
	 * @param idOrganizacao
	 * @return
	 */
	List<Caracteristica> listarCaracteristicasPorOrganizacao(String descricao, Long idOrganizacao);
}
