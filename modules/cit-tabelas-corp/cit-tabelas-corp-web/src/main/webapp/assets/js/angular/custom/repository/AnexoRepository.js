'use strict';

citApp.factory('AnexoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function AnexoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/anexo');
    }

    AbstractRepository.extend(AnexoRepository);

    return new AnexoRepository();
}]);