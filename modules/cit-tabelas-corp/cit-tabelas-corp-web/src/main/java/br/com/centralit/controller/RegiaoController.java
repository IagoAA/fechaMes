package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Pais;
import br.com.centralit.api.model.Regiao;
import br.com.centralit.api.service.RegiaoService;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/regiao")
public class RegiaoController extends GenericController<Regiao>{
	
	/** Atributo regiaoService. */
	private RegiaoService regiaoService;

	@Autowired
	public RegiaoController(RegiaoService regiaoService) {
		
		super(regiaoService);
		
		this.regiaoService = regiaoService;
		
	}
	
	/**
	 * Método responsável por listar a entidade<code>Regiao</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @return Collection<Regiao>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/findRegiao")
	@ResponseBody
	public ResponseBodyWrapper listarRegiao(@RequestBody EntidadeNomeBuscaVH<Pais> paisNome) {

		final Collection<Regiao> listaRegiao = this.regiaoService.listarRegiao(paisNome.getObjeto(), paisNome.getNome());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaRegiao, Views.RegiaoListView.class);

		return responseBody;
	}
	
	@Override
	public Class<Views.RegiaoEditView> getEditView() {

		return Views.RegiaoEditView.class;
	}

	@Override
	public Class<Views.RegiaoListView> getListView() {

		return Views.RegiaoListView.class;
	}
}
