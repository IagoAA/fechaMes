'use strict';

citApp.controller('NotificacaoListController', ['$scope', '$rootScope', 'NotificacaoRepository', 'DominioRepository', '$translate',
		function NotificacaoListController($scope, $rootScope, NotificacaoRepository, DominioRepository, $translate) {

	$scope.dialog;

	$scope.isVisualizar = false;

	$scope.visualizado = false;

	$scope.resetForm = function() {

		angular.element('#notificacao-edit-controller').scope().resetForm();
	};

	$scope.headers = [ {
		title : $translate.instant('LABEL.TIPO_NOTIFICACAO'),
		value : 'tipoNotificacao.descricao'
	}, {
		title : $translate.instant('LABEL.TIPO_PRIORIDADE'),
		value : 'tipoPrioridade.descricao'
	}, {
		title : $translate.instant('LABEL.MENSAGEM'),
		value : 'mensagem'
	}, {
		title : $translate.instant('LABEL.DATA_VISUALIZACAO'),
		value : 'dataVisualizacao',
		filter : 'dateBR'
	}];

	// Iniciar propriedades padrão da consulta que sera enviada para o servidor.
	$scope.filterCriteria = {
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields : [ 'id', 'mensagem', 'tipoNotificacao.descricao', 'tipoPrioridade.descricao', 'dataVisualizacao' ],
		filters : [ {
			type : 'string',
			field : 'tipoNotificacao.descricao', listaDominio : []
		}, {
			type : 'string',
			field : 'tipoPrioridade.descricao', listaDominio : []
		}, {
			type : 'string',
			field : 'mensagem',
		}, {
			type: 'date',
			field: 'dataVisualizacao'
		} ]
	};

	DominioRepository.findAllDominio('tipoNotificacao').then(function(result) {
		$scope.filterCriteria.filters[0].listaDominio = result;
	});


	DominioRepository.findAllDominio('tipoPrioridade').then(function(result) {
		$scope.filterCriteria.filters[1].listaDominio = result;
	});

	//Atualiza a data de visualização da notificação
	$scope.updateVisualizar = function() {

		$scope.setLoadingSalva(true);

		$scope.notificacao.dataVisualizacao = new Date();

		NotificacaoRepository.save($scope.notificacao).then(function(result) {
			$scope.findNotificacoes();
			$scope.setLoading(false);
		});

		$scope.visualizado = true;
	};

	$scope.abrirNotificacao = function(visualizar){

		var notificacao = $scope.notificacaoChecked;

		if(!notificacao) {
			$scope.showAlert('warning', visualizar ?  $translate.instant('MSG.SELECIONE_APENAS_UM_ITEM_PARA_VISUALIZAR') : $translate.instant('MSG.SELECIONE_APENAS_UM_ITEM_PARA_EDICAO'));
			return;
		}

		angular.element('#notificacao-edit-controller').scope().getNotificacao(notificacao.id, visualizar);

		$scope.$showPageEditWorkspace($scope.workspace);
	};

}]);
