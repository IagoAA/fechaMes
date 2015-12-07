'use strict';

citApp.controller('CidadeController',['$scope', 'CidadeRepository', 'EstadoRepository', '$translate', '$timeout',  function CidadeController($scope, CidadeRepository, EstadoRepository, $translate, $timeout) {

	$scope.cidade = {};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparCidade();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.cidadeForm.$submitted = false;
			$scope.cidadeForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchCidade').scope().fetchResult();
	};

	// Bloqueia a Cidade
	$scope.confirmarBloqueioCidade = function(){
		if($scope.cidade.dataBloqueio && $scope.cidade.dataBloqueio !== ""){
			CidadeRepository.save($scope.cidade).then(function(result) {
				$scope.cidade = result.originalElement;

				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BLOQUEIO'));
			});
		}else{
			$scope.showAlert('error', $translate.instant('MSG.MG011'), " ", false);
		}

	};

	// Desbloqueia o Cidade
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.cidade.dataBloqueio = null;

		CidadeRepository.save($scope.cidade).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.cidade = result.originalElement;
			$scope.edit = true;
			$scope.isDesbloquear = true;
			$scope.isBloquear = false;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DA CIDADE
	$scope.remove = function(cidade){
		$scope.cidade = cidade;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_CIDADE'),
			callback: function () {
				CidadeRepository.remove($scope.cidade).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_CIDADE_EXCLUIDO'));
					angular.element('#searchCidade').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA O Cidade
	$scope.saveOrUpdate = function(){
		$scope.cidadeForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.cidadeForm.$valid){

			$scope.setLoadingSalva(true);

			CidadeRepository.save($scope.cidade).then(function(result) {
				$scope.cidade = result.originalElement;
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_CIDADE'));
				$scope.cidadeForm.$submitted = false;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Limpa o formulario preenchido
	$scope.limparCidade = function(){
		$scope.cidade = {};
	};

    // SE ESTIVER EDITANDO CARREGA O CIDADE
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			CidadeRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.cidade = result.originalElement;

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getCidade = function(cidade, edit){
		$scope.setLoadingGet(true);

		CidadeRepository.get(cidade.id).then(function(result) {
			$scope.cidade = result.originalElement;

			if($scope.cidade.dataBloqueio === null || $scope.cidade.dataBloqueio === undefined){
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

	// Método responsável por listar Estado através do nome ou Região,
	// Pais selecionado
	$scope.findEstado = function(value) {

		var regiao = {};

		angular.copy($scope.regiao, regiao);

		angular.copy($scope.pais, regiao.pais);

		var regiaoPaisNomeVH = {
			objeto : regiao,
			nome : value
		};
		return EstadoRepository.findEstado(regiaoPaisNomeVH).then(function(result) {
					return result;
		});
	};

	// Método responsável por limpar o campo do autoComplete Cidade
	$scope.limparAutoCompleteEstado = function() {
		$scope.cidade.estado = null;
	};
}]);


