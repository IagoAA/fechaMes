'use strict';

citApp.controller('DefaultFileController', ['$scope', 'DefaultFileRepository', 'DominioRepository',
		function DefaultFileController($scope, DefaultFileRepository, DominioRepository) {
	$scope.dialog;

	$scope.defaultFiles = [];
	$scope.defaultFile = {};

	DominioRepository.findAllDominio('tipoFile').then(function(result) {
		$scope.dominiosDefaultFile = result;
	});

	// METODO PADRÃO AO INICIAR A TELA
	var init = function() {
		$scope.defaultFile = {};
		$scope.defaultFile.ativo = 'S';
	};

	// BUSCA A LISTA DOS ARQUIVOS PADRÕES
	var getDefaultFileList = function() {
		DefaultFileRepository.getList().then(function(result) {
			$scope.defaultFiles = [];
			for(var i = 0; i < result.length; i++) {
				$scope.defaultFiles.push(result[i].originalElement);
			}
		});
	};

	// CARREGA UM ARQUIVO PADRÃO PARA EDIÇÃO
	$scope.editDefaultFile = function(defaultFile) {
		$scope.defaultFile = defaultFile;
	};

	// EXCLUI UM ARQUIVO PADRÃO
	$scope.removeDefaultFile = function(defaultFile) {
		bootbox.dialog({
			message: "<span class='bigger-110'><p>Confirma exclusão?</p></span>",
			buttons: {
				"danger": {
					"label" : "Confirma",
					"className" : "btn-sm btn-danger",
					"callback": function() {
						DefaultFileRepository.remove(defaultFile).then(function() {
							getDefaultFileList();
						});
					}
				},
				"button": {
					"label" : "Cancela",
					"className" : "btn-sm"
				}
			}
		});
	};

	// SALVA ARQUIVO PADRÃO
	$scope.saveOrUpdate = function() {
		$scope.setLoadingSalva(true);

		if(!$scope.defaultFileForm.$valid) {
			$scope.showAlert("danger", "Preencha os campos obrigatórios!");
			$scope.setLoadingSalva(false);
			return false;
		}

		DefaultFileRepository.save($scope.defaultFile).then(function(result) {
			$scope.setLoading(false);
			$scope.showAlert("success", "Arquivo padrão salvo com sucesso!");

			getDefaultFileList();

			init();
		});
	};

	// SALVA CONFIGURAÇÃO DE POSIÇÕES E MENUS E SUBMENUS
	$scope.saveConfig = function() {
		$scope.setLoading(true);

		DefaultFileRepository.saveConfig($scope.defaultFiles).then(function(result) {
			$scope.setLoading(false);
			$scope.showAlert("success", "Configurações de menu salvas com sucesso!");
			getDefaultFileList();
		});
	};

	getDefaultFileList();

	init();
}]);