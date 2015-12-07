package br.com.centralit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Comissao;
import br.com.centralit.api.service.ComissaoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.view.ResultResponseVH;

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
 * @since 29/12/2014 - 10:51:10
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
@Controller
@RequestMapping("/rest/comissao")
public class ComissaoController extends GenericController<Comissao> {

	/** Atributo inventarioComissaoService. */
	private ComissaoService comissaoService;

	/**
	 *
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param inventarioComissaoService Serviço de InventarioComissao
	 */
	@Autowired
	public ComissaoController( ComissaoService comissaoService ) {

		super(comissaoService);

		this.comissaoService = comissaoService;
	}

	/**
	 * Método responsável por listar <code>Comissao</code> por nome e <code>Organizacao</code>
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @return <code>ResponseBodyWrapper</code>
	 */
	@RequestMapping(value = "/listarComissaoPorNomeEOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarComissaoPorNomeEOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") Long idOrganizacao) {

		ResultResponseVH<List<Comissao>> resultResponseVH = new ResultResponseVH<List<Comissao>>(comissaoService.listarComissaoPorNomeEOrganizacao(nome, idOrganizacao));

		//TODO Rever View
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.GenericView.class);

		return responseBody;
	}

	/**
	 * Método responsável por listar <code>Comissao</code> por nome e <code>Organizacao</code> e <code>Dominio</code>
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idOrganizacao
	 * @param idDominio
	 * @return <code>ResponseBodyWrapper</code>
	 */
	@RequestMapping(value = "/listarComissaoPorNomeEOrganizacaoEDominio", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarComissaoPorNomeEOrganizacaoEDominio(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") Long idOrganizacao, @RequestParam(value = "idDominio") Long idDominio) {

		ResultResponseVH<List<Comissao>> resultResponseVH = new ResultResponseVH<List<Comissao>>(comissaoService.listarComissaoPorNomeEOrganizacaoEDominio(nome, idOrganizacao, idDominio));

		//TODO Rever View
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.GenericView.class);

		return responseBody;
	}

	/**
	 * Recupera o class informando os campos de edição da view de inventario
	 */
	@Override
	public Class<Views.ComissaoEditView> getEditView() {

		return Views.ComissaoEditView.class;
	}

	/**
	 *	Recupera o class informando os campos de visualização na view de lista
	 */
	@Override
	public Class<Views.ComissaoListView> getListView() {

		return Views.ComissaoListView.class;
	}
}
