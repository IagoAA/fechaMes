package br.com.centralit.framework.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;
import br.com.centralit.framework.util.UtilObjeto;

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
 * <b>Title: Menu</b>
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
 * @since 09/12/2014 - 17:38:42
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
@JsonIgnoreProperties({ "$type", "submenuList" })
public class Menu extends PersistentObjectAudit implements Cloneable {

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.BreadcrumbView.class })
	protected Long id;

	/** Atributo nome. */
	@JsonView({ Views.MenuListView.class, Views.MenuListChildrenView.class, Views.BreadcrumbView.class })
	private String nome;

	/** Atributo nome. */
	@JsonView({ Views.MenuListView.class })
	private String icone;

	/** Atributo pagina. */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonView({ Views.MenuEditView.class, Views.MenuListFrontEndView.class, Views.MenuListSearchFrontEndView.class })
	private Pagina pagina;

	/** Atributo parent. */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonView({ Views.BreadcrumbView.class })
	private Menu parent;

	/** Atributo submenu. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
	@OrderBy("ordem ASC")
	@JsonView({ Views.MenuListChildrenView.class, Views.MenuListFrontEndView.class })
	private List<Menu> submenu;

	/** Atributo ativo. */
	@JsonView({ Views.MenuListView.class, Views.MenuListChildrenView.class })
	private Boolean ativo = true;

	/** Atributo ordem. */
	@JsonView({ Views.MenuListView.class, Views.MenuListChildrenView.class })
	private Integer ordem = 0;

	/** Atributo coluna. */
	@JsonView({ Views.MenuListView.class, Views.MenuListChildrenView.class })
	private Integer coluna = 0;

	/** Atributo cor. */
	@JsonView({ Views.MenuListView.class, Views.MenuListChildrenView.class })
	private String cor;

	/** Atributo cssMenu. */
	// TODO SQLSERVERONLY - Encontrar alguma solução de texto longo que funcione no SQLServer e nos outros bancos, pois o tipo 'text' da problema, porque não pode ser comparado
	@Column(length = 3000)
	private String cssMenu;

	/** Atributo cssMenuOpacity. */
	@JsonView({ Views.MenuEditView.class })
	private Double cssMenuOpacity;

	/** Atributo classePagina. */
	@JsonView({ Views.MenuListFrontEndView.class, Views.MenuListSearchFrontEndView.class, Views.MenuEditView.class, Views.BreadcrumbView.class })
	private String classePagina;

	/** Atributo permissaoDeAcessoDefinida. */
	private Boolean permissaoDeAcessoDefinida;

	/** Atributo includes. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = CascadeType.ALL)
	private List<MenuFile> includes;

	/** Atributo menuGrupos. */
	@JsonView({ Views.MenuEditView.class })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menu")
	private Collection<MenuGrupo> menuGrupos;

	/** Atributo menuPrivilegios. */
	@JsonView({ Views.MenuEditView.class })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menu")
	private Collection<MenuPrivilegio> menuPrivilegios;

	/** Atributo modulo. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Modulo modulo;

	/** Atributo chave.*/
	@JsonView({ Views.MenuEditView.class })
	private String chave;

	private static final long serialVersionUID = 1L;

	public Menu() {

		super();
	}

	public Menu( String nome, Pagina pagina, Integer ordem, Modulo modulo ) {

		super();
		this.nome = nome;
		this.pagina = pagina;
		this.ativo = Boolean.TRUE;
		this.ordem = ordem;
		this.modulo = modulo;
	}

	public Menu( String nome, Pagina pagina, Integer ordem ) {

		super();
		this.nome = nome;
		this.pagina = pagina;
		this.ativo = Boolean.TRUE;
		this.ordem = ordem;
	}
	
	public Menu( String nome, Pagina pagina, Menu parent, Integer coluna, Integer ordem, String cor, String cssMenu, Double cssMenuOpacity, String classePagina, Modulo modulo, String chave) {

		this(nome, pagina, ordem, modulo);
		this.coluna = coluna;
		this.parent = parent;
		this.cor = cor;
		this.cssMenu = cssMenu;
		this.cssMenuOpacity = cssMenuOpacity;
		this.classePagina = classePagina;
		this.modulo = modulo;
		this.chave = chave;
	}

	public Menu( String nome, Pagina pagina, Menu parent, Integer coluna, Integer ordem, String cor, String cssMenu, Double cssMenuOpacity, String classePagina, Modulo modulo ) {

		this(nome, pagina, ordem, modulo);
		this.coluna = coluna;
		this.parent = parent;
		this.cor = cor;
		this.cssMenu = cssMenu;
		this.cssMenuOpacity = cssMenuOpacity;
		this.classePagina = classePagina;
		this.modulo = modulo;
	}

	public Menu( String nome, Pagina pagina, Menu parent, Integer coluna, Integer ordem, String cor, String cssMenu, Double cssMenuOpacity, String classePagina ) {

		this(nome, pagina, ordem);
		this.coluna = coluna;
		this.parent = parent;
		this.cor = cor;
		this.cssMenu = cssMenu;
		this.cssMenuOpacity = cssMenuOpacity;
		this.classePagina = classePagina;
	}
	
	public Menu( String nome, Pagina pagina, Menu parent, Integer coluna, Integer ordem, String cor, String cssMenu, Double cssMenuOpacity, String classePagina, String icone, Modulo modulo, String chave ) {

		this(nome, pagina, ordem, modulo);
		this.coluna = coluna;
		this.parent = parent;
		this.cor = cor;
		this.cssMenu = cssMenu;
		this.cssMenuOpacity = cssMenuOpacity;
		this.classePagina = classePagina;
		this.icone = icone;
		this.modulo = modulo;
		this.chave = chave;
	}

	public Menu( String nome, Pagina pagina, Menu parent, Integer coluna, Integer ordem, String cor, String cssMenu, Double cssMenuOpacity, String classePagina, String icone, Modulo modulo ) {

		this(nome, pagina, ordem, modulo);
		this.coluna = coluna;
		this.parent = parent;
		this.cor = cor;
		this.cssMenu = cssMenu;
		this.cssMenuOpacity = cssMenuOpacity;
		this.classePagina = classePagina;
		this.icone = icone;
		this.modulo = modulo;
	}

	public Menu( String nome, Pagina pagina, Menu parent, Integer coluna, Integer ordem, String cor, String cssMenu, Double cssMenuOpacity, String classePagina, String icone ) {

		this(nome, pagina, ordem);
		this.coluna = coluna;
		this.parent = parent;
		this.cor = cor;
		this.cssMenu = cssMenu;
		this.cssMenuOpacity = cssMenuOpacity;
		this.classePagina = classePagina;
		this.icone = icone;
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
	 * Retorna o valor do atributo <code>icone</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getIcone() {

		return icone;
	}

	/**
	 * Define o valor do atributo <code>icone</code>.
	 * 
	 * @param icone
	 */
	public void setIcone(String icone) {

		this.icone = icone;
	}

	/**
	 * Retorna o valor do atributo <code>pagina</code>
	 * 
	 * @return <code>Pagina</code>
	 */
	public Pagina getPagina() {

		return pagina;
	}

	/**
	 * Define o valor do atributo <code>pagina</code>.
	 * 
	 * @param pagina
	 */
	public void setPagina(Pagina pagina) {

		this.pagina = pagina;
	}

	/**
	 * Retorna o valor do atributo <code>parent</code>
	 * 
	 * @return <code>Menu</code>
	 */
	public Menu getParent() {

		return parent;
	}

	/**
	 * Define o valor do atributo <code>parent</code>.
	 * 
	 * @param parent
	 */
	public void setParent(Menu parent) {

		this.parent = parent;
	}

	/**
	 * Retorna o valor do atributo <code>submenu</code>
	 * 
	 * @return <code>List<Menu></code>
	 */
	public List<Menu> getSubmenu() {

		List<Menu> submenus = new ArrayList<Menu>();

		if (submenu != null && !submenu.isEmpty()) {
			for (Menu menu : submenu) {
				if (menu.getDataInativo() == null) {
					submenus.add(menu);
				}
			}
		}

		return submenus;
	}

	/**
	 * Define o valor do atributo <code>submenu</code>.
	 * 
	 * @param submenu
	 */
	public void setSubmenu(List<Menu> submenu) {

		this.submenu = submenu;
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
	 * Retorna o valor do atributo <code>coluna</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getColuna() {

		return coluna;
	}

	/**
	 * Define o valor do atributo <code>coluna</code>.
	 * 
	 * @param coluna
	 */
	public void setColuna(Integer coluna) {

		this.coluna = coluna;
	}

	/**
	 * Retorna o valor do atributo <code>cor</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCor() {

		return cor;
	}

	/**
	 * Define o valor do atributo <code>cor</code>.
	 * 
	 * @param cor
	 */
	public void setCor(String cor) {

		this.cor = cor;
	}

	/**
	 * Retorna o valor do atributo <code>cssMenu</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCssMenu() {

		return cssMenu;
	}

	/**
	 * Define o valor do atributo <code>cssMenu</code>.
	 * 
	 * @param cssMenu
	 */
	public void setCssMenu(String cssMenu) {

		this.cssMenu = cssMenu;
	}

	/**
	 * Retorna o valor do atributo <code>cssMenuOpacity</code>
	 * 
	 * @return <code>Double</code>
	 */
	public Double getCssMenuOpacity() {

		return cssMenuOpacity;
	}

	/**
	 * Define o valor do atributo <code>cssMenuOpacity</code>.
	 * 
	 * @param cssMenuOpacity
	 */
	public void setCssMenuOpacity(Double cssMenuOpacity) {

		this.cssMenuOpacity = cssMenuOpacity;
	}

	/**
	 * Retorna o valor do atributo <code>classePagina</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getClassePagina() {

		return classePagina;
	}

	/**
	 * Define o valor do atributo <code>classePagina</code>.
	 * 
	 * @param classePagina
	 */
	public void setClassePagina(String classePagina) {

		this.classePagina = classePagina;
	}

	/**
	 * Retorna o valor do atributo <code>menuGrupos</code>
	 * 
	 * @return <code>Collection<MenuGrupo></code>
	 */
	public Collection<MenuGrupo> getMenuGrupos() {

		return menuGrupos;
	}

	/**
	 * Define o valor do atributo <code>menuGrupos</code>.
	 * 
	 * @param menuGrupos
	 */
	public void setMenuGrupos(Collection<MenuGrupo> menuGrupos) {

		this.menuGrupos = menuGrupos;
	}

	/**
	 * Retorna o valor do atributo <code>menuPrivilegios</code>
	 * 
	 * @return <code>Collection<MenuPrivilegio></code>
	 */
	public Collection<MenuPrivilegio> getMenuPrivilegios() {

		return menuPrivilegios;
	}

	/**
	 * Define o valor do atributo <code>menuPrivilegios</code>.
	 * 
	 * @param menuPrivilegios
	 */
	public void setMenuPrivilegios(Collection<MenuPrivilegio> menuPrivilegios) {

		this.menuPrivilegios = menuPrivilegios;
	}

	/**
	 * Retorna o valor do atributo <code>permissaoDeAcessoDefinida</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getPermissaoDeAcessoDefinida() {

		return permissaoDeAcessoDefinida;
	}

	/**
	 * Define o valor do atributo <code>permissaoDeAcessoDefinida</code>.
	 * 
	 * @param permissaoDeAcessoDefinida
	 */
	public void setPermissaoDeAcessoDefinida(Boolean permissaoDeAcessoDefinida) {

		this.permissaoDeAcessoDefinida = permissaoDeAcessoDefinida;
	}

	/**
	 * Retorna o valor do atributo <code>includes</code>
	 * 
	 * @return <code>List<MenuFile></code>
	 */
	public List<MenuFile> getIncludes() {

		List<MenuFile> includesMenu = new ArrayList<MenuFile>();

		if (UtilObjeto.isReferencia(this.includes) && !this.includes.isEmpty()) {
			for (MenuFile include : this.includes) {
				if (include.getDataInativo() == null) {
					includesMenu.add(include);
				}
			}
		}

		return includesMenu;
	}

	/**
	 * Define o valor do atributo <code>includes</code>.
	 * 
	 * @param includes
	 */
	public void setIncludes(List<MenuFile> includes) {

		this.includes = includes;
	}

	/**
	 * Retorna o valor do atributo <code>modulo</code>
	 * 
	 * @return <code>Modulo</code>
	 */
	public Modulo getModulo() {

		return modulo;
	}

	/**
	 * Define o valor do atributo <code>modulo</code>.
	 * 
	 * @param modulo
	 */
	public void setModulo(Modulo modulo) {

		this.modulo = modulo;
	}

	/**
	 * Retorna o valor do atributo <code>chave</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getChave() {

		return chave;
	}

	/**
	 * Define o valor do atributo <code>chave</code>.
	 * 
	 * @param chave
	 */
	public void setChave(String chave) {

		this.chave = chave;
	}

	@Override
	public Menu clone() {

		try {

			return (Menu) super.clone();

		} catch (CloneNotSupportedException ex) {

			return null;
		}
	}
}
