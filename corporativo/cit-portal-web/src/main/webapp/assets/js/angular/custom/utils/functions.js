function aplicarMascara(valor, mascara){
	var arrayMask = mascara.split("");
	var arrayValue = valor.toString().split("");
	var result = '';
	for(var i = 0; i <= arrayMask.length ; i++){
		if(i < arrayMask.length - arrayValue.length){
			result = result + "0";
		}else{
			result = result + valor;
			break;
		}
	}
	return result;
};

// serve para converter strings com o formato > mm/yyyy ou dd/mm/yyyy ou dd-mm-yyyy em date
function converterStringEmDate(str) {
	if (str) {
		if (str instanceof Date) {
			return str;
		} else {
			var day = null;
			var month = null;
			var year = null;

			if (str.length == 7) {
				day = "01";
			}
			var dataArray = str.split('/').reverse();
			if (dataArray.length == 1) {
				dataArray = str.split('-').reverse();
			}
			if (!day) {
				day = dataArray[2];
				month = dataArray[1] - 1;
				year = dataArray[0];
			} else {
				month = dataArray[1] - 1;
				year = dataArray[0];
			}
			return new Date(year, month, day);
		}
	}
}

// converte strings com o formato > yyyy/mm ou yyyy/mm/dd ou yyyy-mm-dd em date
function converterStringUSemDate(str) {
	if (str) {
		if (str instanceof Date) {
			return str;
		} else {
			var day = null;
			var month = null;
			var year = null;

			if (str.length == 7) {
				day = "01";
			}
			var dataArray = str.split('/');
			if (dataArray.length == 1) {
				dataArray = str.split('-');
			}
			if (!day) {
				day = dataArray[2];
				month = dataArray[1] - 1;
				year = dataArray[0];
			} else {
				month = dataArray[1] - 1;
				year = dataArray[0];
			}
			return new Date(year, month, day);
		}
	}
}

function converterMoedaToNumber(string) {
	if (typeof string === 'number') {
		return string;
	}
	try {
		return Number(string.replace(".", "").replace(",", ".").replace("R$",
				"").trim());
	} catch (e) {
		return null;
	}
};

var validaData = function(data) {
	if (Object.prototype.toString.call(data) !== "[object Date]") {
		if (!data || !data.match(/[0-9]/)
				|| !data.match(/[0-9]{2}\/[0-9]{2}\/[0-9]{4}/))
			return false;

		var dataArray = data.split('/');

		var day = dataArray[0], month = dataArray[1], year = dataArray[2];

		if (month < 1 || month > 12) {
			return false;
		}

		if (day < 1 || day > 31) {
			return false;
		}

		if ((month === 4 || month === 6 || month === 9 || month === 11)
				&& day === 31) {
			return false;
		}

		if (month == 2) {
			var isleap = false;
			if ((!(year % 4) && year % 100) || !(year % 400)) {
				isleap = true;
			}

			if (isleap && day > 29) {
				return false;
			}
		}

		if (year.length !== 4) {
			return false;
		}

		if (year < 1900 || year > 2099) {
			return false;
		}

		return true;
	}

	return !isNaN(data.getTime());
};

var validaDataMesAno = function(data) {
	if (Object.prototype.toString.call(data) !== "[object Date]") {
		if (!data || !data.match(/[0-9]/)
				|| !data.match(/[0-9]{2}\/[0-9]{4}/))
			return false;

		var dataArray = data.split('/');

		var month = dataArray[0], year = dataArray[1];

		if (month < 1 || month > 12) {
			return false;
		}

		if (year.length !== 4) {
			return false;
		}

		if (year < 1900 || year > 2099) {
			return false;
		}

		return true;
	}

	return !isNaN(data.getTime());
};


var validaDataMesAno = function(data) {
	if (Object.prototype.toString.call(data) !== "[object Date]") {
		if (!data || !data.match(/[0-9]/) || !data.match(/[0-9]{2}\/[0-9]{4}/))
			return false;

		var dataArray = data.split('/');

		var month = dataArray[0], year = dataArray[1];

		if (month < 1 || month > 12) {
			return false;
		}

		if (year.length !== 4) {
			return false;
		}

		if (year < 1900 || year > 2099) {
			return false;
		}

		return true;
	}

	return !isNaN(data.getTime());
};

function elementoClicavel(elemento) {
	elemento.onmouseover = function() {
		mouseOver(elemento);
	};

	elemento.onmouseout = function() {
		mouseOut(elemento);
	};
}

function mouseOver(elemento) {
	elemento.style.cursor = "pointer";
	elemento.style.background = "white";
}

function mouseOut(elemento) {
	elemento.style.background = "#eee";
}

function isDataIguais(data1, data2) {
	return (data1.getDay() == data2.getDay())
			&& (data1.getMonth() == data2.getMonth())
			&& (data1.getFullYear() == data2.getFullYear());
}

