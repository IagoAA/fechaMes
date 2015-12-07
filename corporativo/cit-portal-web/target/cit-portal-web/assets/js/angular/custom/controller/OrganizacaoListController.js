'use strict';

citApp.controller('OrganizacaoListController',['$scope', 'OrganizacaoRepository', '$translate',  function OrganizacaoListController($scope, OrganizacaoRepository, $translate) {
	$scope.$showAdvancedFilters = false;

	$scope.resetForm = function() {
		angular.element("#editOrganizacao").scope().resetForm();
	};

	$scope.headers = [ {
		title : $translate.instant('LABEL.CODIGO'),
		value : 'codigo',
		tamanho: 10
	}, {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome',
		tamanho: 30
	}, {
		title : $translate.instant('LABEL.SIGLA'),
		value : 'sigla',
		tamanho: 15
	}, {
		title : $translate.instant('LABEL.REFERENCIA_VIGENTE'),
		value : 'dataReferenciaVigente',
		filter : 'dateMesBR',
		tamanho : 15
	}, {
		title : $translate.instant('LABEL.DATA_INICIO'),
		value : 'dataInicio',
		filter : 'dateBR',
		tamanho : 15
	}, {
		title : $translate.instant('LABEL.DATA_FIM'),
		value : 'dataFim',
		filter : 'dateBR',
		tamanho : 15
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'codigo',
		limit : 10,
		fields: ['id','codigo', 'nome','sigla', 'dataReferenciaVigente', 'dataInicio', 'dataFim'],
		filters : [{type: 'string', field: 'codigo'},
		           {type: 'string', field: 'nome'},
		           {type: 'string', field: 'sigla'},
		           {type: 'date-range', field: 'dataReferenciaVigente'},
		           {type: 'date-range', field: 'dataInicio'},
		           {type: 'date-range', field: 'dataFim'}]
	};

	// ABRE ORGANIZACAO
	$scope.abrirOrganizacao = function(edit){
		var organizacao = $scope.organizacaoChecked;

		if(!edit && !organizacao) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}
		
		if(edit && !organizacao) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}		

		angular.element('#editOrganizacao').scope().getOrganizacao(organizacao, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


