/**
 * Heavily adapted from the `type="number"` directive in Angular's
 * /src/ng/directive/input.js
 */

'use strict';

citApp.directive('money', ['$filter', '$locale', function ($filter, $locale) {
    return {
        require: '?ngModel',
        $scope: {
            min: '=min',
            max: '=max',
            ngRequired: '=ngRequired'
        },
        controller : ['$scope', function($scope){
        	$scope.cVal = undefined;
        }],
        link: function ($scope, element, attrs, ngModel) {
        	function decimalRex(dChar) {
                return RegExp("\\d|\\" + dChar, 'g')
            }

            function clearRex(dChar) {
                return RegExp("((\\" + dChar + ")|([0-9]{1,}\\" + dChar + "?))&?[0-9]{0,2}", 'g');
            }

            function decimalSepRex(dChar) {
                return RegExp("\\" + dChar, "g")
            }

            function clearValue(value) {
                value = String(value);
                var dSeparator = ",";
                var clear = null;

                if (value.match(decimalSepRex(dSeparator))) {
                    clear = value.match(decimalRex(dSeparator))
                        .join("").match(clearRex(dSeparator));
                    clear = clear ? clear[0].replace(dSeparator, ".") : null;
                }
                else if (value.match(decimalSepRex("."))) {
                    clear = value.match(decimalRex("."))
                        .join("").match(clearRex("."));
                    clear = clear ? clear[0] : null;
                }
                else {
                    clear = value.match(/\d/g);
                    clear = clear ? clear.join("") : null;
                }

                return clear;
            }

            ngModel.$parsers.push(function (viewValue) {
            	$scope.cVal = clearValue(viewValue);
                return parseFloat($scope.cVal);
            });

            element.on("blur", function () {
                element.val($filter('currency')(ngModel.$modelValue));
            });

            ngModel.$formatters.unshift(function (value) {
                return $filter('currency')(value);
            });

            $scope.$watch(function () {
                return ngModel.$modelValue
            }, function (newValue, oldValue) {
                runValidations(newValue)
            })

            function runValidations(cVal) {
                if (!$scope.ngRequired && isNaN(cVal)) {
                    return
                }
                if ($scope.min) {
                    var min = parseFloat($scope.min)
                    ngModel.$setValidity('min', cVal >= min)
                }
                if ($scope.max) {
                    var max = parseFloat($scope.max)
                    ngModel.$setValidity('max', cVal <= max)
                }
            }

        }
    };
}]);