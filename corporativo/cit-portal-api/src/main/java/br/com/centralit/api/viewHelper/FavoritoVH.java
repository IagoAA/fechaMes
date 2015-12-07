package br.com.centralit.api.viewHelper;

import java.io.Serializable;
import java.util.Collection;

import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.PaginaUsuario;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: FavoritoVH</b></p>
 *
 * <p><b>Description: </b></p>
 * 
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
 * 	
 * @since 18/12/2014 - 08:30:06
 *
 * @version 1.0.0
 *
 * @author rogerio.cassimiro
 *	
 */
public class FavoritoVH implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 2342505270077662292L;

	/** Atributo pagina. */
	@JsonView({ Views.GenericView.class })
	private Pagina pagina;

	/** Atributo favorito. */
	@JsonView({ Views.GenericView.class })
	private boolean favorito;

	/** Atributo paginasUsuario. */
	@JsonView({ Views.GenericView.class })
	private Collection<PaginaUsuario> paginasUsuario;

	/**
	 * Retorna o valor do atributo <code>paginasUsuario</code>
	 * 
	 * @return <code>Collection<PaginaUsuario></code>
	 */
	public Collection<PaginaUsuario> getPaginasUsuario() {

		return this.paginasUsuario;
	}

	/**
	 * Define o valor do atributo <code>paginasUsuario</code>.
	 * 
	 * @param paginasUsuario
	 */
	public void setPaginasUsuario(final Collection<PaginaUsuario> paginasUsuario) {

		this.paginasUsuario = paginasUsuario;
	}

	/**
	 * Retorna o valor do atributo <code>pagina</code>
	 * 
	 * @return <code>Pagina</code>
	 */
	public Pagina getPagina() {

		return this.pagina;
	}

	/**
	 * Define o valor do atributo <code>pagina</code>.
	 * 
	 * @param pagina
	 */
	public void setPagina(final Pagina pagina) {

		this.pagina = pagina;
	}

	/**
	 * Retorna o valor do atributo <code>favorito</code>
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isFavorito() {

		return this.favorito;
	}

	/**
	 * Define o valor do atributo <code>favorito</code>.
	 * 
	 * @param favorito
	 */
	public void setFavorito(final boolean favorito) {

		this.favorito = favorito;
	}

}
