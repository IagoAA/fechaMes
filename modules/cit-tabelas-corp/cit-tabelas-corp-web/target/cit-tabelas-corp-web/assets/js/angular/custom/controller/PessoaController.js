'use strict';

citApp.controller('PessoaController', ['$scope', 'PessoaRepository', 'DominioRepository', '$filter', 'FuncaoRepository', 'UsuarioRepository', '$translate', '$timeout',
                                       '$injector', 'EstruturaOrganizacionalRepository', 'FileUploader', 'ServicoRepository', function PessoaController($scope, PessoaRepository, DominioRepository, $filter,
                                    		   FuncaoRepository, UsuarioRepository, $translate, $timeout, $injector, EstruturaOrganizacionalRepository, FileUploader, ServicoRepository) {

    $scope.funcionario = {
        				anexos: []
        			  };


	$scope.isVisualizar = false;

	$scope.edit = false;

	$scope.tiposPessoa = {};

	$scope.tiposPorte = {};

	$scope.tiposAbrangencia = {};

	$scope.dominiosSexo = {};

	$scope.dominiosEstadoCivil = {};

	$scope.pessoaVH = {};

	$scope.contato =  {};

	$scope.MASK_CNPJ = "99.999.999/9999-99";

	$scope.MASK_CPF = "999.999.999-99";

	$scope.MASK_TELEFONE = "(99) 9999?9-9999";

	// Propriedades Tab Parceiro

	$scope.dominiosTipoParceiroSelecionados = [];

	$scope.dominioTipoParceiro = {};

	$scope.apresentarColaborador = false;

	$scope.apresentarOrgaoExterno = false;

	$scope.apresentarPortador = false;

	$scope.apresentarFornecedor = false;

	$scope.apresentarSeguradora = false;

	$scope.apresentarCliente = false;

	$scope.apresentarFuncionario = false;

	$scope.colaborador = {};

	$scope.orgaoExterno = {};

	$scope.portador = {};

	$scope.fornecedor = {};

	$scope.seguradora = {};

	$scope.cliente = {};

	$scope.funcionario = {};

	$scope.fornecedorObservacao = {};

	$scope.fornecedorRamoAtividade = {};

	$scope.contatoInvalid = false;

	$scope.apresentarBotaoBloquear = false;

	$scope.moduloAdmMateriaisAtivo = false;

	$scope.pessoa  = {

			telefones : [],

			enderecos : [],
			contatos : []
	};

	$scope.admMateriaisAtivo = $scope.isModuloAtivo("/cit-adm-materiais-web");

	// listar estruturas
   $scope.findEstrutura = function(value){
	   return EstruturaOrganizacionalRepository.listarEstruturasOrganizacionaisPorOrganizacao(value, $scope.usuarioLogado.organizacao.id).then(function(result){
		   return result;
	   });
   };

	//Obter os dominioPessoa
	DominioRepository.findAllDominio('tipoPessoa').then(function(result) {
		$scope.tiposPessoa = result;
	});

	//Obter os dominioPorte
	DominioRepository.findAllDominio('tipoPorte').then(function(result) {
		$scope.tiposPorte = result;
	});

	//Obter os dominioAbrangencia
	DominioRepository.findAllDominio('tipoAbrangencia').then(function(result) {
		$scope.tiposAbrangencia = result;
	});

	//Obter os dominioSexo
	DominioRepository.findAllDominio('tipoSexo').then(function(result) {
		$scope.dominiosSexo = result;
	});

	//Obter os dominioEstadoCivil
	DominioRepository.findAllDominio('tipoEstadoCivil').then(function(result) {
		$scope.dominiosEstadoCivil = result;
	});

	//Obter os dominioTipoParceiro

	$scope.findAllDominioTipoParceiro = function(){

		$scope.tiposParceiro = [];

		DominioRepository.findAllDominio('tipoParceiro').then(function(result) {

			angular.forEach(result, function(dominio){

				if(dominio.codigo === 4 || dominio.codigo === 6 || dominio.codigo === 7){

					$scope.tiposParceiro.push(dominio);

				}

			});

		});

	};

	//Método responsável por remover a pessoa
	$scope.removerPessoa = function(){

		$scope.setLoadingRemove(true);

		 PessoaRepository.remove($scope.pessoa).then(function(result) {

			 $scope.setLoadingRemove(false);
			 $scope.resetForm();
			 $scope.showAlert("success",  $translate.instant('MSG.MG001'));

		 });

		 $scope.$modalConfirmInstance.dismiss('cancel');
	};

	//Método responsável por bloquear a pessoa
	$scope.bloquearPessoa = function(){

		// Verificar se o usuario informou a data bloqueio
		if(!$scope.pessoa.dataBloqueio){

			$scope.showAlert('error', $translate.instant('MSG.MG011'));
		}else{

			$scope.saveOrUpdate();
		}
	};

	// Salvar Pessoa
	$scope.saveOrUpdate = function() {

		if($scope.pessoa.dataBloqueio){
			$scope.showAlert('error', $translate.instant('VALIDACAO.REGISTRO_BLOQUEADO_NAO_PODE_SER_ALTERADO'));
			return;
		}

		$scope.formPessoa.$submitted = true;
			//Vefifica se o formPessoa está invalido, caso esteja envia um alerta para o usuario
			if($scope.formPessoa.$invalid){

				$scope.showAlert('error', $translate.instant('MSG.MN001'));

			}else{

				if($scope.colaborador.estruturaOrganizacional){
					delete $scope.colaborador.estruturaOrganizacional.classificacao;
					delete $scope.colaborador.estruturaOrganizacional.organizacao;
				}

				var pessoaVH = {

						pessoa       						: $scope.pessoa,
						colaborador  						: $scope.colaborador,
						orgaoExterno 						: $scope.orgaoExterno,
						portador     						: $scope.portador,
						fornecedor   						: $scope.fornecedor,
						seguradora							: $scope.seguradora,
						cliente								: $scope.cliente,
						funcionario							: $scope.funcionario,
						dominiosTipoParceiroSelecionados    : $scope.dominiosTipoParceiroSelecionados
				};

				 $scope.pessoa.parceiros = null;

				 $scope.setLoadingSalva(true);

				 PessoaRepository.save(pessoaVH).then(function(result) {

					 $scope.pessoa = result.originalElement;

					 $scope.apresentarBotaoBloquear = !$scope.pessoa.dataBloqueio;

					 $scope.dominiosTipoParceiroSelecionados = [];
					//Percorre a lista de parceiros vinculados a pessoa para montar na tela.
					angular.forEach($scope.pessoa.parceiros, function(parceiro) {

							$scope.adicionarParceiroEdicao(parceiro);

						});

					 $scope.setLoading(false);
					 $scope.showAlert("success",  $translate.instant('MSG.MG001'));
					 $scope.resetForm();
				});

			}

	};

	//Método responsável por desbloquear a pessoa
	$scope.desbloquearPessoa = function(){

		$scope.pessoa.dataBloqueio = null;

		$scope.saveOrUpdate();

	};

	//Método responsável por obter a Pessoa
	$scope.getPessoa = function(id, visualizar){

		$scope.formPessoa.$submitted = false;

		$scope.setLoadingGet(true);

		$scope.resetForm();

		$scope.edit = true;
		$timeout(function(){
			PessoaRepository.get(id).then(function(result) {

				$scope.pessoa = result.originalElement;

				$scope.isVisualizar = visualizar;

				$scope.apresentarBotaoBloquear = !$scope.pessoa.dataBloqueio;

				$scope.setLoading(false);
				//Percorre a lista de parceiros vinculados a pessoa para montar na tela.
				angular.forEach($scope.pessoa.parceiros, function(parceiro) {

					$scope.adicionarParceiroEdicao(parceiro);

				});
			});
		});
	};
	//Método responsável por adicionar o parceiro na tela de edição
	$scope.adicionarParceiroEdicao = function(parceiro){
		//Vefifica se o parceiro é do tipo colaborador
		if(parceiro.classeParceiro.dominioTipoParceiro.codigo == 1){

			$scope.apresentarColaborador = true;

			$scope.colaborador = parceiro;
		//Vefifica se o parceiro é do tipo orgaoExterno
		}else if(parceiro.classeParceiro.dominioTipoParceiro.codigo == 2){

			$scope.apresentarOrgaoExterno = true;

			$scope.orgaoExterno = parceiro;
		//Verifica se o parceiro é do tipo portador
		}else if(parceiro.classeParceiro.dominioTipoParceiro.codigo == 3){

			$scope.apresentarPortador = true;

			$scope.portador = parceiro;
		//Verifica se o parceiro é do tipo fornecedor
		}else if(parceiro.classeParceiro.dominioTipoParceiro.codigo == 4){

			$scope.apresentarFornecedor = true;

			$scope.fornecedor = parceiro;
		//Verifica se o parceiro é do tipo seguradora
		}else if(parceiro.classeParceiro.dominioTipoParceiro.codigo == 5){

			$scope.apresentarSeguradora = true;

			$scope.seguradora = parceiro;
		//Verifica se o parceiro é do tipo cliente
		}else if(parceiro.classeParceiro.dominioTipoParceiro.codigo == 6){

			$scope.apresentarCliente = true;

			$scope.cliente = parceiro;
		//Verifica se o parceiro é do tipo funcionario
		}else if(parceiro.classeParceiro.dominioTipoParceiro.codigo == 7){

			$scope.apresentarFuncionario = true;

			$scope.funcionario = parceiro;

		}

		$scope.dominiosTipoParceiroSelecionados.push(parceiro.classeParceiro.dominioTipoParceiro);

		$scope.tiposParceiro = $filter('idNotEqualDominio')($scope.tiposParceiro, $scope.dominiosTipoParceiroSelecionados);

	};

	//Método responsável por limpar os dados da Pessoa
	$scope.resetForm = function() {

		$scope.isVisualizar = false,

		$scope.findAllDominioTipoParceiro();

		$scope.dominiosTipoParceiroSelecionados = [];

		$scope.dominioTipoParceiro = {};

		$scope.apresentarColaborador = false;

		$scope.apresentarOrgaoExterno = false;

		$scope.apresentarPortador = false;

		$scope.apresentarFornecedor = false;

		$scope.apresentarSeguradora = false;

		$scope.apresentarCliente = false;

		$scope.apresentarFuncionario = false;

		$scope.colaborador = {};

		$scope.orgaoExterno = {};

		$scope.portador = {};

		$scope.fornecedor = {};

		$scope.seguradora = {};

		$scope.cliente = {};

		$scope.funcionario = {};

		$scope.contato = {};

		$scope.pessoa = {

				telefones : [],

				enderecos : [],

				contatos : [],
		};

		$timeout(function(){
			$scope.formPessoa.$submitted = false;
			$scope.formPessoa.$setPristine();

			if($scope.formPessoa['endereco.cep']) {
				$scope.formPessoa['endereco.cep'].$setViewValue('');
				$scope.formPessoa['endereco.cep'].$render();
			}

			if($scope.formPessoa['endereco.complemento']) {
				$scope.formPessoa['endereco.complemento'].$setViewValue('');
				$scope.formPessoa['endereco.complemento'].$render();
			}

			if($scope.formPessoa['endereco.logradouro']) {
				$scope.formPessoa['endereco.logradouro'].$setViewValue('');
				$scope.formPessoa['endereco.logradouro'].$render();
			}
			if($scope.formPessoa['endereco.numero']) {
				$scope.formPessoa['endereco.numero'].$setViewValue('');
				$scope.formPessoa['endereco.numero'].$render();
			}

			if($scope.formPessoa['pais']) {
				$scope.formPessoa['pais'].$setViewValue('');
				$scope.formPessoa['pais'].$render();
			}

			if($scope.formPessoa['regiao']) {
				$scope.formPessoa['regiao'].$setViewValue('');
				$scope.formPessoa['regiao'].$render();
			}

			if($scope.formPessoa['estado']) {
				$scope.formPessoa['estado'].$setViewValue('');
				$scope.formPessoa['estado'].$render();
			}

			if($scope.formPessoa['cidade']) {
				$scope.formPessoa['cidade'].$setViewValue('');
				$scope.formPessoa['cidade'].$render();
			}
			if($scope.formPessoa['bairro']) {
				$scope.formPessoa['bairro'].$setViewValue('');
				$scope.formPessoa['bairro'].$render();
			}
			if($scope.formPessoa['telefone.numero']) {
				$scope.formPessoa['telefone.numero'].$setViewValue('');
				$scope.formPessoa['telefone.numero'].$render();
			}

			if($scope.formPessoa['pessoa.email']) {
				$scope.formPessoa['pessoa.email'].$setViewValue('');
				$scope.formPessoa['pessoa.email'].$render();
			}

		});
	};

	//Método responsável por adicionar parceiro
	$scope.adicionarParceiro = function(dominioTipoParceiro){
		//Vefifica se o parceiro é do tipo colaborador
		if(dominioTipoParceiro.codigo == 1){

			$scope.apresentarColaborador = true;

			$scope.colaborador = {};

			$scope.activeOrgaoExterno = false;

			$scope.activePortador = false;

			$scope.activeFornecedor = false;

			$scope.activeSeguradora = false;

			$scope.activeCliente = false;

			$scope.activeFuncionario = false;

			$timeout(function() {

				$scope.activeColaborador = true;

			});


		//Vefifica se o parceiro é do tipo orgaoExterno
		}else if(dominioTipoParceiro.codigo == 2){

			$scope.apresentarOrgaoExterno = true;

			$scope.orgaoExterno = {};

			$scope.activeColaborador = false;

			$scope.activePortador = false;

			$scope.activeFornecedor = false;

			$scope.activeSeguradora = false;

			$scope.activeCliente = false;

			$scope.activeFuncionario = false;

			$timeout(function() {

				$scope.activeOrgaoExterno = true;

			});

		//Vefifica se o parceiro é do tipo portador
		}else if(dominioTipoParceiro.codigo == 3){

			$scope.apresentarPortador = true;

			$scope.portador = {};

			$scope.activeColaborador = false;

			$scope.activeOrgaoExterno = false;

			$scope.activeFornecedor = false;

			$scope.activeSeguradora = false;

			$scope.activeCliente = false;

			$scope.activeFuncionario = false;

			$timeout(function() {

				$scope.activePortador = true;
			});

		//Vefifica se o parceiro é do tipo fornecedor
		}else if(dominioTipoParceiro.codigo == 4){

			$scope.apresentarFornecedor = true;

			$scope.fornecedor = {};

			$scope.activeColaborador = false;

			$scope.activeOrgaoExterno = false;

			$scope.activePortador = false;

			$scope.activeSeguradora = false;

			$scope.activeCliente = false;

			$scope.activeFuncionario = false;

			$timeout(function() {

				$scope.activeFornecedor = true;
			});

		}else if (dominioTipoParceiro.codigo == 5){

			$scope.apresentarSeguradora = true;

			$scope.seguradora = {};

			$scope.activeColaborador = false;

			$scope.activeOrgaoExterno = false;

			$scope.activePortador = false;

			$scope.activeFornecedor = false;

			$scope.activeCliente = false;

			$scope.activeFuncionario = false;

			$timeout(function() {
				$scope.activeSeguradora = true;
			});
		} else if (dominioTipoParceiro.codigo == 6){

			$scope.apresentarCliente = true;

			$scope.cliente = {};

			$scope.activeColaborador = false;

			$scope.activeOrgaoExterno = false;

			$scope.activePortador = false;

			$scope.activeFornecedor = false;

			$scope.activeSeguradora = false;

			$scope.activeFuncionario = false;

			$timeout(function() {
				$scope.activeCliente = true;
			});

		} else if (dominioTipoParceiro.codigo == 7){

			$scope.apresentarFuncionario = true;

			$scope.funcionario = {};

			$scope.activeColaborador = false;

			$scope.activeOrgaoExterno = false;

			$scope.activePortador = false;

			$scope.activeFornecedor = false;

			$scope.activeCliente = false;

			$scope.activeSeguradora = false;

			$timeout(function() {
				$scope.activeFuncionario = true;
			});
		}

		//Verifica se o dominioTipoParceiro contem o originalElement
		if(dominioTipoParceiro.originalElement){

			$scope.dominiosTipoParceiroSelecionados.push(dominioTipoParceiro.originalElement);
		}else{

			$scope.dominiosTipoParceiroSelecionados.push(dominioTipoParceiro);
		}

		$scope.tiposParceiro = $filter('idNotEqualDominio')($scope.tiposParceiro, $scope.dominiosTipoParceiroSelecionados);

		$scope.dominioTipoParceiro = {};

		dominioTipoParceiro = {};

		$scope.formPessoa.$submitted = false;

	};

	// Método responsável por remove o parceiro através do codigo selecionado
	$scope.removerParceiro = function(){

		angular.forEach($scope.dominiosTipoParceiroSelecionados, function(dominioTipoParceiro) {
			//Verifica se o codigo do parceiro é igual ao codigoParceiroRemocao
			if(dominioTipoParceiro.codigo == $scope.codigoParceiroRemocao){
				//Verifica se o dominioTipoParceiro contem o originalElement
				if(dominioTipoParceiro.originalElement){

					$scope.dominiosTipoParceiroSelecionados.splice(($scope.dominiosTipoParceiroSelecionados.indexOf(dominioTipoParceiro.originalElement)), 1);
				}else{

					$scope.dominiosTipoParceiroSelecionados.splice(($scope.dominiosTipoParceiroSelecionados.indexOf(dominioTipoParceiro)), 1);

				}

				$scope.tiposParceiro.push(dominioTipoParceiro);

				$scope.removerAbaParceiro();

				$scope.removerEntidadeParceiro(dominioTipoParceiro);
			}
		});

		$scope.$modalConfirmInstance.dismiss('cancel');
	};

	// Método responsável por realizar a exclusão da entidade parceiro
	$scope.removerEntidadeParceiro = function(dominioTipoParceiro){
		//Vefifica se o dominioTipoParceiro é do tipo colaborador e se o colaborador contem id
		if(dominioTipoParceiro.codigo == 1 && $scope.colaborador.id){

			PessoaRepository.removeColaborador($scope.colaborador.id).then(function(result) {

				$scope.colaborador = {};

			});
		// Vefifica se o dominioTipoParceiro é do tipo orgaoExterno e se o orgaoExterno contem id
		}else if(dominioTipoParceiro.codigo == 2 && $scope.orgaoExterno.id){

			PessoaRepository.removeOrgaoExterno($scope.orgaoExterno.id).then(function(result) {

				$scope.orgaoExterno = {};

			});
		// Vefifica se o dominioTipoParceiro é do tipo portador e se o portador contem id
		}else if(dominioTipoParceiro.codigo == 3 && $scope.portador.id){

			PessoaRepository.removePortador($scope.portador.id).then(function(result) {

				$scope.portador = {};

			});
		// Vefifica se o dominioTipoParceiro é do tipo fornecedor e se o fornecedor contem id
		}else if(dominioTipoParceiro.codigo == 4 && $scope.fornecedor.id){

			PessoaRepository.removeFornecedor($scope.fornecedor.id).then(function(result) {

				$scope.fornecedor = {};

			});
		// Vefifica se o dominioTipoParceiro é do tipo seguradora e se a seguradora contem id
		}else if(dominioTipoParceiro.codigo == 5 && $scope.seguradora.id){

			PessoaRepository.removeSeguradora($scope.seguradora.id).then(function(result) {

				$scope.seguradora = {};

			});
		// Vefifica se o dominioTipoParceiro é do tipo cliente e se o cliente contem id
		}else if(dominioTipoParceiro.codigo == 6 && $scope.cliente.id){

			PessoaRepository.removeCliente($scope.cliente.id).then(function(result) {

				$scope.cliente = {};

			});
		// Vefifica se o dominioTipoParceiro é do tipo cliente e se o cliente contem id
		}else if(dominioTipoParceiro.codigo == 6 && $scope.funcionario.id){

			PessoaRepository.removeFuncionario($scope.funcionario.id).then(function(result) {

				$scope.funcionario = {};

			});
		}

	};

	//Método responsável por remover a aba de parceiro através do codigo do dominioTipoParceiro selecionado.
	$scope.removerAbaParceiro = function(){
		//Verifica se o codigoRemocao é referente ao Colaborador
		if($scope.codigoParceiroRemocao == 1){

			$scope.apresentarColaborador = false;

		//Verifica se o codigoRemocao é referente ao OrgaoExterno
		}else if($scope.codigoParceiroRemocao == 2){

			$scope.apresentarOrgaoExterno = false;
		//Verifica se o codigoRemocao é referente ao Portador
		}else if($scope.codigoParceiroRemocao == 3){

			$scope.apresentarPortador = false;
		//Verifica se o codigoRemocao é referente ao Fornecedor
		}else if($scope.codigoParceiroRemocao == 4){

			$scope.apresentarFornecedor = false;

		}else if($scope.codigoParceiroRemocao == 5){

			$scope.apresentarSeguradora = false;

		}else if($scope.codigoParceiroRemocao == 6){

			$scope.apresentarCliente = false;

		}else if($scope.codigoParceiroRemocao == 7){

			$scope.apresentarFuncionario = false;

		}

	};

	// Método responsável por listar as funcoes através do nome
	$scope.findFuncao =  function(value){
	   return FuncaoRepository.findFuncaoPorOrganizacao(value, $scope.usuarioLogado.organizacao.id).then(function(result) {
		   return result;
	   });
	};

	// Método responsável por listar as funcoes através do nome
	$scope.findServico =  function(value){
	   return ServicoRepository.findServicoPorOrganizacao(value, $scope.usuarioLogado.organizacao.id).then(function(result) {
		   return result;
	   });
	};

	// FORNECEDOR

	//Método responsável por editar observacao
	$scope.editObservacao = function(){

		angular.forEach($scope.fornecedor.observacoes, function(observacao, index) {

			if(observacao.$checked){

				$scope.fornecedorObservacao = observacao;

				$scope.fornecedorObservacao.$edit = true;
			}
		});

	};

	// Método responsável por adicionar observação
	$scope.adicionarObservacaoFornecedor = function(){
		//Verifica se a observacao do fornecedor não está vazia ou null
		 if(!$scope.fornecedor.observacoes){

			 $scope.fornecedor.observacoes = [];

		 }
		 //Verifica se o fornecedorObservacao não está sendo editado
		 if(!$scope.fornecedorObservacao.$edit){

			 var fornecedorObservacaoTemp = {};

			 angular.copy($scope.fornecedorObservacao, fornecedorObservacaoTemp);

			 $scope.fornecedor.observacoes.push(fornecedorObservacaoTemp);

		 }

		 $scope.fornecedorObservacao = {};

		 $scope.fornecedorObservacao.$edit = false;

	};
	//Método responsavel por selecionar a observacao
	$scope.checkObservacao = function (observacao) {

		$scope.removeCheckedObservacao();

		observacao.$checked = true;

	};
	//Método responsável por setar false em todos os $checked da observacao
	$scope.removeCheckedObservacao = function () {
		var observacoes = $scope.fornecedor.observacoes;

		observacoes.forEach(function (observacao) {
			observacao.$checked = false;
		});
	};
	//Método responsável por apresentar o dialog de confirmação de remoção da observacao
	$scope.apresentarDialogConfirmacaoRemocaoObservacao = function(){

		$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.removerObservacao});
	};

	// Método responsável por remover observacaoFornecedor
	$scope.removerObservacao = function(){

		angular.forEach($scope.fornecedor.observacoes, function(observacao, index) {
			//Verifica se a observacao está selecionada
			if(observacao.$checked){

				if(observacao.id){

					PessoaRepository.removeFornecedorObservacao(observacao.id).then(function(result) {

					});
				}

				$scope.fornecedor.observacoes.splice(index , 1);
			}
		});

		 $scope.$modalConfirmInstance.dismiss('cancel');
	};

	//Método responsavel por listar classificacaoMaterial do tipo Grupo
	$scope.findGrupo =  function(value){
		var restAngular = $injector.get(["ClassificacaoMaterialRepository"]);
		return restAngular.listarClassificacaoMaterial(value, 1).then(function(result) {
			return result;
		});
	};

	// Método responsável por classificacaoMaterial do tipo SubGrupo
	$scope.findSubGrupo =  function(value){
		var restAngular = $injector.get(["ClassificacaoMaterialRepository"]);
		return restAngular.listarFilhosPorDescricaoIdParent(value, $scope.fornecedorRamoAtividade.grupoFederalSupply.id).then(function(result) {
			return   $filter('idNotEqualClassificacaoMaterial')(result, $scope.fornecedor.fornecedorRamoAtividades);;
		});
	};

   //Método responsável por adicionar RamoAtividade
   $scope.adicionarRamoAtividade = function(){
	   //Vefifica se o fornecedorRamoAtividade contem o subGrupoFederalSupply
	   if($scope.fornecedorRamoAtividade.subGrupoFederalSupply){
		   //Verifica se a lista de fornecedorRamosAtividades não é null ou vazia
		   if(!$scope.fornecedor.fornecedorRamoAtividades){

			   $scope.fornecedor.fornecedorRamoAtividades = [];

		   }

			 var fornecedorRamoAtividadeTemp = {};

			 angular.copy($scope.fornecedorRamoAtividade, fornecedorRamoAtividadeTemp);

			 $scope.fornecedor.fornecedorRamoAtividades.push(fornecedorRamoAtividadeTemp);

			 $scope.fornecedorRamoAtividade = {};

	   }

   };
	//Método responsável por setar true no ramoAtividade selecionado
	$scope.checkRamoAtividade = function (ramoAtividade) {

		$scope.removeCheckedRamoAtividade();

		ramoAtividade.$checked = true;
	};
	//Método responsável por setar false em todos os ramoAtividade
	$scope.removeCheckedRamoAtividade = function () {
		var ramoAtividades =  $scope.fornecedor.fornecedorRamoAtividades;

		ramoAtividades.forEach(function (ramoAtividade) {
			ramoAtividade.$checked = false;
		});
	};

	//Método responsável por setar contato a ser excluido
	$scope.apresentarDialogConfirmacaoRemocaoRamoAtividade = function(){

		$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.removerFornecedorRamoAtividade});
	};

   //Método responsável por remover o fornecedorRamoAtividade
	$scope.removerFornecedorRamoAtividade = function(){
		//Percorre a lista de fornecedorRamoAtividades para encontrar  ramoAtividade selecionado
		angular.forEach($scope.fornecedor.fornecedorRamoAtividades, function(ramoAtividade, index) {
			//Verifica se o ramoAtividade está selecionado
			if(ramoAtividade.$checked){

				if(ramoAtividade.id){

					PessoaRepository.removeFornecedorRamoAtividade(ramoAtividade.id).then(function(result) {

					});
				}

				$scope.fornecedor.fornecedorRamoAtividades.splice(index , 1);
			}

		});

		 $scope.$modalConfirmInstance.dismiss('cancel');

	};

	// FIM FORNECEDOR

	//CONTATO

	//Método responsável por adicionar contato
	$scope.adicionarContato = function(){
		 //Verifica se a lista de contatos da pessoa não é null ou vazia
		 if(!$scope.pessoa.contatos){
				 $scope.pessoa.contatos = [];
		 }

		 if ($scope.existeRepresentanteLegal() && $scope.contato.representanteLegal === true){
			 $scope.showAlert('error', $translate.instant('VALIDACAO.REPRESENTANTE_LEGAL_UNICO'));
			 return
		 }

		 if ($scope.contato.representanteLegal === true){
			 if (!$scope.contato.cpf){
				 $scope.showAlert('error', $translate.instant('VALIDACAO.CPF_OBRIGATORIO_REPRESENTANTE_LEGAL'));
				 return
			 }
		 }

		 if (!CPF.isValid($scope.contato.cpf)) {
			 $scope.showAlert('error', $translate.instant('VALIDACAO.CPF_INVALIDO_CONTATO'));
			 return;
		 }

		 //Vefifica se o nome do contato foi informado pelo usuario
		 if($scope.contato.nome){

			 $scope.nomeInvalid = false;
			 //Verifica se o contato está sendo editado.
			 if(!$scope.contato.$edit){

				 var contatoTemp = {};

				 angular.copy($scope.contato, contatoTemp);

				 $scope.pessoa.contatos.push(contatoTemp);
			 }

			 $scope.contato = {};

			 $scope.contato.$edit = false;
		 }else{
			 $scope.nomeInvalid = true;

		 }

	};

	//Método responsável por ver se alguns dos contatos já é representante legal, visto que somente um pode ser marcado como representante Legal
	$scope.existeRepresentanteLegal = function(){
		var result = false;
		angular.forEach($scope.pessoa.contatos, function(contato, index) {
			if (contato.representanteLegal){
				result = true;
			};

		});
		return result;
	};

	//Método responsável por setar contato a ser excluido
	$scope.apresentarDialogConfirmacaoRemocaoContato = function(){

		$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.removerContato});
	};
	//setar true no contato selecionado
	$scope.checkContato = function (contato) {

		$scope.removeCheckedContato();

		contato.$checked = true;
	};
	//Método responsável por setar false em todos os $checked
	$scope.removeCheckedContato = function () {
		var contatos =  $scope.pessoa.contatos;

		contatos.forEach(function (contato) {
			contato.$checked = false;
		});
	};

	//Método responsável por remover contato
	$scope.removerContato = function(){
		//Percorre a lista de contatos para encontrar o contato selecionado para remoção.
		angular.forEach($scope.pessoa.contatos, function(contato, index) {
			//Verifica se o contato foi selecionado
			if(contato.$checked){

				if(contato.id){

					PessoaRepository.removeContato(contato.id).then(function(result) {

					});
				}

				$scope.pessoa.contatos.splice(index , 1);
			}

		});

		 $scope.$modalConfirmInstance.dismiss('cancel');

	};


	//Método responsável por setar contato a ser excluido
	$scope.apresentarDialogConfirmacaoRemocaoParceiro = function(codigoParceiro){

		$scope.codigoParceiroRemocao = codigoParceiro;

		$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.removerParceiro});
	};
	//Método responsável por apresentar o dialog de confirmação de remoção de pessoa
	$scope.apresentarDialogConfirmacaoRemocaoPessoa = function(){

		$scope.$openModalConfirm({message: $translate.instant('LABEL.CONFIRMA_EXCLUSAO'), callback: $scope.removerPessoa});
	};

	//Método responsável por habilitar os campos e botão para editar os dados.
	$scope.habilitarSalvar = function(){

		$scope.isVisualizar = false;
	};

	$scope.fetchResult = function(){
		angular.element('#searchPessoa').scope().fetchResult();
	};

	$scope.findUsuario = function(value) {
		return UsuarioRepository.findByUsername(value).then(function(result) {
			return result;
		});
	};

	$scope.setUsuario = function (item) {
		if(item && item.id) {
			$scope.pessoa.usuario = item;
		}
	};

	$scope.limparUsuario = function () {
		$scope.pessoa.usuario = null;
	};

	//######################### UPLOAD ANEXO PARCEIRO FUNCIONARIO ###################################

    var uploader = $scope.uploader = new FileUploader({
        autoUpload: false,
        queueLimit: 30,
        url : '/cit-tabelas-corp-web/rest/funcionarioAnexo/uploadMultipleFile?idFuncionario='
	});

	// FILTERS
    uploader.filters.push({
    	name: 'fileFilter',
    	fn: function(item /*{File|FileLikeObject}*/, options) {
    		var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
    		return '|vnd.oasis.opendocument.text|jpg|png|jpeg|bmp|gif|vnd.ms-excel|csv|msword|vnd.openxmlformats-officedocument.wordprocessingml.document|odt|pdf|vnd.openxmlformats-officedocument.spreadsheetml.sheet|'.indexOf(type) !== -1;
    	}
    });

	// FILTERS
    uploader.filters.push({
    	name: 'tamanhoFilter',
    	fn: function(item /*{File|FileLikeObject}*/, options) {
    		return item.size < 20000000;
    	}
    });

    // CALLBACKS
    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
    	var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
    	if (item.size > 20000000){
    		$scope.showAlert("error", $translate.instant('CONTRATOS.MSG.ANEXO_TAMANHO_INVALIDO'));
    	}else if ('|vnd.oasis.opendocument.text|jpg|png|jpeg|bmp|gif|vnd.ms-excel|csv|msword|vnd.openxmlformats-officedocument.wordprocessingml.document|odt|pdf|vnd.openxmlformats-officedocument.spreadsheetml.sheet|'.indexOf(type) === -1){
    		$scope.showAlert("error", $translate.instant('CONTRATOS.MSG.ANEXO_FORMATO_INVALIDO'));
    	}else{
    		console.info('onWhenAddingFileFailed', item, filter, options);
    	}
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
    	uploader.clearQueue();
        console.info('onCompleteAll');
    };

	// Método responsável por salvar o Upload
	$scope.salvarUpload = function(result){
		//Percorre a lista de arquivos para setar na url o id do funcionario
		angular.forEach(uploader.queue, function(item, key) {

			item.url =  item.url + result.originalElement.id;

		});
		//Envia a requisição dos arquivos
		uploader.uploadAll();

	};

	// Método responsável por remover a entidade funcionarioAnexo
	$scope.removerUploadAnexoFuncionario = function(item){
		if(item.id){
			FuncionarioAnexoRepository.remove(item).then(function(result){
				var index = $scope.funcionario.anexos.indexOf(item);
				$scope.funcionario.anexos.splice(index, 1);
			});
		}else{
			var indexQ = uploader.queue.indexOf(item);
			uploader.queue.splice(indexQ, 1);
		}

	};

	$scope.visualizarArquivo = function(funcionarioAnexoSelecionado){

		if (!confuncionarioxoSelecionado.id) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_VISUALIZACAO'));
			return;
		}

		$scope.url = '/cit-tabelas-corp-web/rest/funcionarioAnexo/visualizarArquivo?idFuncionarioAnexo=' + funcionarioAnexoSelecionado.id;

		$scope.$openModal('visualizarArquivos', 'lg');

	};

	//############################ UPLOAD CONTRATO PARCEIRO FUNCIONARIO ######################################



}]);