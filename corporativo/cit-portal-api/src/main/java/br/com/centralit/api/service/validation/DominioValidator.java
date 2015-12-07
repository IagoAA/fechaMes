package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Dominio;

@Component("dominioValidator")
public class DominioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Dominio.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "chave", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.CHAVE");

		ValidationUtils.rejectIfEmpty(errors, "codigo", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.CODIGO");

		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.NOME_DOMINIO");

		ValidationUtils.rejectIfEmpty(errors, "descricao", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.DESCRICAO");

	}
}
