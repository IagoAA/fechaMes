package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * <b>Title: </b>
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
 * @since 05/07/2015 - 17:33:57
 * 
 * @version 1.0.0
 * 
 * @author carlos.alberto
 * 
 */
@Entity
@JsonIgnoreProperties({ "$selected" })
public class PainelModulo extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -6101755343317946307L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo modulo. */
	@JsonView({ Views.PainelEditView.class, Views.DashBoardListView.class })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Modulo modulo;

	/** Atributo painel. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Painel painel;

	/** Atributo painelRemocao. */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Painel painelRemocao;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	@Override
	public Long getId() {

		return this.id;
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

	
	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param id 
	 */
	public void setId(Long id) {
	
		this.id = id;
	}

}
