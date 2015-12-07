package br.com.centralit.api.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.FornecedorRamosAtividadeDao;
import br.com.centralit.api.model.FornecedorRamoAtividade;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Usuario;

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
 * @since 07/01/2015 - 14:24:10
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Repository("fornecedorRamoAtividadeDao")
public class FornecedorRamoAtividadeDaoHinernate extends CitGenericDAOImpl implements FornecedorRamosAtividadeDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 */
	public FornecedorRamoAtividadeDaoHinernate() {

		super(FornecedorRamoAtividade.class);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se existe algum <code>FornecedorRamoAtividade</code> vinculado à alguma das classificações passadas.
	 *
	 * @author geovane.filho
	 *
	 * @param idsClassificacoes Ids das classificações de materiais a verificarem se existe vinculos.
	 * 
	 * @return <code>true</code> caso exista vinculo de alguma classificação com algum ramo atividade e <code>false</code> caso contrário.
	 */
	@Override
	public boolean existeRamoAtividadeVinculadoAClassificacao(Set<Long> idsClassificacoes, Usuario usuarioLogado) {

		SearchSeven search = new SearchSeven();
		
		search.addFilterEqual("fornecedor.pessoa.organizacao.id", usuarioLogado.getOrganizacao().getId());
		
		search.addFilterIn("subGrupoFederalSupply.id", idsClassificacoes);
		
		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
