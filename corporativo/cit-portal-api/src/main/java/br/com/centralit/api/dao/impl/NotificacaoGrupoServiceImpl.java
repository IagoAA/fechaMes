package br.com.centralit.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.NotificacaoGrupoDao;
import br.com.centralit.api.service.impl.NotificacaoGrupoService;
import br.com.centralit.framework.model.NotificacaoGrupo;
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
 * @since 15/06/2015 - 16:57:29
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("notificacaoGrupoService")
public class NotificacaoGrupoServiceImpl extends GenericServiceImpl<NotificacaoGrupo, Long> implements NotificacaoGrupoService {

	/** Atributo notificacaoGrupoDao. */
	@SuppressWarnings("unused")
	private NotificacaoGrupoDao notificacaoGrupoDao;

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
	 * @author rogerio.cost
	 *
	 * @param notificacaoGrupoDao
	 */
	@Autowired
	public NotificacaoGrupoServiceImpl( NotificacaoGrupoDao notificacaoGrupoDao ) {

		this.dao = notificacaoGrupoDao;

		this.notificacaoGrupoDao = notificacaoGrupoDao;

	}

}
