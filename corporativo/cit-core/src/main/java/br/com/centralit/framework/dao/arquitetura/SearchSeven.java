package br.com.centralit.framework.dao.arquitetura;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.util.ConstantsQuery;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilDate;
import br.com.centralit.framework.util.UtilObjeto;
import br.com.centralit.framework.util.UtilString;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

public class SearchSeven extends Search{

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 7670366194148390992L;

	/**
	 * Cria SearchSeven com parametros que não retornam registros bloqueados.
	 * 
	 */
	public SearchSeven() {
		super();

		//Retira os dados inativos (excluidos) da busca
 		this.addFilterAnd(Filter.isNull(br.com.centralit.framework.model.Filter.FIELD_DATA_INATIVO));
 		//Retira os dados bloqueados da busca
 		this.addFilterAnd(Filter.or(Filter.isNull("dataBloqueio"), Filter.greaterThan("dataBloqueio", UtilDate.getCalendarDaDataAtual())));
	}
	
	/**
	 * Cria SearchSeven com parametro que diz se é pra trazer registros bloqueados (com dataBloqueio ) ou não.
	 * 
	 * @param trazerBloqueados <code>true</code> se deseja que a busca traga os registros bloqueados e <code>false</code> casp não deseje
	 */
	public SearchSeven(boolean trazerBloqueados) {
		super();

		//Retira os dados inativos (excluidos) da busca
 		this.addFilterAnd(Filter.isNull(br.com.centralit.framework.model.Filter.FIELD_DATA_INATIVO));
 		
 		if (!trazerBloqueados) {
 			//Retira os dados bloqueados da busca
 	 		this.addFilterAnd(Filter.or(Filter.isNull("dataBloqueio"), Filter.greaterThan("dataBloqueio", UtilDate.getCalendarDaDataAtual())));
 		}
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param searchClass
	 */
	public SearchSeven(Class<?> searchClass) {
		super(searchClass);

		//Retira os dados inativos (excluidos) da busca
 		this.addFilterAnd(Filter.isNull(br.com.centralit.framework.model.Filter.FIELD_DATA_INATIVO));
 		//Retira os dados bloqueados da busca
 		this.addFilterAnd(Filter.or(Filter.isNull("dataBloqueio"), Filter.greaterThan("dataBloqueio", UtilDate.getCalendarDaDataAtual())));
	}
	
	/**
	 * Cria SearchSeven com parametro que diz se é pra trazer registros bloqueados (com dataBloqueio ) ou não.
	 * 
	 * @param searchClass
	 * @param trazerBloqueados <code>true</code> se deseja que a busca traga os registros bloqueados e <code>false</code> casp não deseje
	 */
	public SearchSeven(Class<?> searchClass, boolean trazerBloqueados) {
		super(searchClass);

		//Retira os dados inativos (excluidos) da busca
 		this.addFilterAnd(Filter.isNull(br.com.centralit.framework.model.Filter.FIELD_DATA_INATIVO));
 		
 		if (!trazerBloqueados) {
 			//Retira os dados bloqueados da busca
 	 		this.addFilterAnd(Filter.or(Filter.isNull("dataBloqueio"), Filter.greaterThan("dataBloqueio", UtilDate.getCalendarDaDataAtual())));
 		}
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param searchParams
	 */
	public SearchSeven(SearchParams searchParams) {
		// 	DEFINE O SORTE DO SORT
		if(searchParams.getDir().equals("asc")){
			this.addSortAsc(searchParams.getSort());
		}else{
			this.addSortDesc(searchParams.getSort());
		}

		// CRIA OS FIELDS
		if(searchParams.getFields() != null){
			for (String field : searchParams.getFields()) {
				this.addField(field);
			}
		}
		
		boolean verificaBloqueio = true;
		// CRIA OS FILTROS
 		if(searchParams.getFilters() != null){

 			//Verifica se a busca é genérica
 			String keywordValue = searchParams.getKeywordValue();
 			if (keywordValue != null && !keywordValue.isEmpty()) {
 				criaFiltrosGeral(keywordValue, searchParams.getFilters());
 			} else {
 				// Lista utilizada para guardar filtros que utilizem o comparacao equal com varios valores
 				List<Filter> filtersEqualWithOr = new ArrayList<Filter>();
 				for (br.com.centralit.framework.model.Filter filter : searchParams.getFilters()) {
 					if(filter.getValue() != null && !filter.getValue().equals("")){
 						if(filter.getType().equals(ConstantsQuery.TYPE_STRING)){
 							// TODO Removeu o TO_UPPER_CASE para a consulta ILIKE funcionar
 							this.addFilterILike(filter.getField(), "%" + filter.getValue() + "%");
 						}else if(filter.getType().equals(ConstantsQuery.TYPE_NUMERIC)){
 							if(filter.getComparison().equals(ConstantsQuery.COMPARE_EQUALS)){
 								this.addFilterEqual(filter.getField(), filter.getValue());
 							}else if(filter.getComparison().equals(ConstantsQuery.COMPARE_LOWER_THEN)){
 								this.addFilterLessThan(filter.getField(), filter.getValue());
 							}else if(filter.getComparison().equals(ConstantsQuery.COMPARE_GREATER_THEN)){
 								this.addFilterGreaterThan(filter.getField(), filter.getValue());
 							}else if(filter.getComparison().equals(ConstantsQuery.COMPARE_NOT_EQUALS)){
 								this.addFilterNotEqual(filter.getField(), filter.getValue());
 							}else if(filter.getComparison().equals(ConstantsQuery.COMPARE_EQUALS_WITH_OR)){
 								filtersEqualWithOr.add(Filter.equal(filter.getField(), filter.getValue()));
 							}
 						}else if(filter.getType().equals(ConstantsQuery.TYPE_BOOLEAN)){
 							this.addFilterEqual(filter.getField(), new Boolean(filter.getValue()));
 						}else if(filter.getType().equals(ConstantsQuery.TYPE_DATE)){
 							String dataText = filter.getValue().substring(0, 10);

 							if(filter.getComparison().equals(ConstantsQuery.COMPARE_EQUALS)){
 								this.addFilterGreaterOrEqual(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataText + " 00:00:00")));
 								this.addFilterLessOrEqual(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataText + " 23:59:59")));
 							}else if(filter.getComparison().equals(ConstantsQuery.COMPARE_LOWER_THEN)){
 								this.addFilterLessThan(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataText + " 23:59:59")));
 							}else if(filter.getComparison().equals(ConstantsQuery.COMPARE_GREATER_THEN)){
 								this.addFilterGreaterThan(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataText + " 00:00:00")));
 							}
 						}else if(filter.getType().equals(ConstantsQuery.TYPE_MONEY)){
 							BigDecimal valor = new BigDecimal(filter.getValue().toString());
 							this.addFilterEqual(filter.getField(), UtilObjeto.isReferencia(valor) ? valor : BigDecimal.valueOf(0));
 						}
 					}else if(filter.getType().equals(ConstantsQuery.TYPE_NUMERIC_RANGE)){
 						if(!StringUtils.isEmpty(filter.getValueMin()) && !StringUtils.isEmpty(filter.getValueMax())){
 							this.addFilterAnd(Filter.greaterOrEqual(filter.getField(), filter.getValueMin()), Filter.lessOrEqual(filter.getField(), filter.getValueMax()));
 						}else{
 							if(!StringUtils.isEmpty(filter.getValueMin())){
 								this.addFilterGreaterOrEqual(filter.getField(), filter.getValueMin());
 							}else if(!StringUtils.isEmpty(filter.getValueMax())){
 								this.addFilterLessOrEqual(filter.getField(), filter.getValueMax());
 							}
 						}
					}else if(filter.getType().equals(ConstantsQuery.TYPE_DATE_RANGE)){
						if(!StringUtils.isEmpty(filter.getValueMin()) && !StringUtils.isEmpty(filter.getValueMax())){
							String dataMin = filter.getValueMin().substring(0, 10);
							String dataMax = filter.getValueMax().substring(0, 10);
							this.addFilterAnd(Filter.greaterOrEqual(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataMin + " 00:00:00"))), Filter.lessOrEqual(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataMax + " 23:59:59"))));
						}else{
 							if(!StringUtils.isEmpty(filter.getValueMin())){
 								String dataMin = filter.getValueMin().substring(0, 10);
 								this.addFilterGreaterOrEqual(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataMin + " 00:00:00")));
 							}else if(!StringUtils.isEmpty(filter.getValueMax())){
 								String dataMax = filter.getValueMax().substring(0, 10);
 								this.addFilterLessOrEqual(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(dataMax + " 23:59:59")));
 							}
 						}
					}else if(filter.getComparison().equals(ConstantsQuery.COMPARE_EMPTY)){
							this.addFilterEmpty(filter.getField());
						}
 				}
 				if(!UtilColecao.isVazio(filtersEqualWithOr)){

 					Filter [] filters = new Filter[filtersEqualWithOr.size()];

 					for(int i = 0; i < filtersEqualWithOr.size(); i++){

 						filters[i] = Filter.equal(filtersEqualWithOr.get(i).getProperty(), filtersEqualWithOr.get(i).getValue());

 					}
 					this.addFilterOr(filters);
 				}
 			}
 			
 			verificaBloqueio = false;
		}

 		//Retira os dados inativos (excluidos) da busca
 		this.addFilterAnd(Filter.isNull(br.com.centralit.framework.model.Filter.FIELD_DATA_INATIVO));
 		
 		if (verificaBloqueio) {
 			this.addFilterAnd(Filter.or(Filter.isNull("dataBloqueio"), Filter.greaterThan("dataBloqueio", UtilDate.getCalendarDaDataAtual())));
 		}

		this.setFirstResult(searchParams.getStart());
		this.setMaxResults(searchParams.getLimit());
	}
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param searchParams
	 * @param idOrganizacao
	 */
	public SearchSeven(SearchParams searchParams, Long idOrganizacao) {
		
		this(searchParams);
		
		this.addFilterEqual("organizacao.id", idOrganizacao);
	}
	
	/**
	 * Responsável pela criação de novas instâncias desta classe
	 * @param searchParams
	 * @param isMobile
	 */
	public SearchSeven(SearchParams searchParams, Boolean isMobile) {
		
		//	DEFINE O SORTE DO SORT
		if(searchParams.getDir().equals("asc")){
			this.addSortAsc(searchParams.getSort());
		}else{
			this.addSortDesc(searchParams.getSort());
		}

			// CRIA OS FILTROS
 		if(searchParams.getFilters() != null){

 			//Verifica se a busca é genérica
 			String keywordValue = searchParams.getKeywordValue();
 			if (keywordValue != null && !keywordValue.isEmpty()) {
 				criaFiltrosGeral(keywordValue, searchParams.getFilters());
 			} else {
 				for (br.com.centralit.framework.model.Filter filter : searchParams.getFilters()) {
 					if(filter.getValue() != null && !filter.getValue().equals("")){
 						if(filter.getType().equals(ConstantsQuery.TYPE_NUMERIC)){
 							if(filter.getComparison().equals(ConstantsQuery.COMPARE_EQUALS)){
 								this.addFilterEqual(filter.getField(), filter.getValue());
 							}
 						}
 					}	
 				}
 			}
 		}
 		
 		//Retira os dados inativos (excluidos) da busca
 		this.addFilterAnd(Filter.isNull(br.com.centralit.framework.model.Filter.FIELD_DATA_INATIVO));
 		
		this.setFirstResult(searchParams.getStart());
		this.setMaxResults(searchParams.getLimit());
	}

	
	/**
	 * <p><b>Iniciativa(s): 562</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por criar filtros genéricos a partir dos filtros definidos para o dominio utilizando apenas uma palavra chave como valor de busca.
	 *
	 * @author geovane.filho
	 *
	 * @param keywordValue Palavra-chave utilizada na busca.
	 * @param filters Filtros definidos para o Dominio a se buscar.
	 */
	private void criaFiltrosGeral(String keywordValue, List<br.com.centralit.framework.model.Filter> filters) {
		List<Filter> filtros = new ArrayList<Filter>();

		for (br.com.centralit.framework.model.Filter filter : filters) {
			if (filter.getType().equals(ConstantsQuery.TYPE_STRING)) filtros.add(createFilterString(keywordValue, filter));
			if (filter.getType().equals(ConstantsQuery.TYPE_NUMERIC) || filter.getType().equals(ConstantsQuery.TYPE_NUMERIC_RANGE)) filtros.add(createFilterNumeric(keywordValue, filter));
			if (filter.getType().equals(ConstantsQuery.TYPE_DATE) || filter.getType().equals(ConstantsQuery.TYPE_DATE_RANGE)) filtros.add(createFilterDate(keywordValue, filter));
		}

		Filter filtroOr = null;
		for (Filter filtro : filtros) {
			if (filtro != null) {
				if (filtroOr == null) {
					filtroOr = filtro;
				} else {
					filtroOr = Filter.or(filtroOr, filtro);
				}
			}
		}
		this.addFilter(filtroOr);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por
	 *
	 * @author geovane.filho
	 *
	 * @param keywordValue Palava-Chave a ser utilizada no filtro
	 * @param filter <code>br.com.centralit.framework.model.Filter</code> contendo as informações para criar o <code>Filter</code>
	 */
	private Filter createFilterDate(String keywordValue, br.com.centralit.framework.model.Filter filter) {
		Filter filterGoogle = null;
		Date data = null;
		if (UtilString.isDateFormatted(keywordValue)) {
			data = UtilDate.stringTimestampToDate(keywordValue);
		}

		if (data == null) return filterGoogle;

		Integer operador = null;
		if (filter.getComparison().equals(ConstantsQuery.COMPARE_EQUALS)) operador = Filter.OP_EQUAL;
		if (filter.getComparison().equals(ConstantsQuery.COMPARE_LOWER_THEN)) operador = Filter.OP_LESS_THAN;
		if (filter.getComparison().equals(ConstantsQuery.COMPARE_GREATER_THEN)) operador = Filter.OP_GREATER_THAN;

		if (operador != null && operador == Filter.OP_EQUAL) {
			Filter greaterOrEqual = new Filter(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(keywordValue + " 00:00:00")), Filter.OP_GREATER_OR_EQUAL);
			Filter lessOrEqual = new Filter(filter.getField(), UtilDate.dateToCalendar(UtilDate.getData(keywordValue + " 23:59:59")), Filter.OP_LESS_OR_EQUAL);
			filterGoogle = Filter.and(greaterOrEqual, lessOrEqual);
		} else if (operador != null) {
			filterGoogle = new Filter(filter.getField(), UtilDate.dateToCalendar(data), operador);
		}

		return filterGoogle;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por criar um <code>Filter</code> Númerico para ser utilizado no <code>Search</code>
	 *
	 * @author geovane.filho
	 *
	 * @param keywordValue Palava-Chave a ser utilizada no filtro
	 * @param filter <code>br.com.centralit.framework.model.Filter</code> contendo as informações para criar o <code>Filter</code>
	 *
	 * @return <code>Filter</code> criado ou <code>null</code> caso algum dos parametros esteja nulo ou vazio.
	 */
	private Filter createFilterNumeric(String keywordValue, br.com.centralit.framework.model.Filter filter) {
		Filter filterGoogle = null;

		if (!UtilString.isNumber(keywordValue)) return filterGoogle;

		Integer operador = null;
		if (filter.getComparison().equals(ConstantsQuery.COMPARE_EQUALS)) operador = Filter.OP_EQUAL;
		if (filter.getComparison().equals(ConstantsQuery.COMPARE_LOWER_THEN)) operador = Filter.OP_LESS_THAN;
		if (filter.getComparison().equals(ConstantsQuery.COMPARE_GREATER_THEN)) operador = Filter.OP_GREATER_THAN;
		if (filter.getComparison().equals(ConstantsQuery.COMPARE_NOT_EQUALS)) operador = Filter.OP_NOT_EQUAL;

		if (operador != null) {
			filterGoogle = new Filter(filter.getField(), keywordValue, operador);
		}
		return filterGoogle;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por criar um <code>Filter</code> de String para ser utilizado no <code>Search</code>
	 *
	 * @author geovane.filho
	 *
	 * @param keywordValue Palava-Chave a ser utilizada no filtro
	 * @param filter <code>br.com.centralit.framework.model.Filter</code> contendo as informações para criar o <code>Filter</code>
	 *
	 * @return <code>Filter</code> criado ou <code>null</code> caso algum dos parametros esteja nulo ou vazio.
	 */
	private Filter createFilterString(String keywordValue, br.com.centralit.framework.model.Filter filter) {
		Filter filterGoogle = null;
		if (!UtilString.isNullOrEmpty(keywordValue) && filter != null) {
			filterGoogle = new Filter(filter.getField(), "%" + keywordValue + "%", Filter.OP_ILIKE);
		}
		return filterGoogle;
	}
	
}

