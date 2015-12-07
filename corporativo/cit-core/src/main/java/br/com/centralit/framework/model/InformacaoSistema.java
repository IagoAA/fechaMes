package br.com.centralit.framework.model;

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
 * <b>Title: InformacaoSistema</b>
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
 * @since 06/08/2015 - 11:00
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
public class InformacaoSistema {

	/** Atributo versao. */
	@JsonView({ Views.GenericView.class })
	private String versao;

	/**
	 * Retorna o valor do atributo <code>versao</code>
	 *
	 * @return <code>String</code>
	 */
	public String getVersao() {

		return versao;
	}

	/**
	 * Define o valor do atributo <code>versao</code>.
	 *
	 * @param versao
	 */
	public void setVersao(String versao) {

		this.versao = versao;
	}

}
