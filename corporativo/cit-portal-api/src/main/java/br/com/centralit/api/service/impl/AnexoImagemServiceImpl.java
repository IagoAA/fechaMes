package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.AnexoImagemDao;
import br.com.centralit.api.service.AnexoImagemService;
import br.com.centralit.framework.model.AnexoImagem;
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
 * <b>Title: </b> AnexoImagemServiceImpl
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
 * @since 17/04/2015 - 14:32:40
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Service("anexoImagemService")
public class AnexoImagemServiceImpl extends GenericServiceImpl<AnexoImagem, Long> implements AnexoImagemService  {

	@Autowired
	public AnexoImagemServiceImpl( AnexoImagemDao anexoImagemDao ) {

		this.dao = anexoImagemDao;

	}

}
