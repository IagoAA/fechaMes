package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * <b>Title: </b> Classe UsuarioPrivilegio
 * </p>
 * 
 * <p>
 * <b>Description: </b> Classe de dóminio responsável por definir provilégios ao usuário
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
 * @since 18/11/2014 - 16:25:52
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@Entity
@Table(name = "seguranca_usuario_privilegio")
@JsonIgnoreProperties({ "$selected" })
public class UsuarioPrivilegio extends PersistentObjectAudit implements GrantedAuthority {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -8503052269804529123L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo usuario. */
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuarioInativo;

	/** Atributo privilegio. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonView({ Views.UsuarioEditView.class })
	private Privilegio privilegio;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public UsuarioPrivilegio() {

	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param usuario
	 * @param privilegio
	 */
	public UsuarioPrivilegio( Usuario usuario, Privilegio privilegio ) {

		super();
		this.usuario = usuario;
		this.privilegio = privilegio;
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
	 * Retorna o valor do atributo <code>usuario</code>
	 * 
	 * @return <code>Usuario</code>
	 */
	public Usuario getUsuario() {

		return usuario;
	}

	/**
	 * Define o valor do atributo <code>usuario</code>.
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
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

	/**
	 * 
	 * Método responsável por retornar o nome do privilégio
	 * 
	 * @author wilker.machado
	 * 
	 * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Transient
	public String getAuthority() {

		return privilegio.getNome();
	}

	/**
	 * Retorna o valor do atributo <code>usuarioInativo</code>
	 * 
	 * @return <code>Usuario</code>
	 */
	public Usuario getUsuarioInativo() {

		return usuarioInativo;
	}

	/**
	 * Define o valor do atributo <code>usuarioInativo</code>.
	 * 
	 * @param usuarioInativo
	 */
	public void setUsuarioInativo(Usuario usuarioInativo) {

		this.usuarioInativo = usuarioInativo;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( privilegio == null ) ? 0 : privilegio.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPrivilegio other = (UsuarioPrivilegio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (privilegio == null) {
			if (other.privilegio != null)
				return false;
		} else if (!privilegio.equals(other.privilegio))
			return false;
		return true;
	}

}
