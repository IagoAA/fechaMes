package br.com.centralit.framework.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@JsonIgnoreProperties({ "cor", "classePagina" })
public class Pagina extends PersistentObject {

	private static final long serialVersionUID = -5503087876248538699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	protected Long id;

	@Column
	@JsonView({ Views.UsuarioEditView.class,  Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String nome;

	@Column(unique = true)
	@JsonView({ Views.UsuarioEditView.class,  Views.GenericView.class, Views.UsuarioLogadoView.class })
	private String pagina;

	@Basic(fetch = FetchType.LAZY)
	@Type(type="org.hibernate.type.StringClobType")
	@JsonView({ Views.MenuEditView.class, Views.PaginaAjudaView.class })
	private String ajuda;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "pagina")
	private Menu menu;

	@Transient
	@JsonView({ Views.MenuEditView.class })
	private Boolean possuiAjuda;


	public Pagina() {

		super();
	}

	public Pagina( String nome, String pagina ) {

		this();
		this.nome = nome;
		this.pagina = pagina;
	}

	/**
	 * Retorna o valor do atributo <code>menu.classePagina</code>
	 *
	 * @return <code>String</code>
	 */
	@JsonView({ Views.GenericView.class })
	public String getClassePagina() {

		return UtilObjeto.isReferencia(this.getMenu()) ? this.getMenu().getClassePagina() : "";
	}

	/**
	 * Retorna o valor do atributo <code>menu.cor</code>
	 *
	 * @return <code>String</code>
	 */
	@JsonView({ Views.GenericView.class })
	public String getCor() {

		return UtilObjeto.isReferencia(this.getMenu()) ? this.getMenu().getCor() : "";
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param nome
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
	 * Retorna o valor do atributo <code>pagina</code>
	 *
	 * @return <code>String</code>
	 */
	public String getPagina() {

		return pagina;
	}

	/**
	 * Define o valor do atributo <code>pagina</code>.
	 *
	 * @param pagina
	 */
	public void setPagina(String pagina) {

		this.pagina = pagina;
	}

	/**
	 * Retorna o valor do atributo <code>ajuda</code>
	 *
	 * @return <code>String</code>
	 */
	public String getAjuda() {

		return ajuda;
	}

	/**
	 * Define o valor do atributo <code>ajuda</code>.
	 *
	 * @param ajuda
	 */
	public void setAjuda(String ajuda) {

		this.ajuda = ajuda;
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

	public Boolean getPossuiAjuda() {
		return (ajuda != null && !ajuda.equals(""));
	}

	public void setPossuiAjuda(Boolean possuiAjuda) {
		this.possuiAjuda = possuiAjuda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
