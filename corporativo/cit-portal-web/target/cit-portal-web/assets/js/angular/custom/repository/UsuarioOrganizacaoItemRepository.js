'use strict';

citApp.factory('UsuarioOrganizacaoItemRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function UsuarioOrganizacaoItemRepository() {

    	AbstractRepository.call(this, restangular, 'rest/usuarioOrganizacaoItem');
    }

    AbstractRepository.extend(UsuarioOrganizacaoItemRepository);

    return new UsuarioOrganizacaoItemRepository();
}]);