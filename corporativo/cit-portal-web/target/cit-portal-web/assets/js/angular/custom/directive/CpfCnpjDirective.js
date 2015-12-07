
citApp.directive("ngCpf", [function() {
	return {

        restrict: 'A',

        require: 'ngModel',

        link: function(scope, elm, attrs, ctrl) {
          scope.$watch(attrs.ngModel, function(newVal, oldVal) {
            ctrl.$setValidity( 'cpf', CPF.isValid(newVal) );
          });
        }

      };
}]);

citApp.directive('ngCnpj', [function() {
    return {

      restrict: 'A',

      require: 'ngModel',

      link: function(scope, elm, attrs, ctrl) {
        scope.$watch(attrs.ngModel, function(newVal, oldVal) {
          ctrl.$setValidity( 'cnpj', CNPJ.isValid(newVal) );
        });
      }

    };
}]);
