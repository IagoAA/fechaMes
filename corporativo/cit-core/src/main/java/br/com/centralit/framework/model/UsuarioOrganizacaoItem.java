package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * @since 19/02/2015 - 16:51:44
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Entity
public class UsuarioOrganizacaoItem extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -5419829762395910672L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo organizacao. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.UsuarioLogadoView.class, Views.UsuarioEditView.class })
	private Organizacao organizacao;

	/** Atributo usuario. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Usuario usuario;

	protected UsuarioOrganizacaoItem() {
		super();
	}
	
	/**
	 * Cria instancia com atributos obrigatorios
	 * 
	 * @param organizacao
	 * @param usuario
	 */
	public UsuarioOrganizacaoItem(Organizacao organizacao, Usuario usuario) {
		super();
		this.organizacao = organizacao;
		this.usuario = usuario;
	}

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

}
