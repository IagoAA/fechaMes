package br.com.centralit.api.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.PaginaUsuarioDao;
import br.com.centralit.api.service.FavoritoService;
import br.com.centralit.api.service.PaginaService;
import br.com.centralit.api.service.PaginaUsuarioService;
import br.com.centralit.api.service.SearchParamsService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.api.viewHelper.FavoritoVH;
import br.com.centralit.framework.model.Favorito;
import br.com.centralit.framework.model.Filter;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.PaginaUsuario;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

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
 * <b>Title: </b> Classe PaginaUsuarioServiceImpl
 * </p>
 * 
 * <p>
 * <b>Description: </b> Classe de Implementação Dao de <code>PaginaUsuarioService</code>
 * </p>
 * </p>
 * 
 * @since 19/11/2014 - 09:25:45
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@Service("paginaUsuarioService")
public class PaginaUsuarioServiceImpl extends GenericServiceImpl<PaginaUsuario, Long> implements PaginaUsuarioService {

	/** Atributo paginaUsuarioDao. */
	@Autowired
	private PaginaUsuarioDao paginaUsuarioDao;

	/** Atributo favoritoService. */
	@Autowired
	private FavoritoService favoritoService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/** Atributo paginaService. */
	@Autowired
	private PaginaService paginaService;
	
