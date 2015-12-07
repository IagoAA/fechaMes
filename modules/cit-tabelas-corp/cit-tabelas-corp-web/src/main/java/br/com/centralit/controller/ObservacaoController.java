package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.model.Observacao;
import br.com.centralit.api.service.ObservacaoService;
import br.com.centralit.framework.controller.GenericController;

@Controller
@RequestMapping("/rest/observacao")
public class ObservacaoController extends GenericController<Observacao>{

	public ObservacaoController() {
		super();
	}

	@Autowired
	public ObservacaoController(ObservacaoService observacaoService) {
		super(observacaoService);
	}
}
