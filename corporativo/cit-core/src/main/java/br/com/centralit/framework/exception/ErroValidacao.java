package br.com.centralit.framework.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.centralit.framework.util.UtilObjeto;

public class ErroValidacao implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -3102857297406769070L;

	private String codigoErro;

	private String objeto;

	private List<Erro> erros;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public ErroValidacao() {

		super();
	}
	
	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param codigoErro
	 * @param objeto
	 */
	public ErroValidacao( String codigoErro, String objeto ) {

		super();
		this.codigoErro = codigoErro;
		this.objeto = objeto;
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
	 * Define o valor do atributo <code>codigoErro</code>.
	 * 
	 * @param codigoErro
	 */
	public void setCodigoErro(String codigoErro) {

		this.codigoErro = codigoErro;
	}

	/**
	 * Retorna o valor do atributo <code>objeto</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getObjeto() {

		return objeto;
	}

	/**
	 * Define o valor do atributo <code>objeto</code>.
	 * 
	 * @param objeto
	 */
	public void setObjeto(String objeto) {

		this.objeto = objeto;
	}

	/**
	 * Retorna o valor do atributo <code>erros</code>
	 * 
	 * @return <code>List<Erro></code>
	 */
	public List<Erro> getErros() {

		if (!UtilObjeto.isReferencia(erros)) {

			erros = new ArrayList<Erro>();

		}
		return erros;
	}

	/**
	 * Define o valor do atributo <code>erros</code>.
	 * 
	 * @param erros
	 */
	public void setErros(List<Erro> erros) {

		this.erros = erros;
	}

	@Override
	public String toString() {

		return "ErroValidacao [codigoErro=" + codigoErro + ", objeto=" + objeto + "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigoErro == null ) ? 0 : codigoErro.hashCode() );
		result = prime * result + ( ( objeto == null ) ? 0 : objeto.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErroValidacao other = (ErroValidacao) obj;
		if (codigoErro == null) {
			if (other.codigoErro != null)
				return false;
		} else if (!codigoErro.equals(other.codigoErro))
			return false;
		if (objeto == null) {
			if (other.objeto != null)
				return false;
		} else if (!objeto.equals(other.objeto))
			return false;
		return true;
	}

}
