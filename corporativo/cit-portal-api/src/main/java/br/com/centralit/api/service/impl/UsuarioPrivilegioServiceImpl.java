package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.UsuarioPrivilegioDao;
import br.com.centralit.api.service.UsuarioPrivilegioService;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioPrivilegio;
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
 * @since 31/08/2015 - 00:57:04
 * 
 * @version 1.0.0
 * 
 * @author Rogério Gomes
 * 
 */
@Service("usuarioPrivilegioService")
public class UsuarioPrivilegioServiceImpl extends GenericServiceImpl<UsuarioPrivilegio, Long> implements UsuarioPrivilegioService {

	/** Atributo usuarioPrivilegioDao. */
	private UsuarioPrivilegioDao usuarioPrivilegioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param usuarioPrivilegioDao
	 */
	@Autowired
	public UsuarioPrivilegioServiceImpl( UsuarioPrivilegioDao usuarioPrivilegioDao ) {

		this.dao = usuarioPrivilegioDao;

		this.usuarioPrivilegioDao = usuarioPrivilegioDao;

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
	public void verificarRemocaoAtravesDoUsuario(Usuario entity) {

		Collection<UsuarioPrivilegio> listaPrivilegioVinculados = this.usuarioPrivilegioDao.findPorIdUsuario(entity.getId());
		// Verifica se a lista de listaGrupoUsuarioVinculados não é null ou vazio.
		if (!UtilColecao.isVazio(listaPrivilegioVinculados)) {

			for (UsuarioPrivilegio usuarioPrivilegio : listaPrivilegioVinculados) {
				// Verifica se a lista de grupoUsuarios contem o grupoUsuarioVinculado, caso não contem remove o grupoUsuario.
				if (!entity.getUsuarioPrivilegios().contains(usuarioPrivilegio)) {
					
					usuarioPrivilegio.setUsuarioInativo(entity);
					
					usuarioPrivilegio.setUsuario(null);

					this.remove(usuarioPrivilegio);

				}
			}

		}
	}

}
