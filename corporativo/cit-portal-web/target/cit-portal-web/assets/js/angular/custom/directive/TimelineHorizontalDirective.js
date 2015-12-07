citApp.directive("timelineHorizontal", ["$timeout", function($timeout) {
	var TIMELINE_ITEM_WIDTH = 28;
	var TIMELINE_ITEM_MARGIN_LEFT = 16.1;
	var TIMELINE_ITEM_MARGIN_RIGHT = 0;

	return {
		scope : {
			timelineClass: "@",
			showStartEndPoint: "@",
			startPointIcon: "@",
			startPointText: "@",
			endPointIcon: "@",
			endPointText: "@",
			list: "=",
			listScreen: "=",
			totalItems: "@",
			nextElementEvent: "&"
		},
		replace : true,
		restrict : "E",
		transclude: true,
		templateUrl : "assets/js/angular/custom/directive/html/timelineHorizontal.html",
		link : function($scope, $element, attributes) {
			var timelineHorizontal = angular.element($element[0]),
				timelineItems = timelineHorizontal.find('.timeline-items'),
				timelineButtonNext = timelineItems.find('.timeline-content-button-prev'),
				timelineButtonPrev = timelineItems.find('.timeline-content-button-next'),
				indexFirstActiveItem = 0,
				indexLastActiveItem,
				widthTimelineItems,
				widthTimelineItem,
				countTimelineItem,
				watchListScreen;

			var renderButtonsTimeline = function () {
				if($scope.listScreen.length > countTimelineItem) {
					$scope.showButtons = true;
					$scope.showButtonPrev = true;
					$scope.showButtonNext = true;
				} else {
					$scope.showButtons = false;
					$scope.showButtonPrev = false;
					$scope.showButtonNext = false;
				}
			};

			var getTimelineHorizontalSizes = function () {
				widthTimelineItems = timelineItems.width();
				widthTimelineItem = TIMELINE_ITEM_WIDTH + TIMELINE_ITEM_MARGIN_LEFT + TIMELINE_ITEM_MARGIN_RIGHT;
				countTimelineItem = Math.floor( widthTimelineItems / widthTimelineItem );
			};

			var fillTimelineHorizontal = function (callback) {
				// Enquanto houver espaço para carregar items vai carregar
				(function load() {
					if($scope.listScreen.length < countTimelineItem && $scope.list.length < $scope.totalItems) {
						$scope.nextElementEvent().then(function (result) {
							load();
						});
					} else {
						callback();

						return ;
					}
				})();
			};

			var renderTimelineHorizontal = function () {
				renderButtonsTimeline();

				indexLastActiveItem = (indexFirstActiveItem + countTimelineItem) - 1;

				for(var i = 0; i < $scope.listScreen.length; i++) {
					var item = $scope.listScreen[i];
					if(i >= indexFirstActiveItem && i < (countTimelineItem + indexFirstActiveItem)) {
						item.hidden = false;
					} else {
						item.hidden = true;
					}
				}
			};

			var initTimelineHorizontal = function () {
				getTimelineHorizontalSizes();

				fillTimelineHorizontal(function () {
					renderTimelineHorizontal();
				});
			};

			var resizeTimelineHorizontal = function () {
				getTimelineHorizontalSizes();

				fillTimelineHorizontal(function () {
					renderButtonsTimeline();

					indexFirstActiveItem = 0;
					indexLastActiveItem = countTimelineItem - 1;

					for(var i = 0; i < $scope.listScreen.length; i++) {
						var item = $scope.listScreen[i];
						if(i < countTimelineItem) {
							item.hidden = false;
						} else {
							item.hidden = true;
						}
					}

					return ;
				});
			};

			var nextItem = function () {
				$scope.listScreen[indexFirstActiveItem].hidden = true;
				indexFirstActiveItem++;

				indexLastActiveItem++;
				$scope.listScreen[indexLastActiveItem].hidden = false;
			};

			var prevItem = function () {
				$scope.listScreen[indexLastActiveItem].hidden = true;
				indexLastActiveItem--;

				indexFirstActiveItem--;
				$scope.listScreen[indexFirstActiveItem].hidden = false;
			};

			$scope.nextItem = function () {
				if($scope.listScreen[indexLastActiveItem + 1]) {
					nextItem();
				} else if($scope.list.length < $scope.totalItems) {
					$scope.nextElementEvent().then(function (result) {
						$timeout(function () {
							nextItem();
						}, 200);
					});
				} else {
					$scope.showButtonNext = false;
				}

				$scope.showButtonPrev = true;
			};

			$scope.prevItem = function () {
				if($scope.listScreen[indexFirstActiveItem - 1]) {
					prevItem();
				} else {
					$scope.showButtonPrev = false;
				}

				$scope.showButtonNext = true;
			};

			watchListScreen = $scope.$watch("listScreen", function () {
				initTimelineHorizontal();
			});

			angular.element(window).resize(function () {
				resizeTimelineHorizontal();
			});

			initTimelineHorizontal();
		}
	};
}]);

