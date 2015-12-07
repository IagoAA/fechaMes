'use strict';

citApp.controller('ModuloController', ['$scope', 'ModuloRepository', '$translate', '$timeout',
		function ModuloController($scope, ModuloRepository, $translate, $timeout) {

    $scope.modulo = {};


    // Limpa formulário para novo cadastro
    $scope.resetForm = function() {
        $scope.limparModulo();
        $scope.edit = true;
        $timeout(function(){
            $scope.moduloForm.$submitted = false;
            $scope.moduloForm.$setPristine();
        });
    };

    // Atualiza pagina de pesquisa
    $scope.atualizaPaginaPesquisa = function () {
        angular.element('#searchModulo').scope().fetchResult();
    };

    // MODAL QUE CONFIRMA REMOVER DA CIDADE
    $scope.remove = function(modulo){
        $scope.modulo = modulo;
        $scope.$openModalConfirm({
            message: $translate.instant('MSG.CONFIRMA_EXCLUSAO'),
            callback: function () {
                ModuloRepository.remove($scope.modulo).then(function() {

                    $scope.$modalConfirmInstance.dismiss('cancel');
                    $scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));
                    angular.element('#searchModulo').scope().fetchResult();

                    $scope.resetForm();
                });
            }
        });
    };

    // SALVA O Modulo
    $scope.saveOrUpdate = function(){
        $scope.moduloForm.$submitted = true;

        //verifica se o formulario está valido para salvar
        if($scope.moduloForm.$valid){

            $scope.setLoadingSalva(true);

            ModuloRepository.save($scope.modulo).then(function(result) {
                $scope.modulo = result.originalElement;
                $scope.showAlert("success", $translate.instant('MSG.REGISTRO_SALVO'));
                $scope.moduloForm.$submitted = false;
            });
            $scope.setLoading(false);
        }else{
            //Mensagem de erro de campos obrigatorios não preenchidos
            $scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
        }
    };

    // Limpa o formulario preenchido
    $scope.limparModulo = function(){
        $scope.modulo = {};
    };

    // Consulta entidade e mostra no formulario
    $scope.getModulo = function(modulo, edit){
        $scope.setLoadingGet(true);

        ModuloRepository.get(modulo.id).then(function(result) {
            $scope.modulo = result.originalElement;
            $scope.edit = edit;
            $scope.setLoading(false);
        });
    };

}]);
