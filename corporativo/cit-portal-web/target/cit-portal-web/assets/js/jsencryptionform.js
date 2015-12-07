var encoded = null;
var elementId = null;

function encryptFormText(password) {
	encoded = GibberishAES.enc(password, 'centralIT');
	$("#password").val(encoded);
};
