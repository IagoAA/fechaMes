package br.com.centralit.api.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.model.EstruturaOrganizacional;
import br.com.centralit.api.model.EstruturaOrganizacionalResponsavel;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.util.UtilObjeto;

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
 * <b>Title: EstruturaOrganizacionalValidator</b>
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
 * @since 29/12/2014 - 10:37:34
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Component("estruturaOrganizacionalValidator")
public class EstruturaOrganizacionalValidator implements Validator {

	@Autowired
	@Qualifier("estruturaOrganizacionalResponsavelValidator")
	private Validator estruturaOrganizacionalResponsavelValidator;

	@Override
	public boolean supports(Class<?> clazz) {

		return EstruturaOrganizacional.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.NOME");

		ValidationUtils.rejectIfEmpty(errors, "sigla", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.SIGLA");

		ValidationUtils.rejectIfEmpty(errors, "dominioTipoEstruturaOrganizacional", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.TIPO_DE_ESTRUTURA_ORGANIZACIONAL");

		ValidationUtils.rejectIfEmpty(errors, "codigo", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.CODIGO");

		ValidationUtils.rejectIfEmpty(errors, "classificacao", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.CLASSIFICACAO");

		ValidationUtils.rejectIfEmpty(errors, "dataInicio", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.DATA_INICIO");

		ValidationUtils.rejectIfEmpty(errors, "localizacao", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.LOCALIZACAO");

		EstruturaOrganizacional estruturaOrganizacional = (EstruturaOrganizacional) target;
		
		// TODO trocar para classe validadora de estrutura organizacional responsavel
		// VERIFICAR SE TEM UM RESPONSAVEL SETADO NA ESTRUTURA ORGANIZACIONAL
		if (UtilObjeto.isReferencia(estruturaOrganizacional.getEstruturasOrganizacionalResponsaveis()) && !estruturaOrganizacional.getEstruturasOrganizacionalResponsaveis().isEmpty()) {
			// PASSA POR TODOS OS RESPONSAVEIS LIGADOS A ESTRUTURA ORGANIZACIONAL
			for (EstruturaOrganizacionalResponsavel estruturaOrganizacionalResponsavel : estruturaOrganizacional.getEstruturasOrganizacionalResponsaveis()) {
				// VERIFICAR SE A ORDEM FOI INFORMADA
				if(!UtilObjeto.isReferencia(estruturaOrganizacionalResponsavel.getOrdem())){
					// LANCA ERRO
					errors.rejectValue("ordem", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.ORDEM");
				}
				
				// VERIFICA SE A PESSOA FOI INFORMADA
				if(!UtilObjeto.isReferencia(estruturaOrganizacionalResponsavel.getResponsavel().getPessoa())){
					// LANCA ERRO
					errors.rejectValue("pessoa", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "LABEL.RESPONSAVEL");
				}
				
			}
		}
	}
}
