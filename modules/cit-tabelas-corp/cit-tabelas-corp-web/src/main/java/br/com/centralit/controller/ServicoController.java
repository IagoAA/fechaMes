package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Servico;
import br.com.centralit.api.service.ServicoService;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

@Controller
@RequestMapping("/rest/servico")
public class ServicoController extends GenericController<Servico>{

	/** Atributo servicoService. */
	private ServicoService servicoService;

	@Autowired
	public ServicoController(ServicoService servicoService) {

		super(servicoService);

		this.servicoService = servicoService;

	}

	/**
	 * Método responsável por listar a entidade<code>Servico</code>
	 *
	 * @author iago
	 *
	 * @return Collection<Regiao>
	 */
	@RequestMapping(value = "/listarServico", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper listarServico(@RequestParam(value = "nome") String nome) {

		final Collection<Servico> listaServico = this.servicoService.listarServico(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaServico, Views.ServicoListView.class);

		return responseBody;
	}

	/**
	 * Método responsável por listar a entidade<code>Servico</code>por organizacao
	 *
	 * @author iago
	 *
	 * @return Collection<Regiao>
	 */
	@RequestMapping(value = "/findServicoPorOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findServicoPorOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "organizacaoId") Long organizacaoId) {

		Collection<Servico> listaServico = this.servicoService.findPorNomeAndOrganizacao(nome, organizacaoId);

		//TODO Rever View
		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaServico, Views.ServicoAutoCompleteView.class);

		return responseBody;
	}

	@Override
	public Class<Views.ServicoEditView> getEditView() {

		return Views.ServicoEditView.class;
	}

	@Override
	public Class<Views.ServicoListView> getListView() {

		return Views.ServicoListView.class;
	}
}
