'use strict';

citApp.controller('ServicoListController',['$scope', '$translate', 'ServicoRepository', function ServicoListController($scope, $translate, ServicoRepository) {
	$scope.$showAdvancedFilters = false;

	// Chama controlleredit para limpar o formulario de cadastro
	$scope.resetForm = function() {
		angular.element("#editServico").scope().resetForm();
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

	// ABRI SERVICO SELECIONADO
	$scope.abrirServico = function(edit){
		var servico = $scope.servicoChecked;

		if(!servico) {

			 $scope.showAlert('warning', !edit ? $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO') : $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			 return;
		} else {

			angular.element('#editServico').scope().getServico(servico, edit);
			$scope.$showPageEditWorkspace($scope.workspace);
		}
	};
}]);


