<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" ng-app="citApp">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<link rel="shortcut icon" href="/cit-portal-web/assets/css/img/favicon.ico" />
		
		<title>ATI-AUDIT</title>

		<!-- DEFAULT FILE CSS -->
		<c:forEach items="${defaultFilesCSS}" var="defaultFileCSS">
			<link rel="stylesheet" href="${defaultFileCSS.caminho}"></link>
		</c:forEach>

		<!-- MENU CSS's -->
		<c:forEach items="${menuCSSFiles}" var="menuFile">
			<link rel="stylesheet" href="${menuFile.caminho}" data-menunome="${menuFile.menu.nome}"></link>
		</c:forEach>
		
		<style type="text/css">
		<c:forEach items="${menus}" var="menu">
			<c:if test="${menu.cssMenu != ''}">
				${menu.cssMenu}
			</c:if>
		</c:forEach>
		</style>
	</head>

	<!-- LOADING INITIAL ELEMENTS -->
	<div id="loading-initial">
		<div id="loading-initial-container">
	      	<div id="loading-initial-logo">
	  			<img src="/cit-portal-web/assets/css/images/logo-login.png" alt="ATI">
	      	</div>
	     	<div id="loading-initial-animate">
				<img src="/cit-portal-web/assets/css/images/loading-initial.gif" alt="">
	      	</div>
		</div>
	</div>
	<!-- LOADING INITIAL ELEMENTS -->

	<body id="citapp-controller" ng-controller="AppController" ng-class="{'nav-open': $navContainerShow}">
		<header id="header" class="fixed clearfix">
			<div id="header-container" class="clearfix">
				<menu></menu>
				
				<div id="logo">
					<img src="/cit-portal-web/assets/css/images/ati-logo-mini.gif" alt="ATI"/>
				</div><!-- #logo -->

				<div id="toolbar" class="clearfix">
					<ul>
						<li class="dropdown has-icon-only" dropdown>
							<a href="javascript:;" dropdown-toggle>
								<i class="fa fa-star yellow fa-lg"></i>
								<span class="badge badge-grey">{{paginasUsuarioFavoritoTemp.length}}</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar navbar-grey dropdown-menu dropdown-caret">
								<li class="dropdown-header">
									<i class="fa fa-star black"></i>
									<span class="black">
										{{paginasUsuarioFavoritoTemp.length}} <translate>LABEL.FAVORITOS</translate>
									</span>
								</li>

								<li class="dropdown-content scroll">
									<ul>
										<li ng-repeat="paginaUsuario in paginasUsuarioFavoritoTemp">
											<a href="javascript:;" ng-show="$index < LIMITE_FAVORITO_MENU" ng-click="openWorkspaceFavorito(paginaUsuario.pagina.nome, paginaUsuario.pagina.pagina, paginaUsuario.classePagina);" style="{{ paginaUsuario.pagina.cor ? 'color: ' + paginaUsuario.pagina.cor : '' }};">
												<i class="btn btn-xs btn-yellow no-hover fa fa-star"></i>
												{{paginaUsuario.pagina.nome}}
											</a>
										</li>
									</ul>
								</li>

								<li lass="dropdown-footer" ng-show="paginasUsuarioFavoritoTemp.length > 0">
									<a href="">
										<translate>LABEL.VER_TODOS_FAVORITOS</translate>
										<i class="fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="dropdown has-icon-only" dropdown>
							<a href="javascript:;" dropdown-toggle>
								<i class="fa fa-bell fa-lg"></i>
								<span class="badge badge-important">{{quantidadeNotificacoesNaoLidas}}</span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar navbar-grey dropdown-menu dropdown-caret">
								<li class="dropdown-header red">
									<i class="fa fa-refresh blue" tooltip="Atualizar lista de notificações" tooltip-placement="top" style="cursor:pointer" ng-click="findNotificacoes();"></i>
									<i class="fa fa-exclamation-triangle red"></i>
									{{quantidadeNotificacoesNaoLidas}} {{quantidadeNotificacoesNaoLidas > 1 ? 'Notificações' : 'Notificação'}}
								</li>

								<li class="dropdown-content scroll">
									<ul>
										<li ng-repeat="notificacao in listaNotificacoesUsuario | orderBy:dataCriacao:reverse">
											<a href="javascript:;" ng-click="visualizarNotificacao(notificacao)">
												{{notificacao.assunto}}
											</a>
										</li>
									</ul>
								</li>

								<li class="dropdown-footer">
									<a href="" ng-click="openNotificacoes()">
										Ver todas as notificações
										<i class="fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li>
							<span class="subtext">
								<b><translate>LABEL.REFERENCIA</translate></b>
								{{usuarioLogado.organizacao.dataReferenciaVigente | date : 'MMMM/yyyy'}}
							</span>
						</li>

						<li class="dropdown" dropdown>
							<a href="javascript:;" dropdown-toggle>
								<span class="subtext">
									<b><translate>LABEL.ORGANIZACAO</translate></b>
									{{usuarioLogado.organizacao.sigla}}
								</span> <i class="fa fa-angle-down" ng-show="usuarioLogado.organizacoes.length > 0"></i>
							</a>

							<ul class="dropdown-menu-right dropdown-menu dropdown-caret" ng-show="usuarioLogado.organizacoes.length > 0">
								<li ng-repeat="usuarioOrganizacaoItem in usuarioLogado.organizacoes">
									<a href="javascript:;" ng-click="atualizarOrganizacaoUsuario(usuarioOrganizacaoItem.organizacao)">
										{{usuarioOrganizacaoItem.organizacao.sigla}}
									</a>
								</li>
							</ul>
						</li>

						<li class="dropdown" dropdown>
							<a href="javascript:;" dropdown-toggle>
								<span class="subtext">
									<b><translate>LABEL.OLA</translate>,</b> {{usuarioLogado.username}}
								</span> <i class="fa fa-angle-down"></i>
							</a>

							<ul class="dropdown-menu-right dropdown-menu dropdown-caret">
								<li class="language-menu dropdown" ng-click="openDropdownLanguage($event);">
									<a href="javascript:;">
										<span ng-show="language === 'pt_BR'">
											<i class="flag-icon flag-icon-16x16 brazil"></i>
											Português
										</span>
										<i class="fa fa-angle-down pull-right"></i>
									</a>
								</li>

								<li class="divider"></li>

								<li>
									    <!-- a href="/cit-portal-web/logout">
											<i class="fa fa-power-off"></i>
											Sair
										</a -->
									   <a href="/cit-portal-web/j_spring_cas_security_logout">
											<i class="fa fa-power-off"></i>
											Sair
									   </a>
								</li>
								
								<li>
								  	<a href="" ng-click="visualizarInformacaoSistema()">
								  		<i class="fa fa-info-circle"></i>
										<translate>LABEL.SOBRE</translate>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div><!-- #toolbar -->
			</div><!-- #header-container -->

			<ul id="workspace-nav" class="nav nav-tabs">
				<li ng-repeat="workspace in workspaces" ng-class="{'active': workspace.active}" class="workspace-item {{ workspace.classePagina }}" style="width: {{100/workspaces.length}}%;">
					<a href="javascript:;" ng-click="activeWorkspace(workspace);" class="workspace-item-container" title="{{workspace.name}}">
						<span class="workspace-item-text">{{workspace.name}}</span> <span class="workspace-item-button" ng-click="removeWorkspace(workspace.id)" ><i class="fa fa-times"></i></span>
					</a>
				</li>
			</ul><!-- #workspace-nav -->
		</header><!-- #header -->

		<div id="content" class="fixed">
			<div id="workspace-content" class="container-fluid">
				<div ng-repeat="workspace in workspaces" class="{{ workspace.classePagina }}" ng-show="workspace.active">
					<script type="text/ng-template" id="templates/growl/growl.html">
						<div class="growl-container" ng-class="wrapperClasses()">
							<div class="growl-item alert" ng-repeat="message in growlMessages.directives[referenceId].messages" ng-class="alertClasses(message)">
								<button type="button" class="countdown" aria-hidden="true" ng-show="showCountDown(message)">{{message.countdown}}</button>

								<button type="button" class="close" data-dismiss="alert" aria-hidden="true" ng-click="growlMessages.deleteMessage(message)" ng-show="!message.disableCloseButton">
									<i class="fa fa-close"></i>
								</button>

								<button type="button" class="spin" data-dismiss="alert" aria-hidden="true" ng-click="stopTimeoutClose(message)" ng-class="{'active': message.close}" ng-show="message.ttl > 0">
									<i class="fa fa-thumb-tack"></i>
								</button>

								<h4 class="growl-title" ng-show="message.title" ng-bind="message.title"></h4>

								<div class="growl-message" ng-bind-html="message.text"></div>
							</div>
						</div>
					</script>

					<div growl reference="{{workspace.id}}"></div>
					<div growl reference="exception"></div>
					<div ng-include src='workspace.page'></div>
				</div>
			</div><!-- #workspace-content -->
		</div><!-- #content -->

		<!-- LOADING ELEMENTS -->
		<div id="loading-veil" ng-show="isLoading"></div>
		<div id="loading-feed-container" ng-show="isLoading">
			<div id="loading-feed">
				<div id="loading-feed-image"></div>
				<div id="loading-feed-text">{{mensagemLoading}}</div>
			</div>
		</div>
		<!-- /LOADING ELEMENTS -->

		<!-- MENU JS's -->
		<c:forEach items="${defaultFilesJS}" var="defaultFileJS">
			<script type="text/javascript" src="${defaultFileJS.caminho}"></script>
		</c:forEach>


		<!-- MENU JS's -->
		<c:forEach items="${menuJSFiles}" var="menuFile">
			<script src="${menuFile.caminho}" data-menunome="${menuFile.menu.nome}"></script>
		</c:forEach>
