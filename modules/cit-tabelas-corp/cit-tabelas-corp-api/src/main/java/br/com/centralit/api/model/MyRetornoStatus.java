package br.com.centralit.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.centralit.framework.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Classe responsável por dar um retorno de função com maiores detalhes,
 * sendo possível verificar o 'status' da requisição para saber se ocorreu com sucesso ou não,
 * caso tenha ocorrido com sucesso o atributo 'data' contém o retorno esperado da função
 * e caso contrário o atributo 'mensagens' contém as possíveis mensagens de erro ou informação.
 * 
 * @author geovane.filho
 *
 */
public class MyRetornoStatus implements Serializable {

	private static final long serialVersionUID = 40208505106631497L;

	@JsonView({ Views.GenericView.class })
	public boolean status = false;
	
	@JsonView({ Views.GenericView.class })
	public Object data;
	
	@JsonView({ Views.GenericView.class })
	public List<MyMensagemRetorno> mensagens = new ArrayList<MyMensagemRetorno>();

	/**
	 * Construtor padrão com status FALSE (<code>false</code>) de retorno
	 */
	public MyRetornoStatus() {
		super();
	}
	/**
	 * Construtor padrão com status SUCESSO (<code>true</code>) de retorno, passando apenas o objeto de retorno esperado.
	 * 
	 * @param data Objeto de retorno esperado.
	 */
	public MyRetornoStatus(Object data) {
		super();
		this.status = true;
		this.data = data;
	}

	/**
	 * Construtor padrão com status FALSE de retorno, passando como retorno mensagens de erro ou aviso.
	 * 
	 * @param mensagens Lista contendo as mensagens de erro, informação e/ou aviso.
	 */
	public MyRetornoStatus(List<MyMensagemRetorno> mensagens) {
		super();
		this.mensagens = mensagens;
	}

	/**
	 * Construtor padrão livre para passar o status (TRUE ou FALSE) do retorno, o data com o retorno esperado
	 * e uma lista de mensagens de erro, informação e/ou aviso.
	 * 
	 * @param status Status <code>true</code> ou <code>false</code> do retorno.
	 * @param data Objeto esperado
	 * @param mensagens Lista de mensagens de erro e/ou aviso
	 */
	public MyRetornoStatus(boolean status, Object data, List<MyMensagemRetorno> mensagens) {
		super();
		this.status = status;
		this.data = data;
		this.mensagens = mensagens;
	}
}
