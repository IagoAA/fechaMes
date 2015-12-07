package br.com.centralit.framework.service.arquitetura;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.Validator;

import br.com.centralit.framework.model.arquitetura.PersistentObject;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs.
 *
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) managers for your domain objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by jgarcia: added full text search + reindexing
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericService<T, PK extends Serializable> {

	public PersistentObject getReference(Long id, @SuppressWarnings("rawtypes") Class classs);

	public List<T> findAll(Class classs);

	public <RT> List<RT> search(ISearch search, Class classs);

	public <RT> RT searchUnique(ISearch search, Class classs);

	public <RT> SearchResult<RT> searchAndCount(ISearch search, Class classs);

	public T mergeValidate(T entity, Validator customValidator);

	public T saveValidate(T entity, Validator customValidator);

	/**
	 * <p>
	 * Get the entity with the specified type and id from the datastore.
	 *
	 * <p>
	 * If none is found, return null.
	 */
	public T find(Long id);

	/**
	 * Get all entities of the specified type from the datastore that have one of these ids.
	 */
	public T[] find(Long[] ids);

	/**
	 * <p>
	 * Get a reference to the entity with the specified type and id from the datastore.
	 *
	 * <p>
	 * This does not require a call to the datastore and does not populate any of the entity's values. Values may be fetched lazily at a later time. This increases performance if a another entity is being saved that should reference this entity but the values of this entity are not needed.
	 *
	 * @throws a
	 *             HibernateException if no matching entity is found
	 */
	public T getReference(Long id);

	/**
	 * <p>
	 * Get a reference to the entities of the specified type with the given ids from the datastore.
	 *
	 * <p>
	 * This does not require a call to the datastore and does not populate any of the entities' values. Values may be fetched lazily at a later time. This increases performance if a another entity is being saved that should reference these entities but the values of these entities are not needed.
	 *
	 * @throws a
	 *             HibernateException if any of the matching entities are not found.
	 */
	public T[] getReferences(Long[] ids);

	/**
	 * <p>
	 * Make a transient instance persistent and add it to the datastore. This operation cascades to associated instances if the association is mapped with cascade="persist". Throws an error if the entity already exists.
	 *
	 * <p>
	 * Does not guarantee that the object will be assigned an identifier immediately. With <code>persist</code> a datastore-generated id may not be pulled until flush time.
	 */
	public void persist(T... entities);

	/**
	 * <p>
	 * Copy the state of the given object onto the persistent object with the same identifier. If there is no persistent instance currently associated with the session, it will be loaded. Return the persistent instance. If the given instance is unsaved, save a copy and return it as a newly
	 * persistent instance.
	 *
	 * <p>
	 * The instance that is passed in does not become associated with the session. This operation cascades to associated instances if the association is mapped with cascade="merge".
	 */
	public T merge(T entity);

	/**
	 * <p>
	 * Copy the state of the given objects onto the persistent objects with the same identifier. If there is no persistent instance currently associated with the session, it will be loaded. Return the persistent instances. If a given instance is unsaved, save a copy and return it as a newly
	 * persistent instance.
	 *
	 * <p>
	 * The instances that are passed in do not become associated with the session. This operation cascades to associated instances if the association is mapped with cascade="merge".
	 */
	public T[] merge(T... entities);

	/**
	 * If an entity with the same ID already exists in the database, merge the changes into that entity. If not persist the given entity. In either case, a managed entity with the changed values is returned. It may or may not be the same object as was passed in.
	 */
	public T save(T entity);

	/**
	 * <p>
	 * For each entity: If an entity with the same ID already exists in the database, merge the changes into that entity. If not persist the given entity. In either case, a managed entity with the changed values is returned. It may or may not be the same object as was passed in.
	 *
	 * @return an array containing each managed entity corresponding to the entities passed in.
	 */
	public T[] save(T... entities);

	/**
	 * Remove the specified entity from the datastore.
	 *
	 * @return <code>true</code> if the entity is found in the datastore and removed, <code>false</code> if it is not found.
	 */
	public boolean remove(T entity);

	/**
	 * Remove all of the specified entities from the datastore.
	 */
	public void remove(T... entities);

	/**
	 * Remove the entity with the specified type and id from the datastore.
	 *
	 * @return <code>true</code> if the entity is found in the datastore and removed, <code>false</code> if it is not found.
	 */
	public boolean removeById(Long id);

	/**
	 * Remove all the entities of the given type from the datastore that have one of these ids.
	 */
	public void removeByIds(Long... ids);

	/**
	 * Get a list of all the objects of the specified type.
	 */
	public List<T> findAll();

	/**
	 * Search for entities given the search parameters in the specified <code>ISearch</code> object.
	 *
	 * @param RT
	 *            The result type is automatically determined by the context in which the method is called.
	 */
	public <RT> List<RT> search(ISearch search);

	/**
	 * Search for a single entity using the given parameters.
	 *
	 * @param RT
	 *            The result type is automatically determined by the context in which the method is called.
	 */
	public <RT> RT searchUnique(ISearch search);

	/**
	 * Returns the total number of results that would be returned using the given <code>ISearch</code> if there were no paging or maxResults limits.
	 */
	public int count(ISearch search);

	/**
	 * Returns a <code>SearchResult</code> object that includes both the list of results like <code>search()</code> and the total length like <code>count()</code>.
	 *
	 * @param RT
	 *            The result type is automatically determined by the context in which the method is called.
	 */
	public <RT> SearchResult<RT> searchAndCount(ISearch search);

	/**
	 * Returns <code>true</code> if the object is connected to the current Hibernate session.
	 */
	public boolean isAttached(T entity);

	/**
	 * Refresh the content of the given entity from the current datastore state.
	 */
	public void refresh(T... entities);

	/**
	 * Flushes changes in the Hibernate session to the datastore.
	 */
	public void flush();

	/**
	 * Generates a search filter from the given example using default options.
	 */
	public Filter getFilterFromExample(T example);

	/**
	 * Generates a search filter from the given example using the specified options.
	 */
	public Filter getFilterFromExample(T example, ExampleOptions options);


	public List<T> findList (List<Long> ids);

	public List<T> getReferencesList (List<Long> ids);

	public void persistList(List<T> listEntity);

	public List<PersistentObject> mergeList(List<T> listEntity);

	public List<T> saveList(List<T> listEntity);

	public void removeList(List<T> listEntity);

	public void removeByIdsList(List<Long> ids);

	public void refreshList (List<T> listEntity);

	public List<PersistentObject> findAutoComplete(String chave, String valor);

	public Boolean existeVinculo(String joinClass, Long id);

	public Boolean existeVinculo(String joinClass, List<Long> ids);

	public Boolean existeVinculo(String joinClass, Long id, Long idOrganizacao);

	public T saveIfNotExist(PersistentObject entity);

	public T mergeIfNotExist(PersistentObject entity);

	public void saveListIfNotExist(List<T> listEntity);

	/**
	 * Retorna todos os registros com parametro que diz se é pra trazer registros bloqueados (com dataBloqueio) ou não.
	 *
	 * @param trazerBloqueados <code>true</code> se deseja que a busca traga os registros bloqueados e <code>false</code> caso não deseje
	 */
	List<T> findAll(boolean trazerBloqueados);

	/**
	 * Retorna todos os registros com parametro que diz se é pra trazer registros bloqueados (com dataBloqueio) ou não.
	 *
	 * @param classs
	 * @param trazerBloqueados <code>true</code> se deseja que a busca traga os registros bloqueados e <code>false</code> caso não deseje
	 */
	List<T> findAll(@SuppressWarnings("rawtypes") Class classs, boolean trazerBloqueados);

}
