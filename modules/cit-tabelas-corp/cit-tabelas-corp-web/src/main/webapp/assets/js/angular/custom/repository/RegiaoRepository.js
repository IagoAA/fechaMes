'use strict';

citApp.factory('RegiaoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function RegiaoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/regiao');
    	
		this.findRegiao = function(paisNome) {
			return restangularTabelasCorp.all('rest/regiao' + '/findRegiao').post(paisNome).then();
	
		};
    }
    
    AbstractRepository.extend(RegiaoRepository);
    
    return new RegiaoRepository();
}]);