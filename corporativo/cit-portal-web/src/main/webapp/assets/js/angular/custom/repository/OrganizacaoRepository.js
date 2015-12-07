'use strict';

citApp.factory('OrganizacaoRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function OrganizacaoRepository() {

    	AbstractRepository.call(this, restangular, 'rest/organizacao');
    	
    	this.listarOrganizacaoPorNome = function(nome) {
    		return restangular.one(this.route).getList("listarOrganizacaoPorNome", {"nome" : nome}).then();
    	};
    }

    AbstractRepository.extend(OrganizacaoRepository);

    return new OrganizacaoRepository();
}]);