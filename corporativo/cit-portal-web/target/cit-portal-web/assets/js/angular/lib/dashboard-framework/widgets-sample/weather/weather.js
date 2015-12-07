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

angular.module('sample.widgets.weather', ['adf.provider'])
  .value('weatherServiceUrl', 'http://api.openweathermap.org/data/2.5/weather?units=metric&callback=JSON_CALLBACK&q=')
  .value('nameWidgetWeather', 'temperatura')
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('weather', {
        title: 'Temperatura',
        description: 'Mostra temperatura da cidade definida',
        templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets-sample/weather/weather.html',
        controller: 'weatherCtrl',
        reload: true,
        resolve: {
          data: function(weatcherService, config){
        	if(config.parametros && config.parametros[0]){
        		config.location = config.parametros[0].textoDefault;
        	}

            if (config.location){
              return weatcherService.get(config.location);
            }
          }
        },
        edit: {
        	 templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/edit.html'
        }
      });
  })
  .service('weatcherService', ['$q', '$http', 'weatherServiceUrl', 'nameWidgetWeather', function($q, $http, weatherServiceUrl, nameWidgetWeather){
    return {
      get: function(location){
        var deferred = $q.defer();
        var url = weatherServiceUrl + location;
        $http.jsonp(url)
          .success(function(data){
            if (data && data.cod === 200){
              deferred.resolve(data);
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
  .controller('weatherCtrl', ['$scope', 'data', 'WidgetRepository', '$timeout', 'nameWidgetWeather', 'config', 'DominioRepository', 'PrivilegioRepository', 'GrupoRepository', '$filter', function($scope, data, WidgetRepository, $timeout, nameWidgetWeather,  config, DominioRepository, PrivilegioRepository, GrupoRepository, $filter){

	  $scope.data = data;

	  if(config.path){
		  $timeout(function() {
			  $scope.$parent.model.config.location = config.path;
		  });
	  }

	    //Se o model não conter um widget, busca o widget referente ao nome.
		  if(!$scope.$parent.model.widget){

			  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetWeather).then(function(result){

				  $scope.$parent.model.widget = result.originalElement;

				  $scope.$parent.model.config.parametros = result.originalElement.parametros;

				  config.apresentarUrl = result.originalElement.apresentarUrlServico;

				  $scope.$parent.model.config.location = result.originalElement.urlServico;


			  });
		  };

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
