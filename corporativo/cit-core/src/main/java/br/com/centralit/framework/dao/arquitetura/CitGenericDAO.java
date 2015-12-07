package br.com.centralit.framework.dao.arquitetura;

import java.util.List;

import br.com.centralit.framework.model.arquitetura.PersistentObject;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;

public interface CitGenericDAO extends GenericDAO<PersistentObject, Long> {

	public PersistentObject getReference(Long id, @SuppressWarnings("rawtypes") Class classs);

	public void remove(Long id, @SuppressWarnings("rawtypes") Class classs);

	public List<PersistentObject> findAll(@SuppressWarnings("rawtypes") Class classs);

	public <RT> List<RT> search(ISearch search, Class classs);

	public <RT> SearchResult<RT> searchAndCount(ISearch search, Class classs);

	public <RT> RT searchUnique(ISearch search, Class classs);

	/**
	 * M&eacute;todo respons&aacute;vel por buscar uma lista de entidades por id.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param ids
	 * @return Lista de entidades encontradas na base de dados.
	 */
	public List<PersistentObject> findList(List<Long> ids);

	/**
	 * M&eacute;todo respons&aacute;vel por obtr refer&ecirc;ncias para as entidades de um tipo espec&iacute;fico a partir de uma dada lista de ids de um banco de dados.
	 * <p>
	 * N&atilde;o requer uma chamada ao banco de dados e n&atilde;o popula nenhum valor da entidade. Os valores podem ser anexados sob demanda em momento posterior. Isto aumenta a performance se uma outra entidade est&aacute; sendo salva e deve referenciar estas entidades mas os valores das mesmas
	 * n&atilde;o s&atilde;o necess&aacute;rias.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param ids
	 * @return Lista de entidades referenciadas
	 */
	public List<PersistentObject> getReferencesList(List<Long> ids);

	/**
	 *
	 * M&eacute;todo respons&aacute;vel por tornar uma persistente uma lista de inst&acirc;ncias transientes e as adiciona ao banco de dados. Esta opera&ccedil;&atilde;o &eacute; realizada em cascata para inst&acirc;ncias associadas se as associa&ccedil;&otilde;es estiverem mapeadas com
	 * <code>cascade="persist"</code>. Lan&ccedil;a exce&ccedil;&atilde;o caso a entidade j&aacute; estiver persistida.
	 * <p>
	 * <b>N&atilde;o garante que os objetos ser&atilde;o marcados com um id imediatamente. Com <code>persist</code> um id de um banco de dados gen&eacute;rico pode n&atilde;o ser recuperado at&eacute; que o flush seja realizado</b>
	 * </p>
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param listEntity
	 */
	public void persistList(List<PersistentObject> listEntity);

	/**
	 * M&eacute;todo respons&aacute;vel por realizar merge de uma lista de entidades na base de dados.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param listEntity
	 * @return Lista de entidades mescladas e sincronizadas na base de dados.
	 */
	public List<PersistentObject> mergeList(List<PersistentObject> listEntity);

	/**
	 * M&eacute;todo respons&aacute;vel por salvar uma lista de entidades na base de dados.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param listEntity
	 * @return Lista de entidades salvas e sincronizadas na base de dados.
	 */
	public List<PersistentObject> saveList(List<PersistentObject> listEntity);

	/**
	 * M&eacute;todo respons&aacute;vel por remover da base de dados uma lista de entidades.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param listEntity
	 */
	public void removeList(List<PersistentObject> listEntity);

	/**
	 * M&eacute;todo respons&aacute;vel por remover da base de dados v&aacute;rias entidades recebendo como par&acirc;metro uma lista de ids&lt;Long&gt;.
	 *
	 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
	 *
	 * @param ids
	 */
	public void removeByIdsList(List<Long> ids);

	public void refreshList(List<PersistentObject> listEntity);

	public List<PersistentObject> findAutoComplete(String chave, String valor);

	public Boolean existeVinculo(String joinClass, Long id);

	public Boolean existeVinculo(String joinClass, List<Long> ids);
	
	public Boolean existeVinculo(String joinClass, Long id, Long idOrganizacao);
	
	public PersistentObject saveIfNotExist(PersistentObject entity);

	public PersistentObject mergeIfNotExist(PersistentObject entity);

	public PersistentObject getReferenceByExample(PersistentObject entity);

	/**
	 *
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 *
	 * Método responsável por consultar o último registro inserido na tabela.
	 *
	 * @author luis.camargo
	 *
	 * @return
	 */
	public PersistentObject buscarUltimoRegistroComOrdenadoParametrizada(String sortBy, Long idOrganizacao);

	/**
	 * Retorna todos os registros com parametro que diz se é pra trazer registros bloqueados (com dataBloqueio) ou não.
	 * 
	 * @param trazerBloqueados <code>true</code> se deseja que a busca traga os registros bloqueados e <code>false</code> caso não deseje
	 */
	List<PersistentObject> findAll(boolean trazerBloqueados);

	/**
	 * Retorna todos os registros com parametro que diz se é pra trazer registros bloqueados (com dataBloqueio) ou não.
	 * 
	 * @param classs
	 * @param trazerBloqueados <code>true</code> se deseja que a busca traga os registros bloqueados e <code>false</code> caso não deseje
	 */
	List<PersistentObject> findAll(@SuppressWarnings("rawtypes") Class classs, boolean trazerBloqueados);
	
}
