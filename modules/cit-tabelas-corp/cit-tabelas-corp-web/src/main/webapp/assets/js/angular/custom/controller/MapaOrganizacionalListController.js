'use strict';

citApp.controller('MapaOrganizacionalListController', ['$scope', 'MapaOrganizacionalRepository', '$translate', function MapaOrganizacionalListController($scope, MapaOrganizacionalRepository, $translate) {
	$scope.$showAdvancedFilters = false;

	$scope.headers = [ {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.DATA_INICIO'),
		value : 'dataInicio',
		filter : 'dateBR'
	}, {
		title : $translate.instant('LABEL.DATA_FIM'),
		value : 'dataFim',
		filter : 'dateBR'
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['dataFim','dataInicio','nome','id'],
		filters : [{type: 'string', field: 'nome'}, {type: 'date', field: 'dataInicio'}, {type: 'date', field: 'dataFim'}]
	};

	$scope.resetForm = function(){
		angular.element("#mapaOrganizacionalControllerId").scope().resetForm();
	};

	$scope.abrirMapaOrganizacional = function(visualizar){
		var mapaOrganizacional = $scope.mapaOrganizacionailChecked;

		if(!visualizar && !mapaOrganizacional) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(visualizar && !mapaOrganizacional) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#mapaOrganizacionalControllerId').scope().getMapaOrganizacional(mapaOrganizacional.id, visualizar);
		$scope.$showPageEditWorkspace($scope.workspace);
	};

}]);


