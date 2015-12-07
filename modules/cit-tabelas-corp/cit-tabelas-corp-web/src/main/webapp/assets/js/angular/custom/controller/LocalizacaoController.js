'use strict';

citApp.controller('LocalizacaoController', ['$scope', 'LocalizacaoRepository', '$translate', '$timeout', function LocalizacaoController($scope, LocalizacaoRepository, $translate, $timeout) {

	$scope.localizacao = {
		endereco : {}
	};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparLocalizacao();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.localizacaoForm.$submitted = false;
			$scope.localizacaoForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchLocalizacao').scope().fetchResult();
	};

	// Desbloqueia a Localização
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.localizacao.dataBloqueio =  null;

		LocalizacaoRepository.save($scope.localizacao).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.localizacao = result.originalElement;
			$scope.carregarPaisRegiaoEstadoCidadeDoEndereco();

			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.edit = true;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DA LOCALIZACAO
	$scope.remove = function(localizacao){
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_LOCALIZACAO'),
			callback: function () {
				LocalizacaoRepository.remove(localizacao).then(function() {
					$scope.localizacao = {};

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_LOCALIZACAO_EXCLUIDO'));
					angular.element('#searchLocalizacao').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA A LOCALIZACAO
	$scope.saveOrUpdate = function(){
		$scope.localizacaoForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.localizacaoForm.$valid){

			$scope.setLoadingSalva(true);

			LocalizacaoRepository.save($scope.localizacao).then(function(result) {
				$scope.localizacao = result.originalElement;
				$scope.carregarPaisRegiaoEstadoCidadeDoEndereco();
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_LOCALIZACAO'));
				$scope.localizacaoForm.$submitted = true;
				$scope.setLoading(false);
			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Limpa o formulario preenchido
	$scope.limparLocalizacao = function(){
		$scope.localizacao = {
			endereco : {}
		};
	};

    // SE ESTIVER EDITANDO CARREGA O LOCALIZACAO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			LocalizacaoRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.localizacao = result.originalElement;

				$scope.setLoading(false);
			});
	}else{
		$scope.organizacao = $scope.usuarioLogado.organizacao;
	};

	// Carrega autos complete de pais, regiao, estado, bairro do endereço da localização
	$scope.carregarPaisRegiaoEstadoCidadeDoEndereco = function(){
		$scope.localizacao.endereco.$pais = $scope.localizacao.endereco.bairro.cidade.estado.regiao.pais;
		$scope.localizacao.endereco.$regiao = $scope.localizacao.endereco.bairro.cidade.estado.regiao;
		$scope.localizacao.endereco.$estado = $scope.localizacao.endereco.bairro.cidade.estado;
		$scope.localizacao.endereco.$cidade = $scope.localizacao.endereco.bairro.cidade;
	};

	// Consulta registro e mostra no formulario
	$scope.getLocalizacao = function(localizacao, edit){
		$scope.setLoadingGet(true);

		LocalizacaoRepository.get(localizacao.id).then(function(result) {
			$scope.localizacao = result.originalElement;
			$scope.carregarPaisRegiaoEstadoCidadeDoEndereco();
			$scope.organizacao = $scope.usuarioLogado.organizacao;

			if($scope.localizacao.dataBloqueio === null || $scope.localizacao.dataBloqueio === undefined){
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


