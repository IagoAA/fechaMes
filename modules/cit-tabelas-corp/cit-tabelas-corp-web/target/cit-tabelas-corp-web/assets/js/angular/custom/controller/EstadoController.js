'use strict';

citApp.controller('EstadoController',['$scope', 'EstadoRepository', 'RegiaoRepository', '$translate', '$timeout', function EstadoController($scope, EstadoRepository, RegiaoRepository, $translate, $timeout) {

	$scope.estado = {
		listaCidade : []
	};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparEstado();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.estadoForm.$submitted = false;
			$scope.estadoForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchEstado').scope().fetchResult();
	};

	// Bloqueia o Estado
	$scope.confirmarBloqueioEstado = function(){
		if($scope.estado.dataBloqueio && $scope.estado.dataBloqueio !== ""){

			EstadoRepository.save($scope.estado).then(function(result){
				$scope.estado = result.originalElement;

				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BLOQUEIO'));
			});
		}else{
			$scope.showAlert('error', $translate.instant('MSG.MG011'), " ", false);
		}

	};

	// Desbloqueia a Estado
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.estado.dataBloqueio =  null;

		EstadoRepository.save($scope.estado).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.estado = result.originalElement;

			$scope.estado.listaCidade = [];

			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};


	// MODAL QUE CONFIRMA REMOVER DO ESTADO
	$scope.remove = function(estado){
		$scope.estado = estado;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_ESTADO'),
			callback: function () {
				EstadoRepository.remove($scope.estado).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_ESTADO_EXCLUIDO'));
					angular.element('#searchEstado').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA O ESTADO
	$scope.saveOrUpdate = function(){
		$scope.estadoForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.estadoForm.$valid){

			$scope.setLoadingSalva(true);

			EstadoRepository.save($scope.estado).then(function(result) {
				$scope.estado = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_ESTADO'));
				$scope.estadoForm.$submitted = false;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Limpa o formulario preenchido
	$scope.limparEstado = function(){
		$scope.estado = {
			listaCidade : []
		};
	};

    // SE ESTIVER EDITANDO CARREGA O ESTADO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			EstadoRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.estado = result.originalElement;

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getEstado = function(estado, edit){
		$scope.setLoadingGet(true);

		EstadoRepository.get(estado.id).then(function(result) {
			$scope.estado = result.originalElement;

			if($scope.estado.listaCidade === undefined){
				$scope.estado.listaCidade = [];
			}

			if($scope.estado.dataBloqueio === null || $scope.estado.dataBloqueio === undefined){
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

	// Método responsável por listar Região através do nome digitado ou
	// País selecionado
	$scope.findRegiao = function(value) {
		var paisNomeVH = {
			objeto : $scope.pais,
			nome : value
		};
		return RegiaoRepository.findRegiao(paisNomeVH).then(function(result) {
				return result;

		});
	};

	// Método responsável por limpar o campo do autoComplete Região
	$scope.limparAutoCompleteRegiao = function() {
		$scope.estado.regiao = null;
	};
}]);


