'use strict';

citApp.factory('EstruturaOrganizacionalRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function EstruturaOrganizacionalRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/estruturaOrganizacional');

    	// Listar estruturas organizacionais por nome
    	this.listarEstruturasOrganizacionaisBusca = function(value) {
    		return restangularTabelasCorp.one(this.route).getList("listarEstruturasOrganizacionaisBusca", {nome : value}).then();
    	};

    	// Listar estruturas organizacionais por nome e órgão
    	this.listarEstruturasOrganizacionaisPorOrganizacao = function(value, idOrganizacao) {
    		return restangularTabelasCorp.one(this.route).getList("listarEstruturasOrganizacionaisPorOrganizacao", {nome : value, idOrganizacao : idOrganizacao}).then();
    	};
    			
    	// Listar estruturas organizacionais filhas e por órgão
    	this.listarEstruturasOrganizacionaisFilhasPorOrganizacao = function(value, idOrganizacao) {
    		return restangularTabelasCorp.one(this.route).getList("listarEstruturasOrganizacionaisFilhasPorOrganizacao", {nome : value, idOrganizacao : idOrganizacao}).then();
    	};

    	// busca todas as estruturas organizacionais nivel 0 (zero)
    	this.findParents = function(idOrganizacao) {
    		return restangularTabelasCorp.one(this.route).getList("findParents", {idOrganizacao : idOrganizacao}).then();
    	};

    	// Desativa a configuração almoxarifado = Estrutura almoxarifado
    	this.desativaConfiguracaoAlmoxarifado = function(idConfiguracaoAlmoxarifado) {
    		return this.restangular.all(this.route + '/desativaConfiguracaoAlmoxarifado').post(idConfiguracaoAlmoxarifado);
    	};

    	// Listar as estruturas na árvore por nome
    	this.listarEstruturasPorNome = function(buscaEstruturaNome) {
			return restangularTabelasCorp.one(this.route).getList("listarEstruturasPorNome" , {nome : buscaEstruturaNome}).then();
		};

		// Listar as estruturas na árvore pelo id do órgão do usuário, nome e datafim como filtro
    	this.listarEstruturasOrganizacionaisDaTree = function(idOrganizacao, nome, exibirEstruturasAtivas) {
			return restangularTabelasCorp.one(this.route).getList("listarEstruturasOrganizacionaisDaTree" , {idOrganizacao : idOrganizacao, nome : nome, exibirEstruturasAtivas : exibirEstruturasAtivas}).then();
		};

		// busca todas as estruturas organizacionais filhas do órgão com filtro datafim
    	this.findChildrens = function(idOrganizacao, exibirEstruturasAtivas) {
    		return restangularTabelasCorp.one(this.route).getList("findChildrens", {idOrganizacao : idOrganizacao, exibirEstruturasAtivas : exibirEstruturasAtivas}).then();
    	};

		// verifica se contem alguma entidade vinculada.
    	this.contemEntidadeVinculada = function(id) {
    		return restangularTabelasCorp.one(this.route + "/contemEntidadeVinculada").get({"id": id}).then();
    	};

		// Listar estruturas organizacionais
		this.listarEstruturasOrganizacionais = function(nome) {
			return restangularTabelasCorp.one(this.route).getList("listarEstruturasOrganizacionais", {nome : nome}).then();
		};

    	// Listar estruturas organizacionais por nome com Views mínimo
    	this.listarEstruturasOrganizacionaisBuscaSimples = function(value) {
    		return restangularTabelasCorp.one(this.route).getList("listarEstruturasOrganizacionaisBuscaSimples", {nome : value}).then();
    	};

		// Montar tree das estruturas a partir do órgão - inventário
    	this.montarTreeEstruturasInventario = function(idOrganizacao) {
			return restangularTabelasCorp.one(this.route+ "/montarTreeEstruturasInventario").get({idOrganizacao : idOrganizacao});
		};
			
		// Listar estruturas inventariaveis por nome
		this.findEstruturaOrganizacionalPorNomeInventario = function(nome, idEstruturas) {
			return restangularTabelasCorp.one(this.route).getList("findEstruturaOrganizacionalPorNomeInventario", {nome : nome, idEstruturas : idEstruturas}).then();
		};
		
    }

    AbstractRepository.extend(EstruturaOrganizacionalRepository);

    return new EstruturaOrganizacionalRepository();
}]);