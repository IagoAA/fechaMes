package br.com.centralit.framework.exception;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * @since 26/11/2014 - 12:11:21
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
public enum CodigoErro {

	/** Atributo VALIDACAO_CAMPOS. */
	VALIDACAO_CAMPOS(700, "Erro de Validacao de campos"),

	/** Atributo VALIDACAO_CAMPOS_OBRIGATORIOS. */
	VALIDACAO_CAMPOS_OBRIGATORIOS(701, "Erro de Validacao de campos obrigatorios"),

	/** Atributo REGRA_NEGOCIO. */
	REGRA_NEGOCIO(702, "Erro de regra de negocio"),

	/** Atributo INFRAESTRUTURA. */
	INFRAESTRUTURA(703, "Erro de  infraestrutura");

	/** Atributo value. */
	private final Integer value;

	/** Atributo reasonPhrase. */
	private final String reasonPhrase;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param value
	 * @param reasonPhrase
	 */
	private CodigoErro( Integer value, String reasonPhrase ) {

		this.value = value;

		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Retorna o valor do atributo <code>value</code>
	 *
	 * @return <code>int</code>
	 */
	public Integer getValue() {

		return value;
	}

	/**
	 * Retorna o valor do atributo <code>reasonPhrase</code>
	 *
	 * @return <code>String</code>
	 */
	public String getReasonPhrase() {

		return reasonPhrase;
	}

}
