'use strict';

citApp.factory('AbstractRepository', [function () {

    function AbstractRepository(restangular, route) {
        this.restangular = restangular;
        this.route = route;
    }

    AbstractRepository.prototype = {
        getList: function () {
        	return this.restangular.all(this.route).getList();
        },
        getListPage: function (params) {
            return this.restangular.all(this.route + '/getPage').post(params);
        },
        get: function (id) {
            return this.restangular.one(this.route, id).get();
        },
        getView: function (id) {
            return this.restangular.one(this.route, id).one(this.route + 'view').get();
        },
        save: function (resource) {
        	return resource.id ? this.update(resource) : this.create(resource);
        },
        update: function (updatedResource) {
            return this.restangular.all(this.route + '/update').post(updatedResource);
        },
        create: function (newResource) {
            return this.restangular.all(this.route).post(newResource);
        },
        remove: function (resource) {
        	return this.restangular.one(this.route, resource.id).remove();
        },
        removeAll: function (listIds) {
        	return this.restangular.all(this.route + "/removeAll").post(listIds);
        },
        findAutoComplete: function (chave, valor) {
        	return this.restangular.one(this.route + "/findAutoComplete").get({"chave": chave, "valor": valor});
        },
        existeVinculo: function (paramsGet) {
        	return this.restangular.one(this.route + "/existeVinculo").get(paramsGet);
        }
    };

    AbstractRepository.extend = function (repository) {
        repository.prototype = Object.create(AbstractRepository.prototype);
        repository.prototype.constructor = repository;
    };

    return AbstractRepository;
}]);