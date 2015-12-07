package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Luck;
import br.com.centralit.api.service.LuckService;
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
 * <b>Title: </b>LuckController
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 09/12/2015 - 14:46:30
 *
 * @version 1.0.0
 *
 * @author iago
 *
 */

@Controller
@RequestMapping("/rest/luck")
public class LuckController extends GenericController<Luck>{

	/** Atributo luckService. */
	private LuckService luckService;

	@Autowired
	public LuckController(LuckService luckService) {

		super(luckService);

		this.luckService = luckService;

	}

	/**
	 * Método responsável por listar a entidade<code>Luck</code>
	 *
	 * @author iago
	 *
	 * @return Collection<Luck>
	 */
	@RequestMapping(value = "/listarLuck", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarLuck(@RequestParam(value = "nome") String nome) {

		final Collection<Luck> listaLuck = this.luckService.listarLuck(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaLuck, Views.LuckListView.class);

		return responseBody;
	}

	@Override
	public Class<Views.LuckEditView> getEditView() {

		return Views.LuckEditView.class;
	}

	@Override
	public Class<Views.LuckListView> getListView() {

		return Views.LuckListView.class;
	}
}
