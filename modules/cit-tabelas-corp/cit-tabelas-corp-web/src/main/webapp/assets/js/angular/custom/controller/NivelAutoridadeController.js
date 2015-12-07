'use strict';

citApp.controller('NivelAutoridadeController', ["$scope", "GrupoRepository", "NivelAutoridadeRepository", "$filter", "$translate", "$timeout", 
              function NivelAutoridadeController($scope, GrupoRepository, NivelAutoridadeRepository, $filter, $translate, $timeout) {

    $scope.nivelAutoridade = {};
	
    // Limpa formulário para novo cadastro
    $scope.resetForm = function() {		
        $scope.limparNivelAutoridade();
        $scope.edit = true;
        $timeout(function(){
            $scope.nivelAutoridadeForm.$submitted = false;
            $scope.nivelAutoridadeForm.$setPristine();
        });
    };

    // Atualiza pagina de pesquisa
    $scope.atualizaPaginaPesquisa = function () {
        angular.element('#searchNivelAutoridade').scope().fetchResult();
    };

    // MODAL QUE CONFIRMA REMOVER
    $scope.remove = function(nivelAutoridade){
        $scope.nivelAutoridade = nivelAutoridade;
        $scope.$openModalConfirm({
            message: $translate.instant('MSG.CONFIRMA_EXCLUSAO'),
            callback: function () {
                NivelAutoridadeRepository.remove($scope.nivelAutoridade).then(function() {

                    $scope.$modalConfirmInstance.dismiss('cancel');
                    $scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));
                    angular.element('#searchNivelAutoridade').scope().fetchResult();

                    $scope.resetForm();
                });
            }
        });
    };

    // SALVA O NIVELAUTORIDADE
    $scope.saveOrUpdate = function(){
        $scope.nivelAutoridadeForm.$submitted = true;

        //verifica se o formulario esta valido para salvar
        if($scope.nivelAutoridadeForm.$valid){

            $scope.setLoadingSalva(true);

            NivelAutoridadeRepository.save($scope.nivelAutoridade).then(function(result) {
                $scope.nivelAutoridade = result.originalElement;
                
                $scope.showAlert("success", $translate.instant('MSG.REGISTRO_SALVO'));
                $scope.nivelAutoridadeForm.$submitted = false;
                
                $scope.getNivelAutoridade(result.originalElement, true);
                
                $scope.setLoading(false);
            });
        }else{
            //Mensagem de erro de campos obrigatorios não preenchidos
            $scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
        }
    };

    // Limpa o formulario preenchido
    $scope.limparNivelAutoridade = function(){
        $scope.nivelAutoridade = {};
    };

    // Consulta entidade e mostra no formulario
    $scope.getNivelAutoridade = function(nivelAutoridade, edit){
        $scope.setLoadingGet(true);

        NivelAutoridadeRepository.get(nivelAutoridade.id).then(function(result) {
            $scope.nivelAutoridade = result.originalElement;
            $scope.edit = edit;
            
            $scope.nivelAutoridade.grupos = _.sortBy($scope.nivelAutoridade.grupos, function (nivelAutoridadeGrupo) {
            	return nivelAutoridadeGrupo.grupo.nome;
            });
            
            $scope.setLoading(false);
        });
    };
        
    //#### INICIO METODOS NIVELAUTORIDADEGRUPO ####//
    //FIND PARA AUTOCOMPLETE DO GRUPO
    $scope.findGrupo =  function(value){
      return GrupoRepository.findAutoComplete('nome', value).then(function(result) {
    	  var gruposNivelAutoridadeAtivos = _.where($scope.nivelAutoridade.grupos, {dataInativo: null});
    	  
    	  return $filter('idNotEqualGrupo')(result,  gruposNivelAutoridadeAtivos);
      });
    };

    //ADICIONA O GRUPO SELECIONADO
    $scope.setGrupo = function(item){
      // SE NAO POSSUI ARRAY DE NIVELAUTORIDADEGRUPO, CRIA O ATRIBUTO
      if(!$scope.nivelAutoridade.grupos){
          $scope.nivelAutoridade.grupos = [];
      }
      
      var index = _.findIndex($scope.nivelAutoridade.grupos, {grupo: { id: item.id }});
      if(index >= 0) {
    	  $scope.nivelAutoridade.grupos[index].dataInativo = null;
      } else {
    	  $scope.nivelAutoridade.grupos.push({grupo : item, dataInativo: null});
      }
      
      $timeout(function(){
        $scope.grupo = null;
      });
    };
    
    //REMOVE O GRUPO SELECIONADO
    $scope.removerGrupo = function(){
      if(!$scope.grupoChecked) {
        $scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));
        return;
      } else {
        $scope.$openModalConfirm({
          message: $translate.instant('MSG.DESEJA_EXCLUIR_ITENS'),
          callback: function () {
            $scope.$modalConfirmInstance.dismiss('cancel');
            
            $scope.nivelAutoridade.grupos[$scope.grupoChecked.$index].dataInativo = $filter('date')(new Date(), 'dd/MM/yyyy');
            $scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));
            $scope.grupoChecked = {};
          }
        });
      }
    };
    //#### FIM METODOS NIVELAUTORIDADEGRUPO ####//
    

}]);
