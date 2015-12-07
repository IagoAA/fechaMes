'use strict';

citApp.factory('FavoritoRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function FavoritoRepository() {
    	// REMOVE UM FAVORITO POR PAGINAUSUARIO
    	this.removeFavorito = function(paginaUsuario) {
			return this.restangular.all(this.route + '/removeFavorito').post(paginaUsuario);
		};
    	
    	AbstractRepository.call(this, restangular, 'rest/favorito');
    }
    
    AbstractRepository.extend(FavoritoRepository);
    
    return new FavoritoRepository();
}]);