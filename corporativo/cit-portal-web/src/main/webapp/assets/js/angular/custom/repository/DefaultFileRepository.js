'use strict';

citApp.factory('DefaultFileRepository', ['Restangular', 'AbstractRepository', function (restangular, AbstractRepository) {

    function DefaultFileRepository() {
    	this.saveConfig = function(defaultFiles) {
        	return this.restangular.all(this.route + "/saveConfig").post(defaultFiles);
        };
        
    	AbstractRepository.call(this, restangular, 'rest/defaultFile');
    }
    
    AbstractRepository.extend(DefaultFileRepository);
    
    return new DefaultFileRepository();
}]);