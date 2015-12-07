package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Parceiro;
import br.com.centralit.api.service.ParceiroService;
import br.com.centralit.api.viewHelper.DominioParceirosNomeBuscaVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
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
 * @since 05/12/2014 - 11:13:24
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Controller
@RequestMapping("/rest/parceiro")
public class ParceiroController extends GenericController<Parceiro> {

	private ParceiroService parceiroService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param parceiroService
	 */
	@Autowired
	public ParceiroController( ParceiroService parceiroService ) {

		super(parceiroService);

		this.parceiroService = parceiroService;

	}

	/**
	 * Método responsável por buscar parceiros de forma generica exemplo 'tipoParceiro', ['FORNECEDOR', 'ORGAO_EXTERNO', 'PORTADOR']
	 *
	 * @author wilker.machado
	 *
	 * @param objeto
	 * @return <code>ResponseBodyWrapper</code>
	 */
	@RequestMapping(value = "/findParceiros", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findParceiros(@RequestBody DominioParceirosNomeBuscaVH objeto) {

		Collection<Parceiro> lista = this.parceiroService.findParceiros(objeto);

		ResultResponseVH<Collection<Parceiro>> resultResponseVH = new ResultResponseVH<Collection<Parceiro>>(lista);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.ParceiroAutoCompleteView.class);

		return responseBody;
	}

}
