citApp.directive('sbMax', [function(){
	return {
		restrict: 'A',
		require: '?ngModel',
		link: function($scope, $element, attrs, ngModel) {

			function maximum(inputValue){
				if (inputValue !== null && inputValue !== undefined){
					var inputTransformed = inputValue;
					if(!isNaN(inputTransformed)){
						var validity = Number(inputTransformed) <= attrs.sbMax;
						if (!validity){
							inputTransformed = inputTransformed.substring(0, inputTransformed.length-1);
						}
					}

					if (inputTransformed !== inputValue) {
			 			ngModel.$setViewValue(inputTransformed);
			 			ngModel.$render();
			 		}

				    return inputTransformed;
				}
				return inputValue;
			}

		ngModel.$parsers.push(maximum);
		ngModel.$formatters.push(maximum);
		}
	};
}]);

citApp.directive('sbMin', [function(){
	return {
		restrict: 'A',
		require: '?ngModel',
		link: ['$scope', '$element', 'attrs', 'ngModel', function($scope, $element, attrs, ngModel) {

			function minimum(inputValue){
				if (inputValue !== null && inputValue !== undefined){
					var inputTransformed = inputValue;
					if(!isNaN(inputTransformed)){
						var validity = Number(inputTransformed) >= attrs.sbMin;
						if (!validity){
							inputTransformed = inputTransformed.substring(0, inputTransformed.length-1);
				    	}
					}

			 		if (inputTransformed !== inputValue) {
			 			ngModel.$setViewValue(inputTransformed);
			 			ngModel.$render();
			 		}

					return inputTransformed;
				}
				return inputValue;
			}
		ngModel.$parsers.push(minimum);
		ngModel.$formatters.push(minimum);
		}]
	};
}]);

/** Diretiva para campos number	 **/
citApp.directive("sbNumber", [function() {
   return {
		restrict: 'A',
		require: '?ngModel',
		link: ['$scope', '$element', 'attrs', 'ngModel', function($scope, $element, attrs, ngModel) {

			function validateNumber (inputValue) {
				if (inputValue !== null && inputValue !== undefined){
			 		var validity = !isNaN(inputValue);
			 		var inputTransformed = inputValue;

			 		if (!validity){
			 			inputTransformed = inputTransformed.substring(0, inputTransformed.length-1);
			    	}

			 		if (inputTransformed !== inputValue) {
			 			ngModel.$setViewValue(inputTransformed);
			 			ngModel.$render();
			 		}

			    	return inputTransformed;
				}
				return inputValue;
		 	};

		 	ngModel.$parsers.push(validateNumber);
		 	ngModel.$formatters.push(validateNumber);
		}]
   	};
}]);

citApp.directive('sbPrecision', [function(){
	return {
		restrict: 'A',
		require: 'ngModel',
		link: ['$scope', '$element', 'attrs', 'ngModel', function($scope, $element, attrs, ngModel){
			var precision = attrs.sbPrecision;

			function setPrecision(){
				var value = ngModel.$viewValue;

			    //since this is just a mask, don't hide decimal values that
			    //go beyond our precision and don't format empty values
			    if(value && !isNaN(value) && countDecimalPlaces(value) <= precision){
			      var formatted = Number(value).toFixed(precision);
			      ngModel.$viewValue = formatted;
			      ngModel.$render();
			    }
			}

			$element.bind('blur', setPrecision);
			setTimeout(setPrecision, 0); //after initial page render
		}]
	};
}]);

citApp.directive('sbMaxPrecision', [function(){
	return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: ['$scope', '$element', 'attrs', 'ngModel', function($scope, $element, attrs, ngModel){

			function maxPrecision(inputValue){
				if (inputValue !== null && inputValue !== undefined){
					var inputTransformed = inputValue;
				    if(!isNaN(inputTransformed)){
				    	var validity = countDecimalPlaces(inputTransformed) <= attrs.sbMaxPrecision;

						if (!validity){
							inputTransformed = inputTransformed.substring(0, inputTransformed.length-1);
				    	}
				    }

				    if (inputTransformed !== inputValue) {
			 			ngModel.$setViewValue(inputTransformed);
			 			ngModel.$render();
			 		}

				    return inputTransformed;
				}
				return inputValue;
			}

		ngModel.$parsers.push(maxPrecision);
		ngModel.$formatters.push(maxPrecision);
	    }]

	};
}]);

function countDecimalPlaces(value){
var decimalPos = String(value).indexOf('.');
	if(decimalPos === -1){
	    return 0;
	}else{
	    return String(value).length - decimalPos -1;
	}
}
