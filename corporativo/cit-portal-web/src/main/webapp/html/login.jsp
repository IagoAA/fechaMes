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
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

		<!-- STYLESHEET APP -->
		<link rel="stylesheet" href="assets/css/bootstrap-layout.css" />
		<link rel="stylesheet" href="assets/css/layout.css" />

		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-page">
		<div id="content">
			<div class="login-container">
				<div class="login-logo">
					<img src="/cit-portal-web/assets/css/images/ati-logo.png" alt="ATI" />
				</div><!-- .login-logo -->

				<form id="login-box" class="login-form show" method="post" onsubmit="return login();" action="j_spring_security_check">
					<input type="hidden" name="j_remenberme" value="true" />
					<c:if test="${param.error == 403}">
						<div class="alert alert-warning" role="alert">
							Usuário/senha inválidos!
						</div>
					</c:if>

					<fieldset>
	                    <div class="form-group has-feedback">
	                        <label class="control-label" for="j_username">Login</label>
	                        <input name="j_username" id="j_username" type="text" class="form-control" placeholder="Informe o seu login" />
	                        <span class="form-control-feedback fa fa-user"></span>
	                    </div><!-- .form-group -->

						<div class="form-group has-feedback">
	                        <label class="control-label" for="j_password">Senha</label>
	                        <input name="j_password" id="j_password" type="password" class="form-control" placeholder="Informe a sua senha" />
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
		<script type="text/javascript" src="assets/js/jquery.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		function login() {
			if(!$("#j_username").val()) {
				alert('Informe o seu login!');
				return false;
			}

			if(!$("#j_password").val()) {
				alert('Informe a sua senha!');
				return false;
			}

			// Cria um item "usuario" com valor "Thiago Belem"
			window.sessionStorage.setItem('j_username', $("#j_username").val());
			window.sessionStorage.setItem('j_password', $("#j_password").val());
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