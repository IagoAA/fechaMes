package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.model.Telefone;
import br.com.centralit.api.service.TelefoneService;
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
 * @since 05/12/2014 - 11:13:24
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Controller
@RequestMapping("/rest/telefone")
public class TelefoneController extends GenericController<Telefone> {

	private TelefoneService telefoneService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param telefoneService
	 */
	@Autowired
	public TelefoneController( TelefoneService telefoneService ) {

		super(telefoneService);

		this.telefoneService = telefoneService;

	}


	/**
	 * Retorna o valor do atributo <code>telefoneService</code>
	 *
	 * @return <code>TelefoneService</code>
	 */
	public TelefoneService getTelefoneService() {

		return telefoneService;
	}


	/**
	 * Define o valor do atributo <code>telefoneService</code>.
	 *
	 * @param telefoneService
	 */
	public void setTelefoneService(TelefoneService telefoneService) {

		this.telefoneService = telefoneService;
	}

}
