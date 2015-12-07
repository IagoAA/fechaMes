citApp.directive('regexValidate', [function() {
    return {
        restrict: 'A',
        require: 'ngModel',

        link: function(scope, elem, attr, ctrl) {

            var flags = attr.regexValidateFlags || '';

            var regex = new RegExp(attr.regexValidate, flags);

            ctrl.$parsers.unshift(function(value) {

            	if(value){

            		var valid = regex.test(value);

            		ctrl.$setValidity('regexValidate', valid);

            		return valid ? value : undefined;

            	}else{

            		ctrl.$setValidity('regexValidate', true);

            		return value;

            	}

            });

            ctrl.$formatters.unshift(function(value) {

            	if(value){

            		ctrl.$setValidity('regexValidate', regex.test(value));

            	}else{

            		ctrl.$setValidity('regexValidate', true);
            	}

            	return value;
            });
        }
    };
}]);