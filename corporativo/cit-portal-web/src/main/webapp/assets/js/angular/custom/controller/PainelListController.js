citApp.controller('PainelListController', ['$scope', 'PainelRepository', '$translate', '$timeout',
		function PainelListController($scope, PainelRepository, $translate, $timeout) {

$scope.$showAdvancedFilters = false;

	$scope.totalPages = 10;
	$scope.totalItens = 0;
	$scope.limit = 10;

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'nome',
		limit : 10,
		fields: ['id', 'nome', 'usuario.username'],
		filters : [{
			type: 'string',
			field: 'nome'
			},{
			type: 'string',
			field: 'usuario.username'
			},{
			type: 'numeric', field: 'organizacao.id',
			value : $scope.usuarioLogado.organizacao.id
			},]
	};

	// Will be called when filtering the grid, will reset the page number to one
	$scope.filterResult = function(generic) {

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
		});
	};

	// RECUPERA LISTA DE PAINEL E SETA RESULTADO NA GRID
	$scope.fetchResult = function() {
		$scope.setLoadingPesquisa(true);
		return PainelRepository.getListPage($scope.filterCriteria).then(function(result) {
			$scope.paineis = result.originalElement.objects;
			$scope.totalPages = result.originalElement.totalPages;
			$scope.totalItens =  result.originalElement.totalItens;

			$scope.setLoading(false);
		}, function() {
			$scope.paineis = [];
			$scope.totalPages = 0;
			$scope.totalItens = 0;
		});
	};

	//Método responsável por selecionar o painel
	$scope.checkPainel = function (painel) {

		$scope.removeCheckedPainel();

		painel.$checked = true;
	};

	//Método responsável por remover a seleção dos paineis
	$scope.removeCheckedPainel = function () {

		$scope.paineis.forEach(function (painel) {

			painel.$checked = false;
		});
	};
	//Método responsável por abir o dialog de confirmação da remoção
	$scope.abirDialogConfirmacaoRemocao = function(){

		var painel = $scope.getPainelChecked();

			if(painel === null) {
				$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));

				return ;
			};

			$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.remover});

			$scope.painelRemocao = painel;
		};

	//Método responsável por retornar o painel selecionado
	$scope.getPainelChecked = function () {

			var painelChecked = null;

			$scope.paineis.forEach(function (painel) {

				if(painel.$checked) {

					painelChecked = painel;

					return ;
				}
			});

			return painelChecked;
		};

	//Método responsável por remover painel
	$scope.remover = function(){

			 $scope.setLoadingRemove(true);

			 PainelRepository.remove($scope.painelRemocao).then(function(result) {

				 $scope.setLoadingRemove(false);

				 $scope.showAlert("success",  $translate.instant('MSG.MG001'));

				 $scope.fetchResult();
			 });

			 $scope.$modalConfirmInstance.dismiss('cancel');

		};

	//Método responsável por obter redirecionar para tela de cadastro do painel
	$scope.abrirPainel = function(visualizar){

			var painel = $scope.getPainelChecked();

			if(painel === null) {
				$scope.showAlert('warning', visualizar ?  $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO') : $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));

				return;
			}

			angular.element('#painelEdit').scope().getPainel(painel.id, visualizar);

			$scope.$showPageEditWorkspace($scope.workspace);
		};

	// BUSCA LISTA DE PAINEL
	$scope.fetchResult();
}]);