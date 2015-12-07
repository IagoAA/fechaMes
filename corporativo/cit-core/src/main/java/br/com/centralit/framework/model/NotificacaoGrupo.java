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
 * @since 05/06/2015 - 14:54:09
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class NotificacaoGrupo extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -3489073103545284797L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo grupo. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView(Views.NotificacaoListView.class)
	private Grupo grupo;

	/** Atributo notificacao. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView(Views.NotificacaoListView.class)
	private Notificacao notificacao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param grupo
	 * @param notificacao
	 */
	public NotificacaoGrupo( Grupo grupo, Notificacao notificacao ) {

		this.grupo = grupo;

		this.notificacao = notificacao;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public NotificacaoGrupo() {

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

}
