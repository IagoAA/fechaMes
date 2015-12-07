package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.NivelAutoridadeDao;
import br.com.centralit.api.model.NivelAutoridade;
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
 * @since 22/06/2015 - 15:51:39
 * 
 * @version 1.0.0
 * 
 * @author lucas.ribeiro - (<a href="mailto:lucas.ribeiro@centralit.com.br">lucas.ribeiro@centralit.com.br</a>)
 * 
 */
@Repository("nivelAutoridadeDao")
public class NivelAutoridadeDaoHibernate extends CitGenericDAOImpl implements NivelAutoridadeDao {

	public NivelAutoridadeDaoHibernate() {

		super(NivelAutoridade.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean verificaSeNomeIsUnico(String nome, Long organizacaoId) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", nome);
		search.addFilterEqual("organizacao.id", organizacaoId);

		return this.count(search) > 0;
	}
	
	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">594</a>
	 * </p>
	 * 
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por verificar a existencia de nivel de autoridade com a hierarquia
	 * 
	 * @author juliana.barbosa
	 * 
	 * @param hierarquia, organizacaoId
	 * @return
	 */	
	public boolean existeHierarquiaNivelAutoridade(Integer hierarquia, Long organizacaoId) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("hierarquia", hierarquia);
		search.addFilterEqual("organizacao.id", organizacaoId);

		return this.count(search) > 0;
	}	
}
