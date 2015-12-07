'use strict';

citApp.factory('NivelAutoridadeRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function NivelAutoridadeRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/nivelAutoridade');
    }

    AbstractRepository.extend(NivelAutoridadeRepository);

    return new NivelAutoridadeRepository();
}]);
