package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.GrupoUsuarioDao;
import br.com.centralit.api.service.GrupoUsuarioService;
import br.com.centralit.framework.model.Grupo;
import br.com.centralit.framework.model.GrupoUsuario;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;

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
 * @since 04/03/2015 - 15:38:29
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("grupoUsuarioService")
public class GrupoUsuarioServiceImpl extends GenericServiceImpl<GrupoUsuario, Long> implements GrupoUsuarioService {

	/** Atributo grupoUsuarioDao. */
	private GrupoUsuarioDao grupoUsuarioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param grupoUsuarioDao
	 */
	@Autowired
	public GrupoUsuarioServiceImpl( GrupoUsuarioDao grupoUsuarioDao ) {

		this.dao = grupoUsuarioDao;

		this.grupoUsuarioDao = grupoUsuarioDao;

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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	public void verificarRemocaoGrupoUsuario(Grupo entity) {

		Collection<GrupoUsuario> listaGrupoUsuarioVinculados = this.grupoUsuarioDao.findPorIdGrupo(entity.getId());
		// Verifica se a lista de listaGrupoUsuarioVinculados não é null ou vazio.
		if (!UtilColecao.isVazio(listaGrupoUsuarioVinculados)) {

			for (GrupoUsuario grupoUsuario : listaGrupoUsuarioVinculados) {
				// Verifica se a lista de grupoUsuarios contem o grupoUsuarioVinculado, caso não contem remove o grupoUsuario.
				if (!entity.getGrupoUsuarios().contains(grupoUsuario)) {

					this.remove(grupoUsuario);

				}
			}

		}
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
	 * Método responsável por remover o grupoUsuario através do id do Usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	public void verificarRemocaoGrupoUsuarioAtravesDoUsuario(Usuario entity) {

		Collection<GrupoUsuario> listaGrupoUsuarioVinculados = this.grupoUsuarioDao.findPorIdUsuario(entity.getId());
		// Verifica se a lista de listaGrupoUsuarioVinculados não é null ou vazio.
		if (!UtilColecao.isVazio(listaGrupoUsuarioVinculados)) {

			for (GrupoUsuario grupoUsuario : listaGrupoUsuarioVinculados) {
				// Verifica se a lista de grupoUsuarios contem o grupoUsuarioVinculado, caso não contem remove o grupoUsuario.
				if (!entity.getGrupoUsuarios().contains(grupoUsuario)) {

					this.remove(grupoUsuario);

				}
			}

		}
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
	 * Método responsável por listar os GrupoUsuario através do id do grupo
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idGrupo
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<GrupoUsuario> findPorIdGrupo(Long idGrupo) {

		return this.grupoUsuarioDao.findPorIdGrupo(idGrupo);
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
	 * Método responsável por listar os GrupoUsuario através do id do usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idUsuario
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<GrupoUsuario> findPorIdUsuario(Long idUsuario) {

		return this.grupoUsuarioDao.findPorIdUsuario(idUsuario);
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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Collection<Long> findIdsGrupoUsuarioPorIdUsuario(Long idUsuario) {

		return this.grupoUsuarioDao.findIdsGrupoUsuarioPorIdUsuario(idUsuario);
	}

}
