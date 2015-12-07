'use strict';

citApp.factory('CaracteristicaRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function CaracteristicaRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/caracteristica');
    	
    	// Listar caracteristicas por nome
    	this.listarCaracteristicas = function(descricao) {
    		return restangularTabelasCorp.one('rest/caracteristica').getList("listarCaracteristicas", {"descricao": descricao}).then();
    	};
        
    	// Listar caracteristicas por nome e organizacao
    	this.listarCaracteristicasPorOrganizacao = function (descricao, idOrganizacao) {
    		return restangularTabelasCorp.one(this.route).getList("listarCaracteristicasPorOrganizacao", {descricao : descricao, idOrganizacao : idOrganizacao}).then();
    	};
    }
    
    AbstractRepository.extend(CaracteristicaRepository);
    
    return new CaracteristicaRepository();
}]);