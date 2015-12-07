'use strict';

citApp.factory('DocumentoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function DocumentoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/documento');
    }

    AbstractRepository.extend(DocumentoRepository);

    return new DocumentoRepository();
}]);