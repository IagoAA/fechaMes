'use strict';

citApp.filter('dateBR', ['$filter', function($filter) {
	return function (text) {
		return $filter('date')(text, "dd/MM/yyyy")
	};
}]);

citApp.filter('dateMesBR', ['$filter', function($filter) {
	return function (text) {
		return $filter('date')(text, "MMMM/yyyy")
	};
}]);

citApp.filter('booleanSimNao', ['$filter', '$translate', function($filter, $translate) {
	return function (text) {
		if(text === true || text === 'true'){
			return $translate.instant('LABEL.SIM');
		}else{
			return $translate.instant('LABEL.NAO');
		}
	};
}]);

citApp.filter('percentageList', ['$filter', function ($filter) {
	return function (input) {
		return $filter('number')(input * 100, 2) + '%';
	};
}]);

citApp.filter('idNotEqualBemPatrimonial', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].bemPatrimonial.id) {
			                blnPresente = true;
			            }
		  				if(result[i].idBemPatrimonialPrincipal && result[i].idBemPatrimonialPrincipal === array[j].bemPatrimonial.id){
		  					blnPresente = true;
		  				}
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualObj', [function() {
  return function(result, array, obj) {
      var arrayToReturn = [];

      if(array){

        for (var i=0; i<result.length; i++){

          var blnPresente = false;

          for (var j=0; j<array.length; j++){
            if (result[i].id === array[j][obj].id) {
                    blnPresente = true;
                }
          }

          if(!blnPresente){
            arrayToReturn.push(result[i]);
          }

        }

        return arrayToReturn;
      }else{
        return result;
      }
  };
}]);

citApp.filter('idNotEqual', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('tipoDadoCaracteristica', [function() {
	  return function(result) {
		  	var arrayToReturn = [];
	  		for (var i=0; i<result.length; i++){
	  			var blnPresente = false;
	  			if(result[i].codigo < 9){
	  				arrayToReturn.push(result[i]);
	  			}
	        }
	  		return arrayToReturn;
	  };
}]);

// Filter Dominio
citApp.filter('idNotEqualDominio', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

//Filter ClassificacaoMaterial
citApp.filter('idNotEqualClassificacaoMaterial', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].subGrupoFederalSupply.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualMaterial', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].materialConsumo.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualCaracteristica', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].caracteristica.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);


