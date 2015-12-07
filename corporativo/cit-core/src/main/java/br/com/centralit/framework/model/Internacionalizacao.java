package br.com.centralit.framework.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"chave", "tipodominioidioma_id"}))
public class Internacionalizacao extends PersistentObject {

	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	@JsonView({ Views.InternacionalizacaoListView.class })
	private String chave;

	@JsonView({ Views.InternacionalizacaoListView.class })
	@Column(length = 3000)
	private String valor;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.InternacionalizacaoListView.class })
	private Dominio tipoDominioIdioma;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JsonView({ Views.InternacionalizacaoListView.class })
	private Modulo modulo;

	@Transient
	private Map<String, String> jsonPortal;

	@Transient
	private Internacionalizacao jsonParent;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Internacionalizacao() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param chave
	 * @param valor
	 */
	public Internacionalizacao( String chave, String valor ) {

		super();
		this.chave = chave;
		this.valor = valor;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param chave
	 * @param valor
	 * @param tipoDominioIdioma
	 */
	public Internacionalizacao( String chave, String valor, Dominio tipoDominioIdioma ) {

		this(chave, valor);
		this.tipoDominioIdioma = tipoDominioIdioma;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param chave
	 * @param valor
	 * @param tipoDominioIdioma
	 * @param modulo
	 */
	public Internacionalizacao( String chave, String valor, Dominio tipoDominioIdioma, Modulo modulo ) {

		this(chave, valor, tipoDominioIdioma);
		this.modulo = modulo;
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

	/**
	 * Retorna o valor do atributo <code>valor</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getValor() {

		return valor;
	}

	/**
	 * Define o valor do atributo <code>valor</code>.
	 * 
	 * @param valor
	 */
	public void setValor(String valor) {

		this.valor = valor;
	}

	/**
	 * Retorna o valor do atributo <code>tipoDominioIdioma</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getTipoDominioIdioma() {

		return tipoDominioIdioma;
	}

	/**
	 * Define o valor do atributo <code>tipoDominioIdioma</code>.
	 * 
	 * @param tipoDominioIdioma
	 */
	public void setTipoDominioIdioma(Dominio tipoDominioIdioma) {

		this.tipoDominioIdioma = tipoDominioIdioma;
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
	 * Retorna o valor do atributo <code>jsonPortal</code>
	 * 
	 * @return <code>Map<String,String></code>
	 */
	public Map<String, String> getJsonPortal() {

		return jsonPortal;
	}

	/**
	 * Define o valor do atributo <code>jsonPortal</code>.
	 * 
	 * @param jsonPortal
	 */
	public void setJsonPortal(Map<String, String> jsonPortal) {

		this.jsonPortal = jsonPortal;
	}

	/**
	 * Retorna o valor do atributo <code>jsonParent</code>
	 * 
	 * @return <code>Internacionalizacao</code>
	 */
	public Internacionalizacao getJsonParent() {

		return jsonParent;
	}

	/**
	 * Define o valor do atributo <code>jsonParent</code>.
	 * 
	 * @param jsonParent
	 */
	public void setJsonParent(Internacionalizacao jsonParent) {

		this.jsonParent = jsonParent;
	}

	@Override
	public String toString() {

		return "Internacionalizacao [id=" + id + ", chave=" + chave + ", valor=" + valor + "]";
	}

}
