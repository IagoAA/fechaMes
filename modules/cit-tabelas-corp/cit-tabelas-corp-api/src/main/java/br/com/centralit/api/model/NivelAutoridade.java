package br.com.centralit.api.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.centralit.api.framework.json.ViewsTabelasCorp;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 22/06/2015 - 15:49:42
 *
 * @version 1.0.0
 *
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 *	
 */
@Entity
@JsonIgnoreProperties({"$index"})
public class NivelAutoridade extends PersistentObjectAuditOrganizacao {

    /** Atributo serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ Views.GenericView.class })
    private Long id;
    
    @JsonView({ Views.GenericView.class })
    private String nome;
    
	@JsonView({ Views.GenericView.class })
    private Integer hierarquia;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nivelAutoridade", cascade = CascadeType.ALL)
    @JsonView({ ViewsTabelasCorp.NivelAutoridadeView.class })
    private Collection<NivelAutoridadeGrupo> grupos;
    
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
     * Retorna o valor do atributo <code>hierarquia</code>
     *
     * @return <code>Integer</code>
     */
    public Integer getHierarquia() {

        return hierarquia;
    }

    /**
     * Define o valor do atributo <code>hierarquia</code>.
     *
     * @param hierarquia
     */
    public void setHierarquia(Integer hierarquia) {

        this.hierarquia = hierarquia;
    }
     
    /**
	 * Retorna o valor do atributo <code>grupos</code>
	 *
	 * @return <code>Collection<NivelAutoridadeGrupo></code>
	 */
	public Collection<NivelAutoridadeGrupo> getGrupos() {
	
		return grupos;
	}

	/**
	 * Define o valor do atributo <code>grupos</code>.
	 *
	 * @param grupos 
	 */
	public void setGrupos(Collection<NivelAutoridadeGrupo> grupos) {
	
		this.grupos = grupos;
	}
}
