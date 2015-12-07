'use strict';

citApp.controller('CaracteristicaController',['$scope', 'CaracteristicaRepository', 'DominioRepository', '$translate', '$timeout', '$filter',  function CaracteristicaController($scope, CaracteristicaRepository, DominioRepository, $translate, $timeout, $filter) {
	$scope.caracteristica = {};
	$scope.tiposRestricao = {};
	$scope.tiposDado = {};
	$scope.chavesDominio = [];

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;
	
	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparCaracteristica();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.caracteristicaForm.$submitted = false;
			$scope.caracteristicaForm.$setPristine();
		});
	};
	
	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchCaracteristica').scope().fetchResult();
	};
	
	// Limpa Campos Dinamicos do Formulario
	$scope.limpaCamposDependentes = function (){
		if($scope.caracteristica.tamanho !== null && $scope.caracteristica.tamanho !== undefined){
			$scope.caracteristica.tamanho = null;
		}
		if($scope.caracteristica.chaveDominio !== null && $scope.caracteristica.chaveDominio !== undefined){
			$scope.caracteristica.chaveDominio = null;
		}
		if($scope.caracteristica.expressaoRegular !== null && $scope.caracteristica.expressaoRegular !== undefined){
			$scope.caracteristica.expressaoRegular = null;
		}
	};
	
	// Bloqueia a Caracteristica
	$scope.confirmarBloqueioCaracteristica = function(){
		
		if(!$scope.caracteristica.dataBloqueio){
			
			$scope.showAlert('error', $translate.instant('MSG.MG011'));
		}else{
			
			$scope.setLoadingSalva(true);
			
			CaracteristicaRepository.save($scope.caracteristica).then(function(result){
				$scope.setLoadingSalva(false);
				
				$scope.caracteristica = result.originalElement;
				
				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
				$scope.showAlert('success', $translate.instant('MSG.SUCESSO_BLOQUEIO_CARACTERISTICA'));
			});
		}
	};

	// Desbloqueia a Região 
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);
		
		$scope.caracteristica.dataBloqueio =  null;
		
		CaracteristicaRepository.save($scope.caracteristica).then(function(result){
			$scope.setLoadingSalva(false);
			
			$scope.caracteristica = result.originalElement;
			
			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO_CARACTERISTICA'));
		});
	};
	
	DominioRepository.findAllDominio('tipoRestricao').then(function(result) {
		$scope.dominiosTipoRestricao = result;
	});
	
	DominioRepository.findAllDominio('tipoDado').then(function(result) {
		$scope.dominiosTipoDado = $filter('tipoDadoCaracteristica')(result);
	});
	
	DominioRepository.findAllChavesDominio().then(function(result) {
		$scope.chavesDominio = result;
	});
	
	// MODAL QUE CONFIRMA REMOVER DA CARACTERISTICA
	$scope.remove = function(caracteristica){
		$scope.$openModalConfirm({ 
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_CARACTERISTICA'), 
			callback: function () {
				CaracteristicaRepository.remove(caracteristica).then(function() {
					$scope.caracteristica = {};
					
					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_CARACTERISTICA_EXCLUIDO'));
					angular.element('#searchCaracteristica').scope().fetchResult();
					
					$scope.resetForm();
				});
			}
		});
	};
	
	// SALVA O Caracteristica
	$scope.saveOrUpdate = function(){
		$scope.caracteristicaForm.$submitted = true;
		
		//verifica se o formulario está valido para salvar
		if($scope.caracteristicaForm.$valid){
			
			$scope.setLoadingSalva(true);
			
			CaracteristicaRepository.save($scope.caracteristica).then(function(result) {
				$scope.caracteristica = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_CARACTERISTICA'));
				$scope.caracteristicaForm.$submitted = false;
			});
			$scope.setLoading(false);
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};
	
	// Limpa o formulario preenchido
	$scope.limparCaracteristica = function(){
		$scope.caracteristica = {};
	};

    // SE ESTIVER EDITANDO CARREGA O Caracteristica
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			CaracteristicaRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.caracteristica = result.originalElement;

				$scope.setLoading(false);
			});
	}
	
	// Consulta entidade e mostra no formulario
	$scope.getCaracteristica = function(caracteristica, edit){
		$scope.setLoadingGet(true);
	
		CaracteristicaRepository.get(caracteristica.id).then(function(result) {
			$scope.caracteristica = result.originalElement;
			
			if($scope.caracteristica.dataBloqueio === null || $scope.caracteristica.dataBloqueio === undefined){
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
}]);


