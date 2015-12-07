package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.service.PrivilegioService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.model.Privilegio;

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
 * @since 09/03/2015 - 08:59:56
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Controller
@RequestMapping("/rest/privilegio")
public class PrivilegioController extends GenericController<Privilegio> {

	/** Atributo privilegioService. */
	@SuppressWarnings("unused")
	private PrivilegioService privilegioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param privilegioService
	 */
	@Autowired
	public PrivilegioController( PrivilegioService privilegioService ) {

		super(privilegioService);

		this.privilegioService = privilegioService;

	}

}
