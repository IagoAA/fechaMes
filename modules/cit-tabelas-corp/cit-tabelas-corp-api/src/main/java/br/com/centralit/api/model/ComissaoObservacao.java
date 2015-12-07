package br.com.centralit.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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
 * <b>Title: ComissaoObservacao</b>
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
 * @since 28/08/2015 - 09:55:57
 * 
 * @version 1.0.0
 * 
 * @author rogerio.cassimiro
 * 
 */
@Entity
public class ComissaoObservacao extends Observacao{

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -8597449174758202538L;
	
	/** Atributo comissao. */
	@ManyToOne(fetch = FetchType.LAZY)
	private Comissao comissao;

	/**
	 * Retorna o valor do atributo <code>comissao</code>
	 * 
	 * @return <code>Comissao</code>
	 */
	public Comissao getComissao() {

		return comissao;
	}

	/**
	 * Define o valor do atributo <code>comissao</code>.
	 * 
	 * @param comissao
	 */
	public void setComissao(Comissao comissao) {

		this.comissao = comissao;
	}

}
