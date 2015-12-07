<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="assets/js/jquery.min.js"></script>

<!-- TEMPLATE MODAL EXCLUIR -->
<script type="text/javascript">
$.ajax({
    url: "/cit-portal-web/modulos"
}).then(function(modulos) {
	// SALVA OS MODULOS NA SESSION PARA PODER GERAR DINAMICAMENTE OS REST'S
	window.sessionStorage.setItem('modulos', JSON.stringify(modulos));


	var j_username = window.sessionStorage.getItem('j_username');
	var j_password = window.sessionStorage.getItem('j_password');
	var params = {j_username: j_username ,j_password: j_password};

	for (i = 0; i < modulos.length; i++) {
		modulo = modulos[i];

			$.post(modulo.baseUrl + '/j_spring_security_check', params,
					function(data, status){
				    	console.log("Data: " + data + "\nStatus: " + status);
				});
	}

	window.sessionStorage.removeItem("j_username");
	window.sessionStorage.removeItem("j_password");
});
</script>
