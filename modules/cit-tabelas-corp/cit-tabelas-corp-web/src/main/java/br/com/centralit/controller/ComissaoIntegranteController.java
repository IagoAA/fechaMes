package br.com.centralit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.model.ComissaoIntegrante;
import br.com.centralit.api.service.ComissaoIntegranteService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.view.ResultResponseVH;

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
 * @since 29/12/2014 - 10:51:10
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
@Controller
@RequestMapping("/rest/comissaoIntegrante")
public class ComissaoIntegranteController extends GenericController<ComissaoIntegrante> {

	/** Atributo comissaoIntegranteService. */
	private ComissaoIntegranteService comissaoIntegranteService;

	/**
	 *
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param comissaoIntegranteService
	 *            Serviço de ComissaoIntegrante
	 */
	@Autowired
	public ComissaoIntegranteController( ComissaoIntegranteService comissaoIntegranteService ) {

		super(comissaoIntegranteService);

		this.comissaoIntegranteService = comissaoIntegranteService;
	}

	/**
	 * Método responsável por listar <code>Comissao</code> por nome e idComissao
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrgao
	 * @return <code>ResponseBodyWrapper</code>
	 */
	@RequestMapping(value = "/findComissaoIntegrantePorColaborador", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findComissaoIntegrantePorColaborador(@RequestParam(value = "nome") String nome, @RequestParam(value = "idComissao") Long idComissao) {

		ResultResponseVH<List<Colaborador>> resultResponseVH = new ResultResponseVH<List<Colaborador>>(comissaoIntegranteService.findComissaoIntegrantePorColaborador(nome, idComissao));

		//TODO Rever View
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.GenericView.class);

		return responseBody;
	}

}
