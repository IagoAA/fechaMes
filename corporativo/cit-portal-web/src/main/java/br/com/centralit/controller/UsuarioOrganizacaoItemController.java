package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.service.UsuarioOrganizacaoItemService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;

@Controller
@RequestMapping("/rest/usuarioOrganizacaoItem")
public class UsuarioOrganizacaoItemController extends GenericController<UsuarioOrganizacaoItem> {

	@Autowired
	public UsuarioOrganizacaoItemController( UsuarioOrganizacaoItemService usuarioOrganizacaoItemService ) {

		super(usuarioOrganizacaoItemService);

	}

}