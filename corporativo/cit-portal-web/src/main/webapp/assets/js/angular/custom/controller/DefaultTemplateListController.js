'use strict';

citApp.controller('DefaultTemplateListController', ['$scope', '$modal', function ExampleListController($scope, $modal) {

	$scope.items = [{
		column1: "Valor 1",
		column2: "Valor 2",
		column3: "20/10/2014",
		$show: false
	}, {
		column1: "Valor 1",
		column2: "Valor 2",
		column3: "20/10/2014",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}];

	$scope.radioItens = [{
		descricao: "Valor 1"
	}, {
		descricao: "Valor 2"
	}, {
		descricao: "Valor 3"
	}];

	$scope.selectItens = [{
		descricao: "Valor 1"
	}, {
		descricao: "Valor 2"
	}, {
		descricao: "Valor 3"
	}];

	$scope.showItem = function(item) {
		if(item.$show) {
			item.$show = false;
		} else {
			item.$show = true;
		}
	};
}]);
