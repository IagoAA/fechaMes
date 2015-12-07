/** Diretiva para campos label seguindo de textarea com validacao obrigatorio **/
citApp.directive("labelTextArea", ["$translate", function($translate) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			obrigatorio : "=ngObrigatorio",
			disabled : "=ngDisabled",
			model :	"=ngModel",
			maxlength : "@ngCustomMaxlength",
			regex :	"=ngRegex",
			form : "=form",
			rows : "=rows",
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@',
			eventoChange : "&ngEventoChange"
		},
		replace : true,
		restrict : 'E',
		template :  "<div class='form-group' ng-class=\"{'has-error': (form[id].$error.required || form[id].$error.regexValidate) && (!form[id].$pristine || form.$submitted)}\">" +
					"	<label for='{{id}}' class='control-label' ng-if='labelRender'><translate>{{label}}</translate> " +
					"		<span class='red' ng-if='obrigatorio'>*</span> " +
					"		<i ng-if='obrigatorio' ng-show='form[id].$error.required && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{labelAlertTooltipCopy}}' tooltip-placement='top'></i> " +
					"		<i ng-if='validacaoRegex' ng-show='form[id].$error.regexValidate && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{invalidRegex}}' tooltip-placement='top'></i> " +
					"		<i ng-if='labelInfoTooltipRender' class='fa fa-info-circle blue' tooltip='{{labelInfoTooltip}}' tooltip-placement='top'></i> " +
					"		<i ng-if='labelQuestionTooltipRender' class='fa fa-question-circle green' tooltip='{{labelQuestionTooltip}}' tooltip-placement='top'></i>" +
					"	</label>" +
					"	<textarea class='form-control' id='{{id}}' regex-validate='{{regex}}' ng-change='eventoChange()' rows='{{rows}}' maxlength='{{maxlength ? maxlength : 255}}' ng-disabled='disabled' name='{{id}}' ng-model='model' ng-disabled='disabled' ng-required='obrigatorio' style='resize: none;' placeholder='"+$translate.instant("LABEL.PLACEHOLDER_GENERICO") + "{{label | translate}}'/>" +
					"</div>",
		controller: ['$scope', '$element', '$attrs', function ($scope, $element, $attrs) {
			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}
			$scope.validacaoRegex = $scope.regex ? true : false;

			$scope.invalidRegex = $translate.instant($scope.label) + ' ' + $translate.instant('LABEL.NAO_ATENDE_REGEX');

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
		}]
	};
}]);