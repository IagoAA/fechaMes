'use strict';

citApp.controller('CaixaController',['$scope', 'CaixaRepository', 'LuckRepository', '$translate', '$timeout', function CaixaController($scope, CaixaRepository, LuckRepository, $translate, $timeout) {
	$scope.caixa = {
		listaEstado : []
	};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparCaixa();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.caixaForm.$submitted = false;
			$scope.caixaForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchCaixa').scope().fetchResult();
	};

	// Bloqueia a Caixa
	$scope.confirmarBloqueioCaixa = function(){
		if($scope.caixa.dataBloqueio && $scope.caixa.dataBloqueio !== ""){

			CaixaRepository.save($scope.caixa).then(function(result){
				$scope.caixa = result.originalElement;

				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BLOQUEIO'));
			});
		}else{
			$scope.showAlert('error', $translate.instant('MSG.MG011'), " ", false);
		}

	};

	// Desbloqueia a Região
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.caixa.dataBloqueio =  null;

		CaixaRepository.save($scope.caixa).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.caixa = result.originalElement;

			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DA REGIAO
	$scope.remove = function(caixa){
		$scope.caixa = caixa;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_REGIAO'),
			callback: function () {
				CaixaRepository.remove($scope.caixa).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_REGIAO_EXCLUIDO'));
					angular.element('#searchCaixa').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA o Caixa
	$scope.saveOrUpdate = function(){
		$scope.caixaForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.caixaForm.$valid){

			$scope.setLoadingSalva(true);

			CaixaRepository.save($scope.caixa).then(function(result) {
				$scope.caixa = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_REGIAO'));
				$scope.caixaForm.$submitted = false;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Limpa o formulario preenchido
	$scope.limparCaixa = function(){
		$scope.caixa = {
			listaEstado : []
		};
	};

    // SE ESTIVER EDITANDO CARREGA O CAIXA
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			CaixaRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.caixa = result.originalElement;
				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getCaixa = function(caixa, edit){
		$scope.setLoadingGet(true);

		CaixaRepository.get(caixa.id).then(function(result) {
			$scope.caixa = result.originalElement;

			if($scope.caixa.listaEstado === undefined){
				$scope.caixa.listaEstado = [];
			}

			if($scope.caixa.dataBloqueio === null || $scope.caixa.dataBloqueio === undefined){
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

	// Método responsável por listar Luck através do nome digitado
	$scope.findLuck = function(value) {
		return LuckRepository.listarLuck(value).then(function(result) {
				return result;
		});
	};

	// Método responsável por limpar o campo do autoComplete País
	$scope.limparAutoCompleteLuck = function() {
		$scope.caixa.luck = null;
	};
}]);


