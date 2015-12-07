'use strict';

citApp.factory('PessoaRepository', ['RestangularTabelasCorp','AbstractRepository', function(restangularTabelasCorp, AbstractRepository) {

	function PessoaRepository(){
		AbstractRepository.call(this, restangularTabelasCorp, 'rest/pessoa');

		this.findColaboradorPorNome = function(nome) {
			return restangularTabelasCorp.one(this.route).getList("findColaboradorPorNome", {"nome": nome}).then();
		};

		this.findFuncionarioPorNome = function(nome) {
			return restangularTabelasCorp.one(this.route).getList("findFuncionarioPorNome", {"nome": nome}).then();
		};

		this.findColaboradorPorNomeAndOrganizacao = function(nome, idOrganizacao) {
			return restangularTabelasCorp.one('rest/pessoa').getList("findColaboradorPorNomeAndOrganizacao", {"nome": nome, "idOrganizacao" : idOrganizacao}).then();
		};

		this.save = function(pessoaVH) {

			if(pessoaVH.id){

				pessoaVH.parceiros = null;

				return this.bloquearRegistro(pessoaVH);

			} else {
				return pessoaVH.pessoa.id ? this.update(pessoaVH) : this.create(pessoaVH);

			}

		};

		this.bloquearRegistro = function(pessoa) {
			return restangularTabelasCorp.all(this.route + '/bloquearRegistro').post(pessoa).then();
		};

		this.create = function(pessoaVH) {
			return restangularTabelasCorp.all(this.route + '/save').post(pessoaVH).then();
		};

		this.update = function(pessoaVH) {
			return restangularTabelasCorp.all(this.route + '/update').post(pessoaVH).then();
		};

		this.removeColaborador = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeColaborador/', id).remove();
		};

		this.removeOrgaoExterno = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeOrgaoExterno/', id).remove();
		};

		this.removePortador = function(id) {
			return restangularTabelasCorp.one(this.route + '/removePortador/', id).remove();
		};

		this.removeFornecedor = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeFornecedor/', id).remove();
		};

		this.removeFornecedorObservacao = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeFornecedorObservacao/', id).remove();
		};

		this.removeFornecedorRamoAtividade = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeFornecedorRamoAtividade/', id).remove();
		};

		this.removeContato = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeContato/', id).remove();
		};

		this.removeSeguradora = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeSeguradora/', id).remove();
		};

		this.removeCliente = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeCliente/', id).remove();
		};

		this.removeFuncionario = function(id) {
			return restangularTabelasCorp.one(this.route + '/removeFuncionario/', id).remove();
		};

		// Metodo responsavel por buscar uma pessoa que seja um usuario no sistema
		this.findPessoaColaboradorUsuarioPorNome = function(nome) {
			return restangularTabelasCorp.one('rest/pessoa').getList("findPessoaColaboradorUsuarioPorNome", {"nome": nome}).then();
		};

	}

	AbstractRepository.extend(PessoaRepository);

	return new PessoaRepository();
}]);