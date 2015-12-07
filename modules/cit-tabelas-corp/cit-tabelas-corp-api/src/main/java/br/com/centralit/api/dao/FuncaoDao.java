package br.com.centralit.api.dao;

import java.util.Collection;

import br.com.centralit.api.model.Funcao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;

public interface FuncaoDao extends CitGenericDAO {
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por buscar a funcao por nome e organizacao
	 *
	 * @author juliana.barbosa
	 *
	 * @param nome
	 * @param organizacaoId
	 * @return
	 */
	Collection<Funcao> findPorNomeEOrganizacao(String nome, Long organizacaoId);
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se existe função cadastrada com o mesmo nome
	 *
	 * @author juliana.barbosa
	 *
	 * @param nome, organizacaoId
	 * @return
	 */
	boolean verificaSeNomeEUnico(String nome, Long organizacaoId);
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se existe função cadastrada com o mesmo codigo
	 *
	 * @author juliana.barbosa
	 *
	 * @param codigo, organizacaoId
	 * @return
	 */
	boolean verificaSeCodigoEUnico(String codigo, Long organizacaoId);	
}
