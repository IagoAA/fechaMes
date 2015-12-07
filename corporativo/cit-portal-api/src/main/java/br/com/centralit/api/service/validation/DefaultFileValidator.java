package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.DefaultFile;

@Component("defaultFileValidator")
public class DefaultFileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DefaultFile.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "caminho", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "Caminho");
	}

}
