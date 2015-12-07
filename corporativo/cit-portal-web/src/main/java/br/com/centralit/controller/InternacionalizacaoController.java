package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.InternacionalizacaoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Internacionalizacao;

@Controller
@RequestMapping("/rest/internacionalizacao")
public class InternacionalizacaoController extends GenericController<Internacionalizacao> {

	/** Atributo internacionalizacaoService. */
	private InternacionalizacaoService internacionalizacaoService;

	/**
	 * Responsavel pela criacao de novas instancias desta classe.
	 * @param internacionalizacaoService
	 */
	@Autowired
	public InternacionalizacaoController( InternacionalizacaoService internacionalizacaoService ) {

		super(internacionalizacaoService);
		this.internacionalizacaoService = internacionalizacaoService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/atualizarLabelSistema")
	@ResponseBody
	public ResponseBodyWrapper atualizarLabelSistema() throws Exception {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.internacionalizacaoService.atualizarArquivoPortalJson(), Views.GenericView.class);

		return responseBody;
	}

	@Override
	public Class<Views.InternacionalizacaoListView> getListView() {

		return Views.InternacionalizacaoListView.class;
	}

	@Override
	public Class<Views.InternacionalizacaoListView> getEditView() {

		return Views.InternacionalizacaoListView.class;
	}
}
