package br.com.centralit.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.centralit.api.dao.SearchParamsDao;
import br.com.centralit.api.service.SearchParamsService;
import br.com.centralit.framework.model.SearchParams;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;


/**
 * <p><img src="http://centralit.com.br/images/logo_central.png"></p>
 *
 * <p><b>Company: </b> Central IT - Governança Corporativa - </p>
 *
 * <p><b>Title: </b></p>
 *
 * <p><b>Description: </b></p>
 *
 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
 *
 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
 *
 * @since 13/02/2015 - 10:10:05
 *
 * @version 1.0.0
 *
 * @author iago.almeida
 *
 */
@Service("searchParamsService")
public class SearchParamsServiceImpl extends GenericServiceImpl<SearchParams, Long> implements SearchParamsService{

	private SearchParamsDao searchParamsDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * @param searchParamsDao
	 */
	@Autowired
	public SearchParamsServiceImpl( SearchParamsDao searchParamsDao ) {

		this.dao = searchParamsDao;

		this.searchParamsDao = searchParamsDao;
	}

	/**
	 * <p><b>Iniciativa(s):</b> <a href="LINK_PORTAL">NUMERO_INICIATIVA</a></p>
	 *
	 * <p><b>Regra(s) de negócio:</b> <a href="LINK_PORTAL">NUMERO_REGRA_DE_NEGOCIO</a></p>
	 *
	 * Método responsável por buscar lista de filtros por pagina de usuario
	 *
	 * @author iago.almeida
	 *
	 * @param idPaginaUsuario
	 * @return
	 */
	@Override
	public List<SearchParams> findPorIdPaginaUsuario(Long idPaginaUsuario) {

		return this.searchParamsDao.findPorIdPaginaUsuario(idPaginaUsuario);
	}

}
