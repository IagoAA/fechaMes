package br.com.centralit.api.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.FornecedorRamosAtividadeDao;
import br.com.centralit.api.model.FornecedorRamoAtividade;
import br.com.centralit.api.service.FornecedorRamoAtividadeService;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;

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
 * @since 07/01/2015 - 14:26:51
 * 
 * @version 1.0.0
 * 
 * @author rogerio.costa
 * 
 */
@Service("fornecedorRamoAtividadeService")
public class FornecedorRamoAtividadeServiceImpl extends GenericServiceImpl<FornecedorRamoAtividade, Long> implements FornecedorRamoAtividadeService {

	/** Atributo fornecedorRamosAtividadeDao. */
	private FornecedorRamosAtividadeDao fornecedorRamosAtividadeDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param fornecedorRamosAtividadeDao
	 */
	@Autowired
	private FornecedorRamoAtividadeServiceImpl( FornecedorRamosAtividadeDao fornecedorRamosAtividadeDao ) {

		this.dao = fornecedorRamosAtividadeDao;

		this.fornecedorRamosAtividadeDao = fornecedorRamosAtividadeDao;

	}

	/**
	 * Retorna o valor do atributo <code>fornecedorRamosAtividadeDao</code>
	 * 
	 * @return <code>FornecedorRamosAtividadeDao</code>
	 */
	public FornecedorRamosAtividadeDao getFornecedorRamosAtividadeDao() {

		return fornecedorRamosAtividadeDao;
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
		return fornecedorRamosAtividadeDao.existeRamoAtividadeVinculadoAClassificacao(idsClassificacoes, usuarioLogado);
	}

}
