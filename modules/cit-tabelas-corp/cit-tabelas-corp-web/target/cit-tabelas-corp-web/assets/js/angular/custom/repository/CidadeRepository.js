'use strict';

citApp.factory('CidadeRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function CidadeRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/cidade');
    	
	 	this.findCidade = function(cidade) {
	 		return restangularTabelasCorp.all('rest/cidade' + '/findCidade').post(cidade).then();
	 		
	 	};
    }
    
    AbstractRepository.extend(CidadeRepository);
    
    return new CidadeRepository();
}]);