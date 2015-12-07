'use strict';


citApp.controller('AppController', ['$scope', '$routeParams', '$timeout', '$location', '$anchorScroll', '$filter', '$window', '$translate', '$modal', '$document', 'growl', 'PaginaUsuarioRepository',
                                    'UsuarioRepository', 'NotificacaoRepository', '$rootScope', 'paginationConfig', 'datepickerPopupConfig', '$injector', 'ModuloRepository', '$http', '$sce', 'MenuRepository', 'IndexRepository',
                                    	function AppController($scope, $routeParams, $timeout, $location, $anchorScroll, $filter, $window, $translate, $modal, $document, growl, PaginaUsuarioRepository,
                                    						   UsuarioRepository, NotificacaoRepository, $rootScope, paginationConfig, datepickerPopupConfig, $injector, ModuloRepository, $http, $sce, MenuRepository, IndexRepository) {

	// DEFINE O TRANSLATE PARA TODOS OS CONTROLLERS
	$scope.$translate = $translate;

	$scope.notificacao = {};

	//CONSTANTES
	$scope.MASK_CPF = "999.999.999.99";
	$scope.MASK_CNPJ = "99.999.999/9999-99";
	$scope.LIMITE_FAVORITO_MENU = 5;
	$scope.paginasUsuarioFavoritoTemp = [];

	// BUSCA O USUÁRIO LOGADO
	$scope.usuarioLogado = {};

	// BUSCA VERSAO SISTEMA
	$scope.informacaoSistema = {};

	UsuarioRepository.getUsuarioLogado().then(function(result){
		$scope.usuarioLogado = result.originalElement;
		$scope.usuarioLogado.organizacao.dataReferenciaVigente = converterStringEmDate($scope.usuarioLogado.organizacao.dataReferenciaVigente);
		$scope.usuarioLogado.organizacoes = $filter('itemOrganizacaoDistinct')($scope.usuarioLogado.organizacoes, $scope.usuarioLogado.organizacao);

	});

	$scope.atualizarUsuarioLogado = function(){
		UsuarioRepository.getUsuarioLogado().then(function(result){
			$scope.usuarioLogado = result.originalElement;
			$scope.usuarioLogado.organizacao.dataReferenciaVigente = converterStringEmDate($scope.usuarioLogado.organizacao.dataReferenciaVigente);
			$scope.usuarioLogado.organizacoes = $filter('itemOrganizacaoDistinct')($scope.usuarioLogado.organizacoes, $scope.usuarioLogado.organizacao);
		});
	};

	$scope.idObejct = 0;
	$scope.newObejct = false;
	$scope.mensagemLoading = 'Inicializando Aguarde...';
	$scope.edit = true;

	$scope.openWorkspaceIfNotOpen = function(titulo, pagina, classePagina, paginaId) {
		var workspace = undefined;
		for(var i = 0;  i < $scope.workspaces.length; i++){
			if($scope.workspaces[i].page == pagina) {
				workspace = $scope.workspaces[i];
			};

		}

		if(workspace === undefined) {
			$scope.addNewWorkspace(titulo, pagina, true, classePagina, paginaId);
		} else {
			desactiveAllWorkspace();

			workspace.active = true;
		};

	};

	// Obtém a workspace ativa
	function obterWorskspaceAtiva() {

		var temp = [];

		if($scope.workspaces && $scope.workspaces.length > 0) {

			temp = $scope.workspaces.filter(function(item){
				return item.active;
			});
		}

		$scope.workspaceAtiva = temp.length > 0 ? temp[0] : null;
	};


	//Método responsável por abrir a tela inicial de Notificação
	$scope.openNotificacoes = function(){

		$scope.openMenuPorNome($translate.instant('LABEL.NOTIFICACAO'));

	};

	//Métoto responsável por abir o favorito
	$scope.openWorkspaceFavorito = function(titulo, pagina, classePagina, paginaId){

		MenuRepository.buscarMenusPorPagina({pagina : pagina}).then(function(result) {

			$scope.openWorkspaceIfNotOpen(titulo, pagina, result.originalElement.classePagina, paginaId);

		});

	};


	//Método responsável por abir o menu através do nome
	$scope.openMenuPorNome = function(nome){

		MenuRepository.obterPorNome(nome).then(function(result) {

			var menuNotificacao = result.originalElement;

			$scope.openWorkspaceIfNotOpen(menuNotificacao.nome, menuNotificacao.pagina.pagina, menuNotificacao.classePagina, true);

		});
	};


	// Método responsavel por buscar as notificações do usuario
	$rootScope.findNotificacoes = function() {

		NotificacaoRepository.findNotificacaoUsuario().then(function(result) {

			$rootScope.quantidadeNotificacoesNaoLidas = result.length;

			$rootScope.listaNotificacoesUsuario = result;
		});
	};

	$rootScope.findNotificacoes();

	//Controla a visualização das notificações individualmente na nav-bar do topo da tela
	$scope.visualizarNotificacao = function(notificacao) {

		NotificacaoRepository.visualizar(notificacao.originalElement).then(function(result) {

			$scope.$openModalNotificacao(result.originalElement.assunto, result.originalElement.mensagem);

			$rootScope.findNotificacoes();
		});

	};

	$scope.$openModalNotificacao = function (title, message) {

		obterWorskspaceAtiva();

		var settings = {
			title: title,
			message: message,
		};

		angular.extend(settings);

		var classePagina = "";

		if(!$scope.workspaceAtiva){
			classePagina = 'mod-blue';
		}else{
			classePagina = $scope.workspaceAtiva.classePagina;
		}

		this.$modalNotificacao = settings;

		this.$modalNotificacaoInstace = $modal.open({
			templateUrl: 'modalNotificacao.html',
			windowClass: 'modal-buttons-top ' + classePagina,
			scope: this
		});
	};

	// FUNÇÃO PARA VISUALIZAR PERFIL NO CLICK DO MENU
	$scope.visualizarPerfil = function() {
		var workspace = undefined;
		var pagina = '/cit-portal-web/html/usuario/usuario.html';
		$scope.openWorkspaceIfNotOpen($translate.instant('LABEL.USUARIO'), pagina, 'mod-blue', true);

		$timeout(function() {
			$scope.setLoadingGet(true);
			for(var i = 0;  i < $scope.workspaces.length; i++){
				if($scope.workspaces[i].page == pagina) {
					workspace = $scope.workspaces[i];
				};
			}
			$scope.$showPageEditWorkspace(workspace);
			$timeout(function() {
				angular.element('#editUsuario').scope().getUsuario($scope.usuarioLogado, true);
			}, 1000);
	    });
	};

	// MODIFICA A INTERNACIONALIZAÇÃO DO TRANLATE
	$scope.changeLanguage = function (langKey, $e) {
		$e.preventDefault();
		$e.stopPropagation();

	    $translate.use(langKey);
	    $scope.language = langKey;

	    angular.element('.language-menu').removeClass('open');
	};

	// ABRE O SELECT DE IDIOMAS
	$scope.openDropdownLanguage = function ($e) {
		$e.preventDefault();
		$e.stopPropagation();

		angular.element('.language-menu').addClass('open');
	};

	// INICIA O LANGUAGE COM O DETERMINADO COMO PADRÃO NO APP.JS
	$scope.language = $translate.proposedLanguage();

	// MOSTRA GRITTER DE EXCEÇÃO
	$scope.showException = function(excecao) {
		var workspace = getWorkspaceActived();

		growl.error($translate.instant(excecao.ex ? 'MSG.ERRO_GENERICO' : excecao), {
			title: excecao.codigoErro ? $translate.instant('CODIGOERRO.'+excecao.codigoErro) : "Erro",
			referenceId: workspace ? workspace.id : 'exception',
			ttl: 10000
		});
	};

	// MOSTRA GRITTER DE NOTIFICAÇÃO
	$scope.showMensage = function(exception) {
		var workspace = getWorkspaceActived();

		if(exception.errosValidacao){

			angular.forEach(exception.errosValidacao, function(erroValidacao, key){

				var strErros = '';

				if(erroValidacao.codigoErro == '701' || erroValidacao.codigoErro == '700'){

					angular.forEach(erroValidacao.erros, function(erro, index){

						if(index == 0){
							strErros = $translate.instant(erro.label);
						}else {
							strErros = strErros + ", " + $translate.instant(erro.label);
						}
					});

				}

				growl.error(strErros, {
					title: $translate.instant('CODIGOERRO.'+erroValidacao.codigoErro),
					referenceId: workspace ? workspace.id : 'exception',
					ttl: 10000
				});
			});

		}else{
			growl.error($translate.instant(exception.message) + (exception.msgComplemento ? exception.msgComplemento : ''), {
				title: '',
				ttl: 10000,
				referenceId: workspace ? workspace.id : 'exception'
			});
		}
	};

	$scope.scrollTo = function(id) {
	    $location.hash(id);
	    $anchorScroll();
	};

	$scope.resetForm = function(idForm) {
		angular.element('#' + idForm).scope().resetForm();
	};


	$scope.atualizarLista = function(idForm) {
		angular.element('#' + idForm).scope().filterResult();
	};


	$scope.scrollToTopOfPage = function() {
		$window.scrollTo(0,0);
	};

	$scope.setLoadingSalva = function(loading) {
		$scope.scrollToTopOfPage();
		$scope.mensagemLoading = 'Salvando Aguarde...';
		$scope.isLoading = loading;
	};

	$scope.setLoadingPesquisa = function(loading) {
		$scope.scrollToTopOfPage();
		$scope.mensagemLoading = 'Pesquisando Aguarde...';
		$scope.isLoading = loading;
	};

	$scope.setLoadingRemove = function(loading) {
		$scope.scrollToTopOfPage();
		$scope.mensagemLoading = 'Removendo Aguarde...';
		$scope.isLoading = loading;
	};

	$scope.setLoadingGet = function(loading) {
		$scope.scrollToTopOfPage();
		$scope.mensagemLoading = 'Carregando Aguarde...';
		$scope.isLoading = loading;
	};

	$scope.setLoadingAtualiza = function(loading) {
		$scope.scrollToTopOfPage();
		$scope.mensagemLoading = 'Atualizando Aguarde...';
		$scope.isLoading = loading;
	};

	$scope.setLoading = function(loading) {
		$scope.scrollToTopOfPage();
		$scope.isLoading = loading;
	};

	$scope.setLoading = function(loading, mensagem) {
		$scope.scrollToTopOfPage();
		$scope.mensagemLoading = mensagem;
		$scope.isLoading = loading;
	};

	$scope.setLoadingRelatorio = function(loading) {
		$scope.scrollToTopOfPage();
		$scope.mensagemLoading = 'Gerando Aguarde...';
		$scope.isLoading = loading;
	};

	/**
	 * Metodo para mostrar mensagens para o usuario
	 *
	 * type: success|info|warning|error
	 */
	$scope.showAlert = function(type, message, title) {
		var workspace = this.workspace;
		if(workspace === undefined) {
			workspace = getWorkspaceActived();
		}

		if(growl[type] !== undefined) {
			growl[type](message, {
				'title': title,
				'referenceId': workspace.id
			});
		}
	};

	/**
	 * Metodo para mostrar mensagens para o usuario
	 */
	$scope.showAlertSucesso = function(type, message) {
		var title = $translate.instant('MSG.TITULO_MSG_SUCESSO');
		$scope.showAlert(type, message, title);
	};

	var setAllInactive = function() {
        angular.forEach($scope.workspaces, function(workspace) {
            workspace.active = false;
        });
    };

    $scope.setObjectId = function(id) {
    	$scope.idObejct = id;
    };

    // Variavel crianda para controlar as workspaces criadas
    var workspaceCount = 1;
    $scope.addNewWorkspace = function(name, page, novo, classePagina, paginaId, idObject, edit, idRelatorio){
    	// CONTROLA SE ESTÁ EDITANDO OU VIZUALIZANDO.
    	$scope.edit = edit;
    	if(novo){
    		$scope.idObejct = 0;
    	} else if(idObject){
    		$scope.idObejct = idObject;
    	}

    	$scope.newObejct = novo;

        var id = workspaceCount++;

        desactiveAllWorkspace();

        var workspace = {
        		id: id,
        		name: name,
        		page: page,
        		active: true,
        		favorito: false,
        		classePagina: classePagina,
        		paginaId: paginaId,
        		$showSearch: true,
        		$showEdit: false,
        		$showRelatorios : false,
        		idRelatorio : idRelatorio
        };

        //VALIDA SE A WORKSPACE E FAVORITA
        isWorkspaceFavorito(workspace);

        $scope.workspaces.push(workspace);

    };

    var addNewWorkspace = function() {
    	var id = workspaceCount++;

    	desactiveAllWorkspace();

    	$scope.workspaces.push({
    		id: id,
    		name: 'NAME',
    		active: true
    	});
    };

    var desactiveAllWorkspace = function () {
    	var workspaces = $scope.workspaces;

    	for(var i = 0; i < workspaces.length; i = i + 1) {
    		workspaces[i].active = false;
    	}
    };

    var getWorkspaceActived = function () {
    	var workspaces = $scope.workspaces;

    	for(var i = 0; i < workspaces.length; i = i + 1) {
    		if(workspaces[i].active === true) {
    			return workspaces[i];
    		}
    	}

    	return null;
    };

    $scope.$showPageEditWorkspace = function (workspace) {
    	workspace.$showEdit = true;
    	workspace.$showSearch = false;
    	workspace.$showOptionalPage = false;
    };

    $scope.$showPageSearchWorkspace = function (workspace) {
    	workspace.$showEdit = false;
    	workspace.$showSearch = true;
    	workspace.$showOptionalPage = false;
    };

    $scope.activeWorkspace = function(workspace) {
    	desactiveAllWorkspace();

    	workspace.active = true;
    };

    $scope.$showOptionalPageWorkspace = function (workspace) {
    	workspace.$showEdit = false;
    	workspace.$showSearch = false;
    	workspace.$showOptionalPage = true;
    };

    $scope.removeWorkspace = function(id) {
    	var workspaces = $scope.workspaces,
    		i;
        for (i = 0; i < workspaces.length; i = i + 1) {
			if (id === workspaces[i].id) {
				var workspaceTarget = workspaces[i],
					workspaceNext = workspaces[i + 1],
					workspacePrev = workspaces[i - 1];

				if(workspaceTarget.active === true) {

					if(workspaceNext !== undefined) {

						workspaceNext.active = true;

					} else if(workspacePrev !== undefined) {

						workspacePrev.active = true;

					}

				}

				if (workspaces[i].idRelatorio){

					delete $scope.arrayContentReports[workspaces[i].idRelatorio];

				}

				$scope.removeIntervalDashboard();

				$scope.workspaces.splice(i, 1);
			}
		}
    };
    //	Método responsável por remover os interval dos widget.
    $scope.removeIntervalDashboard = function () {

    	if($rootScope.listaWidgetIntervals){

    		angular.forEach($rootScope.listaWidgetIntervals, function(interval){

    			clearInterval(interval);
    		});

    		$rootScope.listaWidgetIntervals = [];
    	};
    };

//    Foi desabilitado o dashboard pelo fato de nao esta ainda funcionado
//    $scope.workspaces = [{ id: 1, name: "Dasboard", page: '/cit-portal-web/html/home/home.html', active:true  }];
    $scope.workspaces = [];

    $scope.addWorkspace = function () {
        setAllInactive();
        addNewWorkspace();
    };

    /**
     *	Funcao responsavel por validar se a Workspace adicionada ja e favorita - O class do favorito muda de acordo com o booleano
     */
    var isWorkspaceFavorito = function(workspace) {
    	var paginaUsuario = $scope.recuperarPaginaUsuario(workspace);

    	workspace.favorito = (paginaUsuario && paginaUsuario.favorito) ? true : false;
    };

    /**
     *	Responsável por adicionar a Workspace ativa como favorito ou nao conforme status atual
     */
    $scope.addFavorito = function(workspace){

    	var favoritoVH = {
    			pagina : getMontarPagina(workspace),
    			favorito : workspace.favorito
    	};

    	// SALVA OU REMOVE O FAVORITO
    	PaginaUsuarioRepository.saveFavorito(favoritoVH).then(function(result){
    		workspace.favorito = result.favorito;
    		// DETERMINA A LISTA DE PAGINAS DO USUARIO COM A ULTIMA VERSAO SALVA.
    		$scope.usuarioLogado.paginasUsuario = result.paginasUsuario;
    		//ATUALIZA AS ABAS REPETIDAS
    		atualizarWorkspacesFavorito(workspace);
    		//ATUALIZA A LISTA DE PÁGINAS FAVORITAS MENU
    		$scope.getPaginasFavoritadas();
    	});
    };

    /**
     *	Funcao responsavel por atualizar a lista de workspaces apos clicar em favoritar. Util para as abas repetidas
     */
    var atualizarWorkspacesFavorito = function(workspace){

    	for(var index = 0; index < $scope.workspaces.length; index++){

    		if(workspace.page == $scope.workspaces[index].page){

    			$scope.workspaces[index].favorito = workspace.favorito;
    		}
    	}
    };

    /**
     *	Responsavel por obter as páginas favoritas no menu
     */
    $scope.getPaginasFavoritadas = function(){

     	$scope.paginasUsuarioFavoritoTemp = [];

    	angular.forEach($scope.usuarioLogado.paginasUsuario, function(value, key){

    		if(value.favorito){
    			$scope.paginasUsuarioFavoritoTemp.push(value);
    		}
    	});
    };

	$scope.recuperarPaginaUsuario = function(workspace) {

		var result;

		angular.forEach($scope.usuarioLogado.paginasUsuario, function(value, key) {

			  if(angular.equals(value.pagina.pagina, workspace.page)){

				  result = value;

			  }

		});

		return result;

	};

	// MODAL
	$scope.$openModal = function (modalId, size) {

		obterWorskspaceAtiva();

		if(modalId === undefined) {
			return;
		}

		this.$modalInstance = $modal.open({
			templateUrl: modalId,
			size: size,
			windowClass: 'modal-buttons-top modal-no-backdrop ' + $scope.workspaceAtiva.classePagina,
			backdrop: false,
			scope: this
		});
	};

	$scope.$openModalConfirm = function (options) {

		obterWorskspaceAtiva();

		var settings = {
			title: $translate.instant('LABEL.CONFIRMACAO'),
			message: 'Deseja realmente excluir?',
			confirmText: 'Confirmar',
			cancelText: 'Cancelar',
			item: {},
			items: [],
			callback: function () { }
		};

		angular.extend(settings, options);

		this.$modalConfirm = settings;

		var classePagina = $scope.workspaceAtiva ? $scope.workspaceAtiva.classePagina : classePaginaPortal;

		this.$modalConfirmInstance = $modal.open({
			templateUrl: 'modalConfirm.html',
			windowClass: 'modal-buttons-top ' + classePagina,
			scope: this
		});
	};

	$scope.arrayContentReports = new Object();
	var idRelatorioCount = 0;
	// Metodo responsavel por fazer a requisicao relatorios no GRP em uma TAB, abrindo uma nova workspace posteriormente
	$scope.visualizarRelatorio = function(url, titleTab) {

		$scope.titleReport = titleTab;

		if(url.indexOf("download") < 0){
			var info = getAcrobatInfo();
			if(info.acrobat){
				url += '&download=false';
			}else{
				url += '&download=true';
				window.location = url;
			}
		}
		$scope.setLoadingRelatorio(true);
		$http.get(url, {responseType:'arraybuffer'}).success(function (response) {

			var file = new Blob([response], {type: 'application/pdf'});
			var fileURL = URL.createObjectURL(file);

			$scope.idRelatorio = ++idRelatorioCount;

			$scope.arrayContentReports[$scope.idRelatorio] = angular.copy($sce.trustAsResourceUrl(fileURL));

			obterWorskspaceAtiva();
			$timeout(function(){
				$scope.addNewWorkspace(titleTab, "relatorioTab.html", true, $scope.workspaceAtiva ? $scope.workspaceAtiva.classePagina : classePaginaPortal , undefined, undefined, undefined, $scope.idRelatorio);
				$scope.setLoading(false);
			});

		}).error(function(data, status){
			if(status == 412){
				$scope.showAlert("warning", $translate.instant("MSG.RELATORIO_SEM_DADOS") + " ", false );
			} else{
				$scope.showAlert("error", $translate.instant("MSG.ERRO_RELATORIO") + " ", false );
			}
			$scope.setLoading(false);

		});
	};

	// Metodo responsavel por fazer a requisicao relatorios no GRP em uma MODAL, abrindo uma nova $modalRelatorioInstace posteriormente
	$scope.visualizarRelatorioModal = function(url) {

		if(url.indexOf("download") < 0){
			var info = getAcrobatInfo();
			if(info.acrobat){
				url += '&download=false';
			}else{
				url += '&download=true';
				window.location = url;
			}
		}
		$scope.setLoadingRelatorio(true);
		$http.get(url, {responseType:'arraybuffer'}).success(function (response) {
			var file = new Blob([response], {type: 'application/pdf'});
			var fileURL = URL.createObjectURL(file);
			$scope.content = $sce.trustAsResourceUrl(fileURL);
			$scope.setLoading(false);
			$scope.$openModalRelatorio();
		}).error(function(data, status){
			if(status == 412){
				$scope.showAlert("warning", $translate.instant("MSG.RELATORIO_SEM_DADOS") + " ", false );
			} else{
				$scope.showAlert("error", $translate.instant("MSG.ERRO_RELATORIO") + " ", false );
			}
			$scope.setLoading(false);

		});
	};

	// Abri modal do relatorio
	$scope.$openModalRelatorio = function () {

		obterWorskspaceAtiva();

		this.$modalRelatorioInstace = $modal.open({
			templateUrl: 'relatorio.html',
			windowClass: 'modal-buttons-top ' + $scope.workspaceAtiva.classePagina,
			size: 'lg',
			scope: this
		});
	};

	// Controla a visualização de informacao do sistema
	$scope.visualizarInformacaoSistema = function(url) {

		IndexRepository.getInformacaoSistema().then(function(result){

			$scope.informacaoSistema = result;

			$scope.$openModalAbout();

		});
	};

	// Abri modal com informacoes do sistema
	$scope.$openModalAbout = function () {

		var classePaginaPortal;

		obterWorskspaceAtiva();

		if(!$scope.workspaceAtiva){
			classePaginaPortal = 'mod-blue';
		}

		var classePagina = $scope.workspaceAtiva ? $scope.workspaceAtiva.classePagina : classePaginaPortal;

		this.$modalAboutInstace = $modal.open({
			templateUrl: 'about.html',
			windowClass: 'modal-buttons-top ' + classePagina,
			size: 'md',
			scope: this
		});
	};

	// Método se da data da transferência é válida em relação ao mês vigente
	$scope.validarDataTransferencia = function(data) {

		if(data !== undefined){
			var dataComparacao = null;
			try {
				dataComparacao = angular.copy(converterStringEmDate(data));
			} catch(err) {
				dataComparacao = angular.copy(data);
			}
			var dataVigencia = converterStringEmDate($scope.usuarioLogado.organizacao.dataReferenciaVigente);

			try {
				return (dataComparacao.getMonth() == dataVigencia.getMonth()) && (dataComparacao.getFullYear() == dataVigencia.getFullYear());
			} catch(err) {
				return false;
			}

		}

	};

	//TODO refatorar assim que o login estiver pronto. Foi preciso timeout por ter que atualizar o menu com quantidade de favoritos, sem ele o usuario logado ainda nao foi completamente carregado.
	$timeout(function() {

		$scope.getPaginasFavoritadas();

		// Traduzindo os texto da paginacao
		paginationConfig.previousText = $translate.instant('LABEL.ANTERIOR');
		paginationConfig.nextText = $translate.instant('LABEL.PROXIMO');
		paginationConfig.firstText = $translate.instant('LABEL.PRIMEIRO');
		paginationConfig.lastText = $translate.instant('LABEL.ULTIMO');

		// Traduzindo os texto do datepicker
		datepickerPopupConfig.currentText = $translate.instant('LABEL.HOJE');
		datepickerPopupConfig.clearText = $translate.instant('LABEL.LIMPAR');
		datepickerPopupConfig.closeText = $translate.instant('LABEL.FECHAR');
    }, 1000);

	// SE FOR CHAMADO DA DIRECTIVA, SEMPRE MOSTRA OS FILTROS
	$scope.showAdvancedFilters = function(blnCallFromDirective, scope) {
		if(blnCallFromDirective){
			scope.$showAdvancedFilters = false;
		}

		scope.$showAdvancedFilters = !scope.$showAdvancedFilters;
	};

	// SCROLL TO
	$scope.scrollToCadastro = function (element, offset) {
		if(offset) {
			$window.scrollTo(0, offset);
		} else {
			$timeout(function() {
				$document.scrollToElementAnimated(angular.element(element));
			});
		}
	};

	// Variavel que é atribuida sempre que não contem workspace ativa
	var classePaginaPortal;
	if(!$scope.workspaceAtiva){
		classePaginaPortal = 'mod-blue';
	}

	// Atualiza o organização do usuário logado em cada modulo encontrado ativo
	$scope.atualizarOrganizacaoUsuario = function(organizacao){

		obterWorskspaceAtiva();
		var messageConfirmAtualizacaoOrganizacaoUsuario;
		if ($scope.workspaceAtiva){
			if ($scope.workspaces.length > 1){
				messageConfirmAtualizacaoOrganizacaoUsuario = $translate.instant("MSG.CONFIRMA_MUDANCA_AMBIENTE_TRABALHO_FECHAR_ABAS")
			} else {
				messageConfirmAtualizacaoOrganizacaoUsuario = $translate.instant("MSG.CONFIRMA_MUDANCA_AMBIENTE_TRABALHO_FECHAR_ABA")
			}
		} else {
			messageConfirmAtualizacaoOrganizacaoUsuario = $translate.instant("MSG.CONFIRMA_MUDANCA_AMBIENTE_TRABALHO");
		}

		$scope.$openModalConfirm({
			message: messageConfirmAtualizacaoOrganizacaoUsuario,
			callback: function () {

				$scope.setLoadingGet(true);

				var modulos = [];

				UsuarioRepository.getModulos().then(function(result){

					modulos = result;

					var numAtualizacaoUsuario = 0;

					modulos.forEach(function(modulo) {

						++numAtualizacaoUsuario;

						var restAngular = $injector.get(modulo.restAngular);

						UsuarioRepository.atualizarUsuario(restAngular, organizacao).then(function(){

							--numAtualizacaoUsuario;

							if (numAtualizacaoUsuario === 0) {

								UsuarioRepository.atualizarOrganizacaoUsuario(organizacao).then(function(result) {

									$scope.workspaces = [];

									$scope.usuarioLogado = result.originalElement;

									$scope.usuarioLogado.organizacao.dataReferenciaVigente = converterStringEmDate($scope.usuarioLogado.organizacao.dataReferenciaVigente);

									$scope.usuarioLogado.organizacoes = $filter('itemOrganizacaoDistinct')($scope.usuarioLogado.organizacoes, $scope.usuarioLogado.organizacao);

									$scope.$modalConfirmInstance.dismiss('cancel');

									$scope.setLoading(false);

								});
							}

						});

					});

				});

			}

		});

	};

	$scope.modulos = [];

	function carregarModulosAtivos() {
		UsuarioRepository.getModulos().then(function(result){
			$scope.modulos = result;
		});
	}

	carregarModulosAtivos();


	// VALIDA SE MODULO ESTA ATIVO POR NOME
	$scope.isModuloAtivo = function(baseUrl) {

		var ativo = false;

		$scope.modulos.forEach(function(modulo, key) {
			if(modulo.baseUrl === baseUrl){
				ativo = true;
			}
		});

		return ativo;
	};

	$scope.aplicarFiltro = function(value, filter, param) {
		  return $filter(filter)(value, param);
	};

}]);
