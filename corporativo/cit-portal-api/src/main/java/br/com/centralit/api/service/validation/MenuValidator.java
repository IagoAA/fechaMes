package br.com.centralit.api.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Menu;
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
 * <b>Title: MenuValidator</b>
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
 * @since 09/12/2014 - 17:42:43
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Component("menuValidator")
public class MenuValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Menu.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "nome", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "Nome");

		Menu menu = (Menu) target;

		if (UtilObjeto.isReferencia(menu.getPagina()) && !menu.getPagina().getPagina().equals("")) {
			ValidationUtils.rejectIfEmpty(errors, "pagina.nome", CodigoErro.VALIDACAO_CAMPOS.getValue().toString(), "Título página");
		}
	}

}
