package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PaginaUsuarioDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.PaginaUsuario;
import br.com.centralit.framework.model.Usuario;

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
 * <b>Title: </b> PaginaUsuarioDaoHibernate
 * </p>
 *
 * <p>
 * <b>Description: </b> Classe de Implementação Dao de <code>PaginaUsuarioDao</code>
 * </p>
 *
 * @since 19/11/2014 - 09:09:50
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Repository("paginaUsuarioDao")
public class PaginaUsuarioDaoHibernate extends CitGenericDAOImpl implements PaginaUsuarioDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public PaginaUsuarioDaoHibernate() {

		super(PaginaUsuario.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaginaUsuario getPaginaUsuario(Pagina pageWorkspace, Usuario usuario) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterEqual("pagina.pagina", pageWorkspace.getPagina());

		search.addFilterEqual("usuario.id", usuario.getId());

		return this.searchUnique(search, this.persistentClass);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por trazer paginas de usuário ativas
	 *
	 * @author iago.almeida
	 *
	 * @param idUsuario
	 * @return
	 */
	@Override
	public List<PaginaUsuario> findPorIdUsuario(Long idUsuario) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("usuario.id", idUsuario);
		
		search.addFilterEmpty("dataInativo");

		return this.search(search, PaginaUsuario.class);
	}

}