citApp.filter('idBemPrincipal', [function() {
	  return function(result, bemPatrimonialPrincipal) {
		  	var arrayToReturn = [];

		  	if(bemPatrimonialPrincipal){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			if (result[i].id === bemPatrimonialPrincipal.id) {
			           blnPresente = true;
			        }

		  		}

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotObject', [function() {
	  return function(result, object) {
		  	var arrayToReturn = [];

		  	if(object.id){
		  		for (var i=0; i<result.length; i++){

		  			if (result[i].id != object.id) {

		  				arrayToReturn.push(result[i]);
			          
			        }

		  		}

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('bemPatrimonialPrincipalEqualNull', [function() {
	  return function(result) {
		  	var arrayToReturn = [];

	  		for (var i=0; i<result.length; i++){

	  			if(result[i].idBemPatrimonialPrincipal === undefined){
	  				arrayToReturn.push(result[i]);
	  			}

	        }

	  		return arrayToReturn;
	  };
}]);


/**
 * Filtra Bem Patrimonial que ainda não foi definido para o colaborador
 */
citApp.filter('bemPatrimonialJaDefinido', [function() {
	  return function(result, bensVinculados) {

	  		for (var i=0; i<result.length; i++){
	  			for(var j=0; j<bensVinculados.length; j++){
	  				if(result[i].id === bensVinculados[j].id){
	  					result[i].$jaVinculado = true;
	  				}
	  			}

	        }

	  		return result;
	  };
}]);

/**
 * Formata um fator de porcentagem para seu número porcentual com a quantidade de casas decimais definida
 * Ex.: input: 0,2548 - decimals:2 - resultado: 25,48%
 */
citApp.filter('percentageFator', ['$filter', function ($filter) {
	return function (input, decimals) {
		return $filter('number')(input * 100, decimals) + '%';
	};
}]);

/**
 * Formata um número em porcentagem com a quantidade de casas decimais definida
 * Ex.: input: 25 - decimals:1 - resultado: 25,0%
 */
citApp.filter('percentage', ['$filter', function ($filter) {
	return function (input, decimals) {
		return $filter('number')(input, decimals) + '%';
	};
}]);

citApp.filter('dominioStatusDisponivelUtilizado', [function() {
	  return function(result) {
		  	var arrayToReturn = [];
		  	for (var i=0; i<result.length; i++){
		  		if (result[i].nome === 'DISPONIVEL' || result[i].nome === 'UTILIZADO') {
		  				arrayToReturn.push(result[i]);
			    }
		    }
		  	return arrayToReturn;
	  };
}]);

citApp.filter('filterStatusBaixa', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].nome === array[j]) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('itemOrganizacaoDistinct', [function() {
	  return function(result, organizacao) {
		  	var arrayToReturn = [];

		  	if(organizacao){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			if (result[i].organizacao.id !== organizacao.id) {
		  				arrayToReturn.push(result[i]);
			        }

		        }
		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualOrganizacao', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].organizacao.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualGrupoUsuarioSourcePickList', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].usuario.id === array[j].usuario.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualGrupoSourcePickList', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].grupo.id === array[j].grupo.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualPrivilegioSourcePickList', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].privilegio.id === array[j].privilegio.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualGrupoPrivilegioSourcePickList', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].privilegio.id === array[j].id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualUnidadeMedida', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].unidadeMedida.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualGrupo', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].grupo.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualUsuario', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].id === array[j].usuario.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('boolText', [function() {
	return function (boolValue) {
        if (boolValue === true)
            return "Sim";
        else
            return "Não";
    };
}]);

citApp.filter('propsFilter', [function() {
	return function(items, props) {
		var out = [];

		if (angular.isArray(items)) {
			items.forEach(function(item) {
				var itemMatches = false;

				var keys = Object.keys(props);
				for (var i = 0; i < keys.length; i++) {
					var prop = keys[i];
					var text = props[prop].toLowerCase();
					if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
						itemMatches = true;
						break;
					}
				}

				if (itemMatches) {
					out.push(item);
				}
			});
		} else {
			// Let the output be the input untouched
			out = items;
	    }

		return out;
	};
}]);

citApp.filter('idNotEqualModuloSourcePickList', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].modulo.id === array[j].modulo.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualNivelAutoridadeSourcePickList', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].nivelAutoridade.id === array[j].nivelAutoridade.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

citApp.filter('idNotEqualAlcadaSourcePickList', [function() {
	 return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].alcada.id === array[j].alcada.id) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);

/**
 * Atraves de um campo (field) como filtro este metodo retorna um array com objetos unicos
 */
citApp.filter('unique', [function() {

	return function(array, field) {
        var o = {};
        var i, l = array.length;
        var result = [];
        for (i = 0; i < l; i += 1) {
            o[array[i][field]] = array[i];
        }
        for (i in o) {
        	result.push(o[i]);
        }
        return result;
    };
}]);

/**
 * Atraves de um array de objetos com codigo retorna resut diferente do array
 */
citApp.filter('codigoNotEqualDominio', [function() {
	  return function(result, array) {
		  	var arrayToReturn = [];

		  	if(array && array.length > 0){
		  		for (var i=0; i<result.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<array.length; j++){
		  				if (result[i].codigo === array[j].codigo) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(result[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);


citApp.filter('itemArrayUnico', [function() {
	  return function(arrayString, arrayObjeto) {
		  	var arrayToReturn = [];

		  	if(arrayString && arrayObjeto){
		  		for (var i=0; i<arrayString.length; i++){

		  			var blnPresente = false;

		  			for (var j=0; j<arrayObjeto.length; j++){
		  				if (arrayString[i] === arrayObjeto[j].jsonPath) {
			                blnPresente = true;
			            }
		  			}

		  			if(!blnPresente){
		  				arrayToReturn.push(arrayString[i]);
		  			}

		        }

		  		return arrayToReturn;
		  	}else{
		  		return result;
		  	}
	  };
}]);
