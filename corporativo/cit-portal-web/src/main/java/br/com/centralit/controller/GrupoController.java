package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.service.GrupoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Grupo;

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
 * @since 04/03/2015 - 15:43:09
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Controller
@RequestMapping("/rest/grupo")
public class GrupoController extends GenericController<Grupo> {

	/** Atributo grupoService. */
	@SuppressWarnings("unused")
	private GrupoService grupoService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param grupoService
	 */
	@Autowired
	public GrupoController( GrupoService grupoService ) {

		super(grupoService);

		this.grupoService = grupoService;

	}

	@Override
	public Class<Views.GrupoEditView> getEditView() {

		return Views.GrupoEditView.class;
	}

	@Override
	public Class<Views.GrupoListView> getListView() {

		return Views.GrupoListView.class;
	}

	@Override
	public Class<Views.GrupoAutoCompleteView> getAutoCompleteView() {

		return Views.GrupoAutoCompleteView.class;
	}


}
