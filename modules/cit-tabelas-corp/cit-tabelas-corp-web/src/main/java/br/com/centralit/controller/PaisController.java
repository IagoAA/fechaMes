package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Pais;
import br.com.centralit.api.service.PaisService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/pais")
public class PaisController extends GenericController<Pais>{
	
	/** Atributo paisService. */
	private PaisService paisService;
	
	@Autowired
	public PaisController(PaisService paisService) {
		
		super(paisService);
		
		this.paisService = paisService;
		
	}

	/**
	 * Método responsável por listar a entidade<code>Pais</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @return Collection<Regiao>
	 */
	@RequestMapping(value = "/listarPais", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarPais(@RequestParam(value = "nome") String nome) {

		final Collection<Pais> listaPais = this.paisService.listarPais(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaPais, Views.PaisListView.class);

		return responseBody;
	}
	
	@Override
	public Class<Views.PaisEditView> getEditView() {

		return Views.PaisEditView.class;
	}

	@Override
	public Class<Views.PaisListView> getListView() {

		return Views.PaisListView.class;
	}
}
