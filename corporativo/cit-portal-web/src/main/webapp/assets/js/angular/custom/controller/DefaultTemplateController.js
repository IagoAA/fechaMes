'use strict';

citApp.controller('DefaultTemplateController', ['$scope', '$modal', '$timeout',
		function DefaultTemplateController($scope, $modal, $timeout) {
	$scope.lock = function () {
		alert('Lock!');
	};

	$scope.remover = function (testeItem) {
		$openModalConfirm({
			title: 'Excluir item default',
			message: 'Excluir item default',
			callback: function () {
				$scope.$modalConfirmInstance.dismiss('cancel');

				alert('Excluido com sucesso! ' + testeItem.nome);
			}
		});
	};

	$scope.items = [{
		column1: "Valor 1",
		column2: "Valor 2",
		column3: "20/10/2014",
		$show: false
	}, {
		column1: "Valor 1",
		column2: "Valor 2",
		column3: "20/10/2014",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}, {
		column1: "Valor 1",
		column3: "20/10/2014",
		column2: "Valor 2",
		$show: false
	}];

	$scope.testeItem = {
		nome: "Teste"
	};

	$scope.addNewEmptyItem = function () {
		$scope.items.push({});
	};

	$scope.removeItem = function ($index) {
		$scope.items.splice($index, 1);
	};

	$scope.showItem = function(item) {
		if(item.$show) {
			item.$show = false;
		} else {
			item.$show = true;
		}
	};

	$scope.varTeste = "Teste var Teste";

	$scope.funcTeste = function () {
		console.log('Func Teste funcionou!!!');
	};

	$scope.people = [
	                 { name: 'Adam',      email: 'adam@email.com',      age: 10 },
	                 { name: 'Amalie',    email: 'amalie@email.com',    age: 12 },
	                 { name: 'Wladimir',  email: 'wladimir@email.com',  age: 30 },
	                 { name: 'Samantha',  email: 'samantha@email.com',  age: 31 },
	                 { name: 'Estefanía', email: 'estefanía@email.com', age: 16 },
	                 { name: 'Natasha',   email: 'natasha@email.com',   age: 54 },
	                 { name: 'Nicole',    email: 'nicole@email.com',    age: 43 },
	                 { name: 'Adrian',    email: 'adrian@email.com',    age: 21 }
	               ];

	$scope.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas'];

//	$scope.showAlert('success', 'Este &eacute; o corpo da mensagem de sucesso apresentada ao usu&aacute;rio!', 'Titulo sucesso');
//	$scope.showAlert('info', 'Este &eacute; o corpo da mensagem de informa&ccedil;&atilde;o apresentada ao usu&aacute;rio!', 'Titulo informacao');
//	$scope.showAlert('warning', 'Este &eacute; o corpo da mensagem de aten&ccedil;&atilde;o apresentada ao usu&aacute;rio!', 'Titulo atencao');
//	$scope.showAlert('error', 'Este &eacute; o corpo da mensagem de erro apresentada ao usu&aacute;rio!', 'Titulo erro');
}]);
