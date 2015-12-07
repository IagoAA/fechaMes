package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.framework.model.UsuarioOrganizacaoItem;
import br.com.centralit.framework.service.arquitetura.GenericService;

public interface UsuarioOrganizacaoItemService extends GenericService<UsuarioOrganizacaoItem, Long> {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar osrgaos ativos por id usuario
	 *
	 * @author thiago.borges
	 *
	 * @param idUsuario
	 * @return
	 */
	List<UsuarioOrganizacaoItem> buscaOrganizacoesAtivasPorIdUsuario(Long idUsuario);

}
