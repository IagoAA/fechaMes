'use strict';

citApp.controller('LuckController', ['$scope', 'LuckRepository', '$filter', '$translate', '$timeout', function LuckController($scope, LuckRepository, $filter, $translate, $timeout) {

	$scope.luck = {};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparLuck();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.luckForm.$submitted = false;
			$scope.luckForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchLuck').scope().fetchResult();
	};

	// Bloqueia o País
	$scope.confirmarBloqueioLuck = function(){
		if($scope.luck.dataBloqueio && $scope.luck.dataBloqueio !== ""){

			LuckRepository.save($scope.luck).then(function(result){
				$scope.luck = result.originalElement;

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

		$scope.luck.dataBloqueio =  null;

		LuckRepository.save($scope.luck).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.luck = result.originalElement;

			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO LUCK
	$scope.remove = function(luck){
		$scope.luck = luck;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_LUCK'),
			callback: function () {
				LuckRepository.remove($scope.luck).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_LUCK_EXCLUIDO'));
					angular.element('#searchLuck').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA O LUCK
	$scope.saveOrUpdate = function(){
		$scope.luckForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.luckForm.$valid){

			$scope.setLoadingSalva(true);

			LuckRepository.save($scope.luck).then(function(result) {
				$scope.luck = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_LUCK'));
				$scope.luckForm.$submitted = false;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}

	};

	// Limpa o formulario preenchido
	$scope.limparLuck = function(){
		$scope.luck = {};
	};

    // SE ESTIVER EDITANDO CARREGA O LUCK
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			LuckRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.luck = result.originalElement;

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getLuck = function(luck, edit){
		$scope.setLoadingGet(true);

		LuckRepository.get(luck.id).then(function(result) {
			$scope.luck = result.originalElement;

			if($scope.luck.dataBloqueio === null || $scope.luck.dataBloqueio === undefined){
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


