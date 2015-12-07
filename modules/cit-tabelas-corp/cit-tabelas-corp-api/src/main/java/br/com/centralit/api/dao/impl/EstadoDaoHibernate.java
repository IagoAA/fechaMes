package br.com.centralit.api.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Sort;

import br.com.centralit.api.dao.EstadoDao;
import br.com.centralit.api.model.Estado;
import br.com.centralit.api.model.Regiao;
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
 * <p>
 * <b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a>
 * </p>
 *
 * <p>
 * <b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a>
 * </p>
 *
 * @since 09/12/2014 - 14:29:05
 *
 * @version 1.0.0
 *
 * @author rogerio.costa
 *
 */
@Repository("estadoDao")
public class EstadoDaoHibernate extends CitGenericDAOImpl implements EstadoDao {

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 *
	 * @param classs
	 */
	public EstadoDaoHibernate() {

		super(Estado.class);
	}

	/**
	 * Método responsável por listar a Endiade<code>Estado</code>
	 *
	 * @author rogerio.costa
	 *
	 * @return Collection<Cidade>
	 */
	public Collection<Estado> listarEstado(Regiao regiao, String nome) {

		SearchSeven search = new SearchSeven();

		search.addFilterILike("nome", "%" + nome + "%");

		search.addFilterEqual("regiao.id", regiao.getId());

		if (UtilObjeto.isReferencia(regiao.getPais())) {

			search.addFilterEqual("regiao.pais.id", regiao.getPais().getId());
		}

		search.addFilterEmpty("dataInativo");

		search.addSort(Sort.asc("nome"));

		search.setMaxResults(10);

		return this.search(search, Estado.class);

	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por verificar se a regiao a ser excluida é utilizada por estado
	 *
	 * @author iago.almeida
	 *
	 * @param idRegiao
	 * @return boolean
	 */
	@Override
	public boolean existeEstadoVinculadoARegiao(Long idRegiao) {

		SearchSeven search = new SearchSeven();

		search.addFilterIn("regiao.id", idRegiao);

		search.addFilterEmpty("dataInativo");

		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;

	}

	/**
	 * Método responsável por verifica se existe um estado cadastrado com o mesmo nome.
	 * 
	 * @author luis.camargo
	 * 
	 *  @param estado
	 *  @return boolean
	 */
	@Override
	public boolean existeEstadoMesmoNome(Estado estado) {
		SearchSeven search = new SearchSeven();
		if(estado.getId() != null) {
			search.addFilterNotEqual("id", estado.getId());
		}
		search.addFilterILike("nome", estado.getNome());
		return this.count(search) > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
