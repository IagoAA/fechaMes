'use strict';

citApp.controller('MenuController', ['$scope', '$translate', '$timeout', 'MenuRepository', 'MenuFileRepository', 'DominioRepository', '$rootScope',  'PrivilegioRepository', 'GrupoRepository', '$filter',
        function MenuController($scope, $translate, $timeout, MenuRepository, MenuFileRepository, DominioRepository, $rootScope, PrivilegioRepository, GrupoRepository, $filter) {

	var MENU_MAIN_TYPE = 'menuMain';
	$scope.MENU_MAIN_TYPE = MENU_MAIN_TYPE;

	var SUBMENU_TITLE_TYPE = 'submenuTitle';
	$scope.SUBMENU_TITLE_TYPE = SUBMENU_TITLE_TYPE;

	var SUBMENU_LINK_TYPE = 'submenuLink';
	$scope.SUBMENU_LINK_TYPE = SUBMENU_LINK_TYPE;

	$scope.menuList = [];
	$scope.menuSelected = {};
	$scope.menu = {};
	$scope.$translate = $translate;

	$scope.filterCriteriaGrupo = {
			start : 1,
			dir : 'asc',
			sort : 'id',
			limit : 10,
			fields: ['id', 'nome'],
			filters : [{type: 'numeric',
				field: 'organizacao.id',
				value : $scope.usuarioLogado.organizacao.id}]
		};

	$scope.radioValueList = [{
		"text": $translate.instant('LABEL.SIM'),
		"value": true
	}, {
		"text": $translate.instant('LABEL.NAO'),
		"value": false
	}];

	var closeModal = function () {
		if($scope.$modalInstance) {
			$scope.$modalInstance.dismiss('cancel');
		}
	};

	var closeModalConfirm = function () {
		if($scope.$modalConfirmInstance) {
			$scope.$modalConfirmInstance.dismiss('cancel');
		}
	};

	var saveOrdemMainMenu = function (menuList) {
		$scope.setLoadingGet(true);
		MenuRepository.saveOrdem(menuList).then(function (result) {

			$scope.menuList = result;

			$scope.showAlert("success", $translate.instant('MSG.MENU_ORDEM_SALVA'));

			$scope.setLoadingGet(false);
		});
	};

	var saveOrdemChildrenMenu = function (menuList) {
		$scope.setLoadingGet(true);
		MenuRepository.saveOrdem(menuList).then(function (result) {

			$scope.showAlert("success", $translate.instant('MSG.MENU_ORDEM_SALVA'));

			$scope.setLoadingGet(false);

			$scope.selectMenu($scope.menuSelected);
		});
	};

	// Inicia a tela de menu
	$scope.init = function () {

		$scope.menuList = [];
		$scope.menu = {};

		MenuRepository.getAllParent().then(function (menuList) {
			if(menuList && menuList.length > 0) {
				for(var i = 0; i < menuList.length; i++) {
					menuList[i].originalElement.$type = 'menuMain';
					$scope.menuList.push(menuList[i].originalElement);
				}
			}

			$timeout(function(){
				$scope.$emit("menuController-menuDirective",$scope.menuList);
			});

		});
	};

	$scope.sliceBigger = 0;
	$scope.getOrdemSubmenuTitle = function ($indexParent, $index, submenuList) {
		if($indexParent === 0) {
			$scope.sliceBigger = submenuList.length;

			return $index;
		}

		return $indexParent * $index + $scope.sliceBigger * $indexParent;
	};

	$scope.treeOptions = {
		accept: function(sourceNode, destNodes, destIndex) {
			var data = sourceNode.$modelValue;
			var destType = destNodes.$element.attr('data-type');
			return (data.$type == destType);
		},
		dropped: function(event) {
			var destNodes = event.dest.nodesScope;

			var menuList = [];
			if(destNodes.$element.attr('data-type') === MENU_MAIN_TYPE) {
				menuList = $scope.menuList;

				saveOrdemMainMenu(menuList);
			} else if(destNodes.$element.attr('data-type') === SUBMENU_TITLE_TYPE || destNodes.$element.attr('data-type') === SUBMENU_LINK_TYPE) {
				menuList = $scope.menuSelected.submenu;

				for(var i = 0; i < menuList.length; i++) {
					menuList[i].parent = {
						id: $scope.menuSelected.id
					};
				}

				saveOrdemChildrenMenu(menuList);
			}
		}
	};

	$scope.addMenuModule = function () {
		$scope.menu = {
			ativo: true
		};

		$scope.$openModal('html/menu/addOrUpdateMenuModule.html');
	};

	$scope.editMenuModule = function (menu) {
		$scope.setLoadingGet(true);

		MenuRepository.get(menu.id).then(function (menu) {
			$scope.menu = menu.originalElement;

			 $scope.findGrupo();

			 $scope.findPrivilegios();

			$scope.setLoadingGet(false);

			$scope.$openModal('html/menu/addOrUpdateMenuModule.html');
		});
	};

	$scope.addMenuTitle = function () {
		$scope.menu = {
			ativo: true,
			parent: {
				id: $scope.menuSelected.id
			}
		};

		$scope.$openModal('html/menu/addOrUpdateMenuTitle.html');
	};

	$scope.editMenuTitle = function (menu) {
		$scope.setLoadingGet(true);

		MenuRepository.get(menu.id).then(function (menu) {
			$scope.menu = menu.originalElement;

			$scope.menu.parent = {
				id: $scope.menuSelected.id
			};

			 $scope.findGrupo();

			 $scope.findPrivilegios();

			$scope.setLoadingGet(false);

			$scope.$openModal('html/menu/addOrUpdateMenuTitle.html');
		});
	};

	$scope.addMenuLink = function (parent) {
		$scope.menu = {
			ativo: true,
			parent: {
				id: parent.id
			}
		};

		$scope.$openModal('html/menu/addOrUpdateMenuLink.html', 'lg');
	};

	$scope.editMenuLink = function (menu, parent) {
		$scope.setLoadingGet(true);

		MenuRepository.get(menu.id).then(function (menu) {
			$scope.menu = menu.originalElement;

			$scope.menu.parent = {
				id: parent.id
			};

			 $scope.findGrupo();

			 $scope.findPrivilegios();

			$scope.setLoadingGet(false);

			$scope.$openModal('html/menu/addOrUpdateMenuLink.html', 'lg');
		});
	};

	$scope.selectMenu = function (menu) {
		$scope.setLoadingGet(true);

		loadSubmenuList(menu);
	};

	var loadSubmenuList = function (menu) {
		MenuRepository.findMenuByIdParent(menu).then(function (submenuList) {
			menu.submenuList = [];

			submenuList = addDataTypesSubmenu(submenuList);
			menu.submenu = submenuList;

			menu.submenuList = separateSubmenuByColumn(submenuList);
			fillTheColumns(menu.submenuList);

			$scope.menuSelected = menu;

			$scope.setLoadingGet(false);
		});
	};

	var addDataTypesSubmenu = function (submenuList) {
		var thatSubmenuList = [];

		for(var i = 0; i < submenuList.length; i++) {
			var submenu = submenuList[i].originalElement;
			submenu.$type = 'submenuTitle';

			if(submenu.submenu && submenu.submenu.length > 0) {
				for(var j = 0; j < submenu.submenu.length; j++) {
					submenu.submenu[j].$type = 'submenuLink';
				}
			}

			thatSubmenuList[i] = submenu;
		}

		return thatSubmenuList;
	};

	var separateSubmenuByColumn = function (submenuList) {
		var thatSubmenuList = [];

		if(submenuList && submenuList.length > 0) {
			for(var i = 0; i < submenuList.length; i++) {
				var submenu = submenuList[i];

				if(!submenu.coluna) {
					submenu.coluna = 0;
				}

				if(!thatSubmenuList[submenu.coluna]) {
					thatSubmenuList[submenu.coluna] = [];
				}

				thatSubmenuList[submenu.coluna].push(submenu);
			}

			return thatSubmenuList;
		}
	};

	var fillTheColumns = function (submenuList) {
		if(submenuList && submenuList.length > 0) {
			for(var i = 0; i < 3; i++) {
				if(!submenuList[i]) {
					submenuList[i] = [];
					submenuList[i].push({});
				}
			}
		}
	};

	$scope.removeMenu = function (menu) {
		$scope.$openModalConfirm({
			callback: function () {
				MenuRepository.remove(menu).then(function () {
					closeModalConfirm();

					$scope.showAlert("success", $translate.instant('MSG.MENU_EXCLUIDO_COM_SUCESSO'));

					if($scope.menuSelected.id === menu.id) {
						$scope.menuSelected = {};
					} else if($scope.menuSelected.id){
						$scope.selectMenu($scope.menuSelected);
					}

					$scope.init();
				});
			}
		});
	};

	$scope.saveOrUpdateMenu = function () {
		$scope.menuForm.$submitted = true;

		if($scope.menuForm.$valid) {
			$scope.setLoadingSalva(true);

			MenuRepository.save($scope.menu).then(function (menu) {
				$scope.menuForm.$submitted = false;
				$scope.menuForm.$setPristine(false);

				closeModal();

				$scope.setLoadingSalva(false);
				$scope.showAlert("success", $translate.instant('MSG.MENU_SALVO_COM_SUCESSO'));

				if($scope.menuSelected && $scope.menuSelected.id) {
					$scope.selectMenu($scope.menuSelected);
				}

				$scope.init();

			});
		} else {
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Menu file
	var getListMenuFile = function () {
		$scope.setLoadingGet(true);

		MenuFileRepository.findByIdMenu($scope.menu).then(function (menuFileList) {
			$scope.menu.includes = menuFileList;

			$scope.setLoadingGet(false);
		});
	};

	$scope.menuFileList = function (menu) {
		$scope.menu = menu;

		DominioRepository.findAllDominio('tipoFile').then(function (dominioMenuFileList) {
			$scope.dominioMenuFileList = [];

			for(var i = 0; i < dominioMenuFileList.length; i++) {
				$scope.dominioMenuFileList.push(dominioMenuFileList[i].originalElement);
			}
		});

		getListMenuFile();

		$scope.$openModal('html/menu/menuFileList.html', 'lg');
	};

	$scope.editMenuFile = function (include) {
		$scope.setLoadingGet(true);

		MenuFileRepository.get(include.id).then(function (include) {
			$scope.include = include.originalElement;

			$scope.setLoadingGet(false);
		});
	};

	$scope.removeMenuFile = function (include) {
		$scope.$openModalConfirm({
			callback: function () {
				MenuFileRepository.remove(include).then(function () {

					closeModalConfirm();

					$scope.showAlert("success", $translate.instant('MSG.MENU_FILE_EXCLUIDO_COM_SUCESSO'));

					getListMenuFile();

					$rootScope.isMenuAlterado = true;
				});
			}
		});
	};

	$scope.saveOrUpdateMenuFile = function () {
		$scope.menuFileForm.$submitted = true;

		if($scope.menuFileForm.$valid) {
			$scope.setLoadingSalva(true);

			$scope.include.menu = {
				id: $scope.menu.id
			};

			MenuFileRepository.save($scope.include).then(function (include) {
				$scope.include = {};

				$scope.menuFileForm.$submitted = false;
				$scope.menuFileForm.$setPristine(false);

				$scope.setLoadingSalva(false);
				$scope.showAlert("success", $translate.instant('MSG.MENU_FILE_SALVO_COM_SUCESSO'));

				getListMenuFile();
			});
		} else {
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	$scope.init();


	//#####  PRIVILEGIOS DO MENU #####

	//PERMISSÕES

	//Método responsável por listar os privilegios do sistema
	$scope.findPrivilegios = function(){
		$scope.privilegios = [];
		PrivilegioRepository.getList().then(function(result) {
			$scope.prepareMenuPrivilegio(result);
		});
	};

	$scope.prepareMenuPrivilegio = function(list) {

		   for(var i = 0; i < list.length; i++) {
			   var item = list[i];
			   var menuPrivilegio = {};

			   menuPrivilegio.privilegio = item.originalElement ? item.originalElement : item;

			   $scope.addToMenuPrivilegioIfNotExist(menuPrivilegio);
		   }

		   $scope.privilegios = _.sortBy($scope.privilegios, function(item) {
			   return item.privilegio.nome;
		   });

	   };

		$scope.addToMenuPrivilegioIfNotExist = function(menuPrivilegio) {

		   var menuPrivilegioForSearch = {
				   privilegio: {
					  id: menuPrivilegio.privilegio.id
				   }
			   };

		   var indexPrivilegio = _.findIndex($scope.menu.menuPrivilegios, menuPrivilegioForSearch);

		    var indexPrivilegioListado = _.findIndex($scope.privilegios, menuPrivilegioForSearch);

		   if(indexPrivilegio < 0 && indexPrivilegioListado < 0) {
			   $scope.privilegios.push(menuPrivilegio);
		   }
	   };
	//GRUPO
   $scope.searchByGrupo = function(nome) {
 	if(nome && nome != ""){
	   GrupoRepository.findAutoComplete("nome", nome).then(function(result) {
		   $scope.prepareMenuGrupo(result);
	   });

 	};
   };

	//Método responsável por listar os grupos do sistema
   $scope.findGrupo = function(){
	   $scope.grupos = [];

	   GrupoRepository.getListPage($scope.filterCriteriaGrupo).then(function(result) {

		   $scope.prepareMenuGrupo(result.originalElement.objects);
	   });

   };

   $scope.prepareMenuGrupo = function(list) {

	   for(var i = 0; i < list.length; i++) {
		   var item = list[i];
		   var menuGrupo = {};

		   menuGrupo.grupo = item.originalElement ? item.originalElement : item;

		   $scope.addToMenuGrupoIfNotExist(menuGrupo);
	   }

	   $scope.grupos = _.sortBy($scope.grupos, function(item) {
		   return item.grupo.nome;
	   });
   };

	$scope.addToMenuGrupoIfNotExist = function(menuGrupo) {

	   var menuGrupoForSearch = {
			   grupo: {
				  id: menuGrupo.grupo.id
			   }
		   };

	   var indexGrupo = _.findIndex($scope.menu.menuGrupos, menuGrupoForSearch);

	    var indexGrupoListado = _.findIndex($scope.grupos, menuGrupoForSearch);

	   if(indexGrupo < 0 && indexGrupoListado < 0) {
		   $scope.grupos.push(menuGrupo);
	   }
   };
   
   $scope.aplicarFiltroUpper = function() {
	   
	   $scope.menu.chave = $filter("uppercase")($scope.menu.chave);
   };

}]);