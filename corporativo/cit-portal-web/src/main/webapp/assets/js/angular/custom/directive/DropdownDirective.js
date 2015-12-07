citApp.directive("citDropdown", [function () {
  return {
    require: ["^citModel", "^citOptions"],
    restrict: "AE",
    replace: true,
    scope: {
      id: "@citId",
      name: "@citName",
      attrFor: "@citOptionAttrFor",
      model: "=citModel",
      options: "=citOptions"
    },
    templateUrl: "assets/js/angular/custom/directive/html/citDropdown.html"
  };
}]);