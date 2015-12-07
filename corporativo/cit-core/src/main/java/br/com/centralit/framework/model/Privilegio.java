package br.com.centralit.framework.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "seguranca_privilegio")
public class Privilegio extends PersistentObjectAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	@JsonView({ Views.GrupoEditView.class, Views.UsuarioEditView.class, Views.LookupView.class, Views.PainelEditView.class, Views.MenuEditView.class })
	@Column(length = 20)
	private String nome;

	@Column(length = 64)
	@JsonView({ Views.UsuarioEditView.class, Views.LookupView.class })
	private String descricao;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "privilegio")
	private Set<UsuarioPrivilegio> privilegios = new HashSet<UsuarioPrivilegio>();

	public Privilegio() {

	}

	public Privilegio( final String nome ) {

		this.nome = nome;
	}
	
	

	public Privilegio( String nome, String descricao ) {

		super();
		this.nome = nome;
		this.descricao = descricao;
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

	public String getNome() {

		return this.nome;
	}

	public String getDescription() {

		return this.descricao;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public void setDescription(String description) {

		this.descricao = description;
	}

	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (!( o instanceof Privilegio )) {
			return false;
		}

		final Privilegio privilegio = (Privilegio) o;

		return !( nome != null ? !nome.equals(privilegio.nome) : privilegio.nome != null );

	}

	public int hashCode() {
		return (nome != null ? nome.hashCode() : 0);
	}

	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(this.nome).toString();
	}

	private static final long serialVersionUID = 3690197650654049848L;
}
