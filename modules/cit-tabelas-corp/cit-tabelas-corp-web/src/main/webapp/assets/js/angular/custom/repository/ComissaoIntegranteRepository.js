'use strict';

citApp.factory('ComissaoIntegranteRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function ComissaoIntegranteRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/comissaoIntegrante');
    	
    	// Listar comissão integrante por comissão e nome colaborador
    	this.findComissaoIntegrantePorColaborador = function(nome, idComissao) {
    		return restangularTabelasCorp.one(this.route).getList("findComissaoIntegrantePorColaborador", {nome : nome, idComissao : idComissao}).then();
    	};
    	
    }
    
    AbstractRepository.extend(ComissaoIntegranteRepository);
    
    return new ComissaoIntegranteRepository();
}]);