<!-- TEMPLATE MODAL EXCLUIR -->
<script type="text/javascript">
	<!-- Modulos -->
	<c:forEach items="${modulos}" var="modulo">
		citApp.factory('${modulo.restAngular}', function(Restangular) {
		  return Restangular.withConfig(function(RestangularConfigurer) {
			  RestangularConfigurer.setBaseUrl('${modulo.baseUrl}');
		  });
		});
	</c:forEach>
	google.load("visualization", "1", {packages: ["corechart"]});

	// GenericRestangular criado por Carlos Santos em 18/10 para resolver problema de minificação do RuntimeManagerUtils.js
	citApp.factory('GenericRestangular', function(Restangular) {
	  return Restangular.withConfig(function(RestangularConfigurer) {
		  RestangularConfigurer.setBaseUrl('');
	  });
	});	
	
</script>

<!-- DIALOG CONFIRMACAO-->
<script type="text/ng-template" id="modalConfirm.html">
	<div class="modal-header">
	    <button class="btn btn-clear" ng-click="$parent.$modalConfirm.callback();">
	        <i class="fa fa-check blue"></i>
	        {{$parent.$modalConfirm.confirmText}}
	    </button>

	    <button ng-click="$dismiss('cancel');" class="btn btn-clear">
	        <i class="fa fa-times red"></i>
	        {{$parent.$modalConfirm.cancelText}}
	    </button>
	</div><!-- .modal-header -->

	<div class="modal-body">
	    <h2 class="title-modal blue">{{$parent.$modalConfirm.title}}</h2>
	    {{$parent.$modalConfirm.message}}
	</div><!-- .modal-body -->
