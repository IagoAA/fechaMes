package br.com.centralit.api.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.ClasseParceiroDao;
import br.com.centralit.api.model.ClasseParceiro;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
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
 * @since 05/01/2015 - 15:25:51
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Repository("classeParceiroDao")
public class ClasseParceiroDaoHibernate extends CitGenericDAOImpl implements ClasseParceiroDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public ClasseParceiroDaoHibernate() {

		super(ClasseParceiro.class);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por obter através do codigo do dominioClasseParceiro
	 *
	 * @author rogerio.costa
	 *
	 * @param codigo
	 *
	 * @return ClasseParceiro
	 */
	public ClasseParceiro obterPorCodigoDominioTipParceiro(Long codigo) {

		SearchSeven search = new SearchSeven();

		search.addFilterEqual("dominioTipoParceiro.codigo", codigo);

		search.setMaxResults(1);

		return searchUnique(search, ClasseParceiro.class);

	}


	/**
	 * <p>
	 * <b>Regra(s) de negócio:</b> Consulta entidade de acordo com os parametros(dominio),
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

		ClasseParceiro classeParceiro = (ClasseParceiro) entity;

		Search search = new Search();

		search.addFilterEqual("dominioTipoParceiro.id", classeParceiro.getDominioTipoParceiro().getId());

		search.setMaxResults(1);

		PersistentObject obj = searchUnique(search);

		if (UtilObjeto.isReferencia(obj)) {

			return obj;

		} else {

			return this.save(entity);

		}
	}
}