citApp.directive("componenteEndereco", ["PaisRepository", "RegiaoRepository", "EstadoRepository", "CidadeRepository", "BairroRepository", "DominioRepository", "$translate", "$modal", '$injector', "EnderecoRepository", function(PaisRepository, RegiaoRepository, EstadoRepository, CidadeRepository, BairroRepository, DominioRepository, $translate, $modal, $injector, EnderecoRepository) {
	return {
		scope : {
			enderecos : "=ngEnderecos",
			showCampos : '=',

		},
		replace : true,
		restrict : "EA",
		templateUrl : '/cit-tabelas-corp-web/assets/js/angular/custom/directive/html/endereco.html',
		link : function($scope, $element, attributes) {

			$scope.MASK_CEP = "99999-999";

			$scope.endereco = {};

			$scope.obrigatorios = {};

			$scope.obrigatorios.cep = false;

			$scope.obrigatorios.logradouro = false;

			$scope.obrigatorios.complemento = false;

			$scope.obrigatorios.bairro = false;

			$scope.tipoEnderecos = [];

			DominioRepository.findAllDominio('tipoEndereco').then(function(result) {
				$scope.tipoEnderecos = result;
			});


		   // Método responsável por listar Pais através do nome digitado
		   $scope.findPais =  function(value){
			   return PaisRepository.listarPais(value).then(function(result) {
				   return result;
			   });
			};

			// Select result
			$scope.setPaisEndereco = function(item) {
				$scope.endereco.pais = item.originalElement;
			};

			// Método responsável por listar Região atáves do nome digitado ou Pais selecionado
			$scope.findRegiao =  function(value){
					var paisNomeVH = {
							objeto : $scope.pais,
							nome : value
					};
				   return RegiaoRepository.findRegiao(paisNomeVH).then(function(result) {
					   return result;
				   });
				};

			// Método responsável por listar Estado através do nome ou Região, Pais selecionado
			$scope.findEstado =  function(value){

					var regiao = {};

					angular.copy($scope.regiao, regiao);

					angular.copy($scope.pais, regiao.pais);

					var regiaoPaisNomeVH = {
							objeto : regiao,
							nome : value
					};
				   return EstadoRepository.findEstado(regiaoPaisNomeVH).then(function(result) {
					   return result;
				   });
				};

			// Método responsável por listar Cidade através do nome ou Estado, Região, Pais selecionado
			$scope.findCidade =  function(value){

				var pais = {};
				var regiao = {};
				var estado = {};

				angular.copy($scope.pais, pais);
				angular.copy($scope.regiao, regiao);
				angular.copy($scope.estado, estado);

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

			// Método responsável por listar Bairro através do nome ou Cidade, Estado, Região, Pais selecionado
			$scope.findBairro =  function(value){

					var pais = {};
					var regiao = {};
					var estado = {};
					var cidade = {};

					angular.copy($scope.pais, pais);
					angular.copy($scope.regiao, regiao);
					angular.copy($scope.estado, estado);
					angular.copy($scope.cidade, cidade);

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

			// Método responsável por setar o Pais através da Regiao selecionada.
			$scope.setPais = function(regiao){

				$scope.pais = regiao.pais;

				$scope.estado = null;

				$scope.cidade = null;

			};

			// Método responsável por setar o Pais, Regiao através do estado selecionado.
			$scope.setRegiaoPais = function(estado){

				$scope.regiao = estado.regiao;

				$scope.pais = estado.regiao.pais;

			};

			// Método responsável por setar o Estado, Regiao , Pais através da cidade selecionada.
			$scope.setEstadoRegiaoPais = function(cidade){

				$scope.estado = cidade.estado;

				$scope.regiao = cidade.estado.regiao;

				$scope.pais = cidade.estado.regiao.pais;
			};

			//Método responsável por setar a Cidade, Estado, Regiao , Pais através do bairro selecionada.
			$scope.setCidadeEstadoRegiaoPais = function(bairro){

				$scope.cidade = bairro.cidade;

				$scope.estado = bairro.cidade.estado;

				$scope.regiao = bairro.cidade.estado.regiao;

				$scope.pais = bairro.cidade.estado.regiao.pais;

			};

			// Método responsável por iniciar a propriedades do endereço
			$scope.iniciarPropriedadesEndereco = function(){

				if(!$scope.endereco.cidade){

					$scope.endereco.cidade = {};
				}

				if(!$scope.endereco.cidade.estado){

					$scope.endereco.cidade.estado = {};

				}
				if(!$scope.endereco.cidade.estado.regiao){

					$scope.endereco.cidade.estado.regiao = {};

				}
				if(!$scope.endereco.cidade.estado.regiao.pais){

					$scope.endereco.cidade.estado.regiao.pais = {};
				}

			};


			// Método responsável por limpar os campos do autoComplete
			$scope.limparFilhosAutoComplete = function(item){

				if(item == 'pais'){

					$scope.regiao = null;
					$scope.estado = null;

				}else if(item = 'regiao'){

					$scope.estado = null;
				}

				$scope.cidade = null;

			};

			$scope.limparCampos = function(){

				$scope.bairro = null;

				$scope.cidade = null;

				$scope.estado = null;

				$scope.regiao = null;

				$scope.pais = null;


			};

			//Metodo responsavel por ver se alguns dos enderecos ja e o principal
			$scope.existeEnderecoPrincipal = function(){

				var result = false;
				angular.forEach($scope.enderecos, function(endereco, index) {

					if (endereco.enderecoPrincipal){
						result = true;
					};

				});

				return result;
			};

			//Metodo responsavel por ver se alguns dos enderecos ja e o principal
			$scope.remarcarEnderecoPrincipal = function(){

				var result = false;
				angular.forEach($scope.enderecos, function(endereco, index) {

					if (endereco.enderecoPrincipal === true && $scope.endereco != endereco){
						endereco.enderecoPrincipal = false;
					};

				});

				return result;
			};

			//Método responsável por adicionar o endereco
			$scope.adicionarEndereco = function (){

				if($scope.enderecos.size <= 1){
					$scope.endereco.enderecoPrincipal = true;

				} else if($scope.endereco.enderecoPrincipal === true) {
					$scope.remarcarEnderecoPrincipal();

				}

				$scope.iniciarPropriedadesEndereco();

				var podeAdd = true;

				if($scope.bairro && $scope.bairro.id){

					$scope.endereco.bairro = $scope.bairro;

					$scope.obrigatorios.bairro = false;

				}else{
					podeAdd = false;
					$scope.obrigatorios.bairro = true;

				}
				if($scope.cidade && $scope.cidade.id){

					$scope.endereco.cidade = $scope.cidade;

					$scope.obrigatorios.cidade = false;
				}else{

					podeAdd = false;

					$scope.obrigatorios.cidade = true;

				}if($scope.estado && $scope.estado.id){

					$scope.endereco.cidade.estado = $scope.estado;

					$scope.obrigatorios.estado = false;

				}else{

					podeAdd = false;

					$scope.obrigatorios.estado = true;
				}

				if($scope.regiao && $scope.regiao.id){

					$scope.endereco.cidade.estado.regiao =  $scope.regiao;


					$scope.obrigatorios.regiao = false;

				}else{

					podeAdd = false;

					$scope.obrigatorios.regiao = true;
				}
				if($scope.pais && $scope.pais.id){

					$scope.endereco.cidade.estado.regiao.pais = $scope.pais;


					$scope.obrigatorios.pais = false;

				}else{

					podeAdd = false;

					$scope.obrigatorios.pais = true;
				}

				if(!$scope.endereco.dominioTipoEndereco){
					podeAdd = false;
					$scope.obrigatorios.dominioTipoEndereco = true;
				}else{
					$scope.obrigatorios.dominioTipoEndereco = false;
				}

				if(!$scope.endereco.cep || ($scope.endereco.cep &&  $scope.endereco.cep == "")){
					podeAdd = false;
					$scope.obrigatorios.cep = true;
				}else{
					$scope.obrigatorios.cep = false;
				}

				if(!$scope.endereco.logradouro || ($scope.endereco.logradouro && $scope.endereco.logradouro == "")){
					podeAdd = false;
					$scope.obrigatorios.logradouro = true;
				}else{
					$scope.obrigatorios.logradouro = false;
				}

				if(!$scope.endereco.complemento || ($scope.endereco.complemento && $scope.endereco.complemento == "")){
					podeAdd = false;
					$scope.obrigatorios.complemento = true;
				}else{
					$scope.obrigatorios.complemento = false;
				}

				if(podeAdd){

					$scope.enderecos.push($scope.endereco);
					$scope.endereco = {};

					$scope.limparCampos();

				}

				$scope.apresentarDialogConfirmacaoRemocaoEndereco = function(){

					angular.element("#citapp-controller").scope().$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.removerEndereco});

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

				$scope.checkEndereco = function(endereco) {

					$scope.removeCheckedEndereco();

					endereco.$checked = true;
				};

				$scope.removeCheckedEndereco = function () {
					var enderecos =   $scope.enderecos;

					enderecos.forEach(function (endereco) {
						endereco.$checked = false;
					});
				};

				$scope.removerEndereco = function (){

					angular.forEach($scope.enderecos, function(endereco, index) {

						if(endereco.$checked){
				    	 $scope.enderecos.splice(index , 1);

				    	 if(endereco.id){

								EnderecoRepository.remove(endereco).then(function(result) {

								});
							}
						}
					});

					$scope.$modalInstance.dismiss('cancel');
				};

			};

		}
	};
}]);
