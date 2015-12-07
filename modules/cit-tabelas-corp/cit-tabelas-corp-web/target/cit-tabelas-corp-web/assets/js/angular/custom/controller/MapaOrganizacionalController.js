'use strict';

citApp.controller('MapaOrganizacionalController', ['$scope', 'MapaOrganizacionalRepository', '$filter', '$translate', '$timeout', function MapaOrganizacionalController($scope, MapaOrganizacionalRepository, $filter, $translate, $timeout) {

	$scope.dialog;

	$scope.mapaOrganizacional = {
			observacoes : []
	};

	$scope.organizacao = $scope.usuarioLogado.organizacao;

	$scope.edit = true;

	// Metodo responsavel por resetar o formulario
	$scope.resetForm = function() {

		$scope.mapaOrganizacional = {
				dataInicio : $filter('date')(new Date(), "dd/MM/yyyy"),
				observacoes : []
		};

		$scope.edit = true;
		$timeout(function(){
			$scope.formMapaOrganizacional.$submitted = false;
			$scope.formMapaOrganizacional.$setPristine();
			if($scope.formMapaOrganizacional['observacao.descricao']) {
				$scope.formMapaOrganizacional['observacao.descricao'].$setViewValue('');
				$scope.formMapaOrganizacional['observacao.descricao'].$render();
			}
		});
	};

	// Metodo responsavel por limpar o fomulario
	$scope.limpar = function(){
		$scope.resetForm();
	};

	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#mapaOrganizacionalList').scope().fetchResult();
	};

	// SALVAR MAPAORGANIZACIONAL
	$scope.saveOrUpdate = function() {
		$scope.formMapaOrganizacional.$submitted = true;

		// verifica se o formulario está valido para salvar ou fazer update
		if($scope.formMapaOrganizacional.$valid){

		$scope.mapaAtivo = {};

		if($scope.mapaOrganizacional && $scope.mapaOrganizacional.id === undefined){

			//busca o mapaOrganizacional ativo
			MapaOrganizacionalRepository.findMapaAtivo().then(function(result){

				var validacaoMapaAtivo = true;

				if(result.id){
					//valida a data inicio do mapaOrganizacional a ser salvo
					if($scope.validaDataInicio(result.originalElement.dataInicio)){

						validacaoMapaAtivo = false;
						$scope.mapaOrganizacional.dataInicio = "";
						$scope.showAlert("error", $translate.instant('VALIDACAO.CAMPO_DATA_INICIO_MAPA') +" ("+result.originalElement.dataInicio+")");

					}
				}

				if(validacaoMapaAtivo){
					$scope.setLoadingSalva(true);

					//salva o mapaOrganizacional
					MapaOrganizacionalRepository.save($scope.mapaOrganizacional).then(function(result) {

						$scope.setLoading(false);

						$scope.showAlert("success", $translate.instant('LABEL.MAPA_ORGANIZACIONAL_SALVO_SUCESSO'));

						$scope.mapaOrganizacional = result.originalElement;

						$scope.formMapaOrganizacional.$submitted = false;

					});
				}
			});
		}else{

			$scope.setLoadingSalva(true);

			//salva o mapaOrganizacional
			MapaOrganizacionalRepository.save($scope.mapaOrganizacional).then(function(result) {

				$scope.setLoading(false);

				$scope.showAlert("success", $translate.instant('LABEL.MAPA_ORGANIZACIONAL_SALVO_SUCESSO'));

				$scope.mapaOrganizacional = result.originalElement;

			});

		}

		}else{

			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'), " ", false);

		}

		$scope.formMapaOrganizacional.$submitted = false;
	};

    // SE ESTIVER EDITANDO CARREGA O MAPAORGANIZACIONAL
	if ($scope.$parent != undefined && (!$scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {

		$scope.setLoadingGet(true);

		MapaOrganizacionalRepository.get($scope.$parent.idObejct).then(function(result) {

			$scope.mapaOrganizacional = result.originalElement;

			$scope.setLoading(false);

		});
	}else{
		$scope.organizacao = $scope.usuarioLogado.organizacao;
	}

	// seta o mapaOrganizacional no scope
	$scope.getMapaOrganizacional = function(idObject, visualizar){

		if (idObject != 0) {

			MapaOrganizacionalRepository.get(idObject).then(function(result) {

				$scope.mapaOrganizacional = result.originalElement;
				$scope.organizacao = $scope.usuarioLogado.organizacao;

			});

			$scope.edit = visualizar;
		};
	};

	// MODAL QUE CONFIRMA REMOVER DO MAPAORGANIZACIONAL
	$scope.remove = function(mapaOrganizacional){

		//busca o utimo mapaOrganizacional fechado
		MapaOrganizacionalRepository.findUltimoMapaFechado().then(function(result) {

			//verifica se existe algum mapaOrganizacional fechado
			if(result.originalElement.id === undefined || result.originalElement.id === null){

				$scope.showAlert("error", $translate.instant('VALIDACAO.ULTIMO_MAPA_VIGENTE'));

			} else {

				//abre o modal remove mapaOrganizacional
				$scope.$openModalConfirm({
					message: $translate.instant('MSG.EXCLUSAO_MAPA_ORGANIZACIONAL'),
					callback: function () {

						//remove o mapaOrganizacional
						MapaOrganizacionalRepository.remove(mapaOrganizacional).then(function() {

							$scope.mapaOrganizacional = {};

							$scope.$modalConfirmInstance.dismiss('cancel');

							$scope.showAlert('success', $translate.instant('LABEL.MAPA_EXCLUIDO_SALVO_SUCESSO'), " ", false);

							angular.element('#mapaOrganizacionalList').scope().fetchResult();

							$scope.edit = true;
						});
					}
				});
			}
		});
	};

	//Metodo responsavel por validar a data inicio do mapaOrganizacional a ser criado
	$scope.validaDataInicio = function(data){

		var dataInicioMapaAtivo = converterStringEmDate($filter('date')(data, 'dd/MM/yyyy'));

		var dataInicioMapaNovo = converterStringEmDate($filter('date')($scope.mapaOrganizacional.dataInicio, 'dd/MM/yyyy'));

		var dayData = dataInicioMapaNovo.getDate();

		//remove 1 dia da dataInicio do mapaOrganizacional a ser criado
		dataInicioMapaNovo.setDate(dayData - 1);

		//verifica se a data inicio do novo mapaOrganizacional e menor ou igual data inicio do mapa ativo
		return dataInicioMapaNovo.getTime() <= dataInicioMapaAtivo.getTime();
	};
}]);


