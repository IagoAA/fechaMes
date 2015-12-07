<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<title>Citsmart GRP</title>

		<!-- FAVICON -->
		<link rel="shortcut icon" type="image/x-icon" href="/cit-portal-web/assets/css/img/favicon.ico" />

		<!-- STYLESHEET FRAMEWORKS -->
		<link rel="stylesheet" href="/cit-portal-web/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/cit-portal-web/assets/css/font-awesome.min.css" />

		<!-- STYLESHEET APP -->
		<link rel="stylesheet" href="/cit-portal-web/assets/css/bootstrap-layout.css" />
		<link rel="stylesheet" href="/cit-portal-web/assets/css/layout.css" />

		<!--[if lte IE 8]>
			<script src="assets/js/html5shiv.min.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-page">
		<div id="content">
			<div class="login-container">
				<div class="login-logo">
					<img src="/cit-portal-web/assets/css/images/ati-logo2.png" alt="ATI" />
				</div><!-- .login-logo -->

				<form id="login-box" class="login-form show" method="GET" onsubmit="return login();" action="https://<%= request.getServerName() %>:<%= request.getServerPort() %>/cas">
					<input type="hidden" name="rememberMe" value="true" />
					<input type="hidden" name="auto" value="true" />
        			<input type="hidden" name="service" value="https://<%= request.getServerName() %>:<%= request.getServerPort() %>/cit-portal-web/j_spring_cas_security_check" />
        			<input type="hidden" name="redirect" value="https://<%= request.getServerName() %>:<%= request.getServerPort() %>/cit-portal-web" />
        			<input type="hidden" name="renew" value="true" />
        			<input type="hidden" name="useSession" value="false" />        			 

					<c:if test="${param.error == 403}">
						<div class="alert alert-warning" role="alert">
							Usuário/senha inválidos!
						</div>
					</c:if>

					<fieldset>
	                    <div class="form-group has-feedback">
	                        <label class="control-label" for="username">Login</label>
	                        <input name="username" id="username" type="text" class="form-control" placeholder="Informe o seu login" />
	                        <span class="form-control-feedback fa fa-user"></span>
	                    </div><!-- .form-group -->

						<div class="form-group has-feedback">
	                        <label class="control-label" for="password">Senha</label>
	                        <input name="password" id="password" type="password" class="form-control" placeholder="Informe a sua senha" />
	                        <span class="form-control-feedback fa fa-lock"></span>
	                    </div><!-- .form-group -->

						<div class="block clearfix">
							<button type="submit" class="pull-right btn btn-primary">
								Acessar
							</button>
						</div>
					</fieldset>

					<ul class="login-links list-unstyled hidden">
						<li>
							<a href="javascript:;" data-target="#forgot-password-box" class="forgot-password-link">
								<i class="fa fa-angle-right"></i>
								Esqueci minha senha
							</a>
						</li>
					</ul>
				</form>

				<form id="forgot-password-box" action="requestRecoveryPassword" method="POST" onsubmit="return requestRecoveryPassword();" class="forgot-password-form hidden">
					<fieldset>
						<div class="form-group has-feedback">
	                        <label class="control-label">E-mail</label>
	                        <input type="email" name="email_recovery" id="email_recovery" class="form-control" placeholder="E-mail" />
	                        <span class="form-control-feedback fa fa-envelope"></span>
	                    </div><!-- .form-group -->

						<div class="block clearfix">
							<button type="submit" class="pull-right btn btn-success">
								Solicitar alteração de senha!
							</button>
						</div>
					</fieldset>

					<ul class="login-links list-unstyled">
						<li>
							<a href="javascript:;" data-target="#login-box" class="back-to-login-link">
								<i class="fa fa-arrow-left"></i>
								Voltar ao login
							</a>
						</li>
					</ul>
				</form>
			</div><!-- .login-container -->
		</div><!-- #content -->

		<!-- JQUERY -->
		<script type="text/javascript" src="/cit-portal-web/assets/js/jquery.min.js"></script>
		<script type="text/javascript" src="/cit-portal-web/assets/js/jsencryption.js"></script>
		<script type="text/javascript" src="/cit-portal-web/assets/js/jsencryptionform.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		function login() {
			if(!$("#username").val()) {
				alert('Informe o seu login!');
				return false;
			}

			if(!$("#password").val()) {
				alert('Informe a sua senha!');
				return false;
			}else{
				// criptograva a senha
				encryptFormText($("#password").val());
				console.log|($("#password").val());
			}
		}

		function requestRecoveryPassword() {
			if(!$('#email_recovery').val()) {
				alert('Informe o seu e-mail!');

				return false;
			}
		}
				
		jQuery(function($) {
			$(document).on('click', 'a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$(this).parents('form').removeClass('show').addClass('hidden');
				$(target).removeClass('hidden').addClass('show');
			});
		});
		</script>
	</body>
</html>
