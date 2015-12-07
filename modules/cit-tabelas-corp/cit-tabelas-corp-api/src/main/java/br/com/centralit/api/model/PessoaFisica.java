package br.com.centralit.api.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.centralit.framework.json.JsonCalendarSimpleDateDeserializer;
import br.com.centralit.framework.json.JsonCalendarSimpleDateSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObject;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 * 
 * <p>
 * <b>Company: </b> Central IT - Governança Corporativa -
 * </p>
 * 
 * <p>
 * <b>Title: </b>PessoaFisica
 * </p>
 * 
 * <p>
 * <b>Description: </b>Entidade<code>PessoaFisica</code>
 * </p>
 * 
 * @since 27/11/2014 - 15:38:55
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Entity
public class PessoaFisica extends PersistentObject {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 866844945337604587L;

	@Id
	@JsonView({ Views.GenericView.class })
	protected Long id;

	/** Atributo rg. */
	@JsonView({ Views.PessoaEditView.class })
	private String rg;

	/** Atributo cpf. */
	@JsonView({ Views.PessoaEditView.class, Views.ContratoEditView.class })
	private String cpf;

	/** Atributo tituloEleitor. */
	@JsonView({ Views.PessoaEditView.class })
	private String tituloEleitor;

	/** Atributo numeroPassaporte. */
	@JsonView({ Views.PessoaEditView.class })
	private String numeroPassaporte;

	/** Atributo dataNascimento. */
	@JsonView({ Views.PessoaEditView.class })
	@JsonSerialize(using = JsonCalendarSimpleDateSerializer.class)
	@JsonDeserialize(using = JsonCalendarSimpleDateDeserializer.class)
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	/** Atributo nomeMae. */
	@JsonView({ Views.PessoaEditView.class })
	private String nomeMae;

	/** Atributo nomePai. */
	@JsonView({ Views.PessoaEditView.class })
	private String nomePai;

	/** Atributo dominioSexo. */
	/** Atributo dominioPessoa. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dominio_sexo")
	@JsonView({ Views.PessoaEditView.class })
	private Dominio dominioSexo;

	/** Atributo dominioEstadoCivil. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dominio_estado_civil")
	@JsonView({ Views.PessoaEditView.class })
	private Dominio dominioEstadoCivil;

	@MapsId
	@OneToOne(mappedBy = "pessoaFisica")
	private Pessoa pessoa;

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 * 
	 * @param nome
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>rg</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getRg() {

		return rg;
	}

	/**
	 * Define o valor do atributo <code>rg</code>.
	 * 
	 * @param rg
	 */
	public void setRg(String rg) {

		this.rg = rg;
	}

	/**
	 * Retorna o valor do atributo <code>tituloEleitor</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getTituloEleitor() {

		return tituloEleitor;
	}

	/**
	 * Define o valor do atributo <code>tituloEleitor</code>.
	 * 
	 * @param tituloEleitor
	 */
	public void setTituloEleitor(String tituloEleitor) {

		this.tituloEleitor = tituloEleitor;
	}

	/**
	 * Retorna o valor do atributo <code>numeroPassaporte</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNumeroPassaporte() {

		return numeroPassaporte;
	}

	/**
	 * Define o valor do atributo <code>numeroPassaporte</code>.
	 * 
	 * @param numeroPassaporte
	 */
	public void setNumeroPassaporte(String numeroPassaporte) {

		this.numeroPassaporte = numeroPassaporte;
	}

	/**
	 * Retorna o valor do atributo <code>dataNascimento</code>
	 * 
	 * @return <code>Calendar</code>
	 */
	public Calendar getDataNascimento() {

		return dataNascimento;
	}

	/**
	 * Define o valor do atributo <code>dataNascimento</code>.
	 * 
	 * @param dataNascimento
	 */
	public void setDataNascimento(Calendar dataNascimento) {

		this.dataNascimento = dataNascimento;
	}

	/**
	 * Retorna o valor do atributo <code>nomeMae</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNomeMae() {

		return nomeMae;
	}

	/**
	 * Define o valor do atributo <code>nomeMae</code>.
	 * 
	 * @param nomeMae
	 */
	public void setNomeMae(String nomeMae) {

		this.nomeMae = nomeMae;
	}

	/**
	 * Retorna o valor do atributo <code>nomePai</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNomePai() {

		return nomePai;
	}

	/**
	 * Define o valor do atributo <code>nomePai</code>.
	 * 
	 * @param nomePai
	 */
	public void setNomePai(String nomePai) {

		this.nomePai = nomePai;
	}

	/**
	 * Retorna o valor do atributo <code>dominioSexo</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioSexo() {

		return dominioSexo;
	}

	/**
	 * Define o valor do atributo <code>dominioSexo</code>.
	 * 
	 * @param dominioSexo
	 */
	public void setDominioSexo(Dominio dominioSexo) {

		this.dominioSexo = dominioSexo;
	}

	/**
	 * Retorna o valor do atributo <code>dominioEstadoCivil</code>
	 * 
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioEstadoCivil() {

		return dominioEstadoCivil;
	}

	/**
	 * Define o valor do atributo <code>dominioEstadoCivil</code>.
	 * 
	 * @param dominioEstadoCivil
	 */
	public void setDominioEstadoCivil(Dominio dominioEstadoCivil) {

		this.dominioEstadoCivil = dominioEstadoCivil;
	}

	/**
	 * Retorna o valor do atributo <code>cpf</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getCpf() {

		return cpf;
	}

	/**
	 * Define o valor do atributo <code>cpf</code>.
	 * 
	 * @param cpf
	 */
	public void setCpf(String cpf) {

		this.cpf = cpf;
	}

	/**
	 * Retorna o valor do atributo <code>pessoa</code>
	 * 
	 * @return <code>Pessoa</code>
	 */
	public Pessoa getPessoa() {

		return pessoa;
	}

	/**
	 * Define o valor do atributo <code>pessoa</code>.
	 * 
	 * @param pessoa
	 */
	public void setPessoa(Pessoa pessoa) {

		this.pessoa = pessoa;
	}

}
