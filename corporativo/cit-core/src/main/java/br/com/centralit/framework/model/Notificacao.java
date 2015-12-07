package br.com.centralit.framework.model;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import br.com.centralit.framework.json.JsonCalendarSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
 * <b>Title:Notificação</b>
 * </p>
 *
 * <p>
 * <b>Description: Classe que representa as notificações do sistema</b>
 * </p>
 *
 * @since 19/11/2014 - 10:48:54
 *
 * @version 1.0.0
 *
 * @author rodrigo.anaice
 *
 */
@Entity
public class Notificacao extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -7393276042263872730L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo assunto. */
	@JsonView({ Views.NotificacaoAutoCompleteView.class, Views.NotificacaoEditView.class })
	private String assunto;

	/** Atributo mensagem. */
	// TODO SQLSERVERONLY - Encontrar alguma solução de texto longo que funcione no SQLServer e nos outros bancos, pois o tipo 'text' da problema, porque não pode ser comparado
	//@Column(length = 3000)
	//@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type="org.hibernate.type.StringClobType")
	@JsonView({ Views.NotificacaoListView.class })
	private String mensagem;

	@JsonView({ Views.NotificacaoEditView.class })
	private String identificador;

	/** Atributo dataVisualizacao. */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(Views.NotificacaoListView.class)
	@JsonSerialize(using = JsonCalendarSerializer.class)
	private Calendar dataVisualizacao;

	/** Atributo tipoNotificacao. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView(Views.NotificacaoListView.class)
	private Dominio tipoNotificacao;

	/** Atributo tipoPrioridade. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView(Views.NotificacaoListView.class)
	private Dominio tipoPrioridade;

	/** Atributo usuario. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView(Views.NotificacaoListView.class)
	private Usuario usuario;

	/** Atributo notificacaoGrupos. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notificacao", cascade = CascadeType.ALL)
	private Collection<NotificacaoGrupo> notificacaoGrupos;

	/** Atributo notificacaoUsuarios. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notificacao", cascade = CascadeType.ALL)
	private Collection<NotificacaoUsuario> notificacaoUsuarios;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Notificacao() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param assunto
	 * @param mensagem
	 * @param tipoNotificacao
	 * @param tipoPrioridade
	 * @param usuario
	 */
	public Notificacao( String assunto, String mensagem, Dominio tipoNotificacao, Dominio tipoPrioridade, Usuario usuario, Calendar dataVisualizacao, String identificador ) {

		this.assunto = assunto;
		this.mensagem = mensagem;
		this.tipoNotificacao = tipoNotificacao;
		this.tipoPrioridade = tipoPrioridade;
		this.usuario = usuario;
		this.dataVisualizacao = dataVisualizacao;
		this.identificador = identificador;
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
	 * Retorna o valor do atributo <code>mensagem</code>
	 *
	 * @return <code>String</code>
	 */
	public String getMensagem() {

		return mensagem;
	}

	/**
	 * Define o valor do atributo <code>mensagem</code>.
	 *
	 * @param mensagem
	 */
	public void setMensagem(String mensagem) {

		this.mensagem = mensagem;
	}

	/**
	 * Retorna o valor do atributo <code>dataVisualizacao</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataVisualizacao() {

		return dataVisualizacao;
	}

	/**
	 * Define o valor do atributo <code>dataVisualizacao</code>.
	 *
	 * @param dataVisualizacao
	 */
	public void setDataVisualizacao(Calendar dataVisualizacao) {

		this.dataVisualizacao = dataVisualizacao;
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
	 * Retorna o valor do atributo <code>tipoNotificacao</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getTipoNotificacao() {

		return tipoNotificacao;
	}

	/**
	 * Define o valor do atributo <code>tipoNotificacao</code>.
	 *
	 * @param tipoNotificacao
	 */
	public void setTipoNotificacao(Dominio tipoNotificacao) {

		this.tipoNotificacao = tipoNotificacao;
	}

	/**
	 * Retorna o valor do atributo <code>tipoPrioridade</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getTipoPrioridade() {

		return tipoPrioridade;
	}

	/**
	 * Define o valor do atributo <code>tipoPrioridade</code>.
	 *
	 * @param tipoPrioridade
	 */
	public void setTipoPrioridade(Dominio tipoPrioridade) {

		this.tipoPrioridade = tipoPrioridade;
	}

	/**
	 * Retorna o valor do atributo <code>serialversionuid</code>
	 *
	 * @return <code>long</code>
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

	/**
	 * Retorna o valor do atributo <code>assunto</code>
	 *
	 * @return <code>String</code>
	 */
	public String getAssunto() {

		return assunto;
	}

	/**
	 * Define o valor do atributo <code>assunto</code>.
	 *
	 * @param assunto
	 */
	public void setAssunto(String assunto) {

		this.assunto = assunto;
	}

	/**
	 * Retorna o valor do atributo <code>notificacaoGrupos</code>
	 *
	 * @return <code>Collection<NotificacaoGrupo></code>
	 */
	public Collection<NotificacaoGrupo> getNotificacaoGrupos() {

		return notificacaoGrupos;
	}

	/**
	 * Define o valor do atributo <code>notificacaoGrupos</code>.
	 *
	 * @param notificacaoGrupos
	 */
	public void setNotificacaoGrupos(Collection<NotificacaoGrupo> notificacaoGrupos) {

		this.notificacaoGrupos = notificacaoGrupos;
	}

	/**
	 * Retorna o valor do atributo <code>notificacaoUsuarios</code>
	 *
	 * @return <code>Collection<NotificacaoUsuario></code>
	 */
	public Collection<NotificacaoUsuario> getNotificacaoUsuarios() {

		return notificacaoUsuarios;
	}

	/**
	 * Define o valor do atributo <code>notificacaoUsuarios</code>.
	 *
	 * @param notificacaoUsuarios
	 */
	public void setNotificacaoUsuarios(Collection<NotificacaoUsuario> notificacaoUsuarios) {

		this.notificacaoUsuarios = notificacaoUsuarios;
	}

	/**
	 * Retorna o valor do atributo <code>identificador</code>
	 *
	 * @return <code>String</code>
	 */
	public String getIdentificador() {

		return identificador;
	}

	/**
	 * Define o valor do atributo <code>identificador</code>.
	 *
	 * @param identificador
	 */
	public void setIdentificador(String identificador) {

		this.identificador = identificador;
	}

}
