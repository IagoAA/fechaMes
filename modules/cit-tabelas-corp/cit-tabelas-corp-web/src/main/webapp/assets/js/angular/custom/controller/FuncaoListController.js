'use strict';

citApp.controller('FuncaoListController', ['$scope', 'FuncaoRepository', '$translate', '$timeout', 'DominioRepository', function FuncaoListController($scope, FuncaoRepository, $translate, $timeout, DominioRepository) {
	$scope.$showAdvancedFilters = false;

    $scope.resetForm = function() {
        angular.element("#editFuncao").scope().resetForm();
    };

    $scope.headers = [ {title : $translate.instant('LABEL.CODIGO'), value : 'codigo' } , {title : $translate.instant('LABEL.NOME'), value : 'nome' }];

    $scope.filterCriteria = {
        start : 1,
        dir : 'asc',
        sort : 'id',
        limit : 10,
        fields: ['id', 'codigo', 'nome'],
        filters : [ {type : 'numeric', field : 'codigo' } , {type : 'string', field : 'nome' }]
    };
    
    // ABRI Funcao SELECIONADA
    $scope.abrirVisualizar = function(edit){
        var funcao = $scope.funcaoChecked;

        if(!funcao) {
            $scope.showAlert('warning', !edit ? $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO') : $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
            return;
        }
        angular.element('#editFuncao').scope().getFuncao(funcao, edit);
        $scope.$showPageEditWorkspace($scope.workspace);
    };
}]);
