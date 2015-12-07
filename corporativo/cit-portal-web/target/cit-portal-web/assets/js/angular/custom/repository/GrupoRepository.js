citApp.factory('GrupoRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {
	

	function GrupoRepository(){
		AbstractRepository.call(this, restangular, 'rest/grupo');
	};
	
	AbstractRepository.extend(GrupoRepository);

	return new GrupoRepository();
}]);