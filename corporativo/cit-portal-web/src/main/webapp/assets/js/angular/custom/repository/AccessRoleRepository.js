'use strict';

citApp.factory('AccessRoleRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function AccessRoleRepository() {
    	AbstractRepository.call(this, restangular, 'rest/accessRole');
    }
    
    AbstractRepository.extend(AccessRoleRepository);
    
    return new AccessRoleRepository();
}]);