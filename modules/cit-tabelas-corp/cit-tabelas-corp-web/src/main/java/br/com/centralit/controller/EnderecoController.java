package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Endereco;
import br.com.centralit.api.service.EnderecoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.view.GridVH;
import br.com.centralit.framework.view.ResultResponseVH;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

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
 * @since 05/12/2014 - 11:14:43
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Controller
@RequestMapping("/rest/endereco")
public class EnderecoController extends GenericController<Endereco> {

	/** Atributo enderecoService. */
	private EnderecoService enderecoService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param enderecoService
	 */
	@Autowired
	public EnderecoController( EnderecoService enderecoService ) {

		super(enderecoService);

		this.enderecoService = enderecoService;

	}

	/**
	 * Método responsável por listar a entidade<code>Endereco</code> que não tem vinculo com pessoa
	 *
	 * @author iago.almeida
	 *
	 * @return Collection<Endereco>
	 */
	@RequestMapping(value = "/getPage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBodyWrapper findGrid(@RequestBody SearchParams searchParams) {

		SearchSeven search = new SearchSeven(searchParams);

		search.addFilterEmpty("pessoa.id");

		search.setResultMode(Search.RESULT_MAP);

		@SuppressWarnings("rawtypes")
		SearchResult searchResult = genericService.searchAndCount(search);

		// DETERMINA QUAIS OS CAMPOS VAI CONSULTAR
		GridVH gridVH = new GridVH();
		gridVH.setObjects(searchResult.getResult());
		gridVH.addTotalItensTotalPages(searchParams, Long.valueOf(searchResult.getTotalCount()));

		@SuppressWarnings({ "rawtypes", "unchecked" })
		ResultResponseVH resultResponseVH = new ResultResponseVH(gridVH);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, this.getListView());

		return responseBody;
	}

	/**
	 * Método responsável por listar a entidade<code>Endereco</code>
	 *
	 * @author iago.almeida
	 *
	 * @return Collection<Regiao>
	 */
	@RequestMapping(value = "/listarEndereco", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarEndereco(@RequestParam(value = "nome") String nome) {

		final Collection<Endereco> listaEndereco = this.enderecoService.listarEndereco(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaEndereco, Views.EnderecoListView.class);

		return responseBody;
	}

	@Override
	public Class<Views.EnderecoEditView> getEditView() {

		return Views.EnderecoEditView.class;
	}

	@Override
	public Class<Views.EnderecoListView> getListView() {

		return Views.EnderecoListView.class;
	}
}
