'use strict';

citApp.controller('ComissaoController', ['$scope', '$window', 'ComissaoRepository', 'PessoaRepository', '$translate', '$timeout', '$filter',
                                         '$injector', 'DominioRepository',
                                                   function ComissaoController($scope, $window, ComissaoRepository, PessoaRepository, $translate, $timeout, $filter,
                                                		   $injector, DominioRepository) {

	var TIPO_COMISSAO = 'tipoComissao';

	$scope.colaboradorIntegrante = null;

	$scope.resetForm = function() {
		$scope.limparComissao();
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.emUso = false;
		$timeout(function(){
			$scope.comissaoForm.$submitted = false;
			$scope.comissaoForm.$setPristine();
			if($scope.comissaoForm['observacao.descricao']) {
				$scope.comissaoForm['observacao.descricao'].$setViewValue('');
				$scope.comissaoForm['observacao.descricao'].$render();
			}
		});
	};

	$scope.limparComissao = function(){
		$scope.comissao = {
			observacoes : [],
			dataFormacao : $filter('date')(new Date(), "dd/MM/yyyy")
		};
	};

	// SALVA comissao
	$scope.saveOrUpdate = function(){
		$scope.comissaoForm.$submitted = true;

		$scope.setLoadingSalva(true);
		if ($scope.comissaoForm.$valid) { //Se tudo estiver valido salva a comissão.
			$scope.comissao.organizacao = $scope.usuarioLogado.organizacao;

			//Validação para verificar se a data de formacao é maior que a data atual
			if(!$scope.comissao.dataFormacao || !validarDataFormacao(converterStringEmDate($scope.comissao.dataFormacao))){
				$scope.showAlert('error', $translate.instant('MSG.DATA_FORMACAO_MAIOR_ATUAL'));
				$scope.setLoading(false);
				return ;
			}

			//Validação para verificar se a data de extinção é menor ou igual a data de formação
			if($scope.comissao.dataExtincao && !validarDataExtincao(converterStringEmDate($scope.comissao.dataExtincao), converterStringEmDate($scope.comissao.dataFormacao))){
				$scope.showAlert('error', $translate.instant('MSG.DATA_EXTINCAO_MENOR_FORMACAO'));
				$scope.setLoading(false);
				return ;
			}

			if ($scope.comissao.dataExtincao == "") {
				$scope.comissao.dataExtincao = null;
			}

			if ($scope.comissao.presidente == null) {
				$scope.setLoading(false);
				$scope.showAlert("error", $translate.instant("VALIDACAO.PRESIDENTE_NAO_INFORMADO"));
				return ;
			}

			var integrantes = $scope.comissao.integrantes; //verifica se tem ao menos 3 integrantes em uma comissão
			if (integrantes === undefined || integrantes === null || integrantes.length < 3) {
				$scope.setLoading(false);
				$scope.showAlert("error", $translate.instant('VALIDACAO.MINIMO_INTEGRANTES'));
				return ;
			}

			ComissaoRepository.save($scope.comissao).then(function(result) {
				$scope.comissao = result.originalElement;
				$scope.setLoading(false);
				$scope.showAlert("success", $translate.instant('MSG.MG001'));
				$scope.comissaoForm.$submitted = false;
			});

		} else { //senão da mensagem alertando aos campos obrigatorios
			$scope.setLoading(false);
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'));
		}
	};

	//Recupera a comissao e define se a pagina deve permitir edição ou não.
	$scope.getComissao = function(comissao, edit){
		$scope.setLoadingGet(true);
		$scope.emUso = false;
		var idComissao = comissao.id;

		ComissaoRepository.get(idComissao).then(function(result) {
			$scope.organizacao = $scope.usuarioLogado.organizacao;
			$scope.comissao = result.originalElement;

			$scope.edit = edit;
			$scope.setLoading(false);
			$scope.selectedAll = false;
		});

		if($scope.isModuloAtivo("/citgrp-patrimonio-web")) {

			var inventarioRepository = $injector.get(["InventarioRepository"]);

			inventarioRepository.existeVinculo({"joinClass": 'inventarioComissao.id', "id": idComissao}).then(function(result) {
				$timeout(function() {
					if (result) {
						$scope.emUso = true;
					}

					var baixaRepository = $injector.get(["BaixaRepository"]);

					baixaRepository.existeVinculo({"joinClass": 'desfazimentoComissao.id', "id": idComissao}).then(function(result) {
						$timeout(function() {
							if (result) {
								$scope.emUso = true;
							}
						});
					});

				});
			});

		}

		$scope.pgEdit = edit; //pgEdit apenas para saber se a página é de edição (true) ou de visualização (false)
	};

	//Procura um colaborador para integrar a comissão a partir de uma string value
	$scope.findIntegranteComissao =  function(value){
	   return PessoaRepository.findColaboradorPorNome(value).then(function(result) {
		   //retira o colaborador da lista de retorno se o mesmo ja foi inserido
		   if ($scope.comissao.integrantes != null && $scope.comissao.integrantes.length > 0) {
			   var totalIntegrantes = $scope.comissao.integrantes.length;
			   var totalResult = result.length;

			   for (var i=0; i<totalIntegrantes; i++) { //Verifica se o colaborador da lista esta inserido já
				   var integrante = $scope.comissao.integrantes[i].integrante;
				   for (var j=0; j<totalResult; j++) {
					   if (result[j].id == integrante.id) { //se estiver inserido retira da lista de procura
						   result.splice(j, 1);
						   totalResult = result.length;
						   j = totalResult;
					   }
				   }
			   }
		   }
		   return result;
	   });
	};

	// Select result
	$scope.setIntegranteComissao = function(item) {
		$scope.colaboradorIntegrante = item;
	};

	// Reset result
	$scope.limparIntegranteComissao = function() {
		delete $scope.colaboradorIntegrante;
	};

	// ADICIONA RESPONSAVEL E FUNÇÃO
	$scope.addIntegranteComissao = function() {

		if($scope.colaboradorIntegrante == undefined || $scope.colaboradorIntegrante == '' || $scope.colaboradorIntegrante == null) {//alerta se não houver colaborador selecionado no autocomplete
			$scope.showAlert('error', $translate.instant('MSG.SELECIONE_COLABORADOR'));
			return ;
		}

		if($scope.colaboradorIntegrante.id == undefined || $scope.colaboradorIntegrante.id == '' || $scope.colaboradorIntegrante.id == null) {//alerta se não houver colaborador selecionado no autocomplete
			$scope.showAlert('error', $translate.instant('MSG.SELECIONE_COLABORADOR'));
			return ;
		}

		if ($scope.comissao.integrantes == null) {//se a lista estiver vazia inicializa a mesma.
			$scope.comissao.integrantes = [];
		}

		if ($scope.isPresidente) {//verificação para adicionar presidente
			if ($scope.comissao.presidente != null) { //Não permite adicionar outro presidente se um já foi adicionado
				$scope.showAlert('error', $translate.instant('MSG.PRESIDENTE_JA_INFORMADO'));
				return ;
			}
			$scope.comissao.presidente = $scope.colaboradorIntegrante;
		}

		var comissaoIntegrante = { //Inicializa um integrante do inventarioComissao
				integrante : $scope.colaboradorIntegrante
		};
		$scope.comissao.integrantes.push(comissaoIntegrante); //adiciona o colaborador na lista de integrante

		delete $scope.colaboradorIntegrante;
		delete $scope.isPresidente;
	};

	// MODAL QUE CONFIRMA REMOVER DA COMISSAO
	$scope.remove = function(comissao){
		$scope.$openModalConfirm({
            message: $translate.instant("MSG.CONFIRMA_EXCLUSAO"),
            callback: function () {
                $scope.setLoadingSalva(true);

                if(comissao.id !== undefined) {
                	ComissaoRepository.remove(comissao).then(function() {
                		$scope.comissao = {};

                		$scope.$modalConfirmInstance.dismiss('cancel');
                        $scope.setLoading(false);
                        $scope.showAlert("success", $translate.instant('MSG.REGISTRO_EXCLUIDO'));
						angular.element('#searchComissao').scope().fetchResult();

						$scope.resetForm();
					});
				}
            }
		});
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchComissao').scope().fetchResult();
	};

	$scope.checkAll = function(value) {
		angular.forEach($scope.comissao.integrantes, function (item, key) {
			item.$selected = value;
			item.$index = key;
        });
	};

	$scope.verificaSelectedAll = function(integrante) {
		var verificaSelected = true;

		if(!integrante.$selected){
			$scope.selectedAll = false;
		}else{
			angular.forEach($scope.comissao.integrantes, function (item) {
				if(!item.$selected){
					verificaSelected = false;
				}
	        });

			if(verificaSelected){
				$scope.selectedAll = true;
			}
		}
	};

	//exclui da lista de integrantes os colaboradores selecionados
	$scope.prepararExcluirItensSelecionados = function(){
		var lista = recuperarItensSelecionados();
		if(lista.length > 0){
			$scope.$openModalConfirm({
				message: $translate.instant('MSG.DESEJA_EXCLUIR_ITENS'),
				callback: function () {
					angular.forEach(lista, function (colaborador, key) {
						if ($scope.comissao.presidente != null && $scope.comissao.presidente.id == colaborador.integrante.id) { //Se excluir um integrante que seja o presidente, retira a presidencia do mesmo.
							$scope.comissao.presidente = null;
						}

						angular.forEach($scope.comissao.integrantes, function (integrante, idx) {
							if (integrante.integrante.id == colaborador.integrante.id) {
								if(integrante.id !== undefined){
									comissaoIntegranteRepository.remove(integrante);
								}
								$scope.comissao.integrantes.splice(idx, 1); //retira da lista o integrante
							}
				        });
			        });
					$scope.$modalConfirmInstance.dismiss('cancel');
	            }
				});
		} else {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));
			return ;
		}
	};

	var recuperarItensSelecionados = function(){ //recupera todos os integrantes selecionados
		var lista = [];
		angular.forEach($scope.comissao.integrantes, function (item) {
			if(item.$selected){
				lista.push(item);
			}
        });
		return lista;
	};

	var validarDataFormacao = function(dataFormacao){//Valida a data de formacao para verificar se é maior que a data atual.
		dataFormacao.setHours(0, 0, 0, 0);
		var dataAtual = new Date();
		dataAtual.setHours(0, 0, 0, 0);

		if(dataFormacao.getTime() > dataAtual.getTime()){
			return false;
		} else{
			return true;
		}
	};

	var validarDataExtincao = function(dataExtincao, dataFormacao){//Valida a data de formacao para verificar se é maior que a data atual.
		dataExtincao.setHours(0, 0, 0, 0);
		dataFormacao.setHours(0, 0, 0, 0);

		if(dataFormacao.getTime() >= dataExtincao.getTime()){
			return false;
		} else{
			return true;
		}
	};

	// OBTER DOMÍNIOS TIPO COMISSÃO
	(function obterDominioTipoComissao() {

		DominioRepository.findAllDominio(TIPO_COMISSAO).then(function(result) {
			$scope.dominiosTipoComissao = result;
		});
	})();

}]);

citApp.filter('orderIntegrantesBy', [ function(){ //Ordena a lista de integrantes em ordem alfabética
 return function(input) {
	    if (!angular.isObject(input)) return input;

	    var array = [];
	    for(var objectKey in input) {
	    	if (objectKey != "fill") {
	    		array.push(input[objectKey]);
	    	}
	    }

	    array.sort(function(a, b){
    	  var alc = a.integrante.pessoa.nome.toLowerCase(),
    	      blc = b.integrante.pessoa.nome.toLowerCase();
    	  return alc > blc ? 1 : alc < blc ? -1 : 0;
    	});
	    return array;
	 };
}]);


