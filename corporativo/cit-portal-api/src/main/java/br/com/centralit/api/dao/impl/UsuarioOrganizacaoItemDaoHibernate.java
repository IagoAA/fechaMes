package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.UsuarioOrganizacaoItemDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.UsuarioOrganizacaoItem;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Search;


@Repository("usuarioOrganizacaoItemDao")
public class UsuarioOrganizacaoItemDaoHibernate extends CitGenericDAOImpl implements UsuarioOrganizacaoItemDao {

	public UsuarioOrganizacaoItemDaoHibernate() {
		super(UsuarioOrganizacaoItem.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UsuarioOrganizacaoItem> buscaOrganizacoesAtivasPorIdUsuario(Long idUsuario) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("usuario.id", idUsuario);

		search.addFilterEmpty("dataInativo");

		return this.search(search, UsuarioOrganizacaoItem.class);
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

		UsuarioOrganizacaoItem usuarioOrganizacaoItem = (UsuarioOrganizacaoItem) entity;

		Search search = new Search();

		search.addFilterEqual("usuario.id", usuarioOrganizacaoItem.getUsuario().getId());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}
}
