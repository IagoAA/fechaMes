package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
 * <b>Title: MenuFile</b>
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
 * @since 09/12/2014 - 17:41:28
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
public class MenuFile extends PersistentObjectAudit {

	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo caminho. */
	@JsonView({ Views.MenuFileListView.class })
	private String caminho;

	/** Atributo dominioMenuFile. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.MenuFileListView.class })
	private Dominio dominioMenuFile;

	/** Atributo menu. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonView({ Views.MenuFileEditView.class })
	private Menu menu;

	/** Atributo ativo. */
	@JsonView({ Views.MenuFileListView.class })
	private Boolean ativo = true;

	public MenuFile() {

		super();
	}

	public MenuFile( String caminho, Dominio dominioMenuFile, Menu menu ) {

		super();
		this.caminho = caminho;
		this.dominioMenuFile = dominioMenuFile;
		this.menu = menu;
		this.ativo = Boolean.TRUE;
	}

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
	 * Retorna o valor do atributo <code>caminho</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCaminho() {

		return caminho;
	}

	/**
	 * Define o valor do atributo <code>caminho</code>.
	 * 
	 * @param caminho
	 */
	public void setCaminho(String caminho) {

		this.caminho = caminho;
	}

	/**
	 * Retorna o valor do atributo <code>dominioMenuFile</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioMenuFile() {

		return dominioMenuFile;
	}

	/**
	 * Define o valor do atributo <code>dominioMenuFile</code>.
	 * 
	 * @param dominioMenuFile
	 */
	public void setDominioMenuFile(Dominio dominioMenuFile) {

		this.dominioMenuFile = dominioMenuFile;
	}

	/**
	 * Retorna o valor do atributo <code>menu</code>
	 * 
	 * @return <code>Menu</code>
	 */
	public Menu getMenu() {

		return menu;
	}

	/**
	 * Define o valor do atributo <code>menu</code>.
	 * 
	 * @param menu
	 */
	public void setMenu(Menu menu) {

		this.menu = menu;
	}

	/**
	 * Retorna o valor do atributo <code>ativo</code>
	 * 
	 * @return <code>String</code>
	 */
	public Boolean getAtivo() {

		return ativo;
	}

	/**
	 * Define o valor do atributo <code>ativo</code>.
	 * 
	 * @param ativo
	 */
	public void setAtivo(Boolean ativo) {

		this.ativo = ativo;
	}

}