// VERIFICA SE A DATA INICIAL E INFERIOR OU IGUAL A DATA FINAL
function isPeriodoValido(dataInicial, dataFinal) {
	if (validaData(dataInicial) && validaData(dataFinal)) {
		try {
			dataInicio = angular.copy(converterStringEmDate(dataInicial));
			dataFim = angular.copy(converterStringEmDate(dataFinal));
		} catch (err) {
			dataInicio = angular.copy(dataInicial);
			dataFim = angular.copy(dataFinal);
		}
		return dataFim.getTime() >= dataInicio.getTime();
	} else
		return true;
}

//VERIFICA SE A DATA INICIAL E INFERIOR A DATA FINAL
function isPeriodoInferior(dataInicial, dataFinal) {
	if (validaData(dataInicial) && validaData(dataFinal)) {
		try {
			dataInicio = angular.copy(converterStringEmDate(dataInicial));
			dataFim = angular.copy(converterStringEmDate(dataFinal));
		} catch (err) {
			dataInicio = angular.copy(dataInicial);
			dataFim = angular.copy(dataFinal);
		}
		return dataFim.getTime() > dataInicio.getTime();
	} else
		return true;
}

// VERIFICA SE O PERIODO E INFERIOR A 12 MESES
function isPeriodoInferiorDozeMeses(dataInicial, dataFinal) {
	if (validaData(dataInicial) && validaData(dataFinal)) {
		try {
			dataInicio = angular.copy(converterStringEmDate(dataInicial));
			dataFim = angular.copy(converterStringEmDate(dataFinal));
		} catch (err) {
			dataInicio = angular.copy(dataInicial);
			dataFim = angular.copy(dataFinal);
		}
		return dataFim.getTime() - dataInicio.getTime() < 31536000000;
	} else
		return false;
}

function clone(obj) {
	var copy;

	// Handle the 3 simple types, and null or undefined
	if (null == obj || "object" != typeof obj)
		return obj;

	// Handle Date
	if (obj instanceof Date) {
		copy = new Date();
		copy.setTime(obj.getTime());
		return copy;
	}

	// Handle Array
	if (obj instanceof Array) {
		return obj.slice(0);
	}

	// Handle Object
	if (obj instanceof Object) {
		copy = {};
		for ( var attr in obj) {
			if (obj.hasOwnProperty(attr))
				copy[attr] = clone(obj[attr]);
		}
		return copy;
	}

	throw new Error("Unable to copy obj! Its type isn't supported.");
}

var isVazioCampo = function(cammpo) {
	if (!cammpo) {
		return true;
	}
	if (cammpo == null) {
		return true;
	}
	if (cammpo == '') {
		return true;
	}
	return false;
};

var getUuid = (function() {
	function s4() {
		return Math.floor((1 + Math.random()) * 0x10000).toString(16)
				.substring(1);
	}
	return function() {
		return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4()
				+ s4() + s4();
	};
})();

function acionarInputFile(componente) {

	//var res = componente.attributes[2].textContent.split("-");
	var res = componente.id.split("-");
	$('#uploadArquivos-' + res[1]).click();

}

// Método responsável por obter um elemento em uma lista por determinada chave e
// valor
function findAndReturnInList(array, chave, valor) {
	for (var i = 0; i < array.length; i = i + 1) {
		if (array[i][chave] == valor) {
			return array[i];
		}
	}
};

// Método responsável por remover um item de uma lista por determinada chave e
// valor
function findAndRemoveInList(array, key, value) {
	for (var i = 0; i < array.length; i = i + 1) {
		if (array[i][key] == value) {
			array.splice(i, 1);
			break;
		}
	}
};

/**
 * Funcao responsavel por montar o objeto Pagina a partir da Workspace
 */
function getMontarPagina(workspace) {
	return {
		nome : workspace.name,
		pagina : workspace.page
	};
};

/**
 * Obter o formato referência vigente Mês/Ano a partir de uma data
 *
 * @param referenciaVigente
 * @returns {String}
 */

function getReferenciaVigenteString(referenciaVigente) {

	meses = new Array("Janeiro", "Fevereiro", "Março", "Abril", "Maio",
			"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
			"Dezembro");
	mes = referenciaVigente.getMonth();
	ano = referenciaVigente.getFullYear();
	referenciaVigenteStr = meses[mes] + "/" + ano;

	return referenciaVigenteStr;
}

