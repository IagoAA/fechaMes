'use strict';

citApp.factory('MapaOrganizacionalRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function MapaOrganizacionalRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/mapaOrganizacional');

    	this.findUltimoMapaFechado = function(){
    		return 	restangularTabelasCorp.one(this.route + "/findUltimoMapaFechado").get().then();
    	};

    	this.findMapaAtivo = function(){
    		return 	restangularTabelasCorp.one(this.route + "/findMapaAtivo").get().then();
    	};
    }

    AbstractRepository.extend(MapaOrganizacionalRepository);

    return new MapaOrganizacionalRepository();
}]);