	/** Atributo searchParamsService. */
	@Autowired
	private SearchParamsService searchParamsService;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param paginaUsuarioDao
	 */
	@Autowired
	public PaginaUsuarioServiceImpl( PaginaUsuarioDao paginaUsuarioDao ) {

		this.dao = paginaUsuarioDao;

		this.paginaUsuarioDao = paginaUsuarioDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchParams saveFilter(SearchParams searchParams, Pagina pageWorkspace, Usuario usuario) {

		pageWorkspace = verificaExistenciaPagina(pageWorkspace);

		PaginaUsuario pagExistente = this.paginaUsuarioDao.getPaginaUsuario(pageWorkspace, usuario);

		if (!UtilObjeto.isReferencia(pagExistente)) {

			pagExistente = this.salvarPaginaUsuario(pageWorkspace, usuario);
		}

		searchParams.setPaginaUsuario(pagExistente);

		for (Filter filter : searchParams.getFilters()) {

			filter.setSearchParams(searchParams);

		}

		return (SearchParams) this.paginaUsuarioDao.save(searchParams);
	}

	/**
	 * Método responsável por verificar a existência da <code>Página</code>
	 * 
	 * @author wilker.machado
	 * 
	 * @param paginaWorkspace
	 * 
	 * @return <code>Pagina</code>
	 */
	private Pagina verificaExistenciaPagina(Pagina paginaWorkspace) {

		Pagina pagina = this.paginaService.getPagina(paginaWorkspace);

		// se existir o objeto pagina, este e utilizado senao faço a busca
		if (UtilObjeto.isReferencia(pagina)) {

			paginaWorkspace = pagina;

		} else {

			paginaWorkspace = (Pagina) this.paginaService.save(paginaWorkspace);
		}

		return paginaWorkspace;
	}

	/**
	 * Método responsável por salvar um novo objeto <code>PaginaUsuario</code>
	 * 
	 * @author wilker.machado
	 * 
	 * @param paginaWorkspace
	 * @param usuario
	 * @return <code>PaginaUsuario</code>
	 */
	public PaginaUsuario salvarPaginaUsuario(Pagina paginaWorkspace, Usuario usuario) {

		PaginaUsuario paginaUsuario = new PaginaUsuario();

		paginaUsuario.setPagina(this.paginaService.find(paginaWorkspace.getId()));

		paginaUsuario.setUsuario(usuario);

		return this.save(paginaUsuario);
	}

	/**
	 * Método responsável por salvar <code>PaginaUsuario</code> com <code>Pagina</code> com ou sem <code>Favorito</code> do usuário.
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param favoritoVH
	 * @param usuario
	 * @return <code>FavoritoVH</code>
	 */
	@Override
	public FavoritoVH saveFavorito(FavoritoVH favoritoVH, Usuario usuario) {

		PaginaUsuario paginaUsuario = this.paginaUsuarioDao.getPaginaUsuario(favoritoVH.getPagina(), usuario);

		// Página já foi salva pelo filtro ou pelo favorito
		if (UtilObjeto.isReferencia(paginaUsuario)) {

			// Desfavorita
			if (UtilObjeto.isReferencia(paginaUsuario.getFavorito())) {

				this.removeFavoritoPaginaUsuario(paginaUsuario);

			} else {

				// Favorita
				this.insertNovoFavoritoPaginaUsuario(paginaUsuario);
			}

		} else {

			paginaUsuario = this.savePaginaUsuario(favoritoVH.getPagina(), usuario);
		}

		favoritoVH.setPagina(paginaUsuario.getPagina());

		favoritoVH.setFavorito(!favoritoVH.isFavorito());

		favoritoVH.setPaginasUsuario(usuario.getPaginasUsuario());

		return favoritoVH;
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por inserir um novo <code>Favorito</code> na <code>PaginaUsuario</code>
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param paginaUsuario
	 */
	private void insertNovoFavoritoPaginaUsuario(PaginaUsuario paginaUsuario) {

		paginaUsuario.setFavorito(this.saveNovoFavorito());

		this.paginaUsuarioDao.save(paginaUsuario);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por remover o <code>Favorito</code> da <code>PaginaUsuario</code>
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param paginaUsuario
	 */
	private void removeFavoritoPaginaUsuario(PaginaUsuario paginaUsuario) {

		paginaUsuario.getFavorito().setDataInativo(Calendar.getInstance());

		paginaUsuario.getFavorito().setPaginaUsuario(null);
		
		paginaUsuario.setFavorito(null);
		
		paginaUsuarioDao.save(paginaUsuario);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por salvar uma nova <code>PaginaUsuario</code> com <code>Favorito</code> <code>Pagina</code>
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param pagina
	 * @param usuario
	 * @return <code>PaginaUsuario</code>
	 */
	private PaginaUsuario savePaginaUsuario(Pagina pagina, Usuario usuario) {

		PaginaUsuario paginaUsuario = new PaginaUsuario();

		paginaUsuario.setFavorito(this.saveNovoFavorito());

		paginaUsuario.setPagina(verificaExistenciaPagina(pagina));

		paginaUsuario.setUsuario(usuario);

		return (PaginaUsuario) this.paginaUsuarioDao.save(paginaUsuario);
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por savlar um novo <code>Favorito</code>
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @return <code>Favorito</code>
	 */
	private Favorito saveNovoFavorito() {

		return this.favoritoService.save(new Favorito());
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por remover favorito por pagina de usuario
	 *
	 * @author iago.almeida
	 *
	 * @param paginaUsuario
	 * @return
	 */
	@Override
	public PaginaUsuario removeFavoritoPaginaUsuario(Long idPaginaUsuario) {

		PaginaUsuario paginaUsuario = (PaginaUsuario) this.paginaUsuarioDao.getReference(idPaginaUsuario);
		
		this.favoritoService.remove(paginaUsuario.getFavorito());
		
		paginaUsuario.getFavorito().setPaginaUsuario(null);
		
		paginaUsuario.setFavorito(null);
		
		return (PaginaUsuario) this.paginaUsuarioDao.save(paginaUsuario);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por trazer paginas de usuário ativas
	 *
	 * @author iago.almeida
	 *
	 * @param idUsuario
	 * @return
	 */
	@Override
	public List<PaginaUsuario> findPorIdUsuario(Long idUsuario) {

		List<PaginaUsuario> listaPaginasUsuario = this.paginaUsuarioDao.findPorIdUsuario(idUsuario); 
		
		for (PaginaUsuario paginaUsuario : listaPaginasUsuario) {
			paginaUsuario.setSearchParams(this.searchParamsService.findPorIdPaginaUsuario(paginaUsuario.getId()));
		}
		
		return listaPaginasUsuario;
		
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por favoritar pagina de usuario
	 *
	 * @author iago.almeida
	 *
	 * @param idPaginaUsuario
	 * @return
	 */
	@Override
	public PaginaUsuario favoritarPaginaUsuario(Long idPaginaUsuario) {
		
		PaginaUsuario paginaUsuario = (PaginaUsuario) this.paginaUsuarioDao.getReference(idPaginaUsuario);
		
		paginaUsuario.setFavorito(this.saveNovoFavorito());

		return (PaginaUsuario) this.paginaUsuarioDao.save(paginaUsuario);
	}

}
