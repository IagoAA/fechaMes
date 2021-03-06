'use strict';

citApp.controller('PerfilUsuarioListController', ['$scope', 'PaginaUsuarioRepository',
		function PessoaListController($scope, PaginaUsuarioRepository) {

	$scope.filterCriteria = {
		nome : '',
		start : 1,
		dir : 'asc',
		sort : 'id',
		limit : 10,
		fields: ['id', 'nome', 'tipoPessoaEnum', 'data'],
		filters : [{type: 'string', field: 'nome'}, {type: 'string', field: 'tipo'}, {type: 'string', field: 'dataInicio'}, {type: 'string', field: 'dataFim'}]
	};


}]);


