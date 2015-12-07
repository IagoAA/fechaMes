citApp.factory('PainelRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {
	

	function PainelRepository(){
		AbstractRepository.call(this, restangular, 'rest/painel');
		
		this.findPorUsuario = function() {
			return restangular.one(this.route).getList("findPorUsuario").then();
		};
		
		this.salvarDashBoardUsuario = function(painel) {
			return painel.usuario ? this.update(painel) : this.salvarDashBoard(painel);
		};
		
		this.salvarDashBoard = function(painel) {
			return restangular.all(this.route + '/salvarDashBoard').post(painel).then();
		};
		
		this.findPainelDashBoard = function(id) {
			return this.restangular.one(this.route, "findPainelDashBoard").get({"idPainel": id});
		};
		
	};
	
	AbstractRepository.extend(PainelRepository);

	return new PainelRepository();
}]);