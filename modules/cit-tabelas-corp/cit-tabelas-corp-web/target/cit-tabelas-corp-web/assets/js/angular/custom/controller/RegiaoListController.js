'use strict';

citApp.controller('RegiaoListController', ['$scope', 'RegiaoRepository', '$translate', function RegiaoListController($scope, RegiaoRepository, $translate) {
	$scope.$showAdvancedFilters = false;

	$scope.resetForm = function() {
		angular.element("#editRegiao").scope().resetForm();
	};

	$scope.headers = [{
		value : 'codigo',
		title : $translate.instant('LABEL.CODIGO')
	},{
		value : 'nome',
		title : $translate.instant('LABEL.NOME')
	},{
		value : 'pais.nome',
		title : $translate.instant('LABEL.PAIS')
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
		fields: ['id','codigo','nome','pais.nome','dataBloqueio'],
		filters : [{type: 'string', field: 'codigo'},{type: 'string', field: 'nome'},{type: 'string', field: 'pais.nome'}, {type: 'date', field: 'dataBloqueio'}]
	};

	// ABRI REGIAO SELECIONADA
	$scope.abrirRegiao = function(edit){
		var regiao = $scope.regiaoChecked;

		if(!edit && !regiao) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !regiao) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editRegiao').scope().getRegiao(regiao, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


