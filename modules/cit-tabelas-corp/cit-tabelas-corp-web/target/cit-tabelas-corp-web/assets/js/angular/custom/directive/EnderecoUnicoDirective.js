citApp.directive("componenteEnderecoUnico",
				["PaisRepository",
				 "RegiaoRepository",
				 "EstadoRepository",
				 "CidadeRepository",
				 "BairroRepository",
				 "EnderecoRepository",
				 "DominioRepository",
				 "$translate",
				 "$timeout",
				 function(PaisRepository,
						 RegiaoRepository,
						 EstadoRepository,
						 CidadeRepository,
						 BairroRepository,
						 EnderecoRepository,
						 DominioRepository,
						 $translate,
						 $timeout) {
	return {
		scope : {
			novo : "=",
			edit : "=ngEdit",
			endereco : "=ngEndereco",
			form : "=form"
		},
		replace : true,
		restrict : "EA",
		templateUrl : '/cit-tabelas-corp-web/assets/js/angular/custom/directive/html/enderecoUnico.html',
		link : function($scope, $element, attributes) {

			$scope.MASK_CEP = "99999-999";

			DominioRepository.findAllDominio('tipoEndereco').then(function(result) {
				$scope.dominiosTipoEndereco = result;
			});

			// Método responsável por listar enderecos a partir do nome.
			$scope.findEndereco = function(value) {
				return EnderecoRepository.listarEndereco(value).then(function(result) {
					return result;
				});
			};

			// Método responsável por listar País através do nome digitado
			$scope.findPais = function(value) {
				return PaisRepository.listarPais(value).then(function(result) {
						return result;
				});
			};

			// Método responsável por listar Região através do nome digitado ou
			// País selecionado
			$scope.findRegiao = function(value) {
				var paisNomeVH = {
					objeto : $scope.endereco.$pais,
					nome : value
				};
				return RegiaoRepository.findRegiao(paisNomeVH).then(function(result) {
						return result;
				});
			};

			// Método responsável por listar Estado através do nome ou Região,
			// Pais selecionado
			$scope.findEstado = function(value) {

				var regiao = {};

				angular.copy($scope.endereco.$regiao, regiao);
				angular.copy($scope.endereco.$pais, regiao.pais);

				var regiaoPaisNomeVH = {
					objeto : regiao,
					nome : value
				};
				return EstadoRepository.findEstado(regiaoPaisNomeVH).then(function(result) {
					return result;
				});
			};

			// Método responsável por listar Cidade através do nome ou Estado,
			// Região, Pais selecionado
			$scope.findCidade = function(value) {

				var pais = {};
				var regiao = {};
				var estado = {};

				angular.copy($scope.endereco.$pais, pais);
				angular.copy($scope.endereco.$regiao, regiao);
				angular.copy($scope.endereco.$estado, estado);

				regiao.pais = pais;
				estado.regiao = regiao;

				var estadoRegiaoPaisNomeVH = {
					objeto : estado,
					nome : value
				};
				return CidadeRepository.findCidade(estadoRegiaoPaisNomeVH).then(function(result) {
						return result;
				});
			};

			// Método responsável por listar Bairro através do nome ou Cidade,
			// Estado, Região, Pais selecionado
			$scope.findBairro = function(value) {

				var pais = {};
				var regiao = {};
				var estado = {};
				var cidade = {};

				angular.copy($scope.endereco.$pais, pais);
				angular.copy($scope.endereco.$regiao, regiao);
				angular.copy($scope.endereco.$estado, estado);
				angular.copy($scope.endereco.$cidade, cidade);

				regiao.pais = pais;
				estado.regiao = regiao;
				cidade.estado = estado;

				var cidadeEstadoRegiaoPaisNomeVH = {
					objeto : cidade,
					nome : value
				};
				return BairroRepository.findBairro(cidadeEstadoRegiaoPaisNomeVH).then(function(result) {
					return result;
				});
			};

			// Método responsável por setar o endereço através do endereço selecionado.
			$scope.setEndereco = function(endereco) {
				$scope.endereco = endereco;
				$scope.$endereco = {
						nome : ''
				};
				$scope.setBairroCidadeEstadoRegiaoPais(endereco.bairro);
			};

			// Método responsável por setar o Pais através da Regiao
			// selecionada.
			$scope.setRegiaoPais = function(regiao) {
				$scope.endereco.$pais = angular.copy(regiao.pais);
			};

			// Método responsável por setar o Pais, Regiao através do estado
			// selecionado.
			$scope.setEstadoRegiaoPais = function(estado) {
				$scope.setRegiaoPais(estado.regiao);
				$scope.endereco.$regiao = angular.copy(estado.regiao);
			};

			// Método responsável por setar o Estado, Regiao, Pais através da
			// cidade selecionada.
			$scope.setCidadeEstadoRegiaoPais = function(cidade) {
				$scope.setEstadoRegiaoPais(cidade.estado);
				$scope.endereco.cidade = angular.copy(cidade);
				$scope.endereco.$estado = angular.copy(cidade.estado);
			};

			// Método responsável por setar a Cidade, Estado, Regiao, Pais através do
			// bairro selecionado.
			$scope.setBairroCidadeEstadoRegiaoPais = function(bairro) {
				$scope.setCidadeEstadoRegiaoPais(bairro.cidade);
				$scope.endereco.$cidade = angular.copy(bairro.cidade);
			};

			// Método responsável por limpar endereco do autocomplete
			$scope.limparEnderecoFind = function () {
				$scope.enderecoFind = {};
				$scope.endereco = {};
				$timeout(function(){
					$scope.form.$setPristine();
				});
			};

			$scope.$watch('endereco.$pais', function(newVal, oldVal){
				if(!newVal){
					$scope.endereco.$regiao = null;

					if($scope.form['endereco.$pais'])
						$scope.form['endereco.$pais'].$pristine = false;

					if($scope.form['endereco.$regiao'])
						$scope.form['endereco.$regiao'].$pristine = false;
				}
			}, true);

			$scope.$watch('endereco.$regiao', function(newVal, oldVal){
				if(!newVal){
					$scope.endereco.$estado = null;

					if($scope.form['endereco.$regiao'])
						$scope.form['endereco.$regiao'].$pristine = false;

					if($scope.form['endereco.$estado'])
						$scope.form['endereco.$estado'].$pristine = false;
				}
			}, true);

			$scope.$watch('endereco.$estado', function(newVal, oldVal){
				if(!newVal){

					if($scope.form['endereco.$estado'])
						$scope.form['endereco.$estado'].$pristine = false;

					if($scope.form['endereco.$cidade'])
						$scope.form['endereco.$cidade'].$pristine = false;

					$scope.endereco.$cidade = null;
				}
			}, true);

			$scope.$watch('endereco.$cidade', function(newVal, oldVal){
				if(!newVal){

					if($scope.form['endereco.$cidade'])
						$scope.form['endereco.$cidade'].$pristine = false;

					if($scope.form['endereco.bairro'])
						$scope.form['endereco.bairro'].$pristine = false;

					$scope.endereco.bairro = null;
				}
			}, true);


			//Método responsável por redirecionar o usuario para o cadastro de endereço
			$scope.novoEndereco = function() {

				angular.element("#citapp-controller").scope().addNewWorkspace($translate.instant('LABEL.ENDERECO'), '/cit-tabelas-corp-web/html/endereco/endereco.html', true, 'mod-green');

				$timeout(function() {
					angular.element('#searchEndereco').scope().$showPageEditWorkspace(angular.element('#searchEndereco').scope().workspace);
					angular.element('#searchEndereco').scope().resetForm();

			    }, 200);
			};
		}
	};
}]);
