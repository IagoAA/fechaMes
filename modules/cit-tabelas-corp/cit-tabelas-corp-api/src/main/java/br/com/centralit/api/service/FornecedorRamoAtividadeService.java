package br.com.centralit.api.service;

import java.util.Set;

import br.com.centralit.api.model.FornecedorRamoAtividade;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericService;

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
 * @since 07/01/2015 - 14:26:07
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface FornecedorRamoAtividadeService extends GenericService<FornecedorRamoAtividade, Long> {

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>	
	 *
	 * Método responsável por verificar se existe algum <code>FornecedorRamoAtividade</code> vinculado à alguma das classificações passadas.
	 *
	 * @author geovane.filho
	 *
	 * @param idsClassificacoesImpactantes Ids das classificações de materiais a verificarem se existe vinculos.
	 * 
	 * @param usuarioLogado 
	 * 
	 * @return <code>true</code> caso exista vinculo de alguma classificação com algum ramo atividade e <code>false</code> caso contrário.
	 */
	boolean existeRamoAtividadeVinculadoAClassificacao(Set<Long> idsClassificacoesImpactantes, Usuario usuarioLogado);

}
