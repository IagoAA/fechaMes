package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAuditOrganizacao;

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
 * <b>Title: </b> PainelGrupo
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
 * @since 28/04/2015 - 15:32:35
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
@JsonIgnoreProperties({ "$selected" })
public class PainelGrupo extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1890431887338913363L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo grupo. */
	@JsonView({ Views.PainelEditView.class })
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Grupo grupo;

	/** Atributo painel. */
	@ManyToOne(fetch = FetchType.EAGER)
	private Painel painel;

	/** Atributo painelRemocao. */
	@ManyToOne(fetch = FetchType.EAGER)
	private Painel painelRemocao;

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
	 * Retorna o valor do atributo <code>painel</code>
	 * 
	 * @return <code>Painel</code>
	 */
	public Painel getPainel() {

		return painel;
	}

	/**
	 * Define o valor do atributo <code>painel</code>.
	 * 
	 * @param painel
	 */
	public void setPainel(Painel painel) {

		this.painel = painel;
	}

	/**
	 * Retorna o valor do atributo <code>painelRemocao</code>
	 * 
	 * @return <code>Painel</code>
	 */
	public Painel getPainelRemocao() {

		return painelRemocao;
	}

	/**
	 * Define o valor do atributo <code>painelRemocao</code>.
	 * 
	 * @param painelRemocao
	 */
	public void setPainelRemocao(Painel painelRemocao) {

		this.painelRemocao = painelRemocao;
	}

}
