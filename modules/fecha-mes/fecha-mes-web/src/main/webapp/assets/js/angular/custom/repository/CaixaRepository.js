'use strict';

citApp.factory('CaixaRepository', ['RestangularFechaMes', 'AbstractRepository', function (restangularFechaMes, AbstractRepository) {

    function CaixaRepository() {
    	AbstractRepository.call(this, restangularFechaMes, 'rest/caixa');

		this.findCaixa = function(paisNome) {
			return restangularFechaMes.all('rest/caixa' + '/findCaixa').post(paisNome).then();

		};
    }

    AbstractRepository.extend(CaixaRepository);

    return new CaixaRepository();
}]);