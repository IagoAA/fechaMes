'use strict';

citApp.controller('DashboardController', ['$scope', '$translate', 'WidgetRepository', 'dashboard', 'PainelRepository', '$timeout',
		function DashboardController($scope, $translate, WidgetRepository, dashboard, PainelRepository,  $timeout) {

	$scope.dashboardEsi = false;
	$scope.init = function(dashboardEsi) {
		$scope.dashboardEsi = dashboardEsi;
	};

	$scope.paineis = [];

	$scope.painelEdicao = {};

	// BUSCA DADOS DOS PAINEIS
	$scope.obterDadosPainel =  function(painel, index){

		//Verifica se a aba da dashboard já foi inicializada, caso seja false obtem o painel e monta a dashboard.
		if(!painel.montouPainelItens){

			PainelRepository.get(painel.id).then(function(result) {

				result.montouPainelItens = true;

				result.active = true;

				$scope.paineis[index] = result;

				$scope.montarDashboard($scope.paineis[index]);

			});

		}
	};

	// Salvar Painel
	$scope.saveOrUpdate = function(model) {

		 $scope.setLoadingSalva(true);

		 $scope.obterPainelPorId(model.idPainel);

		 $scope.atualizarPainelItens(model);

		 PainelRepository.salvarDashBoardUsuario($scope.painelEdicao.originalElement).then(function(result) {

			 $scope.atualizarPainel(result, model.idPainel);

			 $scope.painelEdicao = result;

			 $scope.setLoading(false);

			 $scope.showAlert("success",  $translate.instant('MSG.MG001'));
		});


	};

	//Método responsavel por obter os paineis
	$scope.obterPaineis = function(){

		PainelRepository.findPorUsuario().then(function(result) {

			if ($scope.dashboardEsi) {
				$scope.paineis = [];
				angular.forEach(result, function (painel) {
					angular.forEach(painel.painelModulos, function (painelModulo) {
						if (painelModulo.modulo.nome.toUpperCase().indexOf("ESI") >= 0) {
							$scope.paineis.push(painel.originalElement);
						}
					});
				});
			}else{
				$scope.paineis = result;
			}

			if($scope.paineis.length > 0){

				$scope.ordenarLista($scope.paineis, "nome");

				PainelRepository.findPainelDashBoard($scope.paineis[0].id).then(function(result) {

					result.montouPainelItens = true;

					result.active = true;

					$scope.paineis[0] = result;

					$scope.montarDashboard($scope.paineis[0]);

				});
			};
		});
	};

	//Método responsável por montar a montarDashboard
	$scope.montarDashboard = function(painel){

		if(painel.painelItens){

			//Ordena a lista através da posição da coluna
			 $scope.ordenarLista(painel.painelItens, 'posicaoColumn');

			var rows = [];
			var row = {};
			var widgets = [];

			var columns = [];
			var column = {};

			var posicaoColumnTemp = 0 ;
			var tempo = 200;

			angular.forEach(painel.painelItens, function (painelItem) {

			//Verifica se o painelItem foi removido
			   var widget = {};

			   widget.title =  painelItem.nome;
			   widget.type = painelItem.type;
			   widget.posicaoLinha = painelItem.posicaoLinha;
			   widget.idPainelItem = painelItem.id;
			   widget.widget = painelItem.widget;
			   widget.remover = false;

			   //Seta as configurações do widget.
			   widget.config = {
				   path : painelItem.urlServico,
				   parametros : painelItem.painelItemParametros,
				   apresentarUrl : painelItem.widget.apresentarUrlServico,
				   tempoServico:  tempo,
				   links : painelItem.links,
				   tempoAtualizacao    : painelItem.tempoAtualizacao
			   };

			   tempo = tempo+200;

			   //Verifica se a posição do item e a mesma posição dos itens da coluna que está sendo montada.
			   if(painelItem.posicaoColumn == posicaoColumnTemp){

				   column.styleClass = painelItem.styleClass;
				   widgets.push(widget);

			   }else{

				   $scope.ordenarLista(widgets, 'posicaoLinha');

				   column.widgets = widgets;
				   columns.push(column);
				   //Inicia as propriedades da nova Coluna
				   widgets = [];
				   column = {};
				   column.styleClass = painelItem.styleClass;
				   widgets.push(widget);
				   //Incrementar a posição da coluna
				   posicaoColumnTemp++;
		   };
		});

		   $scope.ordenarLista(widgets, 'posicaoLinha');

		   column.widgets = widgets;
		   columns.push(column);

		   row.columns = columns;
		   rows.push(row);
		   painel.model = {};
		   painel.model.rows = rows;
		   painel.model.idPainel = painel.id;

		};
	};
	// Método responsável por atualizar a lista de painelItens
	$scope.atualizarPainelItens = function(model){
		//Percorre as rows, columns e widgets para recuperar os dados e setar no painelItem
		 angular.forEach(model.rows, function (row) {

	            angular.forEach(row.columns, function (column, index) {

	            	var linha = 1;

	            	angular.forEach(column.widgets, function (widget) {

	            		var painelItem = $scope.obterPainelItemPorId(widget.idPainelItem);
	            		//Verifica se existe um painelItem, se existir atualiza os dados, caso contrario cria um novo painelItem
	            		if(painelItem){

	            			painelItem.type = widget.type;
	            			painelItem.nome = widget.title;
	            			painelItem.posicaoColumn = index;
	            			painelItem.posicaoLinha = linha;
	            			linha++;
	            			painelItem.styleClass = column.styleClass;
	            			painelItem.remover = widget.remover;
	            			painelItem.urlServico = widget.config.path;
	            			painelItem.painelItemParametros = widget.config.parametros;
	            			painelItem.links = widget.config.links;
	            			painelItem.tempoAtualizacao = widget.config.tempoAtualizacao;
	            			// Verifica se o widget foi removido
	            		}else if(!widget.remover){

	            			var painelItemNovo = {};

	            			painelItemNovo.type = widget.type;
	            			painelItemNovo.nome = widget.title;
	            			painelItemNovo.posicaoColumn = index;
	            			painelItemNovo.posicaoLinha = linha;
	            			painelItemNovo.styleClass = column.styleClass;
	            			painelItemNovo.urlServico = widget.config.path;
	            			painelItemNovo.widget = widget.widget;
	            			painelItemNovo.links = widget.config.links;
	            			painelItemNovo.tempoAtualizacao = widget.config.tempoAtualizacao;
	                		linha++; // Incrementa a linha, para adicionar o proximo widget na outra linha
	                		$scope.painelEdicao.originalElement.painelItens.push(painelItemNovo);

	            		}

	                });
	            });
	        });
		};

	$scope.obterPaineis();

	// Método reponsável por obter o painelItem da listaPaineis através do id
	$scope.obterPainelItemPorId = function(id){

		var painelItemTemp = null;
		//Percorre a lista de painelItens para obter o painelItem
		 angular.forEach($scope.painelEdicao.originalElement.painelItens, function (painelItem) {
			//Verifica se o id do painelItem da lista é o mesmo do id passao por parametro.
			 if(id && painelItem.id === id){

				 painelItemTemp = painelItem;

			 }
		 });

		 return painelItemTemp;

	};

	 //Método responsável por obter o painel da lista de paineis da dashboard através do id
	$scope.obterPainelPorId = function(id){

		$scope.painelEdicao = {};
		//Percorre a lista de painelItens para obter o painelItem
		 angular.forEach($scope.paineis, function (painel) {
			//Verifica se o id do painelItem da lista é o mesmo do id passao por parametro.
			 if(id && painel.id === id){

				 angular.copy(painel, $scope.painelEdicao);

			 }
		 });

	};

	//Método responsável por atualizar o painel editado
	$scope.atualizarPainel = function(painelAtualizado, idPainel){
		//Percorre a lista de painelItens para obter o painelItem
		 angular.forEach($scope.paineis, function (painel, index) {

			 if(painel.id === idPainel){

				 $scope.paineis.splice(index, 1);

				 $scope.paineis.push(painelAtualizado);

				 painelAtualizado.active = true;

				 $scope.ordenarLista($scope.paineis, "id");

				 $scope.montarDashboard(painelAtualizado);

			 }
		 });

	};
	//Método responsável por ordenar lista
	$scope.ordenarLista= function(array, attribute){

			array.sort(function(a, b){
		        a = parseInt(a[attribute]);
		        b = parseInt(b[attribute]);
		        return a - b;
		    });
		    return array;
		};

	$scope.$on('adfDashboardChanged', function (event, name, model) {
		$scope.saveOrUpdate(model);
	});
}]);
