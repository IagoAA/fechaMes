package br.com.centralit.framework.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
 * @since 09/03/2015 - 17:33:57
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
public class PainelItem extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({ Views.PainelEditView.class })
	private String nome;

	/** Atributo type. */
	@JsonView({ Views.PainelEditView.class })
	private String type;

	/** Atributo posicaoColumn. */
	@JsonView({ Views.PainelEditView.class })
	private Integer posicaoColumn;

	@JsonView({ Views.PainelEditView.class })
	private Integer posicaoLinha;

	/** Atributo posicaoSuperColumn. */
	@JsonView({ Views.PainelEditView.class })
	private Integer posicaoSuperColumn;

	/** Atributo styleClass. */
	@JsonView({ Views.PainelEditView.class })
	private String styleClass;

	/** Atributo location. */
	@JsonView({ Views.PainelEditView.class })
	private String location;

	/** Atributo remover. */
	@Transient
	@JsonView({ Views.PainelEditView.class })
	private Boolean remover;

	/** Atributo urlServico. */
	@JsonView({ Views.PainelEditView.class })
	private String urlServico;

	/** Atributo tempoAtualizacao. */
	@JsonView({ Views.PainelEditView.class })
	private Integer tempoAtualizacao;

	/** Atributo regraDefinida. */
	private boolean regraDefinida;

	/** Atributo copia. */
	@Transient
	private boolean copia;

	/** Atributo painel. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Painel painel;

	/** Atributo tipoWidgetDominio. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.PainelEditView.class })
	private Widget widget;

	/** Atributo painelItemParametros. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painelItem", cascade = CascadeType.ALL)
	@JsonView({ Views.PainelEditView.class })
	private Collection<PainelItemParametro> painelItemParametros;

	/** Atributo links. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painelItem", cascade = CascadeType.ALL)
	@JsonView({ Views.PainelEditView.class })
	private Collection<Link> links;

	/** Atributo painelItemGrupos. */
	@JsonView({ Views.PainelEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painelItem", cascade = CascadeType.ALL)
	private Collection<PainelItemGrupo> painelItemGrupos;

	/** Atributo painelItemPrivilegios. */
	@JsonView({ Views.PainelEditView.class })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "painelItem", cascade = CascadeType.ALL)
	private Collection<PainelItemPrivilegio> painelItemPrivilegios;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	@Override
	public Long getId() {

		return this.id;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNome() {

		return this.nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 * 
	 * @param nome
	 */
	public void setNome(final String nome) {

		this.nome = nome;
	}

	/**
	 * Retorna o valor do atributo <code>painel</code>
	 * 
	 * @return <code>Painel</code>
	 */
	public Painel getPainel() {

		return this.painel;
	}

	/**
	 * Define o valor do atributo <code>painel</code>.
	 * 
	 * @param painel
	 */
	public void setPainel(final Painel painel) {

		this.painel = painel;
	}

	/**
	 * Retorna o valor do atributo <code>widget</code>
	 * 
	 * @return <code>Widget</code>
	 */
	public Widget getWidget() {

		return this.widget;
	}

	/**
	 * Define o valor do atributo <code>widget</code>.
	 * 
	 * @param widget
	 */
	public void setWidget(final Widget widget) {

		this.widget = widget;
	}

	/**
	 * Retorna o valor do atributo <code>painelItemParametros</code>
	 * 
	 * @return <code>Collection<PainelItemParametro></code>
	 */
	public Collection<PainelItemParametro> getPainelItemParametros() {

		return this.painelItemParametros;
	}

	/**
	 * Define o valor do atributo <code>painelItemParametros</code>.
	 * 
	 * @param painelItemParametros
	 */
	public void setPainelItemParametros(final Collection<PainelItemParametro> painelItemParametros) {

		this.painelItemParametros = painelItemParametros;
	}

	/**
	 * Retorna o valor do atributo <code>type</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getType() {

		return this.type;
	}

	/**
	 * Define o valor do atributo <code>type</code>.
	 * 
	 * @param type
	 */
	public void setType(final String type) {

		this.type = type;
	}

	/**
	 * Retorna o valor do atributo <code>posicaoColumn</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getPosicaoColumn() {

		return this.posicaoColumn;
	}

	/**
	 * Define o valor do atributo <code>posicaoColumn</code>.
	 * 
	 * @param posicaoColumn
	 */
	public void setPosicaoColumn(final Integer posicaoColumn) {

		this.posicaoColumn = posicaoColumn;
	}

	/**
	 * Retorna o valor do atributo <code>styleClass</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getStyleClass() {

		return this.styleClass;
	}

	/**
	 * Define o valor do atributo <code>styleClass</code>.
	 * 
	 * @param styleClass
	 */
	public void setStyleClass(final String styleClass) {

		this.styleClass = styleClass;
	}

	/**
	 * Retorna o valor do atributo <code>location</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getLocation() {

		return this.location;
	}

	/**
	 * Define o valor do atributo <code>location</code>.
	 * 
	 * @param location
	 */
	public void setLocation(final String location) {

		this.location = location;
	}

	/**
	 * Retorna o valor do atributo <code>posicaoLinha</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getPosicaoLinha() {

		return this.posicaoLinha;
	}

	/**
	 * Define o valor do atributo <code>posicaoLinha</code>.
	 * 
	 * @param posicaoLinha
	 */
	public void setPosicaoLinha(final Integer posicaoLinha) {

		this.posicaoLinha = posicaoLinha;
	}

	/**
	 * Retorna o valor do atributo <code>remover</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getRemover() {

		return this.remover;
	}

	/**
	 * Define o valor do atributo <code>remover</code>.
	 * 
	 * @param remover
	 */
	public void setRemover(final Boolean remover) {

		this.remover = remover;
	}

	/**
	 * Retorna o valor do atributo <code>urlServico</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getUrlServico() {

		return urlServico;
	}

	/**
	 * Define o valor do atributo <code>urlServico</code>.
	 * 
	 * @param urlServico
	 */
	public void setUrlServico(String urlServico) {

		this.urlServico = urlServico;
	}

	/**
	 * Retorna o valor do atributo <code>posicaoSuperColumn</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getPosicaoSuperColumn() {

		return posicaoSuperColumn;
	}

	/**
	 * Define o valor do atributo <code>posicaoSuperColumn</code>.
	 * 
	 * @param posicaoSuperColumn
	 */
	public void setPosicaoSuperColumn(Integer posicaoSuperColumn) {

		this.posicaoSuperColumn = posicaoSuperColumn;
	}

	/**
	 * Retorna o valor do atributo <code>links</code>
	 * 
	 * @return <code>Collection<Link></code>
	 */
	public Collection<Link> getLinks() {

		return links;
	}

	/**
	 * Define o valor do atributo <code>links</code>.
	 * 
	 * @param links
	 */
	public void setLinks(Collection<Link> links) {

		this.links = links;
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
	 * Retorna o valor do atributo <code>tempoAtualizacao</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getTempoAtualizacao() {

		return tempoAtualizacao;
	}

	/**
	 * Define o valor do atributo <code>tempoAtualizacao</code>.
	 * 
	 * @param tempoAtualizacao
	 */
	public void setTempoAtualizacao(Integer tempoAtualizacao) {

		this.tempoAtualizacao = tempoAtualizacao;
	}

	/**
	 * Retorna o valor do atributo <code>painelItemGrupos</code>
	 * 
	 * @return <code>Collection<PainelItemGrupo></code>
	 */
	public Collection<PainelItemGrupo> getPainelItemGrupos() {

		return painelItemGrupos;
	}

	/**
	 * Define o valor do atributo <code>painelItemGrupos</code>.
	 * 
	 * @param painelItemGrupos
	 */
	public void setPainelItemGrupos(Collection<PainelItemGrupo> painelItemGrupos) {

		this.painelItemGrupos = painelItemGrupos;
	}

	/**
	 * Retorna o valor do atributo <code>painelItemPrivilegios</code>
	 * 
	 * @return <code>Collection<PainelItemPrivilegio></code>
	 */
	public Collection<PainelItemPrivilegio> getPainelItemPrivilegios() {

		return painelItemPrivilegios;
	}

	/**
	 * Define o valor do atributo <code>painelItemPrivilegios</code>.
	 * 
	 * @param painelItemPrivilegios
	 */
	public void setPainelItemPrivilegios(Collection<PainelItemPrivilegio> painelItemPrivilegios) {

		this.painelItemPrivilegios = painelItemPrivilegios;
	}

	/**
	 * Retorna o valor do atributo <code>regraDefinida</code>
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isRegraDefinida() {

		return regraDefinida;
	}

	/**
	 * Define o valor do atributo <code>regraDefinida</code>.
	 * 
	 * @param regraDefinida
	 */
	public void setRegraDefinida(boolean regraDefinida) {

		this.regraDefinida = regraDefinida;
	}

	/**
	 * Retorna o valor do atributo <code>copia</code>
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isCopia() {

		return copia;
	}

	/**
	 * Define o valor do atributo <code>copia</code>.
	 * 
	 * @param copia
	 */
	public void setCopia(boolean copia) {

		this.copia = copia;
	}

}
