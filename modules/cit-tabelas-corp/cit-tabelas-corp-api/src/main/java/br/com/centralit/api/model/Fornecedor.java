package br.com.centralit.api.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.centralit.framework.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

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
 * <b>Title: </b> Fornecedor
 * </p>
 *
 * <p>
 * <b>Description: </b> Entidade<code>Fornecedor</code>
 * </p>
 *
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 26/11/2014 - 11:07:44
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Entity
public class Fornecedor extends Parceiro {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 7622071152065154296L;

	/** Atributo comprasEletronicas. */
	@JsonView({ Views.PessoaEditView.class })
	private Boolean comprasEletronicas;

	/** Atributo estrangeiro. */
	@JsonView({ Views.PessoaEditView.class })
	private Boolean estrangeiro;

	/** Atributo observacoes. */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fornecedor", cascade = CascadeType.ALL)
	@JsonView({ Views.PessoaEditView.class })
	private Collection<FornecedorObservacao> observacoes;

	@JsonView({ Views.FornecedorFindView.class, Views.ContratoEditView.class })
	public String getNome() {

		// TODO Auto-generated method stub
		return super.getPessoa().getNome();
	}

	/**
	 * Retorna o valor do atributo <code>observacoes</code>
	 *
	 * @return <code>Collection<FornecedorObservacao></code>
	 */
	public Collection<FornecedorObservacao> getObservacoes() {

		return observacoes;
	}

	/**
	 * Define o valor do atributo <code>observacoes</code>.
	 *
	 * @param observacoes
	 */
	public void setObservacoes(Collection<FornecedorObservacao> observacoes) {

		this.observacoes = observacoes;
	}

	/**
	 * Retorna o valor do atributo <code>comprasEletronicas</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getComprasEletronicas() {

		return comprasEletronicas;
	}

	/**
	 * Define o valor do atributo <code>comprasEletronicas</code>.
	 *
	 * @param comprasEletronicas
	 */
	public void setComprasEletronicas(Boolean comprasEletronicas) {

		this.comprasEletronicas = comprasEletronicas;
	}

	/**
	 * Retorna o valor do atributo <code>estrangeiro</code>
	 *
	 * @return <code>Boolean</code>
	 */
	public Boolean getEstrangeiro() {

		return estrangeiro;
	}

	/**
	 * Define o valor do atributo <code>estrangeiro</code>.
	 *
	 * @param estrangeiro
	 */
	public void setEstrangeiro(Boolean estrangeiro) {

		this.estrangeiro = estrangeiro;
	}

}
