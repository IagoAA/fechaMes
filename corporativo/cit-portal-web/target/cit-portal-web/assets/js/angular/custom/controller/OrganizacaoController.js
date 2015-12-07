'use strict';

citApp.controller('OrganizacaoController', ['$scope', 'OrganizacaoRepository', '$translate', '$timeout', '$filter', function OrganizacaoController($scope, OrganizacaoRepository, $translate, $timeout, $filter) {
	$scope.organizacao = {};

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// Limpa formulário para novo cadastro
	$scope.resetForm = function() {
		$scope.limparOrganizacao();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$timeout(function(){
			$scope.organizacaoForm.$submitted = false;
			$scope.organizacaoForm.$setPristine();
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchOrganizacao').scope().fetchResult();
	};

	// SALVA A ORGANIZACAO
	$scope.saveOrUpdate = function(){
		$scope.organizacaoForm.$submitted = true;

		//verifica se o formulario está valido para salvar
		if($scope.organizacaoForm.$valid){
			
			$scope.setLoadingSalva(true);
			var referencia = converterStringEmDate($scope.organizacao.dataReferenciaVigente);
			$scope.organizacao.dataReferenciaVigente = $filter('date')(referencia, "dd/MM/yyyy");
			
			OrganizacaoRepository.save($scope.organizacao).then(function(result) {
				$scope.organizacao = result.originalElement;
				var referencia = converterStringEmDate($scope.organizacao.dataReferenciaVigente);
				$scope.organizacao.dataReferenciaVigente = $filter('date')(referencia, "MM/yyyy");				
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_ORGANIZACAO'));
				$scope.organizacaoForm.$submitted = false;
			});
			$scope.setLoading(false);
		}else{
			//Mensagem de erro de campos obrigatorios não preenchidos
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
		}
		
	};

	// Limpa o formulario preenchido
	$scope.limparOrganizacao = function(){
		$scope.organizacao = {};
	};

    // SE ESTIVER EDITANDO CARREGA A ORGANIZACAO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			OrganizacaoRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.organizacao = result.originalElement;
				var referencia = converterStringEmDate($scope.organizacao.dataReferenciaVigente);
				$scope.organizacao.dataReferenciaVigente = $filter('date')(referencia, "MM/yyyy");

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getOrganizacao = function(organizacao, edit){
		$scope.setLoadingGet(true);

		OrganizacaoRepository.get(organizacao.id).then(function(result) {
			$scope.organizacao = result.originalElement;
			var referencia = converterStringEmDate($scope.organizacao.dataReferenciaVigente);		
			$scope.organizacao.dataReferenciaVigente = $filter('date')(referencia, "MM/yyyy");
			
			if($scope.organizacao.dataBloqueio === null || $scope.organizacao.dataBloqueio === undefined){
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


