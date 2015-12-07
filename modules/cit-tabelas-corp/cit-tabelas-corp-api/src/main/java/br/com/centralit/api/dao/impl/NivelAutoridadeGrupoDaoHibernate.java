package br.com.centralit.api.dao.impl;

import br.com.centralit.api.model.NivelAutoridadeGrupo;
import br.com.centralit.api.dao.NivelAutoridadeGrupoDao;

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
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 22/06/2015 - 15:51:22
 *
 * @version 1.0.0
 *
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 *	
 */
@Repository("nivelAutoridadeGrupoDao")
public class NivelAutoridadeGrupoDaoHibernate extends CitGenericDAOImpl implements NivelAutoridadeGrupoDao {
	public NivelAutoridadeGrupoDaoHibernate() {
		super(NivelAutoridadeGrupo.class);
	}
}
