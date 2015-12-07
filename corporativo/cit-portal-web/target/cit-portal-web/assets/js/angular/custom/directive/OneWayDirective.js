citApp.directive('oneWay', [function() {
    return {
        scope: true,
        link: function($scope,$elm) {
            setTimeout(function() {
                $scope.$destroy();
                $elm.removeClass('ng-binding ng-scope');
            }, 0);
        }
    };
}]);
