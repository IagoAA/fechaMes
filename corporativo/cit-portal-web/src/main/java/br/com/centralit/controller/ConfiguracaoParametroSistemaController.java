package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.util.UtilObjeto;

@Controller
@RequestMapping("/rest/configuracaoParametroSistema")
public class ConfiguracaoParametroSistemaController extends GenericController<ConfiguracaoParametroSistema>{

	private ConfiguracaoParametroSistemaService configuracaoParametroSistemaService;

	@Autowired
	public ConfiguracaoParametroSistemaController( ConfiguracaoParametroSistemaService configuracaoParametroSistemaService ) {

		super(configuracaoParametroSistemaService);

		this.configuracaoParametroSistemaService = configuracaoParametroSistemaService;

	}
	
	@RequestMapping(value = "/getParametro", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper getParametro(@RequestParam(value = "chave") String chave) {

		ConfiguracaoParametroSistema parametroSistema = this.configuracaoParametroSistemaService.getParametro(chave, ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOrganizacao());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(UtilObjeto.isReferencia(parametroSistema) ? parametroSistema : new ConfiguracaoParametroSistema(), Views.ConfiguracaoView.class);

		return responseBody;
	}
	
}
