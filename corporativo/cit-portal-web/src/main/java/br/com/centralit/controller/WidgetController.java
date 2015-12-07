package br.com.centralit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.WidgetService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.json.Views.GenericView;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Widget;
import br.com.centralit.framework.util.UtilObjeto;

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
 * @since 10/03/2015 - 11:34:30
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Controller
@RequestMapping("/rest/widget")
public class WidgetController extends GenericController<Widget> {

	@Autowired
	DominioService dominioService;

	@Autowired
	WidgetService widgetService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 */
	@Autowired
	public WidgetController( WidgetService widgetService ) {

		super(widgetService);
	}

	@RequestMapping(value = "/buscaWidgetByNomeTipo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper buscaWidgetByNomeTipo(@RequestParam(value = "nomeTipo") String nomeTipo) {

		Dominio tipoComponente = dominioService.findByChaveAndNome(Dominio.TIPO_WIDGET, nomeTipo);

		Widget widget = widgetService.findPorTipo(tipoComponente.getId());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(UtilObjeto.isReferencia(widget) ? widget : new Widget(), getEditView());

		return responseBody;
	}

	@RequestMapping(value = "/exemploPizza", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper exemploPizza() {
		Map<String, Object> exemplo = new HashMap<String, Object>();
		Map<String, Object> serie = new HashMap<String, Object>();
		Map<String, Object> serie2 = new HashMap<String, Object>();
		Map<String, Object> serie3 = new HashMap<String, Object>();
		serie.put("name", "Joao");
		serie.put("value", 5);
		serie2.put("name", "Maria");
		serie2.put("value", 2);
		serie3.put("name", "Pedro");
		serie3.put("value", 17);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(serie);
		data.add(serie2);
		data.add(serie3);

		Map<String, Object> metaData = new HashMap<String, Object>();
		metaData.put("status", 200);

		exemplo.put("data", data);
		exemplo.put("meta", metaData);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(exemplo, getEditView());

		return responseBody;
	}

	@RequestMapping(value = "/exemplo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper exemplo() {
		List<String> categories = new ArrayList<String>();
		categories.add("Jan");
		categories.add("Fev");
		categories.add("Mar");
		categories.add("Abr");
		categories.add("Mai");
		categories.add("Jun");
		categories.add("Jul");
		categories.add("Ago");
		categories.add("Set");
		categories.add("Out");
		categories.add("Nov");
		categories.add("Dez");

		List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();

		Map<String, Object> exemplo = new HashMap<String, Object>();
		Map<String, Object> serie = new HashMap<String, Object>();
		Map<String, Object> serie2 = new HashMap<String, Object>();
		Map<String, Object> serie3 = new HashMap<String, Object>();
		serie.put("name", "Joao");
		List<Object> salariosJoao = new ArrayList<Object>();
		salariosJoao.add(5000);
		salariosJoao.add(4500);
		salariosJoao.add(5150);
		salariosJoao.add(5045);
		salariosJoao.add(4952);
		salariosJoao.add(5245.50);
		salariosJoao.add(4890);
		salariosJoao.add(4250);
		salariosJoao.add(4900.40);
		salariosJoao.add(5150);
		salariosJoao.add(4859);
		salariosJoao.add(5000);
		serie.put("data", salariosJoao);

		serie2.put("name", "Maria");
		List<Object> salariosMaria = new ArrayList<Object>();
		salariosMaria.add(2000);
		salariosMaria.add(5500);
		salariosMaria.add(2150);
		salariosMaria.add(2045);
		salariosMaria.add(8952);
		salariosMaria.add(1245.50);
		salariosMaria.add(1890);
		salariosMaria.add(6250);
		salariosMaria.add(1900.40);
		salariosMaria.add(2150);
		salariosMaria.add(7859);
		salariosMaria.add(2000);
		serie2.put("data", salariosMaria);

		serie3.put("name", "Pedro");
		List<Object> salariosPedro = new ArrayList<Object>();
		salariosPedro.add(7000);
		salariosPedro.add(3500);
		salariosPedro.add(7150);
		salariosPedro.add(7045);
		salariosPedro.add(6952);
		salariosPedro.add(7245.50);
		salariosPedro.add(6890);
		salariosPedro.add(6250);
		salariosPedro.add(7900.40);
		salariosPedro.add(5150);
		salariosPedro.add(9859);
		salariosPedro.add(11000);
		serie3.put("data", salariosPedro);

		series.add(serie);
		series.add(serie2);
		series.add(serie3);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("categories", categories);
		data.put("series", series);
		data.put("tipoDado", "Salário");

		Map<String, Object> metaData = new HashMap<String, Object>();
		metaData.put("status", 200);

		exemplo.put("data", data);
		exemplo.put("meta", metaData);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(exemplo, getEditView());

		return responseBody;
	}

	@RequestMapping(value = "/exemplo2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper exemplo2() {

		Map<String, Object> exemplo = new HashMap<String, Object>();
		Map<String, Object> info = new HashMap<String, Object>();
		Map<String, Object> info2 = new HashMap<String, Object>();
		Map<String, Object> info3 = new HashMap<String, Object>();
		Map<String, Object> info4 = new HashMap<String, Object>();
		Map<String, Object> info5 = new HashMap<String, Object>();
		Map<String, Object> info6 = new HashMap<String, Object>();
		Map<String, Object> info7 = new HashMap<String, Object>();
		info.put("name", "Gabinete");
		info.put("value", 45);
		info2.put("name", "Teclado");
		info2.put("value", 40);
		info3.put("name", "Mouse");
		info3.put("value", 40);
		info4.put("name", "Monitor");
		info4.put("value", 45);
		info5.put("name", "Mousepad");
		info5.put("value", 15);
		info6.put("name", "Estabilizador");
		info6.put("value", 15);
		info7.put("name", "Fone de ouvido");
		info7.put("value", 2);

		List<Map<String, Object>> detalhes = new ArrayList<Map<String, Object>>();
		detalhes.add(info);
		detalhes.add(info2);
		detalhes.add(info3);
		detalhes.add(info4);
		detalhes.add(info5);
		detalhes.add(info6);
		detalhes.add(info7);

		Map<String, Object> metaData = new HashMap<String, Object>();
		metaData.put("status", 200);

		exemplo.put("data", detalhes);
		exemplo.put("meta", metaData);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(exemplo, getEditView());

		return responseBody;
	}

	@RequestMapping(value = "/exemplo3", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper exemplo3() {

		Map<String, Object> exemplo = new HashMap<String, Object>();
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("message", "Não foram encontrados dados para os parametros informados!");


		Map<String, Object> metaData = new HashMap<String, Object>();
		metaData.put("status", 500);

		exemplo.put("data", info);
		exemplo.put("meta", metaData);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(exemplo, getEditView());

		return responseBody;
	}

	@Override
	public Class<? extends GenericView> getEditView() {

		return Views.WidgetEditView.class;
	}

	@Override
	public Class<? extends GenericView> getListView() {

		return Views.WidgetListView.class;
	}
}
