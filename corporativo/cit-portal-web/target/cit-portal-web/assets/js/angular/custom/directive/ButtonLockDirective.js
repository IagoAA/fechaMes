citApp.directive("buttonLock", ["$translate", function($translate) {

	return {
		require: ['^ngModel'],
		scope : {
			model :	"=ngModel",
			actionLock: "&"
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/buttonLock.html',
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {
			$scope.id = $attrs['ngModel'];

			$scope.$buttonLockOpen = false;
		}],
		link : function($scope, $element, $attibutes){
			$scope.noCloseDropdown = function ($e) {
				$e.preventDefault();
				$e.stopPropagation();
			};
		}
	};
}]);