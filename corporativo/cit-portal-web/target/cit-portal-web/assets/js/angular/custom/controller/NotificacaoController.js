'use strict';

citApp.controller('NotificacaoController', ['$scope', '$rootScope', 'NotificacaoRepository', 'DominioRepository', '$translate', '$timeout',
		function NotificacaoController($scope, $rootScope, NotificacaoRepository, DominioRepository, $translate, $timeout) {

	$scope.dialog;

	$scope.isVisualizar = false;

	$scope.visualizado = false;

	$scope.resetForm = function() {
		$scope.limpar();
		$scope.isVisualizar = false;
		$scope.visualizado  = false;
		$timeout(function(){
			$scope.formNotificacaoEdit.$submitted = false;
			$scope.formNotificacaoEdit.$setPristine();
		});
	};

	DominioRepository.findAllDominio('tipoNotificacao').then(function(result) {
		$scope.tiposNotificacao = result;
	});


	DominioRepository.findAllDominio('tipoPrioridade').then(function(result) {
		$scope.tiposPrioridade = result;
	});

	// SALVA A NOTIFICAÇÃO
	$scope.saveOrUpdate = function() {

	$scope.formNotificacaoEdit.$submitted = true;
		//Vefifica se o formulario está invalido, caso esteja apresenta uma alerta para o usuario.
		if($scope.formNotificacaoEdit.$invalid){

			$scope.showAlert('error', $translate.instant('MSG.MN001'));

		}else{

			$scope.setLoadingSalva(true);

			$scope.notificacao.usuario = $scope.usuarioLogado;

			NotificacaoRepository.save($scope.notificacao).then(function(result) {
				$scope.setLoading(false);

				$scope.notificacao = result.originalElement;

				$rootScope.findNotificacoes();

				$scope.showAlert("success",  $translate.instant('MSG.MG001'));

				$scope.formNotificacaoEdit.$submitted = false;
			});
		}
	};

	//Atualiza a data de visualização da notificação
	$scope.updateVisualizar = function() {

		$scope.setLoadingSalva(true);

		NotificacaoRepository.visualizar($scope.notificacao).then(function(result) {
			$scope.setLoading(false);

			$rootScope.findNotificacoes();

		});

		$scope.visualizado = true;
	};

	//Limpa os campos na tela
	$scope.limpar = function() {

		$scope.notificacao = {};

		$timeout(function(){
			$scope.formNotificacaoEdit.$submitted = false;
			$scope.formNotificacaoEdit.$setPristine();
		});
	};

	$scope.fetchResult = function(){
		angular.element('#notificacao-list-controller').scope().fetchResult();
	};

	$scope.getNotificacao = function(idObject, isVisualizar){

		NotificacaoRepository.get(idObject).then(function(result) {

			$scope.notificacao = result.originalElement;

			$scope.visualizado = $scope.notificacao.dataVisualizacao != null;

			$scope.isVisualizar = isVisualizar;
		});

	};

}]);
