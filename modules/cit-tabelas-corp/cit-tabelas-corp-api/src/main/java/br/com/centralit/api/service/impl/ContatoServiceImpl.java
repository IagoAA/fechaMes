package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.ContatoDao;
import br.com.centralit.api.model.Contato;
import br.com.centralit.api.service.ContatoService;
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
 * @since 07/01/2015 - 16:17:37
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("contatoService")
public class ContatoServiceImpl extends GenericServiceImpl<Contato, Long> implements ContatoService {

	/** Atributo contatoDao. */
	private ContatoDao contatoDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param contatoDao
	 */
	@Autowired
	public ContatoServiceImpl( ContatoDao contatoDao ) {

		this.dao = contatoDao;

		this.contatoDao = contatoDao;

	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por listar os contatos através do id da pessoa
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPessoa
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<Contato> findPorIdPessoa(Long idPessoa) {

		return this.contatoDao.findPorIdPessoa(idPessoa);
	}

	/**
	 * Retorna o valor do atributo <code>contatoDao</code>
	 * 
	 * @return <code>ContatoDao</code>
	 */
	public ContatoDao getContatoDao() {

		return contatoDao;
	}

}
