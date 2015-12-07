citApp.factory('ParceiroRepository', ['RestangularTabelasCorp','AbstractRepository', function(restangularTabelasCorp, AbstractRepository) {
	
	function ParceiroRepository(){
		AbstractRepository.call(this, restangularTabelasCorp, 'rest/parceiro');
		
		this.findParceiros = function(tipoDominio, parceiros , value) {
			var objVH = {
				tipoDominio : tipoDominio,
				parceiros : parceiros,
				value : value
			};
			return restangularTabelasCorp.all(this.route + '/findParceiros').post(objVH).then();
		};
	}
	
	AbstractRepository.extend(ParceiroRepository);
	
	return new ParceiroRepository();
}]);