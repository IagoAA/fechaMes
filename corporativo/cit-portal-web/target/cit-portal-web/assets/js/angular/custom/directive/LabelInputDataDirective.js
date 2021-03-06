citApp.directive("labelInputData", ["$translate", "$filter", function($translate, $filter) {

	return {
		require: ['^ngModel'],
		scope : {
			id : "@ngId",
			label : "@ngLabel",
			minDate :"=ngMinDate",
			maxDate :"=ngMaxDate",
			obrigatorio : "=ngObrigatorio",
			disabled : "=ngDisabled",
			model :	"=ngModel",
			eventoChange : "&ngEventoChange",
			format : "@ngFormat",
			form : "=form",
			labelAlertTooltip: '@',
			labelInfoTooltip: '@',
			labelQuestionTooltip: '@',
			mode : "@ngMode"
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/labelInputDate.html',
		controller : ['$scope', '$element', '$attrs', function($scope, $element, $attrs){
			if($scope.id === undefined){
				$scope.id = $attrs['ngModel'];
			}

			if (!$scope.mode) {
				$scope.mode = "day";
			}

			$scope.$datePickerFormat = $scope.format ? $scope.format : 'dd/MM/yyyy';

			$scope.$mask = $scope.$datePickerFormat.replace("dd","99").replace("MM","99").replace("yyyy","9999");

			$scope.$datePickerOpened = [];

			$scope.$datePickerOptions = {
				'datepicker-mode' : "'" + $scope.mode + "'",
				'min-mode' : $scope.mode,
				formatYear: 'yyyy',
				startingDay: 1
			};

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
		link : function($scope, $element, attibutes, controller){
			$scope.dataInvalida = false;
			var formataDataInput = function (data) {

				if(data && data !== ""){
					
					var fn = null;
					
					// data dd/mm/yyyy
					if(data.length > 7){
						fn = validaData(data);
					} else {
						// data mm/yyyy
						fn = validaDataMesAno(data);
					}
					
					if(fn) {
						
						$scope.dataInvalida = false;

						if($scope.$datePickerFormat === 'dd/MM/yyyy') {
							if(Object.prototype.toString.call(data) !== "[object Date]") {
								data = converterStringEmDate(data);
							}

							$scope.model = $filter('date')(data, $scope.$datePickerFormat);

							if($scope.form) {
								$scope.form[$scope.id].$modelValue = $scope.model;
								$scope.form[$scope.id].$setViewValue($scope.model);
								$scope.form[$scope.id].$render();
							}
						} else if($scope.$datePickerFormat === 'MM/yyyy' && validaDataMesAno(data)) {
							$scope.model = $filter('date')(data, $scope.$datePickerFormat);

							if($scope.form) {
								$scope.form[$scope.id].$modelValue = $scope.model;
								$scope.form[$scope.id].$setViewValue($scope.model);
								$scope.form[$scope.id].$render();
							}
						} else if($scope.form) {
							$scope.form[$scope.id].$modelValue = '';
							$scope.form[$scope.id].$setViewValue('');
							$scope.form[$scope.id].$render();
						}

					} else {
						//Envia alerta de data inválida
						$scope.eventoChange();
						$scope.dataInvalida = true;
						angular.element('#citapp-controller').scope().showAlert('warning', $translate.instant('VALIDACAO.DATA_INVALIDA'));
					}
				}

			};

			var removeLetras = function (value) {
				return value.replace(/[^0-9\/\-]/ig, '');
			};

			$scope.$limparModel = function() {
				if(!$scope.disabled){
					$scope.model = '';
					$scope.eventoChange();
					$scope.dataInvalida = false;
				}
			};

			$scope.formataDataAoDesfocar = function ($e) {
				formataDataInput($e.currentTarget.value);
			};

			$scope.formataDataAoSelecionar = function (model) {
				if(model) {
					formataDataInput($scope.$$childHead.date);
				}
			};

			$scope.formataDataAoDigitar = function ($e) {
				var value = $e.currentTarget.value;
				var key = ($e.keyCode ? $e.keyCode : $e.which);

				// VERIFICA SE E NUMERICO
				if ((key >= 96 && key <= 105) || (key >= 48 && key <= 57)) {
					$e.currentTarget.value = removeLetras(value);

					if($e.currentTarget.value.length > $scope.$datePickerFormat.length) {
						$e.currentTarget.value = value.substring(0, value.length - 1);
					} else {
						if($scope.$datePickerFormat === 'MM/yyyy'){
							if(value.length === 2) {
								value = value.replace(/([0-9]{2})/, '$1/');
							}
						} else {
							if(value.length === 2) {
								value = value.replace(/([0-9]{2})/, '$1/');
							}

							if(value.length === 5) {
								value = value.replace(/([0-9\/\-]{5})/, '$1/');
							}
						}

						$e.currentTarget.value = value;
					}
				} else if(key === 111 && $e.type === 'keydown') {
					$e.currentTarget.value = value.substring(0, value.length - 1);
				} else {
					$e.currentTarget.value = removeLetras(value);
				}
			};

			$scope.$datePickerOpen = function ($e) {
				if(!$scope.disabled){
					$e.preventDefault();
					$e.stopPropagation();

					$scope.$opened = true;
				}
			};
		}
	};
}]);