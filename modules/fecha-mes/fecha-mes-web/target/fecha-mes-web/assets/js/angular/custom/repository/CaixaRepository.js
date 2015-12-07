'use strict';

citApp.factory('CaixaRepository', ['RestangularFechaMes', 'AbstractRepository', function (RestangularFechaMes, AbstractRepository) {

    function CaixaRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/caixa');

		this.findCaixa = function(paisNome) {
			return restangularTabelasCorp.all('rest/caixa' + '/findCaixa').post(paisNome).then();

		};
    }

    AbstractRepository.extend(CaixaRepository);

    return new CaixaRepository();
}]);