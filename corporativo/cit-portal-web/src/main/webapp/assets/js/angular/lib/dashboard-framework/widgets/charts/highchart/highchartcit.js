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

angular.module('cit.widgets.highchartcit', ['adf.provider', 'highcharts-ng'])
.value('nameWidgetHighChartCit', 'highchartcit')
  .config(function(dashboardProvider){
    // template object for github widgets
    var widget = {
      templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/highchart/highchart.html',
      reload: true,
      resolve: {
        data: function(highChartCitService, config){
        	if(config.path)
        		return highChartCitService.get(config.path, config.tempoServico);
        }
      },
      edit: {
        templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/edit.html'
      }
    };

    // register github template by extending the template object
    dashboardProvider.widget('highchartcit', angular.extend({
        title: 'Highcharts',
        description: 'Highcharts',
        controller: 'highchartCtrl'
        }, widget));

  })
  .service('highChartCitService', function($q, $http, WidgetRepository, nameWidgetHighChartCit, $timeout){
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
  .controller('highchartCtrl', function($scope, config, data, $timeout, nameWidgetHighChartCit, WidgetRepository, DominioRepository, PrivilegioRepository, GrupoRepository, $filter){

	  // SE EXISTIR O PARAMETRO CONFIG.PATH MANTEM O PARAMETRO, SENÃO BUSCA A CONFIGURAÇÃO PADÃO DO WIDGET
	  if(config.path){
		  $timeout(function() {
			  $scope.$parent.model.urlServico = config.path;
		  });
	  }

	  $timeout(function() {
		  //Se o model não conter um widget, busca o widget referente ao nome.
		  if(!$scope.$parent.model.widget){

			  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetHighChartCit).then(function(result){

				  $scope.$parent.model.widget = result.originalElement;

				  config.apresentarUrl = result.originalElement.apresentarUrlServico;

				  $scope.$parent.model.config.parametros = result.originalElement.parametros;

				  $scope.findDominioPainelItemParametro();
			  });
		  }
	  });

	// Método responsável por retornar  a lista de dominio através da chave selecionada
	$scope.findDominioPainelItemParametro = function(){
		//Verifica se o model contem os parametros, se contem percorre os parametros para montar a lista de dominio
		if($scope.$parent.model.config.parametros){

			angular.forEach($scope.$parent.model.config.parametros, function (parametro) {
				//Verifica se o tipoWidgetParametroDominio é do tipo tabelaDeDominio
				if(parametro.tipoWidgetParametroDominio.codigo == 6){

					DominioRepository.findAllDominio(parametro.chaveDominioDefault).then(function(result) {

						config.listaDominioParametro = result;

					});

				}
			});

		}
	};

	if(!config.listaDominioParametro){

		$scope.findDominioPainelItemParametro();
	}
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

    if ( data ){

	  var seriesData = [];

	  var categories = [];

	    if (seriesData.length > 0){
	      seriesData.sort(function(a, b){
	        return b[1] - a[1];
	      });
	      var s = seriesData[0];
	      seriesData[0] = {
	        name: s[0],
	        y: s[1],
	        sliced: true,
	        selected: true
	      };
	    }

      // BUSCA O TIPO DE GRÁFICO SELECIONADO

    	$scope.tipoGrafico = $.grep(config.parametros, function(e){ return e.nome == "Tipo Grafico"; })[0].dominioDefault.nome;
    	var titulo = $.grep(config.parametros, function(e){ return e.nome == "titulo"; })[0].textoDefault;
    	var subTitulo = $.grep(config.parametros, function(e){ return e.nome == "sub titulo"; })[0].textoDefault;


  	//Verifica se contem a legenda na cols
      $scope.obterIndexLegenda =  function(legenda){

  		var index =  null;
  		angular.forEach(seriesData, function(item) {

  			if(legenda == item.name){

  				index = item.index;
  			}
  		});

  		return index;
  	};

  	//Método responsável por iniciar o chart
  	$scope.iniciarChart = function(){

  	    if($scope.tipoGrafico === 'pie'){

  	    	$scope.montarChartPizza();
        }else{

        	$scope.montarCharts();
      }

  	};

  	//Método responsável por montar os dados do chart de pizza/pier
  	$scope.montarChartPizza = function(){

  		seriesData = [];

  	    angular.forEach(data, function(dados){
  	      seriesData.push([dados.legenda, parseInt(dados.serievalue)]);
  	    });

    	  $scope.chartConfig = {
  		        chart: {
  		          plotBackgroundColor: null,
  		          plotBorderWidth: null,
  		          plotShadow: false
  		        },
  		        title: {
  		          text: titulo
  		        },
  		        subtitle: {
  		            text: subTitulo
  		        },
  		        tooltip: {
  		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
  		        },
  		      plotOptions: {
  	            pie: {
  	                allowPointSelect: true,
  	                cursor: 'pointer',
  	                dataLabels: {
  	                    enabled: true,
  	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
  	                    style: {
  	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
  	                    }
  	                }
  	            }
  		      	},
  		        series: [{
  		          type: 'pie',
  		          name: data[0].categoria,
  		          data: seriesData
  		        }]
  		      };
    	};

  	$scope.montarCharts = function(){

  	  seriesData = [];

  	  var serie = null;

  	  categories = [];

		for(var i = 0; i < data.length; i++){

			//Verifica se é a primeira iteração
			if(i == 0){

				categories.push(data[i].seriename);

				serie = data[i].seriename;

			}else{

				if(serie != data[i].seriename){

					categories.push(data[i].seriename);

					serie = data[i].seriename;

				}
			}
		}

		seriesData  = [];

		//Percorre o array de data para montar as legendas
		for(var i = 0; i < data.length; i++){

			//Verifica se é a primeira iteração
			if(i == 0){

				var dados = {};

				dados.name = data[i].legenda;

				dados.data = [];

				dados.data.push(parseInt(data[i].serievalue));

				dados.index = seriesData.length;

				seriesData.push(dados);

				serie = data[i].seriename;
				//Verifica se a iteração ainda está na mesma serie, se verdadeiro cria um novo dados
			}else if(serie == data[i].seriename){

				var indexLegenda = $scope.obterIndexLegenda(data[i].legenda);

				if(indexLegenda != null){

					seriesData[indexLegenda].data.push(parseInt(data[i].serievalue));

					}else{

						var dados = {};

						dados.name = data[i].legenda;

						dados.data = [];

						dados.data.push(parseInt(data[i].serievalue));

						dados.index = seriesData.length;

						seriesData.push(dados);

					}

				}else{
					//Mudou a serie, obtem o index da legenda.
					serie = data[i].seriename;

					var indexLegenda = $scope.obterIndexLegenda(data[i].legenda);
					//Se contem o index adiciona um novo serieValue na posição a legenda em questão. Caso não encontrou o index cria um novo dados e adiciona no arrayDados.
					if(indexLegenda != null){

						seriesData[indexLegenda].data.push(parseInt(data[i].serievalue));

					}else{

						var dados = {};

						dados.name = data[i].legenda;

						dados.data = [];

						dados.data.push(parseInt(data[i].serievalue));

						dados.index = seriesData.length;

						seriesData.push(dados);

					}

				}

			}

  	  $scope.chartConfig = {
  		        options: {
  		            chart: {
  		                type: $scope.tipoGrafico
  		            }
  		        },
  		        column: {
  	                pointPadding: 0.2,
  	                borderWidth: 0
  	            },
  		        xAxis: {
  		            categories: categories
  		        },
  		        series: seriesData,
  		        title: {
  		            text: titulo
  		        },
  		        subtitle: {
  		            text: subTitulo
  		        },
  		        loading: false
  		    };
  		};

	    $scope.chartSelectionChange = function () {

	    	//Verifica se o tipo de chart selecionado é pizza/pie. Se verdadeiro monta os dados do tipo pizza.
	    	if($scope.tipoGrafico == "pie"){

	    		$scope.montarChartPizza();
	    	}else{

	    		$scope.montarCharts();
	    	};
	    };


    	$scope.iniciarChart();
    };

  });
