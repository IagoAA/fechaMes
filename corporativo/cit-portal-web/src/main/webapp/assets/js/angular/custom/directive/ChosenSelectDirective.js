citApp.directive("citChosenSelect", [function () {
  return {
    restrict: "A",
    link: function (scope, element, attrs) {
      element.chosen({
        allow_single_deselect: true,
        width: '100%',
        dateFormat: 'dd/mm/yyyy'
      });
    }
  };
}]);