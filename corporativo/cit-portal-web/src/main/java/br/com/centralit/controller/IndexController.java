package br.com.centralit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.DefaultFileService;
import br.com.centralit.api.service.InformacaoSistemaService;
import br.com.centralit.api.service.MenuFileService;
import br.com.centralit.api.service.MenuService;
import br.com.centralit.api.service.PainelService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.exception.ApplicationConfigurationException;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.util.UtilObjeto;
import br.com.centralit.framework.util.UtilString;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuFileService menuFileService;

	@Autowired
	private DefaultFileService defaultFileService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PainelService painelService;

	@Autowired
	private InformacaoSistemaService informacaoSistemaService;

	// Definido no arquivo cit-app.properties
	@Value("${metodo.autenticacao}")
	private int metodoAutenticacao;

	private final int DATABASE = 1;

	private final int CAS = 2;

	//private final int OPEN_AM = 3;

	@RequestMapping()
	// TODO precisa verificar qual o tipo de autenticação para resolver o SSO.
	public String sso(ModelMap model) {
		switch (metodoAutenticacao) {
			case DATABASE:
				return sso2(model);
			case CAS:
				return sso2Cas(model);
			default:
				throw new ApplicationConfigurationException("Valor inv\u00e1lido inserido no arquivo cit-app.properties para o m\u00e9todo "
						+ "de autentica\u00e7\u00e3o. Valores permitidos: 1 e 2 somente.");
		}
	}

	@RequestMapping(value = "/sso2")
	public String sso2(ModelMap model) {

		return "sso2";
	}

	@RequestMapping(value = "/sso2Cas")
	public String sso2Cas(ModelMap model) {

		return "sso2Cas";
	}

	@RequestMapping(value = "/sso2Auth")
	public String sso2Auth(ModelMap model) {

		return "sso2Auth";
	}

	@RequestMapping(value = "/sso2AuthCas")
	public String sso2AuthCas(ModelMap model) {

		return "sso2AuthCas";
	}

	@RequestMapping(value = "/resetPassword")
	public String resetPassword(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String token = request.getParameter("token");

		if (UtilObjeto.isReferencia(token) && !UtilString.isNullOrEmpty(token)) {
			String password = request.getParameter("password");
			String passwordConfirm = request.getParameter("password_confirm");

			if (UtilObjeto.isReferencia(password) && !UtilString.isNullOrEmpty(password) && UtilObjeto.isReferencia(passwordConfirm) && !UtilString.isNullOrEmpty(passwordConfirm)) {
				if (!password.equals(passwordConfirm)) {
					model.addAttribute("password", password);
					model.addAttribute("passwordConfirm", passwordConfirm);
					model.addAttribute("error", true);

					return "recoveryPassword";
				} else {

					Usuario usuario = usuarioService.getUserByToken(token);

					usuarioService.updateUserPassword(usuario, password);

					return "resetPassword";
				}
			} else {

				response.sendRedirect("/cit-portal-web/recoveryPassword?token=" + token);
			}

			return "recoveryPassword";
		}

		response.sendRedirect("/cit-portal-web/login");

		return "login";
	}

	@RequestMapping(value = "/recoveryPassword")
	public String recoveryPassword(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String token = request.getParameter("token");

		if (UtilObjeto.isReferencia(token) && !UtilString.isNullOrEmpty(token)) {

			if (!usuarioService.verifyIfTokenRecoveryPasswordExists(token)) {

				model.addAttribute("tokenInvalid", true);
			}

			return "recoveryPassword";
		}

		response.sendRedirect("/cit-portal-web/login");

		return "login";
	}

	@RequestMapping(value = "/requestRecoveryPassword")
	public String requestRecoveryPassword(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

		String emailRecovery = request.getParameter("email_recovery");

		if (UtilObjeto.isReferencia(emailRecovery) && !UtilString.isNullOrEmpty(emailRecovery)) {
			String url = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();

			String resp = usuarioService.requestRecoveryPasswordEmail(emailRecovery, url);

			model.addAttribute("response", resp);

			return "requestRecoveryPassword";
		}

		response.sendRedirect("/cit-portal-web/login");

		return "login";
	}

	@RequestMapping(value = "/index")
	public String index(ModelMap model) {

		// Menus
		model.addAttribute("menus", this.menuService.findMenuPorModulosAtivos());
		model.addAttribute("menuJSFiles", this.menuFileService.findAllJSAtivosPorModulosAtivos());
		model.addAttribute("menuCSSFiles", this.menuFileService.findAllCSSAtivosPorModulosAtivos());

		// DefaultFiles
		model.addAttribute("defaultFilesJS", this.defaultFileService.findAllJSAtivosPorModulosAtivos());
		model.addAttribute("defaultFilesCSS", this.defaultFileService.findAllCSSAtivosComModulosAtivos());
		model.addAttribute("modulos", menuService.findModulosAtivo());

		return "home";
	}

	@RequestMapping(value = "/login")
	public String login(ModelMap model) {

		return "login";
	}

	@RequestMapping(value = "/loginCas")
	public String loginCas(ModelMap model) {

		return "loginCas";
	}

	@RequestMapping(value = "/modulos", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBodyWrapper getModules() {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(menuService.findModulosAtivo(), Views.GenericView.class);

		return responseBody;
	}

	/**
	 *  Método responsável por obter informacoes do sistema
	 *
	 * @author iago.almeida
	 *
	 * @return responseBody
	 */
	@RequestMapping(value = "/getInformacaoSistema", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper getInformacaoSistema() {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.informacaoSistemaService.getInformacaoSistema(), Views.GenericView.class);

		return responseBody;
	}

}
