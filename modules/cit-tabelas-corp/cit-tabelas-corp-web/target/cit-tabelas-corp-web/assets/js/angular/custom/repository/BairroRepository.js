'use strict';

citApp.factory('BairroRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function BairroRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/bairro');
    	
	 	this.findBairro = function(cidade) {
	 		return restangularTabelasCorp.all('rest/bairro' + '/findBairro').post(cidade).then();
	 		
	 	};
    }
    
    AbstractRepository.extend(BairroRepository);
    
    return new BairroRepository();
}]);