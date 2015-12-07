package br.com.centralit.api.viewHelper;

import java.io.Serializable;

import br.com.centralit.framework.model.arquitetura.PersistentObject;

public class EntidadeNomeBuscaVH<T extends PersistentObject> implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2342505270077662292L;

	private T objeto;

	private String nome;

	/**
	 * Retorna o valor do atributo <code>objeto</code>
	 * 
	 * @return <code>T</code>
	 */
	public T getObjeto() {

		return objeto;
	}

	/**
	 * Define o valor do atributo <code>objeto</code>.
	 * 
	 * @param objeto
	 */
	public void setObjeto(T objeto) {

		this.objeto = objeto;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

}
