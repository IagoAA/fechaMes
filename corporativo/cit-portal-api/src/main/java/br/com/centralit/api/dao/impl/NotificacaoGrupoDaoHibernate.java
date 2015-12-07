package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.NotificacaoGrupoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.model.NotificacaoGrupo;

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
 * @since 15/06/2015 - 16:51:30
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("notificacaoGrupoDao")
public class NotificacaoGrupoDaoHibernate extends CitGenericDAOImpl implements NotificacaoGrupoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public NotificacaoGrupoDaoHibernate() {

		super(NotificacaoGrupo.class);
	}

}
