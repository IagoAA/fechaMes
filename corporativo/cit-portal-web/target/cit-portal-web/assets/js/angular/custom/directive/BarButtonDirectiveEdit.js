/** Diretiva para barra de acoes de crud e adicao de butoes customizados **/
citApp.directive("barButtonEdit", ["$translate", "$rootScope", function($translate, $rootScope) {

	return {

		scope : {
			buttons : '=?ngButtons',
			buttonsPadrao : '=ngButtonsPadrao',
			model : '=ngModel'
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/barButton.html',

		controller: ['$scope', function($scope) {
		}],
		link : function($scope, $element, $attributes) {

			$scope.hasDropdownMenu = function(button){
	          return (angular.isDefined(button.menu) && angular.isArray(button.menu));
	        };

	        $scope.$on('barButttonDirective', function(event) {

	        	$scope.loadButtons();

	        });

	        $scope.loadButtons =  function (){

	        	if (!$scope.buttons){

					$scope.buttons = [];

					$scope.buttons.push({title : $translate.instant('LABEL.SALVAR'), actions : 'saveOrUpdate();', iconClass: 'fa fa-save green', show : $scope.$parent.edit},
			                  {title : $translate.instant('LABEL.LIMPAR'), actions : 'resetForm();', iconClass: 'fa fa-eraser yellow-dark', show : !$scope.$parent.pais.id},
			                  {title : $translate.instant('LABEL.EDITAR'), actions : 'edit = true; pgEdit = true;', iconClass: 'fa fa-pencil blue', show : !($scope.$parent.pgEdit || $scope.$parent.edit)},
			                  {title : $translate.instant('LABEL.REMOVER'), actions : 'remove(pais);', iconClass: 'fa fa-times red', show : $scope.$parent.pais.id},
			                  {title : $translate.instant('LABEL.PESQUISAR'), actions : '$showPageSearchWorkspace(workspace); atualizaPaginaPesquisa();', iconClass: 'fa fa-search', show : true})
				}

				if ($scope.buttons && $scope.buttonsPadrao){

					$scope.buttons.push({title : $translate.instant('LABEL.SALVAR'), actions : 'saveOrUpdate();', iconClass: 'fa fa-save green', show : $scope.$parent.edit},
			                  {title : $translate.instant('LABEL.LIMPAR'), actions : 'resetForm();', iconClass: 'fa fa-eraser yellow-dark', show : !$scope.$parent.pais.id},
			                  {title : $translate.instant('LABEL.EDITAR'), actions : 'edit = true; pgEdit = true;', iconClass: 'fa fa-pencil blue', show : !($scope.$parent.pgEdit || $scope.$parent.edit)},
			                  {title : $translate.instant('LABEL.REMOVER'), actions : 'remove(pais);', iconClass: 'fa fa-times red', show : $scope.model.id},
			                  {title : $translate.instant('LABEL.PESQUISAR'), actions : '$showPageSearchWorkspace(workspace); atualizaPaginaPesquisa();', iconClass: 'fa fa-search', show : true})

				}

	        }

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