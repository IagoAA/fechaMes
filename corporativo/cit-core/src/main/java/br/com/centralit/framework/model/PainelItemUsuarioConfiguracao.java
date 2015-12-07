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
 * @since 09/03/2015 - 17:35:01
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
@Entity
public class PainelItemUsuarioConfiguracao extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo ativo. */
	private Boolean ativo;

	/** Atributo ordem. */
	private Integer ordem;

	/** Atributo usuario. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	@Override
	public Long getId() {

		return id;
	}

	/**
	 * Retorna o valor do atributo <code>ativo</code>
	 * 
	 * @return <code>Boolean</code>
	 */
	public Boolean getAtivo() {

		return ativo;
	}

	/**
	 * Define o valor do atributo <code>ativo</code>.
	 * 
	 * @param ativo
	 */
	public void setAtivo(Boolean ativo) {

		this.ativo = ativo;
	}

	/**
	 * Retorna o valor do atributo <code>ordem</code>
	 * 
	 * @return <code>Integer</code>
	 */
	public Integer getOrdem() {

		return ordem;
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
	 * Retorna o valor do atributo <code>usuario</code>
	 * 
	 * @return <code>Usuario</code>
	 */
	public Usuario getUsuario() {

		return usuario;
	}

	/**
	 * Define o valor do atributo <code>usuario</code>.
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

}
