package br.com.centralit.framework.service.arquitetura;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.Erro;
import br.com.centralit.framework.exception.ErroValidacao;
import br.com.centralit.framework.exception.ValidacaoException;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

/**
 * This class serves as the Base class for all other Managers - namely to hold common CRUD methods that they might all use. You should only need to extend this class when your require custom CRUD logic.
 *
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 * @author <a href="mailto:allyjunio@gmail.com">Ally Junio</a>
 */
@Service("genericService")
public class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {

	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * GenericDao instance, set by constructor of child classes
	 */
	protected CitGenericDAO dao;

	protected Validator validator;

	protected Usuario getUsuario() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public GenericServiceImpl() {

	}

	public GenericServiceImpl( CitGenericDAO dao ) {

		this.dao = dao;
	}

	public GenericServiceImpl( CitGenericDAO dao, Validator validator ) {

		this(dao);
		this.validator = validator;
	}

	@Override
	public T getReference(Long id) {

		return (T) this.dao.getReference(id);
	}

	@Override
	public PersistentObject getReference(Long id, Class classs) {

		return this.dao.getReference(id, classs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(@SuppressWarnings("rawtypes") Class classs) {
		return (List<T>) this.dao.findAll(classs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(@SuppressWarnings("rawtypes") Class classs, boolean trazerBloqueados) {
		return (List<T>) this.dao.findAll(classs, trazerBloqueados);
	}

	@Override
	public <RT> List<RT> search(ISearch search, Class classs) {

		return this.dao.search(search, classs);
	}

	@Override
	public <RT> RT searchUnique(ISearch search, Class classs) {

		return this.dao.searchUnique(search, classs);
	}

	@Override
	public <RT> SearchResult<RT> searchAndCount(ISearch search, Class classs) {

		return this.dao.searchAndCount(search, classs);
	}

	@Override
	public T find(Long id) {

		return (T) this.dao.find(id);
	}

	@Override
	public T[] find(Long[] ids) {

		return (T[]) this.dao.find(ids);
	}

	@Override
	public T[] getReferences(Long[] ids) {

		return (T[]) this.dao.getReferences(ids);
	}

	@Override
	public void persist(T... entities) {

		this.dao.persist((PersistentObject[]) entities);

	}

	@Override
	public T merge(T entity) {

		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(entity, this.validator);

		}
		return (T) this.dao.merge((PersistentObject) entity);
	}

	@Override
	public T[] merge(T... entities) {

		return (T[]) this.dao.merge((PersistentObject[]) entities);
	}

	@Override
	public T save(T entity) {

		if (UtilObjeto.isReferencia(this.validator)) {

			this.validarEntidade(entity, this.validator);

		}

		return (T) this.dao.save((PersistentObject) entity);
	}

	@Override
	public T[] save(T... entities) {

		return (T[]) this.dao.save((PersistentObject[]) entities);
	}

	@Override
	public boolean remove(T entity) {

		return this.dao.remove((PersistentObject) entity);
	}

	@Override
	public void remove(T... entities) {

		this.dao.remove((PersistentObject[]) entities);
	}

	@Override
	public boolean removeById(Long id) {

		return this.dao.removeById(id);
	}

	@Override
	public void removeByIds(Long... ids) {

		this.dao.removeByIds(ids);
	}

	@Override
	public List<T> findAll() {

		return (List<T>) this.dao.findAll();
	}

	@Override
	public List<T> findAll(boolean trazerBloqueados) {
		return (List<T>) this.dao.findAll(trazerBloqueados);
	}

	@Override
	public <RT> List<RT> search(ISearch search) {

		return this.dao.search(search);
	}

	@Override
	public <RT> RT searchUnique(ISearch search) {

		return (RT) this.dao.searchUnique(search);
	}

	@Override
	public int count(ISearch search) {

		return this.dao.count(search);
	}

	@Override
	public <RT> SearchResult<RT> searchAndCount(ISearch search) {

		return this.dao.searchAndCount(search);
	}

	@Override
	public boolean isAttached(T entity) {

		return this.dao.isAttached((PersistentObject) entity);
	}

	@Override
	public void refresh(T... entities) {

		this.dao.refresh((PersistentObject[]) entities);
	}

	@Override
	public void flush() {

		this.dao.flush();
	}

	@Override
	public Filter getFilterFromExample(T example) {

		return this.dao.getFilterFromExample((PersistentObject) example);
	}

	@Override
	public Filter getFilterFromExample(T example, ExampleOptions options) {

		return this.dao.getFilterFromExample((PersistentObject) example, options);
	}

	@Override
	public T mergeValidate(T entity, Validator customValidator) {

		this.validarEntidade(entity, customValidator);

		return this.merge(entity);
	}

	@Override
	public T saveValidate(T entity, Validator customValidator) {

		this.validarEntidade(entity, customValidator);

		return this.save(entity);
	}

	/**
	 * Método responsável por retornar o tipo da entidade Generics
	 *
	 * @author wilker.machado
	 *
	 * @return <code>Class<T></code>
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getTipoEntidade() {

		final Type type[] = ( (ParameterizedType) this.getClass().getGenericSuperclass() ).getActualTypeArguments();

		return (Class<T>) type[0];
	}

	/**
	 * Método responsável por validar entidade
	 *
	 * @author wilker.machado
	 *
	 * @param entity
	 */
	public void validarEntidade(T entity, Validator customValidator) {

		BindException errors = new BindException(entity, this.getTipoEntidade().getName());

		// aqui acontece a chamada do validator
		customValidator.validate(entity, errors);

		List<ObjectError> list = errors.getAllErrors();

		// verifica se houve algum erro na validação
		if (!UtilColecao.isVazio(list)) {

			// erros para cada objeto e codigo de erro
			ArrayList<ErroValidacao> errosValidacao = new ArrayList<ErroValidacao>();

			// percorre lista de erros
			for (ObjectError objErr : list) {

				// cria objeto ErroValidacao que será recuperada no angular
				ErroValidacao erroValidacao = new ErroValidacao(objErr.getCode(), objErr.getObjectName());

				// cria o objeto ERRO com a mensagem padrão
				Erro erro = new Erro(objErr.getDefaultMessage());

				if (objErr instanceof FieldError) {

					// se for uma validacao de field com erro sera colocado o atributo no obj ErroValidação
					erro.setAtributo(( (FieldError) objErr ).getField());

					// recuperar o id na tela atraves do metodo e retorna ex: 'objeto.codigo' > padrão definido em todos os input da tela
					erro.setIdCampoTela(criaIdCampo(erro.getAtributo(), objErr.getObjectName()));

				}

				// verifica se ja existe o tipo de erro
				if (errosValidacao.contains(erroValidacao)) {

					// se existir o tipo de erro concatena com o novo atributo
					errosValidacao.get(errosValidacao.indexOf(erroValidacao)).getErros().add(erro);

				} else {

					// cria novo tipo de erro
					erroValidacao.getErros().add(erro);

					errosValidacao.add(erroValidacao);
				}

			}

			// lança a exceção com o mapa de erros
			throw new ValidacaoException(errosValidacao);
		}

	}

	/**
	 *
	 * Método responsável por criar id do campo
	 *
	 * @author wilker.machado
	 *
	 * @param label
	 * @param obj
	 * @return
	 */
	public static String criaIdCampo(String atributo, String obj) {

		Class<?> classs;

		try {

			classs = Class.forName(obj);

		} catch (ClassNotFoundException e) {

			classs = Object.class;
		}

		return classs.getSimpleName().substring(0, 1).toLowerCase(new Locale("pt", "BR")).concat(classs.getSimpleName().substring(1)).concat(".").concat(atributo);
	}

	@Override
	public List<T> findList(List<Long> ids) {

		return (List<T>) this.dao.findList(ids);
	}

	@Override
	public List<T> getReferencesList(List<Long> ids) {

		return (List<T>) this.dao.getReferencesList(ids);
	}

	@Override
	public void persistList(List<T> listEntity) {

		this.dao.persistList((List<PersistentObject>) listEntity);
	}

	@Override
	public List<PersistentObject> mergeList(List<T> listEntity) {

		return this.dao.mergeList((List<PersistentObject>) listEntity);
	}

	@Override
	public List<T> saveList(List<T> listEntity) {

		return (List<T>) this.dao.saveList((List<PersistentObject>) listEntity);
	}

	@Override
	public void removeList(List<T> listEntity) {

		this.dao.removeList((List<PersistentObject>) listEntity);
	}

	@Override
	public void removeByIdsList(List<Long> ids) {

		this.dao.removeByIdsList(ids);
	}

	@Override
	public void refreshList(List<T> listEntity) {

		this.dao.refreshList((List<PersistentObject>) listEntity);
	}


	/**
	 * Retorna o valor do atributo <code>log</code>
	 *
	 * @return <code>Log</code>
	 */
	public Log getLog() {

		return log;
	}

	@Override
	public List<PersistentObject> findAutoComplete(String chave, String valor) {

		return this.dao.findAutoComplete(chave, valor);
	}

	@Override
	public Boolean existeVinculo(String joinClass, Long id) {

		return this.dao.existeVinculo(joinClass, id);
	}

	public Boolean existeVinculo(String joinClass, List<Long> ids) {

		return this.dao.existeVinculo(joinClass, ids);
	}

	@Override
	public Boolean existeVinculo(String joinClass, Long id, Long idOrganizacao) {

		return this.dao.existeVinculo(joinClass, id, idOrganizacao);
	}

	@Override
	public T saveIfNotExist(PersistentObject entity) {

		return (T) this.dao.saveIfNotExist(entity);
	}

	@Override
	public T mergeIfNotExist(PersistentObject entity) {

		return (T) this.dao.mergeIfNotExist(entity);
	}

	@Override
	public void saveListIfNotExist(List<T> listEntity) {

		for(T obj : listEntity){

			this.dao.saveIfNotExist((PersistentObject) obj);

		}

	}

	public void lancarBusinessException(String mensagem, Integer codigoErro, boolean condicao) {

		if (condicao) {

			throw new BusinessException(mensagem, codigoErro);
		}
	}
}