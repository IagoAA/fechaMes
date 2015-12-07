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

angular.module('cit.widgets.googlechartcit', ['adf.provider', 'googlechart'])
.value('nameWidgetGoogleChartCit', 'googlechartcit')
  .config(function(dashboardProvider){
    // template object for github widgets
    var widget = {
      templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/google-chart/googlechart.html',
      reload: true,
      resolve: {
        data: function(googleChartService, config){
        	if(config.path)
        		return googleChartService.get(config.path, config.tempoServico);
        }
      },
      edit: {
        templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/edit.html'
      }
    };

    // register github template by extending the template object
    dashboardProvider.widget('googlechartcit', angular.extend({
        title: 'Google Chart',
        description: 'Google Charts',
        controller: 'googleChartCtrl'
        }, widget));

  })
  .service('googleChartService', function($q, $http, WidgetRepository, nameWidgetGoogleChartCit, $timeout, $filter){
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
  .controller('googleChartCtrl', function($scope, config, data, $timeout, nameWidgetGoogleChartCit, WidgetRepository, DominioRepository, GrupoRepository, $filter, PrivilegioRepository){
	  // SE EXISTIR O PARAMETRO CONFIG.PATH MANTEM O PARAMETRO, SENÃO BUSCA A CONFIGURAÇÃO PADRÃO DO WIDGET
	  if(config.path){
		  $timeout(function() {
			  $scope.$parent.model.urlServico = config.path;
		  });
	  }

	  $timeout(function() {

		  if(!$scope.$parent.model.widget){

			  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetGoogleChartCit).then(function(result){

				  $scope.$parent.model.widget = result.originalElement;

				  config.apresentarUrl = result.originalElement.apresentarUrlServico;

				  $scope.$parent.model.config.parametros = result.originalElement.parametros;

				  $scope.findDominioPainelItemParametro();

				  if($scope.chart){
					  if ($scope.chart.options) {
						// PEGA AS CONFIGURAÇÕES DEFINIDAS NOS PARAMETROS
						$scope.chart.options.hAxis.title = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "hAxis titulo"; })[0].textoDefault;
						$scope.chart.options.vAxis.title = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "vAxis titulo"; })[0].textoDefault;
						$scope.chart.options.isStacked = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "isStacked"; })[0].booleanDefault;
						$scope.chart.options.title = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "Titulo"; })[0].textoDefault;
					  }
					  $scope.chart.type = $.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "Tipo Grafico"; })[0].dominioDefault.descricao;
				  }
			  });
		  }else{
			if($scope.chart && $scope.chart.options){
				// PEGA AS CONFIGURAÇÕES DEFINIDAS NOS PARAMETROS
				$scope.chart.options.hAxis.title = $.grep(config.parametros, function(e){ return e.nome == "hAxis titulo"; })[0].textoDefault;
				$scope.chart.options.vAxis.title = $.grep(config.parametros, function(e){ return e.nome == "vAxis titulo"; })[0].textoDefault;
				$scope.chart.options.isStacked = $.grep(config.parametros, function(e){ return e.nome == "isStacked"; })[0].booleanDefault;
				$scope.chart.options.title = $.grep(config.parametros, function(e){ return e.nome == "Titulo"; })[0].textoDefault;
				$scope.chart.type = $.grep(config.parametros, function(e){ return e.nome == "Tipo Grafico"; })[0].dominioDefault.descricao;
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

	//Método responsável por montar os dados do chart de pizza/pier
	$scope.montarDataChartPizza =  function(){
		$scope.chart.data = undefined;
		try {
			 $scope.chart.data = new google.visualization.DataTable();

			 $scope.chart.data.addColumn('string', 'categoria');
			 $scope.chart.data.addColumn('number', 'serievalue');

			for(var i = 0; i < data.length; i++){

				 $scope.chart.data.addRow([data[i].legenda, parseInt(data[i].serievalue)]);
			}
		} catch (e) {
			console.info(e);
		}

	};

	//Método responsável por montar os dados do chart
	$scope.montarDataChart = function(){

		 // INICIALIZA OS ARRAYS DE COLS E ROWS
		 $scope.chart.data = {"cols": [], "rows": []};

		 $scope.chart.displayed = false;

		 $scope.chart.data.cols.push({id: data[0].categoria, label: data[0].categoria , type: "string"});

		 var serie = null;

		 for(var i = 0; i < data.length; i++){
				//Verifica se é a primeira iteração, caso seja adiciona a primeira legenda.
				if(i == 0){
					$scope.chart.data.cols.push({id: '', label: data[i].legenda, type: "number"});
					//Atribui o nome da serie na variavel serie, para manter o controle de qual serie está montando os dados.
					serie = data[0].seriename;
					//Verifica se a serie atual é a mesma da iteração anterior, caso seja verdadeiro adiciona a legenda caso contrario verifica se a legenda já foi adicionada.
				}else if(data[i].seriename ==  serie){

						$scope.chart.data.cols.push({id: '', label: data[i].legenda, type: "number"});
					}else{
						//Se não contem a legenda na cols adiciona
						if(!$scope.contemLegenda(data[i].legenda)){

							$scope.chart.data.cols.push({id: '', label: data[i].legenda, type: "number"});
						}
					}

		}

		serie = null;
		//Variavel responsável por verificar a posição da linha
		var posicaoRow = 0;

		for(var i = 0; i < data.length; i++){

			//Verifica se é a primeira iteração
			if(i == 0){

				$scope.chart.data.rows.push({c: [{v: data[i].seriename}]});

				$scope.chart.data.rows[0].c.push({v: data[i].serievalue, f: data[i].serievalue});

				serie =  data[i].seriename;

			}else if(serie == data[i].seriename){

					$scope.chart.data.rows[posicaoRow].c.push({v: data[i].serievalue, f:  data[i].serievalue});
				}else{

					$scope.chart.data.rows.push({c: [{v: data[i].seriename}]});

					$scope.chart.data.rows[posicaoRow + 1].c.push({v: data[i].serievalue, f:  data[i].serievalue});

					serie = data[i].seriename;

					posicaoRow++;
				}
		 }

	};

	//Verifica se contem a legenda na cols
	$scope.contemLegenda =  function(legenda){

		var legendaAdicionada =  false;

		angular.forEach($scope.chart.data.cols, function(item) {

			if(legenda == item.label){

				legendaAdicionada = true;
			}
		});


		return legendaAdicionada;
	};

	if(data){
		$scope.chart = {};

		//Verifica se o grafico selecionado é do tipo Pizza/PieChart
		if($.grep($scope.$parent.model.config.parametros, function(e){ return e.nome == "Tipo Grafico"; })[0].dominioDefault.descricao == "PieChart"){

			$scope.montarDataChartPizza();

		}else{

			$scope.montarDataChart();
		}

		 $scope.chart.options = {
	        "title": "",
	        "isStacked": "true",
	        "fill": 20,
	        "displayExactValues": true,
	        "vAxis": {
	            "title": "Sales unit", "gridlines": {"serievalue": 10}
	        },
	        "hAxis": {
	            "title": "Date"
	        },
	        tooltip: {
	        	isHtml: false
	        }
	    };


	    var formatCollection = [
	        {
	            name: "color",
	            format: [
	                {
	                    columnNum: 4,
	                    formats: [
	                        {
	                            from: 0,
	                            to: 3,
	                            color: "white",
	                            bgcolor: "red"
	                        },
	                        {
	                            from: 3,
	                            to: 5,
	                            color: "white",
	                            fromBgColor: "red",
	                            toBgColor: "blue"
	                        },
	                        {
	                            from: 6,
	                            to: null,
	                            color: "black",
	                            bgcolor: "#33ff33"
	                        }
	                    ]
	                }
	            ]
	        },
	        {
	            name: "arrow",
	            checked: false,
	            format: [
	                {
	                    columnNum: 1,
	                    base: 19
	                }
	            ]
	        },
	        {
	            name: "date",
	            format: [
	                {
	                    columnNum: 5,
	                    formatType: 'long'
	                }
	            ]
	        },
	        {
	            name: "number",
	            format: [
	                {
	                    columnNum: 4,
	                    prefix: '$'
	                }
	            ]
	        },
	        {
	            name: "bar",
	            format: [
	                {
	                    columnNum: 1,
	                    width: 100
	                }
	            ]
	        }
	    ];

	    $scope.chart.formatters = {};

		    $scope.cssStyle = "height:400px; width:100%;";

	    }

		$scope.chartSelectionChange = function () {
			//Verifica se o tipo de chart selecionado é pizza/pie. Se verdadeiro monta os dados do tipo pizza.
			if($scope.chart.type == "PieChart"){

				$scope.montarDataChartPizza();
			}else{

				$scope.montarDataChart();
			};
		};

	    $scope.htmlTooltip = function () {

	        if ($scope.chart.options.tooltip.isHtml) {
	            $scope.chart.data.cols.push({id: "", "role": "tooltip", "type": "string", "p": { "role": "tooltip", 'html': true} });
	            $scope.chart.data.rows[0].c[5] = {v: " <b>Shipping " + $scope.chart.data.rows[0].c[4].v + "</b><br /><img src=\"http://icons.iconarchive.com/icons/antrepo/container-4-cargo-vans/512/Google-Shipping-Box-icon.png\" style=\"height:85px\" />"};
	            $scope.chart.data.rows[1].c[5] = {v: " <b>Shipping " + $scope.chart.data.rows[1].c[4].v + "</b><br /><img src=\"http://icons.iconarchive.com/icons/antrepo/container-4-cargo-vans/512/Google-Shipping-Box-icon.png\" style=\"height:85px\" />"};
	            $scope.chart.data.rows[2].c[5] = {v: " <b>Shipping " + $scope.chart.data.rows[2].c[4].v + "</b><br /><img src=\"http://icons.iconarchive.com/icons/antrepo/container-4-cargo-vans/512/Google-Shipping-Box-icon.png\" style=\"height:85px\" />"};
	        } else {
	            $scope.chart.data.cols.pop();
	            delete $scope.chart.data.rows[0].c[5];
	            delete $scope.chart.data.rows[1].c[5];
	            delete $scope.chart.data.rows[2].c[5];
	        }
	    };


	    $scope.hideServer = false;
	    $scope.selectionChange = function () {
	        if ($scope.hideServer) {
	            $scope.chart.view = {columns: [0, 1, 2, 4]};
	        } else {
	            $scope.chart.view = {};
	        }
	    };

	    $scope.formatCollection = formatCollection;
	    $scope.toggleFormat = function (format) {
	        $scope.chart.formatters[format.name] = format.format;
	    };

	    $scope.chartReady = function () {
	        fixGoogleChartsBarsBootstrap();
	    };

	    function fixGoogleChartsBarsBootstrap() {
	        // Google charts uses <img height="12px">, which is incompatible with Twitter
	        // * bootstrap in responsive mode, which inserts a css rule for: img { height: auto; }.
	        // *
	        // * The fix is to use inline style width attributes, ie <img style="height: 12px;">.
	        // * BUT we can't change the way Google Charts renders its bars. Nor can we change
	        // * the Twitter bootstrap CSS and remain future proof.
	        // *
	        // * Instead, this function can be called after a Google charts render to "fix" the
	        // * issue by setting the style attributes dynamically.

	        $(".google-visualization-table-table img[width]").each(function (index, img) {
	            $(img).css("width", $(img).attr("width")).css("height", $(img).attr("height"));
	        });
	    };


  });
