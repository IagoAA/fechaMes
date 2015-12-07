package br.com.centralit.api.dao;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;

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
 * @since 22/06/2015 - 15:51:43
 * 
 * @version 1.0.0
 * 
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 * 
 */
public interface NivelAutoridadeDao extends CitGenericDAO {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por verificar se o nome do nivel de autoridade será unico
	 * 
	 * @author renato.jesus
	 * 
	 * @param nome, organizacaoId
	 * @return
	 */
	boolean verificaSeNomeIsUnico(String nome, Long organizacaoId);
	
	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">594</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por verificar a existencia de nivel de autoridade com a hierarquia
	 * 
	 * @author juliana.barbosa
	 * 
	 * @param hierarquia, organizacaoId
	 * @return
	 */	
	boolean existeHierarquiaNivelAutoridade(Integer hierarquia, Long organizacaoId);
}
