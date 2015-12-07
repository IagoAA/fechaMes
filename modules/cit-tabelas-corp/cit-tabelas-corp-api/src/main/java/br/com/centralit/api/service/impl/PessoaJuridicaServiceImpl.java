package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.centralit.api.dao.PessoaJuridicaDao;
import br.com.centralit.api.model.PessoaJuridica;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.PessoaJuridicaService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

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
 * <b>Title: </b>PessoaJuridicaServiceImpl
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
 * @since 02/01/2015 - 09:14:49
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("pesssoaJuridicaService")
public class PessoaJuridicaServiceImpl extends GenericServiceImpl<PessoaJuridica, Long> implements PessoaJuridicaService {

	/** Atributo pessoaJuridicaDao. */
	private PessoaJuridicaDao pessoaJuridicaDao;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo VALIDACAO_CNPJ_EXISTENTE. */
	private static final String VALIDACAO_CNPJ_EXISTENTE = "VALIDACAO.CNPJ_EXISTENTE";

	/** Atributo VALIDACAO_CNPJ. */
	private static final String VALIDACAO_CNPJ = "VALIDACAO.VALIDACAO_CNPJ";

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param pessoaJuridicaDao
	 */
	@Autowired
	public PessoaJuridicaServiceImpl( PessoaJuridicaDao pessoaJuridicaDao, @Qualifier("pessoaJuridicaValidator") Validator validator ) {

		this.dao = pessoaJuridicaDao;

		this.pessoaJuridicaDao = pessoaJuridicaDao;

		this.validator = validator;

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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	public void validarDados(PessoaJuridica entity) {

		this.montarDadosPessoaJuridica(entity);

		this.validarEntidade(entity, this.validator);

		this.validarCNPJ(entity);

		this.validarExistenciaCNPJ(entity);

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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	private void montarDadosPessoaJuridica(PessoaJuridica entity) {
		//Verifica se o dominioAbrangencia foi selecionado
		if (UtilObjeto.isReferencia(entity.getDominioAbrangencia())) {

			entity.setDominioAbrangencia(this.getDominioService().getReference(entity.getDominioAbrangencia().getId()));
		}
		//Verifica se o dominioPorte foi selecionado
		if (UtilObjeto.isReferencia(entity.getDominioPorte())) {

			entity.setDominioPorte(this.getDominioService().getReference(entity.getDominioPorte().getId()));
		}
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
	 * Método responsável por
	 * 
	 * @author rogerio.costa
	 * 
	 * @param entity
	 */
	private void validarCNPJ(PessoaJuridica entity) {

		CNPJValidator cnpjValidator = new CNPJValidator();

		try {
			cnpjValidator.assertValid(entity.getCnpj());

		} catch (InvalidStateException e) {

			throw new BusinessException(VALIDACAO_CNPJ, CodigoErro.REGRA_NEGOCIO.getValue());
		}
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
	 * Método responsável por verificar se o CNPJ informado esta sendo usado por outra pessoa no mesmo orgão
	 * 
	 * @author rogerio.costa
	 * 
	 * @param pessoaJuridica
	 */
	public void validarExistenciaCNPJ(PessoaJuridica pessoaJuridica) {

		PessoaJuridica pessoa = this.getPessoaJuridicaDao().findPorCNPJ(pessoaJuridica.getCnpj(), ( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getOrganizacao().getId());
		//Verifica se o cnpj existe em outro cadastro, caso existe verifica se pertence a outra pessoa.
		if (UtilObjeto.isReferencia(pessoa) && ( !UtilObjeto.isReferencia(pessoaJuridica.getId()) || UtilObjeto.isReferencia(pessoaJuridica.getId()) && !pessoaJuridica.getId().equals(pessoa.getId()) )) {

			throw new BusinessException(VALIDACAO_CNPJ_EXISTENTE, CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

	/**
	 * Retorna o valor do atributo <code>pessoaJuridicaDao</code>
	 * 
	 * @return <code>PessoaJuridicaDao</code>
	 */
	public PessoaJuridicaDao getPessoaJuridicaDao() {

		return pessoaJuridicaDao;
	}

	/**
	 * Retorna o valor do atributo <code>dominioService</code>
	 * 
	 * @return <code>DominioService</code>
	 */
	public DominioService getDominioService() {

		return dominioService;
	}

}
