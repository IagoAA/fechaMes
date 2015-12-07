'use strict';

citApp.controller('PaisListController',['$scope', '$translate', 'PaisRepository', function PaisListController($scope, $translate, PaisRepository) {
	$scope.$showAdvancedFilters = false;

	// Chama controlleredit para limpar o formulario de cadastro
	$scope.resetForm = function() {
		angular.element("#editPais").scope().resetForm();
	};

	$scope.headers = [ {
		title : $translate.instant('LABEL.CODIGO'),
		value : 'codigo'
	}, {
		title : $translate.instant('LABEL.SIGLA'),
		value : 'sigla'
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
		fields: ['id','codigo','sigla','nome','dataBloqueio'],
		filters : [{type: 'string', field: 'codigo'},{type: 'string', field: 'sigla'},{type: 'string', field: 'nome'}, {type: 'date', field: 'dataBloqueio'}]
	};

	// ABRI PAIS SELECIONADO
	$scope.abrirPais = function(edit){
		var pais = $scope.paisChecked;

		if(!edit && !pais) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !pais) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editPais').scope().getPais(pais, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


