'use strict';

citApp.controller('AccessRoleController', ['$scope, AccessRoleRepository, EnderecoRepository, $translate',
        function AccessRoleController($scope, AccessRoleRepository, EnderecoRepository, $translate) {

	$scope.accessRole = {};

	$scope.resetForm = function() {
		$scope.limparAccessRole();
		$scope.edit = true;
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchAccessRole').scope().fetchResult();
	};

	// MODAL QUE CONFIRMA REMOVER DA ACCESS_ROLE
	$scope.remove = function(accessRole){
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_ACCESS_ROLE'),
			callback: function () {
				AccessRoleRepository.remove(accessRole).then(function() {
					$scope.accessRole = {};

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_ACCESS_ROLE_EXCLUIDO'));
					angular.element('#searchAccessRole').scope().fetchResult();

					$scope.edit = true;
				});
			}
		});
	};

	// SALVA O AccessRole
	$scope.saveOrUpdate = function(){
		$scope.setLoadingSalva(true);

		AccessRoleRepository.save($scope.accessRole).then(function(result) {
			$scope.accessRole = result.originalElement;
			$scope.setLoading(false);
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_ACCESS_ROLE'));
		});
	};

	$scope.limparAccessRole = function(){
		$scope.accessRole = {};
	};

    // SE ESTIVER EDITANDO CARREGA O accessrole
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			AccessRoleRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.accessRole = result.originalElement;

				$scope.setLoading(false);
			});
	}

	$scope.getAccessRole = function(accessRole, edit){
		$scope.setLoadingGet(true);

		AccessRoleRepository.get(accessRole.id).then(function(result) {
			$scope.accessRole = result.originalElement;
			$scope.edit = edit;
			$scope.setLoading(false);
		});
	};
}]);



