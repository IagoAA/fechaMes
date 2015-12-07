'use strict';

citApp.factory('UsuarioPatrimonioRepository', ['RestangularPatrimonio', 'AbstractRepository', function (restangularPatrimonio, AbstractRepository) {

    function UsuarioPatrimonioRepository() {
		
    	this.atualizarOrganizacaoUsuario = function (organizacao) {
    		return restangularPatrimonio.all(this.route + '/atualizarOrganizacaoUsuario').post(organizacao).then();
        }
    	
    	AbstractRepository.call(this, restangularPatrimonio, 'rest/usuarioPatrimonio');
    }
    
    AbstractRepository.extend(UsuarioPatrimonioRepository);
    
    return new UsuarioPatrimonioRepository();
}]);