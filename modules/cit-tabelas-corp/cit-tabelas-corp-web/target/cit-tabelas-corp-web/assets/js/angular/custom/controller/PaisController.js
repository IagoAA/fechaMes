'use strict';

citApp.controller('PaisController', ['$scope', 'PaisRepository', '$filter', '$translate', '$timeout', function PaisController($scope, PaisRepository, $filter, $translate, $timeout) {
	$scope.pais = {};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparPais();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.paisForm.$submitted = false;
			$scope.paisForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchPais').scope().fetchResult();
	};

	// Bloqueia o País
	$scope.confirmarBloqueioPais = function(){
		if($scope.pais.dataBloqueio && $scope.pais.dataBloqueio !== ""){

			PaisRepository.save($scope.pais).then(function(result){
				$scope.pais = result.originalElement;

				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BLOQUEIO'));
			});
		}else{
			$scope.showAlert('error', $translate.instant('MSG.MG011'), " ", false);
		}
	};

	// Desbloqueia o País
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.pais.dataBloqueio =  null;

		PaisRepository.save($scope.pais).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.pais = result.originalElement;

			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO PAIS
	$scope.remove = function(pais){
		$scope.pais = pais;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_PAIS'),
			callback: function () {
				PaisRepository.remove($scope.pais).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_PAIS_EXCLUIDO'));
					angular.element('#searchPais').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA O PAIS
	$scope.saveOrUpdate = function(){
		$scope.paisForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.paisForm.$valid){

			$scope.setLoadingSalva(true);

			PaisRepository.save($scope.pais).then(function(result) {
				$scope.pais = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_PAIS'));
				$scope.paisForm.$submitted = false;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}

	};

	// Limpa o formulario preenchido
	$scope.limparPais = function(){
		$scope.pais = {};
	};

    // SE ESTIVER EDITANDO CARREGA O PAIS
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			PaisRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.pais = result.originalElement;

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getPais = function(pais, edit){
		$scope.setLoadingGet(true);

		PaisRepository.get(pais.id).then(function(result) {
			$scope.pais = result.originalElement;

			if($scope.pais.dataBloqueio === null || $scope.pais.dataBloqueio === undefined){
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


