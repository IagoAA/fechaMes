package br.com.centralit.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: Usada para manter o controle de acesso de recurso por roles</b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 19/01/2015 - 10:51:44
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
public class AccessRole extends PersistentObjectAudit {

	private static final long serialVersionUID = -5503087876248538699L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo url. */
	@Column
	@JsonView({ Views.GenericView.class })
	private String url;

	/** Atributo roles. */
	@Column
	@JsonView({ Views.GenericView.class })
	private String roles;


	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return id;
	}


	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
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
	 * Define o valor do atributo <code>url</code>.
	 *
	 * @param url
	 */
	public void setUrl(String url) {

		this.url = url;
	}


	/**
	 * Retorna o valor do atributo <code>roles</code>
	 *
	 * @return <code>String</code>
	 */
	public String getRoles() {

		return roles;
	}


	/**
	 * Define o valor do atributo <code>roles</code>.
	 *
	 * @param roles
	 */
	public void setRoles(String roles) {

		this.roles = roles;
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
