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

function FilterCriteria() {
	this.start = 1;
	this.limit = 10;
	this.deadline = 1;
	this.processDeadline = 1;
	this.timeManagementStatus = "";
	this.filters = [{type: 'numeric', field: 'workItem.id'}
	 , {type: 'string', field: 'workItem.flowElement.name'}
	 , {type: 'numeric', field: 'workItem.processInstance.id'}
	 , {type: 'string', field: 'workItem.processInstance.businessProcess.description'}
	 , {type: 'string', field: 'workItem.processInstance.flowStatus.name'}];
};

function buildFilters(config, filterCriteria) {
	if (config && config.parametros) {
    	for (var i = 0; i < config.parametros.length; i++) {
			var parametro = config.parametros[i];
			if (parametro.atributoFiltro && parametro.atributoFiltro != '') {
				if (parametro.tipoWidgetParametroDominio.codigo == 3 || parametro.tipoWidgetParametroDominio.codigo == 4) {
					filterCriteria[parametro.atributoFiltro] = parametro.numeroDefault;
				}else if (parametro.tipoWidgetParametroDominio.codigo == 5) {
					filterCriteria[parametro.atributoFiltro] = parametro.dataDefault;
				}else if (parametro.tipoWidgetParametroDominio.codigo == 6) {
					if (parametro.atributoFiltro.toUpperCase().indexOf('DEADLINE') >= 0) {
						if (parametro.dominioDefault.codigo > 1) {
							filterCriteria[parametro.atributoFiltro] = parametro.dominioDefault.codigo;
						}
					}else{
						filterCriteria[parametro.atributoFiltro] = parametro.dominioDefault.codigo;
					}
				}else if (parametro.tipoWidgetParametroDominio.codigo == 10) {
					filterCriteria[parametro.atributoFiltro] = parametro.booleanDefault;
				}else {
					filterCriteria[parametro.atributoFiltro] = parametro.textoDefault;
				}
			}
		}
	}
}

