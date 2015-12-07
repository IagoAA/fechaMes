package br.com.centralit.framework.model;

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
 * <b>Title: </b> AnexoImagem
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
 * @since 17/04/2015 - 10:59:04
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Entity
public class AnexoImagem extends PersistentObject {

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
	@JsonView({ Views.ConfiguracaoView.class })
	private byte[] anexo;

	/** Atributo descricao. */
	@Column(length = 300)
	@JsonView({ Views.ConfiguracaoView.class })
	private String descricao;

	/** Atributo idOrganizacao. */
	private Long idOrganizacao;

	@ManyToOne(fetch = FetchType.LAZY)
	private Configuracao configuracao;

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
	 * Retorna o valor do atributo <code>configuracao</code>
	 * 
	 * @return <code>Configuracao</code>
	 */
	public Configuracao getConfiguracao() {

		return configuracao;
	}

	/**
	 * Define o valor do atributo <code>configuracao</code>.
	 * 
	 * @param configuracao
	 */
	public void setConfiguracao(Configuracao configuracao) {

		this.configuracao = configuracao;
	}

	/**
	 * Retorna o valor do atributo <code>idOrganizacao</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getIdOrganizacao() {

		return idOrganizacao;
	}

	/**
	 * Define o valor do atributo <code>idOrganizacao</code>.
	 * 
	 * @param idOrganizacao
	 */
	public void setIdOrganizacao(Long idOrganizacao) {

		this.idOrganizacao = idOrganizacao;
	}

}
