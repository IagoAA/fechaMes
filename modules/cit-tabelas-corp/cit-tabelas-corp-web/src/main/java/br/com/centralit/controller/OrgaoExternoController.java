package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.model.OrgaoExterno;
import br.com.centralit.api.service.OrgaoExternoService;
import br.com.centralit.framework.controller.GenericController;

@Controller
@RequestMapping("/rest/orgaoExterno")
public class OrgaoExternoController extends GenericController<OrgaoExterno>{

	public OrgaoExternoController() {
		super();
	}

	@Autowired
	public OrgaoExternoController(OrgaoExternoService orgaoExternoService) {
		super(orgaoExternoService);
	}
}
