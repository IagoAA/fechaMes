package br.com.centralit.framework.view;

import java.io.Serializable;

import br.com.centralit.framework.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

public class ResultResponseVH<T> implements Serializable {
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_FAILURE = "FAILURE";

	@JsonView({ Views.GenericView.class })
	private String status = STATUS_SUCCESS;

	@JsonView({ Views.GenericView.class })
	private String code = "200";

	@JsonView({ Views.GenericView.class })
	private String message = "Request processed successfully";

	@JsonView({ Views.GenericView.class })
	private T payload;

	public ResultResponseVH(T payload) {
		super();
		this.payload = payload;
	}

	public ResultResponseVH() {
		super();
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private static final long serialVersionUID = -5584906004219908759L;
}
