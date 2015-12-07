citApp.directive("bloquearDesbloquear", ["$translate", "$injector", function($translate, $injector) {

	return {
		require: ['^ngModel'],
		scope : {
			model :	"=ngModel",
			repository : "@ngRepository",
			edit : "=ngEdit",
			form : "=form"
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/bloquearDesbloquear.html',
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {

			$scope.id = $attrs['ngModel'];
			$scope.$buttonLockOpen = false;
			$scope.repositorio = $injector.get($scope.repository);
			$scope.appController = angular.element("#citapp-controller").scope();
			$scope.dataBloqueio = null;
			$scope.pgEdit = true;

		}],
		link : function($scope, $element, $attibutes){
			$scope.noCloseDropdown = function ($e) {
				$e.preventDefault();
				$e.stopPropagation();
			};

			$scope.bloquear = function(){
				if(!$scope.dataBloqueio){
					$scope.appController.showAlert('error', $translate.instant('MSG.MG011'));
				} else if(!$scope.form.$valid){
					$scope.appController.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
				}
				if ($scope.dataBloqueio && $scope.form.$valid){
					var dataBlock = converterStringEmDate($scope.dataBloqueio);
					if(dataBlock > new Date() || $scope.isDataIguais(dataBlock, new Date())){
						$scope.appController.setLoadingSalva(true);
						$scope.model.dataBloqueio = $scope.dataBloqueio;
						$scope.repositorio.save($scope.model).then(function(result){
							$scope.appController.setLoadingSalva(false);
							$scope.model.dataBloqueio = result.originalElement.dataBloqueio;
							$scope.model.version = result.originalElement.version;
							$scope.dataBloqueio = null;
							$scope.appController.showAlert("success", $translate.instant('MSG.REGISTRO_BLOQUEADO'));
						});
					}
				}
			};

			$scope.desbloquear = function(){
				$scope.appController.setLoadingSalva(true);
				$scope.model.dataBloqueio = null;
				$scope.repositorio.save($scope.model).then(function(result){
					$scope.appController.setLoadingSalva(false);
					$scope.model.dataBloqueio = result.originalElement.dataBloqueio;
					$scope.model.version = result.originalElement.version;
					$scope.appController.showAlert("success", $translate.instant('MSG.REGISTRO_DESBLOQUEADO'));
				});
			};

			$scope.isDataIguais = function(data1, data2) {
				return (data1.getDate() == data2.getDate())
						&& (data1.getMonth() == data2.getMonth())
						&& (data1.getFullYear() == data2.getFullYear());
			};
		}
	};
}]);