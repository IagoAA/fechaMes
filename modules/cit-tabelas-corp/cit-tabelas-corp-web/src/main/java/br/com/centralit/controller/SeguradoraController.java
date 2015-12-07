package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Seguradora;
import br.com.centralit.api.service.SeguradoraService;
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
 * @since 14/04/2015 - 15:42:50
 *
 * @version 1.0.0
 *
 * @author juliana.barbosa
 *	
 */
@Controller
@RequestMapping("/rest/seguradora")
public class SeguradoraController extends GenericController<Seguradora> {

	/** Atributo seguradoraService. */
	private SeguradoraService seguradoraService;

	public SeguradoraController() {
		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param colaboradorService
	 */
	@Autowired
	public SeguradoraController( SeguradoraService seguradoraService ) {

		super(seguradoraService);

		this.seguradoraService = seguradoraService;

	}


	/**
	 * Retorna o valor do atributo <code>fornecedorService</code>
	 *
	 * @return <code>FornecedorService</code>
	 */
	public SeguradoraService getSeguradoraService() {

		return seguradoraService;
	}

	@RequestMapping(value = "/listarSeguradorasPorNomeOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarSeguradorasPorNomeOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") Long idOrganizacao) {

		final Collection<Seguradora> listaSeguradoras = this.getSeguradoraService().listarSeguradorasPorNomeOrganizacao(nome, idOrganizacao);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaSeguradoras, Views.SeguradoraAutoCompleteView.class);

		return responseBody;
	}

}
