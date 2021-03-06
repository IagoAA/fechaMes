package br.com.centralit.api.dao;

import java.util.Collection;
import java.util.List;

import br.com.centralit.api.model.Localizacao;
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
 * <b>Title: LocalizacaoDao</b>
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
 * @since 12/12/2014 - 16:45:14
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
public interface LocalizacaoDao extends CitGenericDAO {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por buscar a localizacao pelo nome
	 * 
	 * @author renato.jesus
	 * 
	 * @param nome
	 * @return
	 */
	public Collection<Localizacao> findLocalizacaoPorNome(String nome);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por listar Localizacao por nome e id da organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	public List<Localizacao> listarLocalizacaoPorOrganizacao(String nome, Long idOrganizacao);
}
