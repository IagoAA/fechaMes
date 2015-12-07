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
 * @since 04/03/2015 - 15:08:30
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
@JsonIgnoreProperties({ "$selected" })
public class GrupoUsuario extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -5576505600634865516L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo grupo. */
	@JsonView({ Views.UsuarioEditView.class })
	@ManyToOne(fetch = FetchType.EAGER)
	private Grupo grupo;

	/** Atributo usuario. */
	@JsonView({ Views.GrupoEditView.class })
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	/** Atributo grupoRemocao. */
	@ManyToOne
	private Grupo grupoRemocao;

	/** Atributo usuarioRemocao. */
	@ManyToOne
	private Usuario usuarioRemocao;

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
	 * Retorna o valor do atributo <code>grupoRemocao</code>
	 * 
	 * @return <code>Grupo</code>
	 */
	public Grupo getGrupoRemocao() {

		return grupoRemocao;
	}

	/**
	 * Define o valor do atributo <code>grupoRemocao</code>.
	 * 
	 * @param grupoRemocao
	 */
	public void setGrupoRemocao(Grupo grupoRemocao) {

		this.grupoRemocao = grupoRemocao;
	}

	/**
	 * Retorna o valor do atributo <code>usuarioRemocao</code>
	 * 
	 * @return <code>Usuario</code>
	 */
	public Usuario getUsuarioRemocao() {

		return usuarioRemocao;
	}

	/**
	 * Define o valor do atributo <code>usuarioRemocao</code>.
	 * 
	 * @param usuarioRemocao
	 */
	public void setUsuarioRemocao(Usuario usuarioRemocao) {

		this.usuarioRemocao = usuarioRemocao;
	}

}
