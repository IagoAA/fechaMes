package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Estado;
import br.com.centralit.api.model.Regiao;
import br.com.centralit.api.service.EstadoService;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/estado")
public class EstadoController extends GenericController<Estado>{
	
	/** Atributo estadoService. */
	private EstadoService estadoService;
	
	@Autowired
	public EstadoController(EstadoService estadoService) {
		
		super(estadoService);
		
		this.estadoService = estadoService;
		
	}

	/**
	 * Método responsável por listar a entidade<code>Estado</code>
	 * 
	 * @author rogerio.costa
	 * 
	 * @return Collection<Regiao>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/findEstado")
	@ResponseBody
	public ResponseBodyWrapper listarEstado(@RequestBody EntidadeNomeBuscaVH<Regiao> estadoNome) {

		final Collection<Estado> listaEstado = this.estadoService.listarEstado(estadoNome.getObjeto(), estadoNome.getNome());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaEstado, Views.EstadoListView.class);

		return responseBody;
	}
	
	@Override
	public Class<Views.EstadoEditView> getEditView() {

		return Views.EstadoEditView.class;
	}

	@Override
	public Class<Views.EstadoListView> getListView() {

		return Views.EstadoListView.class;
	}

}
