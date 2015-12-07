package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.service.arquitetura.GenericService;

public interface ModuloService extends GenericService<Modulo, Long> {

	List<Modulo> getModulosAtivos();

	Modulo moduloEstaAtivo(String string);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de neg�cio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * M�todo respons�vel por obter m�dulo por baseUrl
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param baseUrl
	 * @return Modulo
	 */
	Modulo getModuloPorBaseUrl(String baseUrl);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter a baseUrl dos módulos inativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<String>
	 */
	List<String> getBaseUrlModulosInativos();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter os id dos módulos ativos
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return List<Long>
	 */
	List<Long> getIdModulosAtivos();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por obter a baseUrl dos módulos ativos
	 *
	 * @author iago.almeida
	 *
	 * @return List<String>
	 */
	List<String> getBaseUrlModulosAtivos();
}
