package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.com.centralit.api.dao.BairroDao;
import br.com.centralit.api.model.Bairro;
import br.com.centralit.api.model.Cidade;
import br.com.centralit.api.viewHelper.EntidadeNomeBuscaVH;
import br.com.centralit.framework.dao.arquitetura.CitGenericDAOImpl;
import br.com.centralit.framework.dao.arquitetura.SearchSeven;
import br.com.centralit.framework.util.UtilObjeto;

import com.googlecode.genericdao.search.Sort;

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
 * <b>Title: </b> BairroDaoHibernate
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
 * @author rogerio.cassimiro
 *
 */
@Repository("bairroDao")
public class BairroDaoHibernate extends CitGenericDAOImpl implements BairroDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public BairroDaoHibernate() {

		super(Bairro.class);
	}

	/**
	 * Método responsável por listar a entidade <code>Bairro</code>
	 *
	 * @author rogerio.costa
	 *
	 * @param estado
	 *
	 * @return Collection<Bairro>
	 */
	public Collection<Bairro> listarBairro(EntidadeNomeBuscaVH<Cidade> cidade) {

		SearchSeven search = new SearchSeven(this.persistentClass);

		search.addFilterEqual("cidade.id", cidade.getObjeto().getId());

		search.addFilterEqual("cidade.estado.id", cidade.getObjeto().getEstado().getId());

		search.addFilterEqual("cidade.estado.regiao.id", cidade.getObjeto().getEstado().getRegiao().getId());

		if (UtilObjeto.isReferencia(cidade.getObjeto().getEstado().getRegiao().getPais())) {

			search.addFilterEqual("cidade.estado.regiao.pais.id", cidade.getObjeto().getEstado().getRegiao().getPais().getId());

		}

		search.addFilterILike("nome", "%" + cidade.getNome() + "%");

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
	 * Método responsável por verificar se a cidade a ser excluida é utilizada por bairro
	 *
	 * @author iago.almeida
	 *
	 * @param idCidade
	 * @return boolean
	 */
	@Override
	public boolean existeBairroVinculadoACidade(Long idCidade) {

		SearchSeven search = new SearchSeven();

		search.addFilterIn("cidade.id", idCidade);

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;

	}

	/**
	 * Método responsável por verificar se existe um bairro já cadastrado com o mesmo nome para uma cidade.
	 * 
	 * @author luis.camargo
	 * 
	 * @param bairro
	 * @return boolean
	 */
	@Override
	public boolean existeBairroMesmoNomePorCidade(Bairro bairro) {
		SearchSeven search = new SearchSeven();
		if (bairro.getId() != null) {
			search.addFilterNotEqual("id", bairro.getId());
		}
		search.addFilterILike("nome", bairro.getNome());
		search.addFilterEqual("cidade.id", bairro.getCidade().getId());
		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
