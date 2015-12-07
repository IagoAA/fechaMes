citApp.directive("breadcrumb", ["$translate", "$filter", "MenuRepository", function($translate, $filter, MenuRepository) {

	return {
		scope : {
			workspace : "=ngWorkspace",
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/breadcrumb.html',
		controller : ['$scope', '$element', '$attrs', function($scope, $element, $attrs){
			$scope.menuPagina = {};
			$scope.listaBreadcrumb = [];
			MenuRepository.buscarMenusPorPagina({pagina : $scope.workspace.page}).then(function(result) {
				$scope.menuPagina = result.originalElement;
				adicionarNomes($scope.menuPagina);
				$scope.listaBreadcrumb.reverse();
			});
			var adicionarNomes = function(menu){
				$scope.listaBreadcrumb.push(menu.nome);
				if(menu.parent){
					adicionarNomes(menu.parent);
				}
			};
		}]
	};
}]);