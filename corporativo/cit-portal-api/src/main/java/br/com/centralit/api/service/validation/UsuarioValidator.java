package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.util.UtilColecao;

@Component("usuarioValidator")
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "username", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.USERNAME");

		ValidationUtils.rejectIfEmpty(errors, "password", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.SENHA");

		ValidationUtils.rejectIfEmpty(errors, "email", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.EMAIL");
		
		ValidationUtils.rejectIfEmpty(errors, "organizacao", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.ORGANIZACAO");

		Usuario usuario = (Usuario) target;
		
		if (UtilColecao.isVazio(usuario.getOrganizacoes())) {

			errors.rejectValue("organizacoes", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.ORGANIZACOES");
		}
	}

}
