'use strict';

citApp.factory('ConfiguracaoRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function ConfiguracaoRepository() {
    	this.getConfiguracao = function(idOrganizacao) {
    		return this.restangular.one(this.route + "/getConfiguracao").get({idOrganizacao : idOrganizacao});
        };
    	
    	AbstractRepository.call(this, restangular, 'rest/configuracao');
    }
    
    AbstractRepository.extend(ConfiguracaoRepository);
    
    return new ConfiguracaoRepository();
}]);