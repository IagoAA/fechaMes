package br.com.centralit.framework.exception;



/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 * <p><b>Company: </b> Central IT - Governan&ccedil;a Corporativa - </p>
 *
 * <p><b>Description: </b>
 * Exce&ccedil;&atilde;o para erros inseridos em arquivos de configura&ccedil;&atilde;o.
 *
 * @since 15/06/2015 - 10:59:47
 * @version 1.0.0
 * @author ciro.junior (<a href="mailto:ciro.junior@centralit.com.br">ciro.junior@centralit.com.br</a>)
 */
@SuppressWarnings("serial")
public class ApplicationConfigurationException extends RuntimeException {

	public ApplicationConfigurationException() {
	}

	public ApplicationConfigurationException(String message) {
		super(message);
	}

	public ApplicationConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationConfigurationException(Throwable cause) {
		super(cause);
	}
}
