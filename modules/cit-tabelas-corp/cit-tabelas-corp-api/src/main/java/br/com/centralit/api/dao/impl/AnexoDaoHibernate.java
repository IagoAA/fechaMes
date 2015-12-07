package br.com.centralit.api.dao.impl;

import br.com.centralit.api.dao.AnexoDao;
import br.com.centralit.api.model.Anexo;

import org.springframework.stereotype.Repository;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * @since 07/01/2015 - 11:31:33
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *	
 */
@Repository("anexoDao")
public class AnexoDaoHibernate extends CitGenericDAOImpl implements AnexoDao {
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public AnexoDaoHibernate() {
		super(Anexo.class);
	}
}
