'use strict';

citApp.factory('ComissaoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function ComissaoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/comissao');
    	
    	// Listar comissões por nome e órgão
    	this.listarComissaoPorNomeEOrganizacao = function(value, idOrganizacao) {
    		return restangularTabelasCorp.one(this.route).getList("listarComissaoPorNomeEOrganizacao", {nome : value, idOrganizacao : idOrganizacao}).then();
    	};
    	
    	// Listar comissões por nome/órgão/domínio
    	this.listarComissaoPorNomeEOrganizacaoEDominio = function(value, idOrganizacao, idDominio) {
    		return restangularTabelasCorp.one(this.route).getList("listarComissaoPorNomeEOrganizacaoEDominio", {nome : value, idOrganizacao : idOrganizacao, idDominio : idDominio}).then();
    	};
    }
    
    AbstractRepository.extend(ComissaoRepository);
    
    return new ComissaoRepository();
}]);