package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.PaginaService;
import br.com.centralit.api.service.PaginaUsuarioService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.api.viewHelper.FavoritoVH;
import br.com.centralit.api.viewHelper.FiltroPaginaUsuarioVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.PaginaUsuario;
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
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 19/11/2014 - 10:34:49
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Controller
@RequestMapping("/rest/paginaUsuario")
public class PaginaUsuarioController extends GenericController<PaginaUsuario> {

	private final PaginaUsuarioService paginaUsuarioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PaginaService paginaService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param paginaUsuarioService
	 */
	@Autowired
	public PaginaUsuarioController( final PaginaUsuarioService paginaUsuarioService ) {

		super(paginaUsuarioService);

		this.paginaUsuarioService = paginaUsuarioService;
	}

	/**
	 * Método responsável por salvar o filtro do usuario
	 *
	 * @author wilker.machado
	 *
	 * @param filter
	 *
	 * @return Resposta ao client <code>ResponseBodyWrapper</code>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveFilter")
	@ResponseBody
	public ResponseBodyWrapper saveFilter(@RequestBody FiltroPaginaUsuarioVH filtroPaginaUsuario) {

		Usuario user = this.usuarioService.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.paginaUsuarioService.saveFilter(filtroPaginaUsuario.getSearchParams(), filtroPaginaUsuario.getPagina(), user), Views.GenericView.class);

		return responseBody;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por favoritar/desfavoritar a <code>PaginaUsuario</code>
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param favoritoVH
	 * @return <code>PaginaUsuario</code>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveFavorito")
	@ResponseBody
	public ResponseBodyWrapper saveFavorito(@RequestBody FavoritoVH favoritoVH) {

		Usuario user = this.usuarioService.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.paginaUsuarioService.saveFavorito(favoritoVH, user), this.getEditView());

		return responseBody;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por remover favorito por pagina de usuario
	 *
	 * @author iago.almeida
	 *
	 * @param idPaginaUsuario
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/removeFavoritoPaginaUsuario")
	@ResponseBody
	public ResponseBodyWrapper removeFavoritoPaginaUsuario(@RequestBody Long idPaginaUsuario) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.paginaUsuarioService.removeFavoritoPaginaUsuario(idPaginaUsuario), Views.GenericView.class);

		return responseBody;
	}

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
	@RequestMapping(method = RequestMethod.POST, value = "/favoritarPaginaUsuario")
	@ResponseBody
	public ResponseBodyWrapper favoritarPaginaUsuario(@RequestBody Long idPaginaUsuario) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.paginaUsuarioService.favoritarPaginaUsuario(idPaginaUsuario), Views.GenericView.class);

		return responseBody;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por trazer paginas de usuário ativas
	 *
	 * @author iago.almeida
	 *
	 * @param idUsuario
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/findPorIdUsuario")
	@ResponseBody
	public ResponseBodyWrapper findPorIdUsuario(@RequestBody Long idUsuario) {

		Collection<PaginaUsuario> listaPaginasUsuario = this.paginaUsuarioService.findPorIdUsuario(idUsuario);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaPaginasUsuario, Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findAjudaPagina")
	@ResponseBody
	public ResponseBodyWrapper findAjudaPagina(@RequestBody Long idPagina) {

		Pagina pagina = this.paginaService.find(idPagina);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(pagina, Views.PaginaAjudaView.class);

		return responseBody;
	}
}
