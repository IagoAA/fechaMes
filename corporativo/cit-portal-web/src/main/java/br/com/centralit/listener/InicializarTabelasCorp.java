package br.com.centralit.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.centralit.api.service.DefaultFileService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.api.service.MenuService;
import br.com.centralit.api.service.PaginaService;
import br.com.centralit.api.service.ConfiguracaoParametroSistemaService;
import br.com.centralit.framework.model.DefaultFile;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.Menu;
import br.com.centralit.framework.model.MenuFile;
import br.com.centralit.framework.model.Modulo;
import br.com.centralit.framework.model.Pagina;

@Component
public class InicializarTabelasCorp {

	/** Atributo paginaService. */
	@Autowired
	private PaginaService paginaService;

	/** Atributo menuService. */
	@Autowired
	private MenuService menuService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo defaultFileService. */
	@Autowired
	private DefaultFileService defaultFileService;

	/** Atributo dominioJS. */
	private Dominio dominioJS;

	@Autowired
	private ConfiguracaoParametroSistemaService parametroSistemaService;

	/** Atributo CIT_TABELASCORP_WEB_ANGULAR. */
	private final String CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM = "/cit-tabelas-corp-web/assets/js/angular/custom/";

	/** Atributo CIT_TABELASCORP_WEB_ANGULAR. */
	private final String CIT_PORTAL_WEB_ANGULAR_CUSTOM = "/cit-portal-web/assets/js/angular/custom/";

	/** Atributo CSS_MENU_TABELASCORP. */
	private final String CSS_MENU_TABELASCORP = ".mod-blue .bar-buttons-action:before {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr=\"#ffffff\", endColorstr=\"#62a0d2\",GradientType=0 );}.modal.modal-buttons-top.mod-blue .modal-dialog .modal-header {	background: rgb(255,255,255);	background: -moz-linear-gradient(top, rgba(255,255,255,1) 0%, rgba(98,160,210,0.5) 100%);	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(100%,rgba(98,160,210,0.5)));	background: -webkit-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -o-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: -ms-linear-gradient(top, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	background: linear-gradient(to bottom, rgba(255,255,255,1) 0%,rgba(98,160,210,0.5) 100%);	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr=\"#ffffff\", endColorstr=\"#62a0d2\",GradientType=0 );}#workspace-nav li.mod-blue a {	border-top-color: #62a0d2;}";

	/** Atributo COR_TABELASCORP. */
	private final String COR_TABELASCORP = "#62a0d2";

	/** Atributo CLASSE_TABELASCORP. */
	private final String CLASSE_TABELASCORP = "mod-blue";

	/** Atributo OPACIDADEMENUTABELASCORP. */
	private final Double OPACIDADE_MENU_TABELASCORP = 5D;

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

	/** Atributo ICONE_MENU_CORPORATIVO. */
	private final String ICONE_MENU_CORPORATIVO = "fa-building-o";

