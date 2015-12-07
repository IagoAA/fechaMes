package br.com.centralit.framework.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: Favorito</b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 18/12/2014 - 10:08:35
 *
 * @version 1.0.0
 *
 * @author rogerio.cassimiro
 *	
 */
@Entity
public class Favorito extends PersistentObjectAudit {

	private static final long serialVersionUID = 3296140984077316167L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.GenericView.class, Views.UsuarioLogadoView.class })
	protected Long id;

	/** Atributo paginaUsuario. */
	@OneToOne(mappedBy = "favorito")
	private PaginaUsuario paginaUsuario;

	/**
	 * Retorna o valor do atributo <code>paginaUsuario</code>
	 *
	 * @return <code>PaginaUsuario</code>
	 */
	public PaginaUsuario getPaginaUsuario() {

		return paginaUsuario;
	}

	/**
	 * Define o valor do atributo <code>paginaUsuario</code>.
	 *
	 * @param paginaUsuario
	 */
	public void setPaginaUsuario(PaginaUsuario paginaUsuario) {

		this.paginaUsuario = paginaUsuario;
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param nome
	 */
	public void setId(Long id) {

		this.id = id;
	}
}
