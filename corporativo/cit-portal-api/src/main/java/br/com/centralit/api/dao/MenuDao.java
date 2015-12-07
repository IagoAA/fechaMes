package br.com.centralit.api.dao;

import java.util.Collection;
import java.util.List;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.Usuario;

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
 * <b>Title: MenuDao</b>
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
 * @since 09/12/2014 - 17:40:56
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
public interface MenuDao extends CitGenericDAO {

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
	 * Método responsável por buscar menus conforme nome passado
	 * 
	 * @author renato.jesus
	 * @param idsPrivilegioUsuario
	 * @param idsGrupo
	 * 
	 * @return
	 */
	public List<Menu> findByName(String nome, Collection<Long> idsGrupo, Collection<Long> idsPrivilegioUsuario);

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
	 * Método responsável por carregar menus da tela com suas estruturas por módulos ativos
	 * 
	 * @author iago.almeida
	 * 
	 * @param usuario
	 * @param idsGrupo
	 * @param idsPrivilegioUsuario
	 * 
	 * @return List<Menu>
	 */
	List<Menu> getAllMenusAtivos(Usuario usuario, Collection<Long> idsGrupo, Collection<Long> idsPrivilegioUsuario);

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
	 * Método responsável por obter Menu de módulos inativos
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param baseUrlModulosInativos
	 * @return List<Menu>
	 */
	List<Menu> findMenuModulosInativos(List<String> baseUrlModulosInativos);
	
	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por obter Menu por chave
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param chave
	 * @return Menu
	 */
	Menu obterMenuPorChave(String chave);

}
