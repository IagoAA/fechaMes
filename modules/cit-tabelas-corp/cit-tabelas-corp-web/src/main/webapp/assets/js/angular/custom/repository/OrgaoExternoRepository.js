'use strict';

citApp.factory('OrgaoExternoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function OrgaoExternoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/orgaoExterno');
    }

    AbstractRepository.extend(OrgaoExternoRepository);

    return new OrgaoExternoRepository();
}]);