package br.com.centralit.api.model;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
 * @since 05/12/2014 - 17:28:12
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"$checked", "$index", "$uuid"})
public class Documento extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 8235875348051060421L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo dominioTipoDocumento. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.ContratoEditView.class })
	private Dominio dominioTipoDocumento;

	/** Atributo emitente. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.ContratoEditView.class })
	private Parceiro emitente;

	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@JsonView({ Views.ContratoEditView.class })
	private Calendar dataEmissao;

	/** Atributo numero. */
	@JsonView({ Views.ContratoEditView.class })
	private String numero;

	/** Atributo anexos. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documento", cascade = CascadeType.ALL)
	@JsonView({ Views.ContratoEditView.class })
	private Collection<Anexo> anexos;

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
	 * Retorna o valor do atributo <code>dominioTipoDocumento</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoDocumento() {

		return dominioTipoDocumento;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoDocumento</code>.
	 *
	 * @param dominioTipoDocumento
	 */
	public void setDominioTipoDocumento(Dominio dominioTipoDocumento) {

		this.dominioTipoDocumento = dominioTipoDocumento;
	}

	/**
	 * Retorna o valor do atributo <code>emitente</code>
	 *
	 * @return <code>Parceiro</code>
	 */
	public Parceiro getEmitente() {

		return emitente;
	}

	/**
	 * Define o valor do atributo <code>emitente</code>.
	 *
	 * @param emitente
	 */
	public void setEmitente(Parceiro emitente) {

		this.emitente = emitente;
	}

	/**
	 * Retorna o valor do atributo <code>numero</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNumero() {

		return numero;
	}

	/**
	 * Define o valor do atributo <code>numero</code>.
	 *
	 * @param numero
	 */
	public void setNumero(String numero) {

		this.numero = numero;
	}

	/**
	 * Retorna o valor do atributo <code>anexos</code>
	 *
	 * @return <code>Collection<Anexo></code>
	 */
	public Collection<Anexo> getAnexos() {

		return anexos;
	}

	/**
	 * Define o valor do atributo <code>anexos</code>.
	 *
	 * @param anexos
	 */
	public void setAnexos(Collection<Anexo> anexos) {

		this.anexos = anexos;
	}

	/**
	 * Retorna o valor do atributo <code>dataEmissao</code>
	 *
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataEmissao() {

		return dataEmissao;
	}

	/**
	 * Define o valor do atributo <code>dataEmissao</code>.
	 *
	 * @param dataEmissao
	 */
	public void setDataEmissao(Calendar dataEmissao) {

		this.dataEmissao = dataEmissao;
	}

}
