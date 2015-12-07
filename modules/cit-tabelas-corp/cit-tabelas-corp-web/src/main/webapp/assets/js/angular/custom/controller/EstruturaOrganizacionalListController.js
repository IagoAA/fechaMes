'use strict';

citApp.controller('EstruturaOrganizacionalListController',['$scope', 'EstruturaOrganizacionalRepository', 'DominioRepository', '$translate',
                                                           function EstruturaOrganizacionalListController($scope, EstruturaOrganizacionalRepository, DominioRepository,
                                                        		   $translate) {
	$scope.$showAdvancedFilters = false;
	$scope.mostraArvore = false;

	$scope.resetForm = function(estruturaOrganizacionalPai) {
		angular.element("#editEstruturaOrganizacional").scope().resetForm(estruturaOrganizacionalPai);
	};

	$scope.headers = [ {
		title : $translate.instant('LABEL.CODIGO'),
		value : 'codigo'
	}, {
		title : $translate.instant('LABEL.NOME'),
		value : 'nome'
	}, {
		title : $translate.instant('LABEL.SIGLA'),
		value : 'sigla'
	}, {
		title : $translate.instant('LABEL.TIPO_DE_ESTRUTURA_ORGANIZACIONAL'),
		value : 'dominioTipoEstruturaOrganizacional.descricao'
	}];

	// default criteria that will be sent to the server
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'codigo',
		limit : 10,
		fields: ['id','codigo', 'nome','sigla', 'dominioTipoEstruturaOrganizacional.descricao'],
		filters : [{type: 'string', field: 'codigo'},
		           {type: 'string', field: 'nome'},
		           {type: 'string', field: 'sigla'},
		           {type: 'string', field: 'dominioTipoEstruturaOrganizacional.descricao', listaDominio : []}]
	};
	
	DominioRepository.findAllDominio('tipoEstruturaOrganizacional').then(function(result) {
		$scope.filterCriteria.filters[3].listaDominio = result;
	});
	
	$scope.fetchResultEstrutura = function() {
		delete $scope.estruturaOrganizacionalChecked;
		if ($scope.mostraArvore) {
			inicializarArvore();
		} else {
			$scope.fetchResult();
		}
	};
	
	// VOLTA A TELA AO SEU ESTADO INICIAL = ESTRUTURAS DO ÓRGÃO DO USUÁRIO LOGADO
	$scope.mostrarArvore = function() {
		$scope.mostraArvore = !$scope.mostraArvore;
		delete $scope.estruturaOrganizacionalChecked;
		
		if ($scope.mostraArvore) {
			inicializarArvore();
		} else {
			$scope.fetchResultEstrutura();
		}
	};
	
	var inicializarArvore = function() {
		// Filtra somente estruturas sem data fim
		$scope.exibirEstruturasAtivas = true;
		$scope.$estruturaOrganizacionalBloqueada = false;
		$scope.estruturasOrganizacionais = [];
		// BUSCA TODAS AS ESTRUTURAS ORGANIZACIONAIS NIVEL 0 (ZERO) PELO ÓRGÃO DO USUÁRIO
		EstruturaOrganizacionalRepository.findParents($scope.usuarioLogado.organizacao.id).then(function(result) {
			// PASSA POR TODAS AS ESTRUTURAS ORGANIZACIONAIS RETORNADAS PARA RETIRAR O 'originalElement' E SETAR A PROPRIEDADE DE EXIBIÇÃO DO SINAL POSITIVO/NEGATIVO DA ÁRVORE
			for(var i = 0; i < result.length; i++) {
				result[i].originalElement.sinalPositivo = result[i].originalElement.possuiFilho;
				$scope.estruturasOrganizacionais.push(result[i].originalElement);
			}
		});
	};
	
	// MÉTODO QUE EXIBE OS FILHOS DA ESTRUTURA SELECIONADA PELO SINAL POSITIVO
	$scope.mostrarFilhos = function(estruturaOrganizacionalSelecionada){

		// Valida se a estrutura possui filho
		if(estruturaOrganizacionalSelecionada.sinalPositivo) {

			estruturaOrganizacionalSelecionada.sinalPositivo = !estruturaOrganizacionalSelecionada.sinalPositivo;

			$scope.setLoadingPesquisa(true);

			estruturaOrganizacionalSelecionada.subEstruturasOrganizacionais = [];

			// busca os filhos da estrutura selecionada
			EstruturaOrganizacionalRepository.findChildrens(estruturaOrganizacionalSelecionada.originalElement ? estruturaOrganizacionalSelecionada.originalElement.id : estruturaOrganizacionalSelecionada.id, $scope.exibirEstruturasAtivas).then(function(result) {

				// Percorre as estruturas filhas setando a propriedade do sinal positivo/negativo e adiciona o filho na lista de filhos da estrutura selecionada
				angular.forEach(result, function(estruturaFilha){
					estruturaFilha.originalElement.sinalPositivo = estruturaFilha.originalElement.possuiFilho;
					estruturaOrganizacionalSelecionada.subEstruturasOrganizacionais.push(estruturaFilha.originalElement);
					estruturaFilha.originalElement.subEstruturasOrganizacionais = [];
				});

				$scope.setLoading(false);

				$scope.scrollToCadastro('#'+estruturaOrganizacionalSelecionada.id);

			});

		} else {

			estruturaOrganizacionalSelecionada.sinalPositivo = !estruturaOrganizacionalSelecionada.sinalPositivo && (estruturaOrganizacionalSelecionada.subEstruturasOrganizacionais !== undefined && estruturaOrganizacionalSelecionada.subEstruturasOrganizacionais.length > 0);

			estruturaOrganizacionalSelecionada.subEstruturasOrganizacionais = [];
		}

	};

	// MÉTODO QUE BUSCAR UMA ESTRUTURA NA ÁRVORE POR NOME/SIGLA/ORGANIZACAO USUÁRIO (obs: acima de 4 caracteres)
	$scope.buscarTree = function(buscaEstruturaNomeSiglaOrganizacao){

		// Valida se foi digitado mais que 4 caracteres
		if(buscaEstruturaNomeSiglaOrganizacao.length > 4) {

			$scope.setLoadingPesquisa(true);

			$scope.estruturasOrganizacionais = [];

			return EstruturaOrganizacionalRepository.listarEstruturasOrganizacionaisDaTree($scope.usuarioLogado.organizacao.id, buscaEstruturaNomeSiglaOrganizacao, $scope.exibirEstruturasAtivas).then(function(result) {

				$scope.estruturasOrganizacionais = [];
				// Percorre as estruturas órgão do usuário logado
				angular.forEach(result, function(estruturaResult) {
					estruturaResult.originalElement.sinalPositivo = !(estruturaResult.originalElement.possuiFilho && estruturaResult.originalElement.subEstruturasOrganizacionais.length > 0);
					$scope.estruturasOrganizacionais.push(estruturaResult.originalElement);
				});

				$scope.setLoading(false);

			});

			// Se limpar o campo de pesquisa, inicializa as estruturas do órgão do usuário
		} else if (buscaEstruturaNomeSiglaOrganizacao.length === 0) {
			inicializarArvore();
		}
	};
	
	$scope.removeEstrutura = function(estruturaOrganizacional) {
		angular.element('#editEstruturaOrganizacional').scope().remove(estruturaOrganizacional);
	};

	// ABRE ORGANIZACAO
	$scope.abrirEstruturaOrganizacional = function(edit, estruturaOrganizacional){
		if (!estruturaOrganizacional) {
			estruturaOrganizacional = $scope.estruturaOrganizacionalChecked;
		}

		if(!edit && !estruturaOrganizacional) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}
		
		if(edit && !estruturaOrganizacional) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_EDICAO'));
			return;
		}		

		angular.element('#editEstruturaOrganizacional').scope().getEstruturaOrganizacional(estruturaOrganizacional, edit);
		$scope.$showPageEditWorkspace($scope.workspace);
	};
}]);


