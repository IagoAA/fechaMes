package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.Usuario;



/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p> Controller necessario para mudar o organizacao do usuario para o modulo almoxarifado 
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 01/09/2015 - 17:42:26
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *	
 */
@Controller
@RequestMapping("/rest/usuarioModulo")
public class UsuarioTabelasCorpController {

	@Autowired
	private OrganizacaoService organizacaoService;
	
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por atualizar a organizacao no contexto do modulo. Quando o usuario usa a opcao de mudanca de organizacao no portal. 
	 *
	 * @author juliana.barbosa
	 *
	 * @param organizacao
	 * @return
	 */
	@RequestMapping(value = "/atualizarUsuario", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBodyWrapper atualizarUsuario(@RequestBody Organizacao organizacao) {

		( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).setOrganizacao(organizacaoService.find(organizacao.getId()));
		
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(Boolean.TRUE, Views.GenericView.class);

		return responseBody;
	}
	
	
}
