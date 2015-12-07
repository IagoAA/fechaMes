'use strict';

citApp.factory('TelefoneRepository', ['RestangularPatrimonio','AbstractRepository', function(restangularPatrimonio, AbstractRepository) {
	
	function TelefoneRepository(){
		AbstractRepository.call(this, restangularPatrimonio, 'rest/telefone');
	}
	
	AbstractRepository.extend(TelefoneRepository);
	
	return new TelefoneRepository();
}]);