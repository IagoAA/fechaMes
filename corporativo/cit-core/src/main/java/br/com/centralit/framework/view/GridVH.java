package br.com.centralit.framework.view;

import java.util.List;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.SearchParams;

import com.fasterxml.jackson.annotation.JsonView;

public class GridVH {
	@JsonView({ Views.GenericView.class })
	private Long totalPages;
	@JsonView({ Views.GenericView.class })
	private Long totalItens;
	@JsonView({ Views.GenericView.class })
	private List objects;


	public List getObjects() {
		return objects;
	}

	public void setObjects(List objects) {
		this.objects = objects;
	}

	public Long getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(Long totalItens) {
		this.totalItens = totalItens;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public void addTotalItensTotalPages(SearchParams searchParams, Long totalItens){
		this.totalItens = totalItens;
		this.totalPages = ((totalItens + searchParams.getLimit() - 1) / searchParams.getLimit());
	}
}
