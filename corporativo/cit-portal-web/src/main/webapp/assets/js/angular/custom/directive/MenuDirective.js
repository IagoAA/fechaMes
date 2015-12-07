citApp.directive('menu', ['$timeout', '$rootScope', 'MenuRepository', '$injector', '$translate', function($timeout, $rootScope, MenuRepository, $injector, $translate) {
    return {
    	replace: true,
    	templateUrl: "assets/js/angular/custom/directive/html/menu.html",
        link: function($scope, $element, $attrs) {
        	$scope.menuList = [];
        	$scope.searchMenuItens = [];
        	$scope.businessProcesses = [];
        	
        	// Declaração do filtro para processos de negócio
			var filterProcess = {
					start : 1,
					dir : 'asc',
					sort : 'name',
					limit : 999999,
					fields: ['id', 'category.name', 'description', 'flow.description', 'menu']
				};
			

    		// Se cit-esi estiver ativo, injeta dependências dinamicamente
    		var BusinessProcessRepository = undefined;
    		var RuntimeManagerRepository = undefined;
    		var FlowRepository = undefined;
    		try {
    			BusinessProcessRepository = $injector.get('BusinessProcessRepository');
    			RuntimeManagerRepository = $injector.get('RuntimeManagerRepository');
    			FlowRepository = $injector.get('FlowRepository');
    			$scope.runtimeManagerUtils = new RuntimeManagerUtils($scope, $scope, $translate, RuntimeManagerRepository);
			} catch (e) {
			}

			var esiActivated = BusinessProcessRepository && RuntimeManagerRepository && FlowRepository && $scope.runtimeManagerUtils;
//			esiActivated = false;
			
        	$scope.$watch('$navContainerShow', function () {
        		$rootScope.$navContainerShow = $scope.$navContainerShow;
        	});

        	var init = function() {
        		$scope.$navContainerShow = false;

        		MenuRepository.getAllMenusAtivos().then(function (menuList) {
        			$scope.menuList = menuList;

        			if (esiActivated) {
            			// Verifica se existe fluxo de recuperação de usuários no ESI
    					FlowRepository.getByName("esi_service_groups").then(function(result) {
    						var flowVersion = result && result.originalElement && result.originalElement.id ? result.originalElement : undefined;
    						if (flowVersion && (!flowVersion.flow.dataBloqueio || flowVersion.flow.dataBloqueio == null)) {
    	            			// Recupera processos de negócio do usuário que estejam associados a algum menu
    	    					BusinessProcessRepository.getProcessesWithMenu($scope.usuarioLogado.username, filterProcess).then(function(result) {
    	    						if (result && result.originalElement && result.originalElement.objects && result.originalElement.objects.length > 0) {
    	    							$scope.businessProcesses = result.originalElement.objects;
    	    						}
    	    						
    	    	        			sliceSubmenuInColumn($scope.menuList);
    	    					}, function() {
    	    						sliceSubmenuInColumn($scope.menuList);
    	    					});
    						}else{
	    	        			sliceSubmenuInColumn($scope.menuList);
    						}
    					});
        			}else{
	        			sliceSubmenuInColumn($scope.menuList);
        			}
        			
        		});
        	};

        	var sliceSubmenuInColumn = function (menuList) {
        		if(menuList && menuList.length > 0) {
        			var slice;
        			if(menuList.id === 35){
        				console.log("id: 35");
        			}

        			for(var i = 0; i < menuList.length; i++) {
        				var menu = menuList[i];
        				menu.submenuList = [];

        				// Adiciona processos de negócio como submenu
        				$scope.addBusinessProcess(menu);
        				
        				for(var j = 0; j < menu.submenu.length; j++) {
        					var submenu = menu.submenu[j];

        					if(!submenu.coluna) {
        						submenu.coluna = 0;
        					}

        					if(!menu.submenuList[submenu.coluna]) {
        						menu.submenuList[submenu.coluna] = [];
        					}

        					menu.submenuList[submenu.coluna].push(submenu);
        				}
        			}
        		}
        	};

        	var searchMenuInputDOMElement = $element.find('.nav-item-search input');
        	var focusAndClearSearchMenuInput = function () {
        		$scope.searchMenuInput = '';

        		$timeout(function () {
        			searchMenuInputDOMElement.focus();
        		});
        	};

        	$scope.openAndCloseNav = function() {
        		$scope.$navContainerShow = !$scope.$navContainerShow;

        		focusAndClearSearchMenuInput();
        	};

    		$scope.$on('menuController-menuDirective', function(event, menuList) {
    			if(menuList) {
    				MenuRepository.getAllMenusAtivos().then(function (menuList) {
            			$scope.menuList = menuList;

            			sliceSubmenuInColumn($scope.menuList);
            		});
    			}
       		  });

        	$scope.openLink = function (menu) {
        		if (menu.businessProcess) {
        			$scope.startBusinessProcess(menu);
        		}else{
	        		$scope.openWorkspaceIfNotOpen(
	        			(menu.pagina.nome ? menu.pagina.nome : menu.nome),
	        			(menu.pagina.pagina ? menu.pagina.pagina : ''),
	        			menu.classePagina);
        		}
        		
        		$scope.resetSearchMenu();
        		$scope.openAndCloseNav();
        	};

        	$scope.hasChildren = function (menu) {
        		if(menu.submenu) {
        			return menu.submenu.length > 0;
        		}

        		return false;
        	};

        	$scope.canSearchInServer = function () {
        		if($scope.searchMenuInput) {
        			return $scope.searchMenuInput.length >= 3;
        		}

        		return false;
        	};

        	$scope.searchMenu = function () {
        		if($scope.canSearchInServer()) {
        			var value = $scope.searchMenuInput;
        			var lastValue = $scope.searchMenuInput;
        			var valueForSearch = value; // replaceSpecialChars(value);

        			MenuRepository.findByName(valueForSearch).then(function (result) {
        				$timeout(function () {
        					if(lastValue === value) {
        						$scope.searchMenuItens = [];
        						$scope.searchMenuItens = result;
        					}
        				});
        			});
        		}
        	};

        	$scope.searchMenuIsEmpty = function () {
        		if($scope.searchMenuItens) {
        			return $scope.searchMenuItens.length <= 0;
        		}

        		return false;
        	};

        	$scope.resetSearchMenu = function () {
        		$scope.searchMenuInput = "";
        		$scope.searchMenuItens = [];
        	};

        	// Adiciona processos de negócio aos submenus
        	$scope.addBusinessProcess = function(mainMenu) {
        		if ($scope.businessProcesses.length > 0) {
        			var submenus = mainMenu.submenu;
        			var ordem = 0;
	        		angular.forEach(submenus, function (menu) {
		        		angular.forEach($scope.businessProcesses, function (businessProcess) {
		        			if (businessProcess.menu.id == menu.id) {
		        				ordem ++;
		        				menu.submenu.push({nome: businessProcess.description
				       						 	 , parent: menu
				    						 	 , ativo: true
				    						 	 , ordem: ordem
				    						 	 , coluna: menu.coluna
				    						 	 , cor: menu.cor
				    						 	 , cssMenu: menu.cssMenu
				    						 	 , cssMenuOpacity: menu.cssMenuOpacity
				    						 	 , classePagina: menu.classePagina
				    						 	 , businessProcess: businessProcess
				    						 	 , breadcrumbs: [mainMenu.nome, menu.nome, businessProcess.description]});		        				
		        			}
		        		});
	        		});
        		}
        	};
        	
        	// Inicia um processo de negócio
        	$scope.startBusinessProcess = function(menu){
        		var process = menu.businessProcess;
        		
        		var pagina = '/cit-esi-web/assets/js/angular/custom/directive/html/startBusinessProcess.html';

        		if ($scope.runtimeManagerUtils.existsWorkspace(pagina)) {
        			$scope.showAlert('warning', $translate.instant('ESI.MSG.JA_EXISTE_TELA_PROCESSO'));
        			return ;
        		}

        		BusinessProcessRepository.get(process.id).then(function(result) {
        			$rootScope.businessProcess = result.originalElement;
        			$rootScope.controllerScope = $scope;
        			$rootScope.listaBreadcrumb = menu.breadcrumbs;

        			$scope.addNewWorkspace($rootScope.businessProcess.description, pagina, true, menu.classePagina, $rootScope.businessProcess);
        		});
        	};
        	
        	init();
        }
    };
}]);