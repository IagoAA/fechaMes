'use strict';

citApp.factory('LuckRepository', ['RestangularFechaMes', 'AbstractRepository', function (RestangularFechaMes, AbstractRepository) {

    function LuckRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/luck');

    	this.listarLuck = function(nome) {
			return restangularTabelasCorp.one('rest/luck').getList("listarLuck", {"nome": nome}).then();
		};
    }

    AbstractRepository.extend(LuckRepository);

    return new LuckRepository();
}]);