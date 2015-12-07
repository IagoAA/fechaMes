citApp.directive("autoComplete", ["$translate", "$timeout", function($translate, $timeout) {

	return {
		require: ['^ngModel'],
		scope : {
			label : "@ngLabel",
			obrigatorio : "@ngObrigatorio",
			disabled : "=ngDisabled",
			minLength : "=ngMinLength",
			model :	"=ngModel",
			form : "=form",
			item : "@ngItem",
			find: '&ngFind',
			setResult: '&ngSetResult',
			acaoBorracha : '&ngAcaoBorracha',
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@',
			buttonNewAction: '&',
			buttonNewShow: '@',
			buttonNewTooltip: '@'

		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/auto-complete.html',
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {

			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}

			$scope.chave = $scope.item.replace("item['","").replace("']", "");
			$scope.modelOld = angular.copy($scope.model);

			$scope.defineObrigatoriedade = function(){
				if($scope.model && $scope.model.id){
					$scope.isObrigatorio = (($scope.form[$scope.id].$error.required || !$scope.model.id) && (!$scope.form[$scope.id].$pristine || $scope.form.$submitted) && $scope.obrigatorio);
				}
			};

			// Não renderiza <label> caso nao foi definido 'label'
			$scope.labelRender = true;
			if($scope.label === undefined) {
				$scope.labelRender = false;
			}

			// Define um 'labelAlertTooltip' caso não tenha definido um
			$scope.labelAlertTooltipCopy = $scope.labelAlertTooltip;
			if($scope.labelAlertTooltip === undefined && ($scope.obrigatorio === true || $scope.obrigatorio === "true")) {
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
		link : function($scope, $element, $attrs, modelCtrl){

			$scope.findResult = function (viewValue) {

				if( ($scope.model && !$scope.modelOld && $scope.modelOld !== null && $scope.modelOld !== undefined) || ($scope.model && $scope.model.id && !$scope.modelOld)){
					$scope.modelOld = angular.copy($scope.model);
				}

				if($scope.model && $scope.model[$scope.chave] == viewValue){
					$timeout(function(){
						$scope.model = angular.copy($scope.modelOld);
						$scope.setResult({item : $scope.model });
						return [];
					});
				}else if($scope.model && $scope.model.id){
					$timeout(function(){
						$scope.model = angular.copy($scope.modelOld);
						$scope.setResult({item : $scope.model });
						return [];
					});
				}else{
					return $scope.find({value : viewValue});
				}
			};

			$scope.setResultItem = function (itemSet) {

				$scope.setResult({item : itemSet});
			};

			$scope.limparCampo = function(){
				$scope.model = null;
			};

			$scope.limparCampoSemModel = function(){
				if(!$scope.model || !$scope.model.id){
					$scope.limparCampo();
				}

			};

			$scope.removeModel = function(event){
				if(event.keyCode === 8 || event.keyCode === 46){

					if($scope.model && $scope.model.id) {

						$scope.model = null;
					}
				}

	            if (event.keyCode === 13 && $attrs.ngAcaoEnter) {
					if($scope.model && $scope.model.id){
						$timeout(function(){
		                    $scope.$eval($attrs.ngAcaoEnter);
							$scope.limparCampo();
						});
					}
				}
			};

		}
	};
}]);