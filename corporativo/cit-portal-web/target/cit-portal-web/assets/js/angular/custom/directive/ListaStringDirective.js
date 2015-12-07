citApp.directive("listaString", [function() {
	return {
		scope : {
			lista : "=ngListaItens",
			altura: "=ngAltura",
			eventoChange : "&ngEventoChange",
			numberOnly: "@",
			maxLength: "@"
		},
		restrict : "EA",
		transclude: true,
		templateUrl : 'assets/js/angular/custom/directive/html/listaString.html',
		link : function($scope, $element, attributes) {
			$scope.item = '';
			if (!$scope.lista)
				$scope.lista = [];

			$scope.adicionaItem = function() {
				if ($scope.entradaValida) {
					$scope.lista.push($scope.item.trim());
					if ($scope.eventoChange)
						$scope.eventoChange();
					$scope.item = '';
				}
			};
			
			$scope.entradaValida = function (){
				if (!$scope.item || $scope.item.trim() === '')
					return false;
				var existe = false;
				for (var i = 0; i < $scope.lista.length; i++) {
					if ($scope.lista[i] == $scope.item.trim()) {
						existe = true;
						break;
					}
				}
				if (existe)
					return false;
				return true;
			};
			
			$scope.removeItem = function (index){
			     if($scope.lista.length >= 1){
			    	 $scope.lista.splice(index , 1);
					 if ($scope.eventoChange)
						$scope.eventoChange();
			     }
			};
		}
	};
}]);
