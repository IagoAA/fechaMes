package br.com.centralit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.api.service.ConfiguracaoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.json.Views.GenericView;
import br.com.centralit.framework.model.Configuracao;
import br.com.centralit.framework.view.ResultResponseVH;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 * 
 * <p>
 * <b>Company: </b> Central IT - Governança Corporativa -
 * </p>
 * 
 * <p>
 * <b>Title: </b>
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 * 
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 * 
 * @since 24/02/2015 - 14:28:53
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Controller
@RequestMapping("/rest/configuracao")
public class ConfiguracaoController extends GenericController<Configuracao> {

	private ConfiguracaoService configuracaoService;

	@Autowired
	public ConfiguracaoController( ConfiguracaoService configuracaoService ) {

		super(configuracaoService);

		this.configuracaoService = configuracaoService;

	}

	@Override
	public Class<? extends GenericView> getEditView() {

		return Views.ConfiguracaoView.class;
	}

	@Override
	public Class<? extends GenericView> getListView() {

		return Views.ConfiguracaoView.class;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public void uploadFileHandler(@RequestParam(value = "filename", required = false) String name, @RequestParam("file") MultipartFile file, HttpServletRequest request) {

		String filePath = request.getServletContext().getRealPath("/assets/css/images/logo-login.png");

		this.configuracaoService.uploadLogoPaginaLoginForResource(file, filePath);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por realiza o upload da foto referente a organizacao
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param name
	 * @param file
	 * @param request
	 * @param idOrganizacao
	 * @param idConfiguracao
	 */
	@RequestMapping(value = "/uploadFileLogo", method = RequestMethod.POST)
	@ResponseBody
	public void uploadFileHandle(@RequestParam(value = "filename", required = false) String name, @RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam("idOrganizacao") Long idOrganizacao, @RequestParam("idConfiguracao") String idConfiguracao) {

		this.configuracaoService.uploadAnexoImagemOrganizacao(file, idOrganizacao, Long.parseLong(idConfiguracao));
	}

	@RequestMapping(value = "/getConfiguracao", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBodyWrapper getConfiguracao(@RequestParam(value = "idOrganizacao") Long idOrganizacao) {

		ResultResponseVH<Configuracao> resultResponseVH = new ResultResponseVH<Configuracao>(configuracaoService.getConfiguracao(idOrganizacao));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, this.getEditView());

		return responseBody;
	}
}
