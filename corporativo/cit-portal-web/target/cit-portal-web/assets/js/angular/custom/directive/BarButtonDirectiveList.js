/** Diretiva para barra de acoes de crud e adicao de butoes customizados **/
citApp.directive("barButtonList", ["$translate", function($translate) {

	return {

		scope : {
			buttons : '=ngButtons',
			buttonsPadrao : '=ngButtonsPadrao'
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/barButton.html',

		controller: ['$scope', function($scope) {

			if (!$scope.buttons){

				$scope.buttons = [];

				$scope.buttons.push({title : $translate.instant('LABEL.CADASTRAR'), actions : '$showPageEditWorkspace(workspace); resetForm();', iconClass: 'fa fa-plus-circle yellow-dark', show : true},
		                  {title : $translate.instant('LABEL.VISUALIZAR'), actions : 'abrirPais(false);', iconClass: 'fa fa-search blue', show : true},
		                  {title : $translate.instant('LABEL.EDITAR'), actions : 'abrirPais(true);', iconClass: 'fa fa-pencil blue', show : true},
		                  {title : $translate.instant('LABEL.REMOVER'), actions : 'remove();', iconClass: 'fa fa-times red', show : true})
			}

			if ($scope.buttons && $scope.buttonsPadrao){

				$scope.buttons.push({title : $translate.instant('LABEL.CADASTRAR'), actions : '$showPageEditWorkspace(workspace); resetForm();', iconClass: 'fa fa-plus-circle yellow-dark', show : true},
		                  {title : $translate.instant('LABEL.VISUALIZAR'), actions : 'abrirPais(false);', iconClass: 'fa fa-search blue', show : true},
		                  {title : $translate.instant('LABEL.EDITAR'), actions : 'abrirPais(true);', iconClass: 'fa fa-pencil blue', show : true},
		                  {title : $translate.instant('LABEL.REMOVER'), actions : 'remove();', iconClass: 'fa fa-times red', show : true})

			}

		}],
		link : function($scope, $element, $attributes) {

			$scope.hasDropdownMenu = function(button){
	          return (angular.isDefined(button.menu) && angular.isArray(button.menu));
	        };

			$scope.barAction = function (actions){

				var functions = actions.split(';');

				angular.forEach(functions, function(item){

					if(item && item !== ""){

						$scope.$parent.$eval(item);
					}

				});

			};

		}
	};
}]);