'use strict';

citApp.controller('ComissaoListController', ['$scope', 'ComissaoRepository', '$translate', 'DominioRepository', 
                                                       function ComissaoListController($scope, ComissaoRepository, $translate, DominioRepository) {
	$scope.$showAdvancedFilters = false;
	
	var TIPO_COMISSAO = 'tipoComissao';

	$scope.resetForm = function() {
		angular.element("#searchComissaoEdit").scope().resetForm();
	};

	$scope.headers = [ {
		title : $translate.instant('LABEL.TIPO_COMISSAO'),
		value : 'dominioTipoComissao.descricao'
	},
    {
		title : $translate.instant('INVENTARIOCOMISSAO.CODIGO'),
		value : 'codigo'
	}, {
		title : $translate.instant('INVENTARIOCOMISSAO.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('INVENTARIOCOMISSAO.PRESIDENTE'),
		value : 'presidente.pessoa.nome'
	}, {
		title : $translate.instant('INVENTARIOCOMISSAO.NUMPROCESSO'),
		value : 'numProcesso'
	}, {
		title : $translate.instant('INVENTARIOCOMISSAO.PORTARIA'),
		value : 'portaria'
	}, {
		title : $translate.instant('INVENTARIOCOMISSAO.DATA_FORMACAO'),
		value : 'dataFormacao',
		filter : 'dateBR'
	}, {
		title : $translate.instant('INVENTARIOCOMISSAO.DATA_EXTINCAO'),
		value : 'dataExtincao',
		filter : 'dateBR'
	}];
	
	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['id', ,'dominioTipoComissao.descricao', 'codigo','nome','presidente.pessoa.nome','numProcesso','portaria','dataFormacao','dataExtincao'],
		filters : [ {type: 'string', field: 'dominioTipoComissao.descricao', listaDominio : [] }, {type: 'string', field: 'codigo'}, {type: 'string', field: 'nome'}, 
		            {type: 'string', field: 'presidente.pessoa.nome'}, {type: 'string', field: 'numProcesso'}, {type: 'numeric', field: 'portaria'}, 
		            {type: 'date-range', field: 'dataFormacao'}, {type: 'date-range', field: 'dataExtincao'}]
	};
	
	DominioRepository.findAllDominio(TIPO_COMISSAO).then(function(result) {
        $scope.filterCriteria.filters[0].listaDominio = result;
    });
	
	$scope.organizacao = $scope.usuarioLogado.organizacao;

	// ABRE COMISSAO SELECIONADA PASSANDO SE DEVE PERMITIR EDITAR OU NÃO
	$scope.abrirComissao = function(edit){
		var comissao = $scope.comissaoChecked;

		if(!edit && !comissao) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !comissao) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#searchComissaoEdit').scope().getComissao(comissao, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


