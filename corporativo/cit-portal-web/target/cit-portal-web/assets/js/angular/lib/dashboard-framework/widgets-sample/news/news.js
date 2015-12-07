/*
 * The MIT License
 *
 * Copyright (c) 2015, Sebastian Sdorra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

'use strict';

angular.module('sample.widgets.news', ['adf.provider'])
  .value('newsServiceUrl', 'https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&callback=JSON_CALLBACK&q=')
  .value('nameWidgetNoticia', 'noticia')
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('news', {
        title: 'Noticias',
        description: 'Mostra RSS/Atom feed',
        templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets-sample/news/news.html',
        controller: 'newsCtrl',
        resolve: {
          feed: function(newsService, config){
            if (config.path){
              return newsService.get(config.path);
            }
          }
        },
        edit: {
        	templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/edit.html'
        }
      });
  })
  .service('newsService', ['$q', '$http', 'newsServiceUrl', 'nameWidgetNoticia', function($q, $http, newsServiceUrl, nameWidgetNoticia){
    return {
      get: function(url){
        var deferred = $q.defer();
        $http.jsonp(newsServiceUrl + encodeURIComponent(url))
          .success(function(data){
            if (data && data.responseData && data.responseData.feed){
              deferred.resolve(data.responseData.feed);
            } else {
              deferred.reject();
            }
          })
          .error(function(){
            deferred.reject();
          });
        return deferred.promise;
      }
    };
  }])
  .controller('newsCtrl', ['$scope', 'feed', 'nameWidgetNoticia', 'WidgetRepository', '$timeout', 'config', 'DominioRepository', 'PrivilegioRepository', 'GrupoRepository', '$filter', function($scope, feed, nameWidgetNoticia, WidgetRepository, $timeout, config, DominioRepository, PrivilegioRepository, GrupoRepository, $filter){
    $scope.feed = feed;

	  if(config.path){
		  $timeout(function() {
			  $scope.$parent.model.config.url = config.path;
		  });
	  }

	  $timeout(function() {
		  //Se o model não conter um widget, busca o widget referente ao nome.
		  if(!$scope.$parent.model.widget){

			  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetNoticia).then(function(result){

				  $scope.$parent.model.widget = result.originalElement;

				  config.apresentarUrl = result.originalElement.apresentarUrlServico;

			  });
		  }
	  });

		//Verifica se a listaDominiotempoAtualizacao está null ou vazia, caso esteja busca os dominios e seta no config
		if(!config.listaDominioTempoAtualizacao){

			DominioRepository.findAllDominio("tipoTempoAtualizacao").then(function(result) {

				config.listaDominioTempoAtualizacao = result;
			});

		}



		//Verifica se o widgetGrupos é null
		if(!config.widgetGrupos){

			config.widgetGrupos = [];

		}

		if(!config.widgetPrivilegios){

			config.widgetPrivilegios = [];
		}

	//Método responsável por montar a lista de grupo
	$scope.findGrupoSource = function(){

		config.grupoSource = [];

		  GrupoRepository.getList().then(function(result) {

		   //Percorre a lista de grupo para montar a lista grupoUsuario
		   result.forEach(function (grupo, index) {

				 var widgetGrupo = {};

				 widgetGrupo.grupo = grupo.originalElement;

				 config.grupoSource.push(widgetGrupo);
			 });

			$timeout(function(){
				config.grupoSource =  $filter('idNotEqualGrupoSourcePickList')(config.grupoSource, config.widgetGrupos);
			});

		   });

	  };

	//Método responsável por montar a lista de privilegio
	$scope.findPrivilegioSource = function(){

		config.privilegioSource = [];

		  PrivilegioRepository.getList().then(function(result) {

		   //Percorre a lista de grupo para montar a lista grupoUsuario
		   result.forEach(function (privilegio, index) {

				 var widgetPrivilegio = {};

				 widgetPrivilegio.privilegio = privilegio.originalElement;

				 config.privilegioSource.push(widgetPrivilegio);
			 });

			$timeout(function(){
				config.privilegioSource =  $filter('idNotEqualPrivilegioSourcePickList')(config.privilegioSource, config.widgetPrivilegios);
			});

		   });

	  };

	$scope.findPrivilegioSource();

	$scope.findGrupoSource();




  }]);
