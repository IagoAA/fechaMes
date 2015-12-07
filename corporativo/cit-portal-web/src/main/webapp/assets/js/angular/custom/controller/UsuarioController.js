'use strict';

citApp.controller('UsuarioController', ['$scope', 'UsuarioRepository', 'PaginaUsuarioRepository', 'FiltroRepository', '$translate', '$timeout', 'FavoritoRepository', 'OrganizacaoRepository', 'UsuarioOrganizacaoItemRepository', '$filter', 'PrivilegioRepository', 'GrupoRepository',
		function UsuarioController($scope, UsuarioRepository, PaginaUsuarioRepository, FiltroRepository, $translate, $timeout, FavoritoRepository, OrganizacaoRepository, UsuarioOrganizacaoItemRepository, $filter, PrivilegioRepository, GrupoRepository) {

	$scope.usuario = {
		contaHabilitada : true,
		contaExpirada : false,
		contaBloqueada : false,
		credencialExpirada : false,
		sempreNovaAba : false,
		organizacoes : [],
		grupoUsuarios : []
	};

	$scope.searchParams = {};
	$scope.indexFiltro = {};
	$scope.indexPagina = {};

	$scope.novo = false;

	$scope.isBloquear = false;

	$scope.isDesbloquear = false;

	// LIMPA FORMULÁRIO PARA NOVO CADASTRO
	$scope.resetForm = function() {
		$scope.limparUsuario();
		$scope.edit = true;
		$scope.novo = true;
		$scope.pgEdit = true;
		$scope.isBloquear = false;
		$scope.isDesbloquear = $scope.isBloquear;
		$scope.searchParams = {};
		$scope.indexFiltro = {};
		$scope.indexPagina = {};
		$scope.privilegiosSource = [];
		$scope.atualizarListaPrivilegios();
		delete $scope.isRedefinirSenha;
		$scope.findGrupoSource();
		$timeout(function(){
			$scope.usuarioForm.$submitted = false;
			$scope.usuarioForm.$setPristine();
		});
	};

	$scope.atualizarListaPrivilegios = function(){
		$scope.privilegiosSource = [];
		PrivilegioRepository.getList().then(function(result) {
			var usuarioPrivilegio = {};
			for(var i = 0; i < result.length; i++) {
				usuarioPrivilegio = {
					privilegio: result[i].originalElement
				};
				
				$scope.privilegiosSource.push(usuarioPrivilegio);
			}
			
			if($scope.usuario && $scope.usuario.usuarioPrivilegios){
				$scope.usuario.usuarioPrivilegios.forEach(function (item, index) {
					var indexPrivilegio = _.findIndex($scope.privilegiosSource, {privilegio: {id: item.privilegio.id}});
					
					if(indexPrivilegio >= 0) {
						$scope.privilegiosSource[indexPrivilegio] = item;
					}
				});
			}
		});
	};

	$scope.atualizarListaPrivilegios();

	//Método responsável por montar a lista de grupoUsuario
   $scope.findGrupoSource = function(){
	   $scope.grupoUsuariosSource = [];
	   
	   GrupoRepository.getList().then(function(result) {
		   //Percorre a lista de grupo para montar a lista grupoUsuario
		   var grupoUsuario = {};
		   for(var i = 0; i < result.length; i++) {
			   grupoUsuario = {
				   grupo: result[i].originalElement
			   };
			   
			   $scope.grupoUsuariosSource.push(grupoUsuario);
	   	   }

		   if($scope.usuario && $scope.usuario.grupoUsuarios) {
			   $scope.usuario.grupoUsuarios.forEach(function (item, index) {
				   var indexGrupoUsuario = _.findIndex($scope.grupoUsuariosSource, {grupo: {id: item.grupo.id}});
				   
				   if(indexGrupoUsuario >= 0) {
					   $scope.grupoUsuariosSource[indexGrupoUsuario] = item;
				   }
			   });
		   }
		});	   
	};

	// ATUALIZA PAGINA DE PESQUISA
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchUsuario').scope().fetchResult();
	};

	// BLOQUEIA O USUÁRIO
	$scope.confirmarBloqueioUsuario = function(){
		if($scope.usuario.dataBloqueio && $scope.usuario.dataBloqueio !== ""){

			UsuarioRepository.save($scope.usuario).then(function(result){
				$scope.usuario = result.originalElement;

				$scope.atualizarListaPrivilegios();
				$scope.findGrupoSource();

				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_BLOQUEIO'));
			});
		}else{
			$scope.showAlert('error', $translate.instant('MSG.MG011'), " ", false);
		}
	};

	// DESBLOQUEIA O USUARIO
	$scope.desbloquear = function() {
		$scope.setLoadingSalva(true);

		$scope.usuario.dataBloqueio =  null;

		UsuarioRepository.save($scope.usuario).then(function(result){
			$scope.setLoadingSalva(false);

			$scope.usuario = result.originalElement;

			$scope.atualizarListaPrivilegios();
			$scope.findGrupoSource();

			$scope.edit = true;
			$scope.isBloquear = true;
			$scope.isDesbloquear = !$scope.isBloquear;
			$scope.showAlert("success", $translate.instant('MSG.SUCESSO_DESBLOQUEIO'));
		});
	};

	// MODAL QUE CONFIRMA REMOVER DO USUARIO
	$scope.remove = function(usuario){
		$scope.usuario = usuario;
		$scope.$openModalConfirm({
			message: $translate.instant('MSG.CONFIRMA_EXCLUSAO'),
			callback: function () {
				UsuarioRepository.remove($scope.usuario).then(function() {

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant('MSG.SUCESSO_USUARIO_EXCLUIDO'));
					angular.element('#searchUsuario').scope().fetchResult();

					$scope.resetForm();
				});
			}
		});
	};

	// Metodo responsavel por verificar se o username que o usuario digitou não está em uso
	$scope.validaUsername = function(){
		if($scope.usuario.id === undefined && $scope.usuario.username){

			return UsuarioRepository.buscaUsuarioByUsername(angular.lowercase($scope.usuario.username)).then(function(result) {
				if(result.originalElement.id){
					$scope.usuario.username = "";
					$scope.showAlert('error', $translate.instant('MSG.NOME_USUARIO_EM_USO'), " ", false);
				}
			});
		}
	};

	// Metodo responsavel por verificar se o email que o usuario digitou não está em uso
	$scope.validaEmail = function(){
		if($scope.usuario.id === undefined && $scope.usuario.email){
			return UsuarioRepository.buscaUsuarioByEmail($scope.usuario.email).then(function(result) {
				if(result.originalElement.id){
					$scope.usuario.email = "";
					$scope.showAlert('error', $translate.instant('MSG.EMAIL_USUARIO_EM_USO'), " ", false);
				}
			});
		}
	};

	// REMOVE CHECKED DOS FILTROS
	$scope.removeCheckedSearchParams = function (paginaUsuario){
		var searchsParams = paginaUsuario.searchParams;

		searchsParams.forEach(function (searchParams) {
			searchParams.$checked = false;
		});
	};

	// REMOVE SHOW DAS PAGÍNAS ANTERIORES
	$scope.removeShowPaginaUsuario = function () {
		var paginasUsuario =  $scope.usuario.paginasUsuario;

		paginasUsuario.forEach(function (paginaUsuario) {
			$scope.removeCheckedSearchParams(paginaUsuario);
			paginaUsuario.$show = false;
		});
	};

	// MOSTRA DETALHES DA PÁGINA DO USUÁRIO
	$scope.showPaginaUsuario = function(paginaUsuario) {
		$scope.indexFiltro = null;
		$scope.indexPagina = null;
		if (paginaUsuario.$show !== true){
			$scope.removeShowPaginaUsuario();
		}
		paginaUsuario.$show = !paginaUsuario.$show;
	};

	//#################### INÍCIO MÉTODOS DE CONTROLE DE FITROS ####################
	// MÉTODO RESPONSÁVEL POR REMOVER UM FILTRO USUÁRIO
	$scope.removerFiltro = function() {
		if ($scope.indexPagina !== null && $scope.indexFiltro !== null){
			$scope.$openModalConfirm({
				message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'),
				callback: function () {

					FiltroRepository.remove($scope.searchParams).then(function() {});
					$scope.usuario.paginasUsuario[$scope.indexPagina].searchParams.splice($scope.indexFiltro,1);

					$scope.$modalConfirmInstance.dismiss('cancel');
				}
			});
		} else {

			$scope.showAlert("warning", $translate.instant('MSG.SELECIONE_UM_ITEM_REMOVER_FILTRO'));
		}
	};

	// ADICIONA CHECK NA PAGINA DO USUÁRIO FILTROS
	$scope.checkFiltroUsuario = function (indexPagina, indexFiltro, searchParams) {
		$scope.indexPagina = indexPagina;
		$scope.indexFiltro = indexFiltro;
		$scope.searchParams = searchParams;
	};
	//#################### FIM MÉTODOS DE CONTROLE DE FITROS ####################

	// SALVA O USUARIO
	$scope.saveOrUpdate = function(){
		$scope.usuarioForm.$submitted = true;

		// VERIFICA SE O FORMULARIO ESTÁ VALIDO PARA SALVAR
		if($scope.usuarioForm.$valid && $scope.usuario.organizacoes && $scope.usuario.organizacoes.length > 0){

			$scope.setLoadingSalva(true);

			//percorre a lista de organizacoes para remover a variavel $selected caso ela exista
			angular.forEach($scope.usuario.organizacoes, function (item) {

				if(item.$selected !== undefined){

					delete item.$selected;

				}

			});

			UsuarioRepository.save($scope.usuario).then(function(result) {
				$scope.usuario = result.originalElement;
				
				$scope.atualizarListaPrivilegios();
				$scope.findGrupoSource();

				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.showAlert("success", $translate.instant('MSG.SUCESSO_USUARIO'));
				$scope.isRedefinirSenha = false;
				$scope.novo = false;
				//Verifica se o usuario logado é o mesmo da alteração, caso seja atualizar informações do usuario no sistema.
				if($scope.usuarioLogado.id === $scope.usuario.id){
					$scope.atualizarUsuarioLogado();
				}
				
				$timeout(function(){
					$scope.usuarioForm.$submitted = false;
					$scope.usuarioForm.$setPristine();
					$scope.setLoading(false);
				});
			});
		}else{
			if($scope.usuarioForm.$error.email){
				// MENSAGEM DE ERRO DE EMAIL PREENCHIDO INCORRETAMENTE
				$scope.showAlert('error', $translate.instant('MSG.EMAIL_INVALIDO'), " ", false);
			}else{
				// MENSAGEM DE ERRO DE CAMPOS OBRIGATORIOS NÃO PREENCHIDOS
				$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);
			}
			$scope.usuarioForm.$submitted = false;
		}

	};

	// LIMPA O FORMULARIO PREENCHIDO
	$scope.limparUsuario = function(){
		$scope.usuario = {
			contaHabilitada : true,
			contaExpirada : false,
			contaBloqueada : false,
			credencialExpirada : false,
			sempreNovaAba : false,
			organizacoes : [],
			usuarioPrivilegios : [],
			organizacao : {},
			grupoUsuarios : []
		};
	};

	//verifica se as senhas conhecidem
	$scope.verificaSenha = function(){

		if(($scope.usuario.password && $scope.usuario.confirmPassword) &&  ($scope.usuario.password !== $scope.usuario.confirmPassword)){
			$scope.usuario.password = "";
			$scope.usuario.confirmPassword = "";
			$scope.showAlert('error', $translate.instant('VALIDACAO.SENHAS_DIFERENTES'), " ", false);
		}
	};

	//verifica se as senhas alteradas conhecidem
	$scope.verificaNovaSenha = function(){

		if(($scope.usuario.novaSenha && $scope.usuario.confirmNovoPassword) && ($scope.usuario.novaSenha !== $scope.usuario.confirmNovoPassword)){
			$scope.usuario.novaSenha = "";
			$scope.usuario.confirmNovoPassword = "";
			$scope.showAlert('error', $translate.instant('VALIDACAO.SENHAS_DIFERENTES'), " ", false);
		}
	};

    // SE ESTIVER EDITANDO CARREGA O USUARIO
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

			UsuarioRepository.get($scope.$parent.idObejct).then(function(result) {
				$scope.usuario = result.originalElement;

				$scope.setLoading(false);
			});
	}

	// Consulta entidade e mostra no formulario
	$scope.getUsuario = function(usuario, edit){
		$scope.setLoadingGet(true);

		UsuarioRepository.get(usuario.id).then(function(result) {
			$scope.usuario = result.originalElement;

			$scope.atualizarListaPrivilegios();

			$scope.findGrupoSource();


			if(edit === true){
				$scope.isRedefinirSenha = false;

				$scope.novo = false;
			}

			if($scope.usuario.dataBloqueio === undefined || $scope.usuario.dataBloqueio === null){
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = edit;
			}else{
				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
			}
			$scope.pgEdit = edit;
			$timeout(function(){
				$scope.usuarioForm.$submitted = false;
				$scope.usuarioForm.$setPristine();
				$scope.grupoUsuariosSource =  $filter('idNotEqualGrupoUsuarioSourcePickList')($scope.grupoUsuariosSource, $scope.usuario.grupoUsuarios);
			});
			$scope.setLoading(false);
		});
	};

	//ADICIONA OU REMOVE FAVORITO DA PAGINA DO USUÁRIO
	$scope.adicionarOuRemoverFavoritoPaginaUsuario = function(paginaUsuario){
		if (paginaUsuario.favorito !== undefined && paginaUsuario.favorito !== null ) {
			PaginaUsuarioRepository.removeFavoritoPaginaUsuario(paginaUsuario.id).then(function(result) {
				paginaUsuario.version = result.originalElement.version;
				paginaUsuario.favorito = null;
			});
		} else {
			PaginaUsuarioRepository.favoritarPaginaUsuario(paginaUsuario.id).then(function(result) {
				paginaUsuario.version = result.originalElement.version;
				paginaUsuario.favorito = result.originalElement.favorito;
			});
		};
	};

	// EDITA USUÁRIO APÓS VISUALIZAR
	$scope.editarUsuario = function (){
		$scope.edit = true;
		$scope.pgEdit = true;
		$scope.isRedefinirSenha = false;
		$timeout(function(){
			$scope.usuarioForm.$submitted = false;
			$scope.usuarioForm.$setPristine();
		});
	};

	// LISTA FAVORITOS DO USUARIO
	$scope.listarFavoritos = function (){
		$scope.resetForm();

		UsuarioRepository.get($scope.usuarioLogado.id).then(function(result) {
			$scope.usuario = result.originalElement;

				$scope.isRedefinirSenha = false;

				$scope.novo = false;

				if($scope.usuario.dataBloqueio === null || $scope.usuario.dataBloqueio === undefined){
					$scope.isBloquear = true;
					$scope.isDesbloquear = !$scope.isBloquear;
					$scope.edit = true;
				}else{
					$scope.isBloquear = false;
					$scope.isDesbloquear = !$scope.isBloquear;
					$scope.edit = false;
				}

				$scope.pgEdit = true;

				$timeout(function(){
					$scope.usuarioForm.$submitted = false;
					$scope.usuarioForm.$setPristine();
				});
				$scope.setLoading(false);
		});
	};

	//#################### INÍCIO MÉTODOS AUTOCOMPLETE organizacoes ####################
	// Método responsável por listar Organizacao através do nome digitado
	$scope.findOrganizacao =  function(value) {
		return OrganizacaoRepository.listarOrganizacaoPorNome(value).then(function(result) {
			return  $filter('idNotEqualOrganizacao')(result, $scope.usuario.organizacoes) ;
	   });
	};

	// Método responsável por limpar o auto complete organizacao
	$scope.limparAutoCompleteOrganizacao = function() {
		$timeout(function(){
			$scope.organizacao = null;
		});
	};

	// Método responsável por setar a Estrutua organizacao selecionada
	$scope.setOrganizacao = function(value){
		$scope.UsuarioOrganizacaoItem = {
			organizacao : value
		};

		$scope.usuario.organizacoes.push($scope.UsuarioOrganizacaoItem);

		$scope.limparAutoCompleteOrganizacao();
	};
	//#################### FIM MÉTODOS AUTOCOMPLETE organizacoes ####################

	$scope.checkAll = function(value) {
		angular.forEach($scope.usuario.organizacoes, function (item) {
			item.$selected = value;
        });
	};

	$scope.verificaSelectedAll = function(OrganizacaoItem) {
		var verificaSelected = true;

		if(!OrganizacaoItem.$selected){
			$scope.selectedAll = false;
		}else{
			angular.forEach($scope.usuario.organizacoes, function (item) {
				if(!item.$selected){
					verificaSelected = false;
				}
	        });

			if(verificaSelected){
				$scope.selectedAll = true;
			}
		}
	};

	// Método responsável por excluir um organizacao da lista de organizacoes do usuario
	$scope.montaListaDelete = function(){
		var excluir = false;

		for (var i = $scope.usuario.organizacoes.length - 1; i >= 0; i--){
			if($scope.usuario.organizacoes[i].$selected){
				excluir = true;
				break;
			}
		}

		if(excluir){

			$scope.$openModalConfirm({
				message: $translate.instant("MSG.DESEJA_EXCLUIR_ITENS"),
				callback: function () {

					for (var i = $scope.usuario.organizacoes.length - 1; i >= 0; i--){

						if($scope.usuario.organizacoes[i].$selected){

							if($scope.usuario.organizacoes[i].id !== undefined){
								
								UsuarioOrganizacaoItemRepository.remove($scope.usuario.organizacoes[i]).then(function() {
									$scope.usuario.organizacoes.splice(i, 1);
									//Verifica se o usuario logado é o mesmo da alteração, caso seja atualizar informações do usuario no sistema
									if($scope.usuarioLogado.id === $scope.usuario.id){
										$scope.atualizarUsuarioLogado();
									}
								});
							}
						}
					}

					$timeout(function(){
						$scope.usuarioForm.$submitted = false;
						$scope.usuarioForm.$setPristine();
					});

					if($scope.selectedAll){

						$scope.selectedAll = false;

					}

					$scope.$modalConfirmInstance.dismiss('cancel');
					$scope.showAlert("success", $translate.instant("MSG.ORGANIZACOES_EXCLUIDAS"));
				}
			});


		}else{

			$scope.showAlert("error", $translate.instant("MSG.SELECIONE_ITEM_EXCLUIR"));

		}

	};

}]);