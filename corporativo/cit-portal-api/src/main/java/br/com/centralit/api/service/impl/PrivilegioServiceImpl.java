package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PrivilegioDao;
import br.com.centralit.api.service.PrivilegioService;
import br.com.centralit.framework.model.Privilegio;
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
 * @since 09/03/2015 - 08:51:15
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("privilegioService")
public class PrivilegioServiceImpl extends GenericServiceImpl<Privilegio, Long> implements PrivilegioService {

	/** Atributo privilegioDao. */
	@SuppressWarnings("unused")
	private PrivilegioDao privilegioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param privilegioDao
	 */
	@Autowired
	public PrivilegioServiceImpl( PrivilegioDao privilegioDao ) {

		this.dao = privilegioDao;

		this.privilegioDao = privilegioDao;

	}

}
