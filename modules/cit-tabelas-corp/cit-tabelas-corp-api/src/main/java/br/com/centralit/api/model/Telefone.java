package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

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
 * <b>Title: </b>Telefone
 * </p>
 * 
 * <p>
 * <b>Description: </b>Entidade<code>Telefone</code>
 * </p>
 * 
 * @since 27/11/2014 - 14:34:59
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
@JsonIgnoreProperties({ "$checked" })
public class Telefone extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -8951817708567458537L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo numero. */
	@JsonView({ Views.GenericView.class })
	private String numero;

	/** Atributo dominioTelefone. */
	@ManyToOne
	@JoinColumn(name = "dominio_telefone")
	@JsonView({ Views.GenericView.class })
	private Dominio dominioTelefone;

	/** Atributo pessoa. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Pessoa pessoa;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 * 
	 * @param nome
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>numero</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNumero() {

		return numero;
	}

	/**
	 * Define o valor do atributo <code>numero</code>.
	 * 
	 * @param numero
	 */
	public void setNumero(String numero) {

		this.numero = numero;
	}

	/**
	 * Retorna o valor do atributo <code>dominioTelefone</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTelefone() {

		return dominioTelefone;
	}

	/**
	 * Define o valor do atributo <code>dominioTelefone</code>.
	 * 
	 * @param dominioTelefone
	 */
	public void setDominioTelefone(Dominio dominioTelefone) {

		this.dominioTelefone = dominioTelefone;
	}

	/**
	 * Retorna o valor do atributo <code>pessoa</code>
	 * 
	 * @return <code>Pessoa</code>
	 */
	public Pessoa getPessoa() {

		return pessoa;
	}

	/**
	 * Define o valor do atributo <code>pessoa</code>.
	 * 
	 * @param pessoa
	 */
	public void setPessoa(Pessoa pessoa) {

		this.pessoa = pessoa;
	}

}
