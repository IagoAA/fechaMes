package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.service.arquitetura.GenericService;

public interface OrganizacaoService extends GenericService<Organizacao, Long> {

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

}
