package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.GrupoPrivilegioDao;
import br.com.centralit.api.service.GrupoPrivilegioService;
import br.com.centralit.framework.model.Grupo;
import br.com.centralit.framework.model.GrupoPrivilegio;
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
 * @since 04/03/2015 - 15:40:34
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("grupoRoleService")
public class GrupoPrivilegioServiceImpl extends GenericServiceImpl<GrupoPrivilegio, Long> implements GrupoPrivilegioService {

	/** Atributo grupoRoleDao. */
	private GrupoPrivilegioDao grupoPrivilegioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param grupoRoleDao
	 */
	@Autowired
	public GrupoPrivilegioServiceImpl( GrupoPrivilegioDao grupoPrivilegioDao ) {

		this.dao = grupoPrivilegioDao;

		this.grupoPrivilegioDao = grupoPrivilegioDao;

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
	public void verificarRemocaoGrupoPrivilegio(Grupo entity) {

		Collection<GrupoPrivilegio> listaGrupoPrivilegioVinculados = this.grupoPrivilegioDao.findPorIdGrupo(entity.getId());
		// Verifica se a lista de listaGrupoUsuarioVinculados não é null ou vazio.
		if (!UtilColecao.isVazio(listaGrupoPrivilegioVinculados)) {

			for (GrupoPrivilegio grupoPrivilegio : listaGrupoPrivilegioVinculados) {
				// Verifica se a lista de grupoUsuarios contem o grupoUsuarioVinculado, caso não contem remove o grupoUsuario.
				if (!entity.getGrupoUsuarios().contains(grupoPrivilegio)) {

					this.grupoPrivilegioDao.remove(grupoPrivilegio);

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
	 * Método responsável por listar os GrupoPrivilegio através do id do grupo
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idGrupo
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<GrupoPrivilegio> findPorIdGrupo(Long idGrupo) {

		return this.grupoPrivilegioDao.findPorIdGrupo(idGrupo);
	}

}
