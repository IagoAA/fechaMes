citApp.controller('PainelController', ['$scope', 'PainelRepository', '$translate', '$timeout', 'DominioRepository', 'ModuloRepository', 'GrupoRepository', '$filter', 'PrivilegioRepository',
		function PainelController($scope, PainelRepository, $translate, $timeout, DominioRepository, ModuloRepository, GrupoRepository, $filter, PrivilegioRepository) {


	$scope.model = {};

	$scope.painelItens = [];

	$scope.widgtsRemovidos = [];

	$scope.TIPO_DADO_URL_CODIGO = 9;

	//Salvar painel
	$scope.saveOrUpdate = function() {

			$scope.formPainel.$submitted = true;

			if($scope.formPainel.$invalid){

				$scope.showAlert('error', $translate.instant('MSG.MN001'));

			}else{

				 $scope.setLoadingSalva(true);

				 $scope.atualizarPainelItens();

				 PainelRepository.save($scope.painel).then(function(result) {

					 $scope.painel = result.originalElement;

					 $scope.montarDashBoard();

					 $scope.apresentarBotaoBloquear = !$scope.painel.dataBloqueio;

					 $scope.setLoading(false);

					 $scope.showAlert("success",  $translate.instant('MSG.MG001'));
				});
		}
	};

	//Método responsável por atualizar a lista de painelItens
	$scope.atualizarPainelItens = function(){
		//Percorre as rows, columns e widgets para recuperar os dados e setar no painelItem
		 angular.forEach($scope.model.rows, function (row) {

	            angular.forEach(row.columns, function (column, index) {

	            	var linha = 1;

	            	angular.forEach(column.widgets, function (widget) {

	            		var painelItem = $scope.obterPainelItenPorId(widget.idPainelItem);
	            		//Verifica se existe um painelItem, se existir atualiza os dados, caso contrario cria um novo painelItem
	            		if(painelItem){

	            			painelItem.type = widget.type;
	            			painelItem.nome = widget.title;
	            			painelItem.posicaoColumn = index;
	            			painelItem.posicaoLinha = linha;
	            			linha++; // Incrementa a linha, para adicionar o proximo widget na outra linha
	            			painelItem.styleClass = column.styleClass;
	            			painelItem.remover = widget.remover;
	            			painelItem.urlServico = widget.config.path;
	            			painelItem.painelItemParametros = widget.config.parametros;
	            			painelItem.tempoAtualizacao = widget.config.tempoAtualizacao;
	            			painelItem.links = widget.config.links;
	            			painelItem.painelItemGrupos = widget.config.widgetGrupos;
	            			painelItem.painelItemPrivilegios = widget.config.widgetPrivilegios;
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
	            			painelItemNovo.painelItemGrupos = widget.config.widgetGrupos;
	            			painelItemNovo.painelItemPrivilegios = widget.config.widgetPrivilegios;
	                		linha++; // Incrementa a linha, para adicionar o proximo widget na outra linha
	                		$scope.painel.painelItens.push(painelItemNovo);

	            		}

	                });
	            });
	        });
		};

	// Método reponsável por obter o painelItem da listaPaineis através do id
	$scope.obterPainelItenPorId = function(id){

		var painelItemTemp = null;
		//Percorre a lista de painelItens para obter o painelItem
		 angular.forEach($scope.painel.painelItens, function (painelItem) {
			//Verifica se o id do painelItem da lista é o mesmo do id passao por parametro.
			 if(id && painelItem.id === id){

				 painelItemTemp = painelItem;

			 }
		 });

		 return painelItemTemp;

	};

	//Método responsável por obter o painel selecionado
	$scope.getPainel = function(id, visualizar){

			$scope.formPainel.$submitted = false;

			$scope.setLoadingGet(true);

			PainelRepository.get(id).then(function(result) {

				$scope.painel = result.originalElement;

				$scope.montarDashBoard();

				$scope.isVisualizar = visualizar;

				$scope.apresentarBotaoBloquear = !$scope.painel.dataBloqueio;

				$scope.findPainelGrupoSource();

				$scope.findPainelPrivilegioSource();

				$scope.findPainelModuloSource();

				$scope.setLoading(false);

			});
		};

	//Método responsável por montar a dashBoard com a lista de PainelItens
	$scope.montarDashBoard = function(){

		if($scope.painel.painelItens){

			//Ordena a lista através da posição da coluna
			 $scope.ordenarLista($scope.painel.painelItens, 'posicaoColumn');

			var rows = [];
			var row = {};
			var widgets = [];

			var columns = [];
			var column = {};

			var posicaoColumnTemp = 0 ;
			var tempo = 100;

			angular.forEach($scope.painel.painelItens, function (painelItem) {

			   var widget = {};

			   widget.title =  painelItem.nome;
			   widget.type = painelItem.type;
			   widget.posicaoLinha = painelItem.posicaoLinha;
			   widget.idPainelItem = painelItem.id;
			   widget.widget = painelItem.widget;
			   widget.remover = false;

				   widget.config = {
						   path                : painelItem.urlServico,
						   parametros          : painelItem.painelItemParametros,
						   apresentarUrl       : painelItem.widget.apresentarUrlServico,
						   tempoServico        :  tempo,
						   links               : painelItem.links,
						   tempoAtualizacao    : painelItem.tempoAtualizacao,
						   widgetGrupos        : painelItem.painelItemGrupos,
						   widgetPrivilegios   : painelItem.painelItemPrivilegios
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
			   }

		   });

		   $scope.ordenarLista(widgets, 'posicaoLinha');
		   column.widgets = widgets;
		   columns.push(column);

		   row.columns = columns;
		   rows.push(row);
		   $scope.model.rows = rows;

		   $scope.name = $scope.painel.nome;
		}
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

	//Método responsável por apresentar o dialog de confirmação de remoção
	$scope.apresentarDialogConfirmacaoRemocao = function(){

		$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.remover});
	};

	//Método responsável por remover o painel
	$scope.remover = function(){

		$scope.setLoadingRemove(true);

		PainelRepository.remove($scope.painel).then(function(result) {

			 $scope.setLoadingRemove(false);
			 $scope.resetForm();
			 $scope.showAlert("success",  $translate.instant('MSG.MG001'));

		 });

		 $scope.$modalConfirmInstance.dismiss('cancel');
	};

	//Método responsável por bloquear o painel
	$scope.bloquear = function(){

		// Verificar se o usuario informou a data bloqueio
		if(!$scope.painel.dataBloqueio){

			$scope.showAlert('error', $translate.instant('MSG.MG011'));
		}else{

			$scope.saveOrUpdate();
		}
	};

	//Método responsável por desbloquear o painel
	$scope.desbloquear = function(){

		$scope.painel.dataBloqueio = null;

		$scope.saveOrUpdate();

	};

	//Método responsável por iniciar as propriedades do painel
	$scope.resetForm = function() {

		$scope.painel = {

				painelItens : [],

				painelGrupos : [],

				painelPrivilegios : [],

				painelModulos : []
		};

		$scope.findPainelGrupoSource();

		$scope.findPainelPrivilegioSource();

		$scope.findPainelModuloSource();

		$scope.iniciarEstruturaDashBoard();

		$scope.isVisualizar = false;

		$timeout(function(){
			$scope.formPainel.$submitted = false;
			$scope.formPainel.$setPristine();
		});
	};

	$scope.fetchResult = function(){
		angular.element('#searchPainel').scope().fetchResult();

		 $scope.removeIntervalDashboard();
	};

	$scope.abilitarEditar = function(){

		$scope.isVisualizar = false;
	};

	//Método responsável por montar a lista de painelGrupo
   $scope.findPainelGrupoSource = function(){

	   	  $scope.painelGrupoSource = [];

		  GrupoRepository.getList().then(function(result) {

		   //Percorre a lista de grupo para montar a lista grupoUsuario
		   result.forEach(function (grupo, index) {

				 var painelGrupo = {};

				 painelGrupo.grupo = grupo.originalElement;

				 $scope.painelGrupoSource.push(painelGrupo);
			 });

			$timeout(function(){
				$scope.painelGrupoSource =  $filter('idNotEqualGrupoSourcePickList')($scope.painelGrupoSource, $scope.painel.painelGrupos);
			});

		   });

	   };

		//Método responsável por montar a lista de painelPrivilegio
	   $scope.findPainelPrivilegioSource = function(){

		   	  $scope.painelPrivilegioSource = [];

			  PrivilegioRepository.getList().then(function(result) {

			   //Percorre a lista de privilegio para montar a lista grupoUsuario
			   result.forEach(function (privilegio, index) {

					 var painelPrivilegio = {};

					 painelPrivilegio.privilegio = privilegio.originalElement;

					 $scope.painelPrivilegioSource.push(painelPrivilegio);
				 });

				$timeout(function(){
					$scope.painelPrivilegioSource =  $filter('idNotEqualPrivilegioSourcePickList')($scope.painelPrivilegioSource, $scope.painel.painelPrivilegios);
				});

			   });

		 };

			//Método responsável por montar a lista de painelModulo
		 $scope.findPainelModuloSource = function(){

		   	  $scope.painelModuloSource = [];

			  ModuloRepository.getList().then(function(result) {

			   //Percorre a lista de grupo para montar a lista grupoUsuario
			   result.forEach(function (modulo, index) {

					 var painelModulo = {};

					 painelModulo.modulo = modulo.originalElement;

					 $scope.painelModuloSource.push(painelModulo);
				 });

				$timeout(function(){
					$scope.painelModuloSource =  $filter('idNotEqualModuloSourcePickList')($scope.painelModuloSource, $scope.painel.painelModulos);
				});

			  });
		};

	//Método responsável por iniciar a estrutura de linha e colunas da dashBoard
	$scope.iniciarEstruturaDashBoard = function(){

		  $scope.model = {
	      structure: "4-8",
	      rows: [{
	        columns: [{
	          styleClass: "col-md-4",
	          widgets: []
	        }, {
	          styleClass: "col-md-8",
	          widgets: []
	        }]
	      }]
	    };
	};

	$scope.$on('adfDashboardChanged', function (event, name, model) {
		$scope.saveOrUpdate();
	  });
}]);