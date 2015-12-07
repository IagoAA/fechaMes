package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;

public interface UsuarioOrganizacaoItemDao extends CitGenericDAO {

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
	List<UsuarioOrganizacaoItem> buscaOrganizacoesAtivasPorIdUsuario(Long idUsuario);}
