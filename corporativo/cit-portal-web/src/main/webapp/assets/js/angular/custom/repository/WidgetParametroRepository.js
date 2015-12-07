'use strict';

citApp.factory('WidgetParametroRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {
	
	function WidgetParametroRepository(){
		AbstractRepository.call(this, restangular, 'rest/widgetParametro');
	}
	
	AbstractRepository.extend(WidgetParametroRepository);
	
	return new WidgetParametroRepository();
}]);