citApp.directive("componenteObservacao", ["$filter", "ObservacaoRepository", "$translate", function($filter, ObservacaoRepository, $translate) {
	return {
		scope : {
			observacoes : "=ngObservacoes",
			hide : "=ngHide",
			form : "=form",
			disabled : "=ngDisabled",
			edit : "=ngEdit"
		},
		replace : true,
		restrict : "E",
		templateUrl : '/cit-tabelas-corp-web/assets/js/angular/custom/directive/html/observacao.html',
		link : function($scope, $element, attributes) {

			$scope.observacao = {
					autor : {}
			};

			$scope.usuarioLogado = angular.element('#citapp-controller').scope().usuarioLogado;

			$scope.autor = $scope.usuarioLogado;


			$scope.dataAtual = $filter('date')(new Date(), "dd/MM/yyyy");


			$scope.adicionarObservacao = function (){

				if($scope.observacoes === undefined){
					$scope.observacoes = [];
				}

				if($scope.observacao.descricao === undefined || $scope.observacao.descricao === ""){
					angular.element('#citapp-controller').scope().showAlert('error', $translate.instant('VALIDACAO.PREENCHA_CAMPO_OBSERVACAO'));
					return ;
				}

				$scope.observacao.dataCriacao = new Date();
				$scope.observacao.autor.id = $scope.usuarioLogado.id;
				$scope.observacao.autor.username = $scope.usuarioLogado.username;
				$scope.observacoes.push($scope.observacao);
				$scope.observacao = {
						autor : {}
				};
			};

			$scope.remove = function(value, index) {
				angular.element('#citapp-controller').scope().$openModalConfirm({
					message: $translate.instant('MSG.CONFIRMA_EXCLUSAO'),
					callback: function() {
						if(value.id !== undefined) {
							ObservacaoRepository.remove(value);
						}

						$scope.observacoes.splice(index, 1);

						angular.element('#citapp-controller').scope().showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));

						angular.element('#citapp-controller').scope().$modalConfirmInstance.dismiss('cancel');
					}
				});
			};
		}
	};
}]);
