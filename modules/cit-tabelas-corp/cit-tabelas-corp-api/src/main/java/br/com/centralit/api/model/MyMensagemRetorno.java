package br.com.centralit.api.model;

import br.com.centralit.framework.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Classe responsável por dar um retorno de mensagem, dizendo se a mensagem é de SUCESSO, FALHA ou AVISO.
 * 
 * @author geovane.filho
 *
 */
public class MyMensagemRetorno {

	public static enum TipoMensagemRetorno {
        success("success"), error("error"), warning("warning");
        
        @JsonView({ Views.GenericView.class })
        private final String name;       

        private TipoMensagemRetorno(String name) {
            this.name = name;
        }

        public boolean equalsName(String otherName){
            return (otherName == null)? false:this.name.equals(otherName);
        }

        public String toString(){
           return this.name;
        }
    }
	
	@JsonView({ Views.GenericView.class })
	public TipoMensagemRetorno tipoMensagem;
	
	@JsonView({ Views.GenericView.class })
	public String mensagem;

	public MyMensagemRetorno(TipoMensagemRetorno tipoMensagem, String mensagem) {
		super();
		this.tipoMensagem = tipoMensagem;
		this.mensagem = mensagem;
	}
}
