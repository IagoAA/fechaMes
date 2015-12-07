'use strict';

citApp.factory('TelefoneRepository', ['RestangularTabelasCorp','AbstractRepository', function(restangularTabelasCorp, AbstractRepository) {
	
	function TelefoneRepository(){
		AbstractRepository.call(this, restangularTabelasCorp, 'rest/telefone');
	}
	
	AbstractRepository.extend(TelefoneRepository);
	
	return new TelefoneRepository();
}]);