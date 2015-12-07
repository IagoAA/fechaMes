package br.com.centralit.framework.model.arquitetura;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.com.centralit.framework.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

@MappedSuperclass
public abstract class PersistentObjectDominio extends PersistentObjectAudit {
	@Column(name = "ordem", columnDefinition = "integer default 0", nullable = false)
	@JsonView({Views.GenericView.class})
	protected Integer ordem;
	
	@Column(length=250)
	@JsonView({Views.GenericView.class})
	protected String nome;
	
	@Column(length=600)
	@JsonView({Views.GenericView.class})
	protected String descricao;
	
	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;
}
 
