'use strict';

citApp.factory('IndexRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function IndexRepository() {

    	AbstractRepository.call(this, restangular, '');

		// Responsavel por execultar rest que carrega informacoes do sistema
    	this.getInformacaoSistema = function() {

			return restangular.one(this.route+ "/getInformacaoSistema").get().then();

		};

    }

    AbstractRepository.extend(IndexRepository);

    return new IndexRepository();
}]);