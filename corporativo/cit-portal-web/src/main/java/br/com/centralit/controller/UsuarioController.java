package br.com.centralit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.PaginaUsuario;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.util.UtilObjeto;

@Controller
@RequestMapping("/rest/usuario")
public class UsuarioController extends GenericController<Usuario> {

	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController( UsuarioService usuarioService ) {

		super(usuarioService);

		this.usuarioService = usuarioService;
	}

	@RequestMapping(value = "/buscaUsuarioByUsername", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper buscaUsuarioByUsername(@RequestParam(value = "username") String username) {

		Usuario usuario = this.usuarioService.buscaUsuarioByUsername(username);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(UtilObjeto.isReferencia(usuario) ? usuario : new Usuario(), Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(value = "/buscaUsuarioByEmail", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper buscaUsuarioByEmail(@RequestParam(value = "email") String email) {

		Usuario usuario = this.usuarioService.buscaUsuarioByEmail(email);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(UtilObjeto.isReferencia(usuario) ? usuario : new Usuario(), Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeFavorito")
	@ResponseBody
	public ResponseBodyWrapper removeFavorito(@RequestBody PaginaUsuario paginaUsuario) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.usuarioService.removeFavorito(paginaUsuario), Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeFiltro")
	@ResponseBody
	public ResponseBodyWrapper removeFiltro(@RequestBody SearchParams searchParams) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.usuarioService.removeFiltro(searchParams), Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(value = "/getUsuarioLogado", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBodyWrapper getUsuarioLogado() {

		Usuario user =  usuarioService.getReference(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(user, Views.UsuarioLogadoView.class);

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/atualizarOrganizacaoUsuario")
	@ResponseBody
	public ResponseBodyWrapper atualizarOrganizacaoUsuario(@RequestBody Organizacao organizacao) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.usuarioService.atualizarOrganizacaoUsuario(organizacao), Views.UsuarioLogadoView.class);

		return responseBody;
	}

	/**
	 * Método responsável por listar usuários por nome
	 *
	 * @author carlos.alberto
	 *
	 * @return Collection<Flow>
	 */
	@RequestMapping(value = "/findByUsername", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findByUsername(@RequestParam(value = "username") String username) {

		List<Usuario> usuarios = this.usuarioService.findByUsername(username);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(usuarios, Views.UsuarioEditView.class);

		return responseBody;
	}


	@RequestMapping(value = "/quantidadeUsuarioPorGrupo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper quantidadeUsuarioPorGrupo(@RequestParam( value = "idGrupo") Long idGrupo) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.usuarioService.quantidadeUsarioPorGrupo(idGrupo), this.getEditView());

		return responseBody;
	}

	@Override
	public Class<Views.UsuarioListView> getListView() {

		return Views.UsuarioListView.class;
	}

	@Override
	public Class<Views.UsuarioEditView> getEditView() {

		return Views.UsuarioEditView.class;
	}
}