package br.com.centralit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.MenuFileService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.json.Views.GenericView;
import br.com.centralit.framework.model.MenuFile;
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
 * @since 08/07/2015 - 16:38:26
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Controller
@RequestMapping("/rest/menuFile")
public class MenuFileController extends GenericController<MenuFile> {

	private MenuFileService menuFileService;

	@Autowired
	public MenuFileController( MenuFileService menuFileService ) {

		super(menuFileService);

		this.menuFileService = menuFileService;
	}

	@Override
	public Class<? extends GenericView> getListView() {

		return Views.MenuFileListView.class;
	}

	@Override
	public Class<? extends GenericView> getEditView() {

		return Views.MenuFileEditView.class;
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
	 * @param idParent
	 * @return
	 */
	@RequestMapping(value = "/findByIdMenu", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findByIdMenu(@RequestParam("idMenu") Long idMenu) {

		ResultResponseVH<List<MenuFile>> resultResponseVH = new ResultResponseVH<List<MenuFile>>(this.menuFileService.findByIdMenu(idMenu));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, this.getListView());

		return responseBody;
	}
}
