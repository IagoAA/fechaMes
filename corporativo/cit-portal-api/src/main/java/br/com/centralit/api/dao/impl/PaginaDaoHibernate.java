package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PaginaDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;
import com.googlecode.genericdao.search.Search;

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
 * <b>Title: </b> PaginaDaoHibernate
 * </p>
 *
 * <p>
 * <b>Description: </b> Classe de Implementação Dao de <code>PaginaDao</code>
 * </p>
 *
 * @since 19/11/2014 - 09:09:50
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Repository("paginaDao")
public class PaginaDaoHibernate extends CitGenericDAOImpl implements PaginaDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public PaginaDaoHibernate() {

		super(Pagina.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pagina getPagina(Pagina pagina) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterEqual("pagina", pagina.getPagina()).removeField(pagina.getAjuda());

		return this.searchUnique(search);
	}

	/**
	 * <p>
	 * <b>Regra(s) de negócio:</b> Consulta entidade de acordo com os parametros,
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

		Pagina pagina = (Pagina) entity;

		Search search = new Search();

		search.addFilterEqual("pagina", pagina.getPagina());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}

}