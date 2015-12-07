package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ConfiguracaoParametroSistemaDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.ConfiguracaoParametroSistema;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.util.UtilObjeto;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 	
 * @since 09/06/2015 - 14:58:43
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *	
 */
@Repository("configuracaoParametroSistemaDao")
public class ConfiguracaoParametroSistemaDaoHibernate extends CitGenericDAOImpl implements ConfiguracaoParametroSistemaDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public ConfiguracaoParametroSistemaDaoHibernate() {

		super(ConfiguracaoParametroSistema.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConfiguracaoParametroSistema getParametro(String chave, Organizacao organizacao) {

		SearchSeven search = new SearchSeven();
		
		if(UtilObjeto.isReferencia(organizacao)){
			
			search.addFilterEqual("configuracao.organizacao.id", organizacao.getId());
			
		}
		
		search.addFilterEqual("chave", chave);
		
		search.setMaxResults(1);
		
		return this.searchUnique(search);
	}

}
