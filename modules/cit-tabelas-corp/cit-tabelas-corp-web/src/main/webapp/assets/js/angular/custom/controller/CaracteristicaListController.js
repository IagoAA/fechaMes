'use strict';

citApp.controller('CaracteristicaListController',['$scope', 'CaracteristicaRepository', 'DominioRepository', '$translate',  function CaracteristicaListController($scope, CaracteristicaRepository, DominioRepository, $translate) {
	$scope.$showAdvancedFilters = false;

	$scope.resetForm = function() {
		angular.element("#editCaracteristica").scope().resetForm();
	};
	
	$scope.headers = [
  	{
  		value : 'descricao',
  		title : $translate.instant('LABEL.DESCRICAO')
  	},{
  		value : 'tamanho',
  		title : $translate.instant('LABEL.TAMANHO')
  	},{
  		value : 'expressaoRegular',
  		title : $translate.instant('LABEL.EXPRESSAO_REGULAR')
  	}/*,{
  		value : 'dominioTipoRestricao.descricao',
  		title : $translate.instant('LABEL.TIPO_RESTRICAO')
  	}*/,{
  		value : 'dominioTipoDado.descricao',
  		title : $translate.instant('LABEL.TIPO_DADO')
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
		fields: ['id', 'descricao','tamanho','expressaoRegular', 'dominioTipoRestricao.descricao', 'dominioTipoDado.descricao', 'dataBloqueio'],
		filters : [{type: 'string', field: 'descricao'},
		           {type: 'numeric', field: 'tamanho'},
		           {type: 'string', field: 'expressaoRegular'},
//		           {type: 'string', field: 'dominioTipoRestricao.descricao', listaDominio : []},
		           {type: 'string', field: 'dominioTipoDado.descricao', listaDominio : []},
		           {type: 'date', field: 'dataBloqueio'}]
	};

	DominioRepository.findAllDominio('tipoRestricao').then(function(result) {
		$scope.filterCriteria.filters[3].listaDominio = result;
	});
	
	DominioRepository.findAllDominio('tipoDado').then(function(result) {
		$scope.filterCriteria.filters[4].listaDominio = result;
	});

	// ABRI CARACTERISTICA SELECIONADA
	$scope.abrirCaracteristica = function(edit){
		var caracteristica = $scope.caracteristicaChecked;

		if(!edit && !caracteristica) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !caracteristica) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editCaracteristica').scope().getCaracteristica(caracteristica, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


