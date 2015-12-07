package br.com.centralit.api.model;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Organizacao;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

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
 * <b>Title: Mapa Organizacional</b>
 * </p>
 * 
 * <p>
 * <b>Description: Responsavel por manter o mapa organizacional da organizacao, mantem a estrutura organizacional de determinada gestao.</b>
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
 * @since 04/12/2014 - 15:05:03
 * 
 * @version 1.0.0
 * 
 * @author ally.barra
 * 
 */

@Entity
public class MapaOrganizacional extends PersistentObjectAuditOrganizacao {

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.MapaOrganizacionalEditView.class })
	private String codigo;

	/** Atributo dataFim. */
	@Column(name = "dataFim", nullable = true)
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.MapaOrganizacionalListView.class })
	private Calendar dataFim;

	/** Atributo dataInicio. */
	@Column(name = "dataInicio", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.MapaOrganizacionalListView.class })
	private Calendar dataInicio;

	/** Atributo nome. */
	@Column(length = 100)
	@JsonView({ Views.MapaOrganizacionalListView.class })
	private String nome;

	/** Atributo observacoes. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mapaOrganizacional", cascade = CascadeType.ALL)
	@JsonView({ Views.MapaOrganizacionalListView.class })
	@OrderBy("dataCriacao ASC")
	private Collection<MapaOrganizacionalObservacao> observacoes;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public MapaOrganizacional() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param dataInicio
	 * @param nome
	 * @param organizacao
	 */
	public MapaOrganizacional( Calendar dataInicio, String nome, Organizacao organizacao ) {

		this();
		this.dataInicio = dataInicio;
		this.nome = nome;
		this.organizacao = organizacao;
	}

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
	 * Retorna o valor do atributo <code>observacoes</code>
	 * 
	 * @return <code>Collection<MapaOrganizacionalObservacao></code>
	 */
	public Collection<MapaOrganizacionalObservacao> getObservacoes() {

		return observacoes;
	}

	/**
	 * Define o valor do atributo <code>observacoes</code>.
	 * 
	 * @param observacoes
	 */
	public void setObservacoes(Collection<MapaOrganizacionalObservacao> observacoes) {

		this.observacoes = observacoes;
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;
}
