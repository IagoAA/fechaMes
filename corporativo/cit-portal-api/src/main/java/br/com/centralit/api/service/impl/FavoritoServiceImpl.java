package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.FavoritoDao;
import br.com.centralit.api.service.FavoritoService;
import br.com.centralit.framework.model.Favorito;
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
 * <b>Title: FavoritoServiceImpl</b>
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
 * @since 18/12/2014 - 10:00:57
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Service("favoritoService")
public class FavoritoServiceImpl extends GenericServiceImpl<Favorito, Long> implements FavoritoService {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	@Autowired
	public FavoritoServiceImpl( FavoritoDao favoritoDao ) {

		this.dao = favoritoDao;
	}

}
