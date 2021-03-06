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

angular.module('adf')
  .directive('adfWidgetContent', ['$log', '$q', '$sce', '$http', '$templateCache', '$compile', '$controller', '$injector', 'dashboard', '$rootScope', '$translate', function($log, $q, $sce, $http, $templateCache, $compile, $controller, $injector, dashboard, $rootScope, $translate) {

    function parseUrl(url){
      return url.replace('{widgetsPath}', dashboard.widgetsPath);
    }

    function getTemplate(widget){
      var deferred = $q.defer();

      if ( widget.template ){
        deferred.resolve(widget.template);
      } else if (widget.templateUrl) {
        // try to fetch template from cache
        var tpl = $templateCache.get(widget.templateUrl);
        if (tpl){
          deferred.resolve(tpl);
        } else {
          var url = $sce.getTrustedResourceUrl(parseUrl(widget.templateUrl));
          $http.get(url)
            .success(function(response){
              // put response to cache, with unmodified url as key
              $templateCache.put(widget.templateUrl, response);
              deferred.resolve(response);
            })
            .error(function(){
              deferred.reject('could not load template');
            });
        }
      }

      return deferred.promise;
    }

    function compileWidget($scope, $element, currentScope) {
      var model = $scope.model;
      var content = $scope.content;

      // display loading template
      $element.html(dashboard.loadingTemplate);

      // create new scope
      var templateScope = $scope.$new();

      // pass config object to scope
      if (!model.config) {
        model.config = {};
      }

      templateScope.config = model.config;

      templateScope.$translate = $translate;

      // local injections
      var base = {
        $scope: templateScope,
        widget: model,
        config: model.config
      };

      // SE EXISTIR UMA CONFIGURAÇÃO PADRÃO DE ATUALIZAÇÃO DO WIDGET CRIA O setInterval
      if(model.config.tempoAtualizacao){
    	  // SE JÁ EXISTIR UM setInterval anula ele e cria um novo.
          if($scope.interval){
        	  clearInterval($scope.interval);
          }

    	  var tempo = (parseInt($scope.model.config.tempoAtualizacao) * 60) * 1000;
          $scope.interval = setInterval(function () {compileWidget($scope, $element, currentScope)}, tempo);

          if(!$rootScope.listaWidgetIntervals) {
        	  $rootScope.listaWidgetIntervals = [];
          } else {
        	  $rootScope.listaWidgetIntervals.push($scope.interval);
          }
      }

      // get resolve promises from content object
      var resolvers = {};
      resolvers.$tpl = getTemplate(content);
      if (content.resolve) {
        angular.forEach(content.resolve, function(promise, key) {
          if (angular.isString(promise)) {
            resolvers[key] = $injector.get(promise);
          } else {
            resolvers[key] = $injector.invoke(promise, promise, base);
          }
        });
      }

      // resolve all resolvers
      $q.all(resolvers).then(function(locals) {
        angular.extend(locals, base);

        // compile & render template
        var template = locals.$tpl;
        $element.html(template);
        if (content.controller) {
          var templateCtrl = $controller(content.controller, locals);
          if (content.controllerAs){
            templateScope[content.controllerAs] = templateCtrl;
          }
          $element.children().data('$ngControllerController', templateCtrl);
        }
        $compile($element.contents())(templateScope);
      }, function(reason) {
        // handle promise rejection
        var msg = 'Mensagem erro';
        if (reason) {
          msg += ': ' + reason;
        }
        $log.warn(msg);
        $element.html(dashboard.messageTemplate.replace(/{}/g, msg));
      });

      // destroy old scope
      if (currentScope){
        currentScope.$destroy();
      }

      return templateScope;
    }

    return {
      replace: true,
      restrict: 'EA',
      transclude: false,
      scope: {
        model: '=',
        content: '='
      },
      link: function($scope, $element, $attr) {
        var currentScope = compileWidget($scope, $element, null);
        $scope.$on('widgetConfigChanged', function(){
          currentScope = compileWidget($scope, $element, currentScope);
        });

        $scope.$on('widgetReload', function(){
          currentScope = compileWidget($scope, $element, currentScope);
        });
      }
    };

  }]);
