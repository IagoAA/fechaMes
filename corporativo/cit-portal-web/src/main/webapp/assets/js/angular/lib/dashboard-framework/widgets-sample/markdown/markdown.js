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
angular.module('cit.widgets.markdown', ['adf.provider', 'btford.markdown'])
.value('nameWidgetMarkDown', 'markdown')
  .config(function(dashboardProvider){
    // template object for github widgets
    var widget = {
      templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets-sample/markdown/markdown.html',
      reload: true,
      resolve: {
        data: function(markdownService, config){
        	if(config.path)
        		return markdownService.get(config.path, config.tempoServico);
        }
      },
      edit: {
    	  templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/edit.html'
      }
    };

    // register github template by extending the template object
    dashboardProvider.widget('markdown', angular.extend({
        title: 'Html',
        description: 'Html',
        controller: 'markdownCtrl'
        }, widget));

  })

  .service('markdownService', ['$q', '$http', 'WidgetRepository', 'nameWidgetMarkDown', '$timeout', function($q, $http, WidgetRepository, nameWidgetMarkDown, $timeout){
    return {
      get: function(url, tempo){
	    var deferred = $q.defer();

	    $timeout(function() {
	    	$http.get(url)
	         .success(function(data){
	           if (data){
	        	   var dados = null;

	        	   if(data[0])
	        		   dados = data[0];
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
  }])

  .controller('markdownCtrl', ['$scope', 'nameWidgetMarkDown', 'config', 'data', '$timeout', 'WidgetRepository', 'DominioRepository', 'GrupoRepository', 'PrivilegioRepository', '$filter', function($scope, nameWidgetMarkDown, config, data, $timeout, WidgetRepository, DominioRepository, GrupoRepository, PrivilegioRepository, $filter){

	    if (data){
	    	config.content = data;
	    }

		  if(config.path){
			  $timeout(function() {
				  $scope.$parent.model.urlServico = config.path;
			  });
		  }

	    $scope.config = config;

		  $timeout(function() {
			  //Se o model não conter um widget, busca o widget referente ao nome.
			  if(!$scope.$parent.model.widget){

				  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetMarkDown).then(function(result){

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
