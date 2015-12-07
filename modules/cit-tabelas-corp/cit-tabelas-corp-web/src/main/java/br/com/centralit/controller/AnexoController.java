package br.com.centralit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.api.model.Anexo;
import br.com.centralit.api.service.AnexoService;
import br.com.centralit.framework.controller.GenericController;

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
 * <b>Title: AnexoController</b>
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
 * @since 08/01/2015 - 09:47:27
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Controller
@RequestMapping("/rest/anexo")
public class AnexoController extends GenericController<Anexo> {

	/** Atributo anexoService. */
	private AnexoService anexoService;

	public AnexoController() {

		super();
	}

	@Autowired
	public AnexoController( AnexoService anexoService ) {

		super(anexoService);

		this.anexoService = anexoService;
	}

	/**
	 * Método responsável por realizar o upload dos anexos que estão ligadas a entidade documento
	 * 
	 * @author wilker.machado
	 * 
	 * @param name
	 * @param file
	 * @param idAnexo
	 */
	@RequestMapping(value = "/uploadAnexo", method = RequestMethod.POST)
	public @ResponseBody
	void uploadAnexo(@RequestParam(value = "filename", required = false) String name, @RequestParam("file") MultipartFile file, @RequestParam(value = "idDocumento", required = false) Long idDocumento) {

		this.anexoService.saveAnexo(file, idDocumento);
	}

	/**
	 * Método responsável por realizar o download dos anexos que estão ligadas a entidade documento
	 * 
	 * @author wilker.machado
	 * 
	 * @param idAnexo
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadAnexo", method = RequestMethod.GET)
	public @ResponseBody
	void downloadAnexo(@RequestParam(value = "idAnexo") Long idAnexo, HttpServletResponse response) throws IOException {

		Anexo anexo = this.anexoService.find(idAnexo);

		response.setContentType("application/octet-stream");
		
		response.setHeader("Content-Disposition", "attachment;filename=\"" + anexo.getDescricao() + "\"");

		response.getOutputStream().write(anexo.getAnexo());
	}

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @param idAnexo
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/visualizarImagem", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<byte[]> visualizarImagem(@RequestParam(value = "idAnexo") Long idAnexo, HttpServletResponse response) {

		Anexo anexo = this.anexoService.find(idAnexo);

		HttpHeaders headers = new HttpHeaders();

		headers.add("content-disposition", "inline;filename=" + anexo.getDescricao());

		headers.setContentType(MediaType.parseMediaType(anexo.getDominioTipoAnexo().getDescricao()));

		ResponseEntity<byte[]> resp = new ResponseEntity<byte[]>(anexo.getAnexo(), headers, HttpStatus.OK);

		return resp;

	}
}
