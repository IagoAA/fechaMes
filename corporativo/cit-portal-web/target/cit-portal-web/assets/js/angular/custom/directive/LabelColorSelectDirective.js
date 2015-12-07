citApp.directive("labelColorSelect",["$translate", function($translate) {
	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			obrigatorio : "=ngObrigatorio",
			disabled : "=ngDisabled",
			model :	"=ngModel",
			form : "=form",
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@',
			eventoChange : "&ngEventoChange",
	        customizedColors: '=colors'
		},
		replace : false,
		restrict : 'E',
		templateUrl : "assets/js/angular/custom/directive/html/colorSelect.html", 
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {
		    $scope.defaultColors =  [
				'#ffffff',
				'#000000',
				'#3f3f3f',
				'#595959',
				'#7f7f7f',
				'#44546a',
				'#8496b0',
				'#adb9ca',
				'#d6dce4',
				'#5b9bd5',
				'#9cc3e5',
				'#bdd7ee',
				'#deebf6',
				'#ed7d31',
				'#f4b183',
				'#f7cbac',
				'#fbe5d5',
				'#ffc000',
				'#ffd965',
				'#fee599',
				'#fff2cc',
				'#4472c4',
				'#8eaadb',
				'#b4c6e7',
				'#d9e2f3',
				'#70ad47',
				'#a8d08d',
				'#c5e0b3',
				'#e2efd9'
            ];

            $scope.colors = $scope.customizedColors || $scope.defaultColors;

            if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}

			// Não renderiza <label> caso nao foi definido 'label'
			$scope.labelRender = true;
			if($scope.label === undefined) {
				$scope.labelRender = false;
			}

			// Define um 'labelAlertTooltip' caso não tenha definido um
			$scope.labelAlertTooltipCopy = $scope.labelAlertTooltip;
			if($scope.labelAlertTooltip === undefined && $scope.obrigatorio === true) {
				// TODO retirar este copy e verificar pq não esta funcionando
				$scope.labelAlertTooltipCopy = $translate.instant($scope.label) + ' ' + $translate.instant('LABEL.CAMPO_OBRIGATORIO');
			}

			// Não renderiza o icone de tooltip de informação
			$scope.labelInfoTooltipRender = true;
			if($scope.labelInfoTooltip === undefined) {
				$scope.labelInfoTooltipRender = false;
			}

			// Não renderiza o icone de tooltip de dúvida
			$scope.labelQuestionTooltipRender = true;
			if($scope.labelQuestionTooltip === undefined) {
				$scope.labelQuestionTooltipRender = false;
			}
		}],
		link : function($scope, $element, attibutes, modelCtrl){
			var colorIsValidPattern = /(^#[0-9A-F]{6}$)|(^#[0-9A-F]{3}$)/i;
			var colorIsValid = function() {
				return colorIsValidPattern.test($scope.model);
			};
			
			$scope.isOpen = false;
			
			$scope.$watch('isOpen', function() {
				if($scope.isOpen) {
					$scope.oldColor = angular.copy($scope.model);
				} else if(!colorIsValid()) {
					$scope.model = angular.copy($scope.oldColor);
				}
				
				$scope.hasError = false;
			});
			
			$scope.selectColor = function(color) {
				$scope.model = color;
			};
			
			$scope.noClose = function($e) {
				$e.preventDefault();
				$e.stopPropagation();
			};
			
			$scope.close = function($e) {
				$e.preventDefault();
				$e.stopPropagation();
				
				if($scope.model && colorIsValid()) {
					$scope.isOpen = false;
				} else {
					$scope.hasError = true;
				}
			};
		}
	};
}]);