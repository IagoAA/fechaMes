'use strict';

citApp.factory('FiltroRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function FiltroRepository() {
    	
		this.removerFiltro = function(filtro){
			return this.restangular.all(this.route + '/removeFiltro').post(filtro);
		};
    	
    	AbstractRepository.call(this, restangular, 'rest/searchParams');
    }
    
    AbstractRepository.extend(FiltroRepository);
    
    return new FiltroRepository();
}]);