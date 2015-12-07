package br.com.centralit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.centralit.api.dao.PessoaFisicaDao;
import br.com.centralit.api.model.PessoaFisica;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.PessoaFisicaService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilDate;
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
 * <b>Title: </b>
 * </p>
 * PessoaFisicaServiceImpl
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
 * @since 02/01/2015 - 09:11:01
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Service("pesssoaFisicaService")
public class PessoaFisicaServiceImpl extends GenericServiceImpl<PessoaFisica, Long> implements PessoaFisicaService {

	/** Atributo _14. */
	private static final int _14 = 14;

	/** Atributo VALIDACAO_DATA_NASCIMENTO. */
	private static final String VALIDACAO_DATA_NASCIMENTO = "A idade da pessoa não pode ser inferior a 14 anos.";

	/** Atributo pessoaFisicaDao. */
	@Autowired
	private PessoaFisicaDao pessoaFisicaDao;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo VALIDACAO_CPF_EXISTENTE. */
	private static final String VALIDACAO_CPF_EXISTENTE = "VALIDACAO.CPF_EXISTENTE";

	/** Atributo VALIDACAO_CPF. */
	private static final String VALIDACAO_CPF = "VALIDACAO.VALIDACAO_CPF";

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param pessoaFisicaDao
	 */
	@Autowired
	public PessoaFisicaServiceImpl( PessoaFisicaDao pessoaFisicaDao, @Qualifier("pessoaFisicaValidator") Validator validator ) {

		super.dao = pessoaFisicaDao;

		this.pessoaFisicaDao = pessoaFisicaDao;

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
	 * Método responsável por validar os dados da pessoa Fisica
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	public void validarDados(PessoaFisica entity) {

		this.montarDadosPessoaFisica(entity);

		this.validarEntidade(entity, this.validator);

		this.validarDataNascimento(entity);

		this.validarCPF(entity);

		this.validarExistenciaCPF(entity);

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
	 * Método responsável por validar a data de nascimento da pessoaFisica, o mesmo não pode ser inferior a 14 anos
	 *
	 * @author rogerio.costa
	 *
	 * @param entity
	 */
	private void validarDataNascimento(PessoaFisica entity) {

		// Armazena a idade da pessoa com base na dataNascimento
		int idade = UtilDate.getIdade(UtilDate.calendarToTimestamp(entity.getDataNascimento()));
		// Verifica se a idade é inferior a 14, caso seja lança uma BusinessException informando que a idade minina para realizar o cadastro de pessoaFisica é 14 anos.
		if (idade < _14) {

			throw new BusinessException(VALIDACAO_DATA_NASCIMENTO, CodigoErro.REGRA_NEGOCIO.getValue());

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
	private void validarCPF(PessoaFisica entity) {

		CPFValidator cpfValidator = new CPFValidator();

		try {
			cpfValidator.assertValid(entity.getCpf());

		} catch (InvalidStateException e) {

			throw new BusinessException(VALIDACAO_CPF, CodigoErro.REGRA_NEGOCIO.getValue());
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
	private void montarDadosPessoaFisica(PessoaFisica entity) {

		// Verifica se o dominioSexo foi selecionado
		if (UtilObjeto.isReferencia(entity.getDominioSexo()) && UtilObjeto.isReferencia(entity.getDominioSexo().getId())) {

			entity.setDominioSexo(this.getDominioService().getReference(entity.getDominioSexo().getId()));
		}
		// Verifica se o dominioEstadoCivil foi selecionado
		if (UtilObjeto.isReferencia(entity.getDominioEstadoCivil()) && UtilObjeto.isReferencia(entity.getDominioEstadoCivil().getId())) {

			entity.setDominioEstadoCivil(this.getDominioService().getReference(entity.getDominioEstadoCivil().getId()));
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
	 * Método responsável por validar a existencia do cpf
	 *
	 * @author rogerio.costa
	 *
	 * @param pessoaFisica
	 */
	public void validarExistenciaCPF(PessoaFisica pessoaFisica) {
		Organizacao organizacao = pessoaFisica.getPessoa() != null && pessoaFisica.getPessoa().getOrganizacao() != null ? pessoaFisica.getPessoa().getOrganizacao() : ( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getOrganizacao();

		PessoaFisica pessoa = this.getPessoaFisicaDao().findPorCPFAndOrganizacao(pessoaFisica.getCpf(), organizacao.getId());
		// Verifica se o cpf informado é já existe, caso existe verifica se pertence a outra pessoa
		if (UtilObjeto.isReferencia(pessoa) && ( !UtilObjeto.isReferencia(pessoaFisica.getId()) || UtilObjeto.isReferencia(pessoaFisica.getId()) && !pessoaFisica.getId().equals(pessoa.getId()) )) {

			throw new BusinessException(VALIDACAO_CPF_EXISTENTE, CodigoErro.REGRA_NEGOCIO.getValue());
		}
	}

	/**
	 * Retorna o valor do atributo <code>pessoaFisicaDao</code>
	 *
	 * @return <code>PessoaFisicaDao</code>
	 */
	public PessoaFisicaDao getPessoaFisicaDao() {

		return pessoaFisicaDao;
	}

	/**
	 * Retorna o valor do atributo <code>dominioService</code>
	 *
	 * @return <code>DominioService</code>
	 */
	public DominioService getDominioService() {

		return dominioService;
	}

	@Override
	public PessoaFisica findPorCPFAndOrganizacao(String cpf, long idOrganizacao) {
		return this.getPessoaFisicaDao().findPorCPFAndOrganizacao(cpf, idOrganizacao);
	}

}
