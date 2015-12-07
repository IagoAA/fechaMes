'use strict';

citApp.factory('ModuloRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function ModuloRepository() {
    	AbstractRepository.call(this, restangular, 'rest/modulo');
    	
    	this.obterModulosAtivos = function(params) {
			return restangular.one(this.route).getList("obterModulosAtivos").then();
		};
		
    }

    AbstractRepository.extend(ModuloRepository);

    return new ModuloRepository();
}]);
