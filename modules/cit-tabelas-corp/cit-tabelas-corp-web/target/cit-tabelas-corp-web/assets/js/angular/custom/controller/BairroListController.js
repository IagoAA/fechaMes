'use strict';

citApp.controller('BairroListController',['$scope', 'BairroRepository', '$translate', function BairroListController($scope, BairroRepository, $translate) {
	$scope.$showAdvancedFilters = false;


	$scope.resetForm = function() {
		angular.element("#editBairro").scope().resetForm();
	};
	
	$scope.headers = [ {
		title : $translate.instant('LABEL.CODIGO'),
		value : 'codigo'
	}, {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.DATA_BLOQUEIO'),
		value : 'dataBloqueio',
		filter : 'dateBR'
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['id','codigo','nome', 'dataBloqueio'],
		filters : [{type: 'string', field: 'codigo'},{type: 'string', field: 'nome'}, {type: 'date', field: 'dataBloqueio'}]
	};

	// ABRI BAIRRO SELECIONADA
	$scope.abrirBairro = function(edit){
		var bairro = $scope.bairroChecked;

		if(!edit && !bairro) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !bairro) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editBairro').scope().getBairro(bairro, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


