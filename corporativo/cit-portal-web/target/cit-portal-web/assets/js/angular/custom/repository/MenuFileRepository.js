'use strict';

citApp.factory('MenuFileRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function MenuFileRepository() {
    	this.findByIdMenu = function (menu) {
    		return this.restangular.one(this.route).getList("findByIdMenu", {idMenu: menu.id}).then();
    	};
    	
    	AbstractRepository.call(this, restangular, 'rest/menuFile');
    }
    
    AbstractRepository.extend(MenuFileRepository);
    
    return new MenuFileRepository();
}]);