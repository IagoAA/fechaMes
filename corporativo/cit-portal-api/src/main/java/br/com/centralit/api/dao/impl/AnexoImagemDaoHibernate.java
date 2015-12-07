package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.AnexoImagemDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.model.AnexoImagem;

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
 * <b>Title: </b> AnexoImagemDaoHibernate
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
 * @since 24/02/2015 - 14:31:22
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Repository("anexoImagemDao")
public class AnexoImagemDaoHibernate extends CitGenericDAOImpl implements AnexoImagemDao {

	public AnexoImagemDaoHibernate() {

		super(AnexoImagem.class);

	}

}
