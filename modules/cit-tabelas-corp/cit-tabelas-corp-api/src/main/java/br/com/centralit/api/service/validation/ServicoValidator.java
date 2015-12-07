package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.Servico;
import br.com.centralit.framework.exception.CodigoErro;

@Component("servicoValidator")
public class ServicoValidator implements Validator {

    @Override
	public boolean supports(Class<?> clazz) {

		return Servico.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.NOME");

		ValidationUtils.rejectIfEmpty(errors, "sigla", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.SIGLA");

		//ValidationUtils.rejectIfEmpty(errors, "dominioTipoServico", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.DOMINIOTIPOSERVICO");

	}
}

