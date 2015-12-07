'use strict';

citApp.factory('PaisRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function PaisRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/pais');
    	
    	this.listarPais = function(nome) {
			return restangularTabelasCorp.one('rest/pais').getList("listarPais", {"nome": nome}).then();
		};
    }
    
    AbstractRepository.extend(PaisRepository);
    
    return new PaisRepository();
}]);