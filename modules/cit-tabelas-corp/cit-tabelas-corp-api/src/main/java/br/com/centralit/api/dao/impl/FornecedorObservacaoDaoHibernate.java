package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.FornecedorObservacaoDao;
import br.com.centralit.api.model.FornecedorObservacao;
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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 * 
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 * 
 * @since 07/01/2015 - 10:25:35
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("fornecedorObservacaoDao")
public class FornecedorObservacaoDaoHibernate extends CitGenericDAOImpl implements FornecedorObservacaoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 */
	public FornecedorObservacaoDaoHibernate() {

		super(FornecedorObservacao.class);
	}

}
