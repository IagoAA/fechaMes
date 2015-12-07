package br.com.centralit.framework.exception;

/**
 * Exceção usada para erros de negócio
 * 
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

	/** Atributo codigoErro. */
	private Integer codigoErro;

	/** Atributo msgComplemento. */
	private String msgComplemento;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public BusinessException() {

	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param message
	 */
	public BusinessException( String message ) {

		super(message);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param message
	 * @param cause
	 */
	public BusinessException( String message, Throwable cause ) {

		super(message, cause);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param message
	 * @param codigoErro
	 */
	public BusinessException( String message, Integer codigoErro ) {

		this(message);
		this.codigoErro = codigoErro;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param message
	 * @param codigoErro
	 * @param mensagemComplemento
	 */
	public BusinessException( String message, Integer codigoErro, String mensagemComplemento ) {

		this(message, codigoErro);
		this.msgComplemento = mensagemComplemento;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param cause
	 */
	public BusinessException( Throwable cause ) {

		super(cause);
	}

	/**
	 * Retorna o valor do atributo <code>codigoErro</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getCodigoErro() {

		return codigoErro;
	}

	/**
	 * Define o valor do atributo <code>codigoErro</code>.
	 * 
	 * @param codigoErro
	 */
	public void setCodigoErro(Integer codigoErro) {

		this.codigoErro = codigoErro;
	}

	/**
	 * Retorna o valor do atributo <code>msgComplemento</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getMsgComplemento() {

		return msgComplemento;
	}

	/**
	 * Define o valor do atributo <code>msgComplemento</code>.
	 * 
	 * @param msgComplemento
	 */
	public void setMsgComplemento(String msgComplemento) {

		this.msgComplemento = msgComplemento;
	}

}
