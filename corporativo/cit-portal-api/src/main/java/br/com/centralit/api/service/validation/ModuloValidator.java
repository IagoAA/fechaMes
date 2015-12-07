package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Modulo;

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
 * @since 15/09/2015 - 15:28:15
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Component("moduloValidator")
public class ModuloValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Modulo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.NOME");

		ValidationUtils.rejectIfEmpty(errors, "baseUrl", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.URL");

		ValidationUtils.rejectIfEmpty(errors, "restAngular", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.RESTANGULAR");

		ValidationUtils.rejectIfEmpty(errors, "caminho", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.CAMINHO");

	}
}
