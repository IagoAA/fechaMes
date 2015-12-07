'use strict';

citApp.factory('SeguradoraRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function SeguradoraRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/seguradora');

		this.listarSeguradorasPorNomeOrganizacao = function(value, idOrganizacao) {
			return restangularTabelasCorp.one(this.route).getList("listarSeguradorasPorNomeOrganizacao", {"nome": value, "idOrganizacao" : idOrganizacao}).then();
		};
    }
    
    AbstractRepository.extend(SeguradoraRepository);

    return new SeguradoraRepository();
}]);