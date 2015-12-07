package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
 * <b>Title: </b>
 * </p>
 *
 * <p>
 * <b>Description: </b>
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
 * @since 04/12/2014 - 16:39:37
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */

@Entity
@Table(name = "parceiro_cliente")
public class Cliente extends Parceiro {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = 5444364769310678027L;

	/** Atributo funcao. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonView({ Views.PessoaEditView.class, Views.ServicoAutoCompleteView.class })
	private Servico servico;

	/**
	 * Retorna o valor do atributo <code>servico</code>
	 *
	 * @return <code>Servico</code>
	 */
	public Servico getServico() {
		return servico;
	}

	/**
	 * Define o valor do atributo <code>servico</code>.
	 *
	 * @param servico
	 */
	public void setServico(Servico servico) {
		this.servico = servico;
	}

}
