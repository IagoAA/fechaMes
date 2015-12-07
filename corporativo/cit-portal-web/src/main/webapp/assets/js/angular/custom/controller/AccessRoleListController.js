'use strict';

citApp.controller('AccessRoleListController', ['$scope', 'AccessRoleRepository', '$translate',
        function AccessRoleListController($scope, AccessRoleRepository, $translate) {
	// CHECK CIDADE
	$scope.removeCheckedRegioes = function () {
		var accessRoles = $scope.accessRoles;

		accessRoles.forEach(function (accessRole) {
			accessRole.$checked = false;
		});
	};
	$scope.getCheckedAccessRole = function () {
		var accessRoleChecked = null;
		var accessRoles = $scope.accessRoles;

		accessRoles.forEach(function (accessRole) {
			if(accessRole.$checked) {
				accessRoleChecked = accessRole;
				return ;
			}
		});

		return accessRoleChecked;
	};
	$scope.checkAccessRole = function (accessRole) {
		$scope.removeCheckedRegioes();

		accessRole.$checked = true;
	};

	$scope.totalPages = 10;
	$scope.totalItens = 0;
	$scope.limit = 10;

	$scope.resetForm = function() {
		angular.element("#editAccessRole").scope().resetForm();
	}

	$scope.headers = [{
		value : '',
		title : ''
	},{
        title : 'Url',
        value : $translate.instant('LABEL.URL')
    },{
        title : 'Roles',
        value : $translate.instant('LABEL.ROLES')
    },{
		value : 'dataInativo',
		title : $translate.instant('LABEL.DATA_INATIVO')
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['id', 'url','roles','dataInativo'],
		filters : [{type: 'string', field: 'url'},{type: 'string', field: 'roles'}]
	};

	// Will be called when filtering the grid, will reset the page number to one
	$scope.filterResult = function() {
		$scope.filterCriteria.start = 1;
		$scope.fetchResult().then(function() {
			// The request fires correctly but sometimes the ui doesn't update,
			// that's a fix
			$scope.filterCriteria.start = 1;
		});
	};

	// call back function that we passed to our custom directive sortBy, will be
	// called when clicking on any field to sort
	$scope.onSort = function(sortedBy, sortDir) {
		$scope.filterCriteria.dir = sortDir;
		$scope.filterCriteria.sort = sortedBy;
		$scope.filterCriteria.start = 1;
		$scope.fetchResult().then(function() {
			// The request fires correctly but sometimes the ui doesn't update,
			// that's a fix
			$scope.filterCriteria.start = 1;
		});
	};

	// RECUPERA LISTA DE PAIS E SETA RESULTADO NA GRID
	$scope.fetchResult = function(page) {
		$scope.setLoadingPesquisa(true);

		return AccessRoleRepository.getListPage($scope.filterCriteria).then(function(result) {
			$scope.accessRoles = result.originalElement.objects;
			$scope.totalPages = result.originalElement.totalPages;
			$scope.totalItens =  result.originalElement.totalItens;

			$scope.setLoading(false);
		}, function() {
			$scope.accessRoles = [];
			$scope.totalPages = 0;
			$scope.totalItens = 0;
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO CIDADE
	$scope.remove = function(){
		var accessRole = $scope.getCheckedAccessRole();

		if(accessRole === null) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));

			return ;
		}

		angular.element('#editAccessRole').scope().remove(accessRole);
	};

	// BUSCA LISTA DE CIDADE
	$scope.fetchResult();

	// ABRI CIDADE SELECIONADA
	$scope.abrirAccessRole = function(edit){
		var accessRole = $scope.getCheckedAccessRole();

		if(accessRole === null) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_APENAS_UM_ITEM_PARA_EDICAO'));

			return;
		}

		angular.element('#editAccessRole').scope().getAccessRole(accessRole, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


