package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.LinkDao;
import br.com.centralit.api.service.LinkService;
import br.com.centralit.framework.model.Link;
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
 * @since 06/04/2015 - 17:36:36
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("linkService")
public class LinkServiceImpl extends GenericServiceImpl<Link, Long> implements LinkService {

	/** Atributo linkDao. */
	@SuppressWarnings("unused")
	private LinkDao linkDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param linkDao
	 */
	@Autowired
	public LinkServiceImpl( LinkDao linkDao ) {

		this.dao = linkDao;

		this.linkDao = linkDao;
	}

}
