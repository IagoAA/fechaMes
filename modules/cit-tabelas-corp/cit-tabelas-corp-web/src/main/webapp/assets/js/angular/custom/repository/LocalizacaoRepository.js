'use strict';

citApp.factory('LocalizacaoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function LocalizacaoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/localizacao');
    	
    	// Listar localizacoes por nome
    	this.findLocalizacaoPorNome = function (value) {
    		return restangularTabelasCorp.one(this.route).getList("findLocalizacaoPorNome", {nome : value}).then();
    	};
    	
    	// Listar localizacoes por nome e organização
    	this.listarLocalizacaoPorOrganizacao = function (value, idOrganizacao) {
    		return restangularTabelasCorp.one(this.route).getList("listarLocalizacaoPorOrganizacao", {nome : value, idOrganizacao : idOrganizacao}).then();
    	};
    }
    
    AbstractRepository.extend(LocalizacaoRepository);
    
    return new LocalizacaoRepository();
}]);