package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.Pessoa;
import br.com.centralit.framework.exception.CodigoErro;

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
 * @since 15/12/2014 - 08:34:22
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Component("pessoaValidator")
public class PessoaValidator implements Validator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return Pessoa.class.isAssignableFrom(clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.NOME");

		ValidationUtils.rejectIfEmpty(errors, "dominioPessoa", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.TIPO_PESSOA");

	}

}
