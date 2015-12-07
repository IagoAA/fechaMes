'use strict';

citApp.factory('WidgetRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {
	
	function WidgetRepository(){
		AbstractRepository.call(this, restangular, 'rest/widget');
		
		this.buscaWidgetByNomeTipo = function(nomeTipo){
    		return 	restangular.one(this.route + "/buscaWidgetByNomeTipo").get({"nomeTipo": nomeTipo}).then();
    	};
		
	}
	
	AbstractRepository.extend(WidgetRepository);
	
	return new WidgetRepository();
}]);