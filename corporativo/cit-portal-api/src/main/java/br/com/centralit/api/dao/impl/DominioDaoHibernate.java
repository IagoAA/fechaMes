package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.DominioDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;

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
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
 * </p>
 *
 * @since 05/12/2014 - 10:38:27
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Repository("dominioDao")
public class DominioDaoHibernate extends CitGenericDAOImpl implements DominioDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public DominioDaoHibernate() {

		super(Dominio.class);
	}

	/**
	 * Método responsável por listar a entidade <code>Dominio</code> através da chave
	 *
	 * @author rodrigo.anaice
	 *
	 * @param chave
	 *
	 * @return Collection<Dominio>
	 */
	@Override
	public Collection<Dominio> findByChave(String chave) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", chave);
		
		search.addSort(Sort.asc("descricao"));

		return this.search(search, Dominio.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Dominio> findByChaveAndValor(String chave, String valor) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", chave);

		search.addFilterILike("descricao", "%" + valor + "%");

		search.addSort(Sort.asc("descricao"));

		return this.search(search, Dominio.class);
	}

	/**
	 * Método responsável por obter o dominio através da chave e codigo
	 *
	 * @author rogerio.costa
	 *
	 * @param chave
	 *
	 * @param codigo
	 *
	 * @return Dominio
	 */
	@Override
	public Dominio findByChaveAndCodigo(String chave, Long codigo) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", chave);

		search.addFilterEqual("codigo", codigo);

		return this.searchUnique(search, Dominio.class);
	}

	@Override
	public Collection<String> listarChavesExistentes() {

		SearchSeven search = new SearchSeven();

		search.setDistinct(Boolean.TRUE);

		search.addField("chave");

		search.addSort(Sort.asc("chave"));

		return this.search(search, Dominio.class);
	}

	/**
	 * Método responsável por listar a entidade <code>Dominio</code> através do codigo
	 *
	 * @author thiago.borges
	 *
	 * @param codigo
	 *
	 * @return Collection<Dominio>
	 */
	@Override
	public Collection<Dominio> findByCodigo(Long codigo) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("codigo", codigo);

		return this.search(search, Dominio.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dominio findByChaveAndNome(String chave, String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", chave);

		search.addFilterEqual("nome", nome);

		search.setMaxResults(1);

		return this.searchUnique(search, Dominio.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dominio buscaDominioByCodigo(Long codigo) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("codigo", codigo);

		search.setMaxResults(1);

		return this.searchUnique(search, Dominio.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validaCodigoDominioPorChave(String chave, Long codigo) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("chave", chave);

		search.addFilterEqual("codigo", codigo);

		if( this.searchUnique(search, Dominio.class) != null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * <p>
	 * <b>Regra(s) de negócio:</b> Consulta entidade de acordo com os parametros(chave e valor),
	 * caso ela não exista, salva o registro
	 * </p>
	 *
	 * @author gilberto.nery
	 * @date 09/09/2015
	 *
	 * @return PersistentObject - Entidade que foi salva ou entidade que estava cadastrada
	 */
	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		Dominio dominio = (Dominio) entity;

		Search search = new Search();

		search.addFilterEqual("chave", dominio.getChave());
		search.addFilterEqual("codigo", dominio.getCodigo());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}
}