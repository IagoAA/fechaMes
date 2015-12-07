package br.com.centralit.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.api.model.Colaborador;
import br.com.centralit.api.model.Funcionario;
import br.com.centralit.api.model.Pessoa;
import br.com.centralit.api.service.PessoaService;
import br.com.centralit.api.viewHelper.PessoaVH;
import br.com.centralit.framework.controller.GenericController;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;

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
 * <b>Title: </b>PessoaController
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 28/11/2014 - 11:49:15
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Controller
@RequestMapping("/rest/pessoa")
public class PessoaController extends GenericController<Pessoa> {

	/** Atributo pessoaService. */
	private PessoaService pessoaService;

	public PessoaController() {
		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param pessoaService
	 */
	@Autowired
	public PessoaController( PessoaService pessoaService ) {

		super(pessoaService);

		this.pessoaService = pessoaService;

	}

	/**
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por buscar colaborador por nome <code>Colaborador</code>
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 *
	 * @return responseBody<Colaborador>
	 */
	@RequestMapping(value = "/findColaboradorPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findColaboradorPorNome(@RequestParam(value = "nome") String nome) {

		final Collection<Colaborador> listaColaborador = this.getPessoaService().findColaboradorPorNomeNaOrganizacaoUsuarioLogado(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaColaborador, Views.ColaboradorAutoCompleteView.class);

		return responseBody;
	}

	/**
	 * Método responsável por buscar funcionario por nome <code>Funcionario</code>
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 *
	 * @return responseBody<Funcionario>
	 */
	@RequestMapping(value = "/findFuncionarioPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findFuncionarioPorNome(@RequestParam(value = "nome") String nome) {

		final Collection<Funcionario> listaFuncionario = this.getPessoaService().findFuncionarioPorNomeNaOrganizacaoUsuarioLogado(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaFuncionario, Views.FuncionarioAutoCompleteView.class);

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
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "save")
	@ResponseBody
	public ResponseBodyWrapper save(@RequestBody PessoaVH pessoaVH) throws Exception {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().save(pessoaVH), getEditView());

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
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "update")
	@ResponseBody
	public ResponseBodyWrapper update(@RequestBody PessoaVH pessoaVH) throws Exception {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().merge(pessoaVH), getEditView());

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
	 * @param nome
	 * @param idOrganizacao
	 * @return
	 */
	@RequestMapping(value = "/findColaboradorPorNomeAndOrganizacao", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findColaboradorPorNomeAndOrganizacao(@RequestParam(value = "nome") String nome, @RequestParam(value = "idOrganizacao") Long idOrganizacao) {

		final Collection<Colaborador> listaColaborador = this.getPessoaService().findColaboradorPorNomeAndOrganizacao(nome, idOrganizacao);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaColaborador, Views.ColaboradorAutoCompleteView.class);

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
	 * Método responsável por remover a entidade<code>Colaborador</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param colaborador
	 *
	 * @return responseBody
	 */
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeColaborador/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeColaborador(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeColaborador(id), getEditView());

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
	 * Método responsável por remover a entidade<code>OrgaoExterno</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return responseBody
	 */
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeOrgaoExterno/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeOrgaoExterno(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeOrgaoExterno(id), getEditView());

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
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removePortador/{id}")
	@ResponseBody
	public ResponseBodyWrapper removePortador(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removePortador(id), getEditView());

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
	 * Método responsável por remover a entidade <code>Fornecedor</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param id
	 *
	 * @return responseBody
	 */
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeFornecedor/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeFornecedor(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeFornecedor(id), getEditView());

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
	 * Método responsável por remover a entidade<code>FornecedorObservacao</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param colaborador
	 *
	 * @return responseBody
	 */
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeFornecedorObservacao/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeFornecedorObservacao(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeFornecedorObservacao(id), getEditView());

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
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeFornecedorRamoAtividade/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeFornecedorRamoAtividade(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeFornecedorRamosAtividade(id), getEditView());

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeSeguradora/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeSeguradora(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeSeguradora(id), getEditView());

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
	 * Método responsável por remover a entidade <code>Cliente</code>
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 *
	 * @return responseBody
	 */
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeCliente/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeCliente(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeCliente(id), getEditView());

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
	 * Método responsável por remover a entidade <code>Funcionario</code>
	 *
	 * @author iago.almeida
	 *
	 * @param id
	 *
	 * @return responseBody
	 */
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeFuncionario/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeFuncionario(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeFuncionario(id), getEditView());

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
	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/removeContato/{id}")
	@ResponseBody
	public ResponseBodyWrapper removeContato(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().removeContato(id), getEditView());

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
	 * Método responsável por buscar uma pessoa que seja um usuário no sistema
	 *
	 * @author iago.almeida
	 *
	 * @param nome
	 * @return Collection<Colaborador
	 */
	@RequestMapping(value = "/findPessoaColaboradorUsuarioPorNome", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findPessoaColaboradorUsuarioPorNome(@RequestParam(value = "nome") String nome) {

		final Collection<Colaborador> listaPessoa = this.getPessoaService().findPessoaColaboradorUsuarioPorNome(nome);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(listaPessoa, Views.PessoaEditView.class);

		return responseBody;
	}


	/**
	 * Retorna o valor do atributo <code>pessoaService</code>
	 *
	 * @return <code>PessoaService</code>
	 */
	public PessoaService getPessoaService() {

		return pessoaService;
	}

	@Override
	public Class<Views.PessoaEditView> getEditView() {

		return Views.PessoaEditView.class;
	}

	@Override
	public Class<Views.PessoaListViewListView> getListView() {

		return Views.PessoaListViewListView.class;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por bloquear o registro, salvando o modelo com os dados de bloqueio preenchidas
	 *
	 * @author gilberto.nery
	 *
	 * @param pessoa
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/bloquearRegistro")
	@ResponseBody
	public ResponseBodyWrapper bloquearRegistro(@RequestBody Pessoa pessoa) throws Exception {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(this.getPessoaService().bloquearPessoa(pessoa), getEditView());

		return responseBody;
	}

}