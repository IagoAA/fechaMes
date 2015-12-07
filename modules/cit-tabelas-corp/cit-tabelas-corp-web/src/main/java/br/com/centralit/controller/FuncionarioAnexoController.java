package br.com.centralit.controller;

import java.io.IOException;
import java.util.Collection;

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

import br.com.centralit.api.model.FuncionarioAnexo;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.FuncionarioAnexoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;

@Controller
@RequestMapping("/rest/funcionarioAnexo")
public class FuncionarioAnexoController extends GenericController<FuncionarioAnexo>{

	/** Atributo funcionarioAnexoService. */
	private FuncionarioAnexoService funcionarioAnexoService;

	public FuncionarioAnexoController() {
		super();
	}

	@Autowired
	private DominioService dominioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param funcionarioAnexoService
	 */
	@Autowired
	private FuncionarioAnexoController( FuncionarioAnexoService funcionarioAnexoService ) {

		super(funcionarioAnexoService);

		this.funcionarioAnexoService = funcionarioAnexoService;

	}

	@RequestMapping(value = "/listarAnexos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarAnexos(@RequestParam(value = "idFuncionario") Long idFuncionario) {

		final Collection<FuncionarioAnexo> listaAnexos = this.funcionarioAnexoService.listarAnexos(idFuncionario);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaAnexos, Views.PessoaEditView.class);

		return responseBody;
	}

	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	void uploadMultipleFileHandler(@RequestParam(value = "filename", required = false) String name, @RequestParam("file") MultipartFile file, @RequestParam(value = "idFuncionario", required = false) Long idFuncionario) {

		this.funcionarioAnexoService.save(file, idFuncionario);

	}

	@RequestMapping(value = "/downloadArquivo", method = RequestMethod.GET)
	public @ResponseBody
	void downloadArquivo(@RequestParam(value = "idFuncionarioAnexo") Long idFuncionarioAnexo, HttpServletResponse response) throws IOException {

		FuncionarioAnexo funcionarioAnexo = this.funcionarioAnexoService.find(idFuncionarioAnexo);

		response.setContentType("application/octet-stream");

		response.setHeader("Content-Disposition", "attachment;filename=" + funcionarioAnexo.getDescricao());

		response.getOutputStream().write(funcionarioAnexo.getAnexo());
	}

	@RequestMapping(value = "/visualizarArquivo", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<byte[]> visualizarArquivo(@RequestParam(value = "idFuncionarioAnexo") Long idFuncionarioAnexo, HttpServletResponse response) throws IOException {

		FuncionarioAnexo funcionarioAnexo = this.funcionarioAnexoService.find(idFuncionarioAnexo);

		String extensao = funcionarioAnexo.getDescricao().contains(".") ? funcionarioAnexo.getDescricao().split("\\.")[1] : "";

		String mimeType = "";

		for (Dominio dominio : this.dominioService.listarPorChave(Dominio.TIPO_ANEXO)) {

			if (dominio.getNome().equalsIgnoreCase(extensao.trim())) {

				// mimeType do arquivo esta relacionado com o dominio via sql
				mimeType = dominio.getDescricao();

				break;
			}
		}

		HttpHeaders headers = new HttpHeaders();

		headers.add("content-disposition", "inline;filename=" + funcionarioAnexo.getDescricao());

		headers.setContentType(MediaType.parseMediaType(mimeType));

		ResponseEntity<byte[]> resp = new ResponseEntity<byte[]>(funcionarioAnexo.getAnexo(), headers, HttpStatus.OK);

		return resp;

	}
}
