package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.ImportacaoDadosService;
import br.com.centralit.api.viewHelper.PessoaVH;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">595</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 10/04/2015 - 16:02:25
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *
 */
@Controller
@RequestMapping("/rest/importacaoDados")
public class ImportacaoDadosController  {

	/** Atributo fornecedorService. */
	@Autowired
	private ImportacaoDadosService importacaoDadosService;

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por importar colaborador a partir de um sistema externo
	 *
	 * @author carlos.alberto
	 *
	 * @param id
	 * @return
	 */	@RequestMapping(value = "/importaColaborador", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBodyWrapper importaColaborador(@RequestBody PessoaVH pessoaVH) {
		return new ResponseBodyWrapper(this.importacaoDadosService.importaColaborador(pessoaVH), Views.GenericView.class);
	}

}
