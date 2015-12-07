package br.com.centralit.framework.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
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
 * @since 26/02/2015 - 10:57:29
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Entity
@Table(name = "seguranca_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends PersistentObjectAudit implements UserDetails {

	private static final long serialVersionUID = -5503087876248538699L;

	public static final Long USUARIO_ADMIN = Long.valueOf(1);

	public static final Long USUARIO_SCHEDULER = Long.valueOf(2);

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	protected Long id;

	/** Atributo username. */
	@Column(nullable = false, length = 50, unique = false)
	@JsonView({ Views.GenericView.class, Views.UsuarioEditView.class, Views.PessoaEditView.class, Views.UsuarioLogadoListView.class, Views.MapaOrganizacionalListView.class, Views.UsuarioLogadoView.class, Views.GrupoEditView.class })
	private String username;

	/** Atributo password. */
	private String password;

	/** Atributo confirmPassword. */
	@Transient
	private String confirmPassword;

	/** Atributo passwordHint. */
	@Column
	@JsonView({ Views.UsuarioEditView.class })
	private String passwordHint;

	/** Atributo email. */
	@Column(nullable = false, unique = false)
	@JsonView({ Views.UsuarioEditView.class })
	private String email;

	/** Atributo website. */
	@Column
	private String website;

	/** Atributo usuarioPrivilegios. */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "usuario")
	@JsonView({ Views.UsuarioEditView.class })
	private Set<UsuarioPrivilegio> usuarioPrivilegios;

	/** Atributo contaHabilitada. */
	@Column(nullable = false)
	@JsonView({ Views.UsuarioEditView.class })
	private Boolean contaHabilitada;

	/** Atributo contaExpirada. */
	@Column(nullable = false)
	@JsonView({ Views.UsuarioEditView.class })
	private Boolean contaExpirada;

	/** Atributo contaBloqueada. */
	@Column(nullable = false)
	@JsonView({ Views.UsuarioEditView.class })
	private Boolean contaBloqueada;

	/** Atributo credencialExpirada. */
	@Column(nullable = false)
	@JsonView({ Views.UsuarioEditView.class })
	private Boolean credencialExpirada;

	/** Atributo paginasUsuario. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.MERGE)
	@JsonView({ Views.UsuarioEditView.class, Views.UsuarioLogadoListView.class, Views.UsuarioLogadoView.class })
	@Where(clause = "dataInativo is null")
	private Collection<PaginaUsuario> paginasUsuario;

	/** Atributo sempreNovaAba. */
	@Column(nullable = false)
	@JsonView({ Views.UsuarioEditView.class })
	private Boolean sempreNovaAba;

	/** Atributo novaSenha. */
	@Transient
	private String novaSenha;

	/** Atributo confirmNovoPassword. */
	@Transient
	private String confirmNovoPassword;

	/** Atributo tokenPasswordRecovery. */
	@Column
	@JsonView({ Views.UsuarioEditView.class })
	private String tokenPasswordRecovery;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonView({ Views.UsuarioLogadoView.class, Views.UsuarioEditView.class })
	@Where(clause = "dataInativo is null")
	private List<UsuarioOrganizacaoItem> organizacoes;

	/** Atributo grupoUsuarios. */
	@JsonView({ Views.UsuarioEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
	private Collection<GrupoUsuario> grupoUsuarios;

	@JsonView({ Views.UsuarioLogadoView.class, Views.UsuarioEditView.class })
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	private Organizacao organizacao;

	/** Atributo passwordMobile. */
	private String passwordMobile;

	/**
	 * Retorna o valor do atributo <code>passwordMobile</code>
	 *
	 * @return <code>String</code>
	 */
	public String getPasswordMobile() {

		return passwordMobile;
	}

	/**
	 * Define o valor do atributo <code>passwordMobile</code>.
	 *
	 * @param passwordMobile
	 */
	public void setPasswordMobile(String passwordMobile) {

		this.passwordMobile = passwordMobile;
	}

	/**
	 * Retorna o valor do atributo <code>organizacao</code>
	 *
	 * @return <code>Organizacao</code>
	 */
	public Organizacao getOrganizacao() {
		return organizacao;
	}

	/**
	 * Define o valor do atributo <code>organizacao</code>.
	 *
	 * @param organizacao
	 */
	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}

	public Set<UsuarioPrivilegio> getUsuarioPrivilegios() {

		return this.usuarioPrivilegios;
	}

	public void setUsuarioPrivilegios(Set<UsuarioPrivilegio> usuarioPrivilegios) {

		this.usuarioPrivilegios = usuarioPrivilegios;
	}

	public void addPrivilegio(Privilegio privilegio) {

		getUsuarioPrivilegios().add(new UsuarioPrivilegio(this, privilegio));
	}

	/**
	 * Retorna o valor do atributo <code>tokenPasswordRecovery</code>
	 *
	 * @return <code>String</code>
	 */
	public String getTokenPasswordRecovery() {

		return tokenPasswordRecovery;
	}

	/**
	 * Define o valor do atributo <code>tokenPasswordRecovery</code>.
	 *
	 * @param tokenPasswordRecovery
	 */
	public void setTokenPasswordRecovery(String tokenPasswordRecovery) {

		this.tokenPasswordRecovery = tokenPasswordRecovery;
	}

	@Transient
	@JsonIgnore
	public Set<GrantedAuthority> getAuthorities() {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Set<GrantedAuthority> authorities = new LinkedHashSet();
		authorities.addAll(this.usuarioPrivilegios);

		return authorities;
	}

	public boolean isEnabled() {

		return this.contaHabilitada.booleanValue();
	}

	public boolean isAccountExpired() {

		return this.contaExpirada.booleanValue();
	}

	@Transient
	public boolean isAccountNonExpired() {

		return !isAccountExpired();
	}

	public boolean isAccountLocked() {

		return this.contaBloqueada.booleanValue();
	}

	@Transient
	public boolean isAccountNonLocked() {

		return !isAccountLocked();
	}

	public boolean isCredentialsExpired() {

		return this.credencialExpirada.booleanValue();
	}

	@Transient
	public boolean isCredentialsNonExpired() {

		return !this.credencialExpirada.booleanValue();
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {

		this.confirmPassword = confirmPassword;
	}

	public void setPasswordHint(String passwordHint) {

		this.passwordHint = passwordHint;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public void setWebsite(String website) {

		this.website = website;
	}

	public Boolean getContaHabilitada() {

		return this.contaHabilitada;
	}

	public void setContaHabilitada(Boolean contaHabilitada) {

		this.contaHabilitada = contaHabilitada;
	}

	public Boolean getContaExpirada() {

		return this.contaExpirada;
	}

	public void setContaExpirada(Boolean contaExpirada) {

		this.contaExpirada = contaExpirada;
	}

	public Boolean getContaBloqueada() {

		return this.contaBloqueada;
	}

	public void setContaBloqueada(Boolean contaBloqueada) {

		this.contaBloqueada = contaBloqueada;
	}

	public Boolean getCredencialExpirada() {

		return this.credencialExpirada;
	}

	public void setCredencialExpirada(Boolean credencialExpirada) {

		this.credencialExpirada = credencialExpirada;
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

	public String getUsername() {

		return this.username;
	}

	public String getPassword() {

		return this.password;
	}

	public String getConfirmPassword() {

		return this.confirmPassword;
	}

	public String getPasswordHint() {

		return this.passwordHint;
	}

	public String getEmail() {

		return this.email;
	}

	public String getWebsite() {

		return this.website;
	}

	public Collection<PaginaUsuario> getPaginasUsuario() {

		return paginasUsuario;
	}

	public void setPaginasUsuario(Collection<PaginaUsuario> paginasUsuario) {

		this.paginasUsuario = paginasUsuario;
	}

	/**
	 * Retorna o valor do atributo <code>sempreNovaAba</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getSempreNovaAba() {

		return sempreNovaAba;
	}

	/**
	 * Define o valor do atributo <code>sempreNovaAba</code>.
	 *
	 * @param sempreNovaAba
	 */
	public void setSempreNovaAba(Boolean sempreNovaAba) {

		this.sempreNovaAba = sempreNovaAba;
	}

	/**
	 * Retorna o valor do atributo <code>organizacoes</code>
	 *
	 * @return <code>List<UsuarioOrganizacaoItem></code>
	 */
	public List<UsuarioOrganizacaoItem> getOrganizacoes() {

		return organizacoes;
	}

	/**
	 * Define o valor do atributo <code>organizacoes</code>.
	 *
	 * @param organizacoes
	 */
	public void setOrganizacoes(List<UsuarioOrganizacaoItem> organizacoes) {

		this.organizacoes = organizacoes;
	}

	/**
	 * Retorna o valor do atributo <code>novaSenha</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNovaSenha() {

		return novaSenha;
	}

	public String getConfirmNovoPassword() {

		return confirmNovoPassword;
	}

	public static Long getUsuarioAdmin() {

		return USUARIO_ADMIN;
	}

	public static Long getUsuarioScheduler() {

		return USUARIO_SCHEDULER;
	}

	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (!( o instanceof Usuario )) {
			return false;
		}
		Usuario user = (Usuario) o;

		return this.username != null ? this.username.equals(user.getUsername()) : user.getUsername() == null;
	}

	public int hashCode() {

		return this.username != null ? this.username.hashCode() : 0;
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

	@JsonIgnore
	public String toString() {

		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("username", this.username).append("contaHabilitada", this.contaHabilitada).append("contaExpirada", this.contaExpirada).append("credencialExpirada", this.credencialExpirada).append("contaBloqueada", this.contaBloqueada);
		int i;
		if (this.usuarioPrivilegios != null) {
			sb.append("Granted Authorities: ");

			i = 0;
			for (UsuarioPrivilegio usuarioPrivilegio : this.usuarioPrivilegios) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(usuarioPrivilegio.getPrivilegio().toString());
				i++;
			}
		} else {
			sb.append("No Granted Authorities");
		}
		return sb.toString();
	}
}
