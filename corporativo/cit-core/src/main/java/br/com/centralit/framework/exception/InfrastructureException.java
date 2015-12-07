package br.com.centralit.framework.exception;

/**
 * This exception is used to mark (fatal) failures in infrastructure and system
 * code.
 * 
 * @author Ally Junio <allyjunio@gmail.com>
 */
@SuppressWarnings("serial")
public class InfrastructureException extends RuntimeException {

	public InfrastructureException() {
	}

	public InfrastructureException(String message) {
		super(message);
	}

	public InfrastructureException(String message, Throwable cause) {
		super(message, cause);
	}

	public InfrastructureException(Throwable cause) {
		super(cause);
	}
}
