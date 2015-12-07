'use strict';

citApp.controller('DominioListController', ['$scope', 'DominioRepository', '$translate', '$modal', '$timeout',
		function DominioListController($scope, DominioRepository, $translate, $modal, $timeout) {

	$scope.headers = [ {
		title : $translate.instant('LABEL.CHAVE'),
		value : 'chave'
	}, {
		title : $translate.instant('LABEL.CODIGO'),
		value : 'codigo'
	}, {
		title : $translate.instant('LABEL.NOME_DOMINIO'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.DESCRICAO'),
		value : 'descricao'
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
		fields: ['id', 'chave', 'codigo', 'nome', 'descricao', 'dataBloqueio'],
		filters : [{
			type: 'string',
			field: 'chave'
		}, {
			type: 'numeric',
			field: 'codigo'
		}, {
			type: 'string',
			field: 'nome'
		}, {
			type: 'string',
			field: 'descricao'
		}, {
			type: 'date',
			field: 'dataBloqueio'
		}]
	};

	// reseta o formulario
	$scope.resetForm = function(){
		angular.element("#dominioControllerId").scope().resetForm();
	};

	// Método responsável por selecionar o dominio e abrir a modal bloqueio dominio
	$scope.selecionaDominio = function(dominio){
		$scope.dominio = dominio;
		$scope.$openModal("bloquear_dominio.html");
	};

	// Abre o dominio selecionado na tela de cadastro
	$scope.abrirDominio = function(edit){
		var dominio = $scope.dominioChecked;

		if(!edit && !dominio) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !dominio) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}


		angular.element('#dominioControllerId').scope().getDominio(dominio.id, edit);

		$scope.$showPageEditWorkspace($scope.workspace);
	};

}]);


