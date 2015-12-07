'use strict';

citApp.controller('InternacionalizacaoController', ['$scope', 'InternacionalizacaoRepository', '$translate', '$timeout', 'ModuloRepository', 'DominioRepository',
		function InternacionalizacaoController($scope, InternacionalizacaoRepository, $translate, $timeout, ModuloRepository, DominioRepository ) {

    $scope.internacionalizacao = {};

    $scope.isBloquear = false;
    $scope.isDesbloquear = false;

    // Limpa formulário para novo cadastro
    $scope.resetForm = function() {
        $scope.limparInternacionalizacao();
        $scope.jsonPortal = {};
        $scope.converterJson = false;
        $scope.edit = true;
        $scope.isBloquear = false;
        $scope.isDesbloquear = $scope.isBloquear;
        $timeout(function(){
            $scope.internacionalizacaoForm.$submitted = false;
            $scope.internacionalizacaoForm.$setPristine();
        });
    };

    // Atualiza pagina de pesquisa
    $scope.atualizaPaginaPesquisa = function () {
        angular.element('#searchInternacionalizacao').scope().fetchResult();
    };

    // MODAL QUE CONFIRMA REMOVER DA CIDADE
    $scope.remove = function(internacionalizacao){
        $scope.internacionalizacao = internacionalizacao;
        $scope.$openModalConfirm({
            message: $translate.instant('MSG.CONFIRMA_EXCLUSAO'),
            callback: function () {
                InternacionalizacaoRepository.remove($scope.internacionalizacao).then(function() {

                    $scope.$modalConfirmInstance.dismiss('cancel');
                    $scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));
                    angular.element('#searchInternacionalizacao').scope().fetchResult();

                    $scope.resetForm();
                });
            }
        });
    };

    $scope.converterJsonEmMap = function(){
    	var jsonPortal = {};
		var jsonConvert = JSON.parse($scope.jsonPortalStr);
		angular.forEach(jsonConvert, function(chaveInicial, valorChaveInicial) {
			if(chaveInicial instanceof Object){
				angular.forEach(chaveInicial, function(valor1, chave1) {
					if(valor1 instanceof Object){
						angular.forEach(valor1, function(valor2, chave2) {
        					var chaveFinal = valorChaveInicial+'.'+chave1+'.'+chave2 ;
            				jsonPortal[chaveFinal] = valor2;
        				});
					}else{
						var chaveFinal = valorChaveInicial+'.'+chave1;
        				jsonPortal[chaveFinal] = valor1;
					}
    			});
			}else{
				var chaveFinal = chaveInicial;
				jsonPortal[chaveFinal] = valorChaveInicial;
			}
		});
		return jsonPortal;
    }

    // SALVA O Internacionalizacao
    $scope.saveOrUpdate = function(){
        $scope.internacionalizacaoForm.$submitted = true;

        //verifica se o formulario está valido para salvar
        if($scope.internacionalizacaoForm.$valid){

        	if($scope.converterJson){

        		$scope.internacionalizacao.jsonPortal = $scope.converterJsonEmMap();;

        	}
        	 $scope.setLoadingSalva(true);

             InternacionalizacaoRepository.save($scope.internacionalizacao).then(function(result) {
            	 $scope.resetForm();
            	 $scope.internacionalizacao = result.originalElement;
                 $scope.isBloquear = true;
                 $scope.isDesbloquear = !$scope.isBloquear;
                 $scope.showAlert("success", $translate.instant('MSG.REGISTRO_SALVO'));
                 $scope.internacionalizacaoForm.$submitted = false;
                 $scope.setLoading(false);

                 if($scope.converterJson){
                	 $scope.$showPageSearchWorkspace($scope.$parent.workspace);
                	 $scope.atualizaPaginaPesquisa();
                 }
             });

        }else{
            //Mensagem de erro de campos obrigatorios não preenchidos
            $scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
        }
    };

    // Limpa o formulario preenchido
    $scope.limparInternacionalizacao = function(){
        $scope.internacionalizacao = {};
    };

    // Consulta entidade e mostra no formulario
    $scope.getInternacionalizacao = function(internacionalizacao, edit){
        $scope.setLoadingGet(true);

        InternacionalizacaoRepository.get(internacionalizacao.id).then(function(result) {
            $scope.internacionalizacao = result.originalElement;

            if($scope.internacionalizacao.dataBloqueio === null || $scope.internacionalizacao.dataBloqueio === undefined){
                $scope.isBloquear = true;
                $scope.isDesbloquear = !$scope.isBloquear;
                $scope.edit = edit;
            }else{
                $scope.isBloquear = false;
                $scope.isDesbloquear = !$scope.isBloquear;
                $scope.edit = false;
            }

            $scope.setLoading(false);
        });
    };

    $scope.findAutoCompleteModulo = function(value){
        return ModuloRepository.findAutoComplete('nome', value).then(function(result) {
            return result;
        });
    }

    DominioRepository.findAllDominio('tipoIdioma').then(function(result) {
        $scope.tipoDominioIdiomaList = result;
    });

}]);
