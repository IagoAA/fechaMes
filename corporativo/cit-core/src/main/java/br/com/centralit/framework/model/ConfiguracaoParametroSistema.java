package br.com.centralit.framework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

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
 * @since 24/02/2015 - 14:29:21
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"chave", "configuracao_id"})) 
public class ConfiguracaoParametroSistema extends PersistentObjectAudit {

	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;
	
	@Column(nullable = true)
	@JsonView({ Views.ConfiguracaoView.class })
	private String grupo; //Criado para agrupar chaves diferentes em um mesmo grupo, exemplo: chaves referentes a uma lista de elementos

	/** Atributo chave. */
	@Column(nullable = false)
	@JsonView({ Views.ConfiguracaoView.class })
	private String chave;

	/** Atributo valor. */
	@Column(nullable = false)
	@JsonView({ Views.ConfiguracaoView.class })
	private String valor;

	@ManyToOne(fetch = FetchType.LAZY)
	private Configuracao configuracao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public ConfiguracaoParametroSistema() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param chave
	 * @param valor
	 * @param configuracao
	 */
	public ConfiguracaoParametroSistema( String chave, String valor, Configuracao configuracao ) {

		super();
		this.chave = chave;
		this.valor = valor;
		this.configuracao = configuracao;
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
	 * Retorna o valor do atributo <code>configuracao</code>
	 * 
	 * @return <code>Configuracao</code>
	 */
	public Configuracao getConfiguracao() {

		return configuracao;
	}

	/**
	 * Define o valor do atributo <code>configuracao</code>.
	 * 
	 * @param configuracao
	 */
	public void setConfiguracao(Configuracao configuracao) {

		this.configuracao = configuracao;
	}

}
