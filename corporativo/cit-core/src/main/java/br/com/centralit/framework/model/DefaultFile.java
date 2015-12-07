package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
 * <b>Title: DefaultFile</b>
 * </p>
 * 
 * <p>
 * <b>Description: Entidade de mapeamento dos 'Arquivos Padrões' da aplicação</b>
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
 * @since 09/12/2014 - 17:18:19
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
public class DefaultFile extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo caminho. */
	@JsonView({ Views.GenericView.class })
	private String caminho;

	/** Atributo ordem. */
	@JsonView({ Views.GenericView.class })
	private Integer ordem = 0;

	/** Atributo dominioDefaultFile. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.GenericView.class })
	private Dominio dominioDefaultFile;

	/** Atributo ativo. */
	@JsonView({ Views.GenericView.class })
	private Boolean ativo = true;

	public DefaultFile() {

		super();
	}

	public DefaultFile( String caminho, Integer ordem, Dominio dominioDefaultFile ) {

		super();
		this.caminho = caminho;
		this.ordem = ordem;
		this.dominioDefaultFile = dominioDefaultFile;
		this.ativo = Boolean.TRUE;
	}

	public DefaultFile( String caminho, Dominio dominioDefaultFile, Integer ordem ) {

		this(caminho, ordem, dominioDefaultFile);
	}

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
	 * Define o valor do atributo <code>id</code>.
	 * 
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>caminho</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCaminho() {

		return this.caminho;
	}

	/**
	 * Define o valor do atributo <code>caminho</code>.
	 * 
	 * @param caminho
	 */
	public void setCaminho(String caminho) {

		this.caminho = caminho;
	}

	/**
	 * Retorna o valor do atributo <code>ordem</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getOrdem() {

		return this.ordem;
	}

	/**
	 * Define o valor do atributo <code>ordem</code>.
	 * 
	 * @param ordem
	 */
	public void setOrdem(Integer ordem) {

		this.ordem = ordem;
	}

	/**
	 * Retorna o valor do atributo <code>dominioDefaultFile</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioDefaultFile() {

		return this.dominioDefaultFile;
	}

	/**
	 * Define o valor do atributo <code>dominioDefaultFile</code>.
	 * 
	 * @param dominioDefaultFile
	 */
	public void setDominioDefaultFile(Dominio dominioDefaultFile) {

		this.dominioDefaultFile = dominioDefaultFile;
	}

	/**
	 * Retorna o valor do atributo <code>ativo</code>
	 * 
	 * @return <code>String</code>
	 */
	public Boolean getAtivo() {

		return this.ativo;
	}

	/**
	 * Define o valor do atributo <code>ativo</code>.
	 * 
	 * @param ativo
	 */
	public void setAtivo(Boolean ativo) {

		this.ativo = ativo;
	}
}
