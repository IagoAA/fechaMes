package br.com.centralit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.DefaultFileService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.model.DefaultFile;

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
 * <b>Title: DefaultFileController</b>
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
 * @since 09/12/2014 - 17:21:01
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Controller
@RequestMapping("/rest/defaultFile")
public class DefaultFileController extends GenericController<DefaultFile> {

	/** Atributo defaultFileService. */
	@Autowired
	private DefaultFileService defaultFileService;

	@Autowired
	public DefaultFileController( DefaultFileService defaultFileService ) {

		super(defaultFileService);

	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseBody
	public ResponseBodyWrapper save(@RequestBody DefaultFile defaultFile) throws Exception {

		defaultFile.setOrdem(1000);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(genericService.save(defaultFile), getEditView());

		return responseBody;
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
	 * Método responsável por chamar o serviço para salvar a ordem e configurações dos arquivos padrões
	 * 
	 * @author renato.jesus
	 * 
	 * @param defaultFiles
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveConfig")
	@ResponseBody
	public ResponseBodyWrapper saveConfig(@RequestBody List<DefaultFile> defaultFiles) throws Exception {

		DefaultFile[] defaultFilesArray = new DefaultFile[defaultFiles.size()];

		int i = 0;
		for (DefaultFile defaultFile : defaultFiles) {
			defaultFilesArray[i] = defaultFile;
			i++;
		}

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(defaultFileService.merge(defaultFilesArray), getEditView());

		return responseBody;

	}
}