	/**
	 *
	 * Método responsável por criar paginas e menus do modulo
	 *
	 * @author wilker.machado
	 *
	 */
	protected void criarPaginasMenus(Modulo modulo) {

		this.dominioJS = this.dominioService.findByChaveAndNome("tipoFile", "JS");

		// Menu pai Corporativo
		Menu menuTabelasCorp = new Menu("Corporativo", null, null, null, 3, COR_TABELASCORP, CSS_MENU_TABELASCORP, OPACIDADE_MENU_TABELASCORP, CLASSE_TABELASCORP, ICONE_MENU_CORPORATIVO, modulo);
		List<MenuFile> includesTabelasCorp = new ArrayList<MenuFile>();
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/TelefoneRepository.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/TelefoneRepository.min.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/SeguradoraRepository.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/SeguradoraRepository.min.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/FornecedorRepository.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/FornecedorRepository.min.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ColaboradorRepository.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ColaboradorRepository.min.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ObservacaoRepository.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ObservacaoRepository.min.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/FuncaoRepository.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/FuncaoRepository.min.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/EnderecoRepository.js", this.dominioJS, menuTabelasCorp));
		includesTabelasCorp.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/EnderecoRepository.min.js", this.dominioJS, menuTabelasCorp));
		menuTabelasCorp.setIncludes(includesTabelasCorp);
		menuTabelasCorp = this.menuService.mergeIfNotExist(menuTabelasCorp);

		// Submenu Localidades
		Menu menuLocalidades = new Menu("Localidades", null, menuTabelasCorp, 0, 1, null, null, null, null, modulo);
		menuLocalidades.setParent(menuTabelasCorp);
		menuLocalidades = this.menuService.mergeIfNotExist(menuLocalidades);
		// Menu País
		Pagina pgPais = new Pagina("País", "/cit-tabelas-corp-web/html/pais/pais.html");
		pgPais = this.paginaService.saveIfNotExist(pgPais);
		Menu menuPais = new Menu("País", pgPais, menuLocalidades, null, 1, null, null, null, null, modulo);
		menuPais.setIncludes(this.gerarArquivosMenu(menuPais, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Pais", true, true, true));
		this.menuService.mergeIfNotExist(menuPais);
		// Menu Região
		Pagina pgRegiao = new Pagina("Região", "/cit-tabelas-corp-web/html/regiao/regiao.html");
		pgRegiao = this.paginaService.saveIfNotExist(pgRegiao);
		Menu menuRegiao = new Menu("Região", pgRegiao, menuLocalidades, null, 2, null, null, null, null, modulo);
		menuRegiao.setIncludes(this.gerarArquivosMenu(menuRegiao, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Regiao", true, true, true));
		this.menuService.mergeIfNotExist(menuRegiao);
		// Menu Estado
		Pagina pgEstado = new Pagina("Estado", "/cit-tabelas-corp-web/html/estado/estado.html");
		pgEstado = this.paginaService.saveIfNotExist(pgEstado);
		Menu menuEstado = new Menu("Estado", pgEstado, menuLocalidades, null, 3, null, null, null, null, modulo);
		menuEstado.setIncludes(this.gerarArquivosMenu(menuEstado, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Estado", true, true, true));
		this.menuService.mergeIfNotExist(menuEstado);
		// Menu Cidade
		Pagina pgCidade = new Pagina("Cidade", "/cit-tabelas-corp-web/html/cidade/cidade.html");
		pgCidade = this.paginaService.saveIfNotExist(pgCidade);
		Menu menuCidade = new Menu("Cidade", pgCidade, menuLocalidades, null, 4, null, null, null, null, modulo);
		menuCidade.setIncludes(this.gerarArquivosMenu(menuCidade, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Cidade", true, true, true));
		this.menuService.mergeIfNotExist(menuCidade);
		// Menu Bairro
		Pagina pgBairro = new Pagina("Bairro", "/cit-tabelas-corp-web/html/bairro/bairro.html");
		pgBairro = this.paginaService.saveIfNotExist(pgBairro);
		Menu menuBairro = new Menu("Bairro", pgBairro, menuLocalidades, null, 5, null, null, null, null, modulo);
		menuBairro.setIncludes(this.gerarArquivosMenu(menuBairro, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Bairro", true, true, true));
		this.menuService.mergeIfNotExist(menuBairro);
		// Menu Endereço
		Pagina pgEndereco = new Pagina("Endereço", "/cit-tabelas-corp-web/html/endereco/endereco.html");
		pgEndereco = this.paginaService.saveIfNotExist(pgEndereco);
		Menu menuEndereco = new Menu("Endereço", pgEndereco, menuLocalidades, null, 6, null, null, null, null, modulo);
		menuEndereco.setIncludes(this.gerarArquivosMenu(menuEndereco, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Endereco", true, true, true));
		this.menuService.mergeIfNotExist(menuEndereco);
		// Menu Localização
		Pagina pgLocalizacao = new Pagina("Localização", "/cit-tabelas-corp-web/html/localizacao/localizacao.html");
		pgLocalizacao = this.paginaService.saveIfNotExist(pgLocalizacao);
		Menu menuLocalizacao = new Menu("Localização", pgLocalizacao, menuLocalidades, null, 7, null, null, null, null, modulo);
		menuLocalizacao.setIncludes(this.gerarArquivosMenu(menuLocalizacao, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Localizacao", true, true, true));
		this.menuService.mergeIfNotExist(menuLocalizacao);

		// Submenu Pessoas (Agentes)
		Menu menuPessoas = new Menu("Pessoas (Agentes)", null, menuTabelasCorp, 1, 3, null, null, null, null, modulo);
		menuPessoas.setParent(menuTabelasCorp);
		menuPessoas = this.menuService.mergeIfNotExist(menuPessoas);
		// Menu Cadastro de pessoas
		Pagina pgPessoa = new Pagina("Cadastro de pessoas", "/cit-tabelas-corp-web/html/pessoa/pessoa.html");
		pgPessoa = this.paginaService.saveIfNotExist(pgPessoa);
		Menu menuPessoa = new Menu("Cadastro de pessoas", pgPessoa, menuPessoas, null, 1, null, null, null, null, modulo);
		List<MenuFile> filesMenuFilePessoa = new ArrayList<MenuFile>();
		filesMenuFilePessoa.addAll(this.gerarArquivosMenu(menuPessoa, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Pessoa", true, true, true));
		filesMenuFilePessoa.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ParceiroRepository.js", dominioJS, menuPessoa));
		filesMenuFilePessoa.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ParceiroRepository.min.js", dominioJS, menuPessoa));
		menuPessoa.setIncludes(filesMenuFilePessoa);
		this.menuService.mergeIfNotExist(menuPessoa);

		// Menu Cadastro de Funcao
		Pagina pgFuncao = new Pagina("Função", "/cit-tabelas-corp-web/html/funcao/funcao.html");
		pgFuncao = this.paginaService.saveIfNotExist(pgFuncao);
		Menu menuFuncao = new Menu("Função", pgFuncao, menuPessoas, null, 2, null, null, null, null, modulo);
		List<MenuFile> filesMenuFileFuncao = new ArrayList<MenuFile>();
		filesMenuFileFuncao.addAll(this.gerarArquivosMenu(menuFuncao, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Funcao", true, true, true));
		menuFuncao.setIncludes(filesMenuFileFuncao);
		this.menuService.mergeIfNotExist(menuFuncao);

		// Submenu Atributos personalizados
		Menu menuAtributos = new Menu("Atributos personalizados", null, menuTabelasCorp, 1, 4, null, null, null, null, modulo);
		menuAtributos.setParent(menuTabelasCorp);
		menuAtributos = this.menuService.mergeIfNotExist(menuAtributos);
		// Menu Definir características
		Pagina pgCaracteristica = new Pagina("Definir características", "/cit-tabelas-corp-web/html/caracteristica/caracteristica.html");
		pgCaracteristica = this.paginaService.saveIfNotExist(pgCaracteristica);
		Menu menuCaracteristica = new Menu("Definir características", pgCaracteristica, menuAtributos, null, 1, null, null, null, null, modulo);
		menuCaracteristica.setIncludes(this.gerarArquivosMenu(menuCaracteristica, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Caracteristica", true, true, true));
		this.menuService.mergeIfNotExist(menuCaracteristica);

		// Submenu Estrutura organizacional
		Menu menuEstrutura = new Menu("Estrutura organizacional", null, menuTabelasCorp, 0, 2, null, null, null, null, modulo);
		menuEstrutura.setParent(menuTabelasCorp);
		menuEstrutura = this.menuService.mergeIfNotExist(menuEstrutura);

		// Menu Organizacao - gerei no portal por causa do login, mas vai ficar no menu do tabelas corp
		Pagina pgOrganizacao = new Pagina("Órgão", "/cit-portal-web/html/organizacao/organizacao.html");
		pgOrganizacao = this.paginaService.saveIfNotExist(pgOrganizacao);
		Menu menuOrganizacao = new Menu("Órgão", pgOrganizacao, menuEstrutura, null, 1, null, null, null, null, modulo);
		menuOrganizacao.setIncludes(this.gerarArquivosMenu(menuOrganizacao, CIT_PORTAL_WEB_ANGULAR_CUSTOM, "Organizacao", true, true, true));
		this.menuService.mergeIfNotExist(menuOrganizacao);

		// Menu Nivel de autoridade
		Pagina pgNivelAutoridade = new Pagina("Nivel de autoridade", "/cit-tabelas-corp-web/html/nivelAutoridade/nivelAutoridade.html");
		pgNivelAutoridade = this.paginaService.saveIfNotExist(pgNivelAutoridade);
		Menu menuNivelAutoridade = new Menu("Nível de autoridade", pgNivelAutoridade, menuEstrutura, null, 2, null, null, null, null, modulo);
		menuNivelAutoridade.setIncludes(this.gerarArquivosMenu(menuNivelAutoridade, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "NivelAutoridade", true, true, true));
		this.menuService.mergeIfNotExist(menuNivelAutoridade);

		// Submenu Comissao
		Menu menuComissao = new Menu("Comissão", null, menuTabelasCorp, 1, 5, null, null, null, null, modulo);
		menuComissao = this.menuService.mergeIfNotExist(menuComissao);

		// Menu Cadastro das unidades
		Pagina pgEstrutura = new Pagina("Cadastro das unidades", "/cit-tabelas-corp-web/html/estruturaOrganizacional/estruturaOrganizacional.html");
		pgEstrutura = this.paginaService.saveIfNotExist(pgEstrutura);
		Menu menuEstruturaOrganizacional = new Menu("Cadastro das unidades", pgEstrutura, menuEstrutura, null, 3, null, null, null, null, modulo);
		List<MenuFile> filesMenuFileEstruturaOrganizacional = this.gerarArquivosMenu(menuEstruturaOrganizacional, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "EstruturaOrganizacional", true, true, true);
		filesMenuFileEstruturaOrganizacional.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/EstruturaOrganizacionalResponsavelRepository.js", this.dominioJS, menuEstruturaOrganizacional));
		filesMenuFileEstruturaOrganizacional.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/EstruturaOrganizacionalResponsavelRepository.min.js", this.dominioJS, menuEstruturaOrganizacional));
		menuEstruturaOrganizacional.setIncludes(filesMenuFileEstruturaOrganizacional);
		this.menuService.mergeIfNotExist(menuEstruturaOrganizacional);
		// Menu Mapa organizacional
		Pagina pgMapa = new Pagina("Mapa organizacional", "/cit-tabelas-corp-web/html/mapaOrganizacional/mapaOrganizacional.html");
		pgMapa = this.paginaService.saveIfNotExist(pgMapa);
		Menu menuMapa = new Menu("Mapa Organizacional", pgMapa, menuEstrutura, null, 4, null, null, null, null, modulo);
		menuMapa.setIncludes(this.gerarArquivosMenu(menuMapa, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "MapaOrganizacional", true, true, true));
		this.menuService.mergeIfNotExist(menuMapa);

		// Menu Comissão
		Pagina pgComissao = new Pagina("Comissão", "/cit-tabelas-corp-web/html/comissao/comissao.html");
		pgComissao = this.paginaService.saveIfNotExist(pgComissao);
		Menu menuComissaoItem = new Menu("Cadastro de comissões", pgComissao, menuComissao, null, 5, null, null, null, null, modulo);
		List<MenuFile> filesMenuFileComissaoIntegrante = this.gerarArquivosMenu(menuComissaoItem, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Comissao", true, true, true);
		filesMenuFileComissaoIntegrante.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ComissaoIntegranteRepository.js", this.dominioJS, menuComissaoItem));
		filesMenuFileComissaoIntegrante.add(new MenuFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/ComissaoIntegranteRepository.min.js", this.dominioJS, menuComissaoItem));
		menuComissaoItem.setIncludes(filesMenuFileComissaoIntegrante);
		this.menuService.mergeIfNotExist(menuComissaoItem);

		// Submenu Servico
		Menu menuServico = new Menu("Serviço", null, menuTabelasCorp, 2, 3, null, null, null, null, modulo);
		menuServico = this.menuService.mergeIfNotExist(menuServico);

		// Menu Cadastro de Servicos
		Pagina pgServico = new Pagina("Cadastro de servi\u00e7os", "/cit-tabelas-corp-web/html/servico/servico.html");
		pgServico = this.paginaService.saveIfNotExist(pgServico);
		Menu menuServicoItem = new Menu("Cadastro de servi\u00e7os", pgServico, menuServico, null, 6, null, null, null, null, modulo);
		List<MenuFile> filesMenuFileServico = this.gerarArquivosMenu(menuServicoItem, CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM, "Servico", true, true, true);
		menuServicoItem.setIncludes(filesMenuFileServico);
		this.menuService.mergeIfNotExist(menuServicoItem);
	}

	/**
	 *
	 * Método responsável por
	 *
	 * @author wilker.machado
	 *
	 */
	protected void criarArquivosPadrao() {

		this.dominioJS = this.dominioService.findByChaveAndNome("tipoFile", "JS");

		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/EnderecoDirective.js", 132, dominioJS));
		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/EnderecoDirective.min.js", 132, dominioJS));

		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/EnderecoUnicoDirective.js", 133, dominioJS));
		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/EnderecoUnicoDirective.min.js", 133, dominioJS));

		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/ObservacaoDirective.js", 134, dominioJS));
		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/ObservacaoDirective.min.js", 134, dominioJS));

		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/TelefoneDirective.js", 135, dominioJS));
		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/TelefoneDirective.min.js", 135, dominioJS));

		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/DocumentoRepository.js", dominioJS, 140));
		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/DocumentoRepository.min.js", dominioJS, 141));

		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/AnexoRepository.js", dominioJS, 142));
		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "repository/AnexoRepository.min.js", dominioJS, 143));

		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/AssociarDocumentoDirective.js", dominioJS, 144));
		this.defaultFileService.saveIfNotExist(new DefaultFile(CIT_TABELAS_CORP_WEB_ANGULAR_CUSTOM + "directive/AssociarDocumentoDirective.min.js", dominioJS, 145));

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