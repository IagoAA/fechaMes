'use strict';

citApp.controller('ServicoController', ['$scope', 'ServicoRepository', '$filter', '$translate', '$timeout', function ServicoController($scope, ServicoRepository, $filter, $translate, $timeout) {
	$scope.servico = {};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparServico();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.servicoForm.$submitted = false;
			$scope.servicoForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchServico').scope().fetchResult();
	};

	// Bloqueia o País
	$scope.confirmarBloqueioServico = function(){
		if($scope.servico.dataBloqueio && $scope.servico.dataBloqueio !== ""){

			ServicoRepository.save($scope.servico).then(function(result){
				$scope.servico = result.originalElement;

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

		$scope.servico.dataBloqueio =  null;

		ServicoRepository.save($scope.servico).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.servico = result.originalElement;

			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO SERVICO
	$scope.remove = function(servico){
		$scope.servico = servico;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_SERVICO'),
			callback: function () {
				ServicoRepository.remove($scope.servico).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.EXCLUSAO_SUCESSO'));
					angular.element('#searchServico').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA O SERVICO
	$scope.saveOrUpdate = function(){
		$scope.servicoForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.servicoForm.$valid){

			$scope.setLoadingSalva(true);

			ServicoRepository.save($scope.servico).then(function(result) {
				$scope.servico = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.REGISTRO_SALVO'));
				$scope.servicoForm.$submitted = false;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}

	};

	// Limpa o formulario preenchido
	$scope.limparServico = function(){
		$scope.servico = {};
	};

    // SE ESTIVER EDITANDO CARREGA O SERVICO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			ServicoRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.servico = result.originalElement;

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getServico = function(servico, edit){
		$scope.setLoadingGet(true);

		ServicoRepository.get(servico.id).then(function(result) {
			$scope.servico = result.originalElement;

			if($scope.servico.dataBloqueio === null || $scope.servico.dataBloqueio === undefined){
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
