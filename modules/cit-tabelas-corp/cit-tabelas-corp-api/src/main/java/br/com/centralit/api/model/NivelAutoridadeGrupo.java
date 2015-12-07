package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Grupo;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

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
 * @since 22/06/2015 - 15:50:02
 *
 * @version 1.0.0
 *
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 *	
 */
@Entity
@JsonIgnoreProperties({"$index"})
public class NivelAutoridadeGrupo extends PersistentObject {

    /** Atributo serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ Views.GenericView.class })
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonView({ Views.GenericView.class })
    private Grupo grupo;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private NivelAutoridade nivelAutoridade;
    
     /**
     * Retorna o valor do atributo <code>grupo</code>
     *
     * @return <code>Grupo</code>
     */
    public Grupo getGrupo() {

        return grupo;
    }

    /**
     * Define o valor do atributo <code>grupo</code>.
     *
     * @param grupo
     */
    public void setGrupo(Grupo grupo) {

        this.grupo = grupo;
    }
     
     /**
     * Retorna o valor do atributo <code>nivelAutoridade</code>
     *
     * @return <code>NivelAutoridade</code>
     */
    public NivelAutoridade getNivelAutoridade() {

        return nivelAutoridade;
    }

    /**
     * Define o valor do atributo <code>nivelAutoridade</code>.
     *
     * @param nivelAutoridade
     */
    public void setNivelAutoridade(NivelAutoridade nivelAutoridade) {

        this.nivelAutoridade = nivelAutoridade;
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
