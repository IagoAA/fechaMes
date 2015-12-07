package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.service.PainelService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Painel;

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
 * @since 10/03/2015 - 15:42:40
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Controller
@RequestMapping("/rest/painel")
public class PainelController extends GenericController<Painel> {

	private PainelService painelService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param painelService
	 */
	@Autowired
	public PainelController( PainelService painelService ) {

		super(painelService);

		this.painelService = painelService;

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar os paineis do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idTipoComponente
	 * 
	 * @return Collection<Painel>
	 */
	@RequestMapping(value = "/findPorUsuario", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findPorUsuario() {

		final Collection<Painel> paineis = this.painelService.findPorUsuario();

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(paineis, Views.DashBoardListView.class);

		return responseBody;
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param pessoaVH
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/salvarDashBoard")
	@ResponseBody
	public ResponseBodyWrapper salvarDashBoard(@RequestBody Painel painel) throws Exception {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.painelService.salvarDashBoard(painel), getEditView());

		return responseBody;
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findPainelDashBoard", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findPainelDashBoard(@RequestParam(value = "idPainel") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.painelService.findPainelDashBoard(id), this.getEditView());

		return responseBody;
	}

	@Override
	public Class<Views.PainelEditView> getEditView() {

		return Views.PainelEditView.class;
	}

	@Override
	public Class<Views.PainelListView> getListView() {

		return Views.PainelListView.class;
	}

}
