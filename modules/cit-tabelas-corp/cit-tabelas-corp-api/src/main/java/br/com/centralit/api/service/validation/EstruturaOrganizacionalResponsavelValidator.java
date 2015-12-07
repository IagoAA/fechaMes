package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
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
 * <b>Title: EstruturaOrganizacionalResponsavelValidator</b>
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
 * @since 29/12/2014 - 10:37:55
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Component("estruturaOrganizacionalResponsavelValidator")
public class EstruturaOrganizacionalResponsavelValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return EstruturaOrganizacionalResponsavel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

//		ValidationUtils.rejectIfEmpty(errors, "ordem", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "Ordem");
//
		ValidationUtils.rejectIfEmpty(errors, "pessoa", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "Responsável");
//
		ValidationUtils.rejectIfEmpty(errors, "funcao", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "Função");

	}
}
