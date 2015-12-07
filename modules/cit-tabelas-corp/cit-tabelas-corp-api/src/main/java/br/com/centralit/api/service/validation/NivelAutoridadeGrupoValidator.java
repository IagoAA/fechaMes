package br.com.centralit.api.service.validation;

import br.com.centralit.api.model.NivelAutoridadeGrupo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;

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
 * @since 22/06/2015 - 15:50:51
 *
 * @version 1.0.0
 *
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 *	
 */
@Component("nivelAutoridadeGrupoValidator")
public class NivelAutoridadeGrupoValidator implements Validator {

    @Override
	public boolean supports(Class<?> clazz) {

		return NivelAutoridadeGrupo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        
        ValidationUtils.rejectIfEmpty(errors, "grupo", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.GRUPO");
                   
        ValidationUtils.rejectIfEmpty(errors, "nivelAutoridade", CodigoErro.VALIDACAO_CAMPOS_OBRIGATORIOS.getValue().toString(), "LABEL.NIVELAUTORIDADE");
                   
	}
}

