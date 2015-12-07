'use strict';

citApp.controller('EstruturaOrganizacionalController', ['$scope', '$filter', '$translate', 'EstruturaOrganizacionalRepository', 'DominioRepository',
                                                        'EstruturaOrganizacionalResponsavelRepository', 'LocalizacaoRepository', 'PessoaRepository',
                                                        'FuncaoRepository', '$timeout', '$injector',
		function EstruturaOrganizacionalController($scope, $filter, $translate, EstruturaOrganizacionalRepository, DominioRepository,
													EstruturaOrganizacionalResponsavelRepository, LocalizacaoRepository, PessoaRepository, FuncaoRepository,
													$timeout, $injector) {

	// CONSTANTES DOMINIO
	var TIPO_MATERIAL = 'tipoMaterial';
	var CODIGO_MATERIAL_PERMANENTE = 2;
	var CODIGO_TIPO_UNIDADE_GESTORA = 1;

	$scope.estruturaOrganizacional = {
			estruturasOrganizacionalResponsaveis : []
	};

	$scope.estruturaOrganizacionalPatrimonio = {};
	$scope.estruturaOrganizacionalAlmoxarifado = {};

	$scope.edit = true;

	// Metodo responsavel por resetar o formulario
	$scope.resetForm = function(estruturaOrganizacionalPai) {

		$scope.estruturaOrganizacional = {
				estruturasOrganizacionalResponsaveis : []
		};

		if (estruturaOrganizacionalPai) {
			$scope.estruturaOrganizacional.estruturaOrganizacionalParent = estruturaOrganizacionalPai;
		}

		$scope.estruturaOrganizacionalPatrimonio = {};
		$scope.estruturaOrganizacionalAlmoxarifado = {};

		$scope.edit = true;
		$timeout(function(){
			$scope.estruturaOrganizacionalForm.$submitted = false;
			$scope.estruturaOrganizacionalForm.$setPristine();
		});
	};

	//NOVO CONTROLLER
	// Atualiza pagina de pesquisa
	$scope.atualizaPaginaPesquisa = function () {
		angular.element('#searchEstruturaOrganizacional').scope().fetchResultEstrutura();
	};

	DominioRepository.findAllDominio('tipoEstruturaOrganizacional').then(function(result) {
		$scope.dominiosTipoEstruturaOrganizacional = result;
	});

	// AUTO COMPLETE ESTRUTURA ORGANIZACIONAL SUPERIOR
	// BUSCA A UNIDADE QUE SERÁ DEFINIDA COMO UNIDADE SUPERIOR
	$scope.findEstruturaSuperior = function(value) {
		return EstruturaOrganizacionalRepository.listarEstruturasOrganizacionaisPorOrganizacao(value, $scope.usuarioLogado.organizacao.id).then(function(result) {
			return $filter('idNotObject')(result, $scope.estruturaOrganizacional);
		});
	};

	// EXCLUI O RESPONSAVEL PELA ESTRUTURA ORGANIZACIONAL
	$scope.limparEstruturaOrganizacionalParent = function() {
		delete $scope.estruturaOrganizacional.estruturaOrganizacionalParent;
	};

	// Remove check saída responsável
	$scope.removeCheckedResponsavel = function () {
		var responsaveis = $scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis;

		responsaveis.forEach(function (saida) {
			saida.$checked = false;
		});
	};

	// Get check responsável
	$scope.getCheckedResponsavel = function () {
		var saidaChecked = null;
		var responsaveis = $scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis;

		responsaveis.forEach(function (saida) {
			if(saida.$checked) {
				saidaChecked = saida;
				return ;
			}
		});

		return saidaChecked;
	};

	// Check saída responsável
	$scope.checkResponsavel = function (saida, index) {
		$scope.removeCheckedResponsavel();

		saida.$checked = true;
		saida.$index = index;
	};

	// REMOVE ESTRUTURA ORGANIZACIONAL RESPONSAVEL
	$scope.removeEstruturaOrganizacionalResponsavel = function ( ) {

		var estruturaOrganizacionalResponsavel = $scope.getCheckedResponsavel();

		if(!estruturaOrganizacionalResponsavel) {
			$scope.showAlert('warning', $translate.instant('MSG.SELECIONE_UM_ITEM_PARA_SER_REMOVIDO'));
			return ;
		}

		$scope.$openModalConfirm({
			message: $translate.instant("MSG.MG004"),
			callback: function() {

				$scope.setLoading(true);
				// VERIFICA SE E PRECISO IR NA BASE PARA EXCLUIR O RESPONSAVEL PELA ESTRUTURA ORGANIZACIONAL
				if(estruturaOrganizacionalResponsavel.id !== undefined) {
					// REMOVE O RESPONSAVEL PELA ESTRUTURA ORGANIZACIONAL
					EstruturaOrganizacionalResponsavelRepository.remove(estruturaOrganizacionalResponsavel).then(function() {
						$scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis.splice(estruturaOrganizacionalResponsavel.$index, 1);
					});

				} else {

					$scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis.splice(estruturaOrganizacionalResponsavel.$index, 1);
				}

				$scope.showAlert("success", $translate.instant("MSG.EXCLUSAO_SUCESSO"));

				$scope.setLoading(false);

				$scope.$modalConfirmInstance.dismiss('cancel');
			}
		});
	};

	// AUTO COMPLETE DE LOCALIZAÇÃO
	// BUSCA A LOCALIZAÇÃO COM BASE NO VALUE PASSADO
	$scope.findLocalizacao = function(value) {
		return LocalizacaoRepository.listarLocalizacaoPorOrganizacao(value, $scope.usuarioLogado.organizacao.id).then(function(result) {
			return result;
		});
	};

	// SETA A LOCALIZACAO NA ESTRUTURA ORGANIZACIONAL
	$scope.setLocalizacao = function(item) {
		$scope.estruturaOrganizacional.localizacao = item;
	};

	// EXCLUI A LOCALIZACAO DA ESTRUTURA ORGANIZACIONAL
	$scope.limparLocalizacao = function() {
		delete $scope.estruturaOrganizacional.localizacao;
	};

	//Método responsável por abrir um novo workspace de cadastro de contaContabil.
	$scope.abrirNovoColaborador = function() {

			$scope.openWorkspaceIfNotOpen($translate.instant('LABEL.PESSOA'), '/cit-tabelas-corp-web/html/pessoa/pessoa.html', 'mod-blue');

			$timeout(function() {
				angular.element('#searchPessoa').scope().$showPageEditWorkspace(angular.element('#searchPessoa').scope().workspace);
				angular.element('#pessoaEdit').scope().resetForm();

		    }, 600);
	};

	// AUTO COMPLETE RESPONSAVEL ESTRUTURA ORGANIZACIONAL
	// BUSCA O RESPONSAVEL PELA ESTRUTURA ORGANIZACIONAL COM BASE NO VALUE PASSADO
	$scope.findPessoaEstruturaOrganizacionalResponsavel = function(value) {
		return PessoaRepository.findColaboradorPorNomeAndOrganizacao(value, $scope.usuarioLogado.organizacao.id).then(function(result) {
			return $filter('idNotEqualObj')(result, $scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis, "responsavel");
		});
	};

	// EXCLUI O RESPONSAVEL PELA ESTRUTURA ORGANIZACIONAL
	$scope.limparPessoaEstruturaOrganizacionalResponsavel = function() {
		delete $scope.pessoaEstruturaOrganizacionalResponsavel;
	};

	// ADICIONA RESPONSAVEL E FUNÇÃO
	$scope.addPessoaEFuncaoEstruturaOrganizacionalResponsavel = function() {
		// VERIFICA SE O RESPONSAVEL FOI SELECIONADO
		if(!$scope.pessoaEstruturaOrganizacionalResponsavel || !$scope.pessoaEstruturaOrganizacionalResponsavel.id) {
			$scope.showAlert('error', $translate.instant('VALIDACAO.SELECIONAR_UM_RESPONSAVEL'));
			return ;
		}

		var estruturaOrganizacionalResponsavel = {
			responsavel: $scope.pessoaEstruturaOrganizacionalResponsavel,
		};

		delete $scope.pessoaEstruturaOrganizacionalResponsavel;

		$scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis.push(estruturaOrganizacionalResponsavel);
	};

	// ADICIONA ESTRUTURA ORGANIZACIONAL COM PAI
	$scope.addEstruturaOrganizacionalComPai = function (parent) {

		$scope.estruturaOrganizacionalForm.$submitted = false;

		$scope.scrollToCadastro('#containerEstruturaOrganizacional');

		// Limpa as estruturas selecionada com click ($edit)
		limparEstruturaOrganizacionalSelecionadas();

		parent.$edit = true;

		$scope.estruturaOrganizacional = {
				estruturasOrganizacionalResponsaveis : [],
				estruturaOrganizacionalParent : parent
		};

		$scope.estruturaOrganizacional.idEstruturaOrganizacionalParent = parent.id;

		// Seleciona dias que a unidade requisitante pode requisitar
		selecionarDiasUnidadeRequisitantePodeRequisitar();
	};

	// OBTER DOMÍNIO STATUS TIPO MATERIAL PERMANENTE
	function obterDominioTipoMaterialPermanente() {
		DominioRepository.findAllDominioByCodigo(TIPO_MATERIAL, CODIGO_MATERIAL_PERMANENTE).then(function(result) {
			$scope.dominioTipoMaterialPermanente = result.originalElement;
		});
	}

	// CARREGA ESTRUTURA ORGANIZACIONAL
	$scope.getEstruturaOrganizacional = function (estruturaOrganizacional, edit) {
		$scope.setLoadingSalva(true);

		// Salva a estrutura organizacional
		EstruturaOrganizacionalRepository.get(estruturaOrganizacional.id).then(function(result) {
			$scope.estruturaOrganizacional = result.originalElement;

			if($scope.estruturaOrganizacional.dataBloqueio === null || $scope.estruturaOrganizacional.dataBloqueio === undefined){
				$scope.isBloquear = true;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = edit;
			}else{
				$scope.isBloquear = false;
				$scope.isDesbloquear = !$scope.isBloquear;
				$scope.edit = false;
			}
			$scope.pgEdit = edit;
			$scope.setLoading(false);
		});

		if ($scope.patrimonioAtivo && estruturaOrganizacional.id) {
			var restAngular = $injector.get(["EstruturaOrganizacionalPatrimonioRepository"]);
			restAngular.getByEstruturaOrganizacional(estruturaOrganizacional).then(function(result){
				$scope.estruturaOrganizacionalPatrimonio = result.originalElement;
			});
		}

		if ($scope.almoxarifadoAtivo && estruturaOrganizacional.id) {
			var restAngular = $injector.get(["EstruturaOrganizacionalAlmoxarifadoRepository"]);
			restAngular.getByEstruturaOrganizacional(estruturaOrganizacional).then(function(result){
				$scope.estruturaOrganizacionalAlmoxarifado = result.originalElement;
				selecionarDiasUnidadeRequisitantePodeRequisitar();
			});
		}
	};

	// METODO PARA VALIDAR SE A DATA INICIO E FIM INFORMADAS SAO VALIDAS
	$scope.validaDataInicioMaiorDataFim = function () {
		if($scope.estruturaOrganizacional.dataInicio && $scope.estruturaOrganizacional.dataFim) {
			var dataInicio = converterStringEmDate($filter('date')($scope.estruturaOrganizacional.dataInicio, 'dd/MM/yyyy'));
			var dataFim = converterStringEmDate($filter('date')($scope.estruturaOrganizacional.dataFim, 'dd/MM/yyyy'));

			if(dataInicio.getTime() > dataFim.getTime()){
				return true;
			} else {
				return false;
			}
		}
	};

	// SALVA O ESTRUTURAORGANIZACIONAL
	$scope.saveOrUpdate = function(){

		$scope.estruturaOrganizacionalForm.$submitted = true;

		// Se o formulário não for válido, exibe mensagem de erro
		if(!$scope.estruturaOrganizacionalForm.$valid) {
			$scope.showAlert('error', $translate.instant('MSG.MN001'));
			return ;
		}

		// VALIDA SE A DATA INICIO E MAIOR QUE A DATA FIM
		if($scope.validaDataInicioMaiorDataFim()) {
			$scope.showAlert('error', $translate.instant('VALIDACAO.CAMPO_DATA_INICIO_FIM'));
			return ;
		}

		// Se não houver data fim preenchido na interface, deleta o objeto dataFim
		if(!$scope.estruturaOrganizacional.dataFim) {
			delete $scope.estruturaOrganizacional.dataFim;
		}

		// Estrutura nova
		if($scope.estruturaOrganizacional.id === undefined) {
			// Salva a estrutura organizacional
			save();
			// Editando estrutura
		} else {
			if( $scope.estruturaOrganizacional.dataFim !== undefined && $scope.estruturaOrganizacional.dataFim !== '' && $scope.estruturaOrganizacional.dataFim !== null) {
				// Valida se data fim está sendo preenchida e exibe uma modal para confirmação do usuário
				confirmarDataFimESalvar();
			} else {
				// Salva a estrutura organizacional
				save();
			}
		}
	};

	//FUNCAO QUE ABRE UMA MODAL DE CONFIRMACAO DO USUARIO QUANDO SE TENTA SALVAR A ESTRUTURA COM DATA FIM.
	function confirmarDataFimESalvar() {
		$scope.$openModalConfirm({
			message:  $translate.instant('MSG.CONFIRMA_DATA_FIM'),
			callback: function() {
				// Salva a estrutura organizacional
				save();
				$scope.$modalConfirmInstance.dismiss('cancel');
			}
		});
	}

	// FUNCAO QUE SALVA ESTRUTURA
	function save() {

		$scope.setLoadingSalva(true);

		// Salva a estrutura organizacional
		EstruturaOrganizacionalRepository.save($scope.estruturaOrganizacional).then(function(result) {
			$scope.estruturaOrganizacional = result.originalElement;

			if ($scope.patrimonioAtivo) {
				if (!$scope.estruturaOrganizacionalPatrimonio.estruturaOrganizacional) {
					$scope.estruturaOrganizacionalPatrimonio.isAlmoxarifado = false;
					$scope.estruturaOrganizacionalPatrimonio.isLocalizadoraDeBem = false;
					$scope.estruturaOrganizacionalPatrimonio.estruturaOrganizacional = $scope.estruturaOrganizacional;
				}
				var restAngular = $injector.get(["EstruturaOrganizacionalPatrimonioRepository"]);
				restAngular.save($scope.estruturaOrganizacionalPatrimonio).then(function(result){
					$scope.estruturaOrganizacionalPatrimonio = result.originalElement;
				});
			}

			if ($scope.almoxarifadoAtivo) {
				if (!$scope.estruturaOrganizacionalAlmoxarifado.estruturaOrganizacional) {
					$scope.estruturaOrganizacionalAlmoxarifado.isAlmoxarifado = false;
					$scope.estruturaOrganizacionalAlmoxarifado.isUnidadeConsumidoraRequisitante = false;
					$scope.estruturaOrganizacionalAlmoxarifado.estruturaOrganizacional = $scope.estruturaOrganizacional;
				}
				var restAngular = $injector.get(["EstruturaOrganizacionalAlmoxarifadoRepository"]);
				restAngular.save($scope.estruturaOrganizacionalAlmoxarifado).then(function(result){
					$scope.estruturaOrganizacionalAlmoxarifado = result.originalElement;
				});
			}

			$scope.showAlert("success", $translate.instant('MSG.MG001'));

			$scope.estruturaOrganizacionalForm.$submitted = false;

			$scope.setLoading(false);
		});

	}

	// ESTRUTURA ORGANIZACIONAL = RESPONSAVEIS
	// MOVE A LINHA DA TABELA DE RESPONSAVEL PELA ESTRUTURA ORGANIZACIONAL PARA CIMA
	$scope.moveToUp = function(estruturaOrganizacionalResponsavel, index) {
		var aux,
			estruturasOrganizacionalResponsaveis = $scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis;

		$scope.estruturasOrganizacionalResponsaveisSelection = [];

		// VERIFICA SE HA ITEM ACIMA DELE
		if(estruturasOrganizacionalResponsaveis[index - 1] !== undefined) {
			aux = estruturasOrganizacionalResponsaveis[index - 1];
			estruturasOrganizacionalResponsaveis[index - 1] = estruturasOrganizacionalResponsaveis[index];
			estruturasOrganizacionalResponsaveis[index] = aux;
		}
	};

	// MOVE A LINHA DA TABELA DE RESPONSAVEL PELA ESTRUTURA ORGANIZACIONAL PARA BAIXO
	$scope.moveToDown = function(estruturaOrganizacionalResponsavel, index) {
		var aux,
			estruturasOrganizacionalResponsaveis = $scope.estruturaOrganizacional.estruturasOrganizacionalResponsaveis;

		$scope.estruturasOrganizacionalResponsaveisSelection = [];

		// VERIFICA SE HA ITEM ABAIXO DELE
		if(estruturasOrganizacionalResponsaveis[index + 1] !== undefined) {
			aux = estruturasOrganizacionalResponsaveis[index + 1];
			estruturasOrganizacionalResponsaveis[index + 1] = estruturasOrganizacionalResponsaveis[index];
			estruturasOrganizacionalResponsaveis[index] = aux;
		}
	};

	// REMOVE A ESTRUTURA ORGANIZACIONAL
	$scope.remove = function(estruturaOrganizacional) {
		// Valida se tem vinculo com outras entidades dos modulos de patrimonio/almoxarifado
		EstruturaOrganizacionalRepository.contemEntidadeVinculada(estruturaOrganizacional.id).then(function(result) {
			if(result){
				$scope.showAlert("warning", $translate.instant('VALIDACAO.ERRO_EXCLUSAO_ESTRUTURA'));

			}else{

				$scope.$openModalConfirm({
					message: estruturaOrganizacional.almoxarifado ? $translate.instant('MSG.REMOVER_ESTRUTURA_ALMOXARIFADO') : $translate.instant('MSG.MG004'),
					callback: function() {
						EstruturaOrganizacionalRepository.remove(estruturaOrganizacional).then(function() {

							$scope.estruturaOrganizacional = {};

							$scope.$modalConfirmInstance.dismiss('cancel');
							$scope.showAlert("success", $translate.instant('MSG.MG001'));
							angular.element('#searchEstruturaOrganizacional').scope().fetchResultEstrutura();

							$scope.resetForm();
						});
					}
				});

			}
		});

	};

	//############### CONTROLLER DAS DEFINIÇÕES DE PATRIMONIO
	$scope.patrimonioAtivo = $scope.isModuloAtivo("/citgrp-patrimonio-web");

	$scope.abreModalPatrimonio = function() {
		$scope.editPatrimonio = $scope.edit;
		$scope.$openModal('modal-estruturaOrganizacionalPatrimonio.html', 'lg');
	};

	$scope.salvarEstruturaPatrimonio = function(formDialogEstruturaPatrimonio) {
		formDialogEstruturaPatrimonio.$submitted = true;
		if(formDialogEstruturaPatrimonio.$valid){

			var restAngular = $injector.get(["EstruturaOrganizacionalPatrimonioRepository"]);
			restAngular.save($scope.estruturaOrganizacionalPatrimonio).then(function(result){
				$scope.estruturaOrganizacionalPatrimonio = result.originalElement;
			});

			$scope.$modalInstance.dismiss('cancel');

			$scope.showAlert("success", $translate.instant("MSG.SUCESSO_ESTRUTURA_PATRIMONIO"));

		}else{
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'));
		}
	};

	$scope.resetEstruturaPatrimonio = function() {
		var restAngular = $injector.get(["EstruturaOrganizacionalPatrimonioRepository"]);
		restAngular.getByEstruturaOrganizacional($scope.estruturaOrganizacional).then(function(result){
			$scope.estruturaOrganizacionalPatrimonio = result.originalElement;
		});
	};

	//############### CONTROLLER DAS DEFINIÇÕES DE ALMOXARIFADO
	$scope.almoxarifadoAtivo = $scope.isModuloAtivo("/cit-almoxarifado-web");

	$scope.abreModalAlmoxarifado = function() {
		$scope.editAlmoxarifado = $scope.edit;
		$scope.$openModal('modal-estruturaOrganizacionalAlmoxarifado.html', 'lg');
	};

	$scope.salvarEstruturaAlmoxarifado = function(formDialogEstruturaAlmoxarifado) {
		formDialogEstruturaAlmoxarifado.$submitted = true;
		if(formDialogEstruturaAlmoxarifado.$valid){

			// Se tiver com modulo almoxarifado ativado, obtem os dias selecionados na unidade requisitante
			$scope.estruturaOrganizacionalAlmoxarifado.diasQueUnidadeConsumidoraPodeFazerRequisicao = selecionarDiasUnidadeConsumidora();

			var restAngular = $injector.get(["EstruturaOrganizacionalAlmoxarifadoRepository"]);
			restAngular.save($scope.estruturaOrganizacionalAlmoxarifado).then(function(result){
				$scope.estruturaOrganizacionalAlmoxarifado = result.originalElement;
			});

			$scope.$modalInstance.dismiss('cancel');

			$scope.showAlert("success", $translate.instant("MSG.SUCESSO_ESTRUTURA_ALMOXARIFADO"));

		}else{
			$scope.showAlert('error', $translate.instant('VALIDACAO.ALERTA_OBRIGATORIOS'));
		}
	};

	$scope.resetEstruturaAlmoxarifado = function() {
		var restAngular = $injector.get(["EstruturaOrganizacionalAlmoxarifadoRepository"]);
		restAngular.getByEstruturaOrganizacional($scope.estruturaOrganizacional).then(function(result){
			$scope.estruturaOrganizacionalAlmoxarifado = result.originalElement;
			selecionarDiasUnidadeRequisitantePodeRequisitar();
		});
	};

	// FUNCAO QUE SELECIONA OS DIAS QUE UMA ESTRUTURA DO TIPO UNIDADE REQUISITANTE PODE REQUISITAR MATERIAIS
	function selecionarDiasUnidadeRequisitantePodeRequisitar() {
		$scope.diasRequisitados = [];
		var dia = {
				key : "",
				value : ""
			};
		// Valida se e uma unidade requisitante
		if($scope.estruturaOrganizacionalAlmoxarifado.isUnidadeConsumidoraRequisitante !== undefined && $scope.estruturaOrganizacionalAlmoxarifado.isUnidadeConsumidoraRequisitante
				&& $scope.estruturaOrganizacionalAlmoxarifado.diasQueUnidadeConsumidoraPodeFazerRequisicao !== undefined && $scope.estruturaOrganizacionalAlmoxarifado.diasQueUnidadeConsumidoraPodeFazerRequisicao !== null
					&& $scope.estruturaOrganizacionalAlmoxarifado.diasQueUnidadeConsumidoraPodeFazerRequisicao.length > 0) {

			// Obtem os dias salvos da unidade requisitante em uma lista temporaria
			var diasRequisitadosTemp = $scope.estruturaOrganizacionalAlmoxarifado.diasQueUnidadeConsumidoraPodeFazerRequisicao.split(';');

			$scope.diasRequisitados = [];

			$scope.diasCompletos = [];

			// Percorre a lista de dias temporarios e cria uma nova lista = split gera uma posicao a mais na lista
			for(var i = 0; i < diasRequisitadosTemp.length -1; i++) {
				$scope.diasRequisitados.push(diasRequisitadosTemp[i]);
			}

			// Percorre os 31 dias de um mes e seta como true com o que foi salvo na unidade requisitante
			for(var i = 1; i <=31; i++){
				dia.key = i;
				dia.value = false;
				// Percorre os dias requisitados e seta como true caso seja o mesmo dia na lista de dias salvos na unidade requisitante
				angular.forEach($scope.diasRequisitados, function(diaRequisitado){
					// Se o dia requisitado for igual ao dia do mes percorrido coloca true
					if(new Number(diaRequisitado).valueOf()=== i){
						dia.value = true;
					}
				});
				$scope.diasCompletos.push(angular.copy(dia));
			}
		} else {
			$scope.diasCompletos = [];
			// Percorre os 31 dias de um mes e seta como false os dias do mes
			for(var i = 1; i <=31; i++) {
				dia.key = i;
				dia.value = false;
				$scope.diasCompletos.push(angular.copy(dia));
			}
		}
	}

	// FUNCAO QUE SETA DIA REQUISITANTE NA LISTA DO MES COMO ATIVO/INATIVO
	$scope.setarDiaRequisicao = function(diaRequisitado) {
		$scope.diasCompletos[diaRequisitado].value = !$scope.diasCompletos[diaRequisitado].value;
	};

	// FUNCAO QUE PERCORRE OS 31 DIAS PARA OBTER OS DIAS QUE UNIDADE REQUISITANTE PODE REQUISITAR
	function selecionarDiasUnidadeConsumidora() {
		var diasQueUnidadeConsumidoraPodeFazerRequisicao = "";
		angular.forEach($scope.diasCompletos, function(dia){
			// Se dia estiver selecionado adiciona a lista a ser salva
			if(dia.value) {
				diasQueUnidadeConsumidoraPodeFazerRequisicao += (dia.key.toString()).concat(";");
			}
		});
		return diasQueUnidadeConsumidoraPodeFazerRequisicao === "" ? null : diasQueUnidadeConsumidoraPodeFazerRequisicao;
	}

}]);


