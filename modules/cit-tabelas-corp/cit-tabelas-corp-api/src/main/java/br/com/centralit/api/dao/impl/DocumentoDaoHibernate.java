package br.com.centralit.api.dao.impl;

import br.com.centralit.api.dao.DocumentoDao;
import br.com.centralit.api.model.Documento;

import org.springframework.stereotype.Repository;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;

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
 * @since 07/01/2015 - 12:11:01
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@Repository("documentoDao")
public class DocumentoDaoHibernate extends CitGenericDAOImpl implements DocumentoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public DocumentoDaoHibernate() {

		super(Documento.class);
	}
}
