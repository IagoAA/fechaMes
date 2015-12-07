package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.service.WidgetParametroService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.json.Views.GenericView;
import br.com.centralit.framework.model.WidgetParametro;

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
 * @since 20/03/2015 - 11:34:30
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
@Controller
@RequestMapping("/rest/widgetParametro")
public class WidgetParametroController extends GenericController<WidgetParametro> {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 */
	@Autowired
	public WidgetParametroController( WidgetParametroService widgetParametroService ) {

		super(widgetParametroService);
	}

	@Override
	public Class<? extends GenericView> getEditView() {

		return Views.WidgetEditView.class;
	}

	@Override
	public Class<? extends GenericView> getListView() {

		return Views.WidgetListView.class;
	}
}
