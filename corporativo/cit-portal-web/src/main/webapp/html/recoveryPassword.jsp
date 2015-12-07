<%@ page contentType="text/html; charset=UTF-8"%>  
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

	<body>
		<div class="container-fluid margin-top">
			<c:choose>
				<c:when test="${tokenInvalid == true}">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3">
						<div class="alert alert-danger" role="alert">
							Token inválido!
						</div>
						
						<div class="block">
							<a href="/cit-portal-web">
								<i class="fa fa-arrow-right"></i> Ir para a página de login
							</a>
						</div>
					</div>
				</div>
				</c:when>
				
				<c:otherwise>
				<form method="POST" action="resetPassword?token=${param.token}" onsubmit="return resetPassword();" enctype="application/x-www-form-urlencoded">
					<input type="hidden" name="token" id="token" value="${param.token}" />
					
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3">
							<h1 class="title" style="margin-bottom: 32px;">Recuperação de senha</h1>
							
							<c:if test="${error == true}">
							<div class="alert alert-danger">
								As senhas não são iguais!
							</div>
							</c:if>
							
							<div class="form-group">
		                        <label class="control-label">Senha</label>
		                        <input type="password" name="password" id="password" value="${password}" class="form-control" />
		                    </div><!-- .form-group -->
		                    
		                    <div class="form-group">
		                        <label class="control-label">Confirmar senha</label>
		                        <input type="password" name="password_confirm" id="password_confirm" value="${passwordConfirm}" class="form-control" />
		                    </div><!-- .form-group -->
		                    
		                    <div class="block text-right">
		                    	<button class="btn btn-primary">Alterar senha</button>
		                    </div>
						</div><!-- .col -->
					</div><!-- .row -->
				</form>
				</c:otherwise>
			</c:choose>
		</div>
		
		<!-- JQUERY -->
		<script type="text/javascript" src="assets/js/jquery.min.js"></script>
		
		<script type="text/javascript">
		function resetPassword() {
			if(!$('#password').val()) {
				alert('Informe a senha!');
				return false;
			}
			
			if(!$('#password_confirm').val()) {
				alert('Confirme a senha!');
				return false;
			}
			
			if($('#password').val() != $('#password_confirm').val()) {
				alert('As senhas não são iguais!');
				return false;
			}
			
			return true;
		}
		</script>
	</body>
</html>