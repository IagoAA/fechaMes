package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

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
 * <b>Title: </b>Entidade<code>ClasseParceiro</code>
 * </p>
 *
 * <p>
 * <b>Description: </b> Entidade<code>ClasseParceiro</code>
 * </p>
 *
 * @since 27/11/2014 - 14:30:48
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Entity
public class ClasseParceiro extends PersistentObject {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -7422333888546234579L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo dominioParceiro. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.PessoaEditView.class })
	private Dominio dominioTipoParceiro;

	public ClasseParceiro(){

	};

	public ClasseParceiro(Dominio dominioTipoParceiro){
		this.dominioTipoParceiro = dominioTipoParceiro;
	}

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
	 * Retorna o valor do atributo <code>dominioTipoParceiro</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoParceiro() {

		return dominioTipoParceiro;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoParceiro</code>.
	 *
	 * @param dominioTipoParceiro
	 */
	public void setDominioTipoParceiro(Dominio dominioTipoParceiro) {

		this.dominioTipoParceiro = dominioTipoParceiro;
	}

	/**
	 * Retorna o valor do atributo <code>serialversionuid</code>
	 *
	 * @return <code>long</code>
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
