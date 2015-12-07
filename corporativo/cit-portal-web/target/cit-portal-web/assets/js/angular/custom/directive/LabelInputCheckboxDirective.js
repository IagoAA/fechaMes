/** Diretiva para campos label seguindo de input radio	 **/
citApp.directive("labelInputCheckbox", ["$translate", "$timeout", function($translate, $timeout) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			model :	"=ngModel",
			disabled : "=ngDisabled",
		},
		replace : true,
		restrict : 'E',
		template :  "<label class='label-switch'>" +
					"	<input type='checkbox' name='{{id}}' ng-checked='checked' ng-model='model' ng-disabled='disabled' class='switch-ckeckbox switch switch-5 btn-rotate' />" +
					"	<span class='lbl' data-lbl='SIM&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;N&Atilde;O'></span> "+
					" 	<span class='lbl-text lbl-text-left'><translate>{{label}}</translate></span>" +
					" </label>",
		controller: ['$scope', '$element', '$attrs', function ($scope, $element, $attrs) {
			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}
			$scope.checked = false;
			if($scope.model && ($scope.model === 'true' || $scope.model === true)){
				$scope.checked = true;
			}
			$timeout(function(){
				$scope.model = $scope.checked;
			});
			// Não renderiza <label> caso nao foi definido 'label'
			$scope.labelRender = true;
			if($scope.label === undefined) {
				$scope.labelRender = false;
			}
		}],
		link : function($scope, $element, attibutes){
		}
	};
}]);