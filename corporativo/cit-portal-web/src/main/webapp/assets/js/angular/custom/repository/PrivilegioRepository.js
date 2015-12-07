citApp.factory('PrivilegioRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {
	

	function PrivilegioRepository(){
		AbstractRepository.call(this, restangular, 'rest/privilegio');
	};
	
	AbstractRepository.extend(PrivilegioRepository);

	return new PrivilegioRepository();
}]);