citApp.factory('FuncionarioAnexoRepository', ['RestangularTabelasCorp', 'AbstractRepository', function (restangularTabelasCorp, AbstractRepository) {

    function ContratoAnexoRepository() {

		this.downloadArquivo = function(idContratoAnexo) {
			return restangularTabelasCorp.one('rest/funcionarioAnexo/downloadArquivo').get({"idContratoAnexo": idContratoAnexo}).then();
		};

    	this.listarAnexos = function(idFuncionario) {
    		return restangularTabelasCorp.one('rest/funcionarioAnexo').getList("listarAnexos", {"idFuncionario": idFuncionario}).then();
    	};

    	AbstractRepository.call(this, restangularTabelasCorp, 'rest/funcionarioAnexo');
    }

    AbstractRepository.extend(FuncionarioAnexoRepository);

    return new FuncionarioAnexoRepository();
}]);