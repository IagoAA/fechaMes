package br.com.centralit.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.centralit.framework.json.CepDeserializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.model.arquitetura.PersistentObjectAudit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
 * <b>Title: </b>Endereco
 * </p>
 *
 * <p>
 * <b>Description: </b>Entidade<code>Endereco</code>
 * </p>
 *
 * @since 27/11/2014 - 14:47:51
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Entity
@JsonIgnoreProperties({ "$pais", "$regiao", "$estado", "$cidade", "$checked" })
public class Endereco extends PersistentObjectAudit {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 1071553793338194452L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Views.GenericView.class, Views.ContratoListView.class })
	private Long id;

	/** Atributo cep. */
	@Column(length = 15)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	@JsonDeserialize(using = CepDeserializer.class)
	private String cep;

	/** Atributo codigo. */
	@Column(length = 30)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class })
	private String codigo;

	/** Atributo complemento. */
	@Column(length = 100)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class })
	private String complemento;

	/** Atributo logradouro. */
	@Column(length = 100)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	private String logradouro;

	/** Atributo nome. */
	@Column(length = 300)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	private String nome;

	/** Atributo numero. */
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	private String numero;

	/** Atributo dominioTipoEndereco. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	private Dominio dominioTipoEndereco;

	/** Atributo cidade. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	public Cidade cidade;

	/** Atributo bairro. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.PessoaEditView.class, Views.OrganizacaoListView.class, Views.EnderecoEditView.class})
	public Bairro bairro;

	/** Atributo pessoa. */
	@ManyToOne(fetch = FetchType.LAZY)
	public Pessoa pessoa;

	/** Atributo latitude. */
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class })
	private BigDecimal latitude;

	/** Atributo longitude. */
	@JsonView({ Views.EnderecoListView.class, Views.LocalizacaoListView.class, Views.OrganizacaoListView.class })
	private BigDecimal longitude;

	/** Atributo representanteLegal. */
	@JsonView({ Views.PessoaEditView.class, Views.EnderecoEditView.class, Views.EnderecoListView.class })
	private Boolean enderecoPrincipal;



	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public Endereco() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param cep
	 * @param logradouro
	 * @param nome
	 * @param numero
	 * @param dominioTipoEndereco
	 * @param cidade
	 * @param bairro
	 */
	public Endereco( String cep, String logradouro, String nome, String numero, Dominio dominioTipoEndereco, Cidade cidade, Bairro bairro ) {

		this();
		this.cep = cep;
		this.logradouro = logradouro;
		this.nome = nome;
		this.numero = numero;
		this.dominioTipoEndereco = dominioTipoEndereco;
		this.cidade = cidade;
		this.bairro = bairro;
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 *
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 *
	 * @param id
	 */
	public void setId(Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>cep</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCep() {

		return cep;
	}

	/**
	 * Define o valor do atributo <code>cep</code>.
	 *
	 * @param cep
	 */
	public void setCep(String cep) {

		this.cep = cep;
	}

	/**
	 * Retorna o valor do atributo <code>codigo</code>
	 *
	 * @return <code>String</code>
	 */
	public String getCodigo() {

		return codigo;
	}

	/**
	 * Define o valor do atributo <code>codigo</code>.
	 *
	 * @param codigo
	 */
	public void setCodigo(String codigo) {

		this.codigo = codigo;
	}

	/**
	 * Retorna o valor do atributo <code>complemento</code>
	 *
	 * @return <code>String</code>
	 */
	public String getComplemento() {

		return complemento;
	}

	/**
	 * Define o valor do atributo <code>complemento</code>.
	 *
	 * @param complemento
	 */
	public void setComplemento(String complemento) {

		this.complemento = complemento;
	}

	/**
	 * Retorna o valor do atributo <code>logradouro</code>
	 *
	 * @return <code>String</code>
	 */
	public String getLogradouro() {

		return logradouro;
	}

	/**
	 * Define o valor do atributo <code>logradouro</code>.
	 *
	 * @param logradouro
	 */
	public void setLogradouro(String logradouro) {

		this.logradouro = logradouro;
	}

	/**
	 * Retorna o valor do atributo <code>nome</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNome() {

		return nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 *
	 * @param nome
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * Retorna o valor do atributo <code>numero</code>
	 *
	 * @return <code>String</code>
	 */
	public String getNumero() {

		return numero;
	}

	/**
	 * Define o valor do atributo <code>numero</code>.
	 *
	 * @param numero
	 */
	public void setNumero(String numero) {

		this.numero = numero;
	}

	/**
	 * Retorna o valor do atributo <code>dominioTipoEndereco</code>
	 *
	 * @return <code>Dominio</code>
	 */
	public Dominio getDominioTipoEndereco() {

		return dominioTipoEndereco;
	}

	/**
	 * Define o valor do atributo <code>dominioTipoEndereco</code>.
	 *
	 * @param dominioTipoEndereco
	 */
	public void setDominioTipoEndereco(Dominio dominioTipoEndereco) {

		this.dominioTipoEndereco = dominioTipoEndereco;
	}

	/**
	 * Retorna o valor do atributo <code>cidade</code>
	 *
	 * @return <code>Cidade</code>
	 */
	public Cidade getCidade() {

		return cidade;
	}

	/**
	 * Define o valor do atributo <code>cidade</code>.
	 *
	 * @param cidade
	 */
	public void setCidade(Cidade cidade) {

		this.cidade = cidade;
	}

	/**
	 * Retorna o valor do atributo <code>bairro</code>
	 *
	 * @return <code>Bairro</code>
	 */
	public Bairro getBairro() {

		return bairro;
	}

	/**
	 * Define o valor do atributo <code>bairro</code>.
	 *
	 * @param bairro
	 */
	public void setBairro(Bairro bairro) {

		this.bairro = bairro;
	}

	public Pessoa getPessoa() {

		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {

		this.pessoa = pessoa;
	}

	/**
	 * Retorna o valor do atributo <code>latitude</code>
	 *
	 * @return <code>BigDecimal</code>
	 */
	public BigDecimal getLatitude() {

		return latitude;
	}

	/**
	 * Define o valor do atributo <code>latitude</code>.
	 *
	 * @param latitude
	 */
	public void setLatitude(BigDecimal latitude) {

		this.latitude = latitude;
	}

	/**
	 * Retorna o valor do atributo <code>longitude</code>
	 *
	 * @return <code>BigDecimal</code>
	 */
	public BigDecimal getLongitude() {

		return longitude;
	}

	/**
	 * Define o valor do atributo <code>longitude</code>.
	 *
	 * @param longitude
	 */
	public void setLongitude(BigDecimal longitude) {

		this.longitude = longitude;
	}

	/**
	 * Retorna o valor do atributo <code>enderecoPrincipal</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getEnderecoPrincipal() {

		return enderecoPrincipal;
	}


	/**
	 * Define o valor do atributo <code>enderecoPrincipal</code>.
	 *
	 * @param enderecoPrincipal
	 */
	public void setEnderecoPrincipal(Boolean enderecoPrincipal) {

		this.enderecoPrincipal = enderecoPrincipal;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (nome != null && !nome.isEmpty()) {
			builder.append(nome);
			builder.append(", ");
		}
		if (logradouro != null && !logradouro.isEmpty()) {
			builder.append(logradouro);
			builder.append(" ");
		}
		if (complemento != null && !complemento.isEmpty()) {
			builder.append(complemento);
			builder.append(" - ");
		}
		if (numero != null && !numero.isEmpty()) {
			builder.append(numero);
			builder.append(" - ");
		}
		if (cep != null && !cep.isEmpty()) {
			builder.append(cep);
			builder.append(" - ");
		}
		if (bairro != null) {
			builder.append(bairro.getNome());
			builder.append(" - ");
		}
		if (cidade != null) {
			builder.append(cidade.getNome());
			builder.append(" - ");
			builder.append(cidade.getEstado().getNome());
		}
		return builder.toString();
	}

}