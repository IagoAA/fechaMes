package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.MapaOrganizacional;
import br.com.centralit.framework.exception.CodigoErro;

@Component("mapaOrganizacionalValidator")
public class MapaOrganizacionalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return MapaOrganizacional.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.NOME");

		ValidationUtils.rejectIfEmpty(errors, "dataInicio", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.DATA_INICIO");

	}
}
