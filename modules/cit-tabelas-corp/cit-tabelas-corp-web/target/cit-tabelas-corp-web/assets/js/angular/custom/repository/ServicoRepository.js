'use strict';

citApp.factory('ServicoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function ServicoRepository() {

    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/servico');

    	this.listarServico = function(nome) {
			return restangularTabelasCorp.one('rest/servico').getList("listarServico", {"nome": nome}).then();
		};

    	this.findServicoPorOrganizacao = function(nome, organizacaoId) {
    		return restangularTabelasCorp.one('rest/servico').getList("findServicoPorOrganizacao", {"nome": nome, "organizacaoId" : organizacaoId}).then();
    	};
    }

    AbstractRepository.extend(ServicoRepository);

    return new ServicoRepository();
}]);