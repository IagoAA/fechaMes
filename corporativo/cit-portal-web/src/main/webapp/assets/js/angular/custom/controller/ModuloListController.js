'use strict';

citApp.controller('ModuloListController', ['$scope', 'ModuloRepository', '$translate',
		function ModuloListController($scope, ModuloRepository, $translate) {

	$scope.$showAdvancedFilters = false;

	$scope.modulos = [];
	$scope.moduloChecked = {};

	$scope.resetForm = function() {
		angular.element("#editModulo").scope().resetForm();
	};

	$scope.headers = [ {
		title : 'Nome',
		value : 'nome'
	}, {
		title : 'Caminho',
		value : 'caminho'
	}, {
		title : 'URL',
		value : 'baseUrl'
	}, {
		title : 'Restangular',
		value : 'restAngular'
	}, {
		title : 'Ativo',
		value : 'habilitado',
		filter : 'booleanSimNao'
	} ];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields : [ 'id', 'nome', 'caminho', 'baseUrl', 'restAngular', 'habilitado' ],
		filters : [ {
			type : 'string',
			field : 'nome'
		}, {
			type : 'string',
			field : 'caminho'
		}, {
			type : 'string',
			field : 'baseUrl'
		}, {
			type : 'string',
			field : 'restAngular'
		}, {
			type : 'boolean',
			field : 'habilitado'
		} ]
	};

	// ABRI CIDADE SELECIONADA
	$scope.abrirModulo = function(edit) {
		var modulo = $scope.moduloChecked;

		if (!edit && !modulo) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if (edit && !modulo) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editModulo').scope().getModulo(modulo, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);
