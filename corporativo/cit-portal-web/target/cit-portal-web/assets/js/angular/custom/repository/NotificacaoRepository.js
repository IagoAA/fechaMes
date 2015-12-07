'use strict';

citApp.factory('NotificacaoRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {
	
	function NotificacaoRepository(){
		AbstractRepository.call(this, restangular, 'rest/notificacao');
		
		this.findNotificacaoUsuario = function() {
			return restangular.one('rest/notificacao').getList("findNotificacaoUsuario", { }).then();
		};
		
		this.visualizar = function(notificacao) {
			return restangular.all(this.route + '/visualizar').post(notificacao).then();
        };
	}
	
	AbstractRepository.extend(NotificacaoRepository);
	
	return new NotificacaoRepository();
}]);