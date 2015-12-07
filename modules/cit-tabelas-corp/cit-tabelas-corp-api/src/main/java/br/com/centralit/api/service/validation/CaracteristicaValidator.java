package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.Caracteristica;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.util.UtilObjeto;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 12/12/2014 - 11:07:11
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *	
 */
@Component("caracteristicaValidator")
public class CaracteristicaValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {

		return CaracteristicaValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "descricao", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.DESCRICAO");
		
		Caracteristica entity = (Caracteristica) target;
		
		if (!UtilObjeto.isReferencia(entity.getDominioTipoDado())){
			
			ValidationUtils.rejectIfEmpty(errors, "dominioTipoDado", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.TIPO_DADO");
			
		}
		
//		if (!UtilObjeto.isReferencia(entity.getDominioTipoRestricao())){
//			
//			ValidationUtils.rejectIfEmpty(errors, "dominioTipoRestricao", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.TIPO_RESTRICAO");
//			
//		}
		
		
	}
}
