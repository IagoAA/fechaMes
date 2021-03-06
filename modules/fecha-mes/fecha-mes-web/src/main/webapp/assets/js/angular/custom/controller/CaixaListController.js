'use strict';

citApp.controller('CaixaListController', ['$scope', 'CaixaRepository', '$translate', function CaixaListController($scope, CaixaRepository, $translate) {
	$scope.$showAdvancedFilters = false;

	$scope.resetForm = function() {
		angular.element("#editCaixa").scope().resetForm();
	};

	$scope.headers = [{
		value : 'codigo',
		title : $translate.instant('LABEL.CODIGO')
	},{
		value : 'nome',
		title : $translate.instant('LABEL.NOME')
	},{
		value : 'luck.nome',
		title : $translate.instant('LABEL.LUCK')
	},{
		value : 'dataBloqueio',
		title : $translate.instant('LABEL.DATA_BLOQUEIO'),
		filter : 'dateBR'
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['id','codigo','nome','luck.nome','dataBloqueio'],
		filters : [{type: 'string', field: 'codigo'},{type: 'string', field: 'nome'},{type: 'string', field: 'luck.nome'}, {type: 'date', field: 'dataBloqueio'}]
	};

	// ABRI REGIAO SELECIONADA
	$scope.abrirCaixa = function(edit){
		var luck = $scope.luckChecked;

		if(!edit && !luck) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !luck) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editCaixa').scope().getCaixa(luck, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


