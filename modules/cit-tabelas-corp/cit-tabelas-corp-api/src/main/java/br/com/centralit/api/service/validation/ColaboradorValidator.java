package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.Colaborador;
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
 * @since 08/01/2015 - 15:30:04
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Component("colaboradorValidator")
public class ColaboradorValidator implements Validator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return Colaborador.class.isAssignableFrom(clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "funcao", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.FUNCAO");

		ValidationUtils.rejectIfEmpty(errors, "matricula", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.MATRICULA");
		
		ValidationUtils.rejectIfEmpty(errors, "cargo", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.CARGO");
		
		ValidationUtils.rejectIfEmpty(errors, "estruturaOrganizacional", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.ESTRUTURA_ORGANIZACIONAL");

	}

}
