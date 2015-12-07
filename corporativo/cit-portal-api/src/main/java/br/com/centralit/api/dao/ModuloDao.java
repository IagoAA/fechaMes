package br.com.centralit.api.dao;

import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Modulo;

public interface ModuloDao extends CitGenericDAO {

	boolean existeNomeModulo(Modulo entity);

	List<Modulo> getModulosAtivos();

	Modulo moduloEstaAtivo(String baseUrl);

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
	 * Método responsável por obter os Modulo inativos
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @return List<Modulo>
	 */
	List<Modulo> getModulosInativos();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por obter módulo por nome
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param nome
	 * 
	 * @return Modulo
	 */
	Modulo getModuloPorNome(String nome);
}
