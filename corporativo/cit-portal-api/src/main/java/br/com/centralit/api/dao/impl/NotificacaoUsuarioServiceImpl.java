package br.com.centralit.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.NotificacaoUsuarioDao;
import br.com.centralit.api.service.NotificacaoUsuarioService;
import br.com.centralit.framework.model.NotificacaoUsuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

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
 * @since 15/06/2015 - 16:59:17
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("notificacaoUsuarioService")
public class NotificacaoUsuarioServiceImpl extends GenericServiceImpl<NotificacaoUsuario, Long> implements NotificacaoUsuarioService {

	/** Atributo notificacaoUsuarioDao. */
	@SuppressWarnings("unused")
	private NotificacaoUsuarioDao notificacaoUsuarioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param notificacaoUsuarioDao
	 */
	@Autowired
	public NotificacaoUsuarioServiceImpl( NotificacaoUsuarioDao notificacaoUsuarioDao ) {

		this.dao = notificacaoUsuarioDao;

		this.notificacaoUsuarioDao = notificacaoUsuarioDao;
	}

}
