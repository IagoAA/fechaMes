'use strict';

citApp.factory('FornecedorRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function FornecedorRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/fornecedor');

    	this.findTipoNotificacao = function() {
			return restangularTabelasCorp.one('rest/fornecedor').getList("findTipoFornecedor", { }).then();
		};

		this.listarFornecedoresBusca = function(value) {
			return restangularTabelasCorp.one(this.route).getList("listarFornecedoresBusca", {nome : value}).then();
		};
    }

    AbstractRepository.extend(FornecedorRepository);

    return new FornecedorRepository();
}]);