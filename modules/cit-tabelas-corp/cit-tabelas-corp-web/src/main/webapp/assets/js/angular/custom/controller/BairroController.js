'use strict';

citApp.controller('BairroController',['$scope', 'BairroRepository', 'CidadeRepository', '$translate', '$timeout', function BairroController($scope, BairroRepository, CidadeRepository, $translate, $timeout) {

	$scope.bairro = {};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparBairro();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.bairroForm.$submitted = false;
			$scope.bairroForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchBairro').scope().fetchResult();
	};

	// Bloqueia o Bairro
	$scope.confirmarBloqueioBairro = function(){
		if($scope.bairro.dataBloqueio && $scope.bairro.dataBloqueio !== ""){

			BairroRepository.save($scope.bairro).then(function(result){
				$scope.bairro = result.originalElement;

				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BLOQUEIO'));
			});
		}else{
			$scope.showAlert('error', $translate.instant('MSG.MG011'), " ", false);
		}
	};

	// Desbloqueia o Bairro
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.bairro.dataBloqueio =  null;

		BairroRepository.save($scope.bairro).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.bairro = result.originalElement;

			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.edit = true;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO BAIRRO
	$scope.remove = function(bairro){
		$scope.bairro = bairro;

		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_BAIRRO'),
			callback: function () {
				$scope.setLoadingSalva(true);

                if(bairro.id !== undefined) {
                	BairroRepository.remove($scope.bairro).then(function() {
                		angular.element('#searchBairro').scope().fetchResult();
                		$scope.$modalConfirmInstance.dismiss('cancel');
                        $scope.setLoading(false);
                        $scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));
                		$scope.resetForm();
					});
				}
			}
		});
	};

	// SALVA O BAIRRO
	$scope.saveOrUpdate = function(){
		$scope.bairroForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.bairroForm.$valid){

			$scope.setLoadingSalva(true);

			BairroRepository.save($scope.bairro).then(function(result) {
				$scope.bairro = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BAIRRO'));
				$scope.bairroForm.$submitted = true;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Limpa o formulario preenchido
	$scope.limparBairro = function(){
		$scope.bairro = {};
	};

    // SE ESTIVER EDITANDO CARREGA O BAIRRO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
			$scope.setLoadingGet(true);

			BairroRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.bairro = result.originalElement;

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getBairro = function(bairro, edit){
		$scope.setLoadingGet(true);

		BairroRepository.get(bairro.id).then(function(result) {
			$scope.bairro = result.originalElement;

			if($scope.bairro.dataBloqueio === null || $scope.bairro.dataBloqueio === undefined){
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

	// Método responsável por listar Cidade através do nome ou Estado,
	// Região, Pais selecionado
	$scope.findCidade = function(value) {
		var estadoRegiaoPaisNomeVH = {
			nome : value
		};
		return CidadeRepository.findCidade(estadoRegiaoPaisNomeVH).then(function(result) {
				return result;
		});
	};

	// Método responsável por limpar o campo do autoComplete Cidade
	$scope.limparAutoCompleteCidade = function() {

		$scope.bairro.cidade = null;

	};
}]);


