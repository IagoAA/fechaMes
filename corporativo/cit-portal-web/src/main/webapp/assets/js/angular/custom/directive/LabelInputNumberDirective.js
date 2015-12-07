/** Diretiva para campos label seguindo de input com validacoes obrigatorio/email **/
citApp.directive("labelInputNumber", ["$translate", function($translate) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			obrigatorio : "=ngObrigatorio",
			disabled : "=ngDisabled",
			model :	"=ngModel",
			form : "=form",
			eventoBlur : "&ngEventoBlur",
			eventoChange : "&ngEventoChange",
			regex :	"=ngRegex",
			placeholder: '@',
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@',
			maxlength : "@ngCustomMaxlength",
			maxNumber : "=ngMaxNumber",
			minNumber : "=ngMinNumber"
		},
		replace : true,
		restrict : 'E',
		template :  "<div class='form-group' ng-class=\"{'has-error': (form[id].$error.required || form[id].$error.regexValidate) && (!form[id].$pristine || form.$submitted)}\">" +
					"	<label for='{{id}}' class='control-label' ng-if='labelRender'><translate>{{label}}</translate> " +
					"		<span class='red' ng-show='obrigatorio'>*</span> " +
					"		<i ng-if='obrigatorio' ng-show='form[id].$error.required && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{labelAlertTooltipCopy}}' tooltip-placement='top'></i> " +
					"		<i ng-if='validacaoRegex' ng-show='form[id].$error.regexValidate && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{invalidRegex}}' tooltip-placement='top'></i> " +
					"		<i ng-if='labelInfoTooltipRender' class='fa fa-info-circle blue' tooltip='{{labelInfoTooltip}}' tooltip-placement='top'></i> " +
					"		<i ng-if='labelQuestionTooltipRender' class='fa fa-question-circle green' tooltip='{{labelQuestionTooltip}}' tooltip-placement='top'></i>" +
					"	</label>"+
 					" 	<input type='text' ng-blur='eventoBlur()' ng-change='eventoChange()' min='minNumber' max='maxNumber' class='form-control' id='{{id}}' integer name='{{id}}' ng-trim='false' maxlength='{{maxlength ? maxlength : 19}}' ng-model='model' ng-disabled='disabled' ng-required='obrigatorio' placeholder='{{placeholder}}'/>" +
					"</div>",
		controller: ['$scope', '$element', '$attrs', function ($scope, $element, $attrs) {
			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}

			$scope.validacaoRegex = $scope.regex ? true : false;

			$scope.invalidRegex = $translate.instant($scope.label) + ' ' + $translate.instant('LABEL.NAO_ATENDE_REGEX');

			// Define um placeholder para o item caso ele não seja definido
			if($scope.placeholder === undefined && $scope.disabled !== true) {
				if($scope.label !== undefined) {
					$scope.placeholder = $translate.instant("LABEL.PLACEHOLDER_GENERICO") + $translate.instant($scope.label);
				} else {
					$scope.placeholder = '';
				}
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

		}
	};
}]);