'use strict';
citApp.controller('PessoaListController',['$scope', 'PessoaRepository', 'DominioRepository', '$translate', '$timeout', function PessoaListController($scope, PessoaRepository, DominioRepository, $translate, $timeout) {
	$scope.$showAdvancedFilters = false;

	$scope.headers = [ {
		title : $translate.instant('LABEL.TIPO_PESSOA'),
		value : 'dominioPessoa.descricao'
	}, {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.EMAIL'),
		value : 'email'
	}];

	// Iniciar propriedades padrão da consulta que sera enviada para o servidor.
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields : [ 'id', 'dominioPessoa.descricao', 'nome', 'email' ],
		filters : [ {
			type : 'string',
			field : 'dominioPessoa.descricao'
		}, {
			type : 'string',
			field : 'nome'
		}, {
			type : 'string',
			field : 'email',
		}]
	};

	$scope.abrirPessoa = function(visualizar, workspace){

		var pessoa = $scope.pessoaChecked;

		if(!pessoa) {
			$scope.showAlert('warning', visualizar ?  $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO') : $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));

			return;
		}

		if($scope.pessoaChecked.dataBloqueio){
			$scope.showAlert('error', $translate.instant('VALIDACAO.REGISTRO_BLOQUEADO_NAO_PODE_SER_ALTERADO'));

			return;
		}

		$scope.$showPageEditWorkspace(workspace);
		angular.element('#pessoaEdit').scope().getPessoa(pessoa.id, visualizar);

	};

}]);