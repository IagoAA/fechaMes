package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Organizacao;

@Controller
@RequestMapping("/rest/organizacao")
public class OrganizacaoController extends GenericController<Organizacao> {

	private final OrganizacaoService organizacaoService;

	@Autowired
	public OrganizacaoController( OrganizacaoService organizacaoService ) {

		super(organizacaoService);

		this.organizacaoService = organizacaoService;
	}

	@RequestMapping(value = "/listarOrganizacaoPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarOrganizacaoPorNome(@RequestParam(value = "nome") String nome) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(organizacaoService.listarOrganizacaoPorNome(nome), Views.OrganizacaoAutoCompleteView.class);

		return responseBody;
	}
	
	@Override
	public Class<Views.OrganizacaoListView> getListView() {

		return Views.OrganizacaoListView.class;

	}

	@Override
	public Class<Views.OrganizacaoEditView> getEditView() {

		return Views.OrganizacaoEditView.class;

	}	

}