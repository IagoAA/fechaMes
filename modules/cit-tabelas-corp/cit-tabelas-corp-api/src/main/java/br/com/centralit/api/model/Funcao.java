package br.com.centralit.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Funcao extends PersistentObjectAuditOrganizacao {

    /** Atributo serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ Views.GenericView.class })
    private Long id;

	/** Atributo nome. */
    @Column(length= 80)
	@JsonView({ Views.GenericView.class, Views.EstruturaOrganizacionalEditView.class })
	private String nome;

    @Column(length = 30)
    @JsonView({ Views.GenericView.class })
    private String codigo;

     /**
     * Retorna o valor do atributo <code>codigo</code>
     *
     * @return <code>Integer</code>
     */
    public String getCodigo() {
       return this.codigo;
    }
    /**
     * Define o valor do atributo <code>codigo</code>.
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

     /**
     * Retorna o valor do atributo <code>descricao</code>
     *
     * @return <code>String</code>
     */
    public String getNome() {

        return nome;
    }

    /**
     * Define o valor do atributo <code>descricao</code>.
     *
     * @param descricao
     */
    public void setNome(String nome) {

        this.nome = nome;
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
}
