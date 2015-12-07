package br.com.centralit.api.dao;

import java.util.Set;

import br.com.centralit.framework.dao.arquitetura.CitGenericDAO;
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
 * @since 07/01/2015 - 14:23:25
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
public interface FornecedorRamosAtividadeDao extends CitGenericDAO {

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
	 * @param usuarioLogado 
	 * 
	 * @return <code>true</code> caso exista vinculo de alguma classificação com algum ramo atividade e <code>false</code> caso contrário.
	 */
	public boolean existeRamoAtividadeVinculadoAClassificacao(Set<Long> idsClassificacoes, Usuario usuarioLogado);

}
