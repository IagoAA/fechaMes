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

	for (i = 0; i < modulos.length; i++) {
		modulo = modulos[i];

		$.get(modulo.baseUrl)
		  .done(function( data ) {
		    	//alert( "Data Loaded: " + data );
		  });
	}
});
</script>
