package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.DefaultFile;

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
 * <b>Title: DefaultFileDao</b>
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
 * @since 09/12/2014 - 17:24:45
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
public interface DefaultFileDao extends CitGenericDAO {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por buscar todos os arquivos ativos
	 *
	 * @author renato.jesus
	 *
	 * @return
	 */
	public List<DefaultFile> findAllAtivos();

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
	 * Método responsável por buscar todos os CSS's ativos
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
	 * Método responsável por obter ids dos DefaultFile de módulos inativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param baseUrlModulosInativos
	 * @return List<Long>
	 */
	List<Long> findIdsDefaultFileModulosInativos(List<String> baseUrlModulosInativos);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por buscar todos js ativos de módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idDefaultFileInativosPorModulo
	 *
	 * @return List<DefaultFile>
	 */
	List<DefaultFile> findAllJSAtivosPorModulosAtivos(List<Long> idDefaultFileInativosPorModulo);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por
	 *
	 * @author gilberto.nery
	 *
	 * @param idDefaultFileInativosPorModulo
	 * @return
	 */
	List<DefaultFile> findAllCSSAtivosPorModulosAtivos(List<Long> idDefaultFileInativosPorModulo);

}
