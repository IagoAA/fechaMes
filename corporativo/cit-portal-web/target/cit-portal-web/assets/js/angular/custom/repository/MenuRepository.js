'use strict';

citApp.factory('MenuRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function MenuRepository() {
    	this.getAllParent = function () {
        	return this.restangular.one(this.route).getList("getAllParent").then();
        };
        
        this.getAllMenusAtivos = function () {
        	return this.restangular.one(this.route).getList("getAllMenusAtivos").then();
        };
        
        this.findMenuByIdParent = function (parent) {
        	return this.restangular.one(this.route).getList("findMenuByIdParent", {idParent: parent.id}).then();
        };
        
        this.saveOrdem = function (menuList) {
        	return this.restangular.all(this.route + "/saveOrdem").post(menuList);
        };
        
        this.findByName = function (value) {
        	return this.restangular.one(this.route).getList("findByName", {nome: value}).then();
        };
        
        this.obterPorNome = function (value) {
        	return this.restangular.one(this.route + "/obterPorNome").get({nome: value}).then();
        };
        
        
        this.buscarMenusPorPagina = function (page) {
        	return this.restangular.all(this.route + "/buscarMenusPorPagina").post(page);
        };
        
    	AbstractRepository.call(this, restangular, 'rest/menu');
    }
    
    AbstractRepository.extend(MenuRepository);
    
    return new MenuRepository();
}]);