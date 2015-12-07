package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Funcao;
import br.com.centralit.api.service.FuncaoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/funcao")
public class FuncaoController extends GenericController<Funcao>{

	/** Atributo funcaoService. */
	private FuncaoService funcaoService;

	public FuncaoController() {
		super();
	}

	@Autowired
	public FuncaoController(FuncaoService funcaoService) {
		super(funcaoService);

		this.funcaoService = funcaoService;
	}

	@RequestMapping(value = "/findFuncaoPorOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarFuncao(@RequestParam(value = "nome") String nome, @RequestParam(value = "organizacaoId") String organizacaoId) {

		Collection<Funcao> listaFuncao = this.funcaoService.findPorNomeEOrganizacao(nome, Long.parseLong(organizacaoId));

		//TODO Rever View
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaFuncao, Views.GenericView.class);

		return responseBody;
	}
}
