'use strict';

citApp.factory('ObservacaoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function ObservacaoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/observacao');
    }

    AbstractRepository.extend(ObservacaoRepository);

    return new ObservacaoRepository();
}]);