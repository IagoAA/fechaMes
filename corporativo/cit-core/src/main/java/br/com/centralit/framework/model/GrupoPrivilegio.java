package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

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
 * <b>Title: </b>Role
 * </p>
 * 
 * <p>
 * <b>Description: </b>Entidade<code>Role</code>
 * </p>
 * 
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 * 
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 * 
 * @since 04/03/2015 - 15:17:23
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
@JsonIgnoreProperties({ "$selected"})
public class GrupoPrivilegio extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -4843141898445311856L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo grupo. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Grupo grupo;

	/** Atributo privilegio. */
	@JsonView({ Views.GrupoEditView.class })
	@ManyToOne(fetch = FetchType.LAZY)
	private Privilegio privilegio;

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
	 * Retorna o valor do atributo <code>grupo</code>
	 * 
	 * @return <code>Grupo</code>
	 */
	public Grupo getGrupo() {

		return grupo;
	}

	/**
	 * Define o valor do atributo <code>grupo</code>.
	 * 
	 * @param grupo
	 */
	public void setGrupo(Grupo grupo) {

		this.grupo = grupo;
	}

	/**
	 * Retorna o valor do atributo <code>privilegio</code>
	 * 
	 * @return <code>Privilegio</code>
	 */
	public Privilegio getPrivilegio() {

		return privilegio;
	}

	/**
	 * Define o valor do atributo <code>privilegio</code>.
	 * 
	 * @param privilegio
	 */
	public void setPrivilegio(Privilegio privilegio) {

		this.privilegio = privilegio;
	}

}
