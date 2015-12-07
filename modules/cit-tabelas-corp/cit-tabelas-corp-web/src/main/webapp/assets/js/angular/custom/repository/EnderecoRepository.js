'use strict';

citApp.factory('EnderecoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function EnderecoRepository() {
    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/endereco');
    	
		this.listarEndereco = function(nome) {
			return restangularTabelasCorp.one('rest/endereco').getList("listarEndereco", {"nome": nome}).then();
			
		};
    }
    
    AbstractRepository.extend(EnderecoRepository);
    
    return new EnderecoRepository();
}]);