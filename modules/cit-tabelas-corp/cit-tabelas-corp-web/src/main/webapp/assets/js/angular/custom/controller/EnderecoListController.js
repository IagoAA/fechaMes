'use strict';

citApp.controller('EnderecoListController',['$scope', 'EnderecoRepository', 'DominioRepository', '$translate', function EnderecoListController($scope, EnderecoRepository, DominioRepository, $translate) {
	$scope.$showAdvancedFilters = false;


	$scope.resetForm = function() {
		angular.element("#editEndereco").scope().resetForm();
	};

	$scope.headers = [ {
		title : $translate.instant('LABEL.CODIGO'),
		value : 'codigo'
	}, {
		title : $translate.instant('LABEL.TIPO_ENDERECO'),
		value : 'dominioTipoEndereco.descricao'
	}, {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.CEP'),
		value : 'cep',
		filter : 'brCep'
	}, {
		title : $translate.instant('LABEL.LOGRADOURO'),
		value : 'logradouro'
	}, {
		title : $translate.instant('LABEL.CIDADE'),
		value : 'cidade.nome'
	}, {
		title : $translate.instant('LABEL.BAIRRO'),
		value : 'bairro.nome'
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
		fields: ['id','codigo','dominioTipoEndereco.descricao','nome','cep','logradouro','cidade.nome','bairro.nome','dataBloqueio'],
		filters : [{type: 'string', field: 'codigo'},
		           {type: 'string', field: 'dominioTipoEndereco.descricao', listaDominio : []},
		           {type: 'string', field: 'nome'},
		           {type: 'string', field: 'cep'},
		           {type: 'string', field: 'logradouro'},
		           {type: 'string', field: 'cidade.nome'},
		           {type: 'string', field: 'bairro.nome'},
		           {type: 'date', field: 'dataBloqueio'}]
	};

	DominioRepository.findAllDominio('tipoEndereco').then(function(result) {
		$scope.filterCriteria.filters[1].listaDominio = result;
	});

	// ABRI ENDERECO SELECIONADA
	$scope.abrirEndereco = function(edit){
		var endereco = $scope.enderecoChecked;

		if(!edit && !endereco) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !endereco) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editEndereco').scope().getEndereco(endereco, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};

	// Consulta entidade e mostra no formulario
	$scope.getEndereco = function(endereco, edit){
		$scope.setLoadingGet(true);

		EnderecoRepository.get(endereco.id).then(function(result) {
			$scope.endereco = result.originalElement;

			$scope.carregarPaisRegiaoEstadoCidade();

			if($scope.endereco.dataBloqueio === null || $scope.endereco.dataBloqueio === undefined){
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = edit;
			}else{
				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
			}

			$scope.pgEdit = edit;

			$scope.setLoading(false);
		});
	};

	DominioRepository.findAllDominio('tipoEndereco').then(function(result) {
		$scope.dominiosTipoEndereco = result;
	});
}]);


