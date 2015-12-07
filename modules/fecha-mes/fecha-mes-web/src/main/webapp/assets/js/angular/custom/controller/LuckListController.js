'use strict';

citApp.controller('LuckListController',['$scope', '$translate', 'LuckRepository', function LuckListController($scope, $translate, LuckRepository) {
	$scope.$showAdvancedFilters = false;

	// Chama controlleredit para limpar o formulario de cadastro
	$scope.resetForm = function() {
		angular.element("#editLuck").scope().resetForm();
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

	// ABRI LUCK SELECIONADO
	$scope.abrirLuck = function(edit){
		var luck = $scope.luckChecked;

		if(!edit && !luck) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !luck) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editLuck').scope().getLuck(luck, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


