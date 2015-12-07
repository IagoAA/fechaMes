'use strict';

citApp.controller('UsuarioListController', ['$scope', 'UsuarioRepository', '$translate',
		function UsuarioListController($scope, UsuarioRepository, $translate) {
	$scope.$showAdvancedFilters = false;

	// Chama controlleredit para limpar o formulario de cadastro
	$scope.resetForm = function() {
		angular.element("#editUsuario").scope().resetForm();
	};

	$scope.headers = [ {
		title : $translate.instant('LABEL.NOME'),
		value : 'username'
	}, {
		title : $translate.instant('LABEL.EMAIL'),
		value : 'email'
	}, {
		title : $translate.instant('LABEL.DATA_BLOQUEIO'),
		value : 'dataBloqueio',
		filter : 'dateBR'
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['id', 'username', 'email', 'dataBloqueio'],
		filters : [{type: 'string', field: 'username'},
		           {type: 'string', field: 'email'},
		           {type: 'date', field: 'dataBloqueio'}]
	};

	$scope.abrirUsuario = function(id, edit){
		angular.element('#editUsuario').scope().getUsuario(id, edit);
	};

	// ABRI USUARIO SELECIONADO
	$scope.abrirUsuario = function(edit){
		var usuario = $scope.usuarioChecked;

		if(!edit && !usuario) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		if(edit && !usuario) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#editUsuario').scope().getUsuario(usuario, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};

}]);


