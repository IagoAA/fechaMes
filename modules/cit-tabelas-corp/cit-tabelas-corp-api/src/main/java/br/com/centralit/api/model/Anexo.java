package br.com.centralit.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

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
 * @since 05/12/2014 - 10:59:04
 *
 * @version 1.0.0
 *
 * @author ally.barra
 *
 */
@Entity
public class Anexo extends PersistentObject {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -3972425393785600397L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo anexo. */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] anexo;

	/** Atributo descricao. */
	@Column(length = 300)
	private String descricao;

	/** Atributo tamanho. */
	@JsonView({ Views.ContratoEditView.class })
	private Long tamanho;

	/** Atributo dominioTipoAnexo. */
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JsonView({ Views.ContratoEditView.class })
	private Dominio dominioTipoAnexo;

	/** Atributo documento. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Documento documento;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Documento documentoInativo;

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
	 * Retorna o valor do atributo <code>anexo</code>
	 *
	 * @return <code>byte[]</code>
	 */
	public byte[] getAnexo() {

		return (byte[]) anexo.clone();
	}

	/**
	 * Define o valor do atributo <code>anexo</code>.
	 *
	 * @param anexo
	 */
	public void setAnexo(byte[] anexo) {

		this.anexo = (byte[]) anexo.clone();
	}

	/**
	 * Retorna o valor do atributo <code>descricao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Define o valor do atributo <code>descricao</code>.
	 *
	 * @param descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Retorna o valor do atributo <code>documento</code>
	 *
	 * @return <code>Documento</code>
	 */
	public Documento getDocumento() {

		return documento;
	}

	/**
	 * Define o valor do atributo <code>documento</code>.
	 *
	 * @param documento
	 */
	public void setDocumento(Documento documento) {

		this.documento = documento;
	}

	/**
	 * Retorna o valor do atributo <code>dominioTipoAnexo</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoAnexo() {

		return dominioTipoAnexo;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoAnexo</code>.
	 *
	 * @param dominioTipoAnexo
	 */
	public void setDominioTipoAnexo(Dominio dominioTipoAnexo) {

		this.dominioTipoAnexo = dominioTipoAnexo;
	}

	/**
	 * Retorna o valor do atributo <code>documentoInativo</code>
	 *
	 * @return <code>Documento</code>
	 */
	public Documento getDocumentoInativo() {

		return documentoInativo;
	}

	/**
	 * Define o valor do atributo <code>documentoInativo</code>.
	 *
	 * @param documentoInativo
	 */
	public void setDocumentoInativo(Documento documentoInativo) {

		this.documentoInativo = documentoInativo;
	}

	/**
	 * Retorna o valor do atributo <code>tamanho</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getTamanho() {

		return tamanho;
	}

	/**
	 * Define o valor do atributo <code>tamanho</code>.
	 *
	 * @param tamanho
	 */
	public void setTamanho(Long tamanho) {

		this.tamanho = tamanho;
	}

}
