package br.com.centralit.api.service.validation;

import br.com.centralit.api.model.Funcao;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;

@Component("funcaoValidator")
public class FuncaoValidator implements Validator {

    @Override
	public boolean supports(Class<?> clazz) {

		return Funcao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmpty(errors, "codigo", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.CODIGO");
                   
        ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.NOME");
                   
	}
}

