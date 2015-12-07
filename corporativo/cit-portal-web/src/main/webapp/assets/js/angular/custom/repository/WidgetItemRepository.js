'use strict';

citApp.factory('WidgetItemRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {
	
	function WidgetItemRepository(){
		AbstractRepository.call(this, restangular, 'rest/widgetItem');
	}
	
	AbstractRepository.extend(WidgetItemRepository);
	
	return new WidgetItemRepository();
}]);