citApp.factory('DominioRepository', ['Restangular','AbstractRepository', function(restangular, AbstractRepository) {

	function DominioRepository(){
		AbstractRepository.call(this, restangular, 'rest/dominio');

		this.findAllDominio = function(params) {
			return restangular.one(this.route).getList("findAllDominio", {"chave": params}).then();
		};

		this.findAllChavesDominio = function(params) {
			return restangular.one('rest/dominio').getList("findAllChavesDominio").then();
		};

		this.findAllDominioByValue = function(params, value) {
			return restangular.one(this.route).getList("findAllDominioByValue", {"chave": params, "value": value}).then();
		};

		this.findAllDominioByCodigo = function(params, codigo) {
			return restangular.one(this.route + "/findAllDominioByCodigo").get({"chave": params, "codigo": codigo}).then();
		};

		this.buscaDominioByCodigo = function(codigo){
    		return 	restangular.one(this.route + "/buscaDominioByCodigo").get({"codigo": codigo}).then();
    	};
    	
    	this.buscaDominioByCodigoAndChave = function(codigo, chave){
    		return 	restangular.one(this.route + "/buscaDominioByCodigoAndChave").get({"chave": chave, "codigo": codigo}).then();
    	};

    	this.validaCodigoDominioPorChave = function(chave, codigo){
    		return 	restangular.one(this.route + "/validaCodigoDominioPorChave").get({"chave": chave, "codigo": codigo}).then();
    	};
	}

	AbstractRepository.extend(DominioRepository);

	return new DominioRepository();
}]);