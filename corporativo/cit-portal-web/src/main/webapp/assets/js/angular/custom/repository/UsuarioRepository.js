'use strict';

citApp.factory('UsuarioRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function UsuarioRepository() {

		this.buscaUsuarioByUsername = function(username){
    		return restangular.one(this.route + "/buscaUsuarioByUsername").get({"username": username}).then();
    	};

		this.buscaUsuarioByEmail = function(email){
    		return restangular.one(this.route + "/buscaUsuarioByEmail").get({"email": email}).then();
    	};

    	this.getUsuarioLogado = function(){
    		return restangular.one(this.route + "/getUsuarioLogado").get();
    	};

    	this.atualizarOrganizacaoUsuario = function (organizacao) {
    		return restangular.all(this.route + '/atualizarOrganizacaoUsuario').post(organizacao).then();
        };

        this.getModulos = function () {
    		return restangular.one('/modulos').get().then();
        };

        this.atualizarUsuario = function (restAngularModulo, organizacao) {
    		return restAngularModulo.all('rest/usuarioModulo/atualizarUsuario').post(organizacao).then();
        };
        
    	this.quantidadeUsuarioPorGrupo = function(value){
    		return restangular.one(this.route + "/quantidadeUsuarioPorGrupo").get({"idGrupo" : value}).then();
    	};

		this.findByUsername = function(username) {
			return restangular.one('rest/usuario').getList("findByUsername", {"username": username}).then();
		};

	 	AbstractRepository.call(this, restangular, 'rest/usuario');
    }

    AbstractRepository.extend(UsuarioRepository);

    return new UsuarioRepository();
}]);