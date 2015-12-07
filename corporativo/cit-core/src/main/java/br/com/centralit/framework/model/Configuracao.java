package br.com.centralit.framework.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
 * <b>Title: Configuracao</b>
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
 * @since 26/08/2015 - 16:06:29
 * 
 * @version 1.0.0
 * 
 * @author geovane.filho
 * 
 */
@Entity
public class Configuracao extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -5272945610683954833L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "configuracao")
	@JsonView({ Views.ConfiguracaoView.class })
	private List<ConfiguracaoParametroSistema> parametros;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.ConfiguracaoView.class })
	private AnexoImagem anexoImagem;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JsonView({ Views.ConfiguracaoView.class })
	protected Organizacao organizacao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Configuracao(){
		
		super();
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
	 * Retorna o valor do atributo <code>organizacao</code>
	 * 
	 * @return <code>Organizacao</code>
	 */
	public Organizacao getOrganizacao() {

		return organizacao;
	}

	/**
	 * Define o valor do atributo <code>organizacao</code>.
	 * 
	 * @param organizacao
	 */
	public void setOrganizacao(Organizacao organizacao) {

		this.organizacao = organizacao;
	}

	/**
	 * Retorna o valor do atributo <code>parametros</code>
	 * 
	 * @return <code>List<ParametroSistema></code>
	 */
	public List<ConfiguracaoParametroSistema> getParametros() {

		return parametros;
	}

	/**
	 * Define o valor do atributo <code>parametros</code>.
	 * 
	 * @param parametros
	 */
	public void setParametros(List<ConfiguracaoParametroSistema> parametros) {

		this.parametros = parametros;
	}

	/**
	 * Retorna o valor do atributo <code>anexoImagem</code>
	 * 
	 * @return <code>AnexoImagem</code>
	 */
	public AnexoImagem getAnexoImagem() {

		return anexoImagem;
	}

	/**
	 * Define o valor do atributo <code>anexoImagem</code>.
	 * 
	 * @param anexoImagem
	 */
	public void setAnexoImagem(AnexoImagem anexoImagem) {

		this.anexoImagem = anexoImagem;
	}

}
