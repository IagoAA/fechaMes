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
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 22/01/2015 - 16:23:59
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
public class ErrorInfo {
    /** Atributo url. */
    public final String url;
    /** Atributo stack. */
    public final String stack;
    /** Atributo codigoErro. */
    public final String codigoErro;
    /** Atributo ex. */
    public final Throwable ex;

    public ErrorInfo(String url, Throwable ex, String codigoErro) {
        this.url = url;
        this.stack = ex.getLocalizedMessage();
        this.ex = ex;
        this.codigoErro = codigoErro;
    }


	/**
	 * Retorna o valor do atributo <code>url</code>
	 *
	 * @return <code>String</code>
	 */
	public String getUrl() {

		return url;
	}


	/**
	 * Retorna o valor do atributo <code>stack</code>
	 *
	 * @return <code>String</code>
	 */
	public String getStack() {

		return stack;
	}


	/**
	 * Retorna o valor do atributo <code>codigoErro</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigoErro() {

		return codigoErro;
	}


	/**
	 * Retorna o valor do atributo <code>ex</code>
	 *
	 * @return <code>Throwable</code>
	 */
	public Throwable getEx() {

		return ex;
	}
}