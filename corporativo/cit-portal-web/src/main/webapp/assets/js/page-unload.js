var backspaceIsPressed = false;
document.onkeydown = function (event) {
	var key = event.which ? event.which : event.keyCode;
	if(key === 8) {
		backspaceIsPressed = true;
	}
};

document.onkeyup = function (event) {
	var key = event.which ? event.which : event.keyCode;
	if(key === 8) {
		backspaceIsPressed = false;
	}
};

window.onbeforeunload = function() {
	if(backspaceIsPressed) {
		backspaceIsPressed = false;
		return 'Deseja realmente sair da pagina?';
	}
};