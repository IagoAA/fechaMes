package br.com.centralit.api.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;

import br.com.centralit.api.dao.MenuDao;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.GrupoService;
import br.com.centralit.api.service.MenuFileService;
import br.com.centralit.api.service.MenuGrupoService;
import br.com.centralit.api.service.MenuPrivilegioService;
import br.com.centralit.api.service.MenuService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.PaginaService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.CodigoErro;
import br.com.centralit.framework.model.Grupo;
import br.com.centralit.framework.model.GrupoUsuario;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuGrupo;
import br.com.centralit.framework.model.MenuPrivilegio;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.Privilegio;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.model.UsuarioPrivilegio;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilColecao;
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
 * <b>Title: MenuServiceImpl</b>
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
 * @since 09/12/2014 - 17:42:31
 * 
 * @version 1.0.0
 * 
 * @author renato.jesus
 * 
 */
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
 * @since 11/09/2015 - 13:24:49
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Service("menuService")
public class MenuServiceImpl extends GenericServiceImpl<Menu, Long> implements MenuService {

	/** Atributo menuDao. */
	@Autowired
	private MenuDao menuDao;

	@Autowired
	private DominioService dominioService;

	/** Atributo menuGrupoService. */
	@Autowired
	private MenuGrupoService menuGrupoService;

	/** Atributo menuPrivilegioService. */
	@Autowired
	private MenuPrivilegioService menuPrivilegioService;

	/** Atributo grupoService. */
	@Autowired
	private GrupoService grupoService;

	/** Atributo usuarioService. */
	@Autowired
	private UsuarioService usuarioService;

	/** Atributo moduloService. */
	@Autowired
	private ModuloService moduloService;

	/** Atributo menuFileService. */
	@Autowired
	private MenuFileService menuFileService;

	/** Atributo paginaService. */
	@Autowired
	private PaginaService paginaService;

	/** Atributo menu. */
	private Menu menu;

	@Autowired
	public MenuServiceImpl( MenuDao menuDao, @Qualifier("menuValidator") Validator validator ) {

		this.dao = menuDao;

		this.menuDao = menuDao;

		this.validator = validator;
	}

