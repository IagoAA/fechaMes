package br.com.centralit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.centralit.api.framework.json.ViewsTabelasCorp;
import br.com.centralit.api.model.NivelAutoridade;
import br.com.centralit.api.service.NivelAutoridadeService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.Views.GenericView;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 22/06/2015 - 15:50:15
 *
 * @version 1.0.0
 *
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 *	
 */
@Controller
@RequestMapping("/rest/nivelAutoridade")
public class NivelAutoridadeController extends GenericController<NivelAutoridade>{

	@Autowired
	public NivelAutoridadeController(NivelAutoridadeService nivelAutoridadeService) {
		super(nivelAutoridadeService);
	}
	
	@Override
	public Class<? extends GenericView> getEditView() {
	
		// TODO Auto-generated method stub
		return ViewsTabelasCorp.NivelAutoridadeView.class;
	}
	
	@Override
	public Class<? extends GenericView> getListView() {
	
		// TODO Auto-generated method stub
		return ViewsTabelasCorp.NivelAutoridadeView.class;
	}
}
