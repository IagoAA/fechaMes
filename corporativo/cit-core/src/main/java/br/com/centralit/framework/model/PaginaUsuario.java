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
 * <b>Title: PaginaUsuario</b>
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
 * @since 18/12/2014 - 10:09:10
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Entity
@JsonIgnoreProperties({ "$show" })
public class PaginaUsuario extends PersistentObjectAudit {

	private static final long serialVersionUID = -5503087876248538699L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.UsuarioLogadoView.class })
	protected Long id;

	/** Atributo pagina. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonView({ Views.UsuarioEditView.class, Views.GenericView.class, Views.UsuarioLogadoView.class })
	private Pagina pagina;

	/** Atributo usuario. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Usuario usuario;

	/** Atributo favorito. */
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = true, orphanRemoval = true)
	@JsonView({ Views.UsuarioEditView.class, Views.GenericView.class, Views.UsuarioLogadoView.class })
	private Favorito favorito;

	/** Atributo searchParams. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paginaUsuario", cascade = CascadeType.MERGE, orphanRemoval = true)
	@JsonView({ Views.UsuarioEditView.class, Views.GenericView.class, Views.UsuarioLogadoView.class })
	private List<SearchParams> searchParams;

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

	/**
	 * Retorna o valor do atributo <code>pagina</code>
	 * 
	 * @return <code>Pagina</code>
	 */
	public Pagina getPagina() {

		return pagina;
	}

	/**
	 * Define o valor do atributo <code>pagina</code>.
	 * 
	 * @param pagina
	 */
	public void setPagina(Pagina pagina) {

		this.pagina = pagina;
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

	/**
	 * Retorna o valor do atributo <code>favorito</code>
	 * 
	 * @return <code>Favorito</code>
	 */
	public Favorito getFavorito() {

		return favorito;
	}

	/**
	 * Define o valor do atributo <code>favorito</code>.
	 * 
	 * @param favorito
	 */
	public void setFavorito(Favorito favorito) {

		this.favorito = favorito;
	}

	/**
	 * Retorna o valor do atributo <code>searchParams</code>
	 * 
	 * @return <code>List<SearchParams></code>
	 */
	public List<SearchParams> getSearchParams() {

		return searchParams;
	}

	/**
	 * Define o valor do atributo <code>searchParams</code>.
	 * 
	 * @param searchParams
	 */
	public void setSearchParams(List<SearchParams> searchParams) {

		this.searchParams = searchParams;
	}

	/**
	 * Retorna o valor do atributo <code>serialversionuid</code>
	 * 
	 * @return <code>long</code>
	 */
	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
