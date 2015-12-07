'use strict';

citApp.factory('FuncaoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function FuncaoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/funcao');
    	
    	this.findFuncaoPorOrganizacao = function(nome, organizacaoId) {
    		return restangularTabelasCorp.one('rest/funcao').getList("findFuncaoPorOrganizacao", {"nome": nome, "organizacaoId" : organizacaoId}).then();
    	};    	
    }

    AbstractRepository.extend(FuncaoRepository);

    return new FuncaoRepository();
}]);
