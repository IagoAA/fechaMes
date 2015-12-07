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

angular.module('cit.widgets.googlechartgaugecit', ['adf.provider', 'googlechart'])
.value('nameWidgetGoogleChartGaugeCit', 'googlechartgaugecit')
  .config(function(dashboardProvider){
    // template object for github widgets
    var widget = {
      templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/google-chart-gauge/googlechartgauge.html',
      reload: true,
      resolve: {
        data: function(googleChartGaugeService, config){
        	if(config.path)
        		return googleChartGaugeService.get(config.path, config.tempoServico);
        }
      },
      edit: {
        templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/edit.html'
      }
    };

    // register github template by extending the template object
    dashboardProvider.widget('googlechartgaugecit', angular.extend({
        title: 'Google Chart Gauge',
        description: 'Google Chart Gauge',
        controller: 'googleChartGaugeCtrl'
    }, widget));

  })
  .service('googleChartGaugeService', function($q, $http, WidgetRepository, nameWidgetGoogleChartGaugeCit, $timeout){
    return {
      get: function(url, tempo){
	    var deferred = $q.defer();
			    $timeout(function() {
			    	$http.get(url)
			         .success(function(data){
			           if (data){
			        	   var dados = null;

			        	   if(data[0])
			        		   dados = JSON.parse(data[0]);
			        	   else
			        		   dados = data;

				           deferred.resolve(dados);
			           }
			          })
			          .error(function(){
			        	  deferred.reject();
			          });
				}, tempo);

        return deferred.promise;
      }
    };
  })
  .controller('googleChartGaugeCtrl', function($scope, config, data, $timeout, nameWidgetGoogleChartGaugeCit, WidgetRepository, DominioRepository, GrupoRepository, PrivilegioRepository, $filter){

	  // SE EXISTIR O PARAMETRO CONFIG.PATH MANTEM O PARAMETRO, SENÃO BUSCA A CONFIGURAÇÃO PADÃO DO WIDGET
	  if(config.path){
		  $timeout(function() {
			  $scope.$parent.model.urlServico = config.path;
		  });
	  }

	  $timeout(function() {

		  if(!$scope.$parent.model.widget){

			  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetGoogleChartGaugeCit).then(function(result){

				  $scope.$parent.model.widget = result.originalElement;

				  config.apresentarUrl = result.originalElement.apresentarUrlServico;

				  $scope.$parent.model.config.parametros = result.originalElement.parametros;

				  $scope.findDominioPainelItemParametro();

				if($scope.chart && $scope.chart.options){
					// PEGA AS CONFIGURAÇÕES DEFINIDAS NOS PARAMETROS
					$scope.chart.options.max = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "max"; })[0].numeroDefault;
					$scope.chart.options.yellowFrom = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "yellowFrom"; })[0].numeroDefault;
					$scope.chart.options.yellowTo = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "yellowTo"; })[0].numeroDefault;
					$scope.chart.options.redFrom = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "redFrom"; })[0].numeroDefault;
					$scope.chart.options.redTo = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "redTo"; })[0].numeroDefault;
					$scope.chart.options.greenFrom = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "greenFrom"; })[0].numeroDefault;
					$scope.chart.options.greenTo = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "greenTo"; })[0].numeroDefault;
					$scope.chart.options.minorTicks = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "minorTicks"; })[0].numeroDefault;
				}
			  });
		  }else{
			if($scope.chart && $scope.chart.options){
				// PEGA AS CONFIGURAÇÕES DEFINIDAS NOS PARAMETROS
				$scope.chart.options.max = $.grep(config.parametros, function(e){ return e.nome == "max"; })[0].numeroDefault;
				$scope.chart.options.greenFrom = $.grep(config.parametros, function(e){ return e.nome == "greenFrom"; })[0].numeroDefault;
				$scope.chart.options.greenTo = $.grep(config.parametros, function(e){ return e.nome == "greenTo"; })[0].numeroDefault;
				$scope.chart.options.yellowFrom = $.grep(config.parametros, function(e){ return e.nome == "yellowFrom"; })[0].numeroDefault;
				$scope.chart.options.yellowTo = $.grep(config.parametros, function(e){ return e.nome == "yellowTo"; })[0].numeroDefault;
				$scope.chart.options.redFrom = $.grep(config.parametros, function(e){ return e.nome == "redFrom"; })[0].numeroDefault;
				$scope.chart.options.redTo = $.grep(config.parametros, function(e){ return e.nome == "redTo"; })[0].numeroDefault;
				$scope.chart.options.minorTicks = $.grep(config.parametros, function(e){ return e.nome == "minorTicks"; })[0].numeroDefault;
			}
		  }
	  });

	// Método responsável por retornar  a lista de dominio através da chave selecionada
		$scope.findDominioPainelItemParametro = function(){

			if($scope.$parent.model.config.parametros){

				angular.forEach($scope.$parent.model.config.parametros, function (parametro) {

					if(parametro.tipoWidgetParametroDominio.codigo == 6){

						DominioRepository.findAllDominio(parametro.chaveDominioDefault).then(function(result) {

							config.listaDominioParametro = result;
						});

					}
				});

			}
		};

		$scope.findDominioPainelItemParametro();

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

	  if(data){
		// INICIALIZA AS CONFIGURAÇÕES DO GAUGE
		  $scope.chart = {};
		  $scope.chart.type = "Gauge";
		  $scope.chart.options = {
				  max: 0,
				  greenFrom: 0, greenTo: 0,
				  yellowFrom:0, yellowTo: 0,
				  redFrom: 0, redTo: 0,
				  minorTicks: 0
		  };

		  $scope.chart.data = [['Label', 'Value']];

		  for(var i = 0; i < data.length; i++){
			  $scope.chart.data.push([data[i].legenda, parseInt(data[i].serievalue)]);
		  }
	  }
  });
