package br.com.centralit.framework.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

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
 * <b>Title: </b>Grupo
 * </p>
 *
 * <p>
 * <b>Description: </b>Entidade<code>Grupo</code>
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
 * @since 04/03/2015 - 15:04:36
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Entity
public class Grupo extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -6407704182664573030L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo nome. */
	@JsonView({ Views.GrupoListView.class, Views.LookupView.class,
				Views.UsuarioEditView.class, Views.PainelEditView.class,
				Views.GrupoAutoCompleteView.class,
				Views.MenuEditView.class })
	private String nome;

	/** Atributo sigla. */
	@JsonView({ Views.GrupoListView.class, Views.GrupoAutoCompleteView.class })
	private String sigla;

	/** Atributo email. */
	@JsonView({ Views.GrupoListView.class })
	private String email;

	/** Atributo grupoUsuarios. */
	@JsonView({ Views.GrupoEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo", cascade = CascadeType.ALL)
	private Collection<GrupoUsuario> grupoUsuarios;

	/** Atributo grupoPrivilegios. */
	@JsonView({ Views.GrupoEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo", cascade = CascadeType.ALL)
	private Collection<GrupoPrivilegio> grupoPrivilegios;

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

	/**
	 * Retorna o valor do atributo <code>sigla</code>
	 *
	 * @return <code>String</code>
	 */
	public String getSigla() {

		return sigla;
	}

	/**
	 * Define o valor do atributo <code>sigla</code>.
	 *
	 * @param sigla
	 */
	public void setSigla(String sigla) {

		this.sigla = sigla;
	}

	/**
	 * Retorna o valor do atributo <code>email</code>
	 *
	 * @return <code>String</code>
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * Define o valor do atributo <code>email</code>.
	 *
	 * @param email
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	/**
	 * Retorna o valor do atributo <code>grupoUsuarios</code>
	 *
	 * @return <code>Collection<GrupoUsuario></code>
	 */
	public Collection<GrupoUsuario> getGrupoUsuarios() {

		return grupoUsuarios;
	}

	/**
	 * Define o valor do atributo <code>grupoUsuarios</code>.
	 *
	 * @param grupoUsuarios
	 */
	public void setGrupoUsuarios(Collection<GrupoUsuario> grupoUsuarios) {

		this.grupoUsuarios = grupoUsuarios;
	}

	/**
	 * Retorna o valor do atributo <code>grupoPrivilegios</code>
	 *
	 * @return <code>Collection<GrupoPrivilegio></code>
	 */
	public Collection<GrupoPrivilegio> getGrupoPrivilegios() {

		return grupoPrivilegios;
	}

	/**
	 * Define o valor do atributo <code>grupoPrivilegios</code>.
	 *
	 * @param grupoPrivilegios
	 */
	public void setGrupoPrivilegios(Collection<GrupoPrivilegio> grupoPrivilegios) {

		this.grupoPrivilegios = grupoPrivilegios;
	}

}
