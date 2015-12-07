package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.FornecedorObservacaoDao;
import br.com.centralit.api.model.FornecedorObservacao;
import br.com.centralit.api.service.FornecedorObservacaoService;
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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 * 
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 * 
 * @since 07/01/2015 - 10:27:55
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("fornecedorObservacaoService")
public class FornecedorObservacaoServiceImpl extends GenericServiceImpl<FornecedorObservacao, Long> implements FornecedorObservacaoService {

	/** Atributo fornecedorObservacaoDao. */
	private FornecedorObservacaoDao fornecedorObservacaoDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param fornecedorObservacaoDao
	 */
	@Autowired
	public FornecedorObservacaoServiceImpl( FornecedorObservacaoDao fornecedorObservacaoDao ) {

		this.dao = fornecedorObservacaoDao;

		this.fornecedorObservacaoDao = fornecedorObservacaoDao;

	}

	/**
	 * Retorna o valor do atributo <code>fornecedorObservacaoDao</code>
	 * 
	 * @return <code>FornecedorObservacaoDao</code>
	 */
	public FornecedorObservacaoDao getFornecedorObservacaoDao() {

		return fornecedorObservacaoDao;
	}

}
