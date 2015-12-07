'use strict';

citApp.controller('NivelAutoridadeListController', ["$scope", "NivelAutoridadeRepository", "$translate", "$timeout", "DominioRepository", 
			function NivelAutoridadeListController($scope, NivelAutoridadeRepository, $translate, $timeout, DominioRepository) {
    $scope.$showAdvancedFilters = false;

    $scope.resetForm = function() {
        angular.element("#editNivelAutoridade").scope().resetForm();
    };

    $scope.headers = [ {title : $translate.instant('LABEL.NOME'), value : 'nome' } ,
                       {title : $translate.instant('LABEL.HIERARQUIA'), value : 'hierarquia' }];

    $scope.filterCriteria = {
        start : 1,
        dir : 'asc',
        sort : 'id',
        limit : 10,
        fields: ['id', 'nome', 'hierarquia'],
        filters : [ {type : 'string', field : 'nome' } , 
                    {type : 'numeric', field : 'hierarquia' }]
    };
    
    // ABRI NivelAutoridade SELECIONADA
    $scope.abrirVisualizar = function(edit){
        var nivelAutoridade = $scope.nivelAutoridadeChecked;

        if(!nivelAutoridade) {
            $scope.showAlert('warning', !edit ? $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO') : $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
            return;
        }
        angular.element('#editNivelAutoridade').scope().getNivelAutoridade(nivelAutoridade, edit);
        $scope.$showPageEditWorkspace($scope.workspace);
    };
}]);
