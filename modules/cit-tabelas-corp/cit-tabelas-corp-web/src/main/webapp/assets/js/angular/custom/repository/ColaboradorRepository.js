'use strict';

citApp.factory('ColaboradorRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function ColaboradorRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/colaborador');
    	
    	this.findPorNome = function(nome) {
    		return restangularTabelasCorp.one('rest/colaborador').getList("findPorNome", {"nome": nome}).then();
    	};         	
    	
    	this.findPorNomeAndOrganizacao = function(nome, idOrganizacao) {
    		return restangularTabelasCorp.one('rest/colaborador').getList("findPorNomeAndOrganizacao", {"nome": nome, "idOrganizacao" : idOrganizacao}).then();
    	};    	
    }

    AbstractRepository.extend(ColaboradorRepository);

    return new ColaboradorRepository();
}]);