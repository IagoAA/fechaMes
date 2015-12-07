/** Diretiva para campos identificadores	 **/
citApp.directive("identifier", [function() {
   return {
     require: '?ngModel',
     upper: '=ngUpper',
     link: function($scope, $element, attrs, modelCtrl) {

    	 $scope.replaceSpecialChar = function(input) {
    		 var output = input;

    		 var mapHex = {
    				 a : /[\xE0-\xE6]/g,
    				 A : /[\xC0-\xC6]/g,
    				 e : /[\xE8-\xEB]/g,
    				 E : /[\xC8-\xCB]/g,
    				 i : /[\xEC-\xEF]/g,
    				 I : /[\xCC-\xCF]/g,
    				 o : /[\xF2-\xF6]/g,
    				 O : /[\xD2-\xD6]/g,
    				 u : /[\xF9-\xFC]/g,
    				 U : /[\xD9-\xDC]/g,
    				 c : /\xE7/g,
    				 C : /\xC7/g,
    				 n : /\xF1/g,
    				 N : /\xD1/g,
    		 	};

    			for ( var character in mapHex ) {
    				var regularExpression = mapHex[character];
    				output = output.replace( regularExpression, character );
    			}

    			return output;
    	 };
    	 modelCtrl.$parsers.push(function (inputValue) {
           if (inputValue == undefined) return '';
           var transformedInput = inputValue.replace(/[\ ]+/g, '');
           if ($scope.upper)
        	   transformedInput = transformedInput.toUpperCase();

           transformedInput = $scope.replaceSpecialChar(transformedInput);
           if (transformedInput!=inputValue) {
              modelCtrl.$setViewValue(transformedInput);
              modelCtrl.$render();
           }

           return transformedInput;
       });
     }
   };
}]);

