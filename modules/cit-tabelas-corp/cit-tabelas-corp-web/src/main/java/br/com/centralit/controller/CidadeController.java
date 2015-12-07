package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.model.Estado;
import br.com.centralit.api.service.CidadeService;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/cidade")
public class CidadeController extends GenericController<Cidade>{

	/** Atributo cidadeService. */
	private CidadeService cidadeService;
	
	@Autowired
	public CidadeController(CidadeService cidadeService) {
		
		super(cidadeService);
		
		this.cidadeService = cidadeService;
	}
	
	/**
	 * Método responsável por listar a entidade<code>Cidade</code>
	 * 
	 * @author iago.almeida
	 * 
	 * @return Collection<Regiao>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/findCidade")
	@ResponseBody
	public ResponseBodyWrapper listarCidades(@RequestBody EntidadeNomeBuscaVH<Estado> estadoRegiaoPais) {

		Collection<Cidade> listaCidades = this.cidadeService.listarCidades(estadoRegiaoPais);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaCidades, Views.CidadeListView.class);

		return responseBody;
	}
	
	@Override
	public Class<Views.CidadeEditView> getEditView() {

		return Views.CidadeEditView.class;
	}

	@Override
	public Class<Views.CidadeListView> getListView() {

		return Views.CidadeListView.class;
	}
}
