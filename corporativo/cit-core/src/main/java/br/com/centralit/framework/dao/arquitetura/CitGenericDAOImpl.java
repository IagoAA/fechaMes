package br.com.centralit.framework.dao.arquitetura;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilDate;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;

@SuppressWarnings("rawtypes")
public class CitGenericDAOImpl extends GenericDAOImpl<PersistentObject, Long> implements CitGenericDAO {

	public static Logger LOG = Logger.getLogger(Thread.currentThread().getClass().getName());

	public CitGenericDAOImpl( Class classs ) {

		super();
		this.persistentClass = classs;
	}

	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {

		super.setEntityManager(entityManager);
	}

	@Override
	@Autowired
	public void setSearchProcessor(JPASearchProcessor searchProcessor) {

		super.setSearchProcessor(searchProcessor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PersistentObject getReference(Long id, Class classs) {

		return (PersistentObject) em().createQuery("FROM " + classs.getName() + " where id = " + id).getSingleResult();
	}

	// TODO PENSAR EM UMA QUANTIDADE MENOR DE METODOS DE REMOÇÃO LÓGICA
	@Override
	public boolean remove(PersistentObject entity) {

		entity.setDataInativo(UtilDate.getCalendarDaDataAtual());

		return true;
	}

	@Override
	public void remove(PersistentObject... entities) {

		for (PersistentObject persistentObject : entities) {
			remove(persistentObject);
		}
	}

	@Override
	public boolean removeById(Long id) {

		return remove(find(id));
	}

	@Override
	public void removeByIds(Long... ids) {

		for (Long idObject : ids) {
			remove(find(idObject));
		}
	}

	@Override
	public void remove(Long id, Class classs) {

		getReference(id, classs).setDataInativo(UtilDate.getCalendarDaDataAtual());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeList(List<PersistentObject> listEntity) {

		for (PersistentObject persistentObject : listEntity) {
			remove(persistentObject);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeByIdsList(List<Long> ids) {

		for (Long idObject : ids) {
			remove(getReference(idObject));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersistentObject> findAll(Class classs) {
		SearchSeven searchSeven = new SearchSeven(classs, true);

		return search(searchSeven);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersistentObject> findAll(Class classs, boolean trazerBloqueados) {
		SearchSeven searchSeven = new SearchSeven(classs, trazerBloqueados);

		return search(searchSeven);
	}

	@Override
	public List<PersistentObject> findAll() {
		SearchSeven searchSeven = new SearchSeven(true);
		return this.search(searchSeven);
	}

	@Override
	public List<PersistentObject> findAll(boolean trazerBloqueados) {
		SearchSeven searchSeven = new SearchSeven(trazerBloqueados);
		return this.search(searchSeven);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <RT> List<RT> search(ISearch search, Class classs) {

		if (search == null)
			return (List<RT>) findAll();
		return _search(classs, search);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <RT> SearchResult<RT> searchAndCount(ISearch search, Class classs) {

		if (search == null) {
			SearchResult<RT> result = new SearchResult<RT>();
			result.setResult((List<RT>) findAll());
			result.setTotalCount(result.getResult().size());
			return result;
		}
		return _searchAndCount(classs, search);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <RT> RT searchUnique(ISearch search, Class classs) {

		return (RT) _searchUnique(classs, search);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PersistentObject> findList(List<Long> ids) {

		return Arrays.asList(find(ids.toArray(new Long[ids.size()])));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PersistentObject> getReferencesList(List<Long> ids) {

		return Arrays.asList(getReferences(ids.toArray(new Long[ids.size()])));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persistList(List<PersistentObject> listEntity) {

		persist(listEntity.toArray(new PersistentObject[listEntity.size()]));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PersistentObject> mergeList(List<PersistentObject> listEntity) {

		return Arrays.asList(merge(listEntity.toArray(new PersistentObject[listEntity.size()])));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PersistentObject> saveList(List<PersistentObject> listEntity) {

		return Arrays.asList(save(listEntity.toArray(new PersistentObject[listEntity.size()])));
	}

	@Override
	public void refreshList(List<PersistentObject> listEntity) {

		refresh(listEntity.toArray(new PersistentObject[listEntity.size()]));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersistentObject> findAutoComplete(String chave, String valor) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike(chave, "%" + valor + "%");

		search.setMaxResults(10);

		return _search(this.persistentClass, search);
	}

	public Boolean existeVinculo(String joinClass, Long id) {

		SearchSeven search = new SearchSeven();

		search.addFilterIn(joinClass, id);

		return this.count(search) > 0;
	}

	public Boolean existeVinculo(String joinClass, List<Long> ids) {

		SearchSeven search = new SearchSeven();

		search.addFilterIn(joinClass, ids);

		return this.count(search) > 0;
	}

	public Boolean existeVinculo(String joinClass, Long id, Long idOrganizacao) {

		SearchSeven search = new SearchSeven();

		search.addFilterOr(Filter.equal("organizacao.id", idOrganizacao), Filter.equal("id", idOrganizacao));

		search.addFilterIn(joinClass, id);

		return this.count(search) > 0;
	}

	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		PersistentObject obj = getReferenceByExample(entity);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}

	}

	@Override
	public PersistentObject getReferenceByExample(PersistentObject entity) {

		SearchSeven search = new SearchSeven();

		search.addFilter(this.getFilterFromExample(entity));

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		return obj;
	}

	@Override
	public PersistentObject mergeIfNotExist(PersistentObject entity) {

		PersistentObject obj = getReferenceByExample(entity);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.merge(entity);

		}
	}

	@Override
	public PersistentObject buscarUltimoRegistroComOrdenadoParametrizada(String property, Long idOrganizacao) {

		Search search = new Search();
		search.addFilterNotNull(property);
		search.addFilterEqual("organizacao.id", idOrganizacao);
		search.addSortDesc(property);
		search.setMaxResults(1);
		return searchUnique(search);
	}
}
