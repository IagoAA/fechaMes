'use strict';

citApp.factory('LuckRepository', ['RestangularFechaMes', 'AbstractRepository', function (restangularFechaMes, AbstractRepository) {

    function LuckRepository() {
    	AbstractRepository.call(this, restangularFechaMes, 'rest/luck');

    	this.listarLuck = function(nome) {
			return restangularFechaMes.one('rest/luck').getList("listarLuck", {"nome": nome}).then();
		};
    }

    AbstractRepository.extend(LuckRepository);

    return new LuckRepository();
}]);