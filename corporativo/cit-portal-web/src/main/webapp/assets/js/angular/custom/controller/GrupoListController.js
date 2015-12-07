'use strict';

citApp.controller('GrupoListController', ['$scope', 'GrupoRepository', '$translate', '$modal', '$timeout', 'UsuarioRepository',
		function GrupoListController($scope, GrupoRepository, $translate, $modal, $timeout, UsuarioRepository) {

	$scope.$showAdvancedFilters = false;

	$scope.headers = [ {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.SIGLA'),
		value : 'sigla'
	}, {
		title : $translate.instant('LABEL.EMAIL'),
		value : 'email'
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['id', 'nome', 'sigla', 'email'],
		filters : [{
			type: 'string',
			field: 'nome'
			},{
			type: 'string',
			field: 'sigla'
			},{
			type: 'string',
			field: 'email'
			}]
	};

	$scope.confirmarRemocao = function(){

		if($scope.grupoChecked){

			UsuarioRepository.quantidadeUsuarioPorGrupo($scope.grupoChecked.id).then(function(result){

				if(result > 0){

					var msg = $translate.instant('MSG.GRUPO_VINCULADO_USUARIO').concat(result).concat(" ").concat($translate.instant('LABEL.USUARIO')).concat(" (s)");

					$scope.$openModalConfirm({message: msg, callback: $scope.remover});
				}
			});

		}else{

			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_ITEM_EXCLUIR'));
		};

	};

	$scope.remover = function(){

		$scope.setLoadingRemove(true);

		 GrupoRepository.remove($scope.grupoChecked).then(function(result) {

			 $scope.setLoadingRemove(false);
			 $scope.showAlert("success",  $translate.instant('MSG.MG001'));
			 $scope.fetchResult();
		 });

		 $scope.$modalConfirmInstance.dismiss('cancel');
	};

	//Método responsável obter detalhar o grupo
	$scope.abrirGrupo = function(visualizar){

		var grupo = $scope.grupoChecked;

		if(!grupo) {
			$scope.showAlert('warning', visualizar ?  $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO') : $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#grupoEdit').scope().getGrupo(grupo.id, visualizar);

		$scope.$showPageEditWorkspace($scope.workspace);
	};

}]);