'use strict';

citApp.controller('OrgaoExternoController', ['$scope', 'OrgaoExternoRepository', function OrgaoExternoController($scope, OrgaoExternoRepository) {
	$scope.dialog;
	$scope.orgaoExterno = {};
	$scope.orgaoExterno.pessoa = {};
	$scope.orgaoExterno.pessoa.pessoaFisica = {};
	$scope.orgaoExterno.pessoa.pessoaJuridica = {};
	$scope.endereco = {};
	$scope.endereco.bairro = {};
	$scope.endereco.bairro.cidade = {};
	$scope.endereco.bairro.cidade.estado = {};
	$scope.endereco.bairro.cidade.estado.regiao = {};
	$scope.endereco.bairro.cidade.estado.regiao.pais = {};
	$scope.telefone1 = {};
	$scope.telefone2 = {};
	$scope.fax = {};

	$scope.orgaoExterno.pessoa.listaEndereco = [
       $scope.endereco
    ];


	$scope.orgaoExterno.pessoa.telefones = [
	   $scope.telefone1,
	   $scope.telefone2,
	   $scope.fax
	];

	$scope.orgaoExterno.pessoa.contatos = [];


	// SE ESTIVER EDITANDO CARREGA O ORGAO EXTERNO
	if ($scope.$parent != undefined && ($scope.$parent.newObejct || $scope.$parent.idObejct != 0)) {
		$scope.setLoadingGet(true);

		OrgaoExternoRepository.get($scope.$parent.idObejct).then(function(result) {
			$scope.orgaoExterno = result;
			$scope.setLoading(false);
		});
	};

	// SALVA O ORGAO EXTERNO
	$scope.saveOrUpdate = function(){
		$scope.setLoadingSalva(true);

		angular.forEach($scope.orgaoExterno.pessoa.contatos, function(contato, key) {
			delete contato.$edit;
		});
		OrgaoExternoRepository.save($scope.orgaoExterno).then(function(result) {
			$scope.setLoading(false);
			$scope.showAlert("success", "Orgao Externo salvo com sucesso!");
		});
	};

	// ADICIONAR CONTATO
	$scope.addContato = function() {
		$scope.orgaoExterno.pessoa.contatos.push({
			$edit: true,
			telefones: [{}]
		});
	};
}]);


