package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.framework.model.DefaultFile;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * <b>Title: DefaultFileService</b>
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
 * @since 09/12/2014 - 17:29:47
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
public interface DefaultFileService extends GenericService<DefaultFile, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por buscar todos os JS's ativos
	 *
	 * @author renato.jesus
	 *
	 * @return
	 */
	public List<DefaultFile> findAllJSAtivos();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por todos os CSS's ativos
	 *
	 * @author renato.jesus
	 *
	 * @return
	 */
	public List<DefaultFile> findAllCSSAtivos();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter todos JS ativos por módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<DefaultFile>
	 */
	List<DefaultFile> findAllJSAtivosPorModulosAtivos();

	List<DefaultFile> findAllCSSAtivosComModulosAtivos();
}