</script>

<!-- DIALOG NOTIFICACAO-->
<script type="text/ng-template" id="modalNotificacao.html">
	<div class="modal-header">
	    <button ng-click="$dismiss('cancel');" class="btn btn-clear">
	        <i class="fa fa-times red"></i>
	        {{$translate.instant('LABEL.FECHAR')}}
	    </button>
	</div><!-- .modal-header -->

	<div class="modal-body">
	    <h2 class="title-modal blue">{{$parent.$modalNotificacao.title}}</h2>
	    {{$parent.$modalNotificacao.message}}
	</div><!-- .modal-body -->
</script>

<!-- DIALOG RELATORIO-->
<script type="text/ng-template" id="relatorio.html"> 
	<div class="modal-header">
	   	<button ng-click="$dismiss('cancel');" class="btn btn-clear">
	       	<i class="fa fa-times red"></i>
	       	<translate>LABEL.FECHAR<translate>
	   	</button>
		</div>
		<div class="modal-body">
		<div>
			<embed ng-src="{{content}}" style="width:100%;height:600px;"></embed>
		</div>
	</div>
</script>

<!-- DIALOG ABOUT DO SISTEMA-->
<script type="text/ng-template" id="about.html"> 
	<div class="modal-header">
	   	<button ng-click="$dismiss('cancel');" class="btn btn-clear">
	       	<i class="fa fa-times red"></i>
	       	<translate>LABEL.FECHAR<translate>
	   	</button>
	</div><!-- .modal-heador -->

	<div class="modal-body">

	    <h2 class="title-modal blue">Sobre o Citsmart - GRP</h2>

			<div class="clearfix">
				<img src="/cit-portal-web/assets/css/images/logo.png" alt="Citsmart - GRP" class="float-left margin-right" />
				
				<h2>{{$translate.instant('LABEL.CITSMART_GRP')}}</h2>

				<div>Versão: {{informacaoSistema.versao}}</div>
			</div>

	</div><!-- .modal-body -->

	<div class="modal-footer">
		{{$translate.instant('LABEL.COPYRIGHT')}}
	</div><!-- .modal-footer -->

</script>

<!-- TAB RELATORIO-->
<script type="text/ng-template" id="relatorioTab.html">

<div id="idRelatorioTab" class="page-content clearfix">

    <div class="bar-buttons-action fixed">

        <div class="row">

            <div class="col-sm-8 text-left">

		    	<span>
		        	{{::titleReport}}
	    		</span>

            </div><!-- .col -->       
        </div><!-- .row -->
    </div><!-- .bar-buttons-action --> 

	<form name="formRelatorioTab">

		<div>
			<object data="{{::arrayContentReports[idRelatorio]}}" type="application/pdf" style="width:100%;height:85%;position:absolute;"></object>
		</div>
	</form>
</div>
	
</script>

	</body>
</html>