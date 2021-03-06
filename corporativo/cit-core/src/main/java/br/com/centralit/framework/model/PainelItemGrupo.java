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
 * <b>Title: </b> WidgetGrupo
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
 * @since 29/04/2015 - 15:02:28
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
@JsonIgnoreProperties({ "$selected" })
public class PainelItemGrupo extends PersistentObjectAuditOrganizacao {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 60725619490150359L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;

	/** Atributo grupo. */
	@JsonView({ Views.PainelEditView.class })
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Grupo grupo;

	/** Atributo painelItem. */
	@ManyToOne(fetch = FetchType.EAGER)
	private PainelItem painelItem;

	/** Atributo painelItem. */
	@ManyToOne(fetch = FetchType.EAGER)
	private PainelItem painelItemRemocao;

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
	 * Retorna o valor do atributo <code>painelItemRemocao</code>
	 * 
	 * @return <code>PainelItem</code>
	 */
	public PainelItem getPainelItemRemocao() {

		return painelItemRemocao;
	}

	/**
	 * Define o valor do atributo <code>painelItemRemocao</code>.
	 * 
	 * @param painelItemRemocao
	 */
	public void setPainelItemRemocao(PainelItem painelItemRemocao) {

		this.painelItemRemocao = painelItemRemocao;
	}

}
