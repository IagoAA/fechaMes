citApp.directive("componenteTelefone", ["DominioRepository", "$translate", "$modal", "TelefoneRepository", function(DominioRepository, $translate, $modal, TelefoneRepository) {
	return {
		scope : {
			telefones : "=ngTelefones",
			form : "=form",
			showCampos : "="
		},
		replace : true,
		restrict : "E",
		templateUrl : '/cit-tabelas-corp-web/assets/js/angular/custom/directive/html/telefone.html',
		link : function($scope, $element, attributes) {

			$scope.MASK_TELEFONE = "(99) 9999?9-9999";

			$scope.obrigatorios = {};
			$scope.obrigatorios.numero = false;
			$scope.obrigatorios.dominioTelefone = false;
			$scope.obrigatorios.podeAdd = true;

			$scope.telefone = {};
			$scope.tiposTelefone = [];

			DominioRepository.findAllDominio('tipoTelefone').then(function(result) {
				$scope.tiposTelefone = result;
			});

			$scope.adicionarTelefone = function (){

				$scope.obrigatorios.podeAdd = true;

				if(!$scope.telefone.numero){
					$scope.obrigatorios.podeAdd = false;
					$scope.obrigatorios.numero = true;
				}else{
					$scope.obrigatorios.numero = false;
				}

				if(!$scope.telefone.dominioTelefone){
					$scope.obrigatorios.podeAdd = false;
					$scope.obrigatorios.dominioTelefone = true;
				}else{
					$scope.obrigatorios.dominioTelefone = false;
				}

				if($scope.obrigatorios.podeAdd){
					$scope.telefones.push($scope.telefone);
					$scope.telefone = {};
				}
			};

			$scope.openModal = function (modalId, size) {
				if(modalId === undefined) {
					return;
				}

				$scope.$modalInstance = $modal.open({
					templateUrl: modalId,
					size: size,
					windowClass: 'modal-buttons-top',
					backdrop: false,
					scope: this
				});
			};

			$scope.checkTelefone = function (telefone) {

				$scope.removeCheckedTelefone();

				telefone.$checked = true;
			};

			$scope.removeCheckedTelefone = function () {
				var telefones =   $scope.telefones;

				telefones.forEach(function (telefone) {
					telefone.$checked = false;
				});
			};

			$scope.removerTelefone = function(){
				angular.forEach($scope.telefones, function(telefone, index) {

					if(telefone.$checked){

			    	 $scope.telefones.splice(index , 1);

			    	 if(telefone.id){

							TelefoneRepository.remove(telefone).then(function(result) {

							});
						}
					}

				});
				 $scope.$modalInstance.dismiss('cancel');
			};

		}
	};
}]);
