package br.com.centralit.framework.exception;

import java.util.List;

public class ValidacaoException extends RuntimeException {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2885179118280848622L;

	private List<ErroValidacao> errosValidacao;

	/**
	 * Cria uma instância de ValidacaoException.
	 */
	public ValidacaoException() {

		super();
	}

	/**
	 * Cria o objeto e atribui a mensagem da exceção.
	 * 
	 * @param mensagem
	 *            Mensagem da exceção
	 */
	public ValidacaoException( String mensagem ) {

		super(mensagem);

	}

	/**
	 * Cria o objeto e atribui a mensagem e a causa da exceção.
	 * 
	 * @param mensagem
	 *            Mensagem da exceção
	 * @param causa
	 *            Causa da exceção
	 */
	public ValidacaoException( String mensagem, Throwable causa ) {

		super(mensagem, causa);

	}

	/**
	 * Cria o objeto e atribui a causa da exceção.
	 * 
	 * @param causa
	 *            Causa da exceção
	 */
	public ValidacaoException( Throwable causa ) {

		super(causa);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param mensagem
	 * 
	 * @param errosValidacao
	 */
	public ValidacaoException( String mensagem, List<ErroValidacao> errosValidacao ) {

		super(mensagem);

		this.errosValidacao = errosValidacao;

	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param errosValidacao
	 */
	public ValidacaoException( List<ErroValidacao> errosValidacao ) {

		super();
		this.errosValidacao = errosValidacao;
	}

	/**
	 * Retorna o valor do atributo <code>errosValidacao</code>
	 * 
	 * @return <code>List<ErroValidacao></code>
	 */
	public List<ErroValidacao> getErrosValidacao() {

		return errosValidacao;
	}

	/**
	 * Define o valor do atributo <code>errosValidacao</code>.
	 * 
	 * @param errosValidacao
	 */
	public void setErrosValidacao(List<ErroValidacao> errosValidacao) {

		this.errosValidacao = errosValidacao;
	}

}
