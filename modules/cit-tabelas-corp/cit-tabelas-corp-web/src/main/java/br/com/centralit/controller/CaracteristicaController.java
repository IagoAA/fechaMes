package br.com.centralit.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Caracteristica;
import br.com.centralit.api.service.CaracteristicaService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.view.ResultResponseVH;

@Controller
@RequestMapping("/rest/caracteristica")
public class CaracteristicaController extends GenericController<Caracteristica> {

	private CaracteristicaService caracteristicaService;

	public CaracteristicaController() {
		super();
	}

	@Autowired
	public CaracteristicaController( CaracteristicaService caracteristicaService ) {

		super(caracteristicaService);

		this.caracteristicaService = caracteristicaService;
	}

	/**
	 * Método responsável por listar as entidades <code>Caracteristica</code> a partir de um filtro
	 *
	 * @author rogerio.cassimiro
	 *
	 * @return Collection<Caracteristica>
	 */
	@RequestMapping(value = "/listarCaracteristicas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarCaracteristicas(@RequestParam(value = "descricao") String descricao) {

		final Collection<Caracteristica> listaCaracteristica = this.caracteristicaService.listarCaracteristicas(descricao);

		//TODO Rever View
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaCaracteristica, Views.GenericView.class);

		return responseBody;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por listar Caracteristicas por nome e id do organizacao
	 *
	 * @author iago.almeida
	 *
	 * @param descricao
	 * @param idOrganizacao
	 * @return
	 */
	@RequestMapping(value = "/listarCaracteristicasPorOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarCaracteristicasPorOrganizacao(@RequestParam(value = "descricao") String descricao, @RequestParam(value = "idOrganizacao") String idOrganizacao) {

		ResultResponseVH<List<Caracteristica>> resultResponseVH = new ResultResponseVH<List<Caracteristica>>(caracteristicaService.listarCaracteristicasPorOrganizacao(descricao, Long.parseLong(idOrganizacao)));

		//TODO Rever View
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.GenericView.class);

		return responseBody;
	}

	@Override
	public Class<Views.CaracteristicaListView> getListView() {

		return Views.CaracteristicaListView.class;
	}

	@Override
	public Class<Views.CaracteristicaEditView> getEditView() {

		return Views.CaracteristicaEditView.class;
	}

}
