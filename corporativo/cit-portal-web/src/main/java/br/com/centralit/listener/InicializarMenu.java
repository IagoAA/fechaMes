package br.com.centralit.listener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.api.service.ConfiguracaoService;
import br.com.centralit.api.service.DefaultFileService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.InternacionalizacaoService;
import br.com.centralit.api.service.MenuService;
import br.com.centralit.api.service.ModuloService;
import br.com.centralit.api.service.OrganizacaoService;
import br.com.centralit.api.service.PaginaService;
import br.com.centralit.api.service.PrivilegioService;
import br.com.centralit.api.service.UsuarioOrganizacaoItemService;
import br.com.centralit.api.service.UsuarioService;
import br.com.centralit.api.service.WidgetParametroService;
import br.com.centralit.api.service.WidgetService;
import br.com.centralit.framework.model.DefaultFile;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuFile;
import br.com.centralit.framework.model.Pagina;
import br.com.centralit.framework.model.Widget;
import br.com.centralit.framework.model.WidgetParametro;

@Component
public class InicializarMenu {

	/** Atributo REPOSITORY_JS. */
	private final String REPOSITORY_JS = "Repository.js";

	/** Atributo REPOSITORY_MIN_JS. */
	private final String REPOSITORY_MIN_JS = "Repository.min.js";

	/** Atributo LIST_CONTROLLER_JS. */
	private final String LIST_CONTROLLER_JS = "ListController.js";

	/** Atributo LIST_CONTROLLER_MIN_JS. */
	private final String LIST_CONTROLLER_MIN_JS = "ListController.min.js";

	/** Atributo CONTROLLER_JS. */
	private final String CONTROLLER_JS = "Controller.js";

	/** Atributo CONTROLLER_MIN_JS. */
	private final String CONTROLLER_MIN_JS = "Controller.min.js";

	/** Atributo CIT_PORTAL_WEB_ANGULAR_CUSTOM. */
	private final String CIT_PORTAL_WEB_ANGULAR_CUSTOM = "assets/js/angular/custom/";

	/** Atributo CIT_PORTAL_WEB_ANGULAR_CUSTOM. */
	private final String CIT_PORTAL_WEB_ANGULAR_LIB = "assets/js/angular/lib/";

	/** Atributo CSS_MENU_ALCADA. */
	private final String CSS_MENU_PORTAL = ".mod-blue .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr=\"#ffffff\", endColorstr=\"#62a0d2\",GradientType=0 );}.modal.modal-buttons-top.mod-blue .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr=\"#ffffff\", endColorstr=\"#62a0d2\",GradientType=0 );}#workspace-nav li.mod-blue a {	border-top-color: #62a0d2;}";

	/** Atributo COR_PORTAL. */
	private final String COR_PORTAL = "#62a0d2";

	/** Atributo CLASSE_PORTAL. */
	private final String CLASSE_PORTAL = "mod-blue";

	/** Atributo OPACIDADE_MENU_PORTAL. */
	private final double OPACIDADE_MENU_PORTAL = 5D;

	/** Atributo CLASSE_MENU_DASHBOARD. */
	private final String ICONE_MENU_DASHBOARD = "fa-dashboard";

	/** Atributo ICONE_MENU_DEFINICOES_DO_SISTEMA. */
	private final String ICONE_MENU_DEFINICOES_DO_SISTEMA = "fa-cog";

	@Autowired
	private InternacionalizacaoService internacionalizacaoService;

	@Autowired
	private DominioService dominioService;

	@Autowired
	private DefaultFileService defaultFileService;

	@Autowired
	private ModuloService moduloService;

	@Autowired
	private PaginaService paginaService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private PrivilegioService privilegioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private OrganizacaoService organizacaoService;

	@Autowired
	private UsuarioOrganizacaoItemService usuarioOrganizacaoItemService;

	@Autowired
	private ConfiguracaoService configuracaoService;

	@Autowired
	private ConfiguracaoParametroSistemaService parametroSistemaService;

	@Autowired
	private WidgetService widgetService;

	/** Atributo widgetParametroService. */
	@Autowired
	private WidgetParametroService widgetParametroService;

	/** Atributo dominioJS. */
	private Dominio dominioJS;

