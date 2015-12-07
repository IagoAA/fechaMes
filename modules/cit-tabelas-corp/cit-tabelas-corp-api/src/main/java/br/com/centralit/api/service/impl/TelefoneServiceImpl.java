package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.TelefoneDao;
import br.com.centralit.api.model.Telefone;
import br.com.centralit.api.service.TelefoneService;
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
 * <b>Title: </b>TelefoneServiceImpl
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * <p>
 * 
 * @since 05/12/2014 - 11:09:40
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("telefoneService")
public class TelefoneServiceImpl extends GenericServiceImpl<Telefone, Long> implements TelefoneService {

	/** Atributo telefoneDao. */
	private TelefoneDao telefoneDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param telefoneDao
	 */
	@Autowired
	public TelefoneServiceImpl( TelefoneDao telefoneDao ) {

		this.dao = telefoneDao;

		this.telefoneDao = telefoneDao;
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
	 * Método responsável por listar os telefones através do id da pessoa
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idPessoa
	 * 
	 * @return Collection<Parceiro>
	 */
	public Collection<Telefone> findPorIdPessoa(Long idPessoa) {

		return this.telefoneDao.findPorIdPessoa(idPessoa);
	}

	/**
	 * Retorna o valor do atributo <code>telefoneDao</code>
	 * 
	 * @return <code>TelefoneDao</code>
	 */
	public TelefoneDao getTelefoneDao() {

		return telefoneDao;
	}

	/**
	 * Define o valor do atributo <code>telefoneDao</code>.
	 * 
	 * @param telefoneDao
	 */
	public void setTelefoneDao(TelefoneDao telefoneDao) {

		this.telefoneDao = telefoneDao;
	}
}
