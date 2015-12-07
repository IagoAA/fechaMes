package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Notificacao;

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
 * @since 28/11/2014 - 09:26:08
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@Component("notificacaoValidator")
public class NotificacaoValidator implements Validator {

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public boolean supports(Class<?> clazz) {

		return Notificacao.class.isAssignableFrom(clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "mensagem", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.MENSAGEM");
		
		ValidationUtils.rejectIfEmpty(errors, "tipoNotificacao",  CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.TIPO_NOTIFICACAO");
		
		ValidationUtils.rejectIfEmpty(errors, "tipoPrioridade",  CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.TIPO_PRIORIDADE");

	}

}
