package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Internacionalizacao;

@Component("internacionalizacaoValidator")
public class InternacionalizacaoValidator implements Validator {

    @Override
	public boolean supports(Class<?> clazz) {

		return Internacionalizacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        
//        ValidationUtils.rejectIfEmpty(errors, "modulo", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.MODULO");
                   
        ValidationUtils.rejectIfEmpty(errors, "tipoDominioIdioma", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.TIPODOMINIOIDIOMA");
        
	}
}

