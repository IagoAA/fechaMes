'use strict';

citApp.factory('EstruturaOrganizacionalResponsavelRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function EstruturaOrganizacionalResponsavelRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/estruturaOrganizacionalResponsavel');
    }

    AbstractRepository.extend(EstruturaOrganizacionalResponsavelRepository);

    return new EstruturaOrganizacionalResponsavelRepository();
}]);