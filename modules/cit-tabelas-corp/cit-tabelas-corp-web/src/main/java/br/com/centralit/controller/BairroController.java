package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Bairro;
import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.service.BairroService;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/bairro")
public class BairroController extends GenericController<Bairro>{

	/** Atributo bairroService. */
	private BairroService bairroService;
	
	@Autowired
	public BairroController(BairroService bairroService) {
		
		super(bairroService);
		
		this.bairroService = bairroService;
		
	}
	
	/**
	 * Método responsável por listar a entidade <code>Bairro</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param cidade
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/findBairro")
	@ResponseBody
	public ResponseBodyWrapper listarBairro(@RequestBody EntidadeNomeBuscaVH<Cidade> cidade) {

		Collection<Bairro> listaBairro = this.bairroService.listarBairro(cidade);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaBairro, Views.BairroListView.class);

		return responseBody;
	}
	
	@Override
	public Class<Views.BairroEditView> getEditView() {

		return Views.BairroEditView.class;
	}

	@Override
	public Class<Views.BairroListView> getListView() {

		return Views.BairroListView.class;
	}
}
