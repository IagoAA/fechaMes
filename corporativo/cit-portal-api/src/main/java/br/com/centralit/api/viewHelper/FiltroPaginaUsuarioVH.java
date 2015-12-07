package br.com.centralit.api.viewHelper;

import java.io.Serializable;

import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.SearchParams;

public class FiltroPaginaUsuarioVH implements Serializable{

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2342505270077662292L;

	private Pagina pagina;

	private SearchParams searchParams;

	/**
	 * Retorna o valor do atributo <code>pagina</code>
	 * 
	 * @return <code>Pagina</code>
	 */
	public Pagina getPagina() {

		return pagina;
	}

	/**
	 * Define o valor do atributo <code>pagina</code>.
	 * 
	 * @param pagina
	 */
	public void setPagina(Pagina pagina) {

		this.pagina = pagina;
	}

	/**
	 * Retorna o valor do atributo <code>searchParams</code>
	 * 
	 * @return <code>SearchParams</code>
	 */
	public SearchParams getSearchParams() {

		return searchParams;
	}

	/**
	 * Define o valor do atributo <code>searchParams</code>.
	 * 
	 * @param searchParams
	 */
	public void setSearchParams(SearchParams searchParams) {

		this.searchParams = searchParams;
	}

}