	/**
	 * Método responsável por criar paginas e menus do modulo
	 *
	 * @author wilker.machado
	 *
	 */
	protected void criarPaginasMenus() {

		this.dominioJS = this.dominioService.findByChaveAndNome("tipoFile", "JS");

		// Menu pai Dashboards
		Menu menuDashboards = new Menu("Dashboards", null, null, null, 1, COR_PORTAL, CSS_MENU_PORTAL, OPACIDADE_MENU_PORTAL, CLASSE_PORTAL, ICONE_MENU_DASHBOARD);
		menuDashboards = this.menuService.mergeIfNotExist(menuDashboards);

		// Submenu Gráfico
		Menu menuGrafico = new Menu("Gráfico", null, menuDashboards, 0, 1, null, null, null, null);
		menuGrafico = this.menuService.mergeIfNotExist(menuGrafico);
		// Menu Dashboard
		Pagina pgDashboard = new Pagina("Dashboard", "/cit-portal-web/html/dashboard-portal.html");
		pgDashboard = this.paginaService.saveIfNotExist(pgDashboard);
		Menu menuDashboard = new Menu("Dashboard", pgDashboard, menuGrafico, null, 1, null, null, null, null);
		menuDashboard.setIncludes(this.gerarArquivosMenu(menuDashboard, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Dashboard", true, false, false));
		this.menuService.mergeIfNotExist(menuDashboard);

		// Submenu Configuração
		Menu menuConfiguracao = new Menu("Configuração", null, menuDashboards, 0, 2, null, null, null, null);
		menuConfiguracao = this.menuService.mergeIfNotExist(menuConfiguracao);
		// Menu Painel
		Pagina pgPainel = new Pagina("Painel", "/cit-portal-web/html/painel/painel.html");
		pgPainel = this.paginaService.saveIfNotExist(pgPainel);
		Menu menuPainel = new Menu("Painel", pgPainel, menuConfiguracao, null, 1, null, null, null, null);
		menuPainel.setIncludes(this.gerarArquivosMenu(menuPainel, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Painel", true, true, true));
		this.menuService.mergeIfNotExist(menuPainel);
		// Menu Widget
		Pagina pgWidget = new Pagina("Widget", "/cit-portal-web/html/widget/widget.html");
		pgWidget = this.paginaService.saveIfNotExist(pgWidget);
		Menu menuWidget = new Menu("Widget", pgWidget, menuConfiguracao, null, 2, null, null, null, null);
		List<MenuFile> filesMenuFileWidget = this.gerarArquivosMenu(menuWidget, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Widget", true, true, true);
		filesMenuFileWidget.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/WidgetParametroRepository.js", this.dominioJS, menuWidget));
		filesMenuFileWidget.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/WidgetParametroRepository.min.js", this.dominioJS, menuWidget));
		menuWidget.setIncludes(filesMenuFileWidget);
		this.menuService.mergeIfNotExist(menuWidget);


		// Menu pai Definições do sistema
		Menu menuDefinicoesSistema = new Menu("Definições do sistema", null, null, null, 2, COR_PORTAL, CSS_MENU_PORTAL, OPACIDADE_MENU_PORTAL, CLASSE_PORTAL, ICONE_MENU_DEFINICOES_DO_SISTEMA);
		List<MenuFile> filesMenuFileADMIN = new ArrayList<MenuFile>();
		filesMenuFileADMIN.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/PaginaUsuarioRepository.js", this.dominioJS, menuDefinicoesSistema));
		filesMenuFileADMIN.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/PaginaUsuarioRepository.min.js", this.dominioJS, menuDefinicoesSistema));
		filesMenuFileADMIN.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/FavoritoRepository.js", this.dominioJS, menuDefinicoesSistema));
		filesMenuFileADMIN.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/FavoritoRepository.min.js", this.dominioJS, menuDefinicoesSistema));
		filesMenuFileADMIN.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/FiltroRepository.js", this.dominioJS, menuDefinicoesSistema));
		filesMenuFileADMIN.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/FiltroRepository.min.js", this.dominioJS, menuDefinicoesSistema));

		menuDefinicoesSistema.setIncludes(filesMenuFileADMIN);
		menuDefinicoesSistema = this.menuService.mergeIfNotExist(menuDefinicoesSistema);

		// Submenu Geral
		Menu menuGeral = new Menu("Geral", null, menuDefinicoesSistema, 0, 1, null, null, null, null);
		menuGeral = this.menuService.mergeIfNotExist(menuGeral);
		// Menu Definições gerais
		Pagina pgConfiguracao = new Pagina("Definições gerais", "/cit-portal-web/html/configuracao/configuracao.html");
		pgConfiguracao = this.paginaService.saveIfNotExist(pgConfiguracao);
		Menu menuConfiguracaoSistema = new Menu("Definições gerais", pgConfiguracao, menuGeral, null, 1, null, null, null, null);
		List<MenuFile> filesMenuFileConfiguracaoSistema = this.gerarArquivosMenu(menuConfiguracaoSistema, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Configuracao", true, false, true);
		filesMenuFileConfiguracaoSistema.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/ConfiguracaoParametroSistemaRepository.js", this.dominioJS, menuConfiguracaoSistema));
		filesMenuFileConfiguracaoSistema.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/ConfiguracaoParametroSistemaRepository.min.js", this.dominioJS, menuConfiguracaoSistema));
		menuConfiguracaoSistema.setIncludes(filesMenuFileConfiguracaoSistema);
		menuConfiguracaoSistema = this.menuService.mergeIfNotExist(menuConfiguracaoSistema);
		// Menu Notificação
		Pagina pgNotificacao = new Pagina("Notificação", "/cit-portal-web/html/notificacao/notificacao.html");
		pgNotificacao = this.paginaService.saveIfNotExist(pgNotificacao);
		Menu menuNotificacao = new Menu("Notificação", pgNotificacao, menuGeral, null, 2, null, null, null, null);
		menuNotificacao.setIncludes(this.gerarArquivosMenu(menuNotificacao, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Notificacao", true, true, true));
		this.menuService.mergeIfNotExist(menuNotificacao);

		// Submenu Segurança
		Menu menuSeguranca = new Menu("Segurança", null, menuDefinicoesSistema, 0, 2, null, null, null, null);
		menuSeguranca = this.menuService.mergeIfNotExist(menuSeguranca);
		// Menu Usuário
		Pagina pgUsuario = new Pagina("Usuário", "/cit-portal-web/html/usuario/usuario.html");
		pgUsuario = this.paginaService.saveIfNotExist(pgUsuario);
		Menu menuUsuario = new Menu("Usuário", pgUsuario, menuSeguranca, null, 1, null, null, null, null);
		List<MenuFile> includesUsuario = this.gerarArquivosMenu(menuUsuario, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Usuario", true, true, true);
		includesUsuario.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/UsuarioOrganizacaoItemRepository.js", this.dominioJS, menuUsuario));
		includesUsuario.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/UsuarioOrganizacaoItemRepository.min.js", this.dominioJS, menuUsuario));
		menuUsuario.setIncludes(includesUsuario);
		this.menuService.mergeIfNotExist(menuUsuario);
		// Menu Grupo
		Pagina pgGrupo = new Pagina("Grupo", "/cit-portal-web/html/grupo/grupo.html");
		pgGrupo = this.paginaService.saveIfNotExist(pgGrupo);
		Menu menuGrupo = new Menu("Grupo", pgGrupo, menuSeguranca, null, 2, null, null, null, null);
		List<MenuFile> filesMenuFileGrupo = this.gerarArquivosMenu(menuGrupo, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Grupo", true, true, true);
		filesMenuFileGrupo.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/PrivilegioRepository.js", this.dominioJS, menuGrupo));
		filesMenuFileGrupo.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/PrivilegioRepository.min.js", this.dominioJS, menuGrupo));
		menuGrupo.setIncludes(filesMenuFileGrupo);
		this.menuService.mergeIfNotExist(menuGrupo);

		// Submenu Conteúdo
		Menu menuConteudo = new Menu("Conteúdo", null, menuDefinicoesSistema, 1, 3, null, null, null, null);
		menuConteudo = this.menuService.mergeIfNotExist(menuConteudo);
		// Menu Menu
		Pagina pgMenu = new Pagina("Menu", "/cit-portal-web/html/menu/menu.html");
		pgMenu = this.paginaService.saveIfNotExist(pgMenu);
		Menu menuMenu = new Menu("Menu", pgMenu, menuConteudo, null, 1, null, null, null, null);
		List<MenuFile> filesMenuFileMenu = this.gerarArquivosMenu(menuMenu, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Menu", true, false, true);
		filesMenuFileMenu.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/MenuFileRepository.js", this.dominioJS, menuMenu));
		filesMenuFileMenu.add(new MenuFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/MenuFileRepository.min.js", this.dominioJS, menuMenu));
		menuMenu.setIncludes(filesMenuFileMenu);
		this.menuService.mergeIfNotExist(menuMenu);
		// Menu Módulos
		Pagina pgModulo = new Pagina("Módulos", "/cit-portal-web/html/modulo/modulo.html");
		pgModulo = this.paginaService.saveIfNotExist(pgModulo);
		Menu menuModulo = new Menu("Módulos", pgModulo, menuConteudo, null, 2, null, null, null, null);
		menuModulo.setIncludes(this.gerarArquivosMenu(menuModulo, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Modulo", true, true, true));
		this.menuService.mergeIfNotExist(menuModulo);
		// Menu Arquivos padrão
		Pagina pgArquivoPadrao = new Pagina("Arquivos padrão", "/cit-portal-web/html/defaultFile/defaultFile.html");
		pgArquivoPadrao = this.paginaService.saveIfNotExist(pgArquivoPadrao);
		Menu menuArquivoPadrao = new Menu("Arquivos padrão", pgArquivoPadrao, menuConteudo, null, 3, null, null, null, null);
		menuArquivoPadrao.setIncludes(this.gerarArquivosMenu(menuArquivoPadrao, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "DefaultFile", true, false, true));
		this.menuService.mergeIfNotExist(menuArquivoPadrao);
		// Menu Internacionalização
		Pagina pgInternacionalizacao = new Pagina("Internacionalização", "/cit-portal-web/html/internacionalizacao/internacionalizacao.html");
		pgInternacionalizacao = this.paginaService.saveIfNotExist(pgInternacionalizacao);
		Menu menuInternacionalizacao = new Menu("Internacionalização", pgInternacionalizacao, menuConteudo, null, 4, null, null, null, null);
		menuInternacionalizacao.setIncludes(this.gerarArquivosMenu(menuInternacionalizacao, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Internacionalizacao", true, true, true));
		this.menuService.mergeIfNotExist(menuInternacionalizacao);

		// Submenu Tabelas de domínio
		Menu menuTabelasDominio = new Menu("Tabelas de domínio", null, menuDefinicoesSistema, 1, 5, null, null, null, null);
		menuTabelasDominio = this.menuService.mergeIfNotExist(menuTabelasDominio);
		// Menu Cadastro de domínios
		Pagina pgDominio = new Pagina("Cadastro de domínios", "/cit-portal-web/html/dominio/dominio.html");
		pgDominio = this.paginaService.saveIfNotExist(pgDominio);
		Menu menuDominio = new Menu("Cadastro de domínios", pgDominio, menuTabelasDominio, null, 1, null, null, null, null);
		menuDominio.setIncludes(this.gerarArquivosMenu(menuDominio, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Dominio", true, true, true));
		this.menuService.mergeIfNotExist(menuDominio);

		// Submenu Mapa
		Pagina pgMapa = new Pagina("Mapa", "/cit-portal-web/html/mapa/mapa.html");
		pgMapa = this.paginaService.saveIfNotExist(pgMapa);
		Menu menuMapa = new Menu("Mapa", pgMapa, menuDefinicoesSistema, 2, 6, null, null, null, null);
		menuMapa.setAtivo(Boolean.FALSE);
		this.menuService.mergeIfNotExist(menuMapa);

		// Submenu Access roles
		Pagina pgAccessRole = new Pagina("Access roles", "/cit-portal-web/html/accessRole/accessRole.html");
		pgAccessRole = this.paginaService.saveIfNotExist(pgAccessRole);
		Menu menuAccessRole = new Menu("Access roles", pgAccessRole, menuDefinicoesSistema, 2, 7, null, null, null, null);
		menuAccessRole.setAtivo(Boolean.FALSE);
		menuAccessRole.setIncludes(this.gerarArquivosMenu(menuAccessRole, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "AccessRole", true, true, true));
		this.menuService.mergeIfNotExist(menuAccessRole);

		// Submenu Home
		Pagina pgHome = new Pagina("Home", "/cit-portal-web/html/home/home.html");
		pgHome = this.paginaService.saveIfNotExist(pgHome);
		Menu menuHome = new Menu("Home", pgHome, menuDefinicoesSistema, 2, 8, null, null, null, null);
		menuHome.setAtivo(Boolean.FALSE);


	}

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 * @param menu
	 * @param caminho
	 * @param classe
	 * @param gerarController
	 * @param gerarListController
	 * @param gerarRepository
	 * @return
	 */
	private List<MenuFile> gerarArquivosMenu(Menu menu, String caminho, String classe, boolean gerarController, boolean gerarListController, boolean gerarRepository) {

		this.dominioJS = this.dominioService.findByChaveAndNome("tipoFile", "JS");

		List<MenuFile> result = new ArrayList<MenuFile>();

		MenuFile menuFileController = new MenuFile(caminho + "controller/" + classe + CONTROLLER_JS, this.dominioJS, menu);

		MenuFile menuFileListController = new MenuFile(caminho + "controller/" + classe + LIST_CONTROLLER_JS, this.dominioJS, menu);

		MenuFile menuFileRepository = new MenuFile(caminho + "repository/" + classe + REPOSITORY_JS, this.dominioJS, menu);

		MenuFile menuFileControllerMinify = new MenuFile(caminho + "controller/" + classe + CONTROLLER_MIN_JS, this.dominioJS, menu);

		MenuFile menuFileListControllerMinify = new MenuFile(caminho + "controller/" + classe + LIST_CONTROLLER_MIN_JS, this.dominioJS, menu);

		MenuFile menuFileRepositoryMinify = new MenuFile(caminho + "repository/" + classe + REPOSITORY_MIN_JS, this.dominioJS, menu);

		if (gerarController) {

			result.add(menuFileController);
			result.add(menuFileControllerMinify);
		}

		if (gerarListController) {

			result.add(menuFileListController);
			result.add(menuFileListControllerMinify);
		}

		if (gerarRepository) {

			result.add(menuFileRepository);
			result.add(menuFileRepositoryMinify);
		}

		return result;
	}

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 */
	protected void criarArquivosPadrao() {

		Dominio dominioCSS = this.dominioService.findByChaveAndNome("tipoFile", "CSS");

		this.dominioJS = this.dominioService.findByChaveAndNome("tipoFile", "JS");

		List<DefaultFile> list = new ArrayList<DefaultFile>();

		list.add(new DefaultFile("assets/css/bootstrap.min.css", dominioCSS, 0));
		list.add(new DefaultFile("assets/css/select.min.css", dominioCSS, 1));
		list.add(new DefaultFile("assets/css/select2.min.css", dominioCSS, 2));
		list.add(new DefaultFile("assets/css/angular-growl.min.css", dominioCSS, 3));
		list.add(new DefaultFile("assets/css/font-awesome.min.css", dominioCSS, 4));
		list.add(new DefaultFile("assets/css/angular-ui-tree.min.css", dominioCSS, 5));
		list.add(new DefaultFile("assets/css/textAngular.css", dominioCSS, 6));
		list.add(new DefaultFile("assets/css/bootstrap-layout.css", dominioCSS, 7));
		list.add(new DefaultFile("assets/css/layout.css", dominioCSS, 8));
		list.add(new DefaultFile("assets/js/jquery.min.js", this.dominioJS, 18));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "utils/functions.js", this.dominioJS, 19));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "utils/functions.min.js", this.dominioJS, 19));
		list.add(new DefaultFile("assets/js/bigInteger.js", this.dominioJS, 20));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "1.3.0/angular.min.js", this.dominioJS, 21));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "1.3.0/i18n/angular-locale_pt-br.js", this.dominioJS, 22));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "1.3.0/angular-cookies.min.js", this.dominioJS, 23));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "1.3.0/angular-route.min.js", this.dominioJS, 24));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "1.3.0/angular-sanitize.min.js", this.dominioJS, 25));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "1.3.0/angular-animate.min.js", this.dominioJS, 26));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "translate/angular-translate.js", this.dominioJS, 27));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "translate/angular-translate-storage-cookie.js", this.dominioJS, 28));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "translate/angular-translate-loader-partial.min.js", this.dominioJS, 29));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "directive/lodash.min.js", this.dominioJS, 30));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "restangular.min.js", this.dominioJS, 31));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "ui-bootstrap-tpls-0.12.0-custom.js", this.dominioJS, 32));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "select.min.js", this.dominioJS, 33));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "angular-growl.min.js", this.dominioJS, 34));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "angular-scroll.min.js", this.dominioJS, 35));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "ui-utils.js", this.dominioJS, 36));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "directive/angular-lazy-tree.js", this.dominioJS, 37));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "directive/angular-google-maps.min.js", this.dominioJS, 38));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "textAngular-rangy.min.js", this.dominioJS, 39));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "textAngular-sanitize.min.js", this.dominioJS, 40));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "textAngular.min.js", this.dominioJS, 41));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "angular-ui-tree.min.js", this.dominioJS, 42));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/MasksDirective.js", this.dominioJS, 43));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/MasksDirective.min.js", this.dominioJS, 43));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "utils/ArrayUtil.js", this.dominioJS, 44));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "utils/ArrayUtil.min.js", this.dominioJS, 44));
		list.add(new DefaultFile("assets/js/loading.js", this.dominioJS, 45));
		list.add(new DefaultFile("assets/js/moment.js", this.dominioJS, 45));
		list.add(new DefaultFile("assets/js/page-unload.js", this.dominioJS, 47));
		list.add(new DefaultFile("assets/js/modernizr-2.8.3.min.js", this.dominioJS, 48));
		list.add(new DefaultFile("assets/js/angular-file-upload.js", this.dominioJS, 48));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/jquery-ui/core.min.js", this.dominioJS, 49));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/jquery-ui/widget.min.js", this.dominioJS, 50));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/jquery-ui/mouse.min.js", this.dominioJS, 51));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/jquery-ui/sortable.min.js", this.dominioJS, 52));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/angular-ui-sortable/sortable.min.js", this.dominioJS, 53));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/adf.js", this.dominioJS, 54));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/provider.js", this.dominioJS, 55));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widget-content.js", this.dominioJS, 56));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widget.js", this.dominioJS, 57));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/dashboard.js", this.dominioJS, 58));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/column.js", this.dominioJS, 59));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/row.js", this.dominioJS, 60));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/structures.js", this.dominioJS, 61));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/news/news.js", this.dominioJS, 61));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/weather/weather.js", this.dominioJS, 62));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/linklist/linklist.js", this.dominioJS, 63));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/components/showdown/showdown.js", this.dominioJS, 64));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/components/angular-markdown-directive/markdown.js", this.dominioJS, 65));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/markdown/markdown.js", this.dominioJS, 66));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/components/highcharts/highcharts.js", this.dominioJS, 68));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/components/highcharts-ng/dist/highcharts-ng.js", this.dominioJS, 69));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets-sample/components/angular-google-chart/ng-google-chart.js", this.dominioJS, 69));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets/charts/highchart/highchartcit.js", this.dominioJS, 71));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets/charts/google-chart/googlechart.js", this.dominioJS, 72));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets/charts/google-chart-gauge/googlechartgauge.js", this.dominioJS, 72));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "dashboard-framework/widgets/bpe-task/bpetask.js", this.dominioJS, 73));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "app.js", this.dominioJS, 74));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "app.min.js", this.dominioJS, 74));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "controller/AppController.js", this.dominioJS, 75));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "controller/AppController.min.js", this.dominioJS, 75));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/AbstractRepository.js", this.dominioJS, 76));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/AbstractRepository.min.js", this.dominioJS, 76));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/PaginaUsuarioRepository.js", this.dominioJS, 77));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/PaginaUsuarioRepository.min.js", this.dominioJS, 77));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/UsuarioPatrimonioRepository.js", this.dominioJS, 79));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/UsuarioPatrimonioRepository.min.js", this.dominioJS, 79));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "controller/DefaultTemplateController.js", this.dominioJS, 83));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "controller/DefaultTemplateController.min.js", this.dominioJS, 83));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "controller/DefaultTemplateListController.js", this.dominioJS, 84));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "controller/DefaultTemplateListController.min.js", this.dominioJS, 84));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "filter/filter.js", this.dominioJS, 85));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "filter/filter.min.js", this.dominioJS, 85));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/passwordCheckDirective.js", this.dominioJS, 89));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/passwordCheckDirective.min.js", this.dominioJS, 89));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/onBlurChangeDirective.js", this.dominioJS, 90));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/onBlurChangeDirective.min.js", this.dominioJS, 90));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/MoneyDirective.js", this.dominioJS, 91));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/MoneyDirective.min.js", this.dominioJS, 91));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/IntegerDirective.js", this.dominioJS, 92));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/IntegerDirective.min.js", this.dominioJS, 92));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/onEnterBlurDirective.js", this.dominioJS, 93));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/onEnterBlurDirective.min.js", this.dominioJS, 93));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/DateDirective.js", this.dominioJS, 94));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/DateDirective.min.js", this.dominioJS, 94));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/SortByDirective.js", this.dominioJS, 95));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/SortByDirective.min.js", this.dominioJS, 95));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/FiltroDirective.js", this.dominioJS, 96));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/FiltroDirective.min.js", this.dominioJS, 96));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/DialogDirective.js", this.dominioJS, 97));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/DialogDirective.min.js", this.dominioJS, 97));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/FavoritoDirective.js", this.dominioJS, 98));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/FavoritoDirective.min.js", this.dominioJS, 98));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputDirective.js", this.dominioJS, 99));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputDirective.min.js", this.dominioJS, 99));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputCheckboxDirective.js", this.dominioJS, 100));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputCheckboxDirective.min.js", this.dominioJS, 100));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelSelectDirective.js", this.dominioJS, 101));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelSelectDirective.min.js", this.dominioJS, 101));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputNumberDirective.js", this.dominioJS, 102));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputNumberDirective.min.js", this.dominioJS, 102));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputDataDirective.js", this.dominioJS, 103));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputDataDirective.min.js", this.dominioJS, 103));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputMoneyDirective.js", this.dominioJS, 104));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputMoneyDirective.min.js", this.dominioJS, 104));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputRadioDirective.js", this.dominioJS, 105));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputRadioDirective.min.js", this.dominioJS, 105));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/AutoCompleteDirective.js", this.dominioJS, 109));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/AutoCompleteDirective.min.js", this.dominioJS, 109));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/AutoCompleteObrigatorioDirective.js", this.dominioJS, 110));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/AutoCompleteObrigatorioDirective.min.js", this.dominioJS, 110));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelTextAreaDirective.js", this.dominioJS, 111));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelTextAreaDirective.min.js", this.dominioJS, 111));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/TooltipDirective.js", this.dominioJS, 113));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/TooltipDirective.min.js", this.dominioJS, 113));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ChosenSelectDirective.js", this.dominioJS, 114));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ChosenSelectDirective.min.js", this.dominioJS, 114));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/DropdownDirective.js", this.dominioJS, 115));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/DropdownDirective.min.js", this.dominioJS, 115));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/PercentDirective.js", this.dominioJS, 116));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/PercentDirective.min.js", this.dominioJS, 116));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputPercentDirective.js", this.dominioJS, 117));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputPercentDirective.min.js", this.dominioJS, 117));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/CpfCnpjDirective.js", this.dominioJS, 118));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/CpfCnpjDirective.min.js", this.dominioJS, 118));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ButtonLockDirective.js", this.dominioJS, 119));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ButtonLockDirective.min.js", this.dominioJS, 119));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/HelpButtonDirective.js", this.dominioJS, 120));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/HelpButtonDirective.min.js", this.dominioJS, 120));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/PickListDirective.js", this.dominioJS, 121));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/PickListDirective.min.js", this.dominioJS, 121));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/IdentifierDirective.js", this.dominioJS, 122));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/IdentifierDirective.min.js", this.dominioJS, 122));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputIdentifierDirective.js", this.dominioJS, 123));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputIdentifierDirective.min.js", this.dominioJS, 123));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelColorSelectDirective.js", this.dominioJS, 124));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelColorSelectDirective.min.js", this.dominioJS, 124));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ListaStringDirective.js", this.dominioJS, 125));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ListaStringDirective.min.js", this.dominioJS, 125));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/OneWayDirective.js", this.dominioJS, 126));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/OneWayDirective.min.js", this.dominioJS, 126));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/BloquearDesbloquearDirective.js", this.dominioJS, 127));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/BloquearDesbloquearDirective.min.js", this.dominioJS, 127));
		list.add(new DefaultFile("assets/js/angular-br-filters.js", this.dominioJS, 128));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ListViewDirective.js", this.dominioJS, 129));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/ListViewDirective.min.js", this.dominioJS, 129));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/RegexValidate.js", this.dominioJS, 130));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/RegexValidate.min.js", this.dominioJS, 130));
		list.add(new DefaultFile("assets/js/uds_api_contents.js", this.dominioJS, 130));
		list.add(new DefaultFile("assets/js/jsapi.js", this.dominioJS, 131));
		list.add(new DefaultFile("assets/js/cnpj-validator.js", this.dominioJS, 132));
		list.add(new DefaultFile("assets/js/cpf-validator.js", this.dominioJS, 133));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputDecimalDirective.js", this.dominioJS, 134));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/LabelInputDecimalDirective.min.js", this.dominioJS, 134));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "checklist-model.js", this.dominioJS, 135));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "ZeroClipboard.min.js", this.dominioJS, 136));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_LIB + "ng-clip.min.js", this.dominioJS, 137));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/TimelineHorizontalDirective.js", this.dominioJS, 138));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/TimelineHorizontalDirective.min.js", this.dominioJS, 138));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/FontAwesomeDirective.js", this.dominioJS, 139));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/FontAwesomeDirective.min.js", this.dominioJS, 139));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/BreadcrumbDirective.js", this.dominioJS, 140));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/BreadcrumbDirective.min.js", this.dominioJS, 140));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/MenuDirective.js", this.dominioJS, 141));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "directive/MenuDirective.min.js", this.dominioJS, 141));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/IndexRepository.js", this.dominioJS, 142));
		list.add(new DefaultFile(CIT_PORTAL_WEB_ANGULAR_CUSTOM + "repository/IndexRepository.min.js", this.dominioJS, 142));

		this.defaultFileService.saveListIfNotExist(list);
	}

	/**
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 */
	protected void criarDominios() {

		List<Dominio> list = new ArrayList<Dominio>();

		list.add(new Dominio("tipoFile", "css", "CSS", 1L));
		list.add(new Dominio("tipoFile", "js", "JS", 2L));
		list.add(new Dominio("tipoClassificacaoMaterial", "Classe", "CLASSE", 0L));
		list.add(new Dominio("tipoClassificacaoMaterial", "Grupo", "GRUPO", 1L));
		list.add(new Dominio("tipoClassificacaoMaterial", "Subgrupo", "SUB_GRUPO", 2L));
		list.add(new Dominio("tipoClassificacaoMaterial", "Material", "MATERIAL", 3L));
		list.add(new Dominio("tipoClassificacaoMaterial", "Detalhe", "DETALHE", 4L));
		list.add(new Dominio("tipoMaterial", "Consumo", "CONSUMO", 1L));
		list.add(new Dominio("tipoMaterial", "Permanente", "PERMANENTE", 2L));
		list.add(new Dominio("tipoEndereco", "Residencial", "RESIDENCIAL", 1L));
		list.add(new Dominio("tipoEndereco", "Comercial", "COMERCIAL", 2L));
		list.add(new Dominio("tipoRestricao", "nenhum", "NENHUM", 1L));
		list.add(new Dominio("tipoRestricao", "por material", "POR_MATERIAL", 2L));
		list.add(new Dominio("tipoRestricao", "geral", "GERAL", 3L));
		list.add(new Dominio("tipoDado", "Texto curto", "TEXT_FIELD", 1L));
		list.add(new Dominio("tipoDado", "Texto longo", "TEXT_AREA", 2L));
		list.add(new Dominio("tipoDado", "Numérico", "NUMBER", 3L));
		list.add(new Dominio("tipoDado", "Valor", "DECIMAL", 4L));
		list.add(new Dominio("tipoDado", "Data", "DATA", 5L));
		list.add(new Dominio("tipoDado", "Tabela tipo domínio", "TIPO_DOMINIO", 6L));
		list.add(new Dominio("tipoDado", "Arquivo", "ARQUIVO", 7L));
		list.add(new Dominio("tipoDado", "Lógico", "LOGICO", 8L));
		list.add(new Dominio("tipoDado", "URL de Serviço", "URL_SERVICO", 9L));
		list.add(new Dominio("tipoDado", "Boolean", "BOOLEAN", 10L));
		list.add(new Dominio("tipoDocumento", "Empenho", "EMPENHO", 1L));
		list.add(new Dominio("tipoDocumento", "Processo de Entrada", "PROCESSO_ENTRADA", 2L));
		list.add(new Dominio("tipoDocumento", "Nota Fiscal", "NOTA_FISCAL", 3L));
		list.add(new Dominio("tipoDocumento", "Termo de doação", "TERMO_DOACAO", 4L));
		list.add(new Dominio("tipoDocumento", "Termo de cessão", "TERMO_CESSAO", 5L));
		list.add(new Dominio("tipoDocumento", "Número do Processo", "N_PROCESSO", 6L));
		list.add(new Dominio("tipoDocumento", "Memorando", "MEMORANDO", 7L));
		list.add(new Dominio("tipoDocumento", "Despacho", "DESPACHO", 8L));
		list.add(new Dominio("tipoPessoa", "Física", "FISICA", 1L));
		list.add(new Dominio("tipoPessoa", "Jurídica", "JURIDICA", 2L));
		list.add(new Dominio("tipoTelefone", "Comercial", "COMERCIAL", 1L));
		list.add(new Dominio("tipoTelefone", "Residencial", "RESIDENCIAL", 2L));
		list.add(new Dominio("tipoTelefone", "Celular", "CELULAR", 3L));
		list.add(new Dominio("tipoBem", "Próprio contabilizado", "PROPRIO_CONTABILIZADO", 1L));
		list.add(new Dominio("tipoBem", "Próprio controlado", "PROPRIO_CONTROLADO", 2L));
		list.add(new Dominio("tipoBem", "De terceiros", "DE_TERCEIROS", 3L));
		list.add(new Dominio("tipoEntrada", "Orçamentária", "ORCAMENTARIA", 1L));
		list.add(new Dominio("tipoEntrada", "Extra orçamentária", "EXTRA_ORCAMENTARIA", 2L));
		list.add(new Dominio("tipoStatusBem", "Utilizado", "UTILIZADO", 1L));
		list.add(new Dominio("tipoStatusBem", "Baixado", "BAIXADO", 2L));
		list.add(new Dominio("tipoStatusBem", "Disponível", "DISPONIVEL", 3L));
		list.add(new Dominio("tipoStatusBem", "Em processo de baixa", "EM_PROCESSO_DE_BAIXA", 4L));
		list.add(new Dominio("tipoStatusBem", "Em processo de transferência", "EM_PROCESSO_DE_TRANSFERENCIA", 5L));
		list.add(new Dominio("tipoStatusBem", "Em saída temporária", "EM_SAIDA_TEMPORARIA", 6L));
		list.add(new Dominio("tipoStatusBem", "Extraviado", "EXTRAVIADO", 7L));
		list.add(new Dominio("tipoStatusBem", "Indisponível", "INDISPONIVEL", 8L));
		list.add(new Dominio("tipoStatusBem", "Sindicância", "SINDICANCIA", 9L));
		list.add(new Dominio("tipoStatusBem", "Não localizado", "TIPO_STATUS_BEM_NAO_LOCALIZADO", 10L));
		list.add(new Dominio("tipoPrioridade", "Baixo", "BAIXO", 1L));
		list.add(new Dominio("tipoPrioridade", "Médio", "MEDIO", 2L));
		list.add(new Dominio("tipoPrioridade", "Alto", "ALTO", 3L));
		list.add(new Dominio("tipoNotificacao", "Patrimônio", "MODULO_PATRIMONIO", 1L));
		list.add(new Dominio("tipoNotificacao", "Almoxarifado", "MODULO_ALMOXARIFADO", 2L));
		list.add(new Dominio("tipoNotificacao", "BPE/ESI", "MODULO_ESI", 3L));
		list.add(new Dominio("tipoSexo", "Masculino", "MASCULINO", 1L));
		list.add(new Dominio("tipoSexo", "Feminino", "FEMININO", 2L));
		list.add(new Dominio("tipoEstadoCivil", "Solteiro", "SOLTEIRO", 1L));
		list.add(new Dominio("tipoEstadoCivil", "Casado", "CASADO", 2L));
		list.add(new Dominio("tipoEstruturaOrganizacional", "Unidade gestora", "UNIDADE_GESTORA", 1L));
		list.add(new Dominio("tipoEstruturaOrganizacional", "Unidade administrativa", "UNIDADE_ADMINISTRATIVA", 2L));
		list.add(new Dominio("tipoEstruturaOrganizacional", "Unidade localizadora", "UNIDADE_LOCALIZADORA", 3L));
		list.add(new Dominio("tipoEstruturaOrganizacional", "Unidade requisitante", "UNIDADE_REQUISITANTE", 4L));
		list.add(new Dominio("tipoAbrangencia", "Local", "LOCAL", 1L));
		list.add(new Dominio("tipoAbrangencia", "Nacional", "NACIONAL", 2L));
		list.add(new Dominio("tipoAbrangencia", "Regional", "REGIONAL", 3L));
		list.add(new Dominio("tipoPorte", "Micro empresa", "MICRO_EMPRESA", 1L));
		list.add(new Dominio("tipoPorte", "Pequena", "PEQUENA", 2L));
		list.add(new Dominio("tipoPorte", "Media", "MEDIA", 3L));
		list.add(new Dominio("tipoPorte", "Grande", "GRANDE", 4L));
		list.add(new Dominio("tipoFornecedor", "Juridica", "JURIDICA", 1L));
		list.add(new Dominio("tipoModalidadeTransferencia", "Permanente", "PERMANENTE", 1L));
		list.add(new Dominio("tipoModalidadeTransferencia", "Temporária", "TEMPORARIA", 2L));
		list.add(new Dominio("tipoObjetivoTransferencia", "Comodato", "COMODATO", 1L));
		list.add(new Dominio("tipoObjetivoTransferencia", "Conserto", "CONSERTO", 2L));
		list.add(new Dominio("tipoObjetivoTransferencia", "Empréstimo", "EMPRESTIMO", 3L));
		list.add(new Dominio("tipoObjetivoTransferencia", "Evento", "EVENTO", 4L));
		list.add(new Dominio("tipoObjetivoTransferencia", "Manutenção", "MANUTENCAO", 5L));
		list.add(new Dominio("tipoUnidadeMedida", "Unidade", "UNIDADE", 1L));
		list.add(new Dominio("tipoUnidadeMedida", "Caixa", "CAIXA", 2L));
		list.add(new Dominio("tipoUnidadeMedida", "Pacote", "PACOTE", 3L));
		list.add(new Dominio("tipoUnidadeMedida", "Galão", "GALAO", 4L));
		list.add(new Dominio("tipoAnexo", "application/xml", "XML", 1L));
		list.add(new Dominio("tipoAnexo", "image/jpeg", "JPG", 2L));
		list.add(new Dominio("tipoAnexo", "image/jpeg", "JPEG", 3L));
		list.add(new Dominio("tipoAnexo", "image/png", "PNG", 4L));
		list.add(new Dominio("tipoAnexo", "application/octet-stream", "DOC", 5L));
		list.add(new Dominio("tipoAnexo", "application/octet-stream", "XLS", 6L));
		list.add(new Dominio("tipoAnexo", "application/pdf", "PDF", 7L));
		list.add(new Dominio("tipoAnexo", "image/bmp", "BMP", 8L));
		list.add(new Dominio("tipoAnexo", "application/octet-stream", "XLSX", 9L));
		list.add(new Dominio("tipoAnexo", "application/octet-stream", "DOCX", 10L));
		list.add(new Dominio("tipoAnexo", "application/octet-stream", "ODT", 11L));
		list.add(new Dominio("tipoAnexo", "text/plain", "TXT", 12L));
		list.add(new Dominio("tipoAnexo", "application/octet-stream", "SEM_EXTENSAO", 13L));
		list.add(new Dominio("tipoBaixa", "Venda", "VENDA", 1L));
		list.add(new Dominio("tipoBaixa", "Doação", "DOACAO", 2L));
		list.add(new Dominio("tipoBaixa", "Cessão de uso", "CESSAO_DE_USO", 3L));
		list.add(new Dominio("tipoBaixa", "Extravio", "EXTRAVIO", 4L));
		list.add(new Dominio("tipoBaixa", "Permuta", "PERMUTA", 5L));
		list.add(new Dominio("tipoBaixa", "Transferência fora do sistema", "TRANSFERENCIA_FORA_SISTEMA", 6L));
		list.add(new Dominio("tipoBaixa", "Cadastramento indevido", "CADASTRAMENTO_INDEVIDO", 7L));
		list.add(new Dominio("tipoBaixa", "Mudança Permanente/Consumo", "MUDANCA_PERMANENTE_CONSUMO", 8L));
		list.add(new Dominio("tipoBaixa", "Roubo ou furto", "ROUBO_OU_FURTO", 9L));
		list.add(new Dominio("tipoBaixa", "Outros motivos", "OUTROS_MOTIVOS", 10L));
		list.add(new Dominio("tipoBaixa", "Leilão", "LEILAO", 11L));
		list.add(new Dominio("tipoBaixa", "Outras diversas", "OUTROS_DIVERSAS", 12L));
		list.add(new Dominio("tipoBaixa", "Recuperável", "RECUPERAVEL", 13L));
		list.add(new Dominio("tipoBaixa", "Despacho", "DESPACHO", 14L));
		list.add(new Dominio("tipoBaixa", "Cessão", "CESSAO", 15L));
		list.add(new Dominio("tipoSituacaoBaixa", "Em andamento", "EM_ANDAMENTO", 1L));
		list.add(new Dominio("tipoSituacaoBaixa", "Autorizada", "AUTORIZADA", 2L));
		list.add(new Dominio("tipoSituacaoBaixa", "Não autorizada", "NAO_AUTORIZADA", 3L));
		list.add(new Dominio("tipoObjetivoSaidaTemporaria", "Comodato", "COMODATO", 1L));
		list.add(new Dominio("tipoObjetivoSaidaTemporaria", "Conserto", "CONSERTO", 2L));
		list.add(new Dominio("tipoObjetivoSaidaTemporaria", "Empréstimo", "EMPRESTIMO", 3L));
		list.add(new Dominio("tipoObjetivoSaidaTemporaria", "Evento", "EVENTO", 4L));
		list.add(new Dominio("tipoProjetoSaidaTemporaria", "Outro", "OUTRO", 1L));
		list.add(new Dominio("tipoContaContabil", "Resultado", "RESULTADO", 1L));
		list.add(new Dominio("tipoContaContabil", "Receita", "RECEITA", 2L));
		list.add(new Dominio("tipoContaContabil", "Despesa", "DESPESA", 3L));
		list.add(new Dominio("tipoContaContabil", "Passivo", "PASSIVO", 4L));
		list.add(new Dominio("tipoContaContabil", "Ativo", "ATIVO", 5L));
		list.add(new Dominio("tipoDepreciacao", "Linear", "LINEAR", 1L));
		list.add(new Dominio("tipoAlteracaoBemPatrimonial", "Período de garantia", "PERIODO_GARANTIA", 1L));
		list.add(new Dominio("tipoAlteracaoBemPatrimonial", "Situação física", "SITUACAO_FISICA", 2L));
		list.add(new Dominio("tipoAlteracaoBemPatrimonial", "Status do bem", "STATUS_BEM", 3L));
		list.add(new Dominio("tipoAlteracaoBemPatrimonial", "Número patrimonial", "NUMERO_PATRIMONIAL", 4L));
		list.add(new Dominio("tipoAlteracaoBemPatrimonial", "Reavaliação", "REAVALIACAO", 5L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Ajuste de dados", "AJUSTE_DE_DADOS", 1L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Atualização monetária", "ATUALIZACAO_MONETARIA", 2L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Reavaliação", "REAVALIACAO", 3L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Inventário", "INVENTARIO", 4L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Alteração devida a depreciação", "DEPRECIACAO", 5L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Incorporação de bem patrimonial", "INCORPORACAO_BEM_PATRIMONIAL", 6L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Início do processo de baixa de bens", "INICIO_PROCESSO_BAIXA", 7L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Processo de baixa de bens concluído", "PROCESSO_DE_BAIXA_AUTORIZADO", 8L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Processo de baixa de bens não autorizado", "PROCESSO_DE_BAIXA_NAO_AUTORIZADO", 9L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Atualização do status na baixa", "ATUALIZACAO_STATUS_BAIXA", 10L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Transferência por ocasião de atribuição a bem principal", "TRANSFERENCIA_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL", 11L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Transferência entre estruturas organizacionais", "TRANSFERENCIA_ENTRE_ESTRUTURAS_ORGANIZACIONAIS", 12L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Atribuição de novo detentor por ocasião de atribuição a bem principal", "ATRIBUICAO_DE_NOVO_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL", 13L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Atribuição de novo responsável por ocasião de atribuição a bem principal", "ATRIBUICAO_DE_NOVO_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL", 14L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Atribuição a um bem principal", "ATRIBUICAO_A_UM_BEM_PRINCIPAL", 15L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Atribuição de bens filhos", "ATRIBUICAO_DE_BENS_FILHOS", 16L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Estorno da baixa", "ESTORNO_BAIXA", 17L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Estorno do item da baixa", "ESTORNO_ITEM_BAIXA", 18L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Exclusão da baixa", "EXCLUIR_BAIXA", 19L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Exclusão do item da baixa", "EXCLUIR_ITEM_BAIXA", 20L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Definição detentor", "DEFINICAO_DETENTOR", 21L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Exclusão de um bem vinculado", "EXCLUSAO_DE_UM_BEM_VINCULADO", 22L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Exclusão de vinculo a bem principal", "EXCLUSAO_DE_VINCULO_A_BEM_PRINCIPAL", 23L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Exclusão do detentor por ocasião da exclusão do vínculo com o bem principal", "EXCLUSAO_DO_DETENTOR_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL", 24L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Exclusão do responsável por ocasião da exclusão do vínculo com o bem principal", "EXCLUSAO_DO_RESPONSAVEL_POR_OCASIAO_DA_EXCLUSAO_DO_VINCULO_COM_O_BEM_PRINCIPAL", 25L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Definição responsável", "TIPO_MOTIVO_ALTERACAO_BEM_DEFINICAO_NOVO_RESPONSAVEL_CODIGO", 26L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Saída temporária", "TIPO_MOTIVO_ALTERACAO_BEM_EM_SAIDA_TEMPORARIA", 27L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Transferência por ocasião de definição de novo detentor", "TIPO_MOTIVO_ALTERACAO_BEM_TRANSFERENCIA_POR_OCASIAO_DE_DEFINICAO_DE_NOVO_DETENTOR", 28L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Remoção de Detentor por ocasião de atribuição a Bem Principal", "TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_DETENTOR_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL", 29L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Remoção de Responsável por ocasião de atribuição a Bem Principal", "TIPO_MOTIVO_ALTERACAO_BEM_REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_A_BEM_PRINCIPAL", 30L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Retorno da saída temporária", "RETORNO_SAIDA_TEMPORARIA", 31L));
		list.add(new Dominio("tipoMotivoAlteracaoBem", "Remoção de Responsável por ocasião de atribuição de detentor que não é responsável pelo bem", "REMOCAO_DE_RESPONSAVEL_POR_OCASIAO_DE_ATRIBUICAO_DE_DETENTOR_QUE_NAO_E_RESPONSAVEL_PELO_BEM", 32L));
		list.add(new Dominio("tipoSituacaoFisica", "Bom", "BOM", 1L));
		list.add(new Dominio("tipoSituacaoFisica", "Regular", "REGULAR", 2L));
		list.add(new Dominio("tipoSituacaoFisica", "Inservível", "INSERVIVEL", 3L));
		list.add(new Dominio("tipoSituacaoFisica", "Precário", "PRECARIO", 4L));
		list.add(new Dominio("tipoSituacaoFisica", "Ruim", "RUIM", 5L));
		list.add(new Dominio("tipoSituacaoFisica", "Antieconômico", "ANTIECONOMICO", 6L));
		list.add(new Dominio("tipoSituacaoFisica", "Danificado", "DANIFICADO", 7L));
		list.add(new Dominio("tipoSituacaoFisica", "Obsoleto", "OBSOLETO", 8L));
		list.add(new Dominio("tipoSituacaoFisica", "Ocioso", "OCIOSO", 9L));
		list.add(new Dominio("tipoSituacaoFisica", "Recuperável", "RECUPERAVEL", 10L));
		list.add(new Dominio("tipoOperacao", "Alteração de status", "ATUALIZACAO_STATUS", 1L));
		list.add(new Dominio("tipoOperacao", "Processo de sindicância", "PROCESSO_SINDICANCIA", 2L));
		list.add(new Dominio("tipoHistorico", "Alteração", "ALTERACAO", 1L));
		list.add(new Dominio("tipoHistorico", "Entrada", "HISTORICO_ENTRADA", 2L));
		list.add(new Dominio("tipoHistorico", "Baixa", "HISTORICO_BAIXA", 3L));
		list.add(new Dominio("tipoHistorico", "Adição a bem principal", "ADICAO_A_BEM_PRINCIPAL", 4L));
		list.add(new Dominio("tipoHistorico", "Transferência interna", "TRANSFERENCIA_INTERNA", 5L));
		list.add(new Dominio("tipoHistorico", "Atribuir detentor", "TIPO_HISTORICO_ATRIBUIR_DETENTOR", 6L));
		list.add(new Dominio("tipoStatusDepreciacao", "Depreciável", "DEPRECIAVEL", 1L));
		list.add(new Dominio("tipoStatusDepreciacao", "Armazenado no Almoxarifado", "NAO_DEPRECIAVEL_ALMOXARIFADO", 2L));
		list.add(new Dominio("tipoStatusDepreciacao", "Depreciação finalizada", "NAO_DEPRECIAVEL_VALOR_RESIDUAL", 3L));
		list.add(new Dominio("tipoStatusDepreciacao", "Não Depreciável", "NAO_DEPRECIAVEL_MARCACAO_USUARIO", 4L));
		list.add(new Dominio("tipoStatusDepreciacao", "Não Depreciado em exercício anterior", "NAO_DEPRECIADO_EXERCICIO_ANTERIOR", 5L));
		list.add(new Dominio("tipoMovimentoContaContabil", "Entrada orçamentária", "ENTRADA_ORCAMENTARIA", 1L));
		list.add(new Dominio("tipoMovimentoContaContabil", "Baixa orçamentária", "BAIXA_ORCAMENTARIA", 2L));
		list.add(new Dominio("tipoMovimentoContaContabil", "Entrada extra orçamentária", "ENTRADA_EXTRAORCAMENTARIA", 3L));
		list.add(new Dominio("tipoMovimentoContaContabil", "Baixa extra orçamentária", "BAIXA_EXTRAORCAMENTARIA", 4L));
		list.add(new Dominio("tipoMovimentacao", "Transferência interna", "TRANSFERENCIA_INTERNA", 1L));
		list.add(new Dominio("tipoMovimentacao", "Definição detentor", "DEFINICAO_DETENTOR", 2L));
		list.add(new Dominio("tipoMovimentacao", "Pedido por colaborador detentor", "PEDIDO_DETENTOR", 3L));
		list.add(new Dominio("tipoMovimentacao", "Pedido por U.A. ou U.L.", "PEDIDO_UNIDADE", 4L));
		list.add(new Dominio("tipoRecebimento", "Compra", "COMPRA", 1L));
		list.add(new Dominio("tipoRecebimento", "Compra Extra Orçamentária", "COMPRA_EXTRA_ORCAMENTARIA", 2L));
		list.add(new Dominio("tipoRecebimento", "Doação", "DOACAO", 3L));
		list.add(new Dominio("tipoRecebimento", "Cessão de uso", "CESSAO_USO", 4L));
		list.add(new Dominio("tipoRecebimento", "Transferência fora do sistema", "TRANSFERENCIA_FORA_SISTEMA", 5L));
		list.add(new Dominio("tipoRecebimento", "Reposição", "REPOSICAO", 6L));
		list.add(new Dominio("tipoRecebimento", "Incorporação", "INCORPORACAO", 7L));
		list.add(new Dominio("tipoRecebimento", "Entrada por decisão judicial", "ENTRADA_DECISAO_JUDICIAL", 8L));
		list.add(new Dominio("tipoRecebimento", "Resto a pagar", "RESTO", 9L));
		list.add(new Dominio("tipoRecebimento", "Cessão", "CESSAO", 10L));
		list.add(new Dominio("tipoRecebimento", "Aquisição extra orçamentária/multa", "AQUISICAO_EXTRA_ORCAMENTARIA_MULTA", 11L));
		list.add(new Dominio("tipoFeriado", "Data", "DATA", 1L));
		list.add(new Dominio("tipoFeriado", "Período", "PERIODO", 2L));
		list.add(new Dominio("abrangenciaFeriado", "Mundial", "MUNDIAL", 1L));
		list.add(new Dominio("abrangenciaFeriado", "Nacional", "NACIONAL", 2L));
		list.add(new Dominio("abrangenciaFeriado", "Estadual", "ESTADUAL", 3L));
		list.add(new Dominio("abrangenciaFeriado", "Municipal", "MUNICIPAL", 4L));
		list.add(new Dominio("tipoExcecaoFeriado", "Folga", "FOLGA", 1L));
		list.add(new Dominio("tipoExcecaoFeriado", "Trabalho", "TRABALHO", 2L));
		list.add(new Dominio("tipoWidget", "HighChart", "highchartcit", 1L));
		list.add(new Dominio("tipoWidget", "Link", "link", 2L));
		list.add(new Dominio("tipoWidget", "Temperatura", "temperatura", 3L));
		list.add(new Dominio("tipoWidget", "Notícia", "noticia", 4L));
		list.add(new Dominio("tipoWidget", "Google Chart", "googlechartcit", 5L));
		list.add(new Dominio("tipoWidget", "Google Chart Gauge", "googlechartgaugecit", 6L));
		list.add(new Dominio("tipoWidget", "Html", "markdown", 7L));
		list.add(new Dominio("tipoWidget", "Tarefa BPE", "bpetask", 8L));
		list.add(new Dominio("tipoHighChart", "Pizza", "pie", 1L));
		list.add(new Dominio("tipoHighChart", "Linha", "line", 2L));
		list.add(new Dominio("tipoHighChart", "Barra", "bar", 3L));
		list.add(new Dominio("tipoHighChart", "Coluna", "column", 4L));
		list.add(new Dominio("tipoGoogleChart", "AreaChart", "AreaChart", 1L));
		list.add(new Dominio("tipoGoogleChart", "PieChart", "PieChart", 2L));
		list.add(new Dominio("tipoGoogleChart", "ColumnChart", "ColumnChart", 3L));
		list.add(new Dominio("tipoGoogleChart", "LineChart", "LineChart", 4L));
		list.add(new Dominio("tipoGoogleChart", "Table", "Table", 5L));
		list.add(new Dominio("tipoGoogleChart", "BarChart", "BarChart", 6L));
		list.add(new Dominio("tipoIdioma", "Português", "pt_BR", 1L));
		list.add(new Dominio("tipoIdioma", "English", "en_US", 2L));
		list.add(new Dominio("tipoIdioma", "Español", "es_ES", 3L));
		list.add(new Dominio("tipoPerspectiva", "Financeiro", "FINANCEIRO", 1L));
		list.add(new Dominio("tipoPerspectiva", "Operacional", "OPERACIONAL", 2L));
		list.add(new Dominio("tipoPerspectiva", "Cliente", "CLIENTE", 3L));
		list.add(new Dominio("tipoPerspectiva", "Recursos Humanos", "RECURSOSHUMANOS", 4L));
		list.add(new Dominio("tipoAnalise", "Planejamento Estratégico", "PLANEJAMENTOESTRATEGICO", 1L));
		list.add(new Dominio("tipoAnalise", "Controladoria", "CONTROLADORIA", 2L));
		list.add(new Dominio("tipoAnalise", "Qualidade", "QUALIDADE", 3L));
		list.add(new Dominio("tipoAnalise", "Legal", "LEGAL", 4L));
		list.add(new Dominio("tipoAnalise", "Contratual", "CONTRATUAL", 5L));
		list.add(new Dominio("tipoAnalise", "Documental", "DOCUMENTAL", 6L));
		list.add(new Dominio("tipoColeta", "Manual", "MANUAL", 1L));
		list.add(new Dominio("tipoColeta", "Automática", "AUTOMATICA", 2L));
		list.add(new Dominio("tipoAtribuicao", "Responsável pelo centro de resultado", "RESPONSAVEL", 1L));
		list.add(new Dominio("tipoAtribuicao", "Qualquer membro do grupo", "GRUPO", 2L));
		list.add(new Dominio("tipoCondicao", "Nenhuma", "NENHUMA", 1L));
		list.add(new Dominio("tipoCondicao", "Se anterior for executado", "ANTERIOR_EXECUTADO", 2L));
		list.add(new Dominio("tipoCondicao", "Após agendamento anterior", "APOS_AGENDAMENTO_ANTERIOR", 3L));
		list.add(new Dominio("tipoReiteracao", "Até a execução", "ATE_EXECUCAO", 1L));
		list.add(new Dominio("tipoReiteracao", "Até o final do período", "FINAL_PERIODO", 2L));
		list.add(new Dominio("tipoReiteracao", "Quantidade definida", "QUANTIDADE_DEFINIDA", 3L));
		list.add(new Dominio("tipoDestino", "Responsáveis", "RESPONSAVEIS", 1L));
		list.add(new Dominio("tipoDestino", "Superiores", "SUPERIORES", 2L));
		list.add(new Dominio("tipoDisparo", "No atraso", "ATRASO", 1L));
		list.add(new Dominio("tipoDisparo", "No criação", "CRIACAO", 2L));
		list.add(new Dominio("tipoDisparo", "No Execução", "EXECUCAO", 3L));
		list.add(new Dominio("tipoRepeticao", "Até a execução", "EXECUCAO", 1L));
		list.add(new Dominio("tipoRepeticao", "Nenhuma", "NENHUMA", 2L));
		list.add(new Dominio("tipoRepeticao", "Até o fim do agendamento", "FIM_AGENDAMENTO", 3L));
		list.add(new Dominio("tipoIntercorrencia", "Atividade não entregue", "NAO_ENTREGUE", 1L));
		list.add(new Dominio("tipoImplementacao", "SQL", "SQL", 1L));
		list.add(new Dominio("tipoImplementacao", "JavaScript", "JAVASCRIPT", 2L));
		list.add(new Dominio("tipoImplementacao", "Classe Java", "CLASSE_JAVA", 3L));
		list.add(new Dominio("tipoPapelEquipeContratos", "Gestor", "GESTOR", 1L));
		list.add(new Dominio("tipoPapelEquipeContratos", "Gestor substituto", "GESTOR_SUBSTITUTO", 2L));
		list.add(new Dominio("tipoPapelEquipeContratos", "Fiscal técnico", "FISCAL_TECNICO", 3L));
		list.add(new Dominio("tipoPapelEquipeContratos", "Fiscal administrativo", "FISCAL_ADMINISTRATIVO", 4L));
		list.add(new Dominio("tipoPapelEquipeContratos", "Fiscal solicitante", "FISCAL_SOLICITANTE", 5L));
		list.add(new Dominio("tipoAquisicao", "Serviço", "SERVICO", 1L));
		list.add(new Dominio("tipoAquisicao", "Material", "MATERIAL", 2L));
		list.add(new Dominio("tipoAquisicao", "Material e serviço", "MATERIAL_SERVICO", 3L));
		list.add(new Dominio("tipoSituacaoContrato", "Em execução", "EM_EXECUCAO", 1L));
		list.add(new Dominio("tipoSituacaoContrato", "Concluído", "CONCLUIDO", 2L));
		list.add(new Dominio("tipoSituacaoContrato", "Suspenso", "SUSPENSO", 3L));
		list.add(new Dominio("tipoSituacaoContrato", "Rescindido", "RESCINDIDO", 4L));
		list.add(new Dominio("tipoContrato", "Administrativo", "ADMINISTRATIVO", 1L));
		list.add(new Dominio("tipoContrato", "Por Empenho", "POR_EMPENHO", 2L));
		list.add(new Dominio("tipoContrato", "Por Modalidade", "POR_MODALIDADE", 3L));
		list.add(new Dominio("tipoModalidadeContrato", "Concorrência", "CONCORRENCIA", 1L));
		list.add(new Dominio("tipoModalidadeContrato", "Convite", "CONVITE", 2L));
		list.add(new Dominio("tipoModalidadeContrato", "Tomada de Preço", "TOMADA_PRECO", 3L));
		list.add(new Dominio("tipoModalidadeContrato", "Concurso", "CONCURSO", 4L));
		list.add(new Dominio("tipoModalidadeContrato", "Pregão", "PREGAO", 5L));
		list.add(new Dominio("tipoGarantia", "Contratual", "CONTRATUAL", 1L));
		list.add(new Dominio("tipoGarantia", "Fornecedor", "FORNECEDOR", 2L));
		list.add(new Dominio("tipoModalidadeGarantia", "Calção em dinheiro ou títulos da dívida pública", "CALCAO_DINHEIRO_TITULOS", 1L));
		list.add(new Dominio("tipoModalidadeGarantia", "Seguro-garantia", "SEGURO_GARANTIA", 2L));
		list.add(new Dominio("tipoModalidadeGarantia", "Fiança bancária", "FIANCA_BANCARIA", 3L));
		list.add(new Dominio("tipoRespostaQuestao", "Texto livre", "TEXTO_LIVRE", 1L));
		list.add(new Dominio("tipoRespostaQuestao", "Múltipla Escolha", "MULTIPLA_ESCOLHA", 2L));
		list.add(new Dominio("tipoRespostaQuestao", "Booleana", "BOOLEAN", 3L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Apostilamento", "TIPO_OCORRENCIA_APOSTILAMENTO_EXECUCAO", 1L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Termo aditivo", "TIPO_OCORRENCIA_CADASTRO_TERMO_ADITIVO", 2L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Acionamento garantia", "TIPO_OCORRENCIA_ACIONAMENTO_GARANTIA", 3L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Infração", "TIPO_OCORRENCIA_INFRACAO", 4L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Penalização", "TIPO_OCORRENCIA_PENALIZACAO", 5L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Rescisão", "TIPO_OCORRENCIA_RESCISAO", 6L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Verificação realizada", "TIPO_OCORRENCIA_VERIFICACAO", 7L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Liberação de pagamento", "TIPO_OCORRENCIA_PAGAMENTO", 8L));
		list.add(new Dominio("tipoOcorrenciaContrato", "Empenho", "TIPO_OCORRENCIA_EMPENHO", 9L));
		list.add(new Dominio("tipoTempoAtualizacao", "1 minuto", "UM", 1L));
		list.add(new Dominio("tipoTempoAtualizacao", "5 minuto", "CINCO", 2L));
		list.add(new Dominio("tipoTempoAtualizacao", "10 minuto", "DEZ", 3L));
		list.add(new Dominio("tipoTempoAtualizacao", "15 minuto", "QUINZE", 4L));
		list.add(new Dominio("tipoTempoAtualizacao", "30 minuto", "TRINTA", 5L));
		list.add(new Dominio("tipoTempoAtualizacao", "1 Hora", "UMA_HORA", 6L));
		list.add(new Dominio("tipoStatusInventario", "Aberto", "ABERTO", 1L));
		list.add(new Dominio("tipoStatusInventario", "Em andamento", "EM_ANDAMENTO", 2L));
		list.add(new Dominio("tipoStatusInventario", "Encerrado", "ENCERRADO", 3L));
		list.add(new Dominio("tipoStatusInventario", "Aguardando mobile", "AGUARDANDO_MOBILE", 4L));
		list.add(new Dominio("tipoStatusInventario", "Iniciado mobile", "INICIADO_MOBILE", 5L));
		list.add(new Dominio("tipoStatusInventario", "Finalizado mobile", "FINALIZADO_MOBILE", 6L));
		list.add(new Dominio("tipoInventario", "Anual", "ANUAL", 1L));
		list.add(new Dominio("tipoInventario", "Inicial", "INICIAL", 2L));
		list.add(new Dominio("tipoInventario", "Transferência", "TRANSFERENCIA", 3L));
		list.add(new Dominio("tipoInventario", "Extinção ou Transformação", "EXTINCAO_TRANSFORMACAO", 4L));
		list.add(new Dominio("tipoInventario", "Eventual", "Eventual", 5L));
		list.add(new Dominio("tipoStatusInventarioEstrutura", "Aberto", "ABERTO", 1L));
		list.add(new Dominio("tipoStatusInventarioEstrutura", "Em campo", "EM_CAMPO", 2L));
		list.add(new Dominio("tipoStatusInventarioEstrutura", "Em tratamento", "EM_TRATAMENTO", 3L));
		list.add(new Dominio("tipoStatusInventarioEstrutura", "Encerrado", "ENCERRADO", 4L));
		list.add(new Dominio("tipoStatusInventarioBemPatrimonial", "Não informado", "NAO_INFORMADO", 1L));
		list.add(new Dominio("tipoStatusInventarioBemPatrimonial", "Não há inconsistência", "NAO_HA_INCONSISTENCIA", 2L));
		list.add(new Dominio("tipoStatusInventarioBemPatrimonial", "Em inconsistência", "EM_INCONSISTENCIA", 3L));
		list.add(new Dominio("tipoStatusInventarioBemPatrimonial", "Tratada", "TRATADA", 4L));
		list.add(new Dominio("tipoDominioInconsistencia", "Bem patrimonial não encontrado", "CODIGO_TIPO_INCONSISTENCIA_BEM_NAO_ENCONTRADO", 1L));
		list.add(new Dominio("tipoDominioInconsistencia", "Estrutura organizacional divergente", "CODIGO_TIPO_INCONSISTENCIA_ESTRUTURA_ORGANIZACIONAL", 2L));
		list.add(new Dominio("tipoDominioInconsistencia", "Material divergente", "CODIGO_TIPO_INCONSISTENCIA_MATERIAL", 3L));
		list.add(new Dominio("tipoDominioInconsistencia", "Situação física divergente", "CODIGO_TIPO_INCONSISTENCIA_SITUACAO_FISICA", 4L));
		list.add(new Dominio("tipoDominioInconsistencia", "Status divergente", "CODIGO_TIPO_INCONSISTENCIA_STATUS", 5L));
		list.add(new Dominio("tipoDominioInconsistencia", "Valor divergente", "CODIGO_TIPO_INCONSISTENCIA_VALOR", 6L));
		list.add(new Dominio("tipoDominioInconsistencia", "Responsável divergente", "CODIGO_TIPO_INCONSISTENCIA_RESPONSAVEL", 7L));
		list.add(new Dominio("tipoDominioInconsistencia", "Detentor divergente", "CODIGO_TIPO_INCONSISTENCIA_DETENTOR", 8L));
		list.add(new Dominio("tipoDominioInconsistencia", "Bem principal divergente", "CODIGO_TIPO_INCONSISTENCIA_BEM_PRINCIPAL", 9L));
		list.add(new Dominio("tipoDominioInconsistencia", "Bem patrimonial sem plaqueta", "CODIGO_TIPO_INCONSISTENCIA_BEM_SEM_PLAQUETA", 10L));
		list.add(new Dominio("tipoLocalInconsistencia", "Sistema", "SISTEMA", 1L));
		list.add(new Dominio("tipoLocalInconsistencia", "Manual", "MANUAL", 2L));
		list.add(new Dominio("tipoLocalInconsistencia", "Mobile", "MOBILE", 3L));
		list.add(new Dominio("tipoObjetivoInventario", "'Cadastre objetivos no cadastro de domínios'", "CODIGO_OBJETIVO_INVENTARIO_1", 1L));
		list.add(new Dominio("objetivoContaContabil", "Almoxarifado", "OBJETIVO_CONTA_CONTABIL_ALMOXARIFADO", 1L));
		list.add(new Dominio("objetivoContaContabil", "Material", "OBJETIVO_CONTA_CONTABIL_MATERIAL", 2L));
		list.add(new Dominio("tipoNumerico", "Inteiro", "INTEIRO", 1L));
		list.add(new Dominio("tipoNumerico", "Decimal", "DECIMAL", 2L));
		list.add(new Dominio("tipoStatusRequisicao", "Não concluída", "NAO_CONCLUIDA", 1L));
		list.add(new Dominio("tipoStatusRequisicao", "Pronta para atendimento", "PRONTA_PARA_ATENDIMENTO", 2L));
		list.add(new Dominio("tipoStatusRequisicao", "Atendida parcialmente", "ATENDIDA_PARCIALMENTE", 3L));
		list.add(new Dominio("tipoStatusRequisicao", "Atendida", "ATENDIDA", 4L));
		list.add(new Dominio("tipoStatusRequisicao", "Finalizada", "FINALIZADA", 5L));
		list.add(new Dominio("tipoMovimentoEstoque", "Entrada almoxarifado", "ENTRADA", 1L));
		list.add(new Dominio("tipoMovimentoEstoque", "Baixa", "BAIXA", 2L));
		list.add(new Dominio("tipoMovimentoEstoque", "Atendimento requisição", "ATENDIMENTO", 3L));
		list.add(new Dominio("tipoMovimentoEstoque", "Devolução requisição", "DEVOLUCAO", 4L));
		list.add(new Dominio("tipoSaida", "Baixa", "ENTRADA", 1L));
		list.add(new Dominio("tipoSaida", "Requisição", "REQUISICAO", 2L));
		list.add(new Dominio("tipoAvaliacaoMonetariaEstoque", "FIFO", "FIFO", 1L));
		list.add(new Dominio("tipoAvaliacaoMonetariaEstoque", "Média móvel", "MEDIA_MOVEL", 2L));
		list.add(new Dominio("classeReferencia", "Entrada", "ENTRADA", 1L));
		list.add(new Dominio("classeReferencia", "Baixa", "BAIXA", 2L));
		list.add(new Dominio("classeReferencia", "Transferência Interna", "TRANSFERENCIA_INTERNA", 3L));
		list.add(new Dominio("classeReferencia", "Transferência entre contas contábeis", "TRANSFERENCIA_ENTRE_CONTAS", 4L));
		list.add(new Dominio("classeReferencia", "Atendimento", "ATENDIMENTO", 5L));
		list.add(new Dominio("classeReferencia", "Devolução", "DEVOLUCAO", 6L));
		list.add(new Dominio("classeReferencia", "Depreciação", "DEPRECIACAO", 7L));

		list.add(new Dominio("prazoExecucao", "Todos", "NAO_APLICA", 1L));
		list.add(new Dominio("prazoExecucao", "Vencido", "VENCIDO", 2L));
		list.add(new Dominio("prazoExecucao", "A vencer em menos de 30 min", "30_MIN", 3L));
		list.add(new Dominio("prazoExecucao", "A vencer em menos de 60 min", "60_MIN", 4L));
		list.add(new Dominio("prazoExecucao", "A vencer em menos de 90 min", "90_MIN", 5L));
		list.add(new Dominio("prazoExecucao", "A vencer em menos de 2 horas", "2_HORAS", 6L));
		list.add(new Dominio("prazoExecucao", "A vencer em menos de 3 horas", "3_HORAS", 7L));
		list.add(new Dominio("prazoExecucao", "A vencer hoje", "HOJE", 8L));

		list.add(new Dominio("periodoRelatorio", "Mensal", "PERIODO_MENSAL", 1L));
		list.add(new Dominio("periodoRelatorio", "Anual", "PERIODO_ANUAL", 2L));

		list.add(new Dominio(Dominio.COMISSAO_TIPO, Dominio.COMISSAO_DESCRICAO_INVENTARIO, Dominio.COMISSAO_NOME_INVENTARIO, Dominio.COMISSAO_CODIGO_INVENTARIO));
		list.add(new Dominio(Dominio.COMISSAO_TIPO, Dominio.COMISSAO_DESCRICAO_DESFAZIMENTO, Dominio.COMISSAO_NOME_DESFAZIMENTO, Dominio.COMISSAO_CODIGO_DESFAZIMENTO));

		// Domínios para Alçada
		list.add(new Dominio("tipoUtilizacao", "Uso interno", "INTERNO", 1L));
		list.add(new Dominio("tipoUtilizacao", "Atendimento ao cliente", "ATENDIMENTO_CLIENTE", 2L));

		list.add(new Dominio("tipoLimiteValor", "Individual", "INDIVIDUAL", 1L));
		list.add(new Dominio("tipoLimiteValor", "Mensal", "MENSAL", 2L));
		list.add(new Dominio("tipoLimiteValor", "Anual", "ANUAL", 3L));

		list.add(new Dominio("tipoAbrangenciaCentroResultado", "Somente o responsável", "SOMENTE_RESPONSAVEL", 1L));
		list.add(new Dominio("tipoAbrangenciaCentroResultado", "Todos", "TODOS", 2L));

		list.add(new Dominio("tipoLimite", "Faixa de valores", "FAIXA_VALOR", 1L));
		list.add(new Dominio("tipoLimite", "Qualquer valor", "QUALQUER_VALOR", 2L));
		list.add(new Dominio("tipoLimite", "Não se aplica", "NAO_APLICA", 3L));

		list.add(new Dominio("tipoAbrangenciaCentroResultadoDelegacao", "Requisições novas e em andamento", "NOVAS_E_ANDAMENTO", 1L));
		list.add(new Dominio("tipoAbrangenciaCentroResultadoDelegacao", "Somente novas requisições", "NOVAS", 2L));
		list.add(new Dominio("tipoAbrangenciaCentroResultadoDelegacao", "Requisição(ões) específica(s)", "ESPECIFICAS", 3L));

		list.add(new Dominio("motivoRejeicaoAlcada", "Fora dos limites de aprovação", "LIMITE_VALOR", 1L));
		list.add(new Dominio("motivoRejeicaoAlcada", "Nível de hierarquia abaixo da hierarquia do solicitante", "HIERARQUIA_SOLICITANTE", 2L));
		list.add(new Dominio("motivoRejeicaoAlcada", "Autoridade não tem permissão para aprovar as próprias solicitações", "PERMISSAO_AUTORIDADE", 3L));
		list.add(new Dominio("motivoRejeicaoAlcada", "Regras do processo de negócio", "REGRAS_PROCESSO", 4L));
		list.add(new Dominio("motivoRejeicaoAlcada", "Não existe um usuário ativo associado ao empregado autorizador", "USUARIO_NAO_EXISTE", 5L));
		list.add(new Dominio("motivoRejeicaoAlcada", "Solicitante pertence ao grupo responsável pelo processo de negócio", "PERTENCE_GRUPO_RESPONSAVEL", 6L));
		list.add(new Dominio("motivoRejeicaoAlcada", "O usuário não pertence a nenhum grupo associado aos níveis de autoridade da alçada", "GRUPO_NAO_ASSOCIADO", 7L));
		list.add(new Dominio("motivoRejeicaoAlcada", "Já existe um aprovador com nível hierárquico inferrior", "HIERARQUIA_AUTORIDADE", 8L));

		list.add(new Dominio("situacaoSolicitacaoAlcada", "Gerada", "GERADA", 1L));
		list.add(new Dominio("situacaoSolicitacaoAlcada", "Aprovada", "APROVADA", 2L));
		list.add(new Dominio("situacaoSolicitacaoAlcada", "Cancelada", "CANCELADA", 3L));

		list.add(new Dominio("tipoMascara", "Apenas sequencial", "SEQUENCIAL", 1L));
		list.add(new Dominio("tipoMascara", "Ano atual e sequencial", "ANO_E_SEQUENCIAL", 2L));

		list.add(new Dominio("parameterType", "Texto curto", "TEXT", 1L));
		list.add(new Dominio("parameterType", "Texto longo", "TEXTAREA", 2L));
		list.add(new Dominio("parameterType", "Booleano", "BOOLEAN", 3L));
		list.add(new Dominio("parameterType", "Inteiro", "INTEGER", 4L));
		list.add(new Dominio("parameterType", "Decimal", "DECIMAL", 5L));
		list.add(new Dominio("parameterType", "Data", "DATE", 6L));
		list.add(new Dominio("parameterType", "Senha", "PASSWORD", 7L));
		list.add(new Dominio("parameterType", "Json", "JSON", 8L));
		
		list.add(new Dominio("campoReferencia", "Vida útil restante", "BEM_PAT_VIDA_UTIL_RESTANTE", 1L));
		list.add(new Dominio("campoReferencia", "Valor bruto atual", "BEM_PAT_VALOR_BRUTO_ATUAL", 2L));
		list.add(new Dominio("campoReferencia", "Número patrimonial", "BEM_PAT_NUMERO_PATRIMONIAL", 3L));
		list.add(new Dominio("campoReferencia", "Status", "BEM_PAT_STATUS", 4L));
		list.add(new Dominio("campoReferencia", "Situação física", "BEM_PAT_SITUACAO_FISICA", 5L));
		list.add(new Dominio("campoReferencia", "Data início da garantia", "BEM_PAT_DT_INICIO_GARANTIA", 6L));
		list.add(new Dominio("campoReferencia", "Data fim da garantia", "BEM_PAT_DT_FIM_GARANTIA", 7L));

		this.dominioService.saveListIfNotExist(list);
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
	 * @author rogerio.costa
	 *
	 */
	protected void criarWidget() {

		Widget widgetHigChart = new Widget("HighChart", "HighChart", this.dominioService.findByChaveAndCodigo("tipoWidget", 1L), Boolean.TRUE);
		widgetHigChart = this.widgetService.saveIfNotExist(widgetHigChart);

		List<WidgetParametro> listaWidgetParametrosHigChart = new LinkedList<WidgetParametro>();

		listaWidgetParametrosHigChart.add(new WidgetParametro("Tipo Grafico", this.dominioService.findByChaveAndNome("tipoDado", "TIPO_DOMINIO"), "tipoHighChart", this.dominioService.findByChaveAndNome("tipoHighChart", "pie"), null, null, null, null, widgetHigChart));
		listaWidgetParametrosHigChart.add(new WidgetParametro("titulo", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, "Título", null, null, widgetHigChart));
		listaWidgetParametrosHigChart.add(new WidgetParametro("sub titulo", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, null, null, null, widgetHigChart));
		this.widgetParametroService.saveListIfNotExist(listaWidgetParametrosHigChart);

		Widget widgetGoogleChart = new Widget("Google Chart", "Google Chart", this.dominioService.findByChaveAndCodigo("tipoWidget", 5L), Boolean.TRUE);
		this.widgetService.saveIfNotExist(widgetGoogleChart);

		List<WidgetParametro> listaParametrosGoogleCharts = new LinkedList<WidgetParametro>();
		listaParametrosGoogleCharts.add(new WidgetParametro("hAxis titulo", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, null, null, null, widgetGoogleChart));
		listaParametrosGoogleCharts.add(new WidgetParametro("vAxis titulo", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, null, null, null, widgetGoogleChart));
		listaParametrosGoogleCharts.add(new WidgetParametro("isStacked", this.dominioService.findByChaveAndNome("tipoDado", "BOOLEAN"), null, null, null, null, null, Boolean.FALSE, widgetGoogleChart));
		listaParametrosGoogleCharts.add(new WidgetParametro("Titulo", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, "Título", null, null, widgetGoogleChart));
		listaParametrosGoogleCharts.add(new WidgetParametro("Tipo Grafico", this.dominioService.findByChaveAndNome("tipoDado", "TIPO_DOMINIO"), "tipoGoogleChart", this.dominioService.findByChaveAndNome("tipoGoogleChart", "PieChart"), null, null, null, null, widgetGoogleChart));
		this.widgetParametroService.saveListIfNotExist(listaParametrosGoogleCharts);

		Widget widgetGoogleGouge = new Widget("Google Chart Gauge", "Google Chart Gauge", this.dominioService.findByChaveAndCodigo("tipoWidget", 6L), Boolean.TRUE);

		this.widgetService.saveIfNotExist(widgetGoogleGouge);

		List<WidgetParametro> listaParametroGoogleGouge = new LinkedList<WidgetParametro>();

		listaParametroGoogleGouge.add(new WidgetParametro("max", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 100L, null, null, null, widgetGoogleGouge));
		listaParametroGoogleGouge.add(new WidgetParametro("yellowFrom", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 70L, null, null, null, widgetGoogleGouge));
		listaParametroGoogleGouge.add(new WidgetParametro("yellowTo", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 80L, null, null, null, widgetGoogleGouge));
		listaParametroGoogleGouge.add(new WidgetParametro("redFrom", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 80L, null, null, null, widgetGoogleGouge));
		listaParametroGoogleGouge.add(new WidgetParametro("redTo", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 100L, null, null, null, widgetGoogleGouge));
		listaParametroGoogleGouge.add(new WidgetParametro("greenFrom", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 40L, null, null, null, widgetGoogleGouge));
		listaParametroGoogleGouge.add(new WidgetParametro("greenTo", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 70L, null, null, null, widgetGoogleGouge));
		listaParametroGoogleGouge.add(new WidgetParametro("minorTicks", this.dominioService.findByChaveAndNome("tipoDado", "NUMBER"), null, null, 5L, null, null, null, widgetGoogleGouge));
		this.widgetParametroService.saveListIfNotExist(listaParametroGoogleGouge);

		Widget widgetHtml = new Widget("Html", "Html", this.dominioService.findByChaveAndCodigo("tipoWidget", 7L), Boolean.TRUE);

		this.widgetService.saveIfNotExist(widgetHtml);

		Widget widgetNoticia = new Widget("Notícia", "Notícia", this.dominioService.findByChaveAndCodigo("tipoWidget", 4L), Boolean.TRUE);

		this.widgetService.saveIfNotExist(widgetNoticia);

		Widget widgetTemperatura = new Widget("Temperatura", "Temperatura", this.dominioService.findByChaveAndCodigo("tipoWidget", 3L), Boolean.TRUE);

		this.widgetService.saveIfNotExist(widgetTemperatura);

		Widget widgetlink = new Widget("Link", "Link", this.dominioService.findByChaveAndCodigo("tipoWidget", 2L), Boolean.TRUE);

		this.widgetService.saveIfNotExist(widgetlink);

		Widget widgetBpeTask = new Widget("Tarefas BPE", "Tarefas BPE", this.dominioService.findByChaveAndCodigo("tipoWidget", 8L), Boolean.FALSE);
		this.widgetService.saveIfNotExist(widgetBpeTask);

		List<WidgetParametro> listaParametrosBpeTask = new LinkedList<WidgetParametro>();
		listaParametrosBpeTask.add(new WidgetParametro("Nome da tarefa", "taskName", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, null, null, null, widgetBpeTask));
		listaParametrosBpeTask.add(new WidgetParametro("Nome do processo de negócio", "businessProcessName", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, null, null, null, widgetBpeTask));
		listaParametrosBpeTask.add(new WidgetParametro("Situação do processo", "status", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_FIELD"), null, null, null, null, null, null, widgetBpeTask));
		listaParametrosBpeTask.add(new WidgetParametro("Prazo da tarefa", "deadline", this.dominioService.findByChaveAndNome("tipoDado", "TIPO_DOMINIO"), "prazoExecucao", this.dominioService.findByChaveAndNome("prazoExecucao", "NAO_APLICA"), null, null, null, null, widgetBpeTask));
		listaParametrosBpeTask.add(new WidgetParametro("Prazo do processo", "processDeadline", this.dominioService.findByChaveAndNome("tipoDado", "TIPO_DOMINIO"), "prazoExecucao", this.dominioService.findByChaveAndNome("prazoExecucao", "NAO_APLICA"), null, null, null, null, widgetBpeTask));
		listaParametrosBpeTask.add(new WidgetParametro("Exibe lista de tarefas com todos os atributos", "completeTaskList", this.dominioService.findByChaveAndNome("tipoDado", "BOOLEAN"), null, null, null, null, null, true, widgetBpeTask));
		listaParametrosBpeTask.add(new WidgetParametro("Filtros adicionais", "customFilter", this.dominioService.findByChaveAndNome("tipoDado", "TEXT_AREA"), null, null, null, null, null, null, widgetBpeTask));
		this.widgetParametroService.saveListIfNotExist(listaParametrosBpeTask);

	}

}