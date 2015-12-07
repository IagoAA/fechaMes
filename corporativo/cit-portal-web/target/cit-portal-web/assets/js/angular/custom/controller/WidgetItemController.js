'use strict';

citApp.controller('WidgetItemController', ['$scope', '$translate', 'WidgetItemRepository', 'WidgetRepository',
		function WidgetItemController($scope, $translate, WidgetItemRepository, WidgetRepository) {
	// CRIADO PARA MAPEAR OS DOMINIOS PARA ELEMETOS HTML
	$scope.parametrosTypes = {
		"CARACTERE"		: "text",
		"TEXT_AREA"		: "textarea",
		"NUMBER"		: "",
		"DECIMAL"		: "",
		"DATA"			: "",
		"TIPO_DOMINIO"	: "",
		"ARQUIVO"		: "",
		"LOGICO"		: ""
	};

	$scope.resetForm = function () {
		$scope.widgetItem = {};
		$scope.$edit = true;
		$scope.widgetItem.itemParametros = [];
	};

	$scope.fetchResult = function () {
		angular.element('#widget-item-list-controller').scope().fetchResult();
	};

	WidgetRepository.getList().then(function (result) {
		$scope.listaWidget = [];

		result.forEach(function (widget) {
			$scope.listaWidget.push(widget.originalElement);
		});
	});

	$scope.loadWidgetItemEdit = function (id, $edit) {
		$scope.$showPageEditWorkspace($scope.workspace);

		WidgetItemRepository.get(id).then(function(result) {
			$scope.widgetItem = result.originalElement;
		});

		$scope.$edit = $edit;
	};

	$scope.remove = function (widgetItem) {
		$scope.widgetItem = widgetItem;

		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUIR_WIDGET_ITEM'),
			callback: function () {
				WidgetItemRepository.remove($scope.widgetItem).then(function() {
					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_WIDGET_ITEM_EXCLUIDO'));
					angular.element('#widget-item-list-controller').scope().fetchResult();

					$scope.$showPageSearchWorkspace($scope.workspace);

					$scope.resetForm();
				});
			}
		});
	};

	$scope.saveOrUpdate = function () {
		$scope.formWidgetItemEdit.$submitted = true;

		if($scope.formWidgetItemEdit.$invalid){

			$scope.showAlert('error', $translate.instant('MSG.MN001'));

		}else{
			$scope.setLoading(true);

			WidgetItemRepository.save($scope.widgetItem).then(function (result) {
				$scope.showAlert("success", $translate.instant("WIDGET_ITEM_SALVO_COM_SUCESSO"));

				$scope.resetForm();

				$scope.setLoading(false);

				$scope.formWidgetItemEdit.$submitted = false;
			});
		}
	};

	$scope.resetForm();
}]);
