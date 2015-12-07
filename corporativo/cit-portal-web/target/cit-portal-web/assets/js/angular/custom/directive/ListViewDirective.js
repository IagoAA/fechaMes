citApp.directive("listView", ["$translate", "$injector", "$timeout", "$filter", function($translate, $injector, $timeout, $filter) {

	return {
		scope : {
			lista :	"=ngLista",
			repository : "@ngRepository",
			headers : "=ngHeaders",
			filterCriteria : "=ngFilterCriteria",
			itemSelecionado : "=ngItemSelecionado",
			customRemove : "&ngCustomRemove",
			useCustomRemove : "@ngUseCustomRemove"
		},
		replace : true,
		restrict : 'E',
		templateUrl : 'assets/js/angular/custom/directive/html/listView.html',
		controller: ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {

			$scope.totalPages = 10;
			$scope.totalItens = 0;
			$scope.limit = 10;
			$scope.repositorio = $injector.get($scope.repository);
			$scope.appController = angular.element("#citapp-controller").scope();
			$scope.$showAdvancedFilters = false;
			$scope.labelPalavraChave = $translate.instant('LABEL.DIGITE_UMA_PALAVRA_CHAVE');
			$scope.divisaoColunas = 95 / $scope.headers.length;

		}],
		link : function($scope, $element, $attibutes){

			// Will be called when filtering the grid, will reset the page number to one
			$scope.filterResult = function(generic) {

				//resolver o problema do timeout, solução para problema encontrado no backspace rápido do filter.
				if($scope.filterCriteria.keywordValue !== '' || $scope.filterCriteria.keywordValue === ''){
					$scope.runFilterResult(generic);
				}
		  };

		  $scope.runFilterResult = function(generic){
				$timeout(function(){
					if (generic == undefined) {
						$scope.filterCriteria.keywordValue = null;
					}
					$scope.filterCriteria.start = 1;
					$scope.fetchResult().then(function() {
						// The request fires correctly but sometimes the ui doesn't update,
						// that's a fix
						$scope.filterCriteria.start = 1;
					});
				},200);
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

			// RECUPERA LISTA E SETA RESULTADO NA GRID
			$scope.fetchResult = function(page) {
				$scope.appController.setLoadingPesquisa(true);
				$scope.itemSelecionado = undefined;
				return $scope.repositorio.getListPage($scope.filterCriteria).then(function(result) {
					$scope.lista = result.originalElement.objects;
					$scope.totalPages = result.originalElement.totalPages;
					$scope.totalItens =  result.originalElement.totalItens;

					if(!$scope.filtersCopy){
						$scope.filtersCopy = angular.copy($scope.filterCriteria.filters);
					}

					$scope.appController.setLoading(false);
				}, function() {
					$scope.lista = [];
					$scope.totalPages = 0;
					$scope.totalItens = 0;
				});
			};

			// prepara a exclusão do model abrindo o modal de confirmação
			$scope.remove = function(){
				if($scope.useCustomRemove === true || $scope.useCustomRemove === 'true'){
					$scope.customRemove();
				}else{
					var model = $scope.itemSelecionado;
					if(model && model.id){
						$scope.appController.$openModalConfirm({message: $translate.instant('MSG.DESEJA_EXCLUIR_ITEM'), callback: $scope.excluir, item: model});
					}else{
						$scope.appController.showAlert('warning', $translate.instant('MSG.SELECIONE_ITEM_EXCLUIR'));
					}
				}
			};

			// realiza a exclusão
			$scope.excluir = function(){
				var itemSelecionado = this.item;

				$scope.repositorio.remove(itemSelecionado).then(function() {
					$scope.appController.$modalConfirmInstance.dismiss('cancel');
					$scope.appController.showAlert('success', $translate.instant('MSG.REGISTRO_EXCLUIDO'));
					$scope.fetchResult();
				});

			};


			// Limpa os filtros avançados
			$scope.limparFilters = function() {
				$scope.filterCriteria.keywordValue = null;
				if($scope.filtersCopy){
					$scope.filterCriteria.filters= angular.copy($scope.filtersCopy);
				}
				$scope.fetchResult();
				$scope.$showAdvancedFilters = false;
			};

			$scope.fetchResult();
			$scope.$parent.fetchResult = $scope.fetchResult;
			$scope.$parent.remove = $scope.remove;
			$scope.$parent.filterResult = $scope.filterResult;

			$scope.aplicarFiltro = function(value, filter) {
				  return $filter(filter)(value);
			};

			$scope.$on('filtroDirective-listViewDirective', function(event, active) {
				$scope.$showAdvancedFilters = active;
			});
		}
	};
}]);