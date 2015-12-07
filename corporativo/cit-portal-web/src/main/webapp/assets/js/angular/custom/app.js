'use strict';

var citAppDependencies = [
	'ngRoute',
	'ngSanitize',
	'ngAnimate',
	'restangular',
	'ui.bootstrap',
	'pascalprecht.translate',
	'ui.select',
	'angular-growl',
	'angularFileUpload',
	'ui.tree',
	'ui.utils',
	'lazy-tree',
	'ngCookies',
	'uiGmapgoogle-maps',
	'ui.utils.masks',
	'idf.br-filters',
	'duScroll',
	'textAngular',
	'adf',
	'adf.provider',
	'sample.widgets.news',
	'sample.widgets.weather',
	'cit.widgets.markdown',
	'sample.widgets.linklist',
	'cit.widgets.highchartcit',
	'cit.widgets.googlechartcit',
	'cit.widgets.googlechartgaugecit',
	'cit.widgets.bpetask',
	'googlechart',
	'highcharts-ng',
	'structures',
	'checklist-model',
	'ngClipboard'
];

var citApp = angular.module('citApp', citAppDependencies)
				.value('duScrollOffset', 145)
				.constant('ADMINISTRACAO_ID', 2)
				.constant('MENU_ID', 5)
				.value('adfTemplatePath', 'assets/js/angular/lib/dashboard-framework/templates/')
				.run(function ($rootScope, $http, MENU_ID, ADMINISTRACAO_ID, $timeout) {
					angular.element("#framePrincipal").remove();
					$rootScope.quantidadeNotificacoesNaoLidas = 0;
					$rootScope.MENU_ID = MENU_ID;
					$rootScope.ADMINISTRACAO_ID = ADMINISTRACAO_ID;

					angular.element('#loading-initial').remove();

					})

					.config(function ($routeProvider, RestangularProvider, $translateProvider, $translatePartialLoaderProvider, growlProvider, $httpProvider) {

						$httpProvider.interceptors.push(function($q) {
								return {
									'responseError': function(response) {
										if (response.status == 500) {
											// Handle 500 error code
											angular.element('#citapp-controller').scope().showAlert('error', "VC NAO TEM PERMISSAO");
											angular.element('#citapp-controller').scope().showException(response.data);
										}

										// Always reject (or resolve) the deferred you're given
										return $q.reject(response);
									}
								};
							});

						// GROWL CONFIGURAÇÕES
						growlProvider.globalDisableIcons(true);
						growlProvider.globalReversedOrder(true);
						growlProvider.globalTimeToLive({
							success: 10000,
							error: 10000,
							warning: 10000,
							info: 10000
						});

						// CONFIGURAÇÃO DO RESTANGULAR PARA URL BASE E SUFIX
						RestangularProvider.setBaseUrl('/cit-portal-web');
						RestangularProvider.setRequestSuffix('.json');

						// TRATAMENTO DE ERRO GENERICO
						RestangularProvider.setErrorInterceptor(function(response) {
							// angular.element('[ng-controller=AppController]').setLoading(false);
							// console.log(angular.element('#citapp-controller').scope());
							angular.element('#citapp-controller').scope().setLoading(false);
												if (response.status == 401) {
														console.log("Login required... ");
														$window.location.href='/j_spring_cas_security_logout';
												} else if (response.status == 404) {
														console.log("Resource not available...");
														angular.element('#citapp-controller').scope().showMensage('titulo', 'texto', 10000);
												} else if (response.status == 302) {
													console.log("Login required...");
													angular.element('#citapp-controller').scope().hide();
												} else {
													// $rootScope.setLoading(false);
													if(response.data && response.data.ex){
														if (response.data.ex.errosValidacao || response.data.ex.codigoErro) {

															console.log(angular.toJson(response.data.ex));

															angular.element('#citapp-controller').scope().showMensage(response.data.ex);

														}else{
															angular.element('#citapp-controller').scope().showException(response.data);
														}

													}
														console.log("Response received with HTTP error code: " + response.status );
												}

												return false; // stop the promise chain
								});

						// TRATAMENTO DE RESPOSTA, PARA PADRONIZAÇÃO DE RESPONSE
						RestangularProvider.setResponseExtractor(function(response, operation, what, url) {
							if(typeof response === 'string' && response.indexOf("login-page") > -1){
								angular.element('#citapp-controller').scope().setLoading(false);
								window.location.href='/cit-portal-web/j_spring_cas_security_logout';
							}
							var newResponse;
							if(response.payload){
									newResponse = response.payload;
							}else{
									newResponse = response;
							}

							if (angular.isArray(newResponse)) {
									angular.forEach(newResponse, function(value, key) {
										if (newResponse[key] != undefined && angular.isObject(newResponse[key])){
											newResponse[key].originalElement = angular.copy(value);
										}
									});
							} else {
								if (newResponse != undefined && angular.isObject(newResponse)) {
									newResponse.originalElement = angular.copy(newResponse);
									}
							}

							return newResponse;
							});

						$translateProvider.useLoader('$translatePartialLoader', {
									urlTemplate: 'assets/i18n/{lang}/{part}.json'
						});
						// e.g. index.json file should be under i18n/en-EN/index.json and page1.json should be  i18n/en-EN/index.json
						$translateProvider.preferredLanguage('pt_BR');
						$translatePartialLoaderProvider.addPart('portal');
});

angular.module('pascalprecht.translate')
.filter('translate', ['$parse', '$translate', function ($parse, $translate) {
	return function (translationId, interpolateParams, interpolation) {

		if (!angular.isObject(interpolateParams)) {
			interpolateParams = $parse(interpolateParams)(this);
		}

		return $translate.instant(translationId, interpolateParams, interpolation);
	};
}]);
