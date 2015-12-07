citApp.directive("pickList", ["$translate", function($translate) {

	return {
		scope : {
			listSource : "=",
			listTarget : "=",
			sourceLabel : "@",
			targetLabel : "@",
			disabled : "=ngDisabled",
			itemLabel : "@"
		},
		replace : true,
		restrict : 'E',
		templateUrl :  "assets/js/angular/custom/directive/html/pickList.html",
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {
			$scope.itensLabel = undefined;
			if($scope.itemLabel.indexOf(".") > -1){
				$scope.itensLabel = $scope.itemLabel.split(".");
			}

		}],
		link : function($scope, element, attibutes) {
			var removeItemsSelected = function (list) {
				list.forEach(function (item) {
					item.$selected = false;
				});
			};

			$scope.selectSourceItem = function ($e, item) {
				if(!$e.ctrlKey) {
					removeItemsSelected($scope.listSource);
				}

				item.$selected = !item.$selected;
			};

			$scope.selectTargetItem = function ($e, item) {
				if(!$e.ctrlKey) {
					removeItemsSelected($scope.listTarget);
				}

				item.$selected = !item.$selected;
			};

			$scope.addSourceInTarget = function () {

				var listSourceTemp = angular.copy($scope.listSource);

				var listTargetAdd = listSourceTemp.filter(function (itemSource){

					if (itemSource.$selected){
						itemSource.$selected = !itemSource.$selected;
						return itemSource;
					}

				});

				var listTargetOld = angular.copy($scope.listTarget);

				$scope.listTarget = listTargetOld.concat(listTargetAdd);

				$scope.listSource = $scope.listSource.filter(function (itemSource){

					return !itemSource.$selected;

				});

			};

			$scope.removeSourceInTarget = function () {

				var listTargetTemp = angular.copy($scope.listTarget);

				var listSourceAdd = listTargetTemp.filter(function (itemTarget){

					if (itemTarget.$selected){
						itemTarget.$selected = !itemTarget.$selected;
						return itemTarget;
					}

				});

				var listSourceOld = angular.copy($scope.listSource);

				$scope.listSource = listSourceOld.concat(listSourceAdd);

				$scope.listTarget = $scope.listTarget.filter(function (itemTarget){

					return !itemTarget.$selected;

				});

			};

			$scope.addAllSourceInTarget = function () {

				var listSourceTemp = angular.copy($scope.listSource);

				var listTargetAdd = listSourceTemp.filter(function (itemSource){

					return itemSource;

				});

				var listTargetOld = angular.copy($scope.listTarget);

				$scope.listTarget = listTargetOld.concat(listTargetAdd);

				$scope.listSource = [];

			};

			$scope.removeAllSourceInTarget = function () {

				var listTargetTemp = angular.copy($scope.listTarget);

				var listSourceAdd = listTargetTemp.filter(function (itemTarget){

					return itemTarget;

				});

				var listSourceOld = angular.copy($scope.listSource);

				$scope.listSource = listSourceOld.concat(listSourceAdd);

				$scope.listTarget = [];

			};
		}
	};
}]);