	public List<Menu> findParent() {

		return this.menuDao.findParent();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getAllParent() {

		return this.menuDao.getAllParent();
	}

	@Override
	public List<Menu> findMenuByIdParent(Long idParent) {

		return this.menuDao.findMenuByIdParent(idParent);
	}

	@Override
	public Menu save(Menu menu) {

		this.montarDadosMenu(menu);

		this.menu = menu;

		if (this.isMenuPrincipal()) {
			this.generateClassePaginaMenu();
			this.generateCssMenu();
		} else {
			this.resolveTransientParentMenu();
			this.setParentColorAndClassePagina();
		}

		this.setOrdemMenu();

		this.validarChaveMenu(menu);

		return super.save(this.menu);
	}

	public Menu mergeSimples(Menu menu) {

		return super.merge(menu);
	}

	@Override
	public Menu merge(Menu menu) {

		this.menu = menu;

		this.montarDadosMenu(menu);

		this.menuGrupoService.verificarRemocao(menu);

		this.menuPrivilegioService.verificarRemocao(menu);

		if (this.isMenuPrincipal()) {
			Menu menuBase = this.getReference(this.menu.getId());

			this.menu.setClassePagina(menuBase.getClassePagina());

			if (this.menu.getCor().equals(menuBase.getCor())) {
				this.menu.setCssMenu(menuBase.getCssMenu());
				this.setColorParentInChildren();
				this.setClassePaginaParentInChildren();
			} else {
				this.generateClassePaginaMenu();
				this.generateCssMenu();
				this.setColorParentInChildren();
				this.setClassePaginaParentInChildren();
			}
		} else {
			this.resolveTransientParentMenu();
			this.setParentColorAndClassePagina();
		}
		
		this.validarChaveMenu(menu);

		return super.merge(menu);
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
	 * Método responsável por resolver as propriedades transiente do menu
	 * 
	 * @author rogerio.costa
	 * 
	 * @param menu
	 */
	private void montarDadosMenu(Menu menu) {

		if (!UtilColecao.isVazio(menu.getMenuPrivilegios())) {

			menu.setPermissaoDeAcessoDefinida(true);

			for (MenuPrivilegio menuPrivilegio : menu.getMenuPrivilegios()) {

				menuPrivilegio.setMenu(menu);
			}
		}

		if (!UtilColecao.isVazio(menu.getMenuGrupos())) {

			menu.setPermissaoDeAcessoDefinida(true);

			for (MenuGrupo menuGrupo : menu.getMenuGrupos()) {

				menuGrupo.setGrupo(this.grupoService.getReference(menuGrupo.getGrupo().getId()));

				menuGrupo.setMenu(menu);
			}
		}
	}

	@Override
	public List<Menu> findByName(String nome) {

		Usuario usuario = this.usuarioService.getReference(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		Collection<Long> idsGrupo = this.getIdsGrupoUsuario(usuario);

		Collection<Long> idsPrivilegioUsuario = getIdsPrivilegioUsuario(usuario);

		List<Menu> menusPermitido = new LinkedList<Menu>();

		// Carrega menus da pesquisa
		List<Menu> menus = menuDao.findByName(nome, idsGrupo, idsPrivilegioUsuario);

		// Estrutura de repeticao necessaria para setar classe e cor
		for (Menu menu : menus) {
			// Valida permissão do parent
			if (this.isMenuPermitido(idsGrupo, idsPrivilegioUsuario, menu)) {

				this.setarClasseCorMenuSearch(menu, menu);

				menusPermitido.add(menu);
			}

		}

		return menusPermitido;
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
	 * Método recursivo responsável por verificar a permissão dos parents
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idsGrupo
	 * 
	 * @param idsPrivilegioUsuario
	 * 
	 * @param menu
	 */
	private Boolean isMenuPermitido(Collection<Long> idsGrupo, Collection<Long> idsPrivilegioUsuario, Menu menu) {

		// Verifica se o menu contem parent
		if (UtilObjeto.isReferencia(menu.getParent())) {
			// Verifica se a permissão foi configurada para o parent do menu
			if (UtilObjeto.isReferencia(menu.getParent().getPermissaoDeAcessoDefinida()) && menu.getParent().getPermissaoDeAcessoDefinida()) {
				// Verifica se o parent contem permissão de grupo ou privilegio
				if (this.isContemGrupo(idsGrupo, menu.getParent()) || this.isContemPrivilegio(idsPrivilegioUsuario, menu.getParent())) {
					// Valida a permissão do parent recursivamente.
					if (!this.isMenuPermitido(idsGrupo, idsPrivilegioUsuario, menu.getParent())) {

						return Boolean.FALSE;
					}
				} else {

					// Retorna falso caso o parent não tenha permissão
					return Boolean.FALSE;
				}

			} else {
				// Verifica a permissão do parent.parent recursivamente
				return this.isMenuPermitido(idsGrupo, idsPrivilegioUsuario, menu.getParent());

			}
			// Retorna verdadeiro se o parent não tiver configuração definida.
			return Boolean.TRUE;
		}
		// Retorna verdadeiro se o menu não conter parent
		return Boolean.TRUE;
	}

	/**
	 * 
	 * Metodo recursivo responsável por setar as classes e cores dos menus pesquisados
	 * 
	 * @author iago.almeida
	 * 
	 * @param menu
	 * @param menuSearch
	 */
	private void setarClasseCorMenuSearch(Menu menu, Menu menuSearch) {

		if (UtilObjeto.isReferencia(menu.getParent())) {

			this.setarClasseCorMenuSearch(menu.getParent(), menuSearch);

		} else {

			menuSearch.setClassePagina(menu.getClassePagina());
			menuSearch.setCor(menu.getCor());

		}

	}

	/**
	 * Método responsável por carregar menus da tela com suas estruturas
	 * 
	 * @author iago.almeida
	 * 
	 * @return List<Menu>
	 */
	@Override
	public List<Menu> getAllMenusAtivos() {

		Usuario usuario = this.usuarioService.getReference(( (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId());

		Collection<Long> idsPrivilegioUsuario = this.getIdsPrivilegioUsuario(usuario);

		Collection<Long> idsGrupo = this.getIdsGrupoUsuario(usuario);

		// Carrega menus parents
		List<Menu> menusParents = this.menuDao.getAllMenusAtivos(usuario, idsGrupo, idsPrivilegioUsuario);

		// Estrutura de repeticao necessaria para setar classe e cor dos sub menus
		for (Menu menuParent : menusParents) {

			this.setarClasseCorParent(menuParent, idsGrupo, idsPrivilegioUsuario);
		}

		return menusParents;

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
	 * Método responsável por listar os ids dos grupos vinculado ao usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param usuario
	 * 
	 * @return Collection<Long>
	 */
	private Collection<Long> getIdsGrupoUsuario(Usuario usuario) {

		Collection<Long> idsGrupo = new LinkedList<Long>();
		// Verifica se o usuario contem grupo vinculado, caso seja verdadeiro monta uma lista com os ids do mesmo.
		if (!UtilColecao.isVazio(usuario.getGrupoUsuarios())) {

			for (GrupoUsuario grupoUsuario : usuario.getGrupoUsuarios()) {

				if (UtilObjeto.isReferencia(grupoUsuario.getGrupo())) {

					idsGrupo.add(grupoUsuario.getGrupo().getId());
				}

			}

		}
		return idsGrupo;
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
	 * Método responsável por listar os ids do privilegio vinculado ao usuario
	 * 
	 * @author rogerio.costa
	 * 
	 * @param usuario
	 * 
	 * @return Collection<Long>
	 */
	private Collection<Long> getIdsPrivilegioUsuario(Usuario usuario) {

		Collection<Long> idsPrivilegioUsuario = new LinkedList<Long>();

		// Verifica se o usuario contem privilegio vinculado, caso seja verdadeiro monta uma lista com os ids do mesmo.
		if (usuario.getUsuarioPrivilegios() != null && usuario.getUsuarioPrivilegios().size() > 0) {

			for (UsuarioPrivilegio usuarioPrivilegio : usuario.getUsuarioPrivilegios()) {

				if (UtilObjeto.isReferencia(usuarioPrivilegio.getPrivilegio())) {

					idsPrivilegioUsuario.add(usuarioPrivilegio.getPrivilegio().getId());
				}

			}

		}
		return idsPrivilegioUsuario;
	}

	/**
	 * 
	 * Metodo recursivo responsável por setar as classes e cores a partir dos parents
	 * 
	 * @author iago.almeida
	 * 
	 * @param menuParent
	 * @param idsPrivilegioUsuario
	 * @param idsGrupo
	 */
	private void setarClasseCorParent(Menu menuParent, Collection<Long> idsGrupo, Collection<Long> idsPrivilegioUsuario) {

		List<Menu> menusPermitidos = new LinkedList<Menu>();
		// Quando houver submenu do parent
		if (!UtilColecao.isVazio(menuParent.getSubmenu())) {

			// Itera cada submenu do parent para adicionar a classe e cor
			for (Menu menuFilho : menuParent.getSubmenu()) {

				menuFilho.setCor(menuParent.getCor());

				menuFilho.setClassePagina(menuParent.getClassePagina());

				if (this.isSubMenuPermitidoUsuario(idsGrupo, idsPrivilegioUsuario, menuFilho) && ( !UtilObjeto.isReferencia(menuFilho.getModulo()) || ( UtilObjeto.isReferencia(menuFilho.getModulo()) && menuFilho.getModulo().getHabilitado() ) )) {

					menusPermitidos.add(menuFilho);

					this.setarClasseCorParent(menuFilho, idsGrupo, idsPrivilegioUsuario);
				}

			}

			menuParent.setSubmenu(menusPermitidos);

		}

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
	 * Método responsável por verificar se o subMenu contem permissão
	 * 
	 * @author rogerio.costa
	 * 
	 * @param idsGrupo
	 * @param idsPrivilegioUsuario
	 * @param menusPermitidos
	 * @param menuFilho
	 */
	private boolean isSubMenuPermitidoUsuario(Collection<Long> idsGrupo, Collection<Long> idsPrivilegioUsuario, Menu menuFilho) {

		if (UtilObjeto.isReferencia(menuFilho.getPermissaoDeAcessoDefinida()) && menuFilho.getPermissaoDeAcessoDefinida()) {

			return this.isContemPrivilegio(idsPrivilegioUsuario, menuFilho) || this.isContemGrupo(idsGrupo, menuFilho);

		} else {

			return Boolean.TRUE;
		}
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
	 * Método responsável por
	 * 
	 * @author Rogério Gomes
	 * 
	 * @param idsPrivilegioUsuario
	 * @param menuFilho
	 * @return
	 */
	private boolean isContemPrivilegio(Collection<Long> idsPrivilegioUsuario, Menu menuFilho) {

		Privilegio privilegio = new Privilegio();

		MenuPrivilegio menuPrivilegio = new MenuPrivilegio();

		for (Long idPrivilegio : idsPrivilegioUsuario) {

			privilegio.setId(idPrivilegio);

			menuPrivilegio.setPrivilegio(privilegio);

			if (menuFilho.getMenuPrivilegios().contains(menuPrivilegio)) {

				return Boolean.TRUE;

			}

		}

		return Boolean.FALSE;
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
	 * Método responsável por
	 * 
	 * @author Rogério Gomes
	 * 
	 * @param idsGrupo
	 * @param menuFilho
	 * @return
	 */
	private boolean isContemGrupo(Collection<Long> idsGrupo, Menu menuFilho) {

		Grupo grupo = new Grupo();

		MenuGrupo menuGrupo = new MenuGrupo();

		for (Long idGrupo : idsGrupo) {

			grupo.setId(idGrupo);

			menuGrupo.setGrupo(grupo);

			if (menuFilho.getMenuGrupos().contains(menuGrupo)) {

				return Boolean.TRUE;

			}

		}

		return Boolean.FALSE;
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
	 * Método responsável por obter o menu através do nome
	 * 
	 * @author rogerio.costa
	 * 
	 * @param nome
	 * @return Menu
	 */
	public Menu findPorNome(String nome) {

		Menu menu = this.menuDao.findPorNome(nome);

		return menu;
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
	 * Método responsável por obter o menu e setar a classeCor através do nome
	 * 
	 * @author rogerio.costa
	 * 
	 * @param nome
	 * 
	 * @return Menu
	 */
	public Menu obterMenuCorClassePorNome(String nome) {

		Menu menu = this.menuDao.findPorNome(nome);

		this.setarClasseCorMenuSearch(menu, menu);

		return menu;
	}

	@Override
	public List<Modulo> findModulosAtivo() {

		return menuDao.findModulosAtivo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Menu buscarMenusPorPagina(Pagina pagina) {

		Menu menu = this.menuDao.buscarMenusPorPagina(pagina);

		if (UtilObjeto.isReferencia(menu)) {

			menu = menu.clone();

			this.setarClasseCorMenuSearch(menu, menu);
		}

		return menu;
	}

	@Override
	public List<Menu> saveOrdem(List<Menu> menuList) {

		if (!UtilColecao.isVazio(menuList)) {
			List<Menu> menuNewList = new ArrayList<Menu>();

			Menu menuBase = null;
			for (Menu menu : menuList) {
				this.menu = menu;

				menuBase = this.find(menu.getId());
				menuBase.setOrdem(menu.getOrdem());
				menuBase.setColuna(menu.getColuna());

				if (!isMenuPrincipal()) {
					Menu parent = this.find(menu.getParent().getId());
					menuBase.setParent(parent);

					this.resolveOrdemInChildren();
					this.setIdParentInChildren();
					this.setColorParentInChildren();
				}

				menuNewList.add(menuBase);
			}

			menuList = menuNewList;
		}

		return super.saveList(menuList);
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void resolveOrdemInChildren() {

		if (!UtilColecao.isVazio(this.menu.getSubmenu())) {
			List<Menu> submenuNewList = new ArrayList<Menu>();

			Menu menuBase = null;
			for (Menu menu : this.menu.getSubmenu()) {
				menuBase = this.find(menu.getId());
				menuBase.setOrdem(menu.getOrdem());
				submenuNewList.add(menuBase);
			}

			this.menu.setSubmenu(submenuNewList);
		}
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void setIdParentInChildren() {

		if (!UtilColecao.isVazio(this.menu.getSubmenu())) {
			for (Menu menu : this.menu.getSubmenu()) {
				menu.setParent(this.menu);
			}
		}
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 * @return
	 */
	private boolean isMenuPrincipal() {

		return !UtilObjeto.isReferencia(this.menu.getParent());
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void generateClassePaginaMenu() {

		SecureRandom random = new SecureRandom();
		String aleatoryNumber = new BigInteger(30, random).toString(32);

		this.menu.setClassePagina("mod-" + aleatoryNumber);
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void generateCssMenu() {

		String cor = menu.getCor();

		double opacity = 0.5;

		if (UtilObjeto.isReferencia(this.menu.getCssMenuOpacity()) && this.menu.getCssMenuOpacity() > 0) {
			opacity = menu.getCssMenuOpacity() / 10;
		}

		Integer red = Integer.valueOf(cor.substring(1, 3), 16);
		Integer green = Integer.valueOf(cor.substring(3, 5), 16);
		Integer blue = Integer.valueOf(cor.substring(5, 7), 16);

		StringBuilder cssMenu = new StringBuilder();

		// SETA O BACKGROUND DA BARRA DE AÇÕES DA PÁGINA
		cssMenu.append("." + menu.getClassePagina() + " .bar-buttons-action:before {");
		cssMenu.append("	background: rgb(255,255,255);");
		cssMenu.append("	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(" + red + "," + green + "," + blue + "," + opacity + ")));");
		cssMenu.append("	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='" + cor + "',GradientType=0 );");
		cssMenu.append("}");

		// SETA O BACKGROUND DA BARRA DE AÇÕES DA MODAL
		cssMenu.append(".modal.modal-buttons-top." + menu.getClassePagina() + " .modal-dialog .modal-header {");
		cssMenu.append("	background: rgb(255,255,255);");
		cssMenu.append("	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(" + red + "," + green + "," + blue + "," + opacity + ")));");
		cssMenu.append("	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(" + red + "," + green + "," + blue + "," + opacity + ") 100%);");
		cssMenu.append("	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='" + cor + "',GradientType=0 );");
		cssMenu.append("}");

		// SETA A BORDA DA TAB
		cssMenu.append("#workspace-nav li." + menu.getClassePagina() + " a {");
		cssMenu.append("	border-top-color: " + menu.getCor() + ";");
		cssMenu.append("}");

		this.menu.setCssMenu(cssMenu.toString());
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void setOrdemMenu() {

		Integer countMenu = this.findAll().size();

		this.menu.setOrdem(countMenu);
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void resolveTransientParentMenu() {

		if (UtilObjeto.isReferencia(this.menu.getParent()) && UtilObjeto.isReferencia(this.menu.getParent().getId())) {
			Menu parent = this.getReference(this.menu.getParent().getId());

			this.menu.setParent(parent);
		}
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void setParentColorAndClassePagina() {

		if (UtilObjeto.isReferencia(this.menu.getParent())) {
			Menu parent = menu.getParent();

			this.menu.setCor(parent.getCor());
			this.menu.setClassePagina(parent.getClassePagina());
		}
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void setColorParentInChildren() {

		List<Menu> submenuList = this.findMenuByIdParent(this.menu.getId());
		List<Menu> submenuTitleList = new ArrayList<Menu>();
		List<Menu> submenuLinkList = new ArrayList<Menu>();

		for (Menu submenuTitle : submenuList) {
			submenuTitle.setCor(this.menu.getCor());
			submenuTitle.setParent(this.menu);

			submenuTitleList.add(submenuTitle);

			for (Menu submenuLink : submenuTitle.getSubmenu()) {
				submenuLink.setCor(this.menu.getCor());
				submenuLink.setParent(submenuTitle);

				submenuLinkList.add(submenuLink);
			}
		}

		this.saveList(submenuTitleList);
		this.saveList(submenuLinkList);
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
	 * Método responsável por
	 * 
	 * @author renato.jesus
	 * 
	 */
	private void setClassePaginaParentInChildren() {

		List<Menu> submenuList = this.findMenuByIdParent(this.menu.getId());
		List<Menu> submenuTitleList = new ArrayList<Menu>();
		List<Menu> submenuLinkList = new ArrayList<Menu>();

		for (Menu submenuTitle : submenuList) {
			submenuTitle.setClassePagina(this.menu.getClassePagina());
			submenuTitle.setParent(this.menu);

			submenuTitleList.add(submenuTitle);

			for (Menu submenuLink : submenuTitle.getSubmenu()) {
				submenuLink.setClassePagina(this.menu.getClassePagina());
				submenuLink.setParent(submenuTitle);

				submenuLinkList.add(submenuLink);
			}
		}

		this.saveList(submenuTitleList);
		this.saveList(submenuLinkList);
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
	 * Método responsável por obter todos os Menu que não possuem parent e que são de módulos ativos
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @return List<Menu>
	 */
	@Override
	public List<Menu> findMenuPorModulosAtivos() {

		return this.menuDao.findMenuPorModulosAtivos();
	}

	@Override
	public Menu mergeIfNotExist(PersistentObject entity) {

		Menu menuTemp = (Menu) entity;

		// Seta chave sempre
		if (StringUtils.isEmpty(menuTemp.getChave())) {
			menuTemp.setChave(this.normalizarString(menuTemp.getNome()));
		}

		Menu menu = this.menuDao.obterMenuPorChave(menuTemp.getChave());

		if (UtilObjeto.isReferencia(menu)) {
			return menu;
		}

		if (UtilObjeto.isReferencia(menuTemp.getPagina())) {
			menuTemp.setPagina(this.paginaService.find(menuTemp.getPagina().getId()));
		}

		return save(menuTemp);
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
	 * Método responsável por validar unicidade da chave do Menu
	 * 
	 * @author rogerio.cassimiro
	 * 
	 * @param menu
	 */
	private void validarChaveMenu(Menu menu) {

		// Seta chave sempre
		if (StringUtils.isEmpty(menu.getChave())) {
			menu.setChave(this.normalizarString(menu.getNome()));
		}
		
		Menu menuTemp = this.menuDao.obterMenuPorChave(menu.getChave());

		// Merge || Save	
		if ( (UtilObjeto.isReferencia(menu.getId()) && UtilObjeto.isReferencia(menuTemp) && !menuTemp.getId().equals(menu.getId())) 
				||
				(!UtilObjeto.isReferencia(menu.getId()) && UtilObjeto.isReferencia(menuTemp)) 
																								) {

			throw new BusinessException("MSG.CHAVE_MENU_DUPLICADA", CodigoErro.REGRA_NEGOCIO.getValue(), "");
			
		}
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por normalizar a string sem acentuação e troca espaço em branco por _ 
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param str
	 * @return String
	 */
	private String normalizarString(String str) {
		
		str = str.replaceAll(" ", "_");
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").toUpperCase();
	}

}
