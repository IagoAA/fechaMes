package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Fornecedor;
import br.com.centralit.api.service.FornecedorService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

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
 * <b>Title: </b>FornecedorController
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
 * @since 26/11/2014 - 15:28:14
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Controller
@RequestMapping("/rest/fornecedor")
public class FornecedorController extends GenericController<Fornecedor> {

	/** Atributo fornecedorService. */
	private FornecedorService fornecedorService;

	public FornecedorController() {
		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param fornecedorService
	 */
	@Autowired
	public FornecedorController( FornecedorService fornecedorService ) {

		super(fornecedorService);

		this.fornecedorService = fornecedorService;

	}


	/**
	 * Retorna o valor do atributo <code>fornecedorService</code>
	 *
	 * @return <code>FornecedorService</code>
	 */
	public FornecedorService getFornecedorService() {

		return fornecedorService;
	}

	@RequestMapping(value = "/listarFornecedoresBusca", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarFornecedoresBusca(@RequestParam(value = "nome") String nome) {

		final Collection<Fornecedor> listaFornecedores = fornecedorService.listarFornecedores(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaFornecedores, Views.FornecedorFindView.class);

		return responseBody;
	}

}
