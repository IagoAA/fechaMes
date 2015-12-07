package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.DocumentoDao;
import br.com.centralit.api.model.Documento;
import br.com.centralit.api.service.DocumentoService;
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
 * @since 07/01/2015 - 12:10:41
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@Service("documentoService")
public class DocumentoServiceImpl extends GenericServiceImpl<Documento, Long> implements DocumentoService {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param documentoDao
	 */
	@Autowired
	public DocumentoServiceImpl( DocumentoDao documentoDao ) {

		this.dao = documentoDao;
	}

	@Override
	public boolean removeById(Long id) {

		Documento entity = this.find(id);

		return this.remove(entity);
	}

	@Override
	public boolean remove(Documento entity) {
		return super.remove(entity);
	}
}
