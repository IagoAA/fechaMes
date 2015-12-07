package br.com.centralit.api.dao.impl;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.PainelDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.GrupoUsuario;
import br.com.centralit.framework.model.Painel;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioPrivilegio;
import br.com.centralit.framework.util.UtilColecao;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Filter;

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
 * @since 10/03/2015 - 15:35:18
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("painelDao")
public class PainelDaoHibernate extends CitGenericDAOImpl implements PainelDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public PainelDaoHibernate() {

		super(Painel.class);
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
	 * Método responsável por listar os paineis do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param usuario
	 * 
	 * @return Collection<Painel>
	 */
	public Collection<Painel> findPorUsuario(Usuario usuario) {

		SearchSeven searchPainelSistema = new SearchSeven(this.persistentClass);

		SearchSeven searchPainelUsuario = new SearchSeven(this.persistentClass);

		searchPainelUsuario.addFilterEqual("usuario.id", usuario.getId());

		searchPainelSistema.addFilterNull("usuario");

		Collection<Long> idsGrupo = new LinkedList<Long>();

		Collection<Long> idsPrivilegioUsuario = new LinkedList<Long>();

		// Verifica se o usuario contem privilegio vinculado, caso seja verdadeiro monta uma lista com os ids do mesmo.
		if (usuario.getUsuarioPrivilegios() != null && usuario.getUsuarioPrivilegios().size() > 0) {

			for (UsuarioPrivilegio usuarioPrivilegio : usuario.getUsuarioPrivilegios()) {
				
				if(UtilObjeto.isReferencia(usuarioPrivilegio.getPrivilegio())){
					
					idsPrivilegioUsuario.add(usuarioPrivilegio.getPrivilegio().getId());
				}


			}

		}
		//Verifica se o usuario contem grupo vinculado, caso seja verdadeiro monta uma lista com os ids do mesmo.
		if (!UtilColecao.isVazio(usuario.getGrupoUsuarios())) {

			for (GrupoUsuario grupoUsuario : usuario.getGrupoUsuarios()) {
				
				if(UtilObjeto.isReferencia(grupoUsuario.getGrupo())){
					
					idsGrupo.add(grupoUsuario.getGrupo().getId());
				}


			}

		}
		
		searchPainelSistema.addFilterOr(Filter.in("painelGrupos.grupo.id", idsGrupo), Filter.in("painelPrivilegios.privilegio.id", idsPrivilegioUsuario), Filter.equal("regraDefinida", false));
		
		searchPainelUsuario.addFilterOr(Filter.in("painelGrupos.grupo.id", idsGrupo), Filter.in("painelPrivilegios.privilegio.id", idsPrivilegioUsuario), Filter.equal("regraDefinida", false));
		
		searchPainelUsuario.setDistinct(true);

		searchPainelSistema.setDistinct(true);

		Collection<Painel> paineisUsuario = this.search(searchPainelUsuario, this.persistentClass);

		Collection<Painel> paineisSistema = this.search(searchPainelSistema, this.persistentClass);

		for (Painel painel : paineisSistema) {

			if (!paineisUsuario.contains(painel)) {

				paineisUsuario.add(painel);

			}

		}

		return paineisUsuario;
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
	 * Método responsável por listar os paineis através do nome
	 * 
	 * @author rogerio.costa
	 * 
	 * @param nome
	 * 
	 * @return Boolean
	 */
	public boolean contemPainelPorNome(String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("nome", nome);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;

	}
}
