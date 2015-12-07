package br.com.centralit.api.service;

import java.util.List;

import br.com.centralit.api.viewHelper.FavoritoVH;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.PaginaUsuario;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.model.Usuario;
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
 * <b>Title: </b> Interface PaginaUsuarioService
 * </p>
 * 
 * <p>
 * <b>Description: </b> Interface Responsável pelas regras de negócio referente a <code>PaginaUsuario</code>
 * </p>
 * 
 * @since 19/11/2014 - 09:24:57
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
public interface PaginaUsuarioService extends GenericService<PaginaUsuario, Long> {

	/**
	 * Método responsável por Salvar filtros do usuario
	 * 
	 * @author wilker.machado
	 * 
	 * @param searchParams
	 * @param pageWorkspace
	 * @param usuario
	 * @return <code>SearchParams</code>
	 */
	public SearchParams saveFilter(SearchParams searchParams, Pagina pageWorkspace, Usuario usuario);

	/**
	 * Método responsável por salvar a página como favorito do usuário
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param favoritoVH
	 * @param usuario
	 * @return <code>vhFavorito</code>
	 */
	public FavoritoVH saveFavorito(FavoritoVH favoritoVH, Usuario usuario);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por remover favorito por pagina de usuario
	 *
	 * @author iago.almeida
	 *
	 * @param paginaUsuario
	 * @return
	 */
	public PaginaUsuario removeFavoritoPaginaUsuario(Long idPaginaUsuario);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por trazer paginas de usuário ativas
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 * @return
	 */
	public List<PaginaUsuario> findPorIdUsuario(Long idUsuario);

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por favoritar pagina de usuario
	 *
	 * @author iago.almeida
	 *
	 * @param idPaginaUsuario
	 * @return
	 */
	public PaginaUsuario favoritarPaginaUsuario(Long idPaginaUsuario);
	
}
