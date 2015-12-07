package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.Endereco;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.util.UtilObjeto;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: EnderecoValidator</b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 12/12/2014 - 14:24:59
 *
 * @version 1.0.0
 *
 * @author rogerio.cassimiro
 *	
 */
@Component("enderecoValidator")
public class EnderecoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return EnderecoValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.NOME");
		
		ValidationUtils.rejectIfEmpty(errors, "cep", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.CEP");
		
		ValidationUtils.rejectIfEmpty(errors, "logradouro", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.LOGRADOURO");
		
		ValidationUtils.rejectIfEmpty(errors, "numero", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.NUMERO");
		
		Endereco entity = (Endereco) target;
		
		if (!UtilObjeto.isReferencia(entity.getDominioTipoEndereco())){
			
			ValidationUtils.rejectIfEmpty(errors, "dominioTipoEndereco", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.TIPO_ENDERECO");
			
		}
		
		if (!UtilObjeto.isReferencia(entity.getCidade())) {
			
			ValidationUtils.rejectIfEmpty(errors, "cidade", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.CIDADE");
			
		}
		
		if (!UtilObjeto.isReferencia(entity.getBairro())) {
			
			ValidationUtils.rejectIfEmpty(errors, "bairro", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.BAIRRO");
			
		}
		
	}
}
