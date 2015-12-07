package br.com.centralit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.EstruturaOrganizacionalService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.OrganizacaoService;
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
 * <b>Title: EstruturaOrganizacionalController</b>
 * </p>
 *
 * <p>
 * <b>Description: Atende as requisições rest de estrutura organizacional</b>
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
 * @since 10/12/2014 - 15:44:01
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Controller
@RequestMapping("/rest/estruturaOrganizacional")
public class EstruturaOrganizacionalController extends GenericController<EstruturaOrganizacional> {

	private EstruturaOrganizacionalService estruturaOrganizacionalService;

	@Autowired
	private OrganizacaoService organizacaoService;

	@Autowired
	private DominioService dominioService;

	@Autowired
	private ModuloService moduloService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param estruturaOrganizacionalService
	 */
	@Autowired
	public EstruturaOrganizacionalController( @Qualifier("estruturaOrganizacionalService") EstruturaOrganizacionalService estruturaOrganizacionalService ) {

		super(estruturaOrganizacionalService);

		this.estruturaOrganizacionalService = estruturaOrganizacionalService;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends Views.GenericView> getEditView() {

		return Views.EstruturaOrganizacionalEditView.class;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends Views.GenericView> getListView() {

		return Views.EstruturaOrganizacionalListView.class;

	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public ResponseBodyWrapper update(@RequestBody EstruturaOrganizacional estruturaOrganizacional) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(estruturaOrganizacionalService.save(estruturaOrganizacional), getEditView());

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
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/contemEntidadeVinculada", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper contemEntidadeVinculada(@RequestParam(value = "id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.estruturaOrganizacionalService.contemEntidadeVinculada(id), getEditView());

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
	 * Método responsável por buscar todas as estruturas organizacionais nivel 0 (zero)
	 *
	 * @author renato.jesus
	 *
	 * @return
	 */
	@RequestMapping(value = "/findParents", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findParents(@RequestParam(value = "idOrganizacao") Long idOrganizacao) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.findParents(idOrganizacao));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, getEditView());

		return responseBody;
	}

	@RequestMapping(value = "/listarEstruturasOrganizacionaisBusca", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEstruturasOrganizacionaisBusca(@RequestParam(value = "nome") String nome) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.listarEstruturasOrganizacionais(nome));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, getEditView());

		return responseBody;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar estruturas organizacionais por nome usando View simples
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @return List<EstruturaOrganizacional>
	 */
	@RequestMapping(value = "/listarEstruturasOrganizacionaisBuscaSimples", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEstruturasOrganizacionaisBuscaSimples(@RequestParam(value = "nome") String nome) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.listarEstruturasOrganizacionais(nome));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.EstruturaOrganizacionalAutoCompleteView.class);

		return responseBody;
	}

	/**
	 * Método responsável por listar <code>EstruturaOrganizacional</code> por nome e uma <code>Organizacao</code>
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return <code>ResponseBodyWrapper</code>
	 */
	@RequestMapping(value = "/listarEstruturasOrganizacionaisPorOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEstruturasOrganizacionaisPorOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") String idOrganizacao) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.listarEstruturasOrganizacionaisPorOrganizacao(nome, Long.parseLong(idOrganizacao)));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.EstruturaOrganizacionalAutoCompleteSimplesView.class);

		return responseBody;
	}

	/**
	 * Método responsável por listar estruturas organizacionais filhas por organização
	 *
	 * @author wilker.machado
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param idEstruturaPai
	 * @return responseBody
	 */
	@RequestMapping(value = "/listarEstruturasOrganizacionaisFilhasPorOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEstruturasOrganizacionaisFilhasPorOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") String idOrganizacao) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.listarEstruturasOrganizacionaisFilhasPorOrganizacao(nome, Long.parseLong(idOrganizacao)));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.EstruturaOrganizacionalAutoCompleteSimplesView.class);

		return responseBody;
	}

	/**
	 * Método responsável por listar estruturas organizacionais na tree por nome
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @return responseBody
	 */
	@RequestMapping(value = "/listarEstruturasPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEstruturasPorNome(@RequestParam(value = "nome") String nome) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.listarEstruturasOrganizacionaisPorNomeTree(nome));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, getEditView());

		return responseBody;
	}

	/**
	 * Método responsável por listar estruturas organizacionais da tree por pelo id da estrutura selecionada
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param exibirEstruturasAtivas
	 * @return responseBody
	 */
	@RequestMapping(value = "/listarEstruturasOrganizacionaisDaTree", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEstruturasOrganizacionaisDaTree(@RequestParam(value = "idOrganizacao") Long idOrganizacao, @RequestParam(value = "nome") String nome, @RequestParam(value = "exibirEstruturasAtivas") Boolean exibirEstruturasAtivas) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.listarEstruturasOrganizacionaisDaTree(idOrganizacao, nome, exibirEstruturasAtivas));

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
	 * Método responsável por buscar todas as estruturas organizacionais filhas
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param idOrganizacao
	 * @param exibirEstruturasAtivas
	 * @return List<EstruturaOrganizacional>
	 */
	@RequestMapping(value = "/findChildrens", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findChildrens(@RequestParam(value = "idOrganizacao") Long idOrganizacao, @RequestParam(value = "exibirEstruturasAtivas") Boolean exibirEstruturasAtivas) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.findChildrens(idOrganizacao, exibirEstruturasAtivas));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, getEditView());

		return responseBody;
	}

	/**
	 * Método responsável por listar <code>EstruturaOrganizacional</code> que são loja por nome e uma <code>Organizacao</code>
	 *
	 * @author iago
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return <code>ResponseBodyWrapper</code>
	 */
	@RequestMapping(value = "/listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") String idOrganizacao) {

		ResultResponseVH<List<EstruturaOrganizacional>> resultResponseVH = new ResultResponseVH<List<EstruturaOrganizacional>>(estruturaOrganizacionalService.listarEstruturasOrganizacionaisLojaPorNomeEOrganizacao(nome, Long.parseLong(idOrganizacao)));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.EstruturaOrganizacionalAutoCompleteSimplesView.class);

		return responseBody;
	}

}