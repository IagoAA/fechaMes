package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.MapaOrganizacional;
import br.com.centralit.api.service.MapaOrganizacionalService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.util.UtilObjeto;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 10/12/2014 - 15:53:49
 *
 * @version 1.0.0
 *
 * @author david.silva
 *
 */
@Controller
@RequestMapping("/rest/mapaOrganizacional")
public class MapaOrganizacionalController extends GenericController<MapaOrganizacional>{

	private MapaOrganizacionalService mapaOrganizacionalService;

	public MapaOrganizacionalController() {
		super();
	}

	@Override
	public Class<? extends Views.GenericView> getEditView() {

		return Views.MapaOrganizacionalEditView.class;

	}

	@Override
	public Class<? extends Views.GenericView> getListView() {

		return Views.MapaOrganizacionalListView.class;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param mapaOrganizacionalService
	 */
	@Autowired
	public MapaOrganizacionalController(MapaOrganizacionalService mapaOrganizacionalService) {
		super(mapaOrganizacionalService);
		this.mapaOrganizacionalService = mapaOrganizacionalService;
	}

	@RequestMapping(value = "/findUltimoMapaFechado", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findUltimoMapaFechado() {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(mapaOrganizacionalService.findUltimoMapaFechado(), Views.MapaOrganizacionalListView.class);

		return responseBody;
	}

	@RequestMapping(value = "/findMapaAtivo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findMapaAtivo() {

		MapaOrganizacional mapaOrganizacional = mapaOrganizacionalService.findMapaAtivo();
		
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(UtilObjeto.isReferencia(mapaOrganizacional) ? mapaOrganizacional : new MapaOrganizacional(), Views.MapaOrganizacionalListView.class);

		return responseBody;
	}
}
