'use strict';

citApp.factory('PaginaUsuarioRepository', [ 'Restangular', 'AbstractRepository', function(restangular, AbstractRepository) {

	function PaginaUsuarioRepository() {
		this.saveFavorito = function(pagina) {
			return this.restangular.all(this.route + '/saveFavorito').post(pagina);
		};

		this.salvarFilter = function(VHFilterCriteriaPagina){
			return this.restangular.all(this.route + '/saveFilter').post(VHFilterCriteriaPagina);
		};

		this.removeFavoritoPaginaUsuario = function(idPaginaUsuario){
			return this.restangular.all(this.route + '/removeFavoritoPaginaUsuario').post(idPaginaUsuario);
		};

		this.favoritarPaginaUsuario = function(idPaginaUsuario) {
			return this.restangular.all(this.route + '/favoritarPaginaUsuario').post(idPaginaUsuario);
		};

		this.findAjuda = function(idPagina) {
			return this.restangular.all(this.route + '/findAjudaPagina').post(idPagina);
		};

		this.findPorIdUsuario = function(idUsuario) {
			return this.restangular.all(this.route + '/findPorIdUsuario').post(idUsuario);
		};

		AbstractRepository.call(this, restangular, 'rest/paginaUsuario');
	}

	AbstractRepository.extend(PaginaUsuarioRepository);

	return new PaginaUsuarioRepository();
} ]);