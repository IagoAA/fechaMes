package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PaginaDao;
import br.com.centralit.api.service.PaginaService;
import br.com.centralit.framework.model.Pagina;
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
 * <b>Title: PaginaServiceImpl</b>
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
 * @since 18/12/2014 - 14:29:07
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Service("paginaService")
public class PaginaServiceImpl extends GenericServiceImpl<Pagina, Long> implements PaginaService {

	@Autowired
	private PaginaDao paginaDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param paginaDao
	 */
	@Autowired
	public PaginaServiceImpl( PaginaDao paginaDao ) {

		this.dao = paginaDao;

		this.paginaDao = paginaDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pagina getPagina(Pagina pagina) {

		return this.paginaDao.getPagina(pagina);
	}

}
