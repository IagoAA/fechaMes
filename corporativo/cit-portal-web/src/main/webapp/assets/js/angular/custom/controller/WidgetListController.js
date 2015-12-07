'use strict';

citApp.controller('WidgetListController', ['$scope', '$translate', 'WidgetRepository',
		function WidgetListController($scope, $translate, WidgetRepository) {
	$scope.resetForm = function () {
		angular.element('#widget-edit-controller').scope().resetForm();
	};

	$scope.visualizarWidget = function () {
		if(!$scope.widgetChecked.id) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));

			return;
		}

		angular.element('#widget-edit-controller').scope().loadWidgetEdit($scope.widgetChecked.id, false);
	};

	$scope.editarWidget = function () {
		if(!$scope.widgetChecked.id) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));

			return;
		}

		angular.element('#widget-edit-controller').scope().loadWidgetEdit($scope.widgetChecked.id, true);
	};

	$scope.remove = function () {
		if(!$scope.widgetChecked.id) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));

			return ;
		}

		angular.element('#widget-edit-controller').scope().remove($scope.widgetChecked);
	};

	// Iniciar propriedades padrão da consulta que sera enviada para o servidor.
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields : [ 'id', 'nome', 'descricao' ],
		filters : [ {
			type : 'string',
			field : 'nome'
		}, {
			type : 'string',
			field : 'descricao'
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

		$scope.widgetChecked = {};

		return WidgetRepository.getListPage($scope.filterCriteria).then(function(result) {
			$scope.widgets = result.originalElement.objects;
			$scope.totalPages = result.originalElement.totalPages;
			$scope.totalItens = result.originalElement.totalItens;

			$scope.setLoading(false);
		}, function() {
			$scope.widgets = [];
			$scope.totalPages = 0;
			$scope.totalItens = 0;

			$scope.setLoading(false);
		});
	};

	$scope.fetchResult();
}]);