// ######################################################## ADOBE VERIFY CROSS
// ############################################################################
//
// http://thecodeabode.blogspot.com
// @author: Ben Kitzelman
// @license: FreeBSD: (http://opensource.org/licenses/BSD-2-Clause) Do whatever
// you like with it
// @updated: 03-03-2013
//
var getAcrobatInfo = function() {

	var getBrowserName = function() {
		return this.name = this.name
				|| function() {
					var userAgent = navigator ? navigator.userAgent
							.toLowerCase() : "other";

					if (userAgent.indexOf("chrome") > -1)
						return "chrome";
					else if (userAgent.indexOf("safari") > -1)
						return "safari";
					else if (userAgent.indexOf("msie") > -1)
						return "ie";
					else if (userAgent.indexOf("firefox") > -1)
						return "firefox";
					return userAgent;
				}();
	};

	var getActiveXObject = function(name) {
		try {
			return new ActiveXObject(name);
		} catch (e) {
		}
	};

	var getNavigatorPlugin = function(name) {
		for (key in navigator.plugins) {
			var plugin = navigator.plugins[key];
			if (plugin.name == name)
				return plugin;
		}
	};

	var getPDFPlugin = function() {
		return this.plugin = this.plugin || function() {
					if (getBrowserName() == 'ie') {
						//
						// load the activeX control
						// AcroPDF.PDF is used by version 7 and later
						// PDF.PdfCtrl is used by version 6 and earlier
						return getActiveXObject('AcroPDF.PDF')
								|| getActiveXObject('PDF.PdfCtrl');
					} else {
						return getNavigatorPlugin('Adobe Acrobat')
								|| getNavigatorPlugin('Chrome PDF Viewer')
								|| getNavigatorPlugin('WebKit built-in PDF');
					}
				}();
	};

	var isAcrobatInstalled = function() {
		return !!getPDFPlugin();
	};

	var getAcrobatVersion = function() {
		try {
			var plugin = getPDFPlugin();

			if (getBrowserName() == 'ie') {
				var versions = plugin.GetVersions().split(',');
				var latest = versions[0].split('=');
				return parseFloat(latest[1]);
			}

			if (plugin.version)
				return parseInt(plugin.version);
			return plugin.name;

		} catch (e) {
			return null;
		}
	};

	//
	// The returned object
	//
	return {
		browser : getBrowserName(),
		acrobat : isAcrobatInstalled() ? 'installed' : false,
		acrobatVersion : getAcrobatVersion()
	};
};
// ######################################################## FIM ADOBE VERIFY
// CROSS
// ############################################################################

var replaceSpecialChars = function(value) {
	var replaceAUpper = function (value) {
		return value.replace(/(\u00C0|\u00C1|\u00C2|\u00C3|\u00C4|\u00C5|\u00C6)/g, "A");
	};
	var replaceALower = function (value) {
		return value.replace(/(\u00E0|\u00E1|\u00E2|\u00E3|\u00E4|\u00E5|\u00E6)/g, "a");
	};

	var replaceEUpper = function (value) {
		return value.replace(/(\u00C8|\u00C9|\u00CA|\u00CB)/g, "E");
	};
	var replaceELower = function (value) {
		return value.replace(/(\u00E8|\u00E9|\u00EA|\u00EB)/g, "e");
	};

	var replaceIUpper = function (value) {
		return value.replace(/(\u00CC|\u00CD|\u00CE|\u00CF)/g, "I");
	};
	var replaceILower = function (value) {
		return value.replace(/(\u00EC|\u00ED|\u00EE|\u00EF)/g, "i");
	};

	var replaceOUpper = function (value) {
		return value.replace(/(\u00D2|\u00D3|\u00D4|\u00D5|\u00D6)/g, "O");
	};
	var replaceOLower = function (value) {
		return value.replace(/(\u00F2|\u00F3|\u00F4|\u00F5|\u00F6)/g, "o");
	};

	var replaceUUpper = function (value) {
		return value.replace(/(\u00D9|\u00DA|\u00DB|\u00DC)/g, "U");
	};
	var replaceULower = function (value) {
		return value.replace(/(\u00F9|\u00FA|\u00FB|\u00FC)/g, "u");
	};

	var replaceCedilUpper = function (value) {
		return value.replace(/(\u00C7)/g, "C");
	};
	var replaceCedilLower = function (value) {
		return value.replace(/(\u00E7)/g, "c");
	};

	value = replaceAUpper(value);
	value = replaceALower(value);

	value = replaceEUpper(value);
	value = replaceELower(value);

	value = replaceIUpper(value);
	value = replaceILower(value);

	value = replaceOUpper(value);
	value = replaceOLower(value);

	value = replaceUUpper(value);
	value = replaceULower(value);

	value = replaceCedilUpper(value);
	value = replaceCedilLower(value);

	return value;
};

function completarZerosEsquerda(conteudo, size) {
    var valor = conteudo;
    while (valor.toString().length < size) valor = "0" + valor;
    return valor;
};

function soNumeros(value) {
	var soNumeros = value.replace(/[^\d]+/g,'');
	return soNumeros;
};