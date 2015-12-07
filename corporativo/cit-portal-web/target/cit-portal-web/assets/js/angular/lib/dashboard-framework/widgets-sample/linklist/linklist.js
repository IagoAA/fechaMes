/* *
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

'use strict';

angular.module('sample.widgets.linklist', ['adf.provider']) .value('nameWidgetLink', 'link')
  .config(function(dashboardProvider){
    dashboardProvider
      .widget('linklist', {
        title: 'Links',
        description: 'Mostra lista de links',
        controller: 'linklistCtrl',
        controllerAs: 'list',
        templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets-sample/linklist/linklist.html',
        edit: {
          templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets-sample/linklist/edit.html',
          reload: false,
          controller: 'linklistEditCtrl'
        }
      });
  }).controller('linklistCtrl', ['$scope', 'config', 'WidgetRepository', '$timeout', 'nameWidgetLink', 'PrivilegioRepository', 'GrupoRepository', '$filter', function($scope, config, WidgetRepository, $timeout, nameWidgetLink, PrivilegioRepository, GrupoRepository, $filter){
    if (!config.links){
      config.links = [];
    }
    this.links = config.links;

	  $timeout(function() {
		  //Se o model não conter um widget, busca o widget referente ao nome.
		  if(!$scope.$parent.model.widget){

			  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetLink).then(function(result){

				  $scope.$parent.model.widget = result.originalElement;

			  });
		  }
	  });

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

  }]).controller('linklistEditCtrl', ['$scope', function($scope){

    function getLinks(){
      if (!$scope.config.links){
        $scope.config.links = [];
      }
      return $scope.config.links;
    }
    $scope.addLink = function(){
      getLinks().push({});
    };
    $scope.removeLink = function(index, link){

      link.remover = true;
    };

  }]);
