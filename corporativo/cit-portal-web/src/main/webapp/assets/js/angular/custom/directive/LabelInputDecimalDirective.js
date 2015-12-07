/** Diretiva para campos label seguindo de input com validacoes obrigatorio/money
 * http://assisrafael.github.io/angular-input-masks/
 *  **/
citApp.directive("labelInputDecimal", ["$translate", function($translate) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			obrigatorio : "=ngObrigatorio",
			disabled : "=ngDisabled",
			model :	"=ngModel",
			form : "=form",
			eventoChange : "&ngEventoChange",
			precisao : "=ngPrecisao",
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@',
			maxlength : "@ngCustomMaxlength"
		},
		replace : true,
		restrict : 'E',
		template :  "<div class='form-group' ng-class=\"{'has-error': form[id].$error.required && (!form[id].$pristine || form.$submitted)}\">" +
					"	<label for='{{id}}' class='control-label' ng-if='labelRender'><translate>{{label}}</translate> <span class='red' ng-show='obrigatorio'>*</span> <i ng-if='obrigatorio' ng-show='form[id].$error.required && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{labelAlertTooltipCopy}}' tooltip-placement='top'></i> <i ng-if='labelInfoTooltipRender' class='fa fa-info-circle blue' tooltip='{{labelInfoTooltip}}' tooltip-placement='top'></i> <i ng-if='labelQuestionTooltipRender' class='fa fa-question-circle green' tooltip='{{labelQuestionTooltip}}' tooltip-placement='top'></i></label>"+
					" 	<input type='text' class='form-control' ng-change='eventoChange()' ng-keydown='removeModel($event)' id='{{id}}' name='{{id}}' ng-model='model' ng-disabled='disabled' maxlength='{{maxlength ? maxlength : 23}}' ng-required='obrigatorio' ui-number-mask='precisao'/>" +
					"</div>",
		controller: ['$scope', '$element', '$attrs', function ($scope, $element, $attrs) {
			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}

			// Não renderiza <label> caso nao foi definido 'label'
			$scope.labelRender = true;
			if($scope.label === undefined) {
				$scope.labelRender = false;
			}

			// Define um 'labelAlertTooltip' caso não tenha definido um
			$scope.labelAlertTooltipCopy = $scope.labelAlertTooltip;
			if($scope.labelAlertTooltip === undefined && $scope.obrigatorio === true) {
				// TODO retirar este copy e verificar pq não esta funcionando
				$scope.labelAlertTooltipCopy = $translate.instant($scope.label) + ' ' + $translate.instant('LABEL.CAMPO_OBRIGATORIO');
			}

			// Não renderiza o icone de tooltip de informação
			$scope.labelInfoTooltipRender = true;
			if($scope.labelInfoTooltip === undefined) {
				$scope.labelInfoTooltipRender = false;
			}

			// Não renderiza o icone de tooltip de dúvida
			$scope.labelQuestionTooltipRender = true;
			if($scope.labelQuestionTooltip === undefined) {
				$scope.labelQuestionTooltipRender = false;
			}
		}],
		link : function($scope, $element, attibutes){

			$scope.removeModel = function(event){
				if((event.keyCode === 8 || event.keyCode === 46) && $scope.model === 0.0){
					$scope.model = null;
				}
			};
		}
	};
}]);