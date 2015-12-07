'use strict';

citApp.controller('CidadeListController',['$scope', 'CidadeRepository', '$translate', function CidadeListController($scope, CidadeRepository, $translate) {
	$scope.$showAdvancedFilters = false;


	$scope.resetForm = function() {
		angular.element("#editCidade").scope().resetForm();
	};

	$scope.headers = [{
		value : 'codigo',
		title : $translate.instant('LABEL.CODIGO')
	},{
		value : 'nome',
		title : $translate.instant('LABEL.NOME')
	},{
		value : 'estado.sigla',
		title : $translate.instant('LABEL.ESTADO')
	},{
		value : 'estado.regiao.pais.nome',
		title : $translate.instant('LABEL.PAIS')
	},{
		value : 'estado.regiao.nome',
		title : $translate.instant('LABEL.REGIAO')
	}/*,{
		value : 'codigoIBGE',
		title : $translate.instant('LABEL.CODIGO_IBGE')
	}*/,{
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
		fields: ['id','codigo','nome','estado.sigla','estado.regiao.pais.nome','estado.regiao.nome','codigoIBGE','dataBloqueio'],
		filters : [{type: 'string', field: 'codigo'},{type: 'string', field: 'nome'},{type: 'string', field: 'estado.sigla'},{type: 'string', field: 'estado.regiao.pais.nome'},{type: 'string', field: 'estado.regiao.nome'}, {type: 'date', field: 'dataBloqueio'}]
	};

	// ABRI CIDADE SELECIONADA
	$scope.abrirCidade = function(edit){
		var cidade = $scope.cidadeChecked;

		if(!edit && !cidade) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !cidade) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editCidade').scope().getCidade(cidade, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


