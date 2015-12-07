package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.service.ColaboradorService;
import br.com.centralit.framework.controller.GenericController;
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
@RequestMapping("/rest/colaborador")
public class ColaboradorController extends GenericController<Colaborador> {

	/** Atributo fornecedorService. */
	private ColaboradorService colaboradorService;

	public ColaboradorController() {
		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param colaboradorService
	 */
	@Autowired
	public ColaboradorController( ColaboradorService colaboradorService ) {

		super(colaboradorService);

		this.colaboradorService = colaboradorService;

	}


	/**
	 * Retorna o valor do atributo <code>fornecedorService</code>
	 *
	 * @return <code>FornecedorService</code>
	 */
	public ColaboradorService getColaboradorService() {

		return colaboradorService;
	}

	@RequestMapping(value = "/findPorNomeAndOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findPorNomeAndOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") Long idOrganizacao) {

		final Collection<Colaborador> listaColaborador = this.getColaboradorService().findPorNomeAndOrganizacao(nome, idOrganizacao);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaColaborador, Views.ColaboradorAutoCompleteView.class);

		return responseBody;
	}
	
	@RequestMapping(value = "/findPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findPorNome(@RequestParam(value = "nome") String nome) {

		final Collection<Colaborador> listaColaborador = this.getColaboradorService().findPorNome(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaColaborador, Views.ColaboradorAutoCompleteView.class);

		return responseBody;
	}	

}
