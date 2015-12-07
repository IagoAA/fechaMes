citApp.directive("filtros", ["$modal", "PaginaUsuarioRepository", "UsuarioRepository", "$translate", "FiltroRepository", function($modal, PaginaUsuarioRepository, UsuarioRepository, $translate, FiltroRepository) {
	return {
		scope : {
			filtros : "=ngFilter",
			workspace : "=ngWorkspace"
		},
		replace : true,
		restrict : "EA",
		templateUrl : 'assets/js/angular/custom/directive/html/filtros.html',
		controller : ['$scope', function($scope){

			$scope.$appControllerScope = angular.element('#citapp-controller').scope();

			var paginaUsuario = $scope.$appControllerScope.recuperarPaginaUsuario($scope.workspace);

			if(paginaUsuario && paginaUsuario.searchParams){

				$scope.filters = paginaUsuario.searchParams;

			};

		}],
		link : function($scope, $element, attributes) {

			$scope.setFiltro = function(filter){

				angular.forEach(filter.filters, function(filter, index) {

					if(filter.dominio){

						filter.listaDominio = $scope.$parent.filterCriteria.filters[index].listaDominio;
					}

				});

				var filterCopy = angular.copy(filter);
				$scope.filtros = filterCopy;
				$scope.$parent.filterCriteria = filterCopy;
				$scope.$parent.$parent.filterCriteria = filterCopy;
				$scope.$parent.filterResult(filterCopy.keywordValue);
				$scope.$parent.$broadcast("filtroDirective-listViewDirective", true);

			};

			// Metodo responsavel por remover o filtro selecionado
			$scope.removeFilter = function(filter){

				$scope.$appControllerScope.$openModalConfirm({
					message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'),
					callback: function () {

						$scope.$appControllerScope.setLoadingRemove(true);

						angular.forEach($scope.filters, function (item) {

							if(filter.id === item.id){

								FiltroRepository.remove(filter).then(function(result) {

									if(result){

										var index = $scope.filters.indexOf(item);

										$scope.filters.splice(index, 1);

									}

									$scope.$appControllerScope.setLoading(false);

									$scope.$appControllerScope.showAlert("success",  $translate.instant('MSG.MG001'));

								});

							}

						});

						$scope.$appControllerScope.$modalConfirmInstance.dismiss('cancel');

					}
				});
			};

			$scope.limparFiltro = function(){

				$scope.filtros.nome = '';

			};

			$scope.openModal = function (modalId, size) {

				var that = this;
				if(modalId === undefined) {
					return;
				}

				$scope.$modalInstance = $modal.open({
					templateUrl: modalId,
					size: size,
					windowClass: 'modal-buttons-top ' + that.workspace.classePagina,
					scope: that
				});

			};

			$scope.salvarFiltro = function(filterCriteria, workspace) {
				$scope.$appControllerScope.setLoadingPesquisa(true);

				// VALIDA SE O USUARIO INSERIU UM NOME PARA O FILTRO
				if(!$scope.filtros.nome) {
					$scope.$appControllerScope.showAlert('error', $translate.instant('MSG.NOME_FILTRO_VAZIO'));
					$scope.$appControllerScope.setLoadingPesquisa(false);
					$scope.$modalInstance.dismiss('cancel');
					return false;
				}

				// VALIDA SE O USUARIO PREENCHEU ALGUM FILTRO
				if($scope.filtros.filters && $scope.filtros.filters.length > 0) {

					var isFiltroPreenchido = $scope.filtros.filters.some(function(item){
						return item.value || item.valueMin || item.valueMax;

					});

					if(!isFiltroPreenchido && !$scope.filtros.keywordValue) {
						$scope.$appControllerScope.showAlert('error', $translate.instant('MSG.CAMPOS_FILTROS_VAZIOS'));
						$scope.$appControllerScope.setLoadingPesquisa(false);
						$scope.$modalInstance.dismiss('cancel');
						return false;
					}
				}

				delete filterCriteria.id;

				angular.forEach(filterCriteria.filters, function(filter, key) {
					delete filter.id;
					if(filter.listaDominio){

						filter.dominio = true;
					};
				});

				var filterCriteriaPaginaVH = {
		    			pagina : getMontarPagina(workspace),
		    			searchParams : filterCriteria
		    	};

		    	PaginaUsuarioRepository.salvarFilter(filterCriteriaPaginaVH).then(function(result){
		    		$scope.$modalInstance.dismiss('cancel');

		    		if(!$scope.filters){
		    			$scope.filters = [];
		    		}
		    		$scope.filters.push(result.originalElement);
		    		$scope.$appControllerScope.atualizarUsuarioLogado();
		    		$scope.$appControllerScope.setLoadingPesquisa(false);
		    		$scope.$appControllerScope.showAlertSucesso("success", $translate.instant('MSG.SUCESSO_FILTRO_SALVO'));
		    	});

		    	$scope.$modalInstance.dismiss('cancel');
			};

			$scope.atualizarFiltros = function() {
				$scope.$appControllerScope.atualizarUsuarioLogado();
			};

		}
	};
}]);
