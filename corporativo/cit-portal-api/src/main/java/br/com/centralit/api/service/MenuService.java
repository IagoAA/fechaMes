package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.Pagina;
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
 * <b>Title: MenuService</b>
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
 * @since 09/12/2014 - 17:42:16
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
public interface MenuService extends GenericService<Menu, Long> {

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 */
	public List<Menu> findParent();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por buscar filhos pelo id do pai
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 */
	public List<Menu> findMenuByIdParent(Long idParent);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por buscar menus conforme nome passado
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 */
	public List<Menu> findByName(String nome);

	public List<Modulo> findModulosAtivo();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param nome
	 * @return
	 */
	Menu findPorNome(String nome);

	/**
	 * Método responsável por
	 * 
	 * @author wilker.machado
	 * 
	 * @param menu
	 * @return
	 */
	Menu mergeSimples(Menu menu);

	/**
	 * Método responsável por
	 * 
	 * @author Wilker
	 * 
	 * @param pagina
	 * @return
	 */
	Menu buscarMenusPorPagina(Pagina pagina);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por retornar todos os menus principais ativos ou não
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 */
	List<Menu> getAllParent();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por retornar toda a estrutura de menu
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 */
	List<Menu> getAllMenusAtivos();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 * @param menuList
	 * @return
	 */
	List<Menu> saveOrdem(List<Menu> menuList);

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por obter todos os Menu que não possuem parent e que são de módulos ativos
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @return List<Menu>
	 */
	List<Menu> findMenuPorModulosAtivos();

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param nome
	 * @return
	 */
	Menu obterMenuCorClassePorNome(String nome);
}
