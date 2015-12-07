package br.com.centralit.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.centralit.api.service.DefaultFileService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.MenuService;
import br.com.centralit.api.service.PaginaService;
import br.com.centralit.framework.model.DefaultFile;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuFile;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.Pagina;

@Component
public class InicializarFechaMes {

	@Autowired
	private PaginaService paginaService;

	/** Atributo menuService. */
	@Autowired
	private MenuService menuService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	@Autowired
	private DefaultFileService defaultFileService;

	/** Atributo menuFechaMes. */
	Menu menuFechaMes;

	/** Atributo dominioJS. */
	private Dominio dominioJS;

	/** Atributo CSS_MENU_FECHA_MES. */
	private final String CSS_MENU_FECHA_MES = ".mod-blue-dark .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(0,0,139,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(0,0,139,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr=\"#ffffff\", endColorstr=\"#00008B\",GradientType=0 );}.modal.modal-buttons-top.mod-blue-dark .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(0,0,139,0.3) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(0,0,139,0.3)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(0,0,139,0.3) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr=\"#ffffff\", endColorstr=\"#00008B\",GradientType=0 );}#workspace-nav li.mod-blue-dark a {	border-top-color: #00008B;}";

	/** Atributo COR_FECHA_MES. */
	private final String COR_FECHA_MES = "#00008B";

	/** Atributo CLASSE_FECHA_MES. */
	private final String CLASSE_FECHA_MES = "mod-blue-dark";

	/** Atributo OPACIDADE_MENU_FECHA_MES. */
	private final double OPACIDADE_MENU_FECHA_MES = 3D;

	/** Atributo FECHA_MES_WEB_ANGULAR. */
	private final String FECHA_MES_WEB_ANGULAR = "/fecha-mes-web/assets/js/angular/custom/";

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

	/** Atributo ICONE_MENU_FECHA_MES. */
	private final String ICONE_MENU_FECHA_MES = "fa-file-text-o";

	/**
	 *
	 * Método responsável por criar paginas e menus do modulo
	 *
	 * @author iago.almeida
	 *
	 */
	protected void criarPaginasMenus(Modulo modulo) {

		this.dominioJS = this.dominioService.findByChaveAndNome("tipoFile", "JS");

		// Menu pai Fecha Mes
		this.menuFechaMes = new Menu("Fecha Mês", null, null, null, 5, COR_FECHA_MES, CSS_MENU_FECHA_MES, OPACIDADE_MENU_FECHA_MES, CLASSE_FECHA_MES, ICONE_MENU_FECHA_MES, modulo);
		this.menuFechaMes = this.menuService.mergeIfNotExist(this.menuFechaMes);

		// Submenu Gestao
		Menu menuGestao = new Menu("Gestão", null, this.menuFechaMes, 0, 2, null, null, null, null, modulo);
		menuGestao = this.menuService.mergeIfNotExist(menuGestao);

		// Menu Luck
		Pagina pgLuck = new Pagina("Cadastro de luck", "/fecha-mes-web/html/luck/luck.html");
		pgLuck = this.paginaService.saveIfNotExist(pgLuck);
		Menu menuLuck = new Menu("Cadastro de luck", pgLuck, menuGestao, null, 2, null, null, null, null, modulo);
		List<MenuFile> filesMenuFileLuck = this.gerarArquivosMenu(menuLuck, FECHA_MES_WEB_ANGULAR, "Luck", true, true, true);
		menuLuck.setIncludes(filesMenuFileLuck);
		this.menuService.mergeIfNotExist(menuLuck);

		// Menu Caixa
		Pagina pgCaixa = new Pagina("Cadastro de caixa", "/fecha-mes-web/html/caixa/caixa.html");
		pgCaixa = this.paginaService.saveIfNotExist(pgCaixa);
		Menu menuCaixa = new Menu("Cadastro de caixa", pgCaixa, menuGestao, null, 2, null, null, null, null, modulo);
		List<MenuFile> filesMenuFileCaixa = this.gerarArquivosMenu(menuCaixa, FECHA_MES_WEB_ANGULAR, "Caixa", true, true, true);
		menuCaixa.setIncludes(filesMenuFileCaixa);
		this.menuService.mergeIfNotExist(menuCaixa);
	}

	/**
	 *
	 * Método responsável por criar arquivos padrao para inicializacao do modulo
	 *
	 * @author iago.almeida
	 *
	 */
	protected void criarArquivosPadrao() {

		//Criar defaultfile do css do visual track
		Dominio dominioCSS = this.dominioService.findByChaveAndNome("tipoFile", "CSS");

		this.defaultFileService.saveIfNotExist(new DefaultFile("/fecha-mes-web/assets/css/module-custom.css", dominioCSS, 9));

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
	protected List<MenuFile> gerarArquivosMenu(Menu menu, String caminho, String classe, boolean gerarController, boolean gerarListController, boolean gerarRepository) {

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

}