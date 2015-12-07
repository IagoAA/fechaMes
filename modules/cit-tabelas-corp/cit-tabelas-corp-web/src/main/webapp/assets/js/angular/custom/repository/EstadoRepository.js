'use strict';

citApp.factory('EstadoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function EstadoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/estado');
		
    	this.findEstado = function(regiaoNome) {
			return restangularTabelasCorp.all('rest/estado' + '/findEstado').post(regiaoNome).then();
	
		};
    }
    
    AbstractRepository.extend(EstadoRepository);
    
    return new EstadoRepository();
}]);