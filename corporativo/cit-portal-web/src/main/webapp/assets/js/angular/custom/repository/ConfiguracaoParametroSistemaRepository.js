'use strict';

citApp.factory('ConfiguracaoParametroSistemaRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function ConfiguracaoParametroSistemaRepository() {

    	AbstractRepository.call(this, restangular, 'rest/configuracaoParametroSistema');
    	
    	this.getParametro = function(chave) {
    		return restangular.one(this.route + "/getParametro").get({"chave": chave}).then();
        };
    }

    AbstractRepository.extend(ConfiguracaoParametroSistemaRepository);

    return new ConfiguracaoParametroSistemaRepository();
}]);