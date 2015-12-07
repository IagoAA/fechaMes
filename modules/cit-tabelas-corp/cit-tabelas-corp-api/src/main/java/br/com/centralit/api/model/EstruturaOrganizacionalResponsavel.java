package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * <b>Title: EstruturaOrganizacionalResponsavel</b>
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
 * @since 04/12/2014 - 15:14:11
 * 
 * @version 1.0.0
 * 
 * @author ally.barra
 * 
 */

@Entity
@JsonIgnoreProperties({ "$block", "$edit", "$checked", "$index" })
public class EstruturaOrganizacionalResponsavel extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	public static final Integer RESPONSAVEL_PRINCIPAL_ORDEM = 1;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Long id;

	@Column(nullable = false)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Integer ordem;

	/** Atributo responsavel. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.EstruturaOrganizacionalEditView.class })
	private Colaborador responsavel;

	/** Atributo pessoa. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private EstruturaOrganizacional estruturaOrganizacional;
	
	/** Atributo pessoa. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private EstruturaOrganizacional estruturaOrganizacionalInativo;

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
	 * Retorna o valor do atributo <code>ordem</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getOrdem() {

		return ordem;
	}

	/**
	 * Define o valor do atributo <code>ordem</code>.
	 * 
	 * @param ordem
	 */
	public void setOrdem(Integer ordem) {

		this.ordem = ordem;
	}

	/**
	 * Retorna o valor do atributo <code>responsavel</code>
	 * 
	 * @return <code>Colaborador</code>
	 */
	public Colaborador getResponsavel() {

		return responsavel;
	}

	/**
	 * Define o valor do atributo <code>responsavel</code>.
	 * 
	 * @param responsavel
	 */
	public void setResponsavel(Colaborador responsavel) {

		this.responsavel = responsavel;
	}

	/**
	 * Retorna o valor do atributo <code>estruturaOrganizacional</code>
	 * 
	 * @return <code>EstruturaOrganizacional</code>
	 */
	public EstruturaOrganizacional getEstruturaOrganizacional() {

		return estruturaOrganizacional;
	}

	/**
	 * Define o valor do atributo <code>estruturaOrganizacional</code>.
	 * 
	 * @param estruturaOrganizacional
	 */
	public void setEstruturaOrganizacional(EstruturaOrganizacional estruturaOrganizacional) {

		this.estruturaOrganizacional = estruturaOrganizacional;
	}

	/**
	 * Retorna o valor do atributo <code>estruturaOrganizacionalInativo</code>
	 * 
	 * @return <code>EstruturaOrganizacional</code>
	 */
	public EstruturaOrganizacional getEstruturaOrganizacionalInativo() {
		return estruturaOrganizacionalInativo;
	}

	/**
	 * Define o valor do atributo <code>estruturaOrganizacionalInativo</code>.
	 * 
	 * @param estruturaOrganizacionalInativo
	 */
	public void setEstruturaOrganizacionalInativo(EstruturaOrganizacional estruturaOrganizacionalInativo) {
		this.estruturaOrganizacionalInativo = estruturaOrganizacionalInativo;
	}

}
