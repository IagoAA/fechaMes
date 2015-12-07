'use strict';

citApp.controller('WidgetController', ['$scope', '$timeout', '$translate', 'DominioRepository', 'WidgetRepository', 'WidgetParametroRepository',
		function WidgetController($scope, $timeout, $translate, DominioRepository, WidgetRepository, WidgetParametroRepository) {
	$scope.resetForm = function () {
		$scope.widget = {};
		$scope.widget.parametros = [];
		$scope.parametro = {};
		$scope.$edit = true;
		$scope.parametroChecked = {};
		$scope.dominiosTipoDefault = [];
		$scope.documentos = [];
	};

	$scope.fetchResult = function () {
		angular.element('#widget-list-controller').scope().fetchResult();
	};

	DominioRepository.findAllChavesDominio().then(function(result) {
		$scope.chavesDominio = result;
	});

	// ATUALIZAR DOMINIOS DEFAULT
	$scope.atualizarDominiosDefault = function () {
		$timeout(function() {
			if ($scope.parametro.chaveDominioDefault) {
				DominioRepository.findAllDominio($scope.parametro.chaveDominioDefault).then(function(result) {
					$scope.dominiosTipoDefault = result;
				});
			}
		})
	};

	DominioRepository.findAllDominio('tipoDado').then(function(result) {
		$scope.listaTipoWidgetParametro = [];

		result.forEach(function (item) {
			if (item.originalElement.codigo != 7 && item.originalElement.codigo != 8) {
				$scope.listaTipoWidgetParametro.push(item.originalElement);
			}
		});
	});

	DominioRepository.findAllDominio('tipoWidget').then(function(result) {
		$scope.tiposWidget = result;
	});

	DominioRepository.findAllDominio('tipoHighChart').then(function(result) {
		$scope.tiposHighChart = result;
	});

	$scope.loadWidgetEdit = function (id, $edit) {
		$scope.$showPageEditWorkspace($scope.workspace);

		WidgetRepository.get(id).then(function(result) {
			$scope.widget = result.originalElement;
		});

		$scope.$edit = $edit;
	};

	$scope.remove = function (widget) {
		$scope.widget = widget;

		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_WIDGET'),
			callback: function () {
				WidgetRepository.remove($scope.widget).then(function() {
					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_WIDGET_EXCLUIDO'));
					angular.element('#widget-list-controller').scope().fetchResult();

					$scope.$showPageSearchWorkspace($scope.workspace);

					$scope.resetForm();
				});
			}
		});
	};

	$scope.$watch('parametro', function() {
	       if ($scope.parametro.tipoWidgetParametroDominio && $scope.parametro.tipoWidgetParametroDominio.codigo === 6 && $scope.parametro.chaveDominioDefault) {
	    	   DominioRepository.findAllDominio($scope.parametro.chaveDominioDefault).then(function(result) {
					$scope.dominiosTipoDefault = result;
				});
	       }
	   });

	$scope.saveOrUpdate = function () {
		$scope.formWidgetEdit.$submitted = true;

		if($scope.formWidgetEdit.$invalid){

			$scope.showAlert('error', $translate.instant('MSG.MN001'));

		}else{
			$scope.setLoading(true);

			WidgetRepository.save($scope.widget).then(function (result) {
				$scope.showAlert("success", $translate.instant("MSG.WIDGET_SALVO_COM_SUCESSO"));

				$scope.resetForm();

				$scope.setLoading(false);

				$scope.formWidgetEdit.$submitted = false;
			});
		}
	};

	$scope.resetForm();

	// ADD AND UPDATE WIDGET PARAMETRO
	$scope.addAndUpdateWidgetParametro = function () {
		if ($scope.parametro.tipoWidgetParametroDominio.codigo === 9) {
			$scope.parametro.nome = $scope.parametro.tipoWidgetParametroDominio.descricao;
			if ($scope.widget.urlServico && ($scope.parametro.$index === undefined || $scope.parametro.$index === null)) {
				$scope.showAlert("error", $translate.instant("MSG.URL_SERVICO_DUPLICADA"));

				return;
			} else {
				$scope.widget.urlServico = $scope.parametro.textoDefault;
			}
		}

		if(!$scope.parametro.nome) {
			$scope.showAlert("error", $translate.instant("MSG.INFORME_NOME__PARAMETRO"));

			return;
		}

		if(!$scope.parametro.tipoWidgetParametroDominio || !$scope.parametro.tipoWidgetParametroDominio.id) {
			$scope.showAlert("error", $translate.instant("MSG.SELECIONE_TIPO_PARAMETRO"));

			return;
		}

		var parametro = angular.copy($scope.parametro);

		if(parametro.$index !== undefined && parametro.$index !== null) {
			$scope.widget.parametros[parametro.$index] = parametro;
		} else {
			$scope.widget.parametros.push(parametro);
		}

		$scope.parametro = {};

		$scope.$modalInstance.dismiss('cancel');
	};

	// LOAD WIDGET PARAMETRO
	$scope.loadWidgetParametro = function () {
		if(!$scope.parametroChecked.nome) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));

			return;
		}

		$scope.parametro = angular.copy($scope.parametroChecked);

		$scope.parametroChecked = {};

		$scope.$openModal('addParametroModal.html');
	};

	// REMOVE WIDGET PARAMETRO
	$scope.removeWidgetParametro = function () {
		if(!$scope.parametroChecked.nome) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));

			return ;
		}

		var parametro = $scope.parametroChecked;

		if(parametro.id) {
			$scope.$openModalConfirm({
				message: $translate.instant('MSG.CONFIRMA_EXCLUIR_PARAMETRO'),
				callback: function () {
					$scope.setLoadingRemove(true);

					WidgetParametroRepository.remove(parametro).then(function(result) {
						$scope.$modalConfirmInstance.dismiss('cancel');
						$scope.showAlert("success", $translate.instant('MSG.SUCESSO_PARAMETRO_EXCLUIDO'));

						$scope.setLoadingRemove(false);

						$scope.widget.parametros.splice(parametro.$index, 1);
					});
				}
			});
		} else {
			$scope.widget.parametros.splice(parametro.$index, 1);
		}
	};

	// FECHAR MODAL ADD WIDGET PARAMETRO
	$scope.fecharModalWidgetParametro = function () {
		$scope.parametro = {};
	};
}]);
