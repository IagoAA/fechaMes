'use strict';

citApp.controller('InternacionalizacaoListController', ['$scope', 'InternacionalizacaoRepository', '$translate', '$timeout', 'DominioRepository',
		function InternacionalizacaoListController($scope, InternacionalizacaoRepository, $translate, $timeout, DominioRepository) {
    $scope.$showAdvancedFilters = false;

    $scope.resetForm = function() {
        angular.element("#editInternacionalizacao").scope().resetForm();
    };

    $scope.headers = [ {
		title : $translate.instant('LABEL.MODULO'),
		value : 'modulo.nome'
	}, {
		title : $translate.instant('LABEL.TIPODOMINIOIDIOMA'),
		value : 'tipoDominioIdioma.descricao'
	}, {
		title : $translate.instant('LABEL.CHAVE'),
		value : 'chave'
	}, {
		title : $translate.instant('LABEL.VALOR'),
		value : 'valor'
	}];

    // default criteria that will be sent to the server
    $scope.filterCriteria = {
        start : 1,
        dir : 'asc',
        sort : 'id',
        limit : 15,
        fields: ['id', 'modulo.nome', 'tipoDominioIdioma.descricao', 'chave', 'valor'],
        filters : [ {type : 'string', field : 'modulo.nome'} ,
                    {type : 'string', field : 'tipoDominioIdioma.descricao', listaDominio : []} ,
                    {type : 'string', field : 'chave'} ,
                    {type : 'string', field : 'valor'}]
    };

    DominioRepository.findAllDominio('tipoIdioma').then(function(result) {
    	$scope.filterCriteria.filters[1].listaDominio = result;
    });

    $scope.abrirInternacionalizacao = function(edit){
        var internacionalizacao = $scope.internacionalizacaoChecked;

        if(!internacionalizacao) {
            $scope.showAlert('warning', !edit ? $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO') : $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
            return;
        }

        angular.element('#editInternacionalizacao').scope().getInternacionalizacao(internacionalizacao, edit);
        $scope.$showPageEditWorkspace($scope.workspace);
    };

    $scope.atualizarLabels = function(){

    	$scope.setLoadingSalva(true);

    	InternacionalizacaoRepository.atualizarLabelSistema().then(function(result) {

    		$scope.setLoading(false);

    		if(result){

    			$scope.showAlert('success', $translate.instant('MSG.LABELS_SISTEMA_ATUALIZADO'));
    		}

    	});
    }

}]);
