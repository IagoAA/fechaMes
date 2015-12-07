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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 29/12/2014 - 09:40:57
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
@Entity
public class Comissao extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.ComissaoListView.class })
	private String codigo;

	/** Atributo nome. */
	@Column(length = 100)
	@JsonView({ Views.ComissaoListView.class })
	private String nome;

	/** Atributo numProcesso. */
	@Column(length = 30)
	@JsonView({ Views.ComissaoListView.class })
	private String numProcesso;

	@JsonView({ Views.ComissaoListView.class })
	private String portaria;

	/** Atributo dataFormacao. */
	@JsonView({ Views.ComissaoListView.class })
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@Temporal(TemporalType.DATE)
	private Calendar dataFormacao;

	/** Atributo dataExtincao. */
	@JsonView({ Views.ComissaoListView.class })
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@Temporal(TemporalType.DATE)
	private Calendar dataExtincao;

	/** Atributo integrantes. */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "comissao", cascade = CascadeType.ALL)
	@JsonView({ Views.ComissaoEditView.class })
	private Collection<ComissaoIntegrante> integrantes;

	/** Atributo presidente. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.ComissaoListView.class })
	private Colaborador presidente;

	/** Atributo dominioTipoComissao. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.ComissaoListView.class })
	private Dominio dominioTipoComissao;

	/** Atributo observacoes. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comissao", cascade = CascadeType.ALL)
	@JsonView({ Views.ComissaoListView.class })
	private Collection<ComissaoObservacao> observacoes;

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
	 * Retorna o valor do atributo <code>numProcesso</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNumProcesso() {

		return numProcesso;
	}

	/**
	 * Define o valor do atributo <code>numProcesso</code>.
	 *
	 * @param numProcesso
	 */
	public void setNumProcesso(String numProcesso) {

		this.numProcesso = numProcesso;
	}

	/**
	 * Retorna o valor do atributo <code>portaria</code>
	 *
	 * @return <code>String</code>
	 */
	public String getPortaria() {

		return portaria;
	}

	/**
	 * Define o valor do atributo <code>portaria</code>.
	 *
	 * @param portaria
	 */
	public void setPortaria(String portaria) {

		this.portaria = portaria;
	}

	/**
	 * Retorna o valor do atributo <code>dataFormacao</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataFormacao() {

		return dataFormacao;
	}

	/**
	 * Define o valor do atributo <code>dataFormacao</code>.
	 *
	 * @param dataFormacao
	 */
	public void setDataFormacao(Calendar dataFormacao) {

		this.dataFormacao = dataFormacao;
	}

	/**
	 * Retorna o valor do atributo <code>dataExtincao</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataExtincao() {

		return dataExtincao;
	}

	/**
	 * Define o valor do atributo <code>dataExtincao</code>.
	 *
	 * @param dataExtincao
	 */
	public void setDataExtincao(Calendar dataExtincao) {

		this.dataExtincao = dataExtincao;
	}

	/**
	 * Retorna o valor do atributo <code>integrantes</code>
	 *
	 * @return <code>Collection<InventarioComissaoIntegrante></code>
	 */
	public Collection<ComissaoIntegrante> getIntegrantes() {

		return integrantes;
	}

	/**
	 * Define o valor do atributo <code>integrantes</code>.
	 *
	 * @param integrantes
	 */
	public void setIntegrantes(Collection<ComissaoIntegrante> integrantes) {

		this.integrantes = integrantes;
	}

	/**
	 * Retorna o valor do atributo <code>presidente</code>
	 *
	 * @return <code>Colaborador</code>
	 */
	public Colaborador getPresidente() {

		return presidente;
	}

	/**
	 * Define o valor do atributo <code>presidente</code>.
	 *
	 * @param presidente
	 */
	public void setPresidente(Colaborador presidente) {

		this.presidente = presidente;
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	@Override
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
	 * Retorna o valor do atributo <code>dominioTipoComissao</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoComissao() {

		return dominioTipoComissao;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoComissao</code>.
	 *
	 * @param dominioTipoComissao
	 */
	public void setDominioTipoComissao(Dominio dominioTipoComissao) {

		this.dominioTipoComissao = dominioTipoComissao;
	}

	/**
	 * Retorna o valor do atributo <code>observacoes</code>
	 *
	 * @return <code>Collection<ComissaoObservacao></code>
	 */
	public Collection<ComissaoObservacao> getObservacoes() {

		return observacoes;
	}

	/**
	 * Define o valor do atributo <code>observacoes</code>.
	 *
	 * @param observacoes
	 */
	public void setObservacoes(Collection<ComissaoObservacao> observacoes) {

		this.observacoes = observacoes;
	}

}
