package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.NotificacaoService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Filter;
import br.com.centralit.framework.model.Notificacao;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.view.GridVH;
import br.com.centralit.framework.view.ResultResponseVH;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

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
 * <b>Title:NotificacaoController </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 20/11/2014 - 10:01:53
 *
 * @version 1.0.0
 *
 * @author rodrigo.anaice
 *
 */
@Controller
@RequestMapping("/rest/notificacao")
public class NotificacaoController extends GenericController<Notificacao> {

	/** Atributo notificacaoService. */
	private NotificacaoService notificacaoService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param notificacaoService
	 */
	@Autowired
	public NotificacaoController( NotificacaoService notificacaoService ) {

		super(notificacaoService);

		this.notificacaoService = notificacaoService;

	}

	@RequestMapping(value = "/getPage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBodyWrapper findGrid(@RequestBody SearchParams searchParams) {

		Usuario usuarioLogado = (Usuario) usuarioService.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		searchParams.getFilters().add(new Filter("usuario.id", "numeric", usuarioLogado.getId().toString(), "eq"));

		SearchSeven search = new SearchSeven(searchParams, usuarioLogado.getOrganizacao().getId());

		search.setResultMode(Search.RESULT_MAP);

		@SuppressWarnings("rawtypes")
		SearchResult searchResult = genericService.searchAndCount(search);

		// DETERMINA QUAIS OS CAMPOS VAI CONSULTAR
		GridVH gridVH = new GridVH();
		gridVH.setObjects(searchResult.getResult());
		gridVH.addTotalItensTotalPages(searchParams, Long.valueOf(searchResult.getTotalCount()));

		@SuppressWarnings({ "rawtypes", "unchecked" })
		ResultResponseVH resultResponseVH = new ResultResponseVH(gridVH);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, this.getListView());

		return responseBody;
	}

	/**
	 * Método responsável por listar a entidade <code>Notificacao</code> através do ID do usuario
	 *
	 * @author rogerio.costa
	 *
	 * @return ResponseBodyWrapper/ ListaNotificacao
	 */
	@RequestMapping(value = "/findNotificacaoUsuario", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarNotificacaoUsuario() {

		Collection<Notificacao> listaNotificacao = this.notificacaoService.listarNotificacaoPorUsuario();

		ResultResponseVH<Collection<Notificacao>> resultResponseVH = new ResultResponseVH<Collection<Notificacao>>(listaNotificacao);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, getEditView());

		return responseBody;

	}

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
	 * @param objeto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/visualizar")
	@ResponseBody
	public ResponseBodyWrapper visualizar(@RequestBody Notificacao objeto) throws Exception {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(notificacaoService.visualizar(objeto), getEditView());

		return responseBody;
	}

	@Override
	public Class<Views.NotificacaoAutoCompleteView> getAutoCompleteView() {

		return Views.NotificacaoAutoCompleteView.class;
	}

	@Override
	public Class<Views.NotificacaoListView> getListView() {

		return Views.NotificacaoListView.class;
	}

	@Override
	public Class<Views.NotificacaoEditView> getEditView() {

		return Views.NotificacaoEditView.class;
	}

}
