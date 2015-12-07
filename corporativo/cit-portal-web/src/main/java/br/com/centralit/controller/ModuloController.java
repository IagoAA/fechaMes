package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.ModuloService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Modulo;

@Controller
@RequestMapping("/rest/modulo")
public class ModuloController extends GenericController<Modulo>{

	private ModuloService moduloService;
	
	@Autowired
	public ModuloController(ModuloService moduloService) {
		
		super(moduloService);
		
		this.moduloService = moduloService;
	}
	
	@RequestMapping(value = "/obterModulosAtivos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper obterModulosAtivos() {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.moduloService.getModulosAtivos(), Views.DominioListView.class);

		return responseBody;
	}
}
