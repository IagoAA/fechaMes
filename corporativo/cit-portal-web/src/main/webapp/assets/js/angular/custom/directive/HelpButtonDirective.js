citApp.directive("helpButton", ["$modal", "$translate", "PaginaUsuarioRepository", function($modal, $translate, PaginaUsuarioRepository) {

	return {
		scope : {
			workspace : "="
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/helpButton.html',
		controller: ['$scope', '$element', '$attrs', '$compile', function($scope, $element, $attrs, $compile) {
			$scope.ajuda = $scope.workspace.ajuda;

			$scope.showButtonHelp = false;

			// console.log($scope.workspace.paginaId != null);

			if($scope.workspace.paginaId != null) {
				$scope.showButtonHelp = true;
			}

		}],
		link : function($scope, $element, $attibutes){
			$scope.openModal = function (modalId, size) {
				// BUSCA AJUDA DA PÁGINA
				PaginaUsuarioRepository.findAjuda($scope.workspace.paginaId).then(function(result){
					$scope.ajuda = result.originalElement.ajuda;
				});

				var that = this;
				if(modalId === undefined) {
					return;
				}

				$scope.$modalInstance = $modal.open({
					templateUrl: modalId,
					size: size,
					windowClass: 'modal-buttons-top ' + that.workspace.classePagina,
					scope: that
				});
			};


		}
	};
}]);