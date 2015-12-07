citApp.controller('GrupoController', ['$scope', 'GrupoRepository', '$translate', '$timeout', 'UsuarioRepository', 'PrivilegioRepository', '$filter',
		function GrupoController($scope, GrupoRepository, $translate, $timeout, UsuarioRepository, PrivilegioRepository, $filter) {

	$scope.grupoUsuariosSource = [];

	$scope.usuariosTarget = [];

	$scope.rolesSource = [];

	$scope.rolesTarget = [];

	$scope.grupoPrivilegiosSource = [];

	$scope.privilegiosTarget = [];

	$scope.TIPO_ROLE =  "tipoRole";

	$scope.filterCriteriaUsuario = {
			start : 1,
			dir : 'asc',
			sort : 'id',
			limit : 10,
			fields: ['id', 'username'],
			filters : [{type: 'numeric',
				field: 'organizacao.id',
				value : $scope.usuarioLogado.organizacao.id}]
		};

	$scope.filterCriteriaPrivilegio = {
			start : 1,
			dir : 'asc',
			sort : 'id',
			limit : 10,
			fields: ['id', 'nome']

		};

	//Salvar grupo
	$scope.saveOrUpdate = function() {

			$scope.formGrupo.$submitted = true;

			//Vefifica se o formGrupo está invalido, caso esteja envia um alerta para o usuario
			if($scope.formGrupo.$error.email){

				$scope.showAlert('error', $translate.instant('MSG.EMAIL_INVALIDO'));

			}else if($scope.formGrupo.$invalid){

				$scope.showAlert('error', $translate.instant('MSG.MN001'));

			}else{

				 $scope.setLoadingSalva(true);

				 GrupoRepository.save($scope.grupo).then(function(result) {

					 $scope.grupo = result.originalElement;

					 $scope.apresentarBotaoBloquear = !$scope.grupo.dataBloqueio;

					 $scope.setLoading(false);

					 $scope.findUsuarioSource();

					 $scope.findPrivilegioSource();

					 $scope.showAlert("success",  $translate.instant('MSG.MG001'));
				});
		}
	};

	//Método responsável por obter o grupo selecionado
	$scope.getGrupo = function(id, visualizar){

			$scope.formGrupo.$submitted = false;

			$scope.setLoadingGet(true);

			$scope.resetForm();

			GrupoRepository.get(id).then(function(result) {

				$scope.grupo = result.originalElement;

				$scope.isVisualizar = visualizar;

				$scope.apresentarBotaoBloquear = !$scope.grupo.dataBloqueio;

				$scope.findUsuarioSource();

				$scope.findPrivilegioSource();

				$scope.setLoading(false);
			});
		};

		//Método responsável por listar os usuario do sistema
	   $scope.findUsuarioSource = function(){

		  $scope.grupoUsuariosSource = [];

		  UsuarioRepository.getListPage($scope.filterCriteriaUsuario).then(function(result) {
			  prepareGrupoUsuario(result.originalElement.objects);
		   });

	   };

	   $scope.searchByUsuario = function(login) {
		   UsuarioRepository.findAutoComplete("username", login).then(function(result) {
			   prepareGrupoUsuario(result);
		   });
	   };

	   // Método responsável por buscar uma pessoa que seja um usuário no sistema
	   $scope.searchUsuarioByPessoaColaborador = function(login) {
		   PessoaRepository.findPessoaColaboradorUsuarioPorNome("nome", nome).then(function(result) {
			   prepareGrupoUsuario(result);
		   });
	   };

	   var prepareGrupoUsuario = function(list) {

		   for(var i = 0; i < list.length; i++) {
			   var item = list[i];
			   var grupoUsuario = {};

			   grupoUsuario.usuario = item.originalElement ? item.originalElement : item;

			   addToGrupoUsuarioIfNotExist(grupoUsuario);
		   }

		   if($scope.grupo && $scope.grupo.grupoUsuarios) {
			   for(var i = 0; i < $scope.grupo.grupoUsuarios.length; i++) {
				   var item = $scope.grupo.grupoUsuarios[i];

				   var grupoUsuario = {
					   usuario: {
						   id: item.usuario.id
					   }
				   };
				   var indexUsuario = _.findIndex($scope.grupoUsuariosSource, grupoUsuario);

				   if(indexUsuario >= 0) {
					   $scope.grupoUsuariosSource[indexUsuario] = item;
				   }
			   }
		   }

		   $scope.grupoUsuariosSource = _.sortBy($scope.grupoUsuariosSource, function(item) {
			   return item.usuario.username;
		   });
	   };


	   var addToGrupoUsuarioIfNotExist = function(grupoUsuario) {
		   var grupoUsuarioForSearch = {
			   usuario: {
				  id: grupoUsuario.usuario.id
			   }
		   };

		   var indexUsuario = _.findIndex($scope.grupoUsuariosSource, grupoUsuarioForSearch);

		   if(indexUsuario < 0) {
			   $scope.grupoUsuariosSource.push(grupoUsuario);
		   }
	   };

	   //Método responsável por listar os privilegios
	   $scope.findPrivilegioSource = function(){

		   $scope.grupoPrivilegiosSource = [];

		   PrivilegioRepository.getListPage($scope.filterCriteriaPrivilegio).then(function(result) {

			   prepareGrupoPrivilegio(result.originalElement.objects);
		   });
		};

		$scope.searchByPrivilegio = function(privilegio) {
		   PrivilegioRepository.findAutoComplete("nome", privilegio).then(function(result) {
			   prepareGrupoPrivilegio(result);
		   });
	   };

		var prepareGrupoPrivilegio = function(list) {
			for(var i = 0; i < list.length; i++) {
				var item = list[i];

				var grupoPrivilegio = {};

				grupoPrivilegio.privilegio = item.originalElement ? item.originalElement : item;

				addToGrupoPrivilegioIfNotExist(grupoPrivilegio);
			}

			if($scope.grupo && $scope.grupo.grupoPrivilegios) {
				for(var i = 0; i < $scope.grupo.grupoPrivilegios.length; i++) {
					var item = $scope.grupo.grupoPrivilegios[i];

					var grupoPrivilegio = {
						privilegio: {
							id: item.privilegio.id
						}
					};

					var indexPrivilegio = _.findIndex($scope.grupoPrivilegiosSource, grupoPrivilegio);

					if(indexPrivilegio >= 0) {
						$scope.grupoPrivilegiosSource[indexPrivilegio] = item;
					}
				}
			}

			$scope.grupoPrivilegiosSource = _.sortBy($scope.grupoPrivilegiosSource, function(item) {
				return item.privilegio.nome;
			});
		};

		var addToGrupoPrivilegioIfNotExist = function(grupoPrivilegio) {
			var grupoPrivilegioForSearch = {
				privilegio: {
					id: grupoPrivilegio.privilegio.id
				}
			};

			var indexPrivilegio = _.findIndex($scope.grupoPrivilegiosSource, grupoPrivilegioForSearch);
			if(indexPrivilegio < 0) {
				$scope.grupoPrivilegiosSource.push(grupoPrivilegio);
			}
		};

			//Método responsável por apresentar o dialog de confirmação de remoção
		$scope.apresentarDialogConfirmacaoRemocao = function(){

			$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.remover});
		};

		//Método responsável por remover o grupo
		$scope.remover = function(){

			$scope.setLoadingRemove(true);

			 GrupoRepository.remove($scope.grupo).then(function(result) {

				 $scope.setLoadingRemove(false);
				 $scope.resetForm();
				 $scope.showAlert("success",  $translate.instant('MSG.MG001'));

			 });

			 $scope.$modalConfirmInstance.dismiss('cancel');
		};

		//Método responsável por bloquear o grupo
		$scope.bloquear = function(){

			// Verificar se o usuario informou a data bloqueio
			if(!$scope.grupo.dataBloqueio){

				$scope.showAlert('error', $translate.instant('MSG.MG011'));
			}else{

				$scope.saveOrUpdate();
			}
		};

		//Método responsável por desbloquear o grupo
		$scope.desbloquear = function(){

			$scope.grupo.dataBloqueio = null;

			$scope.saveOrUpdate();

		};

		//Método responsável por iniciar as propriedades do grupo
		$scope.resetForm = function() {

			$scope.grupo = {

					grupoUsuarios : [],

					grupoPrivilegios : []
			};

			$scope.isVisualizar = false;

			$scope.usuariosTarget = [];

			$timeout(function(){
				$scope.formGrupo.$submitted = false;
				$scope.formGrupo.$setPristine();
			});
		};

		$scope.fetchResult = function(){
			angular.element('#searchGrupo').scope().fetchResult();
		};

		$scope.habilitarEdicao = function(){

			$scope.isVisualizar = false;
		};

}]);