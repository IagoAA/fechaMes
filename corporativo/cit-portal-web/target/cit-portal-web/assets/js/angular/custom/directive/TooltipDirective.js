citApp.directive("citTooltip", [function () {
  return {
    restrict: "A",
    link: function (scope, element, attrs) {
      element.tooltip();
    }
  };
}]);