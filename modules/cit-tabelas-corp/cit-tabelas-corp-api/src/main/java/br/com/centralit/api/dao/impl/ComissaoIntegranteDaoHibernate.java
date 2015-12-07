package br.com.centralit.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ComissaoIntegranteDao;
import br.com.centralit.api.model.ComissaoIntegrante;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;

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
 * <b>Title: DAO Hibernate de InventarioComissaoIntegrante </b>
 * </p>
 *
 * <p>
 * <b>Description: Implementação dos serviços de dados de InventarioComissaoIntegrante </b>
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
 * @since 29/12/2014 - 10:46:34
 *
 * @version 1.0.0
 *
 * @author geovane.filho
 *
 */
@Repository("inventarioComissaoIntegranteDao")
public class ComissaoIntegranteDaoHibernate extends CitGenericDAOImpl implements ComissaoIntegranteDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 */
	public ComissaoIntegranteDaoHibernate() {

		super(ComissaoIntegrante.class);
	}
	
	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por as lista de InventarioComissaoIntegrante por nome do colaborador e idComissao
	 *
	 * @author rogerio.cassimiro
	 *
	 * @param nome
	 * @param idComissao
	 * @return List<InventarioComissaoIntegrante>
	 */
	@Override
	public List<ComissaoIntegrante> findComissaoIntegrantePorColaborador(String nome, Long idComissao) {
		
		SearchSeven searchSeven = new SearchSeven();
		
		searchSeven.addFilterILike("integrante.pessoa.nome", "%"+nome+"%");
		
		searchSeven.addFilterEqual("comissao.id", idComissao);
		
		searchSeven.setMaxResults(10);
		
		return this.search(searchSeven, this.persistentClass);
	}
}
