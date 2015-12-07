'use strict';

citApp.factory('InternacionalizacaoRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function InternacionalizacaoRepository() {
    	AbstractRepository.call(this, restangular, 'rest/internacionalizacao');
    	
    	this.atualizarLabelSistema = function () {
    		return restangular.all(this.route + '/atualizarLabelSistema').post().then();
        };
    }

    AbstractRepository.extend(InternacionalizacaoRepository);

    return new InternacionalizacaoRepository();
}]);
