package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Caixa;
import br.com.centralit.api.model.Luck;
import br.com.centralit.api.service.CaixaService;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/caixa")
public class CaixaController extends GenericController<Caixa>{

	/** Atributo caixaService. */
	private CaixaService caixaService;

	@Autowired
	public CaixaController(CaixaService caixaService) {

		super(caixaService);

		this.caixaService = caixaService;

	}

	/**
	 * Método responsável por listar a entidade<code>Caixa</code>
	 *
	 * @author iago
	 *
	 * @return Collection<Caixa>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/findCaixa")
	@ResponseBody
	public ResponseBodyWrapper listarCaixa(@RequestBody EntidadeNomeBuscaVH<Luck> luckNome) {

		final Collection<Caixa> listaCaixa = this.caixaService.listarCaixa(luckNome.getObjeto(), luckNome.getNome());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaCaixa, Views.CaixaListView.class);

		return responseBody;
	}

	@Override
	public Class<Views.CaixaEditView> getEditView() {

		return Views.CaixaEditView.class;
	}

	@Override
	public Class<Views.CaixaListView> getListView() {

		return Views.CaixaListView.class;
	}
}
