package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.service.AccessRoleService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.model.AccessRole;

@Controller
@RequestMapping("/rest/accessRole")
public class AccessRoleController extends GenericController<AccessRole>{

	@Autowired
	public AccessRoleController(AccessRoleService accessRoleService) {
		super(accessRoleService);
	}
}
