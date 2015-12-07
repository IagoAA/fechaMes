package br.com.centralit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.MenuService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.json.Views.GenericView;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.Pagina;
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
 * <b>Title: MenuController</b>
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
 * @since 09/12/2014 - 17:40:22
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Controller
@RequestMapping("/rest/menu")
public class MenuController extends GenericController<Menu> {

	private final MenuService menuService;

	@Autowired
	public MenuController( final MenuService menuService ) {

		super(menuService);

		this.menuService = menuService;
	}

	@Override
	public Class<? extends Views.GenericView> getEditView() {

		return Views.MenuEditView.class;
	}

	@Override
	public Class<? extends GenericView> getListView() {

		return Views.MenuListView.class;
	}

	@RequestMapping(value = "/findByName", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findByName(@RequestParam(value = "nome") final String nome) {

		final ResultResponseVH<List<Menu>> resultResponseVH = new ResultResponseVH<List<Menu>>(this.menuService.findByName(nome));

		final ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.MenuListSearchFrontEndView.class);

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
	 * Método responsável por retornar todos os menus nivel 1
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllParent", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper getAllParent() {

		final ResultResponseVH<List<Menu>> resultResponseVH = new ResultResponseVH<List<Menu>>(this.menuService.getAllParent());

		final ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, this.getListView());

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
	 * Método responsável por retornar toda a estrutura de menu
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllMenusAtivos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper getAllMenusAtivos() {

		final ResultResponseVH<List<Menu>> resultResponseVH = new ResultResponseVH<List<Menu>>(this.menuService.getAllMenusAtivos());

		final ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.MenuListFrontEndView.class);

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
	 * Método responsável por buscar filhos pelo id do pai
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findMenuByIdParent", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findMenuByIdParent(@RequestParam("idParent") final Long idParent) {

		final ResultResponseVH<List<Menu>> resultResponseVH = new ResultResponseVH<List<Menu>>(this.menuService.findMenuByIdParent(idParent));

		final ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.MenuListChildrenView.class);

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
	 * @author renato.jesus
	 * 
	 * @param menuFile
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveOrdem")
	@ResponseBody
	public ResponseBodyWrapper saveOrdem(@RequestBody final List<Menu> menuList) {

		final ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.menuService.saveOrdem(menuList), this.getListView());

		return responseBody;
	}

	/**
	 * Método responsável por
	 * 
	 * @author Wilker
	 * 
	 * @param menuFile
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/buscarMenusPorPagina")
	@ResponseBody
	public ResponseBodyWrapper buscarMenusPorPagina(@RequestBody final Pagina pagina) {

		final ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.menuService.buscarMenusPorPagina(pagina), Views.BreadcrumbView.class);

		return responseBody;
	}

	@RequestMapping(value = "/obterPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper obterPorNome(@RequestParam(value = "nome") final String nome) {

		final ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.menuService.obterMenuCorClassePorNome(nome), Views.MenuEditView.class);

		return responseBody;
	}

}
