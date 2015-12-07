package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.GrupoDao;
import br.com.centralit.api.service.GrupoPrivilegioService;
import br.com.centralit.api.service.GrupoService;
import br.com.centralit.api.service.GrupoUsuarioService;
import br.com.centralit.api.service.PrivilegioService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.model.Grupo;
import br.com.centralit.framework.model.GrupoPrivilegio;
import br.com.centralit.framework.model.GrupoUsuario;
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
 * @since 04/03/2015 - 15:35:36
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("grupoService")
public class GrupoServiceImpl extends GenericServiceImpl<Grupo, Long> implements GrupoService {

	/** Atributo grupoDao. */
	@SuppressWarnings("unused")
	private GrupoDao grupoDao;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/** Atributo privilegioService. */
	@Autowired
	private PrivilegioService privilegioService;

	/** Atributo grupoUsuarioService. */
	@Autowired
	private GrupoUsuarioService grupoUsuarioService;

	@Autowired
	private GrupoPrivilegioService grupoPrivilegioService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param grupoDao
	 */
	@Autowired
	public GrupoServiceImpl( GrupoDao grupoDao, @Qualifier("grupoValidator") Validator validator ) {

		this.dao = grupoDao;

		this.grupoDao = grupoDao;

		this.validator = validator;

	}

	@Override
	public Grupo save(Grupo entity) {

		this.validarEntidade(entity, this.validator);

		this.resolvePropriedadesTransient(entity);

		super.save(entity);

		return this.getReference(entity.getId());
	}

	@Override
	public Grupo merge(Grupo entity) {

		this.validarEntidade(entity, this.validator);

		this.resolvePropriedadesTransient(entity);

		this.grupoUsuarioService.verificarRemocaoGrupoUsuario(entity);

		this.grupoPrivilegioService.verificarRemocaoGrupoPrivilegio(entity);

		super.merge(entity);

		return this.getReference(entity.getId());
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
	private void resolvePropriedadesTransient(Grupo entity) {

		// Verifica se a lista de usuario não é null ou vazio
		if (!UtilColecao.isVazio(entity.getGrupoUsuarios())) {

			for (GrupoUsuario grupoUsuario : entity.getGrupoUsuarios()) {

				grupoUsuario.setGrupo(entity);

				grupoUsuario.setUsuario(this.usuarioService.find(grupoUsuario.getUsuario().getId()));

			}

		}

		if (!UtilColecao.isVazio(entity.getGrupoPrivilegios())) {

			for (GrupoPrivilegio grupoPrivilegio : entity.getGrupoPrivilegios()) {

				grupoPrivilegio.setGrupo(entity);

				grupoPrivilegio.setPrivilegio(this.privilegioService.getReference(grupoPrivilegio.getPrivilegio().getId()));

			}

		}
	}

	@Override
	public boolean removeById(Long id) {

		Grupo grupo = this.getReference(id);

		for (GrupoUsuario grupoUsuario : grupo.getGrupoUsuarios()) {

			grupoUsuario.setGrupoRemocao(grupo);

			grupoUsuario.setGrupo(null);

			grupoUsuario.setUsuarioRemocao(grupoUsuario.getUsuario());

			grupoUsuario.setUsuario(null);

		}

		return super.removeById(id);
	}

	@Override
	public Grupo getReference(Long id) {

		Grupo grupo = super.getReference(id);

		grupo.setGrupoUsuarios(this.grupoUsuarioService.findPorIdGrupo(id));

		grupo.setGrupoPrivilegios(this.grupoPrivilegioService.findPorIdGrupo(id));

		return grupo;
	}
}
