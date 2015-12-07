package br.com.centralit.framework.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.json.ResponseBodyWrapper;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.json.Views.GenericView;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;
import br.com.centralit.framework.service.arquitetura.GenericService;
import br.com.centralit.framework.util.UtilObjeto;
import br.com.centralit.framework.view.GridVH;
import br.com.centralit.framework.view.ResultResponseVH;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class GenericController<T extends PersistentObject> {
	

	protected GenericService<T, Long> genericService;

	@Autowired
	@Qualifier("usuarioService")
	private GenericService usuarioService;

	public Class<? extends Views.GenericView> getListView() {

		return Views.GenericView.class;
	}

	public Class<? extends Views.GenericView> getEditView() {

		return Views.GenericView.class;
	}

	public Class<? extends Views.GenericView> getAutoCompleteView() {

		return this.getEditView();
	}

	public GenericController( GenericService<T, Long> genericService ) {

		this.genericService = genericService;
		
		
	}

	public GenericController() {

	}

	@RequestMapping(value = "/getPage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBodyWrapper findGrid(@RequestBody SearchParams searchParams) {

		SearchSeven search;
		
		//tivemos que trocar o trecho de this.getTipoEntidade().getSuperclass().equals() por PersistentObjectAuditOrganizacao.class.isAssignableFrom(this.getTipoEntidade()) por causa das classes que tem heranca 
		//e o pai e que herda de PersistentObjectAuditOrganizacao

		if (PersistentObjectAuditOrganizacao.class.isAssignableFrom(this.getTipoEntidade())) {

			Usuario usuarioLogado = (Usuario) usuarioService.find(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

			search = new SearchSeven(searchParams, usuarioLogado.getOrganizacao().getId());

		} else {

			search = new SearchSeven(searchParams);
		}

		search.setResultMode(Search.RESULT_MAP);

		SearchResult searchResult = genericService.searchAndCount(search);

		// DETERMINA QUAIS OS CAMPOS VAI CONSULTAR
		GridVH gridVH = new GridVH();
		gridVH.setObjects(searchResult.getResult());
		gridVH.addTotalItensTotalPages(searchParams, Long.valueOf(searchResult.getTotalCount()));

		ResultResponseVH resultResponseVH = new ResultResponseVH(gridVH);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, this.getListView());

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper getList() {

		ResultResponseVH<List<T>> resultResponseVH = new ResultResponseVH<List<T>>(genericService.findAll());

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, Views.LookupView.class);

		return responseBody;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBodyWrapper getObject(@PathVariable("id") Long id) {

		ResultResponseVH<T> resultResponseVH = new ResultResponseVH<T>(genericService.getReference(id));

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(resultResponseVH, this.getEditView());

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseBody
	public ResponseBodyWrapper save(@RequestBody T objeto) throws Exception {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(genericService.save(objeto), getEditView());

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseBody
	public ResponseBodyWrapper update(@RequestBody T objeto) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(genericService.merge(objeto), getEditView());

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseBody
	public ResponseBodyWrapper delete(@PathVariable("id") Long id) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(genericService.removeById(id), getEditView());

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeAll")
	@ResponseBody
	public ResponseBodyWrapper removeAll(@RequestBody List<Long> ids) {

		genericService.removeByIdsList(ids);

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(Boolean.TRUE, getEditView());

		return responseBody;
	}

	@RequestMapping(value = "/findAutoComplete", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseBodyWrapper findAutoComplete(@RequestParam(value = "chave") String chave, @RequestParam(value = "valor") String valor) {

		ResponseBodyWrapper responseBody = new ResponseBodyWrapper(genericService.findAutoComplete(chave, valor), getAutoCompleteView());

		return responseBody;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/existeVinculo")
	@ResponseBody
	public ResponseBodyWrapper existeVinculo(@RequestParam("joinClass") String joinClass, @RequestParam(value = "id", required = false) Long id, @RequestParam(value = "idOrganizacao", required = false) Long idOrganizacao, @RequestParam(value = "ids", required = false) String idsStr) {

		ResponseBodyWrapper responseBody;

		if (StringUtils.isEmpty(idsStr)) {

			if (UtilObjeto.isReferencia(idOrganizacao)) {

				responseBody = new ResponseBodyWrapper(genericService.existeVinculo(joinClass, id, idOrganizacao), GenericView.class);

			} else {

				responseBody = new ResponseBodyWrapper(genericService.existeVinculo(joinClass, id), GenericView.class);
			}

		} else {

			List<Long> ids = new ArrayList<Long>();

			String[] idsString = idsStr.split(",");

			for (int i = 0; i < idsString.length; i++) {

				ids.add(Long.parseLong(idsString[i]));
			}

			responseBody = new ResponseBodyWrapper(genericService.existeVinculo(joinClass, ids), GenericView.class);
		}

		return responseBody;
	}

	/**
	 * Retorna o tipo da entidade. O tipo é recuperado a partir do generics.
	 *
	 * @return <code>T</code>
	 */
	protected Class<T> getTipoEntidade() {

		final Type type[] = ( (ParameterizedType) this.getClass().getGenericSuperclass() ).getActualTypeArguments();

		return (Class<T>) type[0];
	}
	

}
