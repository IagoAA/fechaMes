package br.com.centralit.api.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: Classe JPA que mapeia a entidade de estruturaorganizacional no banco de dados</b>
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
 * @since 26/08/2015 - 11:32:35
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
@Entity
@JsonIgnoreProperties({ "$block", "$edit", "$checked", "sinalPositivo", "$readOnly", "$class, $statusInventarioEstrutura" })
@Table(indexes={@Index(columnList="organizacao_id")})
public class EstruturaOrganizacional extends PersistentObjectAuditOrganizacao implements Comparable<EstruturaOrganizacional>, Cloneable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -2066195369488230267L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String nome;

	/** Atributo sigla. */
	@Column(length = 30)
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String sigla;

	/** Atributo classificacao. */
	@Column(length = 100)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class, Views.EstruturaOrganizacionalAutoCompleteSimplesView.class, Views.EstruturaOrganizacionalAutoCompleteView.class, Views.PessoaEditView.class })
	private String classificacao;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private String codigo;

	/** Atributo dataInicio. */
	@Column(name = "dataInicio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Calendar dataInicio;

	/** Atributo dataFim. */
	@Column(name = "dataFim", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Calendar dataFim;

	/** Atributo dominioTipoEstruturaOrganizacional. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Dominio dominioTipoEstruturaOrganizacional;

	/** Atributo estruturaOrganizacionalParent. */
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
	private EstruturaOrganizacional estruturaOrganizacionalParent;

	@JsonView({ Views.EstruturaOrganizacionalEditView.class, Views.EstruturaOrganizacionalSimplesView.class })
	@Transient
	private List<EstruturaOrganizacional> subEstruturasOrganizacionais;

	/** Atributo localizacao. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Localizacao localizacao;

	/** Atributo estruturasOrganizacionalResponsaveis. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estruturaOrganizacional", cascade = CascadeType.ALL)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	@OrderBy("ordem ASC")
	private Collection<EstruturaOrganizacionalResponsavel> estruturasOrganizacionalResponsaveis;

	/** Atributo possuiFilho. */
	@Transient
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Boolean possuiFilho;

	@Transient
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Long idEstruturaOrganizacionalParent;

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
	 * Retorna o valor do atributo <code>idEstruturaOrganizacionalParent</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getIdEstruturaOrganizacionalParent() {

		return idEstruturaOrganizacionalParent;
	}

	/**
	 * Define o valor do atributo <code>idEstruturaOrganizacionalParent</code>.
	 *
	 * @param idEstruturaOrganizacionalParent
	 */
	public void setIdEstruturaOrganizacionalParent(Long idEstruturaOrganizacionalParent) {

		this.idEstruturaOrganizacionalParent = idEstruturaOrganizacionalParent;
	}

	/**
	 * Retorna o valor do atributo <code>possuiFilho</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getPossuiFilho() {

		return possuiFilho;
	}

	/**
	 * Define o valor do atributo <code>possuiFilho</code>.
	 *
	 * @param possuiFilho
	 */
	public void setPossuiFilho(Boolean possuiFilho) {

		this.possuiFilho = possuiFilho;
	}

	/**
	 * Retorna o valor do atributo <code>classificacao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getClassificacao() {

		return classificacao;
	}

	/**
	 * Define o valor do atributo <code>classificacao</code>.
	 *
	 * @param classificacao
	 */
	public void setClassificacao(String classificacao) {

		this.classificacao = classificacao;
	}

	/**
	 * Retorna o valor do atributo <code>codigo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigo() {

		return codigo;
	}

	/**
	 * Define o valor do atributo <code>codigo</code>.
	 *
	 * @param codigo
	 */
	public void setCodigo(String codigo) {

		this.codigo = codigo;
	}

	/**
	 * Retorna o valor do atributo <code>dataFim</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataFim() {

		return dataFim;
	}

	/**
	 * Define o valor do atributo <code>dataFim</code>.
	 *
	 * @param dataFim
	 */
	public void setDataFim(Calendar dataFim) {

		this.dataFim = dataFim;
	}

	/**
	 * Retorna o valor do atributo <code>dataInicio</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataInicio() {

		return dataInicio;
	}

	/**
	 * Define o valor do atributo <code>dataInicio</code>.
	 *
	 * @param dataInicio
	 */
	public void setDataInicio(Calendar dataInicio) {

		this.dataInicio = dataInicio;
	}

	/**
	 * Retorna o valor do atributo <code>dominioTipoEstruturaOrganizacional</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoEstruturaOrganizacional() {

		return dominioTipoEstruturaOrganizacional;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoEstruturaOrganizacional</code>.
	 *
	 * @param dominioTipoEstruturaOrganizacional
	 */
	public void setDominioTipoEstruturaOrganizacional(Dominio dominioTipoEstruturaOrganizacional) {

		this.dominioTipoEstruturaOrganizacional = dominioTipoEstruturaOrganizacional;
	}

	/**
	 * Retorna o valor do atributo <code>estruturaOrganizacionalParent</code>
	 *
	 * @return <code>EstruturaOrganizacional</code>
	 */
	public EstruturaOrganizacional getEstruturaOrganizacionalParent() {

		return estruturaOrganizacionalParent;
	}

	/**
	 * Define o valor do atributo <code>estruturaOrganizacionalParent</code>.
	 *
	 * @param estruturaOrganizacionalParent
	 */
	public void setEstruturaOrganizacionalParent(EstruturaOrganizacional estruturaOrganizacionalParent) {

		this.estruturaOrganizacionalParent = estruturaOrganizacionalParent;
	}

	/**
	 * Retorna o valor do atributo <code>subEstruturasOrganizacionais</code>
	 *
	 * @return <code>List<EstruturaOrganizacional></code>
	 */
	public List<EstruturaOrganizacional> getSubEstruturasOrganizacionais() {

		return subEstruturasOrganizacionais;
	}

	/**
	 * Define o valor do atributo <code>subEstruturasOrganizacionais</code>.
	 *
	 * @param subEstruturasOrganizacionais
	 */
	public void setSubEstruturasOrganizacionais(List<EstruturaOrganizacional> subEstruturasOrganizacionais) {

		this.subEstruturasOrganizacionais = subEstruturasOrganizacionais;
	}

	/**
	 * Retorna o valor do atributo <code>localizacao</code>
	 *
	 * @return <code>Localizacao</code>
	 */
	public Localizacao getLocalizacao() {

		return localizacao;
	}

	/**
	 * Define o valor do atributo <code>localizacao</code>.
	 *
	 * @param localizacao
	 */
	public void setLocalizacao(Localizacao localizacao) {

		this.localizacao = localizacao;
	}

	/**
	 * Retorna o valor do atributo <code>estruturasOrganizacionalResponsaveis</code>
	 *
	 * @return <code>Collection<EstruturaOrganizacionalResponsavel></code>
	 */
	public Collection<EstruturaOrganizacionalResponsavel> getEstruturasOrganizacionalResponsaveis() {
		return this.estruturasOrganizacionalResponsaveis;
	}

	/**
	 * Define o valor do atributo <code>estruturasOrganizacionalResponsaveis</code>.
	 *
	 * @param estruturasOrganizacionalResponsaveis
	 */
	public void setEstruturasOrganizacionalResponsaveis(Collection<EstruturaOrganizacionalResponsavel> estruturasOrganizacionalResponsaveis) {
		this.estruturasOrganizacionalResponsaveis = estruturasOrganizacionalResponsaveis;
	}

	@Override
	public int compareTo(EstruturaOrganizacional o) {

		return this.getId().compareTo(o.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	public EstruturaOrganizacional clone() {

		try {

			return (EstruturaOrganizacional) super.clone();

		} catch (CloneNotSupportedException ex) {

			return null;
		}
	}

	public EstruturaOrganizacionalResponsavel getEstruturaOrganizacionalResponsavel() {
		for (Iterator<EstruturaOrganizacionalResponsavel> iterator = this.estruturasOrganizacionalResponsaveis.iterator(); iterator.hasNext();) {
			EstruturaOrganizacionalResponsavel responsavel = (EstruturaOrganizacionalResponsavel) iterator.next();
			if (responsavel.getOrdem().equals(EstruturaOrganizacionalResponsavel.RESPONSAVEL_PRINCIPAL_ORDEM)) {
				return responsavel;
			}
		}
		return null;
	}
}
