package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Organizacao;

public interface OrganizacaoDao extends CitGenericDAO {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar Organizacao por nome
	 *
	 * @author geovane.filho
	 *
	 * @param nome
	 * @return
	 */
	List<Organizacao> listarOrganizacaoPorNome(String nome);
	
	boolean existeCodigoIgual(String codigo);

}
