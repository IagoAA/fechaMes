'use strict';

citApp.controller('RegiaoController',['$scope', 'RegiaoRepository', 'PaisRepository', '$translate', '$timeout', function RegiaoController($scope, RegiaoRepository, PaisRepository, $translate, $timeout) {
	$scope.regiao = {
		listaEstado : []
	};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparRegiao();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.regiaoForm.$submitted = false;
			$scope.regiaoForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchRegiao').scope().fetchResult();
	};

	// Bloqueia a Região
	$scope.confirmarBloqueioRegiao = function(){
		if($scope.regiao.dataBloqueio && $scope.regiao.dataBloqueio !== ""){

			RegiaoRepository.save($scope.regiao).then(function(result){
				$scope.regiao = result.originalElement;

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

		$scope.regiao.dataBloqueio =  null;

		RegiaoRepository.save($scope.regiao).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.regiao = result.originalElement;

			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DA REGIAO
	$scope.remove = function(regiao){
		$scope.regiao = regiao;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_REGIAO'),
			callback: function () {
				RegiaoRepository.remove($scope.regiao).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_REGIAO_EXCLUIDO'));
					angular.element('#searchRegiao').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA A REGIAO
	$scope.saveOrUpdate = function(){
		$scope.regiaoForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.regiaoForm.$valid){

			$scope.setLoadingSalva(true);

			RegiaoRepository.save($scope.regiao).then(function(result) {
				$scope.regiao = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_REGIAO'));
				$scope.regiaoForm.$submitted = false;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Limpa o formulario preenchido
	$scope.limparRegiao = function(){
		$scope.regiao = {
			listaEstado : []
		};
	};

    // SE ESTIVER EDITANDO CARREGA A REGIAO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			RegiaoRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.regiao = result.originalElement;
				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getRegiao = function(regiao, edit){
		$scope.setLoadingGet(true);

		RegiaoRepository.get(regiao.id).then(function(result) {
			$scope.regiao = result.originalElement;

			if($scope.regiao.listaEstado === undefined){
				$scope.regiao.listaEstado = [];
			}

			if($scope.regiao.dataBloqueio === null || $scope.regiao.dataBloqueio === undefined){
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

	// Método responsável por listar País através do nome digitado
	$scope.findPais = function(value) {
		return PaisRepository.listarPais(value).then(function(result) {
				return result;
		});
	};

	// Método responsável por limpar o campo do autoComplete País
	$scope.limparAutoCompletePais = function() {
		$scope.regiao.pais = null;
	};
}]);


