'use strict';

citApp.controller('WidgetItemListController', ['$scope', '$translate', 'WidgetItemRepository', 'DominioRepository',
		function WidgetItemListController($scope, $translate, WidgetItemRepository, DominioRepository) {
	$scope.resetForm = function () {
		angular.element('#widget-item-edit-controller').scope().resetForm();
	};

	$scope.visualizarWidgetItem = function () {
		if(!$scope.widgetItemChecked.id) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));

			return;
		}

		angular.element('#widget-item-edit-controller').scope().loadWidgetItemEdit($scope.widgetItemChecked.id, false);
	};

	$scope.editarWidgetItem = function () {
		if(!$scope.widgetItemChecked.id) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));

			return;
		}

		angular.element('#widget-item-edit-controller').scope().loadWidgetItemEdit($scope.widgetItemChecked.id, true);
	};

	$scope.remove = function () {
		if(!$scope.widgetItemChecked.id) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));

			return ;
		}

		angular.element('#widget-item-edit-controller').scope().remove($scope.widgetItemChecked);
	};

	// Iniciar propriedades padrão da consulta que sera enviada para o servidor.
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields : [ 'id', 'nome', 'descricao', 'widget' ],
		filters : [ {
			type : 'string',
			field : 'nome'
		}, {
			type : 'string',
			field : 'descricao'
		}, {
			type : 'string',
			field : 'widget.nome'
		} ]
	};

	$scope.filterResult = function(generic) {
        if (generic === undefined) {
               $scope.filterCriteria.keywordValue = null;
        }

        $scope.filterCriteria.start = 1;
        $scope.fetchResult().then(function() {
        	$scope.filterCriteria.start = 1;
        });
	};

	$scope.fetchResult = function() {
		$scope.setLoadingPesquisa(true);

		$scope.widgetItemChecked = {};

		return WidgetItemRepository.getListPage($scope.filterCriteria).then(function(result) {
			$scope.widgetItens = result.originalElement.objects;
			$scope.totalPages = result.originalElement.totalPages;
			$scope.totalItens = result.originalElement.totalItens;

			$scope.setLoading(false);
		}, function() {
			$scope.widgetItens = [];
			$scope.totalPages = 0;
			$scope.totalItens = 0;

			$scope.setLoading(false);
		});
	};

	$scope.fetchResult();
}]);
