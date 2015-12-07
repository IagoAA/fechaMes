package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.DominioService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.util.UtilObjeto;
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
 * <b>Title: </b>DonimioController
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * <p>
 *
 * @since 05/12/2014 - 10:20:13
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Controller
@RequestMapping("/rest/dominio")
public class DominioController extends GenericController<Dominio> {

	/** Atributo dominioService. */
	private DominioService dominioService;

	@Autowired
	public DominioController( DominioService dominioService ) {

		super(dominioService);

		this.dominioService = dominioService;

	}

	@RequestMapping(value = "/findAllDominio", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findAllDominio(@RequestParam(value = "chave") String chave) {

		Collection<Dominio> listaTipoPrioridade = this.dominioService.listarPorChave(chave);

		ResultResponseVH<Collection<Dominio>> resultResponseVH = new ResultResponseVH<Collection<Dominio>>(listaTipoPrioridade);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.DominioListView.class);

		return responseBody;
	}

	@RequestMapping(value = "/findAllChavesDominio", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findAllChavesDominio() {

		Collection<String> listaChaves = this.dominioService.listarChavesExistentes();

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaChaves, Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(value = "/findAllDominioByValue", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findAllDominioByValue(@RequestParam(value = "chave") String chave, @RequestParam(value = "value") String valor) {

		Collection<Dominio> listaTipoPrioridade = this.dominioService.listarPorChaveValor(chave, valor);

		ResultResponseVH<Collection<Dominio>> resultResponseVH = new ResultResponseVH<Collection<Dominio>>(listaTipoPrioridade);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(value = "/findAllDominioByCodigo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findAllDominioByCodigo(@RequestParam(value = "chave") String chave, @RequestParam(value = "codigo") Long codigo) {

		Dominio dominio = this.dominioService.findByChaveAndCodigo(chave, codigo);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(dominio, Views.GenericView.class);

		return responseBody;
	}

	@RequestMapping(value = "/buscaDominioByCodigo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper buscaDominioByCodigo(@RequestParam(value = "codigo") Long codigo) {

		Dominio dominio = dominioService.buscaDominioByCodigo(codigo);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(UtilObjeto.isReferencia(dominio) ? dominio : new Dominio(), Views.GenericView.class);

		return responseBody;
	}
	
	@RequestMapping(value = "/buscaDominioByCodigoAndChave", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper buscaDominioByCodigoAndChave(@RequestParam(value = "chave") String chave, @RequestParam(value = "codigo") Long codigo) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(dominioService.findByChaveAndCodigo(chave, codigo), Views.GenericView.class);

		return responseBody;
	}
	
	@RequestMapping(value = "/validaCodigoDominioPorChave", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper validaCodigoDominioPorChave(@RequestParam(value = "chave") String chave, @RequestParam(value = "codigo") Long codigo) {

		boolean codigoExitente = dominioService.validaCodigoDominioPorChave(chave, codigo);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(codigoExitente, Views.GenericView.class);

		return responseBody;
	}

	@Override
	public Class<Views.DominioEditView> getEditView() {

		return Views.DominioEditView.class;
	}

	@Override
	public Class<Views.DominioListView> getListView() {

		return Views.DominioListView.class;
	}
}
