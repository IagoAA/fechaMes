citApp.directive("dialog", [function() {
	return {
		scope : {
			customStyle : "@ngCustomStyle",
			idElement : "@ngId",
			title : "@ngTitle",
			label2: "@ngLabel2",
			funcao2: '&',
			label1: "@ngLabel1",
			funcao1: '&'
		},
		replace : true,
        transclude: true,
		restrict : "E",
		templateUrl : 'assets/js/angular/custom/directive/html/dialog.html',
	};
}]);



