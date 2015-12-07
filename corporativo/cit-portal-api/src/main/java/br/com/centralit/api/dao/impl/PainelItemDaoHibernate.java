package br.com.centralit.api.dao.impl;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Filter;

import br.com.centralit.api.dao.PainelItemDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.GrupoUsuario;
import br.com.centralit.framework.model.PainelItem;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioPrivilegio;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 * 
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 * 
 * @since 23/03/2015 - 10:36:43
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("painelItemDao")
public class PainelItemDaoHibernate extends CitGenericDAOImpl implements PainelItemDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param classs
	 */
	public PainelItemDaoHibernate() {

		super(PainelItem.class);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar o painelItem através do id do painel
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * 
	 * @return
	 */
	public Collection<PainelItem> findPorIdPainel(Long idPainel) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("painel.id", idPainel);

		return this.search(search, PainelItem.class);

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por obter painelItem através do id do painel e regras de permissão do usuario logado
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPainel
	 * @param usuario
	 * @return Collection<PainelItem>
	 */
	public Collection<PainelItem> findPorPainelEPermissaoUsuario(Long idPainel, Usuario usuario) {

		SearchSeven search = new SearchSeven();

		Collection<Long> idsGrupoUsuario = new LinkedList<Long>();

		Collection<Long> idsPrivilegioUsuario = new LinkedList<Long>();

		// Verifica se o usuario contem privilegio vinculado, caso seja verdadeiro monta uma lista com os ids do mesmo.
		if (!UtilColecao.isVazio(usuario.getUsuarioPrivilegios())) {

			for (UsuarioPrivilegio usuarioPrivilegio : usuario.getUsuarioPrivilegios()) {
				
				if(UtilObjeto.isReferencia(usuarioPrivilegio.getPrivilegio())){
					
					idsPrivilegioUsuario.add(usuarioPrivilegio.getPrivilegio().getId());
				}


			}

		}
		// Verifica se o usuario contem grupo vinculado, caso seja verdadeiro monta uma lista com os ids do mesmo.
		if (!UtilColecao.isVazio(usuario.getGrupoUsuarios())) {

			for (GrupoUsuario grupoUsuario : usuario.getGrupoUsuarios()) {
				
				if(UtilObjeto.isReferencia(grupoUsuario.getGrupo())){
					
					idsGrupoUsuario.add(grupoUsuario.getGrupo().getId());
				}


			}

		}
		
		search.addFilterEqual("painel.id", idPainel);

		search.addFilterOr(Filter.in("painelItemGrupos.grupo.id", idsGrupoUsuario), Filter.in("painelItemPrivilegios.privilegio.id", idsPrivilegioUsuario), Filter.equal("regraDefinida", false));
		
		search.setDistinct(true);
		
		return this.search(search, PainelItem.class);

	}

}
