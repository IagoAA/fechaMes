'use strict';
citApp.controller('PageDominioController', ['$scope', function PageDominioController($scope) {
	$scope.showSearch = function() {
		$scope.$showSearch = true;
		$scope.$showEdit = false;
	};

	$scope.showEdit = function() {
		$scope.$showSearch = false;
		$scope.$showEdit = true;
	};

	$scope.showSearch();
}]);

citApp.controller('DominioController', ['$scope', 'DominioRepository', '$translate', '$timeout',
		function DominioController($scope, DominioRepository, $translate, $timeout) {

	// metodo para atualizar a lista de resultados ao clicar em pesquisar
	$scope.atualizarLista = function(){

		angular.element("#dominioList").scope().fetchResult();
	};

	$scope.dialog;

	$scope.edit = true;

	$scope.isExcluir = true;

	$scope.dominio = {};

	$scope.dominios = [];

	$scope.resetForm = function() {
		$scope.limpar();
		$timeout(function(){
			$scope.formDominio.$submitted = false;
			$scope.formDominio.$setPristine();
		});
	};

	// Metodo responsavel por verificar se o codigo que o usuario digitou não está em uso
	$scope.validaCodigo = function(){
		if($scope.dominio.id === undefined && $scope.dominio.codigo && $scope.dominio.chave){
			return DominioRepository.validaCodigoDominioPorChave($scope.dominio.chave, $scope.dominio.codigo).then(function(result) {
				if(result){
					$scope.dominio.codigo = "";
					$scope.showAlert('error', $translate.instant('MSG.CODIGO_EM_USO'), " ", false);
				}
			});
		}else if($scope.dominio.id !== undefined && $scope.dominio.codigo && $scope.dominio.chave){
			return DominioRepository.findAllDominioByCodigo($scope.dominio.chave, $scope.dominio.codigo).then(function(result) {
				if(result && result.originalElement.id !== $scope.dominio.id){
					$scope.dominio.codigo = "";
					$scope.showAlert('error', $translate.instant('MSG.CODIGO_EM_USO'), " ", false);
				}
			});
		}
	};

	//metodo responsavel por fazer o set do dominio selecionado na aba de listagem para o usuario editar ou visualizar
	$scope.getDominio = function(idObject, visualizar){
		if (idObject != 0) {
			DominioRepository.get(idObject).then(function(result) {
				$scope.dominio = result.originalElement;
			});
			$scope.edit = visualizar;
			$scope.isExcluir = visualizar;
		};
	};

	// Metodo responsavel por salvar ou atualizar o dominio
	$scope.saveOrUpdate = function() {
		$scope.formDominio.$submitted = true;

		// verifica se o formulario está valido para salvar ou fazer update
		if($scope.formDominio.$valid){

			if($scope.dominio.dataBloqueio === ""){
				$scope.dominio.dataBloqueio = null;
			}

			$scope.setLoadingSalva(true);

			DominioRepository.save($scope.dominio).then(function(result) {
				$scope.setLoading(false);
				$scope.showAlert('success', $translate.instant('MSG.SUCESSO_DOMINIO'), " ", false);
				$scope.dominio = result.originalElement;
				$scope.isExcluir = true;
			});

		}else{

			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);

		}
	};

	//Limpa os campos na tela
	$scope.limpar = function() {
		$scope.dialog;

		$scope.edit = true;

		$scope.isExcluir = false;

		$scope.dominio = {};

		$scope.dominios = [];

		$timeout(function(){
			$scope.formDominio.$submitted = false;
			$scope.formDominio.$setPristine();
		});

	};

	// Metodo responsavel por abrir modal que confirma remover dominio
	$scope.remove = function(dominio){

		//Verifica se o dominio está indefinido para seta-lo no scope caso esteja
		if(dominio === undefined){
			dominio = $scope.dominio;
		}

		//abre modal que confirma remover dominio
		$scope.$openModalConfirm({
			message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'),
			callback: function () {
				$scope.setLoadingSalva(true);

				DominioRepository.remove(dominio).then(function() {
					$scope.showAlert("danger", $translate.instant('LABEL.EXCLUIDO'), " ", false);
					$scope.limpar();
					$scope.setLoadingSalva(false);
					$scope.$modalConfirmInstance.dismiss('cancel');

					angular.element('#dominioList').scope().fetchResult();
					$scope.edit = true;

				});
			}
		});
	};
}]);
