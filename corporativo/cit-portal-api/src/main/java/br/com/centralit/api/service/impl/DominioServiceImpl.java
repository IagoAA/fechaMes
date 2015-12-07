package br.com.centralit.api.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.DominioDao;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.framework.model.Dominio;
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
 * @since 05/12/2014 - 10:41:26
 *
 * @version 1.0.0
 *
 * @author thiago.borges
 *
 */
@Service("dominioService")
public class DominioServiceImpl extends GenericServiceImpl<Dominio, Long> implements DominioService {

	/** Atributo dominioDao. */
	private DominioDao dominioDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param dominioDao
	 * @param validator
	 */
	@Autowired
	public DominioServiceImpl( DominioDao dominioDao, @Qualifier("dominioValidator") Validator validator ) {

		this.dao = dominioDao;

		this.dominioDao = dominioDao;

		this.validator = validator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Dominio> listarPorChave(String chave) {

		return this.dominioDao.findByChave(chave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Dominio> listarPorChaveValor(String chave, String valor) {

		return this.dominioDao.findByChaveAndValor(chave, valor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dominio findByChaveAndCodigo(String chave, Long codigo) {

		return this.dominioDao.findByChaveAndCodigo(chave, codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> listarChavesExistentes() {

		return this.dominioDao.listarChavesExistentes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dominio findByChaveAndNome(String chave, String nome) {

		return this.dominioDao.findByChaveAndNome(chave, nome);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dominio buscaDominioByCodigo(Long codigo) {

		return this.dominioDao.buscaDominioByCodigo(codigo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validaCodigoDominioPorChave(String chave, Long codigo) {

		return this.dominioDao.validaCodigoDominioPorChave(chave, codigo);
	}

}
