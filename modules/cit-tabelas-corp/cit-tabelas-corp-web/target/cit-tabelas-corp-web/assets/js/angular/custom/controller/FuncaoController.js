'use strict';

citApp.controller('FuncaoController',['$scope', 'FuncaoRepository', '$translate', '$timeout', function FuncaoController($scope, FuncaoRepository, $translate, $timeout) {
    $scope.funcao = {};

    // Limpa formulário para novo cadastro
    $scope.resetForm = function() {
        $scope.limparFuncao();
        $scope.edit = true;
        $timeout(function(){
            $scope.funcaoForm.$submitted = false;
            $scope.funcaoForm.$setPristine();
        });
    };

    // Atualiza pagina de pesquisa
    $scope.atualizaPaginaPesquisa = function () {
        angular.element('#searchFuncao').scope().fetchResult();
    };

    // MODAL QUE CONFIRMA REMOVER DA CIDADE
    $scope.remove = function(funcao){
        $scope.funcao = funcao;
        $scope.$openModalConfirm({
            message: $translate.instant('MSG.CONFIRMA_EXCLUSAO'),
            callback: function () {
                FuncaoRepository.remove($scope.funcao).then(function() {
                    $scope.$modalConfirmInstance.dismiss('cancel');
                    $scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));
                    angular.element('#searchFuncao').scope().fetchResult();

                    $scope.resetForm();
                });
            }
        });
    };

    // SALVA O Funcao
    $scope.saveOrUpdate = function(){
        $scope.funcaoForm.$submitted = true;

        //verifica se o formulario está valido para salvar
        if($scope.funcaoForm.$valid){

            $scope.setLoadingSalva(true);

            FuncaoRepository.save($scope.funcao).then(function(result) {
                $scope.funcao = result.originalElement;
                $scope.showAlert("success", $translate.instant('MSG.REGISTRO_SALVO'));
                $scope.funcaoForm.$submitted = false;
            });
            $scope.setLoading(false);
        }else{
            //Mensagem de erro de campos obrigatorios não preenchidos
            $scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
        }
    };

    // Limpa o formulario preenchido
    $scope.limparFuncao = function(){
        $scope.funcao = {};
    };

    // Consulta entidade e mostra no formulario
    $scope.getFuncao = function(funcao, edit){
        $scope.setLoadingGet(true);

        FuncaoRepository.get(funcao.id).then(function(result) {
            $scope.funcao = result.originalElement;
            $scope.edit = edit;
            $scope.setLoading(false);
        });
    };
}]);
