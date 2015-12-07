/** Diretiva para campos label seguindo de input com validacoes obrigatorio/email **/
citApp.directive("labelSelect", ["$translate", function($translate) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			obrigatorio : "=ngObrigatorio",
			disabled : "=ngDisabled",
			model :	"=ngModel",
			form : "=form",
			lista : "=ngList",
			options : "@ngCustomOptions",
			trackBy: "@",
			change : "&ngCustomChange",
			multiple: '@',
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@'
		},
		replace : true,
		restrict : 'E',
		template :  "<div class='form-group' ng-class=\"{'has-error': form[id].$error.required && (!form[id].$pristine || form.$submitted)}\">" +
					"	<label for='{{id}}' class='control-label' ng-if='labelRender'><translate>{{label}}</translate> <span class='red' ng-if='obrigatorio'>*</span> <i ng-if='obrigatorio' ng-show='form[id].$error.required && (!form[id].$pristine || form.$submitted)' class='fa fa-warning red' tooltip='{{labelAlertTooltipCopy}}' tooltip-placement='top'></i> <i ng-if='labelInfoTooltipRender' class='fa fa-info-circle blue' tooltip='{{labelInfoTooltip}}' tooltip-placement='top'></i> <i ng-if='labelQuestionTooltipRender' class='fa fa-question-circle green' tooltip='{{labelQuestionTooltip}}' tooltip-placement='top'></i></label>" +
					"	<select ng-multiple='true' class='form-control' name='{{id}}' id='{{id}}' ng-model='model' ng-options='{{options}} in lista {{trackBy}}' ng-change='setChangeItem(model)' ng-disabled='disabled' ng-required='obrigatorio'>" +
					"		<option value=''>{{selecione}}</option>" +
					"	</select>" +
					"</div>",
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {
			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}

			$scope.selecione = $translate.instant('LABEL.SELECIONE');

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
		link : function($scope, $element, attibutes, modelCtrl){

			$scope.setChangeItem = function (model) {

				$scope.change({model: model});

			};

		}
	};
}]);