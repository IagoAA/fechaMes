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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 * 
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 * 
 * @since 05/06/2015 - 15:07:47
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class NotificacaoUsuario extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -2780179802659726002L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo notificacao. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Notificacao notificacao;

	/** Atributo usuario. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param notificacao
	 * @param usuario
	 */
	public NotificacaoUsuario( Notificacao notificacao, Usuario usuario ) {

		this.notificacao = notificacao;

		this.usuario = usuario;
	}

	public NotificacaoUsuario( ) {

		super();
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
	 * Retorna o valor do atributo <code>notificacao</code>
	 * 
	 * @return <code>Notificacao</code>
	 */
	public Notificacao getNotificacao() {

		return notificacao;
	}

	/**
	 * Define o valor do atributo <code>notificacao</code>.
	 * 
	 * @param notificacao
	 */
	public void setNotificacao(Notificacao notificacao) {

		this.notificacao = notificacao;
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
