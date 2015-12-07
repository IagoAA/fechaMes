package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
import br.com.centralit.api.service.EstruturaOrganizacionalResponsavelService;
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
 * @since 06/01/2015 - 15:49:32
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Controller
@RequestMapping("/rest/estruturaOrganizacionalResponsavel")
public class EstruturaOrganizacionalResponsavelController extends GenericController<EstruturaOrganizacionalResponsavel> {

	public EstruturaOrganizacionalResponsavelController() {
		super();
	}

	@Autowired
	public EstruturaOrganizacionalResponsavelController( EstruturaOrganizacionalResponsavelService estruturaOrganizacionalResponsavelService ) {

		super(estruturaOrganizacionalResponsavelService);
	}
}
