/** Diretiva para campos label seguindo de input radio	 **/
citApp.directive("labelInputRadio", ["$translate", function($translate) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			model :	"=ngModel",
			obrigatorio : "=ngObrigatorio",
			label : "@ngLabel",
			list : "=",
			disabled : "=ngDisabled",
			form : "=form",
			value: '@',
			text: '@',
			labelAlertTooltip: '@',
			change : "&ngCustomChange",
		},
		replace : true,
		restrict : 'E',
		template :
				"<div class='form-group' ng-class=\"{'has-error': form[id].$error.required && (!form[id].$pristine || form.$submitted)}\">"+
					"	    <label class='control-label block'><translate>{{label}}</translate> " +
					"			<span class='red' ng-if='obrigatorio'>*</span> " +
					"			<i ng-if='obrigatorio' ng-show='form[id].$error.required && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{labelAlertTooltipCopy}}' tooltip-placement='top'></i> "+
					"        </label>"+
					"       <div ng-repeat='item in list' class='radio-inline' >" +
					"			<label > "+
					" 				<input type='radio' name={{name}} ng-model='$parent.model' ng-required='obrigatorio' ng-change='setChangeItem(model)' ng-disabled='disabled' ng-value='{{value ? item[value] : item}}' /> {{item[text]}}" +
					"			</label>" +
					"	     </div>" +
				"</div>",

		controller: ['$scope', '$element', '$attrs', function ($scope, $element, $attrs) {
			$scope.name = $attrs['ngModel'];

			// Define um 'labelAlertTooltip' caso não tenha definido um
			$scope.labelAlertTooltipCopy = $scope.labelAlertTooltip;
			if($scope.labelAlertTooltip === undefined && $scope.obrigatorio === true) {
				// TODO retirar este copy e verificar pq não esta funcionando
				$scope.labelAlertTooltipCopy = $translate.instant($scope.label) + ' ' + $translate.instant('LABEL.CAMPO_OBRIGATORIO');
			}
		}],
		link : function($scope, $element, attibutes){
			
			$scope.setChangeItem = function (model) {

				$scope.change({model: model});

			};
		}
	};
}]);