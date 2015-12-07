package br.com.centralit.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Localizacao;
import br.com.centralit.api.service.LocalizacaoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.view.ResultResponseVH;

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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 05/01/2015 - 16:05:49
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Controller
@RequestMapping("/rest/localizacao")
public class LocalizacaoController extends GenericController<Localizacao> {

	@Autowired
	private LocalizacaoService localizacaoService;

	public LocalizacaoController() {
		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param localizacaoService
	 */
	@Autowired
	public LocalizacaoController( LocalizacaoService localizacaoService ) {

		super(localizacaoService);
	}

	@Override
	public Class<Views.LocalizacaoListView> getEditView() {

		return Views.LocalizacaoListView.class;
	}

	@Override
	public Class<Views.LocalizacaoEditView> getListView() {

		return Views.LocalizacaoEditView.class;
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
	 * Método responsável por buscar a localizacao pelo nome
	 *
	 * @author renato.jesus
	 *
	 * @param nome
	 * @return
	 */
	@RequestMapping(value = "/findLocalizacaoPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findLocalizacaoPorNome(@RequestParam(value = "nome") String nome) {

		Collection<Localizacao> listaLocalizacao = localizacaoService.findLocalizacaoPorNome(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaLocalizacao, Views.LocalizacaoAutoCompleteSimplesView.class);

		return responseBody;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar Localizacao por nome e id do organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	@RequestMapping(value = "/listarLocalizacaoPorOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarLocalizacaoPorOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") String idOrganizacao) {

		ResultResponseVH<List<Localizacao>> resultResponseVH = new ResultResponseVH<List<Localizacao>>(localizacaoService.listarLocalizacaoPorOrganizacao(nome, Long.parseLong(idOrganizacao)));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.LocalizacaoAutoCompleteSimplesView.class);

		return responseBody;
	}
}
