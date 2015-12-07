package br.com.centralit.api.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ConfiguracaoDao;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.model.Configuracao;
import br.com.centralit.framework.model.arquitetura.PersistentObject;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Search;

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
 * @since 24/02/2015 - 14:31:22
 *
 * @version 1.0.0
 *
 * @author renato.jesus
 *
 */
@Repository("configuracaoDao")
public class ConfiguracaoDaoHibernate extends CitGenericDAOImpl implements ConfiguracaoDao {

	public ConfiguracaoDaoHibernate() {

		super(Configuracao.class);

	}

	@Override
	public String getParametro(String chave) {

		Query query = em().createQuery("select p.valor from ConfiguracaoParametroSistema p where p.chave = :chave");
		query.setParameter("chave", chave);

		return (String) query.getSingleResult();
	}

	@Override
	public String getParametroByConfiguracao(String chave, Long idConfiguracaoOrganizacao) {

		Query query = em().createQuery("select p.valor from ConfiguracaoParametroSistema p where p.chave = :chave and p.configuracao.id = :idConf");
		query.setParameter("chave", chave);
		query.setParameter("idConf", idConfiguracaoOrganizacao);

		return (String) query.getSingleResult();
	}

	/**
	 * <p>
	 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
	 * </p>
	 *
	 * <p>
	 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
	 * </p>
	 * 
	 * Método responsável por obter a configuração por organizacao
	 * 
	 * @author renato.jesus
	 * 
	 * @return Configuracao
	 */
	@Override
	public Configuracao getConfiguracao(Long idOrganizacao) {
		
		SearchSeven searchSeven = new SearchSeven(this.persistentClass);
		
		searchSeven.addFilterEqual("organizacao.id", idOrganizacao);
		
		return searchUnique(searchSeven);
	}


	/**
	 * <p>
	 * <b>Regra(s) de negócio:</b> Consulta entidade de acordo com os parametros,
	 * caso ela não exista, salva o registro
	 * </p>
	 *
	 * @author gilberto.nery
	 * @date 09/09/2015
	 *
	 * @return PersistentObject - Entidade que foi salva ou entidade que estava cadastrada
	 */
	@Override
	public PersistentObject saveIfNotExist(PersistentObject entity) {

		Configuracao configuracao = (Configuracao) entity;

		Search search = new Search();

		search.addFilterEqual("organizacao.id", configuracao.getOrganizacao().getId());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}
}