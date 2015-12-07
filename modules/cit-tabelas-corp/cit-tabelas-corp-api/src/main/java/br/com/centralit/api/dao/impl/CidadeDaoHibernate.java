package br.com.centralit.api.dao.impl;

import java.util.Collection;



import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Sort;

import br.com.centralit.api.dao.CidadeDao;
import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.model.Estado;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.util.UtilObjeto;

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
 * @since 09/12/2014 - 08:33:12
 *
 * @version 1.0.0
 *
 * @author wilker.machado
 *
 */
@Repository("cidadeDao")
public class CidadeDaoHibernate extends CitGenericDAOImpl implements CidadeDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public CidadeDaoHibernate() {

		super(Cidade.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Cidade> listarCidades(EntidadeNomeBuscaVH<Estado> estado) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		if (UtilObjeto.isReferencia(estado.getObjeto())){
			
			search.addFilterEqual("estado.id", estado.getObjeto().getId());
			
			search.addFilterEqual("estado.regiao.id", estado.getObjeto().getRegiao().getId());

			if (UtilObjeto.isReferencia(estado.getObjeto().getRegiao().getPais())) {
				
				search.addFilterEqual("estado.regiao.pais.id", estado.getObjeto().getRegiao().getPais().getId());
			}
			
		}

		search.addFilterILike("nome", "%" + estado.getNome() + "%");

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, this.persistentClass);
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se o estado a ser excluido é utilizado por cidade
	 *
	 * @author iago.almeida
	 *
	 * @param idEstado
	 * @return boolean
	 */
	@Override
	public boolean existeCidadeVinculadoAoEstado(Long idEstado) {

		SearchSeven search = new SearchSeven();

		search.addFilterIn("estado.id", idEstado);

		search.addFilterEmpty("dataInativo");

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Método responsável por verificar se existe uma cidade já cadastrada com o mesmo nome para um estado.
	 * 
	 * @author luis.camargo
	 * 
	 * @param cidade
	 * @return boolean
	 */
	@Override
	public boolean existeCidadeMesmoNomePorEstado(Cidade cidade) {
		SearchSeven search = new SearchSeven();
		if (cidade.getId() != null) {
			search.addFilterNotEqual("id", cidade.getId());
		}
		search.addFilterILike("nome", cidade.getNome());
		search.addFilterEqual("estado.id", cidade.getEstado().getId());
		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
