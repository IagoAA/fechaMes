package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
 * @since 06/04/2015 - 14:52:20
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class Link extends PersistentObject {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 5522371793275513453L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo title. */
	@JsonView({ Views.GenericView.class })
	private String title;

	/** Atributo link. */
	@JsonView({ Views.GenericView.class })
	private String href;

	/** Atributo remover. */
	@Transient
	@JsonView({ Views.PainelEditView.class })
	private Boolean remover;

	/** Atributo tipoWidgetDominio. */
	@ManyToOne(fetch = FetchType.LAZY)
	private PainelItem painelItem;

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
	 * Retorna o valor do atributo <code>title</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getTitle() {

		return title;
	}

	/**
	 * Define o valor do atributo <code>title</code>.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {

		this.title = title;
	}

	/**
	 * Retorna o valor do atributo <code>href</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getHref() {

		return href;
	}

	/**
	 * Define o valor do atributo <code>href</code>.
	 * 
	 * @param href
	 */
	public void setHref(String href) {

		this.href = href;
	}

	/**
	 * Retorna o valor do atributo <code>painelItem</code>
	 * 
	 * @return <code>PainelItem</code>
	 */
	public PainelItem getPainelItem() {

		return painelItem;
	}

	/**
	 * Define o valor do atributo <code>painelItem</code>.
	 * 
	 * @param painelItem
	 */
	public void setPainelItem(PainelItem painelItem) {

		this.painelItem = painelItem;
	}

	/**
	 * Retorna o valor do atributo <code>remover</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getRemover() {

		return remover;
	}

	/**
	 * Define o valor do atributo <code>remover</code>.
	 * 
	 * @param remover
	 */
	public void setRemover(Boolean remover) {

		this.remover = remover;
	}

}
