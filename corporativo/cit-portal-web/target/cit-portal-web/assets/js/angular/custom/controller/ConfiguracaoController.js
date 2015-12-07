'use strict';

citApp.controller('ConfiguracaoController', ['$scope', '$translate', 'ConfiguracaoRepository', 'FileUploader', '$window', '$timeout', 'ModuloRepository',
                                             'DominioRepository', '$injector', function ConfiguracaoController($scope, $translate, ConfiguracaoRepository,
                                            		 FileUploader, $window, $timeout, ModuloRepository, DominioRepository, $injector) {
	$scope.configuracao = {};
	$scope.parametro = {};
	$scope.configuracao.parametros = [];
	$scope.uploaderLogoSalvo = false;
	$scope.almoxarifadoAtivo = $scope.isModuloAtivo("/cit-almoxarifado-web");
	$scope.patrimonioAtivo = $scope.isModuloAtivo("/citgrp-patrimonio-web");
	$scope.admMateriaisAtivo = $scope.isModuloAtivo("/cit-adm-materiais-web");

	$scope.mascara = {};
	
	//Obter os dominioPessoa
	DominioRepository.findAllDominio('tipoMascara').then(function(result) {
		$scope.radioValueList = result;
	});
	
	ModuloRepository.obterModulosAtivos().then(function(result) {
    	$scope.mondulosAtivos = result;
    });

	// ESTA VARIAVEL FOI CRIADA APENAS PARA FACILITAR O ENVIO DE PARAMETROS JÁ SALVOS
	$scope.parametros = {};
	
	// PEGA A CONFIGURACAO DO SISTEMA
	var getConfiguracao = function () {
		ConfiguracaoRepository.getConfiguracao($scope.usuarioLogado.organizacao.id).then(function (result) {
			$scope.configuracao = result.originalElement;
			if ($scope.configuracao.id == undefined) {
				$scope.configuracao = {};
				ConfiguracaoRepository.save($scope.configuracao).then(function(result){
					$scope.configuracao = result.originalElement;
					vincularIdConfiguracaoUploaderLogo();
				});
			} else {
				vincularIdConfiguracaoUploaderLogo();
			}
			setParametroScope($scope.configuracao.parametros);
		});
	};
	
	var getContaAlmoxarifado = function(id) {
		if($scope.admMateriaisAtivo){
			var restAngular = $injector.get(["ContaContabilRepository"]);
			restAngular.get(id).then(function(result){
				$scope.contaAlmoxarifado = result.originalElement;
			});
			var restContaMovimento = $injector.get(["ContaContabilMovimentoRepository"]);
			restContaMovimento.existeVinculo({"joinClass": 'contaContabil.id', "id": id}).then(function(result) {
				$timeout(function() {
					if (result) {
						$scope.contaAlmoxarifadoEmUso = true;
					}
				});
			});
		}
	};

	var setParametroScope = function (parametros) {
		if(parametros) {
			var parametro;
			for(var i = 0; i < parametros.length; i++) {
				parametro = parametros[i];
				$scope.parametro[parametro.chave] = parametro.valor;
				$scope.parametros[parametro.chave] = parametro;
				if(parametro.chave == "CONTA_CONTABIL_ALMOXARIFADO" && $scope.admMateriaisAtivo) {
					getContaAlmoxarifado(Number(parametro.valor));
					$scope.defineContaAlmox(Number(parametro.valor));
				}
			}
		}
	};

	var setParametrosConfiguracaoSistema = function (parametros) {
		$scope.configuracao.parametros = [];

		for(var chave in parametros) {
    		if(parametros.hasOwnProperty(chave)) {
    			var parametro = {};

    			if($scope.parametros[chave]) {
    				parametro = $scope.parametros[chave];
    			} else {
    				parametro.chave = chave;
    			}

    			parametro.valor = parametros[chave];

    			$scope.configuracao.parametros.push(parametro);
    		}
		}
	};

	// INSTANCIA DO OBJETO PARA FAZER UPLOAD
	var uploader = $scope.uploader = new FileUploader({
		autoUpload: false,
		queueLimit: 1,
		url : '/cit-portal-web/rest/configuracao/uploadFile'
	});

	// FILTERS
	uploader.filters.push({
		name: 'imageFilter',
		fn: function(item /*{File|FileLikeObject}*/, options) {
			var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
			return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
		}
	});

	// CALLBACKS
    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
    };

    // SALVA CONFIGURACAO PAGINA LOGIN
    $scope.saveOrUpdateConfigPaginaLogin = function () {
    	uploader.uploadAll();
    };

    // SALVA PARAMETROS DO SISTEMA
    $scope.saveOrUpdateParametros = function () {
    	$scope.setLoading(true);

    	setParametrosConfiguracaoSistema($scope.parametro);

    	ConfiguracaoRepository.save($scope.configuracao).then(function (result) {
    		$scope.showAlert('success', $translate.instant('MSG.PARAMETRO_SALVO_COM_SUCESSO'));

    		if(uploaderLogo.queue.length > 0){
    			uploaderLogo.uploadAll();
    		}

    		$scope.setLoading(false);

    		$timeout(function(){
    			getConfiguracao();
    		},2000);
    	});
    };
    
    // SALVA PARAMETROS GERAIS DO SISTEMA
    $scope.saveOrUpdateParametrosGeral = function () {
    	if($scope.mascara.tamanhoSequencial < 5) {
    		$scope.showAlert('error', $translate.instant('MSG.INFO_TAMANHO_MASCARA_OPERACAO'));
    		return;
    	} else {
    		$scope.saveOrUpdateParametros();
    	}
    };

    getConfiguracao();

    function vincularIdConfiguracaoUploaderLogo(){
    	if(!$scope.uploaderLogoSalvo &&  $scope.configuracao.id !== undefined){
    		uploaderLogo.url += $scope.configuracao.id;
    		$scope.uploaderLogoSalvo = !$scope.uploaderLogoSalvo;
    	}
    }

    // INSTANCIA DO OBJETO PARA FAZER UPLOAD
	var uploaderLogo = $scope.uploaderLogo = new FileUploader({
		autoUpload: false,
		queueLimit: 1,
		url : '/cit-portal-web/rest/configuracao/uploadFileLogo?idOrganizacao=' + $scope.usuarioLogado.organizacao.id + '&idConfiguracao='
	});

	// FILTERS
	uploaderLogo.filters.push({
		name: 'imageFilter',
		fn: function(item /*{File|FileLikeObject}*/, options) {
			var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
			return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
		}
	});

	// CALLBACKS
	uploaderLogo.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
		uploaderLogo.clearQueue();
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploaderLogo.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploaderLogo.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploaderLogo.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploaderLogo.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploaderLogo.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploaderLogo.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploaderLogo.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploaderLogo.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploaderLogo.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploaderLogo.onCompleteAll = function() {
    	uploaderLogo.clearQueue();
    	getConfiguracao();
        console.info('onCompleteAll');
    };
	
	// CONSTANTES DOMINIO
	var TIPO_MATERIAL = 'tipoMaterial';
	var CODIGO_MATERIAL_PERMANENTE = 2;
	DominioRepository.findAllDominioByCodigo(TIPO_MATERIAL, CODIGO_MATERIAL_PERMANENTE).then(function(result) {
		$scope.dominioTipoMaterialPermanente = result.originalElement;
	});
	
	// listar contas para selecionar de almoxarifado
	$scope.findContaAlmoxarifado = function(value){
		if($scope.isModuloAtivo("/cit-adm-materiais-web")){
			var restAngular = $injector.get(["ContaContabilRepository"]);
			return restAngular.findContaContabilPorOrganizacaoEDominioSemUsoMaterial(value, $scope.usuarioLogado.organizacao.id, $scope.dominioTipoMaterialPermanente.id).then(function(result){
				return result;
			});
		}
	};
	
	// Watch para definir conta almoxarifado nos parametros
	$scope.defineContaAlmox = function(contaId) {	
		$scope.parametro['CONTA_CONTABIL_ALMOXARIFADO'] = "" + contaId;	
	};
	
	$scope.$watch('almoxarifadoAtivo', function() {
		if ($scope.almoxarifadoAtivo) {
			DominioRepository.findAllDominio('tipoAvaliacaoMonetariaEstoque').then(function(result) {//Retorna todos os tipos de avaliação monetária de estoque implementadas no sistema.
	    		$scope.dominiosTipoAvaliacaoMonetariaEstoque = result;
	    	});
		}
	});

	$scope.configurarMascara = function () {
    	if ($scope.mascara.dominioTipoMascara && $scope.mascara.tamanhoSequencial > 0) {
    		if ($scope.mascara.dominioTipoMascara.codigo == 1) {
    			$scope.parametro.MASCARA_NUMERO_IDENTIFICACAO = montarMascara(9, $scope.mascara.tamanhoSequencial);
    		}
    		
    		if ($scope.mascara.dominioTipoMascara.codigo == 2) {
    			$scope.parametro.MASCARA_NUMERO_IDENTIFICACAO = "AAAA" + montarMascara(9, $scope.mascara.tamanhoSequencial - 4);
    		}
    	}
    };

    function montarMascara(conteudo, size) {
        var valor = "";
        while (valor.length < size) valor = conteudo + valor;
        return valor;
    }

}]);