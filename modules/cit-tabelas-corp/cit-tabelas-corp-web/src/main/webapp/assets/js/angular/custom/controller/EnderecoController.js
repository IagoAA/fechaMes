'use strict';

citApp.controller('EnderecoController', ['$scope', 'EnderecoRepository', 'DominioRepository', '$translate', '$timeout',
              function EnderecoController($scope, EnderecoRepository, DominioRepository, $translate, $timeout) {

	$scope.endereco = {};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparEndereco();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.enderecoForm.$submitted = false;
			$scope.enderecoForm.$setPristine();
		});

	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchEndereco').scope().fetchResult();
	};

	// Bloqueia a Região
	$scope.confirmarBloqueioEndereco = function(){

		if(!$scope.endereco.dataBloqueio){

			$scope.showAlert('error', $translate.instant('MSG.MG011'), " ", false);

		} else {

			var dataBlock = converterStringEmDate($scope.endereco.dataBloqueio);
			if(dataBlock > new Date() || $scope.isDataIguais(dataBlock, new Date())) {
				if($scope.enderecoForm.$valid){
					$scope.setLoadingSalva(true);
					EnderecoRepository.save($scope.endereco).then(function(result){
						$scope.setLoadingSalva(false);

						$scope.endereco = result.originalElement;
						$scope.carregarPaisRegiaoEstadoCidade();

						$scope.isDesbloquear = true;
						$scope.isBloquear = false;
						$scope.edit = false;
						$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BLOQUEIO'));
					});
				} else {
					//Mensagem de erro de campos obrigatorios não preenchidos
					$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
				}
			} else {
				$scope.showAlert('error', $translate.instant('VALIDACAO.MN005'));
			}

		}
	};

	$scope.isDataIguais = function(data1, data2) {
		return (data1.getDate() == data2.getDate())
				&& (data1.getMonth() == data2.getMonth())
				&& (data1.getFullYear() == data2.getFullYear());
	};

	// Desbloqueia a Região
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.endereco.dataBloqueio =  null;

		EnderecoRepository.save($scope.endereco).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.endereco = result.originalElement;
			$scope.carregarPaisRegiaoEstadoCidade();

			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.edit = true;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO ENDERECO
	$scope.remove = function(endereco){
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_ENDERECO'),
			callback: function () {
				EnderecoRepository.remove(endereco).then(function() {
					$scope.endereco = {};

					$scope.$modalConfirmInstance.dismiss('cancel');
					angular.element('#searchEndereco').scope().fetchResult();
					$scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));

					$scope.resetForm();
				});
			}
		});
	};

	// SALVA O ENDERECO
	$scope.saveOrUpdate = function() {
		$scope.enderecoForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.enderecoForm.$valid){

			$scope.setLoadingSalva(true);

			EnderecoRepository.save($scope.endereco).then(function(result) {
				$scope.endereco = result.originalElement;
				$scope.carregarPaisRegiaoEstadoCidade();
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_ENDERECO'));
				$scope.enderecoForm.$submitted = false;
				$scope.setLoading(false);

			});
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
	};

	// Limpa o formulario preenchido
	$scope.limparEndereco = function() {
		$scope.endereco = {};
	};

	// SE ESTIVER EDITANDO CARREGA O ENDERECO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

		EnderecoRepository.get($scope.$parent.idObejct).then(function(result) {
			$scope.endereco = result.originalElement;

			$scope.setLoading(false);
		});
	}

	// Carrega autos complete de pais, regiao, estado e cidade
	$scope.carregarPaisRegiaoEstadoCidade = function(){
		$scope.endereco.$pais = $scope.endereco.bairro.cidade.estado.regiao.pais;
		$scope.endereco.$regiao = $scope.endereco.bairro.cidade.estado.regiao;
		$scope.endereco.$estado = $scope.endereco.bairro.cidade.estado;
		$scope.endereco.$cidade = $scope.endereco.bairro.cidade;
	};

	// Consulta entidade e mostra no formulario
	$scope.getEndereco = function(endereco, edit){
		$scope.setLoadingGet(true);

		EnderecoRepository.get(endereco.id).then(function(result) {
			$scope.endereco = result.originalElement;

			$scope.carregarPaisRegiaoEstadoCidade();

			if($scope.endereco.dataBloqueio === null || $scope.endereco.dataBloqueio === undefined){
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