'use strict';
angular.module('cit.widgets.bpetask', ['adf.provider'])
.value('nameWidgetBpeTask', 'bpetask')
  .config(function(dashboardProvider){
    // template object for github widgets
    var widget = {
      templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/bpe-task/bpetask.html',
      reload: true,
      resolve: {
        data: function(bpetaskService, config){
       		return bpetaskService.get(config, config.tempoServico);
        }
      },
      edit: {
    	  templateUrl: 'assets/js/angular/lib/dashboard-framework/widgets/charts/edit.html'
      }
    };

    // register github template by extending the template object
    dashboardProvider.widget('bpetask', angular.extend({
        title: 'Tarefa BPE',
        description: 'Tarefa de fluxo BPE',
        controller: 'bpetaskCtrl'
        }, widget));

  })

  .service('bpetaskService', ['$q', '$http', 'WidgetRepository', 'nameWidgetBpeTask', '$timeout', 'BusinessProcessRepository', function($q, $http, WidgetRepository, nameWidgetBpeTask, $timeout, BusinessProcessRepository){
    return {
      get: function(config, tempo){
	    var deferred = $q.defer();

	    $timeout(function() {
	    	var appController = angular.element("#citapp-controller").scope();

	    	var filterCriteria = new FilterCriteria();
	    	buildFilters(config, filterCriteria);

			BusinessProcessRepository.tasksByParam(filterCriteria).then(function(result) {
		        deferred.resolve(result);
			}, function() {
	        	  deferred.reject();
			});
		}, tempo);

        return deferred.promise;
      }
    };
  }])

  .controller('bpetaskCtrl', ['$scope', 'nameWidgetBpeTask', 'config', 'data', '$timeout', 'WidgetRepository', 'DominioRepository', 'GrupoRepository', 'PrivilegioRepository', '$filter',
			 'FlowRepository', 'BusinessProcessRepository', 'DomainRepository', 'RuntimeManagerRepository', '$compile', '$translate', '$modal', '$injector', '$parse', '$rootScope',
		  function($scope, nameWidgetBpeTask, config, data, $timeout, WidgetRepository, DominioRepository, GrupoRepository, PrivilegioRepository, $filter,
		  							 FlowRepository, BusinessProcessRepository, DomainRepository, RuntimeManagerRepository, $compile, $translate, $modal, $injector, $parse, $rootScope){

	  	$scope.trataPrazo = function() {
			var dataAtual = new Date();

			angular.forEach($scope.assignments, function (assignment) {
				assignment.classePrazoProcesso = new Date(assignment.processInstance.estimatedEndTimestamp) >= dataAtual ? 'green' : 'red';
				assignment.classePrazoTarefa = new Date(assignment.estimatedEndTimestamp) >= dataAtual ? 'green' : 'red';
			});
	  	};

		DomainRepository.findAllDomain('prazoExecucao').then(function(result) {
			$scope.prazosExecucao = result;
		});

		$scope.$showAdvancedFilters = false;
		$scope.compile = $compile;

		if (data){
			$scope.filterCriteria = new FilterCriteria();

	    	config.content = data.originalElement.objects;
	    	$scope.assignments = data.originalElement.objects;

	    	$scope.trataPrazo();

			$scope.totalPages = data.originalElement.totalPages;
			$scope.totalItens =  data.originalElement.totalItens;

			$scope.appController = angular.element("#citapp-controller").scope();
			$scope.runtimeManagerUtils = new RuntimeManagerUtils($scope, $scope.appController, $translate, RuntimeManagerRepository);
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

				  WidgetRepository.buscaWidgetByNomeTipo(nameWidgetBpeTask).then(function(result){

					  $scope.$parent.model.widget = result.originalElement;

					  config.apresentarUrl = result.originalElement.apresentarUrlServico;

					  $scope.$parent.model.config.parametros = result.originalElement.parametros;

					  $scope.findDominioPainelItemParametro();

					  $scope.defineTaskListType();
				  });
			  }else{
				  $scope.defineTaskListType();
			  }

			  $scope.idPainelItem = $scope.$parent.model.idPainelItem;
		  });

		  $scope.defineTaskListType = function() {
			  $scope.completeTaskList = false;
			  angular.forEach($scope.$parent.model.config.parametros, function (parametro) {

				if(parametro.atributoFiltro && parametro.atributoFiltro == "completeTaskList"){
					$scope.completeTaskList = parametro.booleanDefault;
				}
			  });
		  };

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

	DomainRepository.getEnumeratedDomain('TimeManagementStatusEnum').then(function(result) {
		$scope.timeManagementStatus = [];
		$scope.timeManagementStatus.push({chave:"", descricao: $translate.instant("LABEL.TODOS")})
		for (var i = 0; i < result.length; i++) {
			$scope.timeManagementStatus.push(result[i]);
		}
	});

	$scope.getTimeManagementStatus = function(processInstance) {
		if (processInstance && processInstance.timeManagementStatus) {
			processInstance.backgroundColorTimeStatus = processInstance.timeManagementStatus === 'SUSPENDED' ? "red" : processInstance.timeManagementStatus === 'NOT_STARTED' ? "yellow-dark" : "green";
			processInstance.textColorTimeStatus = processInstance.timeManagementStatus === 'NOT_STARTED' ? "black" : "white";
			return DomainRepository.getEnumDescription(processInstance.timeManagementStatus, $scope.timeManagementStatus);
		}else{
			return "";
		}
	};

	$scope.recuperarPermissoes = function(assignment){
		assignment.workItem = {}
		BusinessProcessRepository.getPermissions({id: assignment.id}).then(function(result) {
			assignment.workItem = result.originalElement;
			if (!assignment.workItem.execute && !assignment.workItem.delegate && !assignment.workItem.suspend && !assignment.workItem.restart && !assignment.workItem.visualize) {
				$scope.fetchResult();
			}
		});
	};

	$scope.executarTarefa = function(workItem){
		var pagina = '/cit-esi-web/assets/js/angular/custom/directive/html/userTask.html';

		if ($scope.runtimeManagerUtils.existsWorkspace(pagina)) {
			$scope.appController.showAlert('warning', $translate.instant('ESI.MSG.JA_EXISTE_TELA_TAREFA'));
			return ;
		}

		$scope.task = workItem;

		if ($scope.task.status == 'CANCELLED' || $scope.task.status == 'COMPLETED') {
			$scope.appController.showAlert('error', $translate.instant('ESI.MSG.TAREFA_JA_EXECUTADA'));
			$scope.fetchResult();
			return;
		}

		$scope.processInstance = $scope.task.processInstance;
		$scope.processInstance.collapsed = true;
		$scope.processInstance.collapsedError = true;

		$rootScope.task = $scope.task;
		$rootScope.processInstance = $scope.processInstance;
		$rootScope.controllerScope = $scope;

		var nome = $scope.task.flowElement.description;
		if (!nome || nome == '')
			nome = $scope.task.flowElement.name;

		$scope.appController.addNewWorkspace($translate.instant('ESI.PERMISSAO.EXECUTAR')+" "+$translate.instant('LABEL.TAREFA').toLowerCase()+" '"+nome+"'", pagina, true, 'mod-red-dark', $scope.task);
	};

	// Will be called when filtering the grid, will reset the page number to one
	$scope.filterResult = function() {

		//Timeout adicionado para que os filtros de pesquisa funcionem
		$timeout(function(){
			$scope.filterCriteria.start = 1;
			$scope.fetchResult().then(function() {
				// The request fires correctly but sometimes the ui doesn't update,
				// that's a fix
				$scope.filterCriteria.start = 1;
			});

		});
	};

	// call back function that we passed to our custom directive sortBy, will be
	// called when clicking on any field to sort
	$scope.onSort = function(sortedBy, sortDir) {
		$scope.filterCriteria.dir = sortDir;
		$scope.filterCriteria.sort = sortedBy;
		$scope.filterCriteria.start = 1;
		$scope.fetchResult().then(function() {
			// The request fires correctly but sometimes the ui doesn't update,
			// that's a fix
			$scope.filterCriteria.start = 1;
		});
	};

	$scope.fetchResult = function() {
		var filterCriteria = new FilterCriteria();
		filterCriteria.start = $scope.filterCriteria.start;
		filterCriteria.limit = $scope.filterCriteria.limit;
		filterCriteria.filters = $scope.filterCriteria.filters;
		filterCriteria.deadline = $scope.filterCriteria.deadline;
		filterCriteria.timeManagementStatus = $scope.filterCriteria.timeManagementStatus;
		filterCriteria.processDeadline = $scope.filterCriteria.processDeadline;

		buildFilters(config, filterCriteria);

		$scope.carregando = true;
		return BusinessProcessRepository.tasksByParam(filterCriteria).then(function(data) {
			$scope.filterCriteria = filterCriteria;

	    	config.content = data.originalElement.objects;
	    	$scope.assignments = data.originalElement.objects;

	    	$scope.trataPrazo();

			$scope.totalPages = data.originalElement.totalPages;
			$scope.totalItens =  data.originalElement.totalItens;
			$scope.carregando = false;

			for (var i = 0; i < $scope.filterCriteria.filters.length; i++) {
				if ($scope.filterCriteria.filters[i].value && $scope.filterCriteria.filters[i].value != '') {
					$scope.$showAdvancedFilters = true;
					break;
				}
			}
		}, function() {
	    	config.content = [];
	    	$scope.assignments = [];

			$scope.totalPages = 0;
			$scope.totalItens = 0;
			$scope.carregando = false;
		});
  	};

	$scope.delegarTarefa = function(workItem){
		var pagina = '/cit-esi-web/assets/js/angular/custom/directive/html/userTaskDelegation.html';

		if ($scope.runtimeManagerUtils.existsWorkspace(pagina)) {
			$scope.appController.showAlert('warning', $translate.instant('ESI.MSG.JA_EXISTE_TELA_DELEGACAO'));
			return ;
		}

		$scope.task = workItem;

		if ($scope.task.status == 'CANCELLED' || $scope.task.status == 'COMPLETED') {
			$scope.appController.showAlert('error', $translate.instant('ESI.MSG.TAREFA_JA_EXECUTADA'));
			$scope.fetchResult();
			return;
		}

		$scope.processInstance = $scope.task.processInstance;
		$scope.processInstance.collapsed = true;
		$scope.processInstance.collapsedError = true;

		$rootScope.task = $scope.task;
		$rootScope.processInstance = $scope.processInstance;
		$rootScope.controllerScope = $scope;

		var nome = $scope.task.flowElement.description;
		if (!nome || nome == '')
			nome = $scope.task.flowElement.name;

		$scope.appController.addNewWorkspace($translate.instant('ESI.PERMISSAO.DELEGAR')+" "+$translate.instant('LABEL.TAREFA').toLowerCase()+" '"+nome+"'", pagina, true, 'mod-red-dark', $scope.task);
	};

	$scope.suspenderProcesso = function(assignment){
		$scope.appController.$openModalConfirm({
			message: $translate.instant('ESI.MSG.CONFIRMA_SUSPENSAO'),
			callback: function () {
				$scope.appController.$modalConfirmInstance.dismiss('cancel');

				$scope.appController.setLoading(true);
				RuntimeManagerRepository.suspendProcessInstance(assignment.workItem.processInstance.id).then(function(result) {
					$scope.appController.setLoading(false);
					$scope.fetchResult();
					$scope.showAlert("success","ESI.MSG.PROCESSO_SUSPENSO","");
				});
			}
		});
	};

	$scope.reativarProcesso = function(assignment){
		$scope.appController.$openModalConfirm({
			message: $translate.instant('ESI.MSG.CONFIRMA_REATIVACAO'),
			callback: function () {
				$scope.appController.$modalConfirmInstance.dismiss('cancel');

				$scope.appController.setLoading(true);
				RuntimeManagerRepository.restartProcessInstance(assignment.workItem.processInstance.id).then(function(result) {
					$scope.appController.setLoading(false);
					$scope.fetchResult();
					$scope.showAlert("success","ESI.MSG.PROCESSO_REATIVADO","");
				});
			}
		});
	};

	$scope.capturarTarefa = function(workItem){
		$scope.appController.$openModalConfirm({
			message: $translate.instant('ESI.MSG.CONFIRMA_CAPTURA'),
			callback: function () {
				$scope.appController.$modalConfirmInstance.dismiss('cancel');

				$scope.task = workItem;

				if ($scope.task.status == 'CANCELLED' || $scope.task.status == 'COMPLETED') {
					$scope.appController.showAlert('error', $translate.instant('ESI.MSG.TAREFA_JA_EXECUTADA'));
					$scope.fetchResult();
					return;
				}

				$scope.appController.setLoading(true,$translate.instant('ESI.CAPTURANDO_TAREFA'));

				var param = new RuntimeEnvironmentInput("",[]);
				param.workItemId = workItem.id;

				RuntimeManagerRepository.captureTask(param).then(function(result) {
					$scope.appController.setLoading(false);
					$scope.fetchResult();
					$scope.appController.showAlert("success","ESI.MSG.TAREFA_CAPTURADA","");
				});
			}
		});

	};

	$scope.visualizarFluxo = function(workItem){
		var pagina = '/cit-esi-web/assets/js/angular/custom/directive/html/visualizacaoEsi.html';

		if ($scope.runtimeManagerUtils.existsWorkspace(pagina)) {
			$scope.appController.showAlert('warning', $translate.instant('ESI.MSG.JA_EXISTE_TELA_VISUALIZACAO'));
			return ;
		}

		$scope.tarefa = workItem;
		$scope.instancia = $scope.tarefa.processInstance;
		$rootScope.tarefa = $scope.tarefa;
		$rootScope.instancia = $scope.instancia;
		$rootScope.controllerScope = $scope;
		$scope.appController.addNewWorkspace(workItem.processInstance.businessProcess.description, pagina, true, 'mod-red-dark', $scope.tarefa);
	};

	$scope.consultarHistorico = function(workItem){
		var pagina = '/cit-esi-web/assets/js/angular/custom/directive/html/consultaLog.html';

		if ($scope.runtimeManagerUtils.existsWorkspace(pagina)) {
			$scope.appController.showAlert('warning', $translate.instant('ESI.MSG.JA_EXISTE_TELA_HISTORICO'));
			return ;
		}

		$scope.processInstance = workItem.processInstance;
		$rootScope.processInstance = $scope.processInstance;
		$rootScope.controllerScope = $scope;

		$scope.processInstance.collapsed = true;
		$scope.processInstance.collapsedError = true;
		$scope.processInstance.instances = [];

		$scope.appController.addNewWorkspace(workItem.processInstance.businessProcess.description, pagina, true, 'mod-red-dark', $scope.processInstance);
	};

	$scope.retrieveAditionalHtml = function(assignment) {
		assignment.$show = !assignment.$show;
		if (!assignment.$show)
			return;

		if (!assignment.workItem) {
			BusinessProcessRepository.getPermissions({id: assignment.id}).then(function(result) {
				assignment.workItem = result.originalElement;
				$scope.showAditionalHtml(assignment.workItem);
			});
		}else{
			$scope.showAditionalHtml(assignment.workItem);
		}
	};

	$scope.showAditionalHtml = function(workItem) {
		var id = "divHtmlAdicional_" + $scope.idPainelItem + "_" + workItem.id;

		var el = document.getElementById(id);
		if (!el || el.innerHTML != '')
			return;

		var element = angular.element("#"+id);
		$scope.runtimeManagerUtils.retrieveAditionalHtml(workItem, element, $injector);
		el.focus();
	};

	$scope.signalEvent = function() {
		$scope.appController.setLoading(true);
		RuntimeManagerRepository.signalEvent($scope.signalEventInput).then(function(result) {
			$scope.appController.setLoading(false);
		});
	};

	$scope.getAplicacaoFluxo = function(processoNegocio) {
		return DomainRepository.getEnumDescription(processoNegocio['flow.flowApplication'], $scope.aplicacaoFluxo);
	};

  }]);
