/** Diretiva para campos label seguindo de input com validacoes obrigatorio/email **/
citApp.directive("labelInputIdentifier", ["$translate", function($translate) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			obrigatorio : "=ngObrigatorio",
			disabled : "=ngDisabled",
			model :	"=ngModel",
			mask : "=ngMask",
			maxlength : "@ngCustomMaxlength",
			form : "=form",
			type : "@ngType",
			eventoBlur : "&ngEventoBlur",
			eventoChange : "&ngEventoChange",
			regex :	"=ngRegex",
			placeholder: '@',
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@',
			maxNumber : "=ngMaxNumber",
			minNumber : "=ngMinNumber"
		},
		replace : true,
		restrict : 'E',
		template :  "<div class='form-group' ng-class=\"{'has-error': (form[id].$error.required || form[id].$error.regexValidate) && (!form[id].$pristine || form.$submitted)}\">" +
					"	<label for='{{id}}' class='control-label' ng-if='labelRender'><translate>{{label}}</translate> " +
					"		<span class='red' ng-if='obrigatorio'>*</span> " +
					"		<i ng-if='obrigatorio' ng-show='form[id].$error.required && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{labelAlertTooltipCopy}}' tooltip-placement='top'></i> " +
					"		<i ng-if='validacaoRegex' ng-show='form[id].$error.regexValidate && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{invalidRegex}}' tooltip-placement='top'></i> " +
					"		<i ng-show='form[id].$error.email && (!form[id].$pristine || form.$pristine)' class='fa fa-warning red' tooltip='{{invalidTooltip}}' tooltip-placement='top'></i> " +
					"		<i ng-if='labelInfoTooltipRender' class='fa fa-info-circle blue' tooltip='{{labelInfoTooltipMsg}}' tooltip-placement='top'></i> " +
					"		<i ng-if='labelQuestionTooltipRender' class='fa fa-question-circle green' tooltip='{{labelQuestionTooltip}}' tooltip-placement='top'></i>" +
					"	</label>"+
					" 	<input identifier type='{{type}}' regex-validate='{{regex}}' ng-blur='eventoBlur()' ng-change='eventoChange()' class='form-control' id='{{id}}' maxlength='{{maxlength ? maxlength : 255}}' name='{{id}}' ng-model='model' ui-mask='{{mask}}' ng-disabled='disabled' ng-required='obrigatorio' placeholder='{{placeholder}}'/>" +
					"</div>",
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {
			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}
			$scope.validacaoRegex = $scope.regex ? true : false;

			$scope.invalidRegex = $translate.instant($scope.label) + ' ' + $translate.instant('LABEL.NAO_ATENDE_REGEX');

			// Define mensagem de email invalido
			$scope.invalidTooltip = $translate.instant($scope.label) + ' ' + $translate.instant('LABEL.INVALIDO');

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
			if(!$scope.labelInfoTooltip) {
				$scope.labelInfoTooltipRender = false;
			}else{
				console.log($translate.instant($scope.labelInfoTooltip));
				$scope.labelInfoTooltipMsg = $translate.instant($scope.labelInfoTooltip);
			}

			// Não renderiza o icone de tooltip de dúvida
			$scope.labelQuestionTooltipRender = true;
			if($scope.labelQuestionTooltip === undefined) {
				$scope.labelQuestionTooltipRender = false;
			}
		}],
		link : function($scope, element, attibutes) {

		}
	};
}]);