'use strict';

citApp.controller('EstadoListController', ['$scope', '$translate', 'EstadoRepository', function EstadoListController($scope, $translate, EstadoRepository) {
	$scope.$showAdvancedFilters = false;

	$scope.resetForm = function() {
		angular.element("#editEstado").scope().resetForm();
	};
	
	$scope.headers = [ {
		title : $translate.instant('LABEL.CODIGO'),
		value : 'codigo'
	}, {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.SIGLA'),
		value : 'sigla'
	}, {
		title : $translate.instant('LABEL.PAIS'),
		value : 'regiao.pais.nome'
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
		fields: ['id','codigo','nome','sigla','regiao.pais.nome','dataBloqueio'],
		filters : [{type: 'string', field: 'codigo'},{type: 'string', field: 'nome'},{type: 'string', field: 'sigla'},{type: 'string', field: 'regiao.pais.nome'}, {type: 'date', field: 'dataBloqueio'}]
	};

	// ABRIR ESTADO SELECIONADO
	$scope.abrirEstado = function(edit) {
		var estado = $scope.estadoChecked;

		if(!edit && !estado) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !estado) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editEstado').scope().getEstado(estado, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


