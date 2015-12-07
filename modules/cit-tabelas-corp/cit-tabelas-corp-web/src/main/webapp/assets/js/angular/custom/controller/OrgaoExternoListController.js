'use strict';

citApp.controller('OrgaoExternoListController', ['$scope', 'OrigaoExternoRepository', '$translate', function OrigaoExternoListController($scope, OrigaoExternoRepository, $translate) {
	$scope.$showAdvancedFilters = false;

	$scope.totalPages = 10;
	$scope.totalItens = 0;
	$scope.limit = 10;

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['codigo', 'nome', 'sigla', 'identificacao','id'],
		filters : [{type: 'string', field: 'nome'}]
	};

	// Will be called when filtering the grid, will reset the page number to one
	$scope.filterResult = function() {
		$scope.filterCriteria.start = 1;
		$scope.fetchResult().then(function() {
			// The request fires correctly but sometimes the ui doesn't update,
			// that's a fix
			$scope.filterCriteria.start = 1;
		});
	};

	// call back function that we passed to our custom directive sortBy, will be
	// called when clicking on any field to sort
	$scope.onSort = function(sortedBy, sortDir) {
		$scope.filterCriteria.dir = sortDir;
		$scope.filterCriteria.sort = sortedBy;
		$scope.filterCriteria.start = 1;
		$scope.fetchResult().then(function() {
			// The request fires correctly but sometimes the ui doesn't update,
			// that's a fix
			$scope.filterCriteria.start = 1;
		});
	};

	// RECUPERA LISTA DE ORGAOS EXTERNOS E SETA RESULTADO NA GRID
	$scope.fetchResult = function(page) {
		$scope.setLoadingPesquisa(true);

		return OrigaoExternoRepository.getListPage($scope.filterCriteria).then(function(result) {
			$scope.OrgaosExternos = result.originalElement.objects;
			$scope.totalPages = result.originalElement.totalPages;
			$scope.totalItens =  result.originalElement.totalItens;

			$scope.setLoading(false);
		}, function() {
			$scope.OrgaosExternos = [];
			$scope.totalPages = 0;
			$scope.totalItens = 0;
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO ORGAOS EXTERNOS
	$scope.remove = function(OrgaoExterno){
		$scope.OrgaoExterno = OrgaoExterno;

		bootbox.dialog({
			message: "<span class='bigger-110'><p>"+$translate.instant('LABEL.CONFIRMA_EXCLUSAO')+"</p></span>",
			buttons:
			{
				"button" :
				{
					"label" : "Cancela",
					"className" : "btn-sm"
				},
				"danger" :
				{
					"label" : "Confirma",
					"className" : "btn-sm btn-danger",
					"callback": function() {
						OrgaoExternoRepository.remove($scope.OrgaoExterno).then(function() {
							$scope.fetchResult();
						});
					}
				}
			}
		});

	};

	$scope.abrirOrgaoExterno = function(id, visualizar){
		angular.element('#editOrgaoExterno').scope().getOrgaoExterno(id, visualizar);
	};
	$scope.limparOrgaoExterno = function (){
		$scope.OrgaoExterno = "";
	};

	// BUSCA LISTA DE ORGAOS EXTERNOS
	$scope.fetchResult();
}]);


