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
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3" style="margin-top: 32px;">
					<div class="alert alert-info">
						${response}
					</div>
					
					<div class="block">
						<a href="/cit-portal-web">
							<i class="fa fa-arrow-left"></i> Voltar para a página de login
						</a>
					</div>
				</div><!-- .col -->
			</div><!-- .row -->
		</div><!-- .container-fluid -->
	</body>
